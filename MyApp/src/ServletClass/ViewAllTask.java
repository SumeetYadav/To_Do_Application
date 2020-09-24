package ServletClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BeanClass.Task;
import DaoClass.Dao;

@WebServlet("/ViewAllTask")

public class ViewAllTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		PreparedStatement preparedstatement=null;
		Connection connection=null;
		ResultSet resultset=null;
		connection= Dao.getConnection();
		ArrayList<Task> List = new ArrayList<>();

		String query = "select * from task where id=?";
		try {
		    preparedstatement = connection.prepareStatement(query);
		    preparedstatement.setInt(1, id);

			resultset = preparedstatement.executeQuery();
			while (resultset.next()) {
				Task task = new Task();
				task.setTid(resultset.getInt(1));
				task.setTaskname(resultset.getString(2));
				task.setDiscription(resultset.getString(3));
				task.setId(resultset.getInt(4));
				task.setStatus(resultset.getString(5));
				List.add(task);
				
			}

			out.println("<html><head>");
			out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n"
					+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
			out.println("<title>View All Task</title><script <script type=\"text/javascript\">\r\n" + 
					"function showAlert(){\r\n" + 
					"alert('Task deleted Successfully');\r\n" + 
					"} \r\n" + 
					"</script>");
			out.println("</head><body><div class='container'>");
			out.println("<br/><br/><a href='AddNewTask?id=" + id + "' class='btn btn-info'>Add new task</a>");
			out.println("</br></br><a href='Logout' class='btn btn-danger'>Logout</a>");
			out.println("<h1>Task List</h1>");

			out.print("<table class='table table-bordered table-sm '>");
			out.print(
					"<tr><th></th><th>Task Name</th><th>Discription</th><th>Status</th><th>Edit</th><th>Delete</th></tr>");
		
			for (Task task : List) {
				out.print("<td><input type='checkbox' onchange='self.location.href=\"ChangeStatus?tid=" + task.getTid()
						+ "\"' name='ckbox'{checkstat==1?disabled:}></td>");

				out.print("<td>" + task.getTaskname() + "</td>");
				out.print("<td>" + task.getDiscription() + "</td>");
				out.print("<td>" + task.getStatus() + "</td>");
				out.print("<td><a href='EditTask?tid=" + task.getTid() + "'class='btn btn-info' >Edit</a></td>");
				out.print("<td><a href='DeleteTask?tid=" + task.getTid() + "'class='btn btn-danger' onclick='showAlert()' >Delete</a></td>");
				out.print("</tr>");
			}
			out.close();
		} 
		catch (SQLException e)
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
	}
}