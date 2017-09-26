/**
 * This class includes the field of the game 
 */
package Game;

import Model.Color;
import Model.Figure;
import Model.GameSettings;
import Model.Position;
import Model.RiverPart;
import Model.Figure.FigureType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author martina.kettenbach
 *
 */
public class Field {
	private List<RiverPart> riverParts;
	private GameSettings gameSettings;	
	public static final int FIGURE_COUNT = 6;
		 
		
	public Field(GameSettings gameSettings)
	{
		this.riverParts   = new ArrayList<RiverPart>();
		this.gameSettings = gameSettings;
	}
	
			
	public void initField()
	{
		riverParts.clear();
			
		int totalRiverPartsCount = gameSettings.getRiverPartsLeftCount()  + 
								   gameSettings.getRiverPartsRightCount() + 3; //Add 3 for sea, anglers and fish part
		
		// Build river
		for(int i = 0; i < totalRiverPartsCount; i++)
			riverParts.add(new RiverPart());
		
		// Add anglers 
		riverParts.get(0).addFigure(new Figure(Color.RED, FigureType.ANGLERS));
		riverParts.get(0).addFigure(new Figure(Color.GREEN, FigureType.ANGLERS));
			
		
		// Add fishes
		int startPositionFishes = gameSettings.getRiverPartsLeftCount() + 1;
		riverParts.get(startPositionFishes).addFigure(new Figure(Color.BLUE, FigureType.FISH));
		riverParts.get(startPositionFishes).addFigure(new Figure(Color.ORANGE, FigureType.FISH));
		riverParts.get(startPositionFishes).addFigure(new Figure(Color.YELLOW, FigureType.FISH));
		riverParts.get(startPositionFishes).addFigure(new Figure(Color.PINK, FigureType.FISH));
		
	}
	
	
	public int getRiverSize()
	{
		return riverParts.size();
	}
	
	public RiverPart getRiverPartByIndex(int index)
	{
		return riverParts.get(index);
	}
	
		
	public boolean allFishesFished()
	{
		if(riverParts.get(0).getFigures().size() == FIGURE_COUNT-1)
			return true;
		else 
			return false;
	}
	
	
	public void move(Color color) 
	{
		Position selectedFigurePosition = getFigurePosition(color);
				
		//move anglers
		if(selectedFigurePosition.getX() == 0)
		{
			fishing(selectedFigurePosition);
			riverParts.remove(selectedFigurePosition.getX()+1);
		}				
		// fish already in sea --> select new fish 
		else if(selectedFigurePosition.getX() == getRiverSize()-1)
		{
			Position rndFishPos = selectRandomFish();
			moveFigureForword(rndFishPos);
		}
		//move fish
		else		
			moveFigureForword(selectedFigurePosition);
		
	}	
	
	private void moveFigureForword(Position fishPosition)
	{				
		
		RiverPart oldRiverPart = riverParts.get(fishPosition.getX());
		RiverPart newRiverPart = riverParts.get(fishPosition.getX() + 1);
		Figure fish = oldRiverPart.getFigureByIndex(fishPosition.getY());
		
		newRiverPart.addFigure(fish);
		oldRiverPart.removeFigure(fishPosition.getY());	
	}
		
	
	private Position selectRandomFish()
	{
		List<Position> freeFishes = new ArrayList<Position>();
		
		// collect all fishes 
		for(int i=1; i < getRiverSize()-1; i++)
		{
			RiverPart riverPart = riverParts.get(i);
			for(int j=0; j < riverPart.getFigures().size(); j++)
				freeFishes.add(new Position(i, j));
		}
		
		//select random fish
		Random randomGenerator = new Random();		
		return freeFishes.get(randomGenerator.nextInt(freeFishes.size()));
	}
	
	private void fishing(Position anglerPosition)
	{
		List<Figure> fishedFishes;
		
		// boat does not arrived yet
		if(anglerPosition.getX()+1 < getRiverSize())	
		{
			fishedFishes = riverParts.get(anglerPosition.getX() + 1).getFigures(); 
			riverParts.get(anglerPosition.getX()).appendFigureLst(fishedFishes);
		}
			
	}
	
	public Position getFigurePosition(Color color)
	{		
		for(int x=0; x < getRiverSize(); x++)
		{
			int y = riverParts.get(x).getFigureIdxByColor(color); 
			if(y > -1)
				return new Position(x, y);
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		
		return "{RiverParts: " + riverParts.toString() + "}";
	}
}
