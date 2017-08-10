package game;

public class TwoPlayerZeroSumGame extends TwoPlayerGame {

	public TwoPlayerZeroSumGame(double[][] p1) {
		super(p1, negate(p1));
	}
	
	private static double[][] negate(double[][] p1) {
		double[][] negation = new double[p1.length][p1[0].length];
		
		for (int i = 0; i < p1.length; i++)
			for (int j = 0; j < p1[0].length; j++)
				negation[i][j] = -p1[i][j]; 
		
		return negation;
	}

}
