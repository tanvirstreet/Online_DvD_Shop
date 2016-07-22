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

@WebServlet("/customers")
public class Customers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private CustomerModle customerModle = new CustomerModle();
	private ResultSet rs , search_rs;
	private boolean searchFlag = false;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		session = request.getSession();
		if (session.getAttribute("username") == null || session.getAttribute("username").equals("") && session.getAttribute("username").equals("admin")){
			response.sendRedirect("/DVDShop/");
		}
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>DVD Shop || Customer List</title>"
				+ "</head>"
				+ "<body>"
				+ "<a href='/DVDShop/'><h2>Home</h2></a><br><br>"
				+ "<center>"
				+ "<h1> Customer List </h1>"
					+ "<form method='post'>"
					+ "Customer Name : <input type='text' name='cus_name'> "
					+ "<input type='submit' name='Submit' value='Search'><br>"
					+ "</form>"
				+ "<table border='1'>"
					+ "<thead>"
						+ "<tr>"
							+ "<th>Customer Name</th>"
							+ "<th>Gender</th>"
							+ "<th>Date of Birth</th>"
						+ "</tr>"
					+ "</thead>"
					+ "<tbody>");
		if(searchFlag){
			this.rs = this.search_rs;
			this.searchFlag = false;
		}else{
			this.rs = customerModle.showCustomer();
		}
		if(rs == null){
			out.println("<br><h2> NO Data Founds... </h2><br><i>reload or login again.</i>");
		}else{
			try {
				while(rs.next()){
					int cus_id = rs.getInt(1);
					int usr_id = rs.getInt(2);
					String cus_name = rs.getString(3);
					String gender = rs.getString(4);
					String dob = rs.getString(5);
					
					out.println("<tr>"
							+ "<td>" + cus_name +"</td>"
							+ "<td>" + gender +"</td>"
							+ "<td>" + dob +"</td>"
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
				+ "</center>"
				+ "</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cus_name = request.getParameter("cus_name").toString();
		
		System.out.println("POST Request: search = "+cus_name);
		this.search_rs = customerModle.searchCustomer(cus_name);
		searchFlag = true;
		doGet(request, response);
	}

}
