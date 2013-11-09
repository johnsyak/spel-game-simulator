package cards.enemy;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;
import cards.CardType;

public class BatCard extends EnemyBase {

	public BatCard() {
		this.type = CardType.BAT;
		this.name = "a bat";
	}

	@Override
	public void runEffect(Board board, List<Player> players, Log log) {
		if (board.contains(CardType.BAT, 2)) {
			for (Player p : players) {
				p.die();
				log.logDeath(p, this);
			}

		}
	}

}
