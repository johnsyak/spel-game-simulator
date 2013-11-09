package strategy;

import actions.ActionType;

public abstract class StrategyBase {

	public StrategyBase() {
	}

	public abstract ActionType decide();

}
