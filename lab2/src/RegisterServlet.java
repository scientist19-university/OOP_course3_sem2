

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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("loginReg");
		String password = request.getParameter("passwordReg");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthDate = request.getParameter("birthDate");
		String sex = request.getParameter("sex");
		
		PrintWriter out = response.getWriter();
		
		try {
			
			
			Connection con = DatabaseConnection.initializeDatabase();
			
			PreparedStatement stmt = con.prepareStatement(
					"select * from \"readers\" where \"Login\"='" + login + "'"
					);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				out.println("User with this login is already registered.");
				return;
			}			
			
			if (password == "" || password == null) {

				out.println("Password cannot be empty.");
				return;
			} 
			
			if (password.length() > 30) {

				out.println("Password length must be from 1 to 30 symbols.");
				return;
			}
				
			PreparedStatement stmt1 = con.prepareStatement(getReadersCountQuery);
			ResultSet rs1 = stmt1.executeQuery();
			rs1.next();
			int nextId = rs1.getInt(1) + 1;

			
			PreparedStatement stmt2 = con.prepareStatement(
					"INSERT INTO public.readers(\"Id\", \"FirstName\", \"LastName\", \"Login\", \"Password\", \"BirthDate\", \"Sex\") VALUES ("+
							nextId + ", '" + firstName + "', '" + lastName + "', '" + login + "', '" + password + "', '" + birthDate + "', '" + sex + "')"
					);
			try {
				
				stmt2.execute();
			} catch (Exception ex1) {
				System.out.println("Exception: " + ex1.getMessage());
			}
			
			Controller.currentUser = login;
			String path = "/mainPage.jsp";
	        ServletContext servletContext = getServletContext();
	        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
	        requestDispatcher.forward(request, response);
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}		
	}

	private final String getReadersCountQuery = "select count(*) from readers";
}
