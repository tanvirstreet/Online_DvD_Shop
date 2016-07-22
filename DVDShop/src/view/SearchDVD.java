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

@WebServlet("/search_dvd")
public class SearchDVD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private DvdModel dvdModel = new DvdModel();
	
	private ResultSet rs;
	
	//public String search;
	
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
				+ "<center>"
				+ "<form method='GET'>"
				+ "DvD Name : <input type='text' name='search'> "
				+ "<input type='submit' name='Submit' value='Search'><br>"
				+ "</form>");
				out.println("<h1> DVD List </h1>"
						+ "<table border='1'>"
							+ "<thead>"
								+ "<tr>"
									+ "<th>Category</th>"
									+ "<th>DVD Name</th>"
									+ "<th>Actor</th>"
								+ "</tr>"
							+ "</thead>"
							+ "<tbody>");
				
				String search;
				if(request.getParameter("search").toString() == "" && request.getParameter("search").toString() == null ){
					search = "";
				}else{
					search = request.getParameter("search").toString();
				}
				System.out.println("GET Request: search = "+search);
				rs = dvdModel.searchDvds(search);
				try {
					while(rs.next()){
						int dvd_id = this.rs.getInt(1);
						String ctg_name = this.rs.getString(2);
						String title = this.rs.getString(3);
						String actor = this.rs.getString(4);
						
						out.println("<tr>"
								+ "<td>" + ctg_name +"</td>"
								+ "<td>" + title +"</td>"
								+ "<td>" + actor +"</td>"
								+ "</tr>");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				}
				
				out.println("</tbody>"
						+ "</table>"
						+ "</center>"
						+ "</body>"
						+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search;
		if(request.getParameter("search").toString() == "" && request.getParameter("search").toString() == null ){
			search = "";
		}else{
			search = request.getParameter("search").toString();
		}
				
		System.out.println("POST Request: search = "+search);
		response.sendRedirect("/DVDShop/search_dvd?search="+search);
//		rs = dvdModel.searchDvds(search);
//		searchFlag = true;
//		doGet(request, response);
	}

}
