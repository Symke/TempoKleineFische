package DataAccess;

import java.sql.Connection;

public interface IDatabaseConnector {
	public void connect();
	public Connection getConnection();
	public void close();
}
