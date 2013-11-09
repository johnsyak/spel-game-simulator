package model;

import java.util.ArrayList;
import java.util.List;

import main.Log;
import strategy.StrategyBase;
import actions.ActionType;

public class Player {

	private int totalGold;
	private int currentGold;
	public boolean active;
	public boolean dead;
	public String name;

	private List<ActionType> actions;
	public ActionType chosenAction;

	private StrategyBase strategy;

	public Player(String name, StrategyBase strategy) {
		this.strategy = strategy;

		this.name = name;

		actions = new ArrayList<ActionType>();
		actions.add(ActionType.DEEPER);
		actions.add(ActionType.LEAVE);
		actions.add(ActionType.ROPE);
		actions.add(ActionType.BOMB);
	}

	public void choose(Board board, Log log) throws Exception {
		chosenAction = strategy.decide(board, this);
		log.logAction(name, chosenAction);
	}

	public void addGold(int amount) {
		currentGold += amount;
	}

	public void keepGold() {
		totalGold += currentGold;
		currentGold = 0;
	}

	public void die() {
		active = false;
		dead = true;
		currentGold = 0;
	}

	public void resetForNewRound() {
		active = true;
		dead = false;
		chosenAction = ActionType.DEEPER;
		totalGold += currentGold;
		currentGold = 0;
	}

	public int totGold() {
		return totalGold;
	}

	public int currGold() {
		return currentGold;
	}

}
