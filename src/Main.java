import java.sql.SQLException;

import data.GameSettings;
import data.Position;
import data.Score;
import dataAccess.IDatabaseConnector;
import dataAccess.IScoreDAO;
import dataAccess.SQLiteDatabaseConnector;
import dataAccess.ScoreDAO;
import game.Game;

/**
 * 
 * @author martina.kettenbach
 *
 */
public class Main {

	public static void main(String[] args) 
	{
		GameSettings gameSettings;
		Game game;
		Score score;
				
		gameSettings = new GameSettings();
		game 		 = new Game(gameSettings);
		
		score = game.play();
		System.out.println("Current winner:" + score.getWinner());
		
		saveScore(score);						
	}
	
	public static void saveScore(Score score)
	{
		String url = "database\\TempoKleineFische.db";
		IDatabaseConnector databaseConnector = new SQLiteDatabaseConnector(url, "", "");
		
		try {
			databaseConnector.connect();
			
			IScoreDAO scoreDAO = new ScoreDAO(databaseConnector.getConnection());
			scoreDAO.addScore(score);	
			
			System.out.println("FishFriendsWonCount: " + scoreDAO.getFisherFriendsWonCount());
			System.out.println("AnglersFriendsWonCount: " + scoreDAO.getAnglersFriendsWonCount());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
