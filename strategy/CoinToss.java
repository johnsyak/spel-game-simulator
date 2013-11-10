package strategy;

import model.ActionType;
import model.Board;
import model.Player;

public class CoinToss extends StrategyBase {

	public CoinToss() {
	}

	@Override
	public ActionType decide(Board board, Player player) {
		ActionType result;
		if (Math.random()>.5)
			result = ActionType.DEEPER;
		else
			result = ActionType.LEAVE;
		return result;
	}

}
