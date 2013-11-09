package main;

import model.Board;
import model.Player;
import cards.TreasureCard;

public class Runner {

	private Log log;

	public Runner() {
		log = new Log();
	}

	public void runGame() throws Exception {
		Board board = new Board(5);

		for (int i = 0; i < 5; i++) {
			chooseActions(board);

			flipNextCard(board);
		}

	}

	private void flipNextCard(Board board) {
		board.addCard(new TreasureCard(5, false, false));
	}

	private void chooseActions(Board board) throws Exception {
		for (Player p : board.activePlayers()) {
			p.choose(log);
		}
	}

	public void printLog() {
		for (String s : log.lines()) {
			System.out.println(s);
		}
	}

}
