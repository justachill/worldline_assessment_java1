package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Signin
 */
@WebServlet("/Signin")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/test";//dbserver address&dbname
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username="root";
		String pwd="";
		try {
			Connection con=DriverManager.getConnection(url,username,pwd);
			String name=request.getParameter("name");
			String uname=request.getParameter("username");
			String pswd=request.getParameter("password");
			String mobile=request.getParameter("mobile");
			String mail=request.getParameter("mail");
			String age=request.getParameter("age");
			String sex=request.getParameter("sex");
			String berth=request.getParameter("berth");
			String master=request.getParameter("master");
			Integer wallet=Integer.parseInt(request.getParameter("wallet"));
			String query="INSERT INTO login value('"+uname+"','"+pswd+"','"+0+"','"+name+"','"+mobile+"','"+mail+"','"+age+"','"+sex+"','"+berth+"','"+master+"',"+wallet+")";
			Statement st=con.createStatement();
			st.execute(query);
			response.sendRedirect("book.html");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
