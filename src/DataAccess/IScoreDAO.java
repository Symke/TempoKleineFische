package DataAccess;

import Model.Score;
import java.util.List;
import java.util.Set;

public interface IScoreDAO {
	public Set<Score> readAllScores();
	public void addScore(Score score);
	public int getFisherFriendsWonCount();
	public int getAnglersFriendsWonCount();
	public void deleteScore();
	
}
