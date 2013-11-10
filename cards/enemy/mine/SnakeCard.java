package cards.enemy.mine;

import java.util.List;

import cards.CardType;
import cards.enemy.EnemyBase;

import main.Log;
import model.Board;
import model.Player;

public class SnakeCard extends EnemyBase {

	public SnakeCard() {
		this.type = CardType.SNAKE;
		this.name = "a snake";
		this.numberToKillParty = 3;
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		if (board.contains(CardType.SNAKE, 3)) {
			for (Player p : players) {
				p.die();
				log.logDeath(p, this);
			}
			//TODO: remove the bat from the deck if it kills someoen
		}
		return false;
	}

}
