package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DvdModel;

@WebServlet("/add_category")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private DvdModel dvdModel = new DvdModel();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		if (session.getAttribute("username") == null || session.getAttribute("username").equals("") && session.getAttribute("username").equals("admin")){
			response.sendRedirect("/DVDShop/");
		}
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>DVD Shop || Category Add</title>"
				+ "</head>"
				+ "<body>"
				+ "<a <a href='/DVDShop/dvds'>Back</a>"
				+ "<center>"
				+ "<h1> Add Category </h1>"
				+ "<form method='POST'>"
						+ "Category Name : <input type='text' name='ctg_name'><br>"
						+ "<input type='submit' name='Submit' value='ADD'>"
				+ "</form>"
				+ "</center>"
				+ "</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ctg_name = request.getParameter("ctg_name").toString();
		
		if(dvdModel.saveCategory(ctg_name)){
			response.sendRedirect("/DVDShop/dvds");
		}
	}

}
