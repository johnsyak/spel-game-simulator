package main;

import java.util.ArrayList;
import java.util.List;

import model.ActionType;
import model.Board;
import model.Player;
import cards.CardBase;
import cards.CardList;

public class Log {
	private List<String> log;

	public Log() {
		log = new ArrayList<String>();
	}

	public void logString(String entry) {
		log.add(entry);
	}

	public void logAction(String name, ActionType chosenAction)
			throws Exception {
		String chosenStr;

		switch (chosenAction) {
		case DEEPER:
			chosenStr = "to go deeper";
			break;
		case LEAVE:
			chosenStr = "to leave";
			break;
		default:
			throw new Exception("LogAction Exception");
		}

		log.add(name + " chose " + chosenStr);
	}

	public void logLeaving(Player player, List<CardBase> cards, int amount) {
		String itemsString = "";
		if (cards.size() > 0) {
			itemsString += " and items: ";
			for (CardBase c : cards)
				itemsString += c.name + ",";
		}

		log.add(player.name + " receives " + amount + " gold" + itemsString);
	}

	public void logLeaving(List<Player> players, int amount, int remaining) {
		String leavingPlayersNames = "";
		for (Player p : players)
			leavingPlayersNames += p.name + ", ";
		leavingPlayersNames.substring(0, leavingPlayersNames.length() - 1);
		boolean plural = players.size() > 1;

		log.add(leavingPlayersNames + " receive" + ((!plural) ? "s " : " ")
				+ amount + " gold" + ((plural) ? " each" : "") + ". "
				+ remaining + " gold remains on the board");
	}

	public void logDeath(String victim, String killer) {
		log.add(victim + " was killed by " + killer);
	}

	public void logDeath(Player victim, CardBase killer) {
		log.add(victim.name + " was killed by " + killer.name);
	}

	public void logGoldSplit(int perPlayer, int leftOver) {
		log.add("Each remaining player received " + perPlayer + " gold. "
				+ leftOver + " gold is added to the board.");
	}

	public void logFlip(CardBase card) {
		if (CardList.isEnemy(card))
			log.add(card.name + " appears");
		else if (CardList.isTrap(card))
			log.add(card.name + " is encountered");
		else
			log.add(card.name + " is found");
	}

	public List<String> lines() {
		return log;
	}

	public void printGameState(Board b, List<Player> players) {
		String playersGold = "";
		for (Player p : players) {
			playersGold += " | " + p.name +" " + ((!p.dead)?"":"(DEAD)")+ " TOT: " + p.totGold() + " CUR: "
					+ p.currGold();
		}

		log.add("Gold:: BRD:" + b.gold + " " + playersGold);
	}

}
