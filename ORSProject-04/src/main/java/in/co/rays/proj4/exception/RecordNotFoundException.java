package in.co.rays.proj4.exception;

/**
 * RecordNotFoundException is a custom exception class used to indicate that 
 * a specifically requested record could not be found in the database. 
 * This is often thrown during search, retrieval, or authentication operations 
 * where a valid result is expected but not returned.
*  @author Deepak Vishwakarma
 */
public class RecordNotFoundException extends Exception {

	/**
	 * Constructs a RecordNotFoundException with the specified detailed error message.
	 * * @param msg the detailed error message explaining what record was not found
	 */
	public RecordNotFoundException(String msg) {
		super(msg);
	}
}