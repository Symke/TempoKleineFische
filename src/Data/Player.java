package Data;

import Data.Figure.FigureType;

public class Player {
	private String name;
	private FigureType figureType;

	public Player (String name, FigureType figureType)
	{
		this.name = name;
		this.figureType = figureType; 
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FigureType getFigureType() {
		return figureType;
	}

	public void setFigureType(FigureType figureType) {
		this.figureType = figureType;
	} 
	
	
}
