package cards;

import java.util.List;

import model.Player;


public class TreasureCard extends CardBase {

	public TreasureCard(int value, boolean bombable, boolean ropeable) {
		 this.value = value;
		 this.bombable = bombable;
		 this.ropeable = ropeable;
	}
	
	public TreasureCard(int value, boolean bombable, boolean ropeable, int secondaryValue) {
		 this.value = value;
		 this.bombable = bombable;
		 this.ropeable = ropeable;
		 this.secondaryValue = secondaryValue;
	}

	@Override
	public void runEffect(List<Player> players) {
		int numplayers = players.size();
		
	}

}
