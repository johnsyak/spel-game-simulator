package cards.treasure;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;

public class RopeableTreasureCard extends TreasureCard {

	public RopeableTreasureCard(int value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		super.runEffect(board, players, log);
		

		return false;
	}

}
