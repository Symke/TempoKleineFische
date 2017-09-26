import static org.junit.Assert.*;

import javax.swing.text.DefaultEditorKit.DefaultKeyTypedAction;

import org.junit.Before;

import Model.Color;
import Model.GameSettings;

import org.junit.Test;

import Game.Field;

public class FieldTest {
	
	GameSettings defaultGameSettings;
	
	@Before
	public void init()
	{
		defaultGameSettings  = new GameSettings();
	}
		
			
	@Test
	public void testMoveFish() {			
		Field field = new Field(defaultGameSettings);		
		field.initField();
		
		field.move(Color.YELLOW);

		System.out.println(field.toString());
	}

	@Test
	public void testMoveAnglers() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testMoveFishInSea() {
		fail("Not yet implemented");
	}
}
