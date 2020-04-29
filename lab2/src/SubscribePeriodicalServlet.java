

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

@WebServlet("/SubscribePeriodicalServlet")
public class SubscribePeriodicalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.sendRedirect("subscribePeriodicalPage.jsp");

		PrintWriter out = response.getWriter();
		out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Subscribe</title><link rel=\"stylesheet\" href=\"templated-binary/assets/css/main.css\" /></head><body>");
		
		try {

			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt = con.prepareStatement(getAllSubscriptionsQuery);
			ResultSet rs = stmt.executeQuery();

			out.println("<form action=\"SubscribePeriodicalServlet\" method=\"post\">"+
			   		"<p><select name=\"subscriptions\" size=\"10\">");

			while (rs.next()) {
				out.println("<option>" + rs.getInt(1) + " \"" + rs.getString(4) + "\" " + rs.getString(2) + " for " + rs.getDouble(3) + " uah per month" + "</option>");
			}
			
			out.println("</select>" +
			   		"</p>" +
			   		"<p><input type=\"number\" value=\"1\" name=\"months\"></p>" +
			   		"<p><input type=\"submit\" value=\"Subscribe\"></p>" +
			  	"</form>");

			out.println("</body></html>");
			
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sub = request.getParameter("subscriptions");
		int months = Integer.parseInt(request.getParameter("months"));
		PrintWriter out = response.getWriter();
		out.println("Chosen subscription: " + sub + " for " + months + " months");
		
		try {
			
			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt1 = con.prepareStatement(getPaymentsCountQuery);
			ResultSet rs1 = stmt1.executeQuery();
			rs1.next();
			int nextId = rs1.getInt(1) + 1;
			
			PreparedStatement stmt3 = con.prepareStatement(
					"select * from \"readers\" where \"Login\"='" + Controller.currentUser + "'"
					);
			ResultSet rs3 = stmt3.executeQuery();

			if (!rs3.next()) return;
			
			int readerId = rs3.getInt(1);

			String[] subInfo = sub.split("\"");
			int subscriptionId = Integer.parseInt((subInfo[0].split(" "))[0]);
			String[] subInfoWithoutName = subInfo[2].split(" ");
			double costPerMonth = Double.parseDouble(subInfoWithoutName[3]);
			
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO public.payments(" + 
							"\"Id\", \"Reader_id\", \"Subscription_id\", \"Months\", \"TotalCost\") VALUES (" +
							nextId + ", " + readerId + ", " + subscriptionId + ", " + months + ", " + months*costPerMonth + ")"
					);
			
			out.println("Reader id#" + readerId + " " + Controller.currentUser);
			out.println("Months: " + months);
			out.println("Total cost: " + months*costPerMonth);
			
			try {
				
				stmt.executeQuery();
			} catch (Exception ex1) {
				System.out.println("Exception: " + ex1.getMessage());
			}
			
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}	
		
	}
	

	private final String getAllSubscriptionsQuery = "select \"subscriptions\".\"Id\", \"subscriptions\".\"Type\", \"subscriptions\".\"CostPerMonth\", \"periodicals\".\"Name\" from" +
"\"subscriptions\" join \"periodicals\" on \"periodicals\".\"Id\" = \"subscriptions\".\"Periodical_id\"";
	
	private final String getPaymentsCountQuery = "select count(*) from payments";
}
