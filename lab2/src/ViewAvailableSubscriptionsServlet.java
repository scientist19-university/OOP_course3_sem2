

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewAvailableSubscriptionsServlet")
public class ViewAvailableSubscriptionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		try {

			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt = con.prepareStatement(
					"select * from \"readers\" where \"Login\"='" + Controller.currentUser + "'"
					);
			ResultSet rs = stmt.executeQuery();
			
			if (!rs.next()) return;
				
			out.println("You are user #" + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			out.println("Here are all available subscriptions: ");
			PreparedStatement stmt1 = con.prepareStatement(getAllSubscriptionsQuery);
			ResultSet rs1 = stmt1.executeQuery();
			
			while (rs1.next()) {

				out.println(rs1.getString(1) + ", " + rs1.getDouble(2) + " uah per month, " + rs1.getString(3));
			}
			
			
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	
	private final String getAllSubscriptionsQuery = "select \"subscriptions\".\"Type\", \"subscriptions\".\"CostPerMonth\", \"periodicals\".\"Name\" from" +
"\"subscriptions\" join \"periodicals\" on \"periodicals\".\"Id\" = \"subscriptions\".\"Periodical_id\"";
}
