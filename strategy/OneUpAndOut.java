package strategy;

import model.ActionType;
import model.Board;
import model.Player;

public class OneUpAndOut extends StrategyBase {

	public OneUpAndOut() {
		this.id=3;
	}

	@Override
	public ActionType decide(Board board, Player player) {
		int highestScore = 0;
		for (Player p : board.allPlayers()){
			if (!p.equals(player) && p.roundGold()>highestScore)
				highestScore = p.roundGold();
		}
		
		if (player.roundGold()>highestScore)
			return ActionType.LEAVE;
		else
			return ActionType.DEEPER;
	}

}
