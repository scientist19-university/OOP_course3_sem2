

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

@WebServlet("/AdminAddSubscriptionsServlet")
public class AdminAddSubscriptionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("periodical");
		String type = request.getParameter("type");
		double cost = Double.parseDouble(request.getParameter("cost"));

		PrintWriter out = response.getWriter();
		
		try {
			
			
			Connection con = DatabaseConnection.initializeDatabase();

			PreparedStatement stmt1 = con.prepareStatement(getsSubscriptionsCountQuery);
			ResultSet rs1 = stmt1.executeQuery();
			rs1.next();
			int nextId = rs1.getInt(1) + 1;

			PreparedStatement stmt2 = con.prepareStatement("select \"periodicals\".\"Id\" from \"periodicals\" where \"periodicals\".\"Name\" = '" + name + "'");
			ResultSet rs2 = stmt2.executeQuery();
			if (!rs2.next()) {
				out.println("Incorrect periodical name");
				return;
			}
			int periodicalId = rs2.getInt(1);
			
			PreparedStatement stmt = con.prepareStatement("INSERT INTO public.subscriptions(" +
					"\"Id\", \"Type\", \"CostPerMonth\", \"Periodical_id\") VALUES (" +
					nextId + ", '" + type + "', " + cost + ", " + periodicalId + ");"
					);
			try {
			ResultSet rs = stmt.executeQuery();
			}
			catch (Exception e) {}

			out.println("Subscription " + name + " " + type + " for " + cost + " uah per month added successfully");		
			
		} catch (Exception ex) {
			out.println("Exception: " + ex.getMessage() + ex.getCause());
		}	
	}
	
	private final String getsSubscriptionsCountQuery = "select count(*) from subscriptions";
}
