package main;

import stats.MonteCarlo;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 0;
		Runner game = new Runner();
		MonteCarlo mc = new MonteCarlo();
		try {
			while (i++ < 10000) {
				mc.addResult(game.runGame());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (game.log.loggingOn)
			game.printLog();
		else
			mc.totals();
	}

}
