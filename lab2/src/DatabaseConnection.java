import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

	public static Connection initializeDatabase() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/periodicals", 
													 "postgres", "admin");
		return con;
	}
}
