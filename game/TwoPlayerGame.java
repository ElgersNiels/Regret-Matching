package game;
public class TwoPlayerGame extends Game {

	public TwoPlayerGame(double[][] p1, double[][] p2) {
		this(buildMatrix(p1), buildMatrix(p2));
	}
	
	@SuppressWarnings("unchecked")
	public TwoPlayerGame(Matrix<Double> p1, Matrix<Double> p2) {
		super((Matrix<Double>[]) new Matrix[]{p1, p2});
	}
	
	protected static Matrix<Double> buildMatrix(double[][] payoffs) {
		Matrix<Double> matrix = new Matrix<Double>(new int[]{payoffs.length, payoffs[0].length});
		
		for (int i = 0; i < payoffs.length; i++)
			for (int j = 0; j < payoffs[0].length; j++)
				matrix.put(new int[]{i, j}, payoffs[i][j]);
		
		return matrix;
	}
	
	public double getPayoff(int player, int a1, int a2) {
		return super.getPayoff(player, new int[]{a1,a2});
	}
	
	public double[] getPayoff(int a1, int a2) {
		return super.getPayoff(new int[]{a1,a2});
	}

}
