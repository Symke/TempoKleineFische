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
import Model.Score;
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
	public void testBuildField() {			
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
			for (int j = 0; j < figures.size(); j++) 			{
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
		
		// move fish to sea
		for (int i = 0; i <=defaultGameSettings.getRiverPartsRightCount()+1; i++) {
			field.move(Color.PINK);			
		}
		
		assertEquals(2, field.getRiverPartByIndex(6).getFigures().size());
		assertEquals(1, field.getRiverPartByIndex(7).getFigures().size());
	}
	
	@Test
	public void testNoFishesInRiver()
	{
		GameSettings gameSettings = new GameSettings(0,1);
		Field field = new Field(gameSettings);		
		field.initField();
		
		// move fish to sea
		for (int i = 0; i <=gameSettings.getRiverPartsRightCount(); i++) 
			field.move(Color.PINK);
		
		field.move(Color.RED);		
		field.move(Color.PINK); 
		
		assertEquals(2, field.getRiverSize());
	}
	
//	@Test
//	public void allFishesReachedSea() {			
//		Score score = new Score();
//		Field field = new Field(defaultGameSettings);		
//		field.initField();
//		
//		//=============================================
//		//move all fishes to sea
//		for(Color color : defaultGameSettings.getFishes()){
//			for (int i = 0; i <= defaultGameSettings.getRiverPartsRightCount(); i++) 			{
//				field.move(color);
//			}
//		}		
//
//		assertTrue(field.isGameOver(score));
//		assertEquals(Score.FISH_FRIENDS, score.getWinner());
//		Game.drawGame(field);
//	}
//	
//	@Test
//	public void allFishesAngled() {
//		Score score = new Score();
//		Field field = new Field(defaultGameSettings);		
//		field.initField();
//		
//		for (int i = 0; i <= defaultGameSettings.getRiverPartsLeftCount(); i++) {
//			field.move(Color.RED);
//			Game.drawGame(field);
//		}
//		
//		Game.drawGame(field);
//
//		assertTrue(field.isGameOver(score));
//		assertEquals(Score.ANGLER_FRIENDS, score.getWinner());
//	}
//	
//	@Test
//	public void boatReachedSeaFishFriedsWin() {
//		Score score = new Score();
//		Field field = new Field(defaultGameSettings);		
//		field.initField();
//		
//		for (int i = 0; i <= defaultGameSettings.getRiverPartsRightCount(); i++) 			{
//			field.move(Color.YELLOW);
//			field.move(Color.PINK);
//			field.move(Color.ORANGE);			
//		}
//		
//		do {
//			field.move(Color.RED);
//		} while (field.getRiverSize() > 2);
//		
//		assertTrue(field.isGameOver(score));
//		assertEquals(Score.FISH_FRIENDS, score.getWinner());
//	}
//		
//	@Test
//	public void boatReachedSeaFisherFriendsWin() {
//		Score score = new Score();
//		Field field = new Field(defaultGameSettings);		
//		field.initField();
//		
//		for (int i = 0; i <= defaultGameSettings.getRiverPartsRightCount(); i++) 			{
//			field.move(Color.YELLOW);			
//		}
//		
//		do {
//			field.move(Color.RED);
//		} while (field.getRiverSize() > 2);
//		
//		assertTrue(field.isGameOver(score));
//		assertEquals(Score.ANGLER_FRIENDS, score.getWinner());
//	}
//	
//	@Test
//	public void testGameEndedInDraw() {
//		Score score = new Score();
//		Field field = new Field(defaultGameSettings);		
//		field.initField();
//		
//		for (int i = 0; i <= defaultGameSettings.getRiverPartsRightCount(); i++) 			{
//			field.move(Color.YELLOW);		
//			field.move(Color.ORANGE);		
//		}
//		
//		do {
//			field.move(Color.RED);
//		} while (field.getRiverSize() > 2);
//		
//		assertTrue(field.isGameOver(score));
//		assertEquals(Score.DRAW, score.getWinner());
//	}
	
}
