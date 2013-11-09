package main;

import java.util.ArrayList;
import java.util.List;

import actions.ActionType;
import cards.CardBase;

public class Log {
	private List<String> log;

	public Log() {
		log = new ArrayList<String>();
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

	public void logFlip(CardBase card) {

	}

	public List<String> lines() {
		return log;
	}

}
