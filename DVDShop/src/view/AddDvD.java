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

import model.DvdModel;

@WebServlet(description = "dvd can be added", urlPatterns = { "/add_dvd" })
public class AddDvD extends HttpServlet {
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
				+ "<title>DVD Shop || DVD Add</title>"
				+ "</head>"
				+ "<body>"
				+ "<a <a href='/DVDShop/dvds'>Back</a>"
				+ "<center>"
				+ "<h1> Add DVD </h1>"
				+ "<form method='POST'>"
						+ "Category Name : <select name='ctg_id'>");
						ResultSet rs = dvdModel.showCategory();
						try {
							while(rs.next()){
								int ctg_id = rs.getInt(1);
								String ctg_name = rs.getString(2);
								
								out.println("<option value='"+ctg_id+"'>"+ctg_name+"</option>");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (Exception e){
							e.printStackTrace();
						}
							out.println("</select><br>"
						+ "Title : <input type='text' name='title'><br>"
						+ "Actor : <input type='text' name='actor'><br>"
						+ "<input type='submit' name='Submit' value='ADD'>"
				+ "</form>"
				+ "</center>"
				+ "</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ctg_id = request.getParameter("ctg_id").toString();
		String title = request.getParameter("title").toString();
		String actor = request.getParameter("actor").toString();
		
		if(dvdModel.saveDVD(ctg_id, title, actor)){
			response.sendRedirect("/DVDShop/dvds");
		}
	}

}
