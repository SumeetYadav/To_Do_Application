package ServletClass;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DaoClass.Dao;

@WebServlet("/BarGraph")
public class BarGraph extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public BarGraph() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
		int UserCount=0,TaskCount=0,ActiveCount=0;
		String UserQuery="select count(date) as UserTotal from signup WHERE date>= DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
		String TaskQuery="select count(date) as TaskTotal from task WHERE date>= DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
		String ActiveQuery="select count(*) as ActiveTotal from signup where activatedeactivate='Active'";
		Connection connection=null;
		connection=Dao.getConnection();
		ResultSet UserResult=null;
		ResultSet TaskResult=null;
		ResultSet ActiveResult=null;
		Statement UserStatement=null;
		Statement TaskStatement=null;
		Statement ActiveStatement=null;
		try
		{
			UserStatement=connection.createStatement();
		    TaskStatement=connection.createStatement();
		    ActiveStatement=connection.createStatement();
		    
		    UserResult=UserStatement.executeQuery(UserQuery); 
			while(UserResult.next())
			{
				UserCount=UserResult.getInt(1);			
			}
			
			TaskResult=TaskStatement.executeQuery(TaskQuery); 
			while(TaskResult.next())
			{
				TaskCount=TaskResult.getInt(1);
			}
			ActiveResult=ActiveStatement.executeQuery(ActiveQuery);
			while(ActiveResult.next())
			{
				ActiveCount=ActiveResult.getInt(1);
			}
			
		}
		 catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
		finally
		{
			if(ActiveStatement != null)
			{
				try	
				{
					ActiveStatement.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(TaskStatement != null)
			{
				try	
				{
					TaskStatement.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(UserStatement != null)
			{
				try
				{
					UserStatement.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(ActiveResult != null)
			{
				try
				{
					ActiveResult.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(TaskResult != null)
			{
				try
				{
					TaskResult.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(UserResult != null)
			{
				try
				{
					UserResult.close();
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
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
				"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
				"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");

		out.print("<body><div class='container'>");
		out.println("</br></br><a href='Logout' class='btn btn-danger'>Logout</a><br/><br/>");
		out.println("<a href='Admin' class='btn btn-info'>Back</a>");
		out.print("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<title>BarGraph</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"BarGraph.css\">\r\n" + 
				"	  <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<script type=\"text/javascript\">\r\n" + 
				"		google.charts.load('current', {packages: ['corechart', 'bar']});\r\n" + 
				"google.charts.setOnLoadCallback(drawBasic);\r\n" + 
				"\r\n" + 
				"function drawBasic() \r\n" + 
				"{\r\n" + 
				"        var data = google.visualization.arrayToDataTable\r\n" + 
				"        ([\r\n" + 
				"         ['Element', 'Users data in last seven days', { role: 'style' }],\r\n" + 
				"         ['New Users',"+UserCount+", '#b87333'],      // RGB value\r\n" + 
				"         ['Active Users',"+ActiveCount+", 'silver'],            // English color name\r\n" + 
				"         ['Total Tasks',"+TaskCount+", 'gold'], // CSS-style declaration\r\n" + 
				"         ]);\r\n" + 
				"      var options = {\r\n" + 
				"        title: 'Report',\r\n" + 
				"        chartArea:{width :'30%'},\r\n" +  
				"        hAxis: \r\n" + 
				"        {\r\n" + 
				"          title: 'User names',\r\n" + 
				"        },\r\n" + 
				"        vAxis: \r\n" + 
				"        {\r\n" + 
				"          title: ' No of Users '\r\n" + 
				"        }\r\n" + 
				"      };\r\n" + 
				"\r\n" + 
				"      var chart = new google.visualization.ColumnChart(\r\n" + 
				"        document.getElementById('chart_div'));\r\n" + 
				"\r\n" + 
				"      chart.draw(data, options);\r\n" + 
				"    }\r\n" + 
				"	</script>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<div id=\"chart_div\" class=\"chart\"></div>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	}
}