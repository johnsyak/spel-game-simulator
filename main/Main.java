package main;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runner game = new Runner();
		try {
			game.runGame();
		} catch (Exception e) {

			e.printStackTrace();
		}

		game.printLog();
	}

}
