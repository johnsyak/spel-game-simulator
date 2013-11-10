package cards.treasure;

import java.util.List;

import cards.CardType;

import main.Log;
import model.Board;
import model.Player;

public class IdolCard extends TreasureCard {
	
	private int valueToAddToBoard;
	
	public IdolCard(int value) {
		super(value);
		this.available = true;
		this.type = CardType.IDOL;
		this.name = "an idol statue worth " + this.value;
		this.valueToAddToBoard = value;
	}

	
	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		//should also run this on leaving... maybe add a new section called takeables
		if (players.size() == 1 && this.available) {
			Player lucky = players.get(0);
			lucky.addGold(this.value);
			this.available = false;

			log.logString(lucky.name + " got an idol statue worth " + this.value);
		} 
		
		board.idolValue += valueToAddToBoard;
		valueToAddToBoard = 0;
		
		return false;
	}

}
