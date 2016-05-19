package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
       BookDAO book;
    RequestDispatcher rd;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession hs = request.getSession();
			System.out.println(hs.getAttribute("username"));
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
		PreparedStatement st;
		PrintWriter pw = response.getWriter();
		try {
			
			st = con.prepareStatement("select * from books where bname=?");
		st.setString(1, request.getParameter("bname"));
			
			ResultSet rs =  st.executeQuery();
			if(rs.next())	
			{
				book = new BookDAO(rs.getInt(1),rs.getInt(4)  ,rs.getString(2), rs.getString(3), rs.getString(5));
				pw.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1.0'>");
				pw.println("<style>label{font-family:sans-serif;display:block;padding-top: 20px;}span.heading{font-family:cursive;font-style:italic;font-size:30px;padding-left: 80px;padding-bottom: 20px;width:420px;position: absolute;}span{font-size:20px;display:inline-block;width:120px;}div.wrapper{background-color: bisque;border-radius: 10px;padding: 20px;border-color: red;border-width: 3px;border-style: solid;width: 400px;height: 250px;margin: auto;position: absolute;top: 0;bottom: 0;left: 0;right: 0;}h2.heading{top: 0;bottom: 0;left: 0;right: 0;}body{background-color: rgba(0,0,0,0,5);}input.btn {border: 0;background-color: #3bab53;width: 80px;height: 30px;font-size: 16px;color: white;}</style></head>");
				pw.println("<body><div class='wrapper'><span class='heading'>Book Found : </span><br><br>");
						pw.println("<label><table><tbody><tr><td>Book ID : </td><td>"+book.getBookid()+"</td></tr><tr><td>Book Name : </td><td>"+book.getName()+"</td></tr><tr><td>Number Of Pages : </td><td>"+book.getNo_of_pages()+"</td></tr><tr><td>Book Author : </td><td>"+book.getAuthor()+"</td></tr><tr><td>Book Review : </td><td>"+book.getReview()+"</td></tr></tbody></table></label>");

						pw.println("</body></html>");
			}
			else
			{
				pw.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1.0'>");
				pw.println("<style>label{font-family:sans-serif;display:block;padding-top: 20px;}span.heading{font-family:cursive;font-style:italic;font-size:30px;padding-left: 80px;padding-bottom: 20px;width:420px;position: absolute;}span{font-size:20px;display:inline-block;width:120px;}div.wrapper{background-color: bisque;border-radius: 10px;padding: 20px;border-color: red;border-width: 3px;border-style: solid;width: 450px;height: 100px;margin: auto;position: absolute;top: 0;bottom: 0;left: 0;right: 0;}h2.heading{top: 0;bottom: 0;left: 0;right: 0;}body{background-color: rgba(0,0,0,0,5);}input.btn {border: 0;background-color: #3bab53;width: 80px;height: 30px;font-size: 16px;color: white;}</style></head>");
				pw.println("<body><div class='wrapper'><span class='heading'>Book Not Found !!! </span><br><br>");
					

						pw.println("</body></html>");
			}
			pw.println("\n");
			pw.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1.0'><style>label.logout{padding-left:60px;}label{font-family:sans-serif;display:block;margin:10px;padding-left: 60px;padding-top: 20px;}</style></head><body><form method='get' action='LogoutServlet'>");
			pw.println("<label class='logout'><input class='btn' type='submit' value='Logout'></label>");
			pw.println("</form></div></body></html>");
		} catch (SQLException e) {
			rd=request.getRequestDispatcher("Error.html");
			rd.forward(request, response);
		}
				
	}

}
