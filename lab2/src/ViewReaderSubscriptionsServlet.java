

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewReaderSubscriptionsServlet")
public class ViewReaderSubscriptionsServlet extends HttpServlet {
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
			out.println("Here are your subscriptions: ");
			PreparedStatement stmt1 = con.prepareStatement(subscriptionsByLoginQuery);
			ResultSet rs1 = stmt1.executeQuery();
			
			while (rs1.next()) {

				out.println(rs1.getString(1) + " " + rs1.getString(2) + " " + rs1.getString(3));
			}
			
			
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	
	private final String subscriptionsByLoginQuery = "select \"periodicals\".\"Type\", \"periodicals\".\"Name\", \"subscriptions\".\"Type\" from \"payments\"" +
			"join \"subscriptions\" on \"subscriptions\".\"Id\" = \"payments\".\"Subscription_id\"" +
			"join \"periodicals\" on \"periodicals\".\"Id\" = \"subscriptions\".\"Periodical_id\"" +
			"join \"readers\" on \"readers\".\"Id\" = \"payments\".\"Reader_id\"" +
			"where \"readers\".\"Login\" = '" + Controller.currentUser + "'";
}
