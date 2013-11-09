package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cards.CardBase;
import cards.enemy.BatCard;
import cards.treasure.TreasureCard;

public class Deck {

	private List<CardBase> cards;
	public RoundType type;

	public Deck(RoundType type) {
		this.type = type;
		cards = new ArrayList<CardBase>();
		build();
		shuffle();
	}

	private void shuffle() {
		long seed = System.nanoTime();
		Collections.shuffle(cards, new Random(seed));
	}

	public CardBase drawCard() {
		// TODO: what if we're out of cards?
		CardBase card = cards.get(0);
		cards.remove(0);
		return card;
	}

	private void build() {
		switch (type) {
		case MINE:
			buildMineDeck();
			break;
		case JUNGLE:
		case ICECAVE:
		case TEMPLE:
		case HELL:
		default:
			break;
		}
	}

	private void buildMineDeck() {

		for (int i = 0; i < 8; i++) {
			cards.add(new TreasureCard(i + 1, false, false));
		}
		for (int j=0; j < 3; j++){
			cards.add(new BatCard());
		}
	}

}
