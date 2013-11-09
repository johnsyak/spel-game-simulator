package cards;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;




public abstract class CardBase {
	
	protected int value;
	protected int secondaryValue;
	protected boolean bombable;
	protected boolean ropeable;
	public CardType type;
	public String name;
	
	public CardBase() {
	}
	
	public abstract void runEffect(Board board, List<Player> players, Log log);

}
