package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.ActionType;
import model.Board;
import model.Player;
import cards.CardBase;
import cards.CardList;
import cards.CardType;
import cards.treasure.BombableTreasureCard;
import cards.treasure.RopeableTreasureCard;

public class Log {
	private List<String> log;
	public boolean loggingOn;

	public Log(boolean on) {
		this.log = new ArrayList<String>();
		this.loggingOn = on;
	}

	public void logString(String entry) {
		if (loggingOn) {
			log.add(entry);
		}
	}

	public void logAction(String name, ActionType chosenAction)
			throws Exception {
		if (loggingOn) {
			String chosenStr;

			switch (chosenAction) {
			case DEEPER:
				chosenStr = "to go deeper";
				break;
			case LEAVE:
				chosenStr = "to leave";
				break;
			case BOMB:
				chosenStr = "to throw a bomb";
				break;
			case ROPE:
				chosenStr = "to use a rope";
				break;
			default:
				throw new Exception("LogAction Exception");
			}

			log.add(name + " chose " + chosenStr);
		}

	}

	public void logLeaving(Player player, List<CardBase> cards, int amount) {
		if (loggingOn) {
			String itemsString = "";
			if (cards.size() > 0) {
				itemsString += " and items: ";
				for (CardBase c : cards)
					itemsString += c.name + ",";
			}

			log.add(player.name + " receives " + amount + " gold" + itemsString);
		}

	}

	public void logLeaving(List<Player> players, int amount, int remaining) {
		if (loggingOn) {
			String leavingPlayersNames = "";
			for (Player p : players)
				leavingPlayersNames += p.name + ", ";
			leavingPlayersNames.substring(0, leavingPlayersNames.length() - 1);
			boolean plural = players.size() > 1;

			log.add(leavingPlayersNames + " received" + amount + " gold"
					+ ((plural) ? " each" : "") + ". " + remaining
					+ " gold remains on the board");
		}

	}

	public void logDeath(String victim, String killer) {
		if (loggingOn)
			log.add(victim + " was killed by " + killer);
	}

	public void logDeath(Player victim, CardBase killer) {
		if (loggingOn)
			log.add(victim.name + " was killed by " + killer.name);
	}

	public String namesFromPlayerList(List<Player> players) {
		String pNames = "";
		for (Player p : players)
			pNames += p.name + ", ";
		if (pNames.length() > 0)
			pNames = pNames.substring(0, pNames.length() - 2);
		else
			pNames = "No one";
		return pNames;
	}

	public void logGoldSplit(List<Player> players, int perPlayer, int leftOver) {
		if (loggingOn) {
			String pNames = namesFromPlayerList(players);

			log.add(pNames + " received " + perPlayer + " gold. " + leftOver
					+ " gold is added to the board.");
		}

	}

	public void logRopeSplit(List<Player> players, int perPlayer, int leftOver) {
		if (loggingOn) {
			String pNames = namesFromPlayerList(players);

			log.add(pNames + " received " + perPlayer + " gold. " + leftOver
					+ " gold remains on the ledge(s).");
		}

	}

	public void logFlip(CardBase card) {
		if (loggingOn) {
			if (CardList.isEnemy(card)) {
				if (!Arrays.asList("chill").contains(card.name))// don't log a
																// reshuffled
																// card
					log.add(card.name + " appears");
			} else if (CardList.isTrap(card))
				log.add(card.name + " is encountered");
			else {
				log.add(card.name + " is found");

				if (card.type == CardType.BOMB)
					log.add("A GEM (worth "
							+ ((BombableTreasureCard) card).bombValue
							+ " gold) is stuck in the wall...");
				else if (card.type == CardType.ROPE)
					log.add("Some loot (worth "
							+ ((RopeableTreasureCard) card).ropeCard.value
							+ " gold) is up on a ledge...");

			}
		}

	}

	public List<String> lines() {
		return log;
	}

	public void printGameState(Board b, List<Player> players) {
		if (loggingOn) {
			String playersGold = "";
			for (Player p : players) {
				playersGold += " | " + p.name + " "
						+ ((!p.dead) ? "" : "(DEAD)") + " TOT: " + p.totGold()
						+ " CUR: " + p.currGold();
			}

			log.add("Gold:: BRD:" + b.gold + " " + playersGold);
		}

	}

}
