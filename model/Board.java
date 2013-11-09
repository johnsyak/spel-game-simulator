package model;

import java.util.ArrayList;
import java.util.List;

import actions.ActionType;
import cards.CardBase;

public class Board {

	private List<CardBase> flippedCards;
	private List<Player> players;

	public Board(int numPlayers) {
		players = new ArrayList<Player>();
		for (int i = 0; i < numPlayers; i++) {
			Player p = new Player("Player_" + (i + 1));
			p.resetForNewRound();
			players.add(p);
		}

		flippedCards = new ArrayList<CardBase>();

	}

	public List<CardBase> cards() {
		return flippedCards;
	}

	public void addCard(CardBase card) {
		flippedCards.add(card);
	}

	public List<Player> playersGoingDeeper() {
		List<Player> result = new ArrayList<Player>();
		for (Player p : activePlayers()) {
			if (p.chosenAction == ActionType.DEEPER)
				result.add(p);
		}
		return result;
	}

	public List<Player> activePlayers() {
		List<Player> result = new ArrayList<Player>();
		for (Player p : players) {
			if (p.active)
				result.add(p);
		}
		return result;
	}

}
