package cards.trap;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;
import cards.CardBase;

public abstract class TrapBase extends CardBase {
	protected int numberToKillParty;

	public TrapBase() {
	}

	@Override
	public abstract boolean runEffect(Board board, List<Player> players, Log log);

}
