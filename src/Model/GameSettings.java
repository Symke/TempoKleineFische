package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author martina.kettenbach
 *
 */
public class GameSettings {
	public static final int MAX_RIVER_PARTS_COUNT = 10;
	public static final int FIGURE_COUNT = 6;
	
	private List<Player> players;
	private int riverPartsLeftCount; 
	private int riverPartsRightCount;
	
	public GameSettings()
	{
		this.players = new ArrayList<Player>();
		this.riverPartsLeftCount  = 5;
		this.riverPartsRightCount = 5;
	}
	
	public int getRiverPartsLeftCount() {
		return riverPartsLeftCount;
	}	

	public int getRiverPartsRightCount() {
		return riverPartsRightCount;
	}
		
	/***
	 * Set field size properties and check if the given values are valid 
	 * @param riverPartsLeftCount
	 * @param riverPartsRightCount
	 */
	public void setFieldSize(int riverPartsLeftCount, int riverPartsRightCount)
	{
		int totalRiverParts = riverPartsLeftCount+riverPartsRightCount;
		
		if(riverPartsLeftCount < 0 || riverPartsRightCount < 0)
			throw new IllegalArgumentException("River part count can't be samller than 0!");
			
		if(totalRiverParts > MAX_RIVER_PARTS_COUNT)
			throw new IllegalArgumentException("Total river parts count to large!");
			
		this.riverPartsLeftCount  = riverPartsLeftCount;
		this.riverPartsRightCount = riverPartsRightCount;		
		
	}	
	
	public void addPlayer(Player player)
	{		
		players.add(player);
	}

	
}
