

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

@WebServlet("/AdminAddPeriodicalsServlet")
public class AdminAddPeriodicalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String type = request.getParameter("type");

		PrintWriter out = response.getWriter();
		
		try {
			
			Connection con = DatabaseConnection.initializeDatabase();

			PreparedStatement stmt1 = con.prepareStatement(getPeriodicalsCountQuery);
			ResultSet rs1 = stmt1.executeQuery();
			rs1.next();
			int nextId = rs1.getInt(1) + 1;
			
			PreparedStatement stmt = con.prepareStatement("INSERT INTO public.periodicals(" +
					"\"Id\", \"Name\", \"Type\") VALUES (" +
					nextId + ", '" + name + "', '" + type + "');"
					);
			try {
			ResultSet rs = stmt.executeQuery();
			}
			catch (Exception e) {}

			out.println("Periodical " + name + " of type " + type + " added successfully");		
			
		} catch (Exception ex) {
			out.println("Exception: " + ex.getMessage() + ex.getCause());
		}	
	}
	
	private final String getPeriodicalsCountQuery = "select count(*) from periodicals";
}
