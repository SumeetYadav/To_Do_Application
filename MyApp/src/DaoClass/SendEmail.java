package DaoClass;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import BeanClass.Person;

public class SendEmail 
{
	// generate verification code of 6digit 
	public static String getRandomNumber() 
	{
		Random rnd = new Random();
		int number = rnd.nextInt(999999);  //6 digit so 999999 used
		return String.format("%06d", number); //to generate 6digit code
	}
	
	// send email to the user email
	public static boolean sendEmail(Person person) 
	{
		boolean sent = false;
		
		String toEmail = person.getEmail();            //email reciever
		String fromEmail = "abc@gmail.com";   // your email email sender
		String password = "12345";  //your password

		try {
	        Properties properties = new Properties();
			properties.setProperty("mail.smtp.host", "smtp.gmail.com");
			properties.setProperty("mail.smtp.port", "465");
			properties.setProperty("mail.smtp.auth", "true");
			properties.setProperty("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			
			
			// get session to authenticate the host email address and password
			Session session = Session.getInstance(properties, new Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(fromEmail, password);
				}
			});

			// set email message details
			MimeMessage message = new MimeMessage(session);

			// set from email address
			message.setFrom(new InternetAddress(fromEmail));
			// set to email address or destination email address
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			// set email subject
			message.setSubject("User Email Verification");

			// set message text
			message.setText("Registered successfully.Please verify your account using this code: " + person.getCode());
			// send the message
			Transport.send(message);

			sent = true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return sent;
	}
}