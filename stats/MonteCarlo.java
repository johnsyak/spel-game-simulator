package stats;

import java.util.ArrayList;
import java.util.List;

public class MonteCarlo {

	private List<GameResult> results;

	public MonteCarlo() {
		results = new ArrayList<GameResult>();
	}

	public void addResult(GameResult gr) {
		results.add(gr);
	}

	public void totals() {
		int toss = 0;
		int wait = 0;
		int oneUp = 0;
		int smart = 0;

		for (GameResult gr : results) {
			switch (gr.strategy) {
			case 1:
				toss += 1;
				break;
			case 2:
				wait += 1;
				break;
			case 3:
				oneUp += 1;
				break;
			case 4:
				smart += 1;
				break;

			default:
				break;
			}
		}
		
		System.out.println("WINNERS:");
		
		System.out.println("Coin Toss: " + toss);
		System.out.println("Wait-2:    " + wait);
		System.out.println("1UP:       " + oneUp);
		System.out.println("Smart:     " + smart);
		
		

	}

}
