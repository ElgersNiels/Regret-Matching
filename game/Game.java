package game;
import java.util.stream.IntStream;

public class Game {

	private Matrix<Double>[] payoffs;
	
	public Game(Matrix<Double>[] payoffs) {
		for (Matrix<Double> m : payoffs)
			if (!m.isZeroIndexed())
				throw new IllegalArgumentException("All payoff matrices must be zero-indexed.");
		
		this.payoffs = payoffs;
	}
	
	@SuppressWarnings("unchecked")
	public Game(int players, int[] nbOfActions) {
		if (players != nbOfActions.length)
			throw new IllegalArgumentException("Tried to initialize a game with " + players + " players but gave " + nbOfActions.length + " amounts of actions. Must be equal.");
		
		this.payoffs = new Matrix[players];
		
		for (int p = 0; p < players; p++)
			this.payoffs[p] = new Matrix<Double>(nbOfActions);
	}
	
	public void putPayoff(int player, int[] actions, double value) {
		this.payoffs[player].put(actions, value);
	}
	
	public int getNbOfPlayers() {
		return this.payoffs.length;
	}
	
	public int getNbOfActions(int player) {
		return this.payoffs[0].getLengthOfDim(player);
	}
	
	public int[] getNbOfActions() {
		return IntStream.range(0, getNbOfPlayers())
				.map(p -> getNbOfActions(p))
				.toArray();
	}
	
	public double getPayoff(int player, int[] actions) {
		return this.payoffs[player].get(actions);
	}
	
	public double[] getPayoff(int[] actions) {
		return IntStream.range(0, getNbOfPlayers())
			.mapToDouble(p -> getPayoff(p, actions))
			.toArray();
	}
	
	public double getPayoff(int player, double[][] strategies) {
		throw new UnsupportedOperationException();
	}
	
	public double[] getPayoff(double[][] strategies) {
		return IntStream.range(0, getNbOfPlayers()) //.parallel() ??
			.mapToDouble(p -> getPayoff(p, strategies))
			.toArray();
	}
	
	public String toString() {
		String toString = "";
		for (Matrix<Double> m : this.payoffs)
			toString += m.toString() + "\n";
		return toString;
	}
}
