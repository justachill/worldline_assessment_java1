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
		HttpSession session=request.getSession(false);
		System.out.println(session.getAttribute("uname"));
		String username="root";
		String pwd="";
		try {
			Connection con=DriverManager.getConnection(url,username,pwd);
			String number=request.getParameter("train");
			String query="Select wallet from login where username='"+session.getAttribute("uname")+"'";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				Integer mon=rs.getInt(1);
				Integer mon2=(Integer) session.getAttribute("fare");
				response.setContentType("text/html");
			    PrintWriter pwriter=response.getWriter(); 
			    pwriter.println("");
			   
			}else {
				response.sendRedirect("index.html");
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
