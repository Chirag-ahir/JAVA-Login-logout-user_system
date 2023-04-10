package com.usystem.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String uemail = request.getParameter("uemail");
		String upwd = request.getParameter("upwd");

		Connection con = null;
		RequestDispatcher dispatcher = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/User_Portal", "root", "Prin@2014");
			PreparedStatement ps = con.prepareStatement("INSERT INTO user_portal" + " (uname, uemail, upwd) values " + " (?,?,?);");
			ps.setString(1, uname);
			ps.setString(2, uemail);
			ps.setString(3, upwd);

			int rowCount = ps.executeUpdate();
			dispatcher = request.getRequestDispatcher("login.jsp");
			
			if (rowCount > 0) {
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("status", "failed");
			}
			
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
