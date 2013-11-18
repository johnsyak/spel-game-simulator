package stats;

import java.util.List;

import model.Player;
import cards.CardBase;

public class GameResult {
	
	public int strategy;
	public List<Integer> cardIDs; 
	
	public GameResult(Player winner, List<CardBase> flippedCards) {
		strategy = winner.strategy().id;
	}

}
