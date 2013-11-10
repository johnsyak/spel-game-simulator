package cards.enemy.mine;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;
import cards.CardType;
import cards.enemy.EnemyBase;

public class BatCard extends EnemyBase {

	public BatCard() {
		this.type = CardType.BAT;
		this.name = "a bat";
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		if (board.contains(CardType.BAT, 2)) {
			for (Player p : players) {
				p.die();
				log.logDeath(p, this);
			}
			//TODO: remove the bat from the deck if it kills someoen
		}
		
		return false;
	}

}
