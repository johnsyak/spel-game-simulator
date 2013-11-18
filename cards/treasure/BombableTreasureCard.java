package cards.treasure;

import java.util.List;

import cards.CardType;

import main.Log;
import model.Board;
import model.Player;

public class BombableTreasureCard extends TreasureCard {

	public int bombValue;
	
	public BombableTreasureCard(int value, int bombValue) {
		super(value);
		this.bombValue = bombValue;
		this.type = CardType.BOMB;
		this.available = true;
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		super.runEffect(board, players, log);
		return false;
	}

}
