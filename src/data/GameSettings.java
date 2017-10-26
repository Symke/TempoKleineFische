package data;

import java.util.ArrayList;
import java.util.List;
import data.Dice.Color;;

/**
 * @author martina.kettenbach
 *
 */
public class GameSettings {
	public static final int MAX_RIVER_PARTS_COUNT = 10;

	private List<Player> players;
	private int riverPartsLeftCount;
	private int riverPartsRightCount;
	private List<Color> anglers;
	private List<Color> fishes;

	public GameSettings() {
		this(5, 5);
	}

	public GameSettings(int riverPartsLeftCount, int riverPartsRightCount) {
		this.players = new ArrayList<Player>();
		this.anglers = new ArrayList<Color>();
		this.fishes = new ArrayList<Color>();

		addAngler(Color.RED);
		addAngler(Color.GREEN);

		addFish(Color.BLUE);
		addFish(Color.ORANGE);
		addFish(Color.YELLOW);
		addFish(Color.PINK);

		// default field size settings
		setFieldSize(riverPartsLeftCount, riverPartsRightCount);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Color> getAnglers() {
		return anglers;
	}

	public void setAnglers(List<Color> anglers) {
		this.anglers = anglers;
	}

	public List<Color> getFishes() {
		return fishes;
	}

	public void setFishes(List<Color> fishes) {
		this.fishes = fishes;
	}

	public int getRiverPartsLeftCount() {
		return riverPartsLeftCount;
	}

	public int getRiverPartsRightCount() {
		return riverPartsRightCount;
	}

	public int getFigureCount() {
		return anglers.size() + fishes.size();
	}

	/***
	 * Set field size properties and check if the given values are valid
	 * 
	 * @param riverPartsLeftCount
	 * @param riverPartsRightCount
	 */
	public void setFieldSize(int riverPartsLeftCount, int riverPartsRightCount) {
		int totalRiverParts = riverPartsLeftCount + riverPartsRightCount;

		if (riverPartsLeftCount < 0 || riverPartsRightCount < 0)
			throw new IllegalArgumentException("River part count can't be samller than 0!");

		if (totalRiverParts > MAX_RIVER_PARTS_COUNT)
			throw new IllegalArgumentException(
					"The total number of river parts must not be grater than " + MAX_RIVER_PARTS_COUNT + "!");

		this.riverPartsLeftCount = riverPartsLeftCount;
		this.riverPartsRightCount = riverPartsRightCount;

	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	private void addAngler(Color color) {
		anglers.add(color);
	}

	private void addFish(Color color) {
		fishes.add(color);
	}

}
