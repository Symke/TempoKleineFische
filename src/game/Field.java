/**
 * This class includes the game field and logic
 */
package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data.Dice.Color;
import data.Figure;
import data.GameSettings;
import data.Position;
import data.RiverPart;
import data.Score;
import data.Figure.FigureType;

/**
 * @author martina.kettenbach
 *
 */
public class Field {
	private List<RiverPart> riverParts = new ArrayList<RiverPart>();
	private GameSettings gameSettings;
	private int winner = -1;

	public Field(GameSettings gameSettings) {
		this.riverParts = new ArrayList<RiverPart>();
		this.gameSettings = gameSettings;
	}

	public int getWinner() {
		return winner;
	}

	public int getRiverSize() {
		return riverParts.size();
	}

	public RiverPart getRiverPartByIndex(int index) {
		return riverParts.get(index);
	}

	public boolean boatReachSea() {
		return getRiverSize() == 2;
	}

	public boolean allFishesInSea() {
		int fishInSeaCount = getSeaPart().getFigures().size();
		int totalFishCount = gameSettings.getFishes().size();

		return fishInSeaCount == totalFishCount;
	}

	public int getAngeldFishCount() {
		return getBoat().getFigures().size() - gameSettings.getAnglers().size();
	}

	public boolean allFishesAngled() {
		int totalFishCount = gameSettings.getFishes().size();

		return getAngeldFishCount() == totalFishCount;
	}

	/**
	 * The first river part includes the boat
	 * 
	 * @return
	 */
	public RiverPart getBoat() {
		return riverParts.get(0);
	}

	/**
	 * The last river part is the sea
	 * 
	 * @return
	 */
	public RiverPart getSeaPart() {
		return riverParts.get(getRiverSize() - 1);
	}

	public void initField() {
		riverParts.clear();

		int totalRiverPartsCount = gameSettings.getRiverPartsLeftCount() + gameSettings.getRiverPartsRightCount() + 3; // Add
																														// 3
																														// for
																														// sea,
																														// anglers
																														// and
																														// fish
																														// part

		// Build river
		for (int i = 0; i < totalRiverPartsCount; i++) {
			riverParts.add(new RiverPart());
		}

		// Add anglers
		for (Color color : gameSettings.getAnglers()) {
			riverParts.get(0).addFigure(new Figure(color, FigureType.ANGLERS));
		}

		// Add fishes
		int startPositionFishes = gameSettings.getRiverPartsLeftCount() + 1;
		for (Color color : gameSettings.getFishes()) {
			riverParts.get(startPositionFishes).addFigure(new Figure(color, FigureType.FISH));
		}

	}

	/**
	 * Game is over if, - boat reach the sea - all fishes are in the sea - all
	 * fishes are angled
	 * 
	 * @return as long as there is no winner return -1 otherwise return winner
	 */
	public boolean isGameOver() {
		int angledFishCount = getAngeldFishCount();
		int freeFishCount = getSeaPart().getFigures().size();

		if (boatReachSea()) {
			if (angledFishCount == freeFishCount) {
				winner = Score.DRAW;
			} else if (angledFishCount > freeFishCount) {
				winner = Score.ANGLER_FRIENDS;
			} else if (angledFishCount < freeFishCount) {
				winner = Score.FISH_FRIENDS;
			}
		} else if (allFishesAngled()) {
			winner = Score.ANGLER_FRIENDS;
		} else if (allFishesInSea()) {
			winner = Score.FISH_FRIENDS;
		}

		return winner > -1;
	}

	public boolean move(Color color) {
		// Game is over
		if (winner > -1) {
			return false;
		}

		if (color == null) {
			throw new IllegalArgumentException("Value 'color' must not be empty!");
		}

		Position selectedFigurePosition = getFigurePosition(color);

		// move anglers
		if (selectedFigurePosition.getX() == 0) {
			fishing(selectedFigurePosition);
			riverParts.remove(selectedFigurePosition.getX() + 1);
		}
		// fish already in sea --> select new fish
		else if (selectedFigurePosition.getX() == getRiverSize() - 1) {
			Color rndFishPos = selectRandomFish();
			move(rndFishPos);
		}
		// move fish
		else {
			moveFigureForward(selectedFigurePosition);
		}

		return true;
	}

	/**
	 * Delete it from the current river part and add it to the following
	 * 
	 * @param fishPosition
	 */
	private void moveFigureForward(Position fishPosition) {

		RiverPart oldRiverPart = riverParts.get(fishPosition.getX());
		RiverPart newRiverPart = riverParts.get(fishPosition.getX() + 1);
		Figure fish = oldRiverPart.getFigureByIndex(fishPosition.getY());

		newRiverPart.addFigure(fish);
		oldRiverPart.removeFigure(fishPosition.getY());
	}

	/**
	 * 
	 * @return
	 */
	private Color selectRandomFish() {
		Color selectedFigure = null;
		List<Color> freeFishes = new ArrayList<Color>();

		// collect all fishes in the river
		for (int i = 1; i < getRiverSize() - 1; i++) {
			RiverPart riverPart = riverParts.get(i);
			for (Figure figure : riverPart.getFigures()) {
				freeFishes.add(figure.getColor());
			}
		}

		// select figure from boat if no free fishes left
		if (freeFishes.size() == 0) {
			selectedFigure = gameSettings.getAnglers().get(0);
		}
		else {
			Random randomGenerator = new Random();
			selectedFigure = freeFishes.get(randomGenerator.nextInt(freeFishes.size()));
		}

		return selectedFigure;
	}

	/**
	 * 
	 * @param anglerPosition
	 */
	private void fishing(Position anglerPosition) {
		List<Figure> fishedFishes;

		// check, if boat does not arrived yet
		if (anglerPosition.getX() + 1 < getRiverSize()) {
			fishedFishes = riverParts.get(anglerPosition.getX() + 1).getFigures();
			riverParts.get(anglerPosition.getX()).appendFigureLst(fishedFishes);
		}
	}

	/**
	 * 
	 * @param color
	 * @return
	 */
	public Position getFigurePosition(Color color) {
		for (int x = 0; x < getRiverSize(); x++) {
			int y = riverParts.get(x).getFigureIdxByColor(color);
			if (y > -1) {
				return new Position(x, y);
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return "{RiverParts: " + riverParts.toString() + "}";
	}
}
