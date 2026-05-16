package in.co.rays.proj4.util;

/**
 * EmailMessage is a Data Transfer Object (DTO) class that encapsulates 
 * the details needed to send an email.
*  @author Deepak Vishwakarma
 */
public class EmailMessage {

	/** The recipient's email address */
	private String to;
	/** The subject line of the email */
	private String subject;
	/** The body content of the email */
	private String message;
	/** The format of the email (HTML or TEXT), default is TEXT_MSG */
	private int messageType = TEXT_MSG;

	/** Constant for HTML formatted messages */
	public static final int HTML_MSG = 1;
	/** Constant for plain text formatted messages */
	public static final int TEXT_MSG = 2;

	/**
	 * Default constructor.
	 */
	public EmailMessage() {
	}

	/**
	 * Parameterized constructor to initialize the email details.
	 * * @param to recipient address
	 * @param subject email subject
	 * @param message email body content
	 */
	public EmailMessage(String to, String subject, String message) {
		this.to = to;
		this.subject = subject;
		this.message = message;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTo() {
		return to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getMessageType() {
		return messageType;
	}
}