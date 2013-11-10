package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import cards.CardBase;
import cards.enemy.mine.BatCard;
import cards.enemy.mine.ChillCard;
import cards.enemy.mine.SnakeCard;
import cards.enemy.mine.SpiderCard;
import cards.trap.ArrowTrapCard;
import cards.trap.BoulderCard;
import cards.treasure.IdolCard;
import cards.treasure.TreasureCard;

public class Deck {

	private List<CardBase> cards;
	public RoundType type;

	public Deck(RoundType type) throws FileNotFoundException {
		this.type = type;
		cards = new ArrayList<CardBase>();
		build();
		shuffle();
	}

	public void shuffle() {
		long seed = System.nanoTime();
		Collections.shuffle(cards, new Random(seed));
	}

	public void addCard(CardBase card) {
		cards.add(card);
	}

	public CardBase drawCard() {
		// TODO: what if we're out of cards?
		CardBase card = cards.get(0);
		cards.remove(0);
		return card;
	}

	private void build() throws FileNotFoundException {
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

	private void buildMineDeck() throws FileNotFoundException {
		List<DeckCard> setup = readDeck("mine");
		for (DeckCard dc : setup) {
			switch (dc.name) {
			case "bat":
				for (int i = 0; i < dc.numCards; i++)
					cards.add(new BatCard());
				break;
			case "gold":
				for (int i = 1; i <= dc.numCards; i++) {
					if (i == 6)// 6 + ropeable
						cards.add(new TreasureCard(i));
					else if (i == 8)// 8 + bombable
						cards.add(new TreasureCard(i));
					else
						cards.add(new TreasureCard((i)));
				}
				break;
			case "idol":
				for (int i = 0; i < dc.numCards; i++)
					cards.add(new IdolCard(5));
				break;
			case "chill":
				for (int i = 0; i < dc.numCards; i++)
					cards.add(new ChillCard());
				break;
			case "boulder":
				for (int i = 0; i < dc.numCards; i++)
					cards.add(new BoulderCard());
				break;
			case "arrowtrap":
				for (int i = 0; i < dc.numCards; i++)
					cards.add(new ArrowTrapCard());
				break;
			case "snake":
				for (int i = 0; i < dc.numCards; i++)
					cards.add(new SnakeCard());
				break;
			case "spider":
				for (int i = 0; i < dc.numCards; i++)
					cards.add(new SpiderCard());
				break;
			}

		}
	}

	private List<DeckCard> readDeck(String fname) throws FileNotFoundException {
		List<DeckCard> setup = new ArrayList<DeckCard>();
		Scanner s = null;

		try {
			s = new Scanner(new BufferedReader(new FileReader(
					"C:\\simConfigs\\" + fname + ".deck")));

			while (s.hasNext()) {
				String line = s.next().replace(" ", "");
				if (line.contains("deck") || line.isEmpty())
					continue;

				String[] rs = line.split(":");
				setup.add(new DeckCard(rs[0], Integer.parseInt(rs[1])));

				// System.out.println("read from file: " + line);
			}
		} finally {
			if (s != null) {
				s.close();
			}
		}

		return setup;
	}

	private class DeckCard {
		public DeckCard(String s, int n) {
			this.numCards = n;
			this.name = s;
		}

		public int numCards;
		public String name;
	}

}
