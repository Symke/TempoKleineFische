package dataAccess;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


import data.Score;

public class ScoreDAO implements IScoreDAO {

	// Const
	private static final String TABLE_NAME = "Score";

	private Connection connection;
	private String tableName;

	public ScoreDAO(Connection connection) {
		this(connection, TABLE_NAME);
	}

	public ScoreDAO(Connection connection, String tableName) {
		this.connection = connection;
		this.tableName = tableName;

		createTable();
	}

	public String getTableName() {
		return tableName;
	}

	
	@Override
	public Set<Score> readAllScores() {

		try {

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Score");

			Set<Score> scores = new HashSet<Score>();

			while (rs.next()) {
				Score score = new Score();
				score.setId(rs.getInt("ID"));
				score.setWinner(rs.getInt("Winner"));
				score.setFishCount(rs.getInt("FishCount"));
				score.setAnglersCount(rs.getInt("AnglersCount"));
				score.setLeftRiverPartsCount(rs.getInt("LeftRiverPatsCount"));
				score.setRightRiverPartsCount(rs.getInt("RightRiverPartsCount"));

				scores.add(score);
			}

			return scores;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean addScore(Score score) {
		boolean successful = true;
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO Score VALUES (NULL, ?, ?, ?, ?, ?)");

			preparedStatement.setInt(1, score.getWinner());
			preparedStatement.setInt(2, score.getFishCount());
			preparedStatement.setInt(3, score.getAnglersCount());
			preparedStatement.setInt(4, score.getLeftRiverPatsCount());
			preparedStatement.setInt(5, score.getRightRiverPartsCount());

			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			successful = false;
			ex.printStackTrace();
		}

		return successful;
	}

	@Override
	public int getAnglersFriendsWonCount() {
		int count = -1;
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("Select count(*) as AnglersWonCount From "
					+ getTableName() + " Where winner =" + Score.ANGLER_FRIENDS + ";");
			ResultSet resultSet = preparedStatement.executeQuery();

			count = resultSet.getInt("AnglersWonCount");

		} catch (SQLException ex) {

			ex.printStackTrace();

		}
		return count;
	}

	@Override
	public int getFisherFriendsWonCount() {
		int count = -1;
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("Select count(*) as AnglersWonCount From "
					+ getTableName() + " Where winner =" + Score.FISH_FRIENDS + ";");
			ResultSet resultSet = preparedStatement.executeQuery();

			count = resultSet.getInt("AnglersWonCount");

		} catch (SQLException ex) {

			ex.printStackTrace();

		}
		return count;
	}

	@Override
	public void createTable() {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS " + this.getTableName()
				+ "  (ID           			INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ "   Winner            	INTEGER," + "   FishCount          	INTEGER,"
				+ "   AnglersCount          INTEGER," + "   LeftRiverPartsCount   INTEGER,"
				+ "   RightRiverPartsCout   INTEGER)";

		try {
			Statement stmt = connection.createStatement();
			stmt.execute(sqlCreate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteScore() {
		// TODO Auto-generated method stub

	}
}
