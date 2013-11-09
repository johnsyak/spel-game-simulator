package cards.treasure;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;
import cards.CardBase;
import cards.CardType;

public class TreasureCard extends CardBase {

	public TreasureCard(int value, boolean bombable, boolean ropeable) {
		this.value = value;
		this.bombable = bombable;
		this.ropeable = ropeable;
		this.type = CardType.TREASURE;
		this.name = "" + this.value + " gold";
	}

	public TreasureCard(int value, boolean bombable, boolean ropeable,
			int secondaryValue) {
		this.value = value;
		this.bombable = bombable;
		this.ropeable = ropeable;
		this.secondaryValue = secondaryValue;
	}

	@Override
	public void runEffect(Board board, List<Player> players, Log log) {
		int[] result = board.splitGold(value, players);
		log.logGoldSplit(result[0], result[1]);
	}

}
