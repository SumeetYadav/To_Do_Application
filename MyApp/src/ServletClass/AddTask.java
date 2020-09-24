package ServletClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import BeanClass.Task;
import DaoClass.Dao;

@WebServlet("/AddTask")
public class AddTask extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	    {  
	        response.setContentType("text/html");  
	        PrintWriter out=response.getWriter();  
	        out.println("<html><head>");
	        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
	        		"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
	        		"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
	        		"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
	        out.println("</head><body><div class='container'>");
	        
	        int id=Integer.parseInt(request.getParameter("id"));
	        String taskname=request.getParameter("tname"); 
	        String discription=request.getParameter("disc");
	        LocalDate dat=java.time.LocalDate.now();
			String date=dat.toString();
	        
	         Task task=new Task();
	         task.setTaskname(taskname);
	         task.setDiscription(discription);
	         task.setId(id);
	         task.setDate(date);
	         
	         int status=Dao.AddTask(task);
	        
	        if(status>0)
	        {
				response.sendRedirect("AddNewTask?id="+id+"");
	        }
	        else
	        {
	            out.println("<h1>Sorry! unable to save task</h1>");  
	        }
	    }
}