package strategy;

import model.ActionType;
import model.Board;
import model.Player;

public class SmartBot extends StrategyBase {

	public SmartBot() {
	}

	@Override
	public ActionType decide(Board board, Player player) {
		boolean rand = Math.random()>.5;
		
		if (board.hasRopeLoot() ){
			return ActionType.ROPE;
		} else if (board.hasBombLoot() && (rand)) {
			return ActionType.BOMB;
		} else {
			return StratList.oneUp.decide(board, player);
		}
	}

}
