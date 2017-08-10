package game;
public class TwoPlayerGame extends Game {

	@SuppressWarnings("unchecked")
	public TwoPlayerGame(double[][] p1, double[][] p2) {
		super((Matrix<Double>[]) new Matrix[]{buildMatrix(p1), buildMatrix(p2)});
	}
	
	private static Matrix<Double> buildMatrix(double[][] payoffs) {
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
