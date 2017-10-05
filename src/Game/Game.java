package Game;
import Model.GameSettings;
import Model.Position;
import Model.Score;

import java.io.ObjectInputStream.GetField;
import java.util.Random;

import DataAccess.IScoreDAO;
import DataAccess.ScoreDAO;
import Model.Color;

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
	 */
	public Score play()
	{		
		Color color = null; 	
		field.initField();
		
		while (!isGameOver()) {
			color  = rollTheDice();
			
			field.move(color);		
			drawGame(field);			
		} 
				
		score.setAnglersCount(gameSettings.getAnglers().size());
		score.setFishCount(gameSettings.getFishes().size());
		score.setLeftRiverPartsCount(gameSettings.getRiverPartsLeftCount());
		score.setRightRiverPartsCount(gameSettings.getRiverPartsRightCount());
		
		return score;
			
	}	
	
	/**
	 * Game is over if,
	 * 	- boat reach the sea
	 *  - all fishes are in the sea
	 *  - all fishes are angled 
	 * @return the winner 
	 */
	public boolean isGameOver()
	{
		boolean isOver = true; 
		
		int angledFishCount = field.getAngeldFishCount(); 
		int freeFishCount   = field.getSeaPart().getFigures().size(); 
		
		if (field.boatReachSea()) 
		{						
			if(angledFishCount == freeFishCount)
				score.setWinner(Score.DRAW);
			else if(angledFishCount > freeFishCount)
				score.setWinner(Score.ANGLER_FRIENDS);
			else if(angledFishCount < freeFishCount)
				score.setWinner(Score.FISH_FRIENDS);			 
		}		
		else if(field.allFishesAngled())
			score.setWinner(Score.ANGLER_FRIENDS);
		else if(field.allFishesInSea())
			score.setWinner(Score.FISH_FRIENDS);	
		else 
			isOver = false;
				
		return isOver; 		
	}	
		
	/**
	 * 
	 * @return random color of the possible colors 
	 */
	private Color rollTheDice()
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
