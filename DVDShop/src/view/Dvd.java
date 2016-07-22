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


@WebServlet(description = "main dvd page", urlPatterns = { "/dvds" })
public class Dvd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private DvdModel dvdModel = new DvdModel();
	
	private ResultSet rs;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		session = request.getSession();
		if (session.getAttribute("username") == null || session.getAttribute("username").equals("")){
			response.sendRedirect("/DVDShop/");
		}
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>DVD Shop || DVD List</title>"
				+ "</head>"
				+ "<body>"
				+ "<a href='/DVDShop/'><h2>Home</h2></a><br><br>"
				+ "<a href='/DVDShop/search_dvd?search='>Search</a>");
		if(session.getAttribute("username").equals("admin")){
			out.println("<a href='/DVDShop/add_dvd'>Add New DVD</a><br>"
					+ "<a href='/DVDShop/add_category'>Add Category</a>"
					+ "<center>"
					+ "<h1> DVD List </h1>"
					+ "<table border='1'>"
						+ "<thead>"
							+ "<tr>"
								+ "<th>Category</th>"
								+ "<th>DVD Name</th>"
								+ "<th>Actor</th>"
								+ "<th>Action</th>"
							+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
			
			this.rs = dvdModel.showDVD();
			if(this.rs == null){
				out.println("<br><h2> NO Data Founds... </h2><br><i>reload or login again.</i>");
			}else{
				try {
					while(this.rs.next()){
						int dvd_id = this.rs.getInt(1);
						String ctg_name = this.rs.getString(2);
						String title = this.rs.getString(3);
						String actor = this.rs.getString(4);
						
						out.println("<tr>"
								+ "<td>" + ctg_name +"</td>"
								+ "<td>" + title +"</td>"
								+ "<td>" + actor +"</td>"
								+ "<td><a href='/DVDShop/comment_dvd?dvd_id=" + dvd_id +"'>Comment</a></td>"
								+ "<td><a href='/DVDShop/edit_dvd?dvd_id=" + dvd_id +"'>Edit</a></td>"
								+ "</tr>");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			out.println("</tbody>"
					+ "</table>"
					+ "</center>");
		}
		else{
			out.println("<center>"
					+ "<h1> DVD List </h1>"
					+ "<table>"
						+ "<thead>"
							+ "<tr>"
								+ "<th>Category</th>"
								+ "<th>DVD Name</th>"
								+ "<th>Actor</th>"
								+ "<th>Comment</th>"
							+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
			this.rs = dvdModel.showDVD();
			if(this.rs == null){
				out.println("<br><h2> NO Data Founds... </h2><br><i>reload or login again.</i>");
			}else{
				try {
					while(this.rs.next()){
						int dvd_id = this.rs.getInt(1);
						String ctg_name = this.rs.getString(2);
						String title = this.rs.getString(3);
						String actor = this.rs.getString(4);
						
						out.println("<tr>"
								+ "<td>" + ctg_name +"</td>"
								+ "<td>" + title +"</td>"
								+ "<td>" + actor +"</td>"
								+ "<td><a href='/DVDShop/comment_dvd?dvd_id=" + dvd_id +"'>Comment</a></td>"
								+ "</tr>");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			out.println("</tbody>"
					+ "</table>"
					+ "</center>");
		}
		out.println("</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
