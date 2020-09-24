<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*,java.util.*"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/to_do_application",
					"root", "root");
			Statement statement = connection.createStatement();

			ResultSet resultset = statement
					.executeQuery("select * from signup where email='" + email + "'and password='" + password + "' ");
			out.println("<div class='container'>");

			try {

				if (resultset.next()) 
				{
					if (("1234").equals(resultset.getString("password"))
							&& ("admin@gmail.com").equals(resultset.getString("email"))) 
					{
						out.println("<script type='text/javascript'>");
						out.println("alert('Login Successfully as Admin');");
						out.println("</script>");
						out.println("<br/><br/><h1>Welcome Admin</h1><br/><br/>"); //need to redirect to admin page
						out.println("<a href='Admin' class='btn btn-warning' >View all users</a>");
						out.println("<a href='BarGraph' class='btn btn-info'>View Report</a>");
					}

					else if (password.equals(resultset.getString("password")) && email.equals(resultset.getString("email"))
							&& ("ACTIVE").equals(resultset.getString("activatedeactivate"))) 
					{
						out.println("<script type='text/javascript'>");
						out.println("alert('Login Successfully as User');");
						out.println("</script>");
						String name = resultset.getString("firstname");
						out.println("<br/><br/><h1>Welcome " + name + "</h1>");
						out.println("<br/><a href='AddNewTask?id=" + resultset.getInt("id")
								+ "' class='btn btn-info'>Add new task</a>");
						out.println("<a href='ViewAllTask?id=" + resultset.getInt("id")
								+ "' class='btn btn-info'>View all tasks</a><br/><br/>");
						out.println("<a href='Logout' class='btn btn-danger'>Logout</a>");
					}

					else if (password.equals(resultset.getString("password")) && email.equals(resultset.getString("email"))
							&& ("INACTIVE").equals(resultset.getString("activatedeactivate"))) 
					{
						out.println("<script type='text/javascript'>");
						out.println("alert('Sorry it looks like you are deactivated by admin.');");
						out.println("</script>");
						out.println("<br/><br/><a href='index.html' class='btn btn-info'>Back</a>");
					}

				} 
				else 
				{
					out.println("<script type='text/javascript'>");
					out.println("alert('Invalid user input credentials');");
					out.println("alert('Try to login again');");
					out.println("</script>");
					out.println("<br/><br/><a href='index.html' class='btn btn-info'>Back</a>");
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		%>
	</div>
</body>
</html>