package cards.trap;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;
import model.Tools;
import cards.CardType;

public class BoulderCard extends TrapBase {

	public BoulderCard() {
		this.type = CardType.BOULDER;
		this.name = "a boulder";
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		if (board.contains(CardType.BOULDER, 2)
				|| board.contains(CardType.BOULDER, 3)
				|| board.contains(CardType.BOULDER, 4)) {
			for (Player p : players) {
				if (!Tools.d20Check(board.cards().size() + 1)) {
					p.die();
					log.logDeath(p, this);
				}
			}
		}
		// TODO: remove the boulder from the deck if it kills someone
		return false;
	}

}
