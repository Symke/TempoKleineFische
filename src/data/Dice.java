package data;
/**
 * 
 */

import java.util.Random;

/**
 * @author martina.kettenbach
 *
 */
public class Dice
{
	private Random randomGenerator = new Random();
	
	public enum Color {
		RED, 
		GREEN,
		BLUE,
		ORANGE,
		YELLOW,
		PINK 
	}
		
	/**
	 * 
	 * @return random color of the possible colors
	 */
	public Color rollTheDice() {
		
		int rndColorIndex = randomGenerator.nextInt(Color.values().length);

		return Color.values()[rndColorIndex];

	}	
}
