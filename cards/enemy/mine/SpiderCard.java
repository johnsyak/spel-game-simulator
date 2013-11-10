package cards.enemy.mine;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;
import cards.CardType;
import cards.enemy.EnemyBase;

public class SpiderCard extends EnemyBase {

	public SpiderCard() {
		this.type = CardType.SPIDER;
		this.name = "the spider";
		this.recurring = true;
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		if (players.size()==1 ){
			players.get(0).die();
			log.logDeath(players.get(0), this);
		}
		return false;
	}

}
