package main;

import actions.ActionType;
import model.Board;
import model.Deck;
import model.Player;
import model.RoundType;
import cards.CardBase;
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
			flipNextCard(board);

			log.printGameState(board, board.allPlayers());

			if (board.activePlayers().size() == 0)
				break;

			chooseActions(board);

			if (board.activePlayers().size() == 0)
				break;
		}

	}

	private void flipNextCard(Board board) {
		CardBase card = board.decks.get(0).drawCard();
		board.addCard(card);

		board.playersLeave(log);

		log.logFlip(card);
		card.runEffect(board, board.playersDoing(ActionType.DEEPER), log);
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
