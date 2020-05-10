

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

@WebServlet("/AdminAddThemeToPeriodicalServlet")
public class AdminAddThemeToPeriodicalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Add theme to periodical</title><link rel=\"stylesheet\" href=\"templated-binary/assets/css/main.css\" />" +
	"<link rel=\"stylesheet\" href=\"adminStyle.css\"></head><body><div class=\"form\">");
		
		try {

			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt = con.prepareStatement("select * from themes");
			ResultSet rs = stmt.executeQuery();

			PreparedStatement stmt1 = con.prepareStatement("select * from periodicals");
			ResultSet rs1 = stmt1.executeQuery();

			out.println("<form action=\"AdminAddThemeToPeriodicalServlet\" method=\"post\">"+
			   		"<p><select name=\"themes\" size=\"10\">");

			while (rs.next()) {
				out.println("<option>" + rs.getInt(1) + " " + rs.getString(2) + "</option>");
			}
			
			out.println("</select>" +
			   		"</p>" +
			   		"<p><select name=\"periodicals\" size=\"10\">"
			  	);
			
			while (rs1.next()) {
				out.println("<option>" + rs1.getInt(1) + " " + rs1.getString(3) + " \"" + rs1.getString(2) + "\", Publisher: " + rs1.getString(5) + ", " + rs1.getString(4) + "</option>");
			}
			
			out.println("</select>" +
			   		"</p>" +
			   		"<p><input type=\"submit\" value=\"Add theme to periodical\"></p>"
			  	);

			out.println("</form></div></body></html>");
			
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String theme = request.getParameter("themes");
		int themeId = Integer.parseInt(theme.split(" ")[0]);
		String periodical = request.getParameter("periodicals");
		int periodicalId = Integer.parseInt(periodical.split(" ")[0]);
		
		PrintWriter out = response.getWriter();
		
		try {
			
			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt1 = con.prepareStatement("select count(*) from periodicals_themes_relation");
			ResultSet rs1 = stmt1.executeQuery();
			rs1.next();
			int nextId = rs1.getInt(1) + 1;

			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO public.periodicals_themes_relation(\"Id\", \"Periodical_id\", \"Theme_id\") VALUES ("
					+ nextId
					+ ", "
					+ periodicalId
					+ ", "
					+ themeId
					+ ");"
					);
			
			
			stmt.execute();
			response.sendRedirect("adminPage.jsp");
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}	
		
	}

}
