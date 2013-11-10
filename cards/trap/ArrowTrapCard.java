package cards.trap;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;
import cards.CardType;

public class ArrowTrapCard extends TrapBase {

	public ArrowTrapCard() {
		this.type = CardType.ARROWTRAP;
		this.name = "an arrow trap";
		this.numberToKillParty = 2;
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		if (board.contains(CardType.ARROWTRAP, 2)) {
			for (Player p : players) {
				p.die();
				log.logDeath(p, this);
			}
			//TODO: remove the trap from the deck if it kills someoen
		}
		
		return false;
	}

}
