package game;



import data.Dice.Color;
import data.GameSettings;
import data.Score;
import data.Dice;

/**
 * @author martina.kettenbach
 *
 */
public class Game {

	private Field field;
	private Score score;
	private Dice dice; 
	private GameSettings gameSettings;

	public Game(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
		this.field = new Field(gameSettings);
		this.score = new Score();
		this.dice = new Dice();
	}

	/**
	 * 
	 * @return score of the game
	 * @throws Exception
	 */
	public Score play() {
		Color color = null;
		field.initField();

		while (!field.isGameOver()) {
			color = dice.rollTheDice();
			field.move(color);
			drawGame(field);
		}

		score.setWinner(field.getWinner());
		score.setAnglersCount(gameSettings.getAnglers().size());
		score.setFishCount(gameSettings.getFishes().size());
		score.setLeftRiverPartsCount(gameSettings.getRiverPartsLeftCount());
		score.setRightRiverPartsCount(gameSettings.getRiverPartsRightCount());

		return score;

	}

	
	public static void drawGame(Field field) {
		System.out.println(
				"====================================================================================================");
		for (int i = 0; i < field.getRiverSize(); i++) {
			System.out.println(field.getRiverPartByIndex(i).toString());
		}
		System.out.println(
				"====================================================================================================");
		System.out.println();
	}
}
