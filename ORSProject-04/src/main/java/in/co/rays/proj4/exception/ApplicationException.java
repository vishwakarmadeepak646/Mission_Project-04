package in.co.rays.proj4.exception;

/**
 * ApplicationException is a custom exception class used to handle generic, 
 * application-level errors. It is typically thrown when a business logic 
 * failure or an unexpected system error occurs that needs to be caught 
 * and handled gracefully by the controllers.
*  @author Deepak Vishwakarma
 */
public class ApplicationException extends Exception {

	/**
	 * Constructs an ApplicationException with the specified detailed error message.
	 * * @param msg the detailed error message explaining the cause of the exception
	 */
	public ApplicationException(String msg) {
		super(msg);
	}
}