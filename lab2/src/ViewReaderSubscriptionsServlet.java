

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
				
			//out.println("You are user #" + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			//out.println("Here are your subscriptions: ");
			PreparedStatement stmt1 = con.prepareStatement(subscriptionsByLoginQuery);
			ResultSet rs1 = stmt1.executeQuery();

			out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Subscribe</title><link rel=\"stylesheet\" href=\"templated-binary/assets/css/main.css\" /></head><body>");
			out.println("<table><thead><tr><th>Name</th><th>Type</th><th>Subscription</th></tr></thead><tbody>");

			while (rs1.next()) {

				out.println("<tr><td>" + rs1.getString(1) + "</td><td>" + rs1.getString(2) + "</td><td>" + rs1.getString(3) + "</td></tr>");
			}
			
			out.println("</table></body></html>");
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	
	private final String subscriptionsByLoginQuery = "select \"periodicals\".\"Name\", \"periodicals\".\"Type\", \"subscriptions\".\"Type\" from \"payments\"" +
			"join \"subscriptions\" on \"subscriptions\".\"Id\" = \"payments\".\"Subscription_id\"" +
			"join \"periodicals\" on \"periodicals\".\"Id\" = \"subscriptions\".\"Periodical_id\"" +
			"join \"readers\" on \"readers\".\"Id\" = \"payments\".\"Reader_id\"" +
			"where \"readers\".\"Login\" = '" + Controller.currentUser + "'";
}
