package Game;
import java.io.ObjectInputStream.GetField;
import java.util.Random;

import Data.Color;
import Data.GameSettings;
import Data.Position;
import Data.Score;
import DataAccess.IScoreDAO;
import DataAccess.ScoreDAO;

/**
 * @author martina.kettenbach
 *
 */
public class Game {
		
	private Field field;
	private Score score;
	private GameSettings gameSettings;
	
	public Game(GameSettings gameSettings)
	{
		this.gameSettings = gameSettings;
		this.field 		  = new Field(gameSettings);	
		this.score        = new Score();		
	}
		
	/**
	 * 
	 * @return score of the game
	 * @throws Exception 
	 */
	public Score play() 
	{		
		Color color = null;
		field.initField();
			
		while (!field.isGameOver()) {
			color = rollTheDice();			
			field.move(color);		
			drawGame(field);	
		}
				
		score.setWinner(field.getWinner());
		score.setAnglersCount(gameSettings.getAnglers().size());
		score.setFishCount(gameSettings.getFishes().size());
		score.setLeftRiverPartsCount(gameSettings.getRiverPartsLeftCount());
		score.setRightRiverPartsCount(gameSettings.getRiverPartsRightCount());
		
		return score;
			
	}	
		
	/**
	 * 
	 * @return random color of the possible colors 
	 */
	private static Color rollTheDice()
	{
		Random randomGenerator = new Random();			
		int rndColorIndex = randomGenerator.nextInt(Color.values().length);
		
		return Color.values()[rndColorIndex];
		
	}
	
	public static void drawGame(Field field)
	{
		System.out.println("====================================================================================================");
		for (int i = 0; i < field.getRiverSize(); i++) {
			System.out.println(field.getRiverPartByIndex(i).toString());
		}
		System.out.println("====================================================================================================");
		System.out.println();
	}		
}
