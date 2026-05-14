package in.co.rays.proj4.Test;

import java.util.HashMap;

import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;

public class TestEmail {

	public static void main(String[] args) throws ApplicationException{
		testUserRegistrationMail();
	}

	private static void testUserRegistrationMail() throws ApplicationException {
		
		HashMap<String,String> map = new HashMap<String , String>();
		
		map.put("login", "vishwakarmadeepak646@gmail.com");
		map.put("password", "abc@123");
		
		String msg = EmailBuilder.getUserRegistrationMessage(map);  // This msg send when user register
		
		System.out.println(msg);
		
		EmailMessage message = new EmailMessage();
		
		message.setTo("vishwakarmadeepak646@gmail.com");
		message.setSubject("user registration successfull");
		message.setMessageType(EmailMessage.HTML_MSG);
		message.setMessage(msg);
		
		EmailUtility.sendMail(message);  // Used for sending mail to TO person.
		
		System.out.println("Message send successfully");
		
		
	}
	
	
}
