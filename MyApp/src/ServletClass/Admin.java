package ServletClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BeanClass.Person;
import DaoClass.Dao;

@WebServlet("/Admin")
public class Admin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	 {
			doPost(request,response);
	 }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
				"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
				"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");

		out.print("<html>");
		out.print("<body><div class='container'>");
		out.println("</br><a href='BarGraph' class='btn btn-info'>View Report</a>");
		out.println("</br></br><a href='Logout' class='btn btn-danger'>Logout</a>");
		out.println("<h1>Users</h1>");
		List<Person> list = Dao.getAllUsers();
		out.print("<table class='table table-bordered table-sm '>");
		out.print("<tr><th>First Name</th><th>Last Name</th><th>Status</th><th>Activate</th><th>Deactivate</th></tr>");
		for (Person p : list) 
		{
			out.print("<tr>"
					+ "<td>" + p.getFirstname() + "</td><td>" + p.getLastname() + "</td>"
					+ "<td>" + p.getActivatedeactivate() + "</td>"
					+ "<td> <a href='Activate?id="+p.getId()+"'   class='btn btn-info'>Activate</a></td>"
					+ "<td> <a href='Deactivate?id="+p.getId()+"' class='btn btn-warning'>Deactivate</a></td>"
					+ "</tr>");
		}
		out.print("</div>");
		out.print("</table>");
		out.print("</body>");
		out.print("</html>");
		out.close();
	}
}