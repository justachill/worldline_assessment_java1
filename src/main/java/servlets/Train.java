package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class Train
 */
@WebServlet("/Train")
public class Train extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Train() {
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
		HttpSession session=request.getSession(false);
		System.out.println(session.getAttribute("uname"));
		String username="root";
		String pwd="";
		try {
			Connection con=DriverManager.getConnection(url,username,pwd);
			String number=request.getParameter("train");
			String query="Select * from train where number='"+number+"'";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				session.setAttribute("fare", rs.getInt(4));
				response.setContentType("text/html");
			    PrintWriter pwriter=response.getWriter(); 
			    pwriter.println("<form action=\"Book\">");
			    pwriter.println("<center>"); 
			    pwriter.println("dept "+rs.getString(2)+"</br>");
			    pwriter.println("arrv "+rs.getString(3)+"</br>");
			    pwriter.println("fare "+rs.getInt(4)+"</br>");
			    pwriter.println("3ac "+rs.getInt(5)+"</br>");
			    pwriter.println("sleeper "+rs.getInt(6)+"</br>");
			    pwriter.println("time "+rs.getInt(7)+"</br>");
			    pwriter.println("Enter the train number ");
			    pwriter.println("<input type=\"text\" name=\"number\"><br/>");
			    pwriter.println("Enter the type ");
			    pwriter.println("<input type=\"text\" name=\"seat\"><br/>");
			    pwriter.println("Enter the name ");
			    pwriter.println("<input type=\"text\" name=\"name\"><br/>");
			    pwriter.println("</br><input type=\"submit\" value=\"Book\">");
			    pwriter.println("</center></form>");
			    
			}else {
				response.sendRedirect("index.html");
			}
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
