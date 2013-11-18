package cards.treasure;

import java.util.List;

import cards.CardType;

import main.Log;
import model.Board;
import model.Player;

public class RopeableTreasureCard extends TreasureCard {

	public TreasureCard ropeCard;

	public RopeableTreasureCard(int value) {
		super(value);
		this.type = CardType.ROPE;
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		super.runEffect(board, players, log);

		this.ropeCard = (TreasureCard) board.decks.get(0).drawCard(
				CardType.TREASURE);
		return false;
	}

}
