package Model;
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
}
