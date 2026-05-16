package in.co.rays.proj4.exception;

/**
 * DatabaseException is a custom exception class used to handle database-related errors.
 * It is typically thrown by the model layer when operations like connecting to the database,
 * executing queries, or fetching results fail (e.g., wrapping standard SQLExceptions).
*  @author Deepak Vishwakarma
 */
public class DatabaseException extends Exception {

	/**
	 * Constructs a DatabaseException with the specified detailed error message.
	 * * @param msg the detailed error message explaining the database failure
	 */
	public DatabaseException(String msg) {
		super(msg);
	}
}