

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Game.Field;
import Game.Game;
import Model.Color;
import Model.GameSettings;

public class GameTest {

	private GameSettings defaultGameSettings;
		
	@Before
	public void init()
	{
		defaultGameSettings = new GameSettings();
		defaultGameSettings.setFieldSize(2, 2);
	}
	
	@Test
	public void allFishesReachedSea() {			
		Field field = new Field(defaultGameSettings);		
		field.initField();
		
		//=============================================
		//move all fishes to sea
		for(Color colorFish : defaultGameSettings.getFishes())
		{
			for (int i = 0; i <= defaultGameSettings.getRiverPartsRightCount()+1; i++) 
			{
				field.move(colorFish);
				Game.drawGame(field);
			}
		}
	}
	
	@Test
	public void allFishesAngled() {
		fail("Not yet implemented");		
	}
	
	@Test
	public void boatReachedSeaFishFriedsWin() {
		fail("Not yet implemented");
	}
	
	@Test
	public void boatReachedSeaFisherFriedsWin() {
		fail("Not yet implemented");
	}
	

}
