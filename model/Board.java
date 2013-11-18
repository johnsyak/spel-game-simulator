package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.Log;
import strategy.StratList;
import cards.CardBase;
import cards.CardType;
import cards.treasure.BombableTreasureCard;
import cards.treasure.RopeableTreasureCard;

public class Board {

	public List<CardBase> flippedCards;
	private List<Player> players;
	public int gold;
	public List<Deck> decks;

	public int idolValue;

	public Board(int numPlayers) {
		players = new ArrayList<Player>();
		boolean has1UP = false;

		for (int i = 0; i < numPlayers; i++) {
			Player p;

			if (i == 0) {
				p = new Player("SmartBot", StratList.smartBot);
			} else if (Math.random() > .66) {
				p = new Player("P(Wait)_" + (i + 1), StratList.waitGold2);
			} else if (Math.random() > .33) {
				p = new Player("P(50%)_" + (i + 1), StratList.coinToss);
			} else {
				if (has1UP) {
					p = new Player("P(50%)_" + (i + 1), StratList.coinToss);
				} else {
					p = new Player("P(1UP)_" + (i + 1), StratList.oneUp);
					has1UP = true;
				}
			}

			p.resetForNewRound();
			players.add(p);
		}

		flippedCards = new ArrayList<CardBase>();
		decks = new ArrayList<Deck>();
		idolValue = 0;
	}

	public List<CardBase> cards() {
		return flippedCards;
	}

	public boolean contains(CardType type, int number) {
		boolean result = false;
		int threshold = 0;
		for (CardBase card : flippedCards) {
			if (card.type == type)
				threshold += 1;
		}
		if (threshold >= number)
			result = true;
		return result;
	}

	public boolean hasRopeLoot() {
		boolean result = false;
		if (this.contains(CardType.ROPE, 1)) {
			for (CardBase c : flippedCards) {
				if (c instanceof RopeableTreasureCard) {
					RopeableTreasureCard rc = (RopeableTreasureCard) c;
					if (rc.ropeCard.value > 0) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

	public boolean hasBombLoot() {
		boolean result = false;
		if (this.contains(CardType.BOMB, 1)) {
			for (CardBase c : flippedCards) {
				if (c instanceof BombableTreasureCard) {
					BombableTreasureCard bc = (BombableTreasureCard) c;
					if (bc.available) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

	public void addCard(CardBase card) {
		flippedCards.add(card);
	}

	public void addGold(int amount) {
		gold += amount;
	}

	public int[] splitGold(int amount, List<Player> players) {
		int[] result = { 0, amount };
		if (players.size() == 0)
			return result;

		int numplayers = players.size();
		int goldPerPlayer = (int) Math.floor(amount / numplayers);
		int leftOver = (int) Math.floor(amount % numplayers);

		gold += leftOver;

		for (Player p : players)
			p.addGold(goldPerPlayer);

		result[0] = goldPerPlayer;
		result[1] = leftOver;
		return result;
	}

	public List<Player> allPlayers() {
		return players;
	}

	public List<Player> playersDoing(ActionType action) {
		List<Player> result = new ArrayList<Player>();
		for (Player p : activePlayers()) {
			if (p.chosenAction == action)
				result.add(p);
		}
		return result;
	}

	public void playersLeave(Log log) {
		List<Player> leaving = new ArrayList<Player>();

		for (Player p : activePlayers())
			if (p.chosenAction == ActionType.LEAVE)
				leaving.add(p);

		if (leaving.size() > 0) {
			if (leaving.size() == 1) {
				List<CardBase> actionCardsReceived = new ArrayList<CardBase>();
				int goldReceived = gold;

				Player escapee = leaving.get(0);
				escapee.addGold(goldReceived);
				gold = 0;

				// TODO: other action cards like specs, rock, etc.

				log.logLeaving(escapee, actionCardsReceived, goldReceived);

				claimTakeables(escapee, log);
				idolValue = 0;
			} else {
				int[] result = splitGold(gold, leaving);
				gold = result[1];
				log.logLeaving(leaving, result[0], result[1]);

			}

			for (Player p : leaving) {
				p.keepGold();
				p.active = false;
			}

		}

	}

	private void claimTakeables(Player player, Log log) {
		for (CardBase c : flippedCards) {
			if (Arrays.asList(CardType.IDOL).contains(c.type) && c.available) {
				List<Player> p1 = new ArrayList<Player>();
				p1.add(player);

				c.runEffect(this, p1, log);
			}
		}
	}

	public List<Player> activePlayers() {
		List<Player> result = new ArrayList<Player>();
		for (Player p : players) {
			if (p.active)
				result.add(p);
		}
		return result;
	}

}
