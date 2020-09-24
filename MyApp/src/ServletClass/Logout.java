package ServletClass;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet("/Logout")

public class Logout extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	    {
	        HttpSession session = request.getSession(false);
	        if (session != null) 
	        {
	            session.removeAttribute("email");
	            
	            RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");//index page is of login page redirect their after logout
	            dispatcher.forward(request, response);   
	        }
	    }
}