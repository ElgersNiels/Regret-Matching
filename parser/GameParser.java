package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

import game.Game;

public class GameParser {

	public Game parse(String path) {
		Game game = null;
		
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			Iterator<String> iter = stream.iterator();
			
			String[] gameInit = iter.next().split(" ");
			
			int nbOfPlayers = Integer.parseInt(gameInit[0]);
			int[] nbOfActions = Arrays.stream(gameInit).skip(1)
					.mapToInt(a -> Integer.parseInt(a))
					.toArray();
			
			game = new Game(nbOfPlayers, nbOfActions);
			
			while (iter.hasNext()) {
				String next = iter.next();
				
				String[] temp = next.split(" : ");
				String[] params = temp[0].split(" ");
				
				int player = Integer.parseInt(params[0]);
				int[] actions = Arrays.stream(params).skip(1)
						.mapToInt(a -> Integer.parseInt(a))
						.toArray();
				double value = Double.parseDouble(temp[1]);
				
				game.putPayoff(player, actions, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return game;
	}
}
