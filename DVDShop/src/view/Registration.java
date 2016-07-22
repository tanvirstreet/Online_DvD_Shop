package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RegisterModel;

@WebServlet(description = "new user can be registered here", urlPatterns = { "/registration" })
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RegisterModel registerModel = new RegisterModel();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>DVD Shop || Registration</title>"
				+ "</head>"
				+ "<body>"
				+ "<center>"
				+ "<form method='post'>"
					+ "Full Name : <input type='text' name='cus_name'><br>"
					+ "User Name : <input type='text' name='username'><br>"
					+ "Password  : <input type='password' name='password'><br>"
					+ "Gender : Male <input type='radio' name='gender' value='male'>"
					+ " Female <input type='radio' name='gender' value='female'><br>"
					+ "Date of Birth : <input type='date' name='dob'><br>"
					+ "<input type='submit' name='Submit' value='Sign Up'>"
				+ "</form>"
				+ "</center>"
				+ "</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cus_name = request.getParameter("cus_name").toString();
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		String gender = request.getParameter("gender").toString();
		String dob = request.getParameter("dob").toString();
		
		if(registerModel.save(cus_name, username, password, gender, dob)){
			response.sendRedirect("/DVDShop/");
		}
		
	}

}
