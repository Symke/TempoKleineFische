package Game;
import Model.GameSettings;
import Model.Color;

/**
 * @author martina.kettenbach
 *
 */
public class Game {
		
	private Field field;
	private GameSettings gameSettings;
	
	public void initGame(GameSettings gameSettings)
	{
		this.gameSettings = gameSettings;
		this.field = new Field(gameSettings);
		
	}
		
	public void play()
	{
		Color color = null; 
		
		field.initField();
		while(!isGameOver())
		{
			color = rollTheDice();
			field.move(color);			
		}
	}
	
	private boolean isGameOver()
	{
		//boat arrived
		if (field.getRiverSize() == 2) 
		{
			return true;
		}		
		else if(field.allFishesFished())
			return true;
		else 
			return false;
		
	}
	
	private static Color rollTheDice()
	{
		return null;
	}
	
}
