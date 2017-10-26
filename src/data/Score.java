package data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author martina.kettenbach
 *
 */
public class Score implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int DRAW		 	= 0;
	public static final int FISH_FRIENDS 	= 1;
	public static final int ANGLER_FRIENDS  = 2;
	
	public int id;
	public int winner; 
	public int fishCount;
	public int anglersCount;
	public int leftRiverPartsCount; 
	public int rightRiverPartsCount;
		
	public Score() {
		this.winner = -1;
		this.fishCount = 0;
		this.anglersCount = 0;
		this.leftRiverPartsCount = 0;
		this.rightRiverPartsCount = 0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public int getFishCount() {
		return fishCount;
	}
	public void setFishCount(int fishCount) {
		this.fishCount = fishCount;
	}
	public int getAnglersCount() {
		return anglersCount;
	}
	public void setAnglersCount(int anglersCount) {
		this.anglersCount = anglersCount;
	}
	public int getLeftRiverPatsCount() {
		return leftRiverPartsCount;
	}
	public void setLeftRiverPartsCount(int leftRiverPartsCount) {
		this.leftRiverPartsCount = leftRiverPartsCount;
	}
	public int getRightRiverPartsCount() {
		return rightRiverPartsCount;
	}
	public void setRightRiverPartsCount(int rightRiverPartsCount) {
		this.rightRiverPartsCount = rightRiverPartsCount;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(anglersCount, fishCount, id, leftRiverPartsCount, rightRiverPartsCount, winner);
	}
	
	@Override
	public boolean equals(Object obj) {

		if(!super.equals(obj))
			return false;
		
		Score target = (Score)obj; 		
		if(this.anglersCount != target.anglersCount)		{
			return false;
		}
		if(this.fishCount != target.fishCount) {
			return false;
		}
		if(this.id != target.id) {
			return false;
		}
		if(this.leftRiverPartsCount != target.leftRiverPartsCount) {
			return false;
		}
		if(this.rightRiverPartsCount != target.rightRiverPartsCount) {
			return false;
		}
		if(this.winner != target.winner) {
			return false;
		}
		
		return true;
	}	

	@Override
	public String toString() {
		return new StringBuffer(" Winner : ").append(this.winner)
						.append(" FishCount : ").append(this.fishCount)
						.append(" AnglersCount : ").append(this.anglersCount)
						.append(" leftRiverPatsCount : ").append(this.leftRiverPartsCount)
						.append(" rightRiverPartsCount : ").append(this.rightRiverPartsCount).toString();
	}
}
