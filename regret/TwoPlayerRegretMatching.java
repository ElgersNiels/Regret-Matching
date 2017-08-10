package regret;

import game.TwoPlayerGame;

public class TwoPlayerRegretMatching extends RegretMatching {
	
	public TwoPlayerRegretMatching(double[][] p1, double[][] p2) {
		super(new TwoPlayerGame(p1,p2));
	}

}
