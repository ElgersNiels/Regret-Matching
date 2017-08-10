import java.util.Arrays;

import game.Game;
import game.TwoPlayerGame;
import game.TwoPlayerZeroSumGame;
import parser.GameParser;
import regret.RegretMatching;

public class Main {

	public static void main(String[] args) {
		//--
		Game g = new TwoPlayerGame(
				new double[][]{{1,3,-1},{2,-2,3}}, 
				new double[][]{{-1,-3,1},{-2,2,-3}}
				);
		RegretMatching r = new RegretMatching(g);
		
		r.train((int) 1e6);
		
		for (double[] s : r.getTrainedStrategy())
			System.out.println(Arrays.toString(s));
		
		//--
		g = new TwoPlayerZeroSumGame(new double[][]{{1,3,-1},{2,-2,3}});
		r = new RegretMatching(g);
		
		r.train((int) 1e6);
		
		for (double[] s : r.getTrainedStrategy())
			System.out.println(Arrays.toString(s));
		
		//--
		GameParser gp = new GameParser();
		g = gp.parse("C:/Users/elger/Desktop/game.txt");
		
		r = new RegretMatching(g);
		r.train((int) 1e6);
		
		for (double[] s : r.getTrainedStrategy())
			System.out.println(Arrays.toString(s));
	}

}
