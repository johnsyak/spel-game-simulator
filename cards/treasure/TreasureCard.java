package cards.treasure;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;
import cards.CardBase;
import cards.CardType;

public class TreasureCard extends CardBase {
	public int value;

	public TreasureCard(int value) {
		this.value = value;
		this.type = CardType.TREASURE;
		this.name = "" + this.value + " gold";
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {

		int[] result = board.splitGold(value, players);
		log.logGoldSplit(players, result[0], result[1]);

		return false;
	}

}
