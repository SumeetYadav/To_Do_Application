package BeanClass;

public class Person 
{
	private int id;
	private String firstname;
	private String lastname;
	private String gender;
	private String dob;
	private String email;
	private String password;
	private String confirmpassword;
	private String activatedeactivate;
	private String code;
	private String date;
	
	public Person(String firstname, String lastname, String gender, String dob, String email, String password,
			String confirmpassword,String date) 
	{
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.date=date;
	}
	
	public Person(String email, String code)
	{
		super();
		this.email = email;
		this.code = code;
	}

	public Person() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getActivatedeactivate() {
		return activatedeactivate;
	}
	public void setActivatedeactivate(String activatedeactivate) {
		this.activatedeactivate = activatedeactivate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}