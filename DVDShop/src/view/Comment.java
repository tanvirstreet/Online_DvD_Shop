package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CommentModel;
import model.CustomerModle;

@WebServlet(description = "write the comment", urlPatterns = { "/comment_dvd" })
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private CommentModel commentModel = new CommentModel();
	private CustomerModle customerModle = new CustomerModle();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		session = request.getSession();
		if (session.getAttribute("username") == null || session.getAttribute("username").equals("")){
			response.sendRedirect("/DVDShop/");
		}
		String dvd_id = request.getParameter("dvd_id").toString();
		
	    out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>DVD Shop || Comment</title>"
				+ "</head>"
				+ "<body>"
				+ "<a href='/DVDShop/'><h2>Home</h2></a><br><br>");
	    if(session.getAttribute("username").equals("admin")){
	    	out.println("<center>"
					+ "<h1> Comments </h1>"
					+ "<table border='1'>"
						+ "<thead>"
							+ "<tr>"
								+ "<th>Date</th>"
								+ "<th>Customer Name</th>"
								+ "<th>Comment</th>"
							+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
			ResultSet rs = commentModel.showComments(dvd_id);
			if(rs == null){
				out.println("<br><h2> NO Data Founds... </h2><br><i>reload or login again.</i>");
			}else{
				try {
					while(rs.next()){
						String com_id = rs.getString(1);
						String cus_name = rs.getString(2);
						String comment = rs.getString(3);
						String date = rs.getString(4);
						
						out.println("<tr>"
								+ "<td>" + date +"</td>"
								+ "<td>" + cus_name +"</td>"
								+ "<td>" + comment +"</td>"
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
			String username = session.getAttribute("username").toString();
			int cus_id = customerModle.getCustomerID(username);
			
			Date dNow = new Date();
		    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-mm-dd");

		    System.out.println("Current Date: " + ft.format(dNow));
			out.println("<center>"
					+ "<h1> Write a Comment </h1>"
					+ "<form method='POST'>"
					+ "<textarea name='comment' rows='6' cols=''65></textarea><br>"
					+ "<input type='hidden' name='dvd_id' value='"+dvd_id+"'>"
					+ "<input type='hidden' name='cus_id' value='"+cus_id+"'>"
					+ "<input type='hidden' name='date' value='"+ft.format(dNow)+"'>"
					+ "<input type='submit' name='Submit' value='Comment'>"
			+ "</form></center>");
		}
		out.println("</body>"
				+ "</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comment = request.getParameter("comment").toString();
		String dvd_id = request.getParameter("dvd_id").toString();
		String cus_id = request.getParameter("cus_id").toString();
		String date = request.getParameter("date").toString();
		System.out.println(comment);
		
		if(commentModel.saveComment(dvd_id, cus_id, comment,date)){
			response.sendRedirect("/DVDShop/dvds");
		}
	}

}
