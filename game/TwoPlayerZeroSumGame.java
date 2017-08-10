package game;

public class TwoPlayerZeroSumGame extends TwoPlayerGame {

	public TwoPlayerZeroSumGame(double[][] p1) {
		super(buildMatrix(p1), buildMatrix(p1).negation());
	}

}
