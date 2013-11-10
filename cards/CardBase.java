package cards;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;




public abstract class CardBase {
	
	
	public CardType type;
	public String name;
	public boolean recurring;
	public boolean available;
	
	public CardBase() {
		this.type = CardType.DEFAULT;
		this.name = "default";
	}
	
	public abstract boolean runEffect(Board board, List<Player> players, Log log);

}
