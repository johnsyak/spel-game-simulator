package strategy;

import model.Board;
import model.Player;
import actions.ActionType;

public abstract class StrategyBase {

	public StrategyBase() {
	}

	public abstract ActionType decide(Board board, Player player);

}
