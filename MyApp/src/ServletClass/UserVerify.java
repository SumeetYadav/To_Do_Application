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
import DaoClass.SendEmail;

@WebServlet("/UserVerify")
public class UserVerify extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
			protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
		    {
		        response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        try 
		        {
		           //feth form value
		           String email = request.getParameter("email");
		           
		      	   //create instance object of the SendEmail Class
		      	   //get the 6-digit code
		           String code = SendEmail.getRandomNumber();
		           
		      	   //create new user using all information
		           Person person = new Person(email,code);
		           
		           //call the send email method
		           boolean sent = SendEmail.sendEmail(person);
		           
		      		//check if the email send successfully
		           if(sent)
		           {
		               HttpSession session  = request.getSession();
		               session.setAttribute("authcode", person);
		               response.sendRedirect("Verify.jsp");
		           }
		           else
		           {
		      		  out.println("Failed to send verification email");
		      	   }      
		        }
		        catch (Exception ex) 
				{
					ex.printStackTrace();
				}
		    }
		    @Override
		    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
		    {
		        doGet(request, response);
		    }
}