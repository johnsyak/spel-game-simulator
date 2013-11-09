package model;

import java.util.List;

import cards.CardBase;

public class Deck {

	private List<CardBase> cards;
	public RoundType type;

	public Deck(RoundType type) {
		this.type = type;
		build();
	}

	private void build() {
		switch (type) {
		case MINE:
		case JUNGLE:
		case ICECAVE:
		case TEMPLE:
		case HELL:
		default:
			break;
		}
	}

}
