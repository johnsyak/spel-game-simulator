package cards;

import java.util.Arrays;
import java.util.List;

public class CardList {

	private CardList() {

	}

	private final static List<CardType> ENEMIES = Arrays.asList(CardType.ENEMY,
			CardType.BAT, CardType.CHILL, CardType.GHOST, CardType.SNAKE,
			CardType.SPIDER);

	public static boolean isEnemy(CardBase card) {
		return ENEMIES.contains(card.type);
	}

	private final static List<CardType> TRAPS = Arrays.asList(CardType.BOULDER,
			CardType.ARROWTRAP);

	public static boolean isTrap(CardBase card) {
		return TRAPS.contains(card.type);
	}

	private final static List<CardType> TREASURES = Arrays
			.asList(CardType.TREASURE);

	public static boolean isTreasure(CardBase card) {
		return TREASURES.contains(card.type);
	}

}
