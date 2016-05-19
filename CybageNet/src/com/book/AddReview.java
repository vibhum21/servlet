package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddReview
 */
@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs = request.getSession();
		try {
			
			if(hs.getAttribute("username")==null)
			{
				response.sendRedirect("Login.html");
			}
			Class.forName("com.mysql.jdbc.Driver");
			try {
				
				 con = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
				 System.out.println("Connected Successfully");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String review =  request.getParameter("review");
		
		PreparedStatement st,st1;
		PrintWriter pw  = response.getWriter();
		try {
				st  = con.prepareStatement("select bookid from book where bname=?");
				st.setString(1,(String) hs.getAttribute("comment"));
				ResultSet rs  = st.executeQuery();
				while(rs.next())
				{
					st1 = con.prepareStatement("insert into review values(?,?)");
					st1.setInt(1,rs.getInt(1));
					st1.setString(2,review);
					int a = st1.executeUpdate();
					if(a>0)
					{
						response.getWriter().append("Successfully Reviewed");
					}
					else
					{
						response.getWriter().append("Error Occurred");
					}
				}
				pw.println("<form method='post' action='LogoutServlet'>");
				pw.println("<input type='submit' value='Logout'>");
				pw.println("</form>");
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
