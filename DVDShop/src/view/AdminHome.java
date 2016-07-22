package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "this page is admin home", urlPatterns = { "/admin_home" })
public class AdminHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		session = request.getSession();
		if (session.getAttribute("username") == null || session.getAttribute("username").equals("")){
			response.sendRedirect("/DVDShop/");
		}
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>DVD Shop || Home</title>"
				+ "</head>"
				+ "<body>"
				+ "<form method='post'>"
				+ "<input type='submit' name='Submit' value='LogOut'><br>"
				
				+ "</form>"
				+ "<center>"
				+ "<h1> Admin </h1><br>"
				+ "<a href='/DVDShop/dvds'> DVD </a><br>"
				+ "<td><a href='/DVDShop/customers'> Customers </a><br>"
				+ "<td><a href='/DVDShop/requests'> Requests </a><br>"
				+ "</center>"
				+ "</body>"
				+"</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.invalidate();
		response.sendRedirect("/DVDShop/");
	}

}
