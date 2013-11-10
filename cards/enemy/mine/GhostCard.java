package cards.enemy.mine;

import java.util.List;

import cards.CardType;
import cards.enemy.EnemyBase;

import main.Log;
import model.Board;
import model.Player;

public class GhostCard extends EnemyBase {

	public GhostCard() {
		this.type = CardType.BAT;
		this.name = "a ghost";
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		for (Player p : players) {
			p.die();
			log.logDeath(p, this);
		}
		// TODO: remove the ghost from the deck if it kills someone
		return false;
	}

}
