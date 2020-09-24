package ServletClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DaoClass.Dao;

@WebServlet("/Deactivate")
public class Deactivate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Deactivate() 
    {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();
        int id=Integer.parseInt(request.getParameter("id")); 
        
        PreparedStatement preparedstatement=null;
		Connection connection=null;
        try
        {
        	connection=Dao.getConnection();
        	preparedstatement=connection.prepareStatement("update signup set activatedeactivate=? where id=?");
        	
        	preparedstatement.setString(1,"INACTIVE");
        	preparedstatement.setInt(2,id);
        	int status=preparedstatement.executeUpdate();
        	
        	if(status==1)
        	{
        		response.sendRedirect("Admin");
        	}
        	else
        	{
        		out.print("<h1>Not Done</h1>");
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