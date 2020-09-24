package ServletClass;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import BeanClass.Person;

@WebServlet("/VerifyCode")
public class VerifyCode extends HttpServlet 
{  
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        try
        {           
            HttpSession session = request.getSession();
            Person person= (Person) session.getAttribute("authcode");
            
            String code = request.getParameter("authcode");
            
            if(code.equals(person.getCode()))
            {
            	out.println("<script type='text/javascript'>");
			    out.println("alert('Email verification done you can login now.');");
			    out.println("</script>");   
                out.println("<a href='index.html'>Login</a>");
            }
            else
            {
                out.println("Incorrect verification code please try again");
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
}