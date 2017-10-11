package Data;

import java.time.Period;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		Position target = (Position)obj; 
		
		result &= this.x == target.x;		
		result &= this.y == target.y;	
		
		return result;
	}
	
}
