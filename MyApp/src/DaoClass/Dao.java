package DaoClass;

import java.util.*;
import BeanClass.Person;
import BeanClass.Task;
import java.sql.*;

public class Dao {
	static final String driver = "com.mysql.cj.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/to_do_application";
	static final String user = "root";
	static final String pass = "root";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		  /*finally 
		  { 
			  if (connection != null) 
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
		}*/
		 
		return connection;
	}

	public static int AddTask(Task task) {
		int status = 0;
		PreparedStatement preparedstatement=null;
		Connection connection=null;
		try {
			 connection = Dao.getConnection();
			preparedstatement = connection
					.prepareStatement("insert into task(tname,disc,id,date) values (?,?,?,?)");
			preparedstatement.setString(1, task.getTaskname());
			preparedstatement.setString(2, task.getDiscription());
			preparedstatement.setInt(3, task.getId());
			preparedstatement.setString(4, task.getDate());

			status = preparedstatement.executeUpdate();
			 
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
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
		return status;
	}

	public static int edit(Task task) {
		int status = 0;
		PreparedStatement preparedstatement=null;
		Connection connection=null;
		try {
			connection = Dao.getConnection();
			preparedstatement = connection
					.prepareStatement("update task set tname=?,disc=? where tid=?");

			preparedstatement.setString(1, task.getTaskname());
			preparedstatement.setString(2, task.getDiscription());
			preparedstatement.setInt(3, task.getTid());

			status = preparedstatement.executeUpdate();

			 } 
		catch (Exception e) {
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
		return status;
	}

	public static int editStatus(Task task) {
		int status = 0;
		PreparedStatement preparedstatement=null;
		Connection connection=null;
		try {
			connection = Dao.getConnection();
			preparedstatement = connection.prepareStatement("update task set status=? where tid=?");

			preparedstatement.setString(1, task.getStatus());
			preparedstatement.setInt(2, task.getTid());

			status = preparedstatement.executeUpdate();

		} catch (Exception e) {
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
		return status;
	}

	public static int activate(int id) {
		Person person = new Person();
		int status = 0;
		PreparedStatement preparedstatement=null;
		Connection connection=null;
		try {
			 connection = Dao.getConnection();
			 preparedstatement = connection.prepareStatement("update signup set activatedeactivate=? where id=?");
			 preparedstatement.setString(1, person.getActivatedeactivate());
			 preparedstatement.setInt(2, person.getId());

			 status = preparedstatement.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
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
		return status;
	}

	public static Task getTaskById(int tid) {
		Task task = new Task();
		PreparedStatement preparedstatement=null;
		Connection connection=null;
		ResultSet resultset=null;
		try {
			connection = Dao.getConnection();
		    preparedstatement = connection.prepareStatement("select * from task where tid=?");
			preparedstatement.setInt(1, tid);

			resultset = preparedstatement.executeQuery();
			if (resultset.next()) {
				task.setTid(resultset.getInt(1));
				task.setTaskname(resultset.getString(2));
				task.setDiscription(resultset.getString(3));
			}
			
		} catch (Exception e) {
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
		return task;
	}

	// list to get all users for admin

	public static List<Person> getAllUsers() {
		List<Person> list = new ArrayList<Person>();
		PreparedStatement preparedstatement=null;
		Connection connection=null;
		ResultSet resultset=null;
		try {
			connection = Dao.getConnection();
			preparedstatement = connection.prepareStatement("select * from signup");

			resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				Person person = new Person();
				person.setId(resultset.getInt(1));
				person.setFirstname(resultset.getString(2));
				person.setLastname(resultset.getString(3));
				person.setGender(resultset.getString(4));
				person.setDob(resultset.getString(5));
				person.setEmail(resultset.getString(6));
				person.setPassword(resultset.getString(7));
				person.setConfirmpassword(resultset.getString(8));
				person.setActivatedeactivate(resultset.getString(9));
				list.add(person);
			}
			
		} catch (Exception e) {
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
		return list;
	}

	// data insert in signup database
	public static int insertIntoSignUp(Person person) throws SQLException {
		String query = "insert into signup (firstname,lastname,gender,dob,email,password,confirmpassword,date)"
				+ "values (?,?,?,?,?,?,?,?)";
		int c = 0;
		PreparedStatement preparedstatement=null;
		Connection connection=null;
		try {
			connection = Dao.getConnection();
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1, person.getFirstname());
			preparedstatement.setString(2, person.getLastname());
			preparedstatement.setString(3, person.getGender());
			preparedstatement.setString(4, person.getDob());
			preparedstatement.setString(5, person.getEmail());
			preparedstatement.setString(6, person.getPassword());
			preparedstatement.setString(7, person.getConfirmpassword());
			preparedstatement.setString(8, person.getDate());

			c = preparedstatement.executeUpdate();

			return c;
		} catch (SQLIntegrityConstraintViolationException e) 
		{
			e.printStackTrace();
			return c;
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