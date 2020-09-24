package ServletClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DaoClass.Dao;  

@WebServlet("/DeleteTask")  

public class DeleteTask extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}
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
	        int tid=Integer.parseInt(request.getParameter("tid"));
	        HttpSession session=request.getSession(false);
	    	int id=(int) session.getAttribute("id");
	        String query="delete from task where tid=?";
	        Connection connection=null;
	        connection=Dao.getConnection();
	        PreparedStatement preparedstatement=null;
	 	        try
	 	        {  
	 	            preparedstatement=connection.prepareStatement(query);  
	 	            preparedstatement.setInt(1,tid);  
	 	            
	 	            int status=preparedstatement.executeUpdate();  
	 	            if(status==1)
	 	            {
	 	            	response.sendRedirect("ViewAllTask?id="+id+"");
	 	            }
	 	            else
	 	            {
	 	            	out.print("<h1>Error while deleting</h1>"); 
	 	            }
	 	        }
	 	        catch(Exception e)
	 	        {
	 	        	e.printStackTrace();
	 	        } 
	 	       finally
	 			{
	 				if(preparedstatement != null)
	 				{
	 					try	
	 					{
	 						preparedstatement.close();
	 					}
	 					catch(SQLException e)
	 					{
	 						e.printStackTrace();
	 					}
	 				}
	 				if(connection != null)
	 				{
	 					try
	 					{
	 						connection.close();
	 					}
	 					catch(SQLException e)
	 					{
	 						e.printStackTrace();
	 					}
	 				}
	 			}
	}  
}