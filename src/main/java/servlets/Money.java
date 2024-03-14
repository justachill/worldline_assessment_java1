package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Money
 */
@WebFilter("/Book")
public class Money extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Money() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("123");
		
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
			String number=request.getParameter("train");
			String query="Select wallet from login where username='"+request.getParameter("usnm")+"'";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
			response.setContentType("text/html");
		    PrintWriter pwriter=response.getWriter();
			if(rs.next()) {
				Integer mon=rs.getInt(1);
				Integer mon2=Integer.parseInt(request.getParameter("fare"));
				if(mon<mon2) {
				    pwriter.println("<center>Not enough money</center>");
				    pwriter.println("<a href=\"book.html\">Go Back</a>");
				}else {
					System.out.println("got");
					query="UPDATE login SET wallet = wallet - "+mon2+" WHERE name = '"+request.getParameter("usnm")+"'";
					st.execute(query);
					query="UPDATE train SET "+request.getParameter("seat")+" = "+request.getParameter("seat")+" - 1 WHERE number = '"+request.getParameter("number")+"'";
					st.execute(query);					
				}
			   
			}else {
				System.out.println("Fine");
				//
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
