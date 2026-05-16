package in.co.rays.proj4.util;

import java.util.HashMap;

/**
 * EmailBuilder is a utility class used to generate structured HTML email 
 * message templates for various application events.
*  @author Deepak Vishwakarma
 */
public class EmailBuilder {

	/**
	 * Builds an HTML email message for new user registration.
	 * * @param map a hashmap containing 'login' and 'password' keys
	 * @return formatted HTML string for the email body
	 */
	public static String getUserRegistrationMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Welcome to ORS, ").append(map.get("login")).append("!</H1>");
		msg.append("<P>Your registration is successful. You can now log in and manage your account.</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("<P>Change your password after logging in for security reasons.</P>");
		msg.append("<P>For support, contact +91 98273 60504 or hrd@sunrays.co.in.</P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}

	/**
	 * Builds an HTML email message for password recovery.
	 * * @param map a hashmap containing user details and credentials
	 * @return formatted HTML string for the email body
	 */
	public static String getForgetPasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Password Recovery</H1>");
		msg.append("<P>Hello, ").append(map.get("firstName")).append(" ").append(map.get("lastName")).append(".</P>");
		msg.append("<P>Your login details are:</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}

	/**
	 * Builds an HTML email message confirming a password change.
	 * * @param map a hashmap containing user details and the new password
	 * @return formatted HTML string for the email body
	 */
	public static String getChangePasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Password Changed Successfully</H1>");
		msg.append("<P>Dear ").append(map.get("firstName")).append(" ").append(map.get("lastName"))
				.append(", your password has been updated.</P>");
		msg.append("<P>Your updated login details are:</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>New Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}
}