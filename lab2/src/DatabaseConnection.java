import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

	private static boolean instantiated = false;
	private static Connection connection;
	
	public static Connection initializeDatabase() throws ClassNotFoundException, SQLException {
		
		if (!instantiated) {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/periodicals", 
					 "postgres", "admin");
			instantiated = true;
		}
			
		return connection;
	}
}
