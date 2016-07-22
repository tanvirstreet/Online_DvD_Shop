package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginModel;


@WebServlet(description = "this is the index page", urlPatterns = { "/" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private LoginModel loginMain = new LoginModel();
	
	private boolean invalide = false;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		session = request.getSession();
		if (session.getAttribute("username") == null || session.getAttribute("username").equals("")){
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>DVD Shop || Login</title>"
				+ "</head>"
				+ "<body>"
				+ "<a href='/DVDShop/registration'><b>Registration</b></a>"
				+ "<center>"
				+ "<h1> Login </h1>"
				+ "<form method='post'>"
						+ "User Name : <input type='text' name='username'><br>"
						+ "Password  : <input type='password' name='password'><br>"
						+ "<input type='submit' name='Submit' value='Login'><br>"
						+ "</form>"
						+ "<a href=''><i>Change Password ?</i></a>");

		 if(this.invalide){
			 out.println("<br><br><h2>username and password is invalied.!!</h2>");
		 }
		out.println("</center>"
				+ "</body>"
				+"</html>");
		}else{
//			int role = (int) session.getAttribute("role");
			if(session.getAttribute("username").equals("admin")){response.sendRedirect("/DVDShop/admin_home");}
			else{response.sendRedirect("/DVDShop/customer_home");}
//			else{
//				response.sendRedirect("/DVDShop/");
//			}
		}
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		
		session = request.getSession();
		PrintWriter out = response.getWriter();
		int check = loginMain.checkLogin(username, password);
		if(check == 1){
			session.setAttribute("username", username);
			session.setAttribute("role", check);
			response.sendRedirect("/DVDShop/admin_home");
		}else if(check == 2){
			session.setAttribute("username", username);
			session.setAttribute("role", check);
			response.sendRedirect("/DVDShop/customer_home");
		}else{
			this.invalide = true;
			doGet(request, response);
		}
		
	}

}
