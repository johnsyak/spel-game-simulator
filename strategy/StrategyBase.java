package strategy;

import model.ActionType;
import model.Board;
import model.Player;

public abstract class StrategyBase {

	public int id;

	public StrategyBase() {
		this.id = 0;
	}

	public abstract ActionType decide(Board board, Player player);

}
