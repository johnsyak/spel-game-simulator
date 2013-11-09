package cards;

import java.util.List;

import model.Player;




public abstract class CardBase {
	
	protected int value;
	protected int secondaryValue;
	protected boolean bombable;
	protected boolean ropeable;
	protected CardType type;
	
	public CardBase() {
	}
	
	public abstract void runEffect(List<Player> players);

}
