

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

@WebServlet("/ViewThemesPeriodicalsServlet")
public class ViewThemesPeriodicalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>View periodical's themes</title><link rel=\"stylesheet\" href=\"templated-binary/assets/css/main.css\" />" +
	"<link rel=\"stylesheet\" href=\"adminStyle.css\"></head><body><div class=\"form\">");
		
		try {

			Connection con = DatabaseConnection.initializeDatabase();

			PreparedStatement stmt1 = con.prepareStatement("select * from periodicals");
			ResultSet rs1 = stmt1.executeQuery();

			out.println("<form action=\"ViewThemesPeriodicalsServlet\" method=\"post\">"+
			   		"<p><select name=\"periodicals\" size=\"10\">");
			
			while (rs1.next()) {
				out.println("<option>" + rs1.getInt(1) + " " + rs1.getString(3) + " \"" + rs1.getString(2) + "\", Publisher: " + rs1.getString(5) + ", " + rs1.getString(4) + "</option>");
			}

			out.println("</select>" +
			   		"</p>" 		   		
			  	);
			
			out.println(
			   		"<p><input type=\"submit\" value=\"View themes\"></p>"
			  	);

			out.println("</form></div></body></html>");
			
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String periodical = request.getParameter("periodicals");
		int periodicalId = Integer.parseInt(periodical.split(" ")[0]);
		
		PrintWriter out = response.getWriter();
		
		try {
			
			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt1 = con.prepareStatement(
					"SELECT \"themes\".\"Name\" from \"themes\" join \"periodicals_themes_relation\" on \"periodicals_themes_relation\".\"Theme_id\" = \"themes\".\"Id\""+
					"join \"periodicals\" on \"periodicals\".\"Id\" = \"periodicals_themes_relation\".\"Periodical_id\" where \"periodicals\".\"Id\" = "
					+ periodicalId
					);
			ResultSet rs1 = stmt1.executeQuery();

			out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Themes</title><link rel=\"stylesheet\" href=\"templated-binary/assets/css/main.css\" />"
					);
			out.println("<table><thead><tr><th>Name</th></tr></thead><tbody>");

			while (rs1.next()) {
	
				out.println("<tr><td>" + rs1.getString(1) + "</td></tr>");
			}

			out.println("</table></body></html>");

		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}	
		
	}

}
