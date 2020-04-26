import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DemoDB {

	public static void main(String[] args) {

		System.out.println("Hello world! Let's start");
		try {
		Connection con = DatabaseConnection.initializeDatabase();
		PreparedStatement stmt = con.prepareStatement("select * from readers");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
		}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void doStuff() {
		/*
try {
			
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/periodicals", 
														 "postgres", "admin");
			
			PreparedStatement stmt = con.prepareStatement("select * from readers");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			
			
			while (true) {
				
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		*/
	}
	
	private final String getAllReadersQuery = "select * from readers";
	private final String getAllAdminsQuery = "select * from admins";
	private final String getAllSubscriptionsQuery = "select * from subscriptions";
	private final String getAllPaymentsQuery = "select * from payments";
	private final String getAllPeriodicalsQuery = "select * from periodicals";
}
