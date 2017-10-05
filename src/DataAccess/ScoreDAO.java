package DataAccess;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.AbstractDocument.LeafElement;

import Model.Score;

public class ScoreDAO implements IScoreDAO {

	private String filename = "c:\\out\\test.ser";
	private Connection connection; 
	
	public ScoreDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Set<Score> readAllScores() {

	    try {

	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Score");

	        Set<Score> scores = new HashSet<Score>();

	        while(rs.next())

	        {

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
	public void addScore(Score score) {
		
	    try {

	        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Score VALUES (NULL, ?, ?, ?, ?, ?)");

	        preparedStatement.setInt(1, score.getWinner());
	        preparedStatement.setInt(2, score.getFishCount());
	        preparedStatement.setInt(3, score.getAnglersCount());
	        preparedStatement.setInt(4, score.getLeftRiverPatsCount());
	        preparedStatement.setInt(5, score.getRightRiverPartsCount());
	        
	        int i = preparedStatement.executeUpdate();
	   

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }
		
	}
	
	@Override
	public int getAnglersFriendsWonCount() {
		int count = 0;
		try {

	        PreparedStatement preparedStatement = connection.prepareStatement("Select count(*) as AnglersWonCount From score Where winner =" + Score.ANGLER_FRIENDS +";");	        	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        count = resultSet.getInt("AnglersWonCount");
	   

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }
		return count;
	}
	
	@Override
	public int getFisherFriendsWonCount() {
		int count = 0;
		try {

	        PreparedStatement preparedStatement = connection.prepareStatement("Select count(*) as AnglersWonCount From score Where winner =" + Score.FISH_FRIENDS +";");	        	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        count = resultSet.getInt("AnglersWonCount");
	   

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }
		return count;
	}
	
	@Override
	public void deleteScore() {
		// TODO Auto-generated method stub
		
	}
}
