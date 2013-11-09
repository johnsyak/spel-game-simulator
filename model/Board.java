package model;

import java.util.ArrayList;
import java.util.List;

import strategy.StratList;
import strategy.ThresholdGold;

import main.Log;

import actions.ActionType;
import cards.CardBase;
import cards.CardType;

public class Board {

	private List<CardBase> flippedCards;
	private List<Player> players;
	public int gold;
	public List<Deck> decks;

	public Board(int numPlayers) {
		players = new ArrayList<Player>();
		for (int i = 0; i < numPlayers; i++) {
			Player p;
			if (Math.random()>.5)
				p = new Player("P(Wait)_" + (i + 1), StratList.waitGold2);
			else
				p = new Player("P(50%)_" + (i + 1), StratList.coinToss);
			
			p.resetForNewRound();
			players.add(p);
		}

		flippedCards = new ArrayList<CardBase>();
		decks = new ArrayList<Deck>();

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

	public List<Player> activePlayers() {
		List<Player> result = new ArrayList<Player>();
		for (Player p : players) {
			if (p.active)
				result.add(p);
		}
		return result;
	}

}
