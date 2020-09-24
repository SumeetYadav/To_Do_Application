package ServletClass;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddNewTask")
public class AddNewTask extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public AddNewTask()
    {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		HttpSession session=request.getSession();
		session.setAttribute("id", id);
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
       
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
        		"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
        		"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
        		"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
        out.println("<title>Add Task</title><script type=\"text/javascript\"> \r\n" + 
        		"function showAlert(){ \r\n" + 
        		"alert('Task added Successfully'); \r\n" + 
        		"} \r\n" + 
        		"</script>");
        out.println("</head>");
        out.println("<body><div class='container'>");
        out.println("<form action='AddTask?id="+id+"' method='post'>");
        out.println("<table><tr>");
        out.println("<br/><br/><td>Task Name:</td>");
        out.println("<td><input type='text' name='tname' placeholder='Enter Taskname'></td> </tr>");
        out.println("<tr><td>Discription:</td><td>");
        out.println("<br/><input type='text' name='disc' placeholder='Enter Task Discription'></td> </tr>");
        out.println("</table>");
        out.println("<br/><br/><input type='submit' name='submit' class='btn btn-warning' onclick='showAlert()' value='Add new task'>");
        out.println("<a href='ViewAllTask?id="+id+"' class='btn btn-info'>View all tasks</a>");
        out.println("<a href='Logout' class='btn btn-danger'>Logout</a><br/>");
        out.println("</div></form></body></html>");
	}
}