package Data;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author martina.kettenbach
 *
 */
public class RiverPart{
	private List<Figure> figures;
	
	public RiverPart()
	{
		this.figures = new ArrayList<Figure>();
	}
	
	public List<Figure> getFigures()
	{
		return figures;		
	}
	
	public int getFigureIdxByColor(Color color)
	{
		for(int i=0; i < figures.size(); i++)
		{
			if(figures.get(i).getColor()==color)
				return i;
		}
		
		return -1;
	}	
	
	public Figure getFigureByIndex(int index)
	{
		return figures.get(index);
	}
			
	public void addFigure(Figure newFigure)
	{
		figures.add(newFigure);
	}
	

	public void appendFigureLst(List<Figure> newFigureLst)
	{
		figures.addAll(newFigureLst);
	}
	
	
	public void removeFigure(int index)
	{
		figures.remove(index);
	}
	
	@Override
	public String toString() {		
		return "{Figures: " + figures.toString() + "}";
	}
	
}
