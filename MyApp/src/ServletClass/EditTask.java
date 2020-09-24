package ServletClass;

import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import BeanClass.Task;
import DaoClass.Dao; 

@WebServlet("/EditTask") 

public class EditTask extends HttpServlet
{ 
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			doPost(request,response);
		}
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	    { 
	    	
	    	HttpSession session=request.getSession(false);
	    	int id=(int) session.getAttribute("id");
	        response.setContentType("text/html");  
	        PrintWriter out=response.getWriter();
	        out.println("<html><head>");
	        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
	        		"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
	        		"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
	        		"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script> <script type=\"text/javascript\">\r\n" + 
	        		"function edit(){\r\n" + 
	        		"alert('Task updated and saved Successfully');\r\n" + 
	        		"} \r\n" + 
	        		"</script>");
	        out.println("</head><body><div class='container'>");
	        out.println("<br/><br/><h1>Update task</h1>");  
	        int tid=Integer.parseInt(request.getParameter("tid"));   	          
	        
	        Task task=Dao.getTaskById(tid); 
	        
	        out.print("<form action='EditTask2?id="+id+"' method='post'>");
	        out.print("<table>"); 
	        out.print("<tr><td><input type='hidden' name='tid' value='"+task.getTid()+"'/></td></tr>");
	        out.print("<tr><td>Task Name:</td>");
	        out.print("<td><input type='text' name='tname' value='"+task.getTaskname()+"'/></td></tr>");
	        out.print("<tr><td>Discription:</td>");
	        out.print("<td><input type='text' name='disc' value='"+task.getDiscription()+"'/></td></tr>");
	        out.print("<tr><td colspan='2'><input type='submit' value='Update & Save' class='btn btn-info' onclick='edit()'></td></tr>");
	        out.print("</table>");  
	        out.print("</form></div>");  
	          
	    }  
}