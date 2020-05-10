

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

@WebServlet("/AddPreferenceServlet")
public class AddPreferenceServlet extends HttpServlet {
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
				
			PreparedStatement stmt1 = con.prepareStatement("select * from themes");
			ResultSet rs1 = stmt1.executeQuery();

			out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Add preference</title>"
					+ "<link rel=\"stylesheet\" href=\"templated-binary/assets/css/main.css\" />"
					+ "<link rel=\"stylesheet\" href=\"adminStyle.css\">"
					+ "</head><body>");
			out.println("<div class=\"form\"><form action=\"AddPreferenceServlet\" method=\"post\">"+
			   		"<p><select name=\"themes\" size=\"10\">");

			while (rs1.next()) {
				out.println("<option>" + rs1.getInt(1) + " " + rs1.getString(2) + "</option>");
			}
			
			out.println("</select>" +
			   		"</p>" +
			   		"<p><input type=\"submit\" value=\"Add\"></p>" +
			  	"</form>");
			
			out.println("</div></body></html>");
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String theme = request.getParameter("themes");
		PrintWriter out = response.getWriter();
		
		try {
			
			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt1 = con.prepareStatement("select count(*) from readers_themes_relation");
			ResultSet rs1 = stmt1.executeQuery();
			rs1.next();
			int nextId = rs1.getInt(1) + 1;
			
			PreparedStatement stmt3 = con.prepareStatement(
					"select * from \"readers\" where \"Login\"='" + Controller.currentUser + "'"
					);
			ResultSet rs3 = stmt3.executeQuery();

			if (!rs3.next()) return;
			
			int readerId = rs3.getInt(1);

			String[] themeInfo = theme.split(" ");
			int themeId = Integer.parseInt(themeInfo[0]);
			
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO public.readers_themes_relation(\"Id\", \"Reader_id\", \"Theme_id\") VALUES "
					+ "("
					+ nextId
					+ ", "
					+ rs3.getInt(1)
					+ ", "
					+ themeId
					+ ");"
					);
			
			
			stmt.execute();

			out.println("Theme successfully added!");
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}	
		
	}

}
