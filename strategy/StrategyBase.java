package strategy;

import model.ActionType;
import model.Board;
import model.Player;

public abstract class StrategyBase {

	public StrategyBase() {
	}

	public abstract ActionType decide(Board board, Player player);

}
