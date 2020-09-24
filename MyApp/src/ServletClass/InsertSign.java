package ServletClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import BeanClass.Person;
import DaoClass.Dao;

@WebServlet("/InsertSign")
public class InsertSign extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	response.setContentType("text/html");  
        PrintWriter out=response.getWriter(); 
        out.println("<html><head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
        		"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
        		"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
        		"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
        out.println("</head><body><div class='container'>");
        
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String confirmpassword=request.getParameter("confirmpassword");
		LocalDate dat=java.time.LocalDate.now();
		String date=dat.toString();
		
		HttpSession session=request.getSession();
		session.setAttribute("email", email);
		
		boolean flag=false;
		if(password.equals(confirmpassword))
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
		
		if(flag==true)
		{
		
			Person person=new Person(firstname,lastname,gender,dob,email,password,confirmpassword,date);
			try
			{
				int k=Dao.insertIntoSignUp(person);
				
				if(k==0)
				{
					out.println("<script type='text/javascript'>");
					out.println("alert('Email id already present please change your email id.');");
					out.println("</script>");
					out.println("</br></br><a href='Reg.html' class='btn btn-info'>Reenter again</a>");
				}
				
				else if(k==1)
				{
					out.println("<script type='text/javascript'>");
					out.println("alert('Signup Successfully');");
					out.println("alert('Thank you for registration! Please verify your email to login.');");
					out.println("</script>");
					out.println("<h1>Thank you for registration! Please verify your email to login.</h1>");
					out.println("</br></br><a href='UserVerify?email="+email+"' class='btn btn-info'>VerifyEmail</a>");
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else if(flag==false)
		{
			out.println("<script type='text/javascript'>");
			out.println("alert('Sorry password and confirm password does not match!Reenter again');");
			out.println("</script>");
			out.println("</br></br><a href='Reg.html' class='btn btn-info'>Reenter again</a>");
		}
	}
}