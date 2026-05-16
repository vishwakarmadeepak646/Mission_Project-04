package in.co.rays.proj4.exception;

/**
 * DuplicateRecordException is a custom exception class used to indicate that 
 * a user is attempting to add or update a record with data that already exists 
 * in the system (e.g., trying to register with an email address or roll number 
 * that is already in use).
*  @author Deepak Vishwakarma
 */
public class DuplicateRecordException extends Exception {

	/**
	 * Constructs a DuplicateRecordException with the specified detailed error message.
	 * * @param msg the detailed error message explaining which record is duplicated
	 */
	public DuplicateRecordException(String msg) {
		super(msg);
	}
}