import DataAccess.IDatabaseConnector;
import DataAccess.IScoreDAO;
import DataAccess.SQLiteDatabaseConnector;
import DataAccess.ScoreDAO;
import Game.Game;
import Model.GameSettings;
import Model.Score;

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
		
		//---------------------------------------------------
		// Save score
		String url = "C:\\Workspaces\\JavaProjects\\TempoKleineFische\\database\\TempoKleineFische.db";
		IDatabaseConnector databaseConnector = new SQLiteDatabaseConnector(url, "", "");
		databaseConnector.connect();
		
		IScoreDAO scoreDAO = new ScoreDAO(databaseConnector.getConnection());
		scoreDAO.addScore(score);	
		
		System.out.println("FishFriendsWonCount: " + scoreDAO.getFisherFriendsWonCount());
		System.out.println("AnglersFriendsWonCount: " + scoreDAO.getAnglersFriendsWonCount());
	}
	

}
