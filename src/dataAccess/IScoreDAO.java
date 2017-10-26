package dataAccess;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import data.Score;

public interface IScoreDAO {
	public Set<Score> readAllScores();
	public boolean addScore(Score score);
	public int getFisherFriendsWonCount();
	public int getAnglersFriendsWonCount();
	public void deleteScore();
	public void createTable();
}
