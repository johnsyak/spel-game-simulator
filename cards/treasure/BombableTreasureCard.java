package cards.treasure;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;

public class BombableTreasureCard extends TreasureCard {

	public BombableTreasureCard(int value) {
		super(value);
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		super.runEffect(board, players, log);

		return false;
	}

}
