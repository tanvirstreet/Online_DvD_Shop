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

import model.CustomerModle;
import model.RequestModel;


@WebServlet("/requests")
public class Requests extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private RequestModel requestModel = new RequestModel();
	private CustomerModle customerModle = new CustomerModle();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		session = request.getSession();
		if (session.getAttribute("username") == null || session.getAttribute("username").equals("")){
			response.sendRedirect("/DVDShop/");
		}
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>DVD Shop || Request</title>"
				+ "</head>"
				+ "<body>"
				+ "<a href='/DVDShop/'><h2>Home</h2></a><br><br>");
		if(session.getAttribute("username").equals("admin")){
			out.println("<center>"
					+ "<h1> Requests </h1>"
					+ "<table border='1'>"
						+ "<thead>"
							+ "<tr>"
								+ "<th>Customer Name</th>"
								+ "<th>DVD Name</th>"
								+ "<th>Comment</th>"
							+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
			ResultSet rs = requestModel.showRequest();
			if(rs == null){
				out.println("<br><h2> NO Data Founds... </h2><br><i>reload or login again.</i>");
			}else{
				try {
					while(rs.next()){
						String req_id = rs.getString(1);
						String cus_name = rs.getString(2);
						String dvd_name = rs.getString(3);
						String comment = rs.getString(4);
						
						out.println("<tr>"
								+ "<td>" + cus_name +"</td>"
								+ "<td>" + dvd_name +"</td>"
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
		}else{
			String username = session.getAttribute("username").toString();
			int cus_id = customerModle.getCustomerID(username);
			out.println("<center>"
					+ "<h1> Create DVD Request </h1>"
					+ "<form method='POST'>"
					+ "Title : <input type='text' name='dvd_name'><br>"
					+ "Comment : <input type='text' name='comment'><br>"
					+ "<input type='hidden' name='cus_id' value='"+cus_id+"'><br>"
					+ "<input type='submit' name='Submit' value='Request'>"
			+ "</form></center>");
		}
		out.println("</body>"
				+ "</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cus_id = request.getParameter("cus_id").toString();
		String dvd_name = request.getParameter("dvd_name").toString();
		String comment = request.getParameter("comment").toString();
		
		if(requestModel.saveRequest(cus_id, dvd_name, comment)){
			response.sendRedirect("/DVDShop/");
		}
	}

}
