

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
			
			PreparedStatement stmt1 = con.prepareStatement(getAllSubscriptionsQuery);
			ResultSet rs1 = stmt1.executeQuery();
			
			out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Subscriptions</title><link rel=\"stylesheet\" href=\"templated-binary/assets/css/main.css\" /></head><body>");
			out.println("<table><thead><tr><th>Type</th><th>Cost per month</th><th>Periodical</th></tr></thead><tbody>");

			while (rs1.next()) {

				out.println("<tr><td>" + rs1.getString(1) + "</td><td>" + rs1.getDouble(2) + " uah</td><td>" + rs1.getString(3) + "</td></tr>");
			}
			
			out.println("</table></body></html>");
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	
	private final String getAllSubscriptionsQuery = "select \"subscriptions\".\"Type\", \"subscriptions\".\"CostPerMonth\", \"periodicals\".\"Name\" from" +
"\"subscriptions\" join \"periodicals\" on \"periodicals\".\"Id\" = \"subscriptions\".\"Periodical_id\"";
}
