package strategy;

import model.ActionType;
import model.Board;
import model.Player;

public class ThresholdGold extends StrategyBase {
	
	private int amount;
	
	public ThresholdGold(int amount) {
		this.amount = amount;
	}

	@Override
	public ActionType decide(Board board, Player player) {
		if (player.currGold() >= amount)
			return ActionType.LEAVE;
		else
			return ActionType.DEEPER;
	}

}
