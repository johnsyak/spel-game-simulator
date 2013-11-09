package cards;

import java.util.Arrays;
import java.util.List;

public class CardList {

	private CardList() {

	}
	
	private static List<CardType> ENEMIES = Arrays.asList(CardType.BAT);
	public static boolean isEnemy(CardBase card){
		return ENEMIES.contains(card.type);
	}
	
	private static List<CardType> TREASURES = Arrays.asList(CardType.TREASURE);
	public static boolean isTreasure(CardBase card){
		return TREASURES.contains(card.type);
	}

}
