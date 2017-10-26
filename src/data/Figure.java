package data;
import java.util.Objects;

import data.Dice.Color;
/***
 * 
 * @author martina.kettenbach
 *
 */
public class Figure {
	
	public enum FigureType {
		FISH, ANGLERS
	}	

	private Color color;
	private FigureType figureType;

	public Figure(Color color, FigureType figureType)
	{
		this.color = color;
		this.figureType = figureType;
	}
	
	public Color getColor() {
		return color;
	}

	public FigureType getFigureType() {
		return figureType;
	}
	
	@Override
	public String toString() {		
		return "{FigureType: \"" + figureType.name() + "\", Color: \"" + color.name() + "\"}";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(color, figureType);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!super.equals(obj)) {
			return false;
		}
						
		Figure target = (Figure)obj; 
		
		if(color != target.color) {
			return false;
		}
		if(figureType != target.figureType) {
			return false;
		}
		
		return true;
	}
	
	
}
