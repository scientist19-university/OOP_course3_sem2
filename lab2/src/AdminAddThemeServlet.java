

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

@WebServlet("/AdminAddThemeServlet")
public class AdminAddThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String theme = request.getParameter("name");
		PrintWriter out = response.getWriter();
		
		try {
			
			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt1 = con.prepareStatement("select count(*) from themes");
			ResultSet rs1 = stmt1.executeQuery();
			rs1.next();
			int nextId = rs1.getInt(1) + 1;
			
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO public.themes(\"Id\", \"Name\") VALUES ("
					+ nextId
					+ ", '"
					+ theme
					+ "');"
					);
			
			stmt.execute();
			
			out.println("Theme \"" + theme + "\" added successfully!");
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}
}
