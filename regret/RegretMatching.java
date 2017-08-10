package regret;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import game.Game;

public class RegretMatching {

	protected Game game;
	
	protected Random rng = new Random();
	
	protected double[][] regretSums;
	
	protected double[][] strategies;
	protected double[][] strategySums;
	
	public RegretMatching(Game game) {
		this.game = game;

		this.regretSums = new double[game.getNbOfPlayers()][];
		this.strategies = new double[game.getNbOfPlayers()][];
		this.strategySums = new double[game.getNbOfPlayers()][];
		
		for (int p = 0; p < game.getNbOfPlayers(); p++) {
			this.regretSums[p] = new double[game.getNbOfActions(p)];
			this.strategies[p] = new double[game.getNbOfActions(p)];
			this.strategySums[p] = new double[game.getNbOfActions(p)];
		}
	}
	
	public double[][] getTrainedStrategy() {
		double[][] trainedStrategies = new double[game.getNbOfPlayers()][];
		
		for (int p = 0; p < trainedStrategies.length; p++)
			trainedStrategies[p] = getTrainedStrategy(p);
		
		return trainedStrategies;
	}
	
	public double[] getTrainedStrategy(int p) {	
		double sum = Arrays.stream(strategySums[p]).sum();
		return Arrays.stream(strategySums[p]).map(s -> s / sum).toArray();
	}
	
	public void train(int iterations) {
		for (int i = 0; i < iterations; i++) {			
			//Compute strategies.
			computeStrategies();

			//Play the game.
			int[] actions = IntStream.range(0, game.getNbOfPlayers())
					.map(p -> getAction(p))
					.toArray();

			for (int p = 0; p < game.getNbOfPlayers(); p++)
				for (int a = 0; a < game.getNbOfActions(p); a++) {
					int[] actionsClone = actions.clone(); actionsClone[p] = a;
					
					regretSums[p][a] += game.getPayoff(p, actionsClone) - game.getPayoff(p, actions);
				}
		}
	}
	
	private void computeStrategies() { 
		IntStream.range(0, game.getNbOfPlayers())
			.forEach(p -> computeStrategies(p));
	}
	
	private void computeStrategies(int p) {
		strategies[p] = IntStream.range(0, game.getNbOfActions(p))
				.mapToDouble(
						n -> regretSums[p][n] > 0 ? regretSums[p][n] : 0
							)
				.toArray();
		
		double normSum = Arrays.stream(strategies[p]).sum();
		strategies[p] = IntStream.range(0, game.getNbOfActions(p))
				.mapToDouble(
						n -> normSum > 0 ? strategies[p][n] / normSum : 1.0 / game.getNbOfActions(p)
							)
				.toArray();
		
		strategySums[p] = IntStream.range(0, game.getNbOfActions(p))
				.mapToDouble(
						n -> strategySums[p][n] + strategies[p][n]
							)	
				.toArray();
	}
	
	protected int getAction(int p) {
		double r = rng.nextDouble();
		int a = 0;
		double cumP = 0;
		while (a < strategies[p].length - 1) {
			cumP += strategies[p][a];
			if (r < cumP)
				break;
			a++;
		}
		return a;
	}
}
