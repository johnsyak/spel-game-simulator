package cards.enemy;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;
import cards.CardBase;

public abstract class EnemyBase extends CardBase {
	
	protected int numberToKillParty;
	
	public EnemyBase() {
	}

	@Override
	public abstract void runEffect(Board board, List<Player> players, Log log);

}
