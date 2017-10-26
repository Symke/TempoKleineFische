package dataAccess;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseConnector {
	public void connect() throws SQLException;
	public Connection getConnection() throws SQLException;
	public void close();
}
