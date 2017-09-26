package DataAccess;

import Model.Score;
import java.util.List;

public interface IScoreDAO {
	public List<Score> readAllScores();
	public void addScore();
	public void deleteScore();
	
}
