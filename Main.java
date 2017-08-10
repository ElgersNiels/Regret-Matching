import java.util.Arrays;

import regret.TwoPlayerRegretMatching;

public class Main {

	public static void main(String[] args) {
		System.out.println("Some game (zero-sum)");
		TwoPlayerRegretMatching r = new TwoPlayerRegretMatching(
				new double[][]{{1,3,-1},{2,-2,3}}, 
				new double[][]{{-1,-3,1},{-2,2,-3}});
		
		r.train((int) 10e2);
		
		for (double[] s : r.getTrainedStrategy())
			System.out.println(Arrays.toString(s));
		
		System.out.println("RPS (zero-sum)");
		r = new TwoPlayerRegretMatching(
				new double[][]{{0,-1,1},{1,0,-1},{-1,1,0}}, 
				new double[][]{{0,1,-1},{-1,0,1},{1,-1,0}});
		
		r.train((int) 10e2);
		
		for (double[] s : r.getTrainedStrategy())
			System.out.println(Arrays.toString(s));
	}

}