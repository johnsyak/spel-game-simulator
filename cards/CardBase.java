package cards;

import java.util.List;

import strategy.Player;



public class CardBase {
	
	private int value;
	private int secondaryValue;
	private boolean bombable;
	private boolean ropeable;
	private CardType type;
	
	public CardBase() {
		type = CardType.TREASURE;
	}
	
	public void runEffect(List<Player> players){
		
	}

}
