package main;

import java.util.List;

import model.ActionType;
import model.Board;
import model.Deck;
import model.Player;
import model.RoundType;
import cards.CardBase;
import cards.CardType;
import cards.treasure.BombableTreasureCard;
import cards.treasure.RopeableTreasureCard;
import cards.treasure.TreasureCard;

public class Runner {

	private Log log;

	public Runner() {
		log = new Log();
	}

	public void runGame() throws Exception {
		Board board = new Board(5);

		// build decks
		board.decks.add(new Deck(RoundType.MINE));// building and shuffling
													// handled by constructor
		log.logString("Built Mine deck");

		while (board.activePlayers().size() > 0) {
			boolean skipCard = flipNextCard(board);
			if (skipCard)
				continue;

			bombsAndRopes(board);// handle bombs and ropes

			runRecurring(board);

			log.printGameState(board, board.allPlayers());

			if (board.activePlayers().size() == 0)
				break;

			chooseActions(board);

			if (board.activePlayers().size() == 0)
				break;
		}

	}

	private void bombsAndRopes(Board board) {
		List<Player> playersBombing = board.playersDoing(ActionType.BOMB);
		List<Player> playersRoping = board.playersDoing(ActionType.ROPE);

		if (board.contains(CardType.BOMB, 1) && playersBombing.size() > 0) {
			if (playersBombing.size() == 1) {
				Player bomber = playersBombing.get(0);

				// get bomb treasure totals
				int bombableTotalValue = 0;
				for (CardBase c : board.flippedCards) {
					if (c instanceof BombableTreasureCard) {
						BombableTreasureCard bc = (BombableTreasureCard) c;
						if (!bc.available)
							continue;
						bombableTotalValue += bc.bombValue;
						bc.bombValue = 0;
						bc.available = false;
					}

				}

				bomber.addGold(bombableTotalValue);

				log.logString(bomber.name + " threw a bomb and got gems worth "
						+ bombableTotalValue + " gold.");
			} else {
				log.logString(log.namesFromPlayerList(playersBombing)
						+ " threw bombs, but were unable to remove the gem(s).");
			}
		}

		if (board.contains(CardType.ROPE, 1) && playersRoping.size() > 0) {
			// get rope treasure totals
			int ropeValue = 0;
			int unsplittable = 0;
			for (CardBase c : board.flippedCards) {
				if (c instanceof RopeableTreasureCard) {
					TreasureCard rc = ((RopeableTreasureCard) c).ropeCard;

					if (playersRoping.size() == 1) {
						ropeValue += rc.value;
						rc.value = 0;
					} else {
						int splittable = rc.value
								- (rc.value % playersRoping.size());
						ropeValue += splittable;
						rc.value -= splittable;
						unsplittable += rc.value;// so can report how much sits
													// on ledge still
					}
				}
			}

			if (ropeValue > 0) {
				int reward = ropeValue / playersRoping.size();
				for (Player p : playersRoping) {
					p.addGold(reward);
				}
				log.logRopeSplit(playersRoping, reward, unsplittable);
			}
		}// end rope section
	}

	private void runRecurring(Board board) {
		for (CardBase c : board.cards()) {
			if (c.recurring)
				c.runEffect(board, board.activePlayers(), log);
		}
	}

	private boolean flipNextCard(Board board) {
		CardBase card = board.decks.get(0).drawCard();
		board.addCard(card);

		board.playersLeave(log);

		log.logFlip(card);

		return card
				.runEffect(board, board.playersDoing(ActionType.DEEPER), log);
	}

	private void chooseActions(Board board) throws Exception {
		for (Player p : board.activePlayers()) {
			p.choose(board, log);
		}
	}

	public void printLog() {
		for (String s : log.lines()) {
			System.out.println(s);
		}
	}

}
