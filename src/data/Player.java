package data;

import java.util.Objects;

import data.Figure.FigureType;

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

	public FigureType getFigureType() {
		return figureType;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, figureType);
	}
	
	@Override
	public boolean equals(Object obj) {
				
		if(!super.equals(obj)) {
			return false;
		}
		
		Player target = (Player)obj; 
		
		if(this.name != target.name) {
			return false;
		}
		if(this.figureType != target.figureType) {
			return false;
		}
		
		return true;
	}
}
