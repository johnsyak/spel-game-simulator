package strategy;

import actions.ActionType;

public class CoinToss extends StrategyBase {

	public CoinToss() {
	}

	@Override
	public ActionType decide() {
		ActionType result;
		if (Math.random()>.5)
			result = ActionType.DEEPER;
		else
			result = ActionType.LEAVE;
		return result;
	}

}
