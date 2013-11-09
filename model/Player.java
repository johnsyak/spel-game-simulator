package model;

import java.util.ArrayList;
import java.util.List;

import main.Log;
import strategy.StratList;
import strategy.StrategyBase;
import actions.ActionType;

public class Player {

	private int totalGold;
	private int currentGold;
	public boolean active;
	private String name;

	private List<ActionType> actions;
	public ActionType chosenAction;

	private StrategyBase strategy;

	public Player(String name) {
		strategy = StratList.coinToss;

		this.name = name;

		actions = new ArrayList<ActionType>();
		actions.add(ActionType.DEEPER);
		actions.add(ActionType.LEAVE);
		actions.add(ActionType.ROPE);
		actions.add(ActionType.BOMB);
	}

	public void choose(Log log) throws Exception {
		chosenAction = strategy.decide();
		log.logAction(name, chosenAction);
	}

	public void resetForNewRound() {
		active = true;
		totalGold += currentGold;
		currentGold = 0;
	}

}
