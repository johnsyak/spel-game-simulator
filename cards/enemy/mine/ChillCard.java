package cards.enemy.mine;

import java.util.List;

import main.Log;
import model.Board;
import model.Player;
import cards.CardType;
import cards.enemy.EnemyBase;

public class ChillCard extends EnemyBase {

	public ChillCard() {
		this.type = CardType.CHILL;
		this.name = "chill";
	}

	@Override
	public boolean runEffect(Board board, List<Player> players, Log log) {
		
		if (board.cards().size() >= 10) {
			for (int i = 0; i < 3; i++)
				board.decks.get(0).addCard(new GhostCard());
			board.decks.get(0).shuffle();
			log.logString("A chill runs up your spine...");
			return false;
		} else {
			return true;
		}
	}

}
