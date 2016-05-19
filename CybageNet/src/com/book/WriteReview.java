package com.book;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WriteReview
 */
@WebServlet("/WriteReview")
public class WriteReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw  = response.getWriter();
		HttpSession hs = request.getSession();
		
		if(hs.getAttribute("username")==null)
		{
			response.sendRedirect("Login.html");
		}
		response.getWriter().append("<html><body><form method='post' action='AddReview'>");
		response.getWriter().append("<textarea rows='4' cols='50' name='review'></textarea>");
		response.getWriter().append("<input type='submit' value='Confirm'></form></body></html>");
		String radio = request.getParameter("list");
		hs.setAttribute("comment", radio);
		pw.println("<form method='get' action='LogoutServlet'>");
		pw.println("<input type='submit' value='Logout'>");
		pw.println("</form>");
	}

}
