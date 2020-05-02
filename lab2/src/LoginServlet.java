

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		
		try {
			
			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt = con.prepareStatement(
					"select * from \"readers\" where \"Login\"='" + login + "'"
					);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				PreparedStatement stmt1 = con.prepareStatement(
						"select * from \"readers\" where \"Login\"='" + login + "' and \"Password\"='" + password + "'" 
						);
				ResultSet rs1 = stmt1.executeQuery();
				if (rs1.next()) out.println(rs1.getInt(1) + " " + rs1.getString(2) + " " + rs1.getString(3));
				else out.println("incorrect password");
			}
			else
				out.println("no such login registered");			

			Controller.currentUser = login;
			response.sendRedirect("mainPage.jsp");
			
		} catch (Exception ex) {
			out.println("Exception: " + ex.getMessage() + ex.getCause());
		}		
		
	}
}
