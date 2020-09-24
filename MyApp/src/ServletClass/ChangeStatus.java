package ServletClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import BeanClass.Task;
import DaoClass.Dao;

@WebServlet("/ChangeStatus")
public class ChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangeStatus() 
    {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession(false);
		int id=(int) session.getAttribute("id");
		
		Task task=new Task();
		int tid=Integer.parseInt(request.getParameter("tid"));
		String status="";
		Connection connection=null;
		PreparedStatement preparedstatement=null;
		ResultSet resultset=null;
		try
        {  
            connection=Dao.getConnection();  
            preparedstatement =connection.prepareStatement("select * from task where tid=?");  
            preparedstatement.setInt(1,tid);
            
            resultset=preparedstatement.executeQuery();  
            if(resultset.next())
            { 
            	status=resultset.getString("status");
            } 
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		finally
		{
			if(resultset != null)
			{
				try	
				{
					resultset.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
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
		
	    if(status.equals("New"))
		{
	    	task.setStatus("Complete");
		}
		else if(status.equals("Complete"))
		{
			task.setStatus("New");
		}
	    task.setTid(tid);
		Dao.editStatus(task);
		response.sendRedirect("ViewAllTask?id="+id+"");
	}
}