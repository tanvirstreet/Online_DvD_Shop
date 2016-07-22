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

@WebServlet(description = "dvd can be edit by id", urlPatterns = { "/edit_dvd" })
public class EditDvD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private DvdModel dvdModel = new DvdModel();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		if (session.getAttribute("username") == null || session.getAttribute("username").equals("") && session.getAttribute("username").equals("admin")){
			response.sendRedirect("/DVDShop/");
		}
		PrintWriter out = response.getWriter();
		String dvd_id = request.getParameter("dvd_id").toString();
		
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>DVD Shop || DVD Add</title>"
				+ "</head>"
				+ "<body>"
				+ "<a <a href='/DVDShop/dvds'>Back</a>"
				+ "<center>"
				+ "<h1> Add DVD </h1>"
				+ "<form method='POST'>");
		
		ResultSet rs = dvdModel.showDVD(dvd_id);
		try {
			while(rs.next()){
				String ctg_name = rs.getString(1);
				String title = rs.getString(2);
				String actor = rs.getString(3);
				
				out.println("Category Name : <b>"+ctg_name+"</b><br>"
						+ "Title : <input type='text' name='title' value='"+title+"'><br>"
						+ "Actor : <input type='text' name='actor' value='"+actor+"'><br>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		out.println("<input type='hidden' name='dvd_id' value='"+dvd_id+"'><br>"
				+ "<input type='submit' name='Submit' value='Confirm'>"
				+ "</form>"
				+ "</center>"
				+ "</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dvd_id = request.getParameter("dvd_id").toString();
		String title = request.getParameter("title").toString();
		String actor = request.getParameter("actor").toString();
		
		if(dvdModel.updateDVD(dvd_id, title, actor)){
			response.sendRedirect("/DVDShop/dvds");
		}
	}

}
