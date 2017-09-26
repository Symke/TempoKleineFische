import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DefaultEditorKit.DefaultKeyTypedAction;

import org.junit.Before;

import Model.Color;
import Model.Figure;
import Model.GameSettings;
import Model.Position;
import Model.RiverPart;
import Model.Figure.FigureType;

import org.junit.Test;

import Game.Field;
import Game.Game;

public class FieldTest {
	
	GameSettings defaultGameSettings;
	
	@Before
	public void init()
	{
		defaultGameSettings  = new GameSettings();
	}
	
	@Test
	public void test()
	{
		
		Field field = new Field(defaultGameSettings);
		
		field.initField();
		System.out.println(field.getRiverSize());
				
	}
	
	@Test
	public void buildFieldTest() {			
		Field field = new Field(defaultGameSettings);		
		field.initField();
				
		Position pos;
		
		//=============================================
		// first river part includes the two anglers
		pos = field.getFigurePosition(Color.RED);
		assertEquals(pos.getX(), 0);
		
		pos = field.getFigurePosition(Color.GREEN);
		assertEquals(pos.getX(), 0);
		
		assertEquals(field.getRiverPartByIndex(0).getFigures().size(), 2);
		
		//=============================================
		//Position places in the middle of the field 
		pos = field.getFigurePosition(Color.BLUE);
		assertEquals(pos.getX(), 6);
		
		pos = field.getFigurePosition(Color.ORANGE);
		assertEquals(pos.getX(), 6);
		
		pos = field.getFigurePosition(Color.YELLOW);
		assertEquals(pos.getX(), 6);
		
		pos = field.getFigurePosition(Color.PINK);
		assertEquals(pos.getX(), 6);
		
		assertEquals(4, field.getRiverPartByIndex(6).getFigures().size());
		
	}
		
			
	@Test
	public void testMoveFish() {			
		Field field = new Field(defaultGameSettings);		
		field.initField();
		
		//=============================================
		//Check if figure is moved forward
		Position newPosition;
		Position oldPosition;
		Position expectedPosition;
		
		oldPosition 	 = field.getFigurePosition(Color.YELLOW);
		expectedPosition = new Position(oldPosition.getX()+1, 0);
		
		field.move(Color.YELLOW);	
		newPosition = field.getFigurePosition(Color.YELLOW);
		
		assertEquals(newPosition, expectedPosition);
						
		//=============================================
		// Check if figure only appears once		
		List<Figure> tmpFigures = new ArrayList<Figure>();
		for (int i = 0; i < field.getRiverSize(); i++) {
			
			List<Figure> figures = field.getRiverPartByIndex(i).getFigures();
			for (int j = 0; j < figures.size(); j++) 
			{
				if(figures.get(j).getColor() == Color.YELLOW){
					tmpFigures.add(figures.get(j));
				}
			}
		}
		assertEquals(1, tmpFigures.size());
				
	}

	@Test
	public void testMoveAnglers() {
		Field field = new Field(defaultGameSettings);		
		field.initField();
		
		//=============================================
		// move anglers --> no fishes on the following river part 
		int oldRiverSize = field.getRiverSize();
		
		field.move(Color.RED);
		assertEquals(field.getRiverSize(), oldRiverSize-1);		
		
		
		//=============================================
		// angle fishes
		field.move(Color.RED);
		field.move(Color.GREEN);
		field.move(Color.RED);
		field.move(Color.GREEN);
		field.move(Color.RED);
		assertEquals(6, field.getRiverPartByIndex(0).getFigures().size());
	}
	
	@Test
	public void testMoveFishInSea() {
		Field field = new Field(defaultGameSettings);		
		field.initField();
		
		field.move(Color.PINK);
		field.move(Color.PINK);
		field.move(Color.PINK);
		field.move(Color.PINK);
		field.move(Color.PINK);
		field.move(Color.PINK);
		field.move(Color.PINK);
		
		assertEquals(2, field.getRiverPartByIndex(6).getFigures().size());
		assertEquals(1, field.getRiverPartByIndex(7).getFigures().size());
	}
}
