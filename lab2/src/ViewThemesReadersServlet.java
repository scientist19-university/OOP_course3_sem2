

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

@WebServlet("/ViewThemesReadersServlet")
public class ViewThemesReadersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		try {
	
			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt1 = con.prepareStatement("select * from readers");
			ResultSet rs1 = stmt1.executeQuery();

			out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>View reader's preferences</title><link rel=\"stylesheet\" href=\"templated-binary/assets/css/main.css\" />"
					+ "<link rel=\"stylesheet\" href=\"templated-binary/assets/css/main.css\" /><link rel=\"stylesheet\" href=\"adminStyle.css\"></head><body>");

			out.println("<div class=\"form\"><form action=\"ViewThemesReadersServlet\" method=\"post\">"+
			   		"<p><select name=\"readers\" size=\"10\">");
			
			while (rs1.next()) {
				out.println("<option>" + rs1.getInt(1) + " " + rs1.getString(2) + " " + rs1.getString(3) + ", born: " + rs1.getDate(6) + ", " + rs1.getString(7) + "</option>");
			}

			out.println("</select>" +
			   		"</p>" 		   		
			  	);
			
			out.println(
			   		"<p><input type=\"submit\" value=\"View preferences\"></p>"
			  	);

			out.println("</form></div></body></html>");
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String reader = request.getParameter("readers");
		int readerId = Integer.parseInt(reader.split(" ")[0]);
		
		PrintWriter out = response.getWriter();
		
		try {
			
			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt1 = con.prepareStatement(
					"SELECT \"themes\".\"Name\" from \"themes\""+
					"join \"readers_themes_relation\" on \"readers_themes_relation\".\"Theme_id\" = \"themes\".\"Id\""+
					"join \"readers\" on \"readers_themes_relation\".\"Reader_id\" = readers.\"Id\""+
					"where \"readers\".\"Id\" = " + readerId
					);
			ResultSet rs1 = stmt1.executeQuery();

			out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Themes</title><link rel=\"stylesheet\" href=\"templated-binary/assets/css/main.css\" />"

					+ "<link rel=\"stylesheet\" href=\"adminStyle.css\"></head><body><div class=\"form\">");
			out.println("<div class=\"form\"><table><thead><tr><th>Name</th></tr></thead><tbody>");

			while (rs1.next()) {
	
				out.println("<tr><td>" + rs1.getString(1) + "</td></tr>");
			}

			out.println("</table><div></body></html>");

		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}	
		
	}

}
