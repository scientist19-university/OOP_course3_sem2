

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

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String index = request.getParameter("adminIndex");
		
		PrintWriter out = response.getWriter();
		
		try {
					
			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt = con.prepareStatement(
					"select * from \"admins\" where \"Login\"='" + login + "'"
					);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				PreparedStatement stmt1 = con.prepareStatement(
						"select * from \"admins\" where \"Login\"='" + login + "' and \"Password\"='" + password + "' and \"AdminIndex\"='" + index + "'"  
						);
				ResultSet rs1 = stmt1.executeQuery();
				if (rs1.next()) out.println(rs1.getInt(1) + " " + rs1.getString(2) + " " + rs1.getString(3) + " " + rs1.getString(4));
				else {
					out.println("incorrect password or admin index");
					return;
				}
			}
			else {
				out.println("no such admin name");		
				return;
			}

			response.sendRedirect("adminPage.jsp");
			
		} catch (Exception ex) {
			out.println("Exception: " + ex.getMessage() + ex.getCause());
		}		
	}
}
