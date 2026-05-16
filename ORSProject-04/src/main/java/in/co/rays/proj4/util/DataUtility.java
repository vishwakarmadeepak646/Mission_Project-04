package in.co.rays.proj4.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DataUtility is a utility class used to format and convert data types. 
 * It handles string manipulation, numeric conversions, and date/time formatting.
*  @author Deepak Vishwakarma
 */
public class DataUtility {

	/** Default application date format */
	public static final String APP_DATE_FORMAT = "yyyy-MM-dd";

	/** Default application time format */
	public static final String APP_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

	/** Date formatter instance */
	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);

	/** Time formatter instance */
	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(APP_TIME_FORMAT);

	/**
	 * Trims leading and trailing spaces from a string.
	 * * @param val the string to format
	 * @return trimmed string, or the original null value
	 */
	public static String getString(String val) {
		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return val;
		}
	}

	/**
	 * Converts an Object to a String.
	 * * @param val the object to convert
	 * @return string representation of the object, or empty string if null
	 */
	public static String getStringData(Object val) {
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	/**
	 * Converts a String to an integer.
	 * * @param val the string value
	 * @return integer value, or 0 if conversion fails
	 */
	public static int getInt(String val) {
		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}

	/**
	 * Converts a String to a long.
	 * * @param val the string value
	 * @return long value, or 0 if conversion fails
	 */
	public static long getLong(String val) {
		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}

	/**
	 * Converts a String to a Date object using the application format.
	 * * @param val the date string
	 * @return Date object, or null if parsing fails
	 */
	public static Date getDate(String val) {
		Date date = null;
		try {
			date = formatter.parse(val);
		} catch (Exception e) {

		}
		return date;
	}

	/**
	 * Converts a Date object to a String using the application format.
	 * * @param date the Date object
	 * @return formatted date string, or empty string if formatting fails
	 */
	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * Converts a time string to a Timestamp object.
	 * * @param val the time string
	 * @return Timestamp object, or null if parsing fails
	 */
	public static Timestamp getTimestamp(String val) {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp((timeFormatter.parse(val)).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	/**
	 * Converts a time representation in milliseconds to a Timestamp.
	 * * @param l time in milliseconds
	 * @return Timestamp object
	 */
	public static Timestamp getTimestamp(long l) {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	/**
	 * Retrieves the current system Timestamp.
	 * * @return current Timestamp
	 */
	public static Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
		}
		return timeStamp;

	}

	/**
	 * Extracts the time in milliseconds from a Timestamp object.
	 * * @param tm the Timestamp object
	 * @return time in milliseconds, or 0 if extraction fails
	 */
	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Main method to test DataUtility methods.
	 * * @param args command line arguments
	 */
	public static void main(String[] args) {
		// Test getString
		System.out.println("getString Test:");
		System.out.println("Original: '  Hello World  ' -> Trimmed: '" + getString("  Hello World  ") + "'");
		System.out.println("Null input: " + getString(null));

		// Test getStringData
		System.out.println("\ngetStringData Test:");
		System.out.println("Object to String: " + getStringData(1234));
		System.out.println("Null Object: '" + getStringData(null) + "'");

		// Test getInt
		System.out.println("\ngetInt Test:");
		System.out.println("Valid Integer String: '124' -> " + getInt("124"));
		System.out.println("Invalid Integer String: 'abc' -> " + getInt("abc"));
		System.out.println("Null String: -> " + getInt(null));

		// Test getLong
		System.out.println("\ngetLong Test:");
		System.out.println("Valid Long String: '123456789' -> " + getLong("123456789"));
		System.out.println("Invalid Long String: 'abc' -> " + getLong("abc"));

		// Test getDate
		System.out.println("\ngetDate Test:");
		String dateStr = "10-15-2024";
		Date date = getDate(dateStr);
		System.out.println("String to Date: '" + dateStr + "' -> " + date);

		// Test getDateString
		System.out.println("\ngetDateString Test:");
		System.out.println("Date to String: '" + getDateString(new Date()) + "'");

		// Test getTimestamp (String)
		System.out.println("\ngetTimestamp(String) Test:");
		String timestampStr = "10/15/2024 10:30:45";
		Timestamp timestamp = getTimestamp(timestampStr);
		System.out.println("String to Timestamp: '" + timestampStr + "' -> " + timestamp);

		// Test getTimestamp (long)
		System.out.println("\ngetTimestamp(long) Test:");
		long currentTimeMillis = System.currentTimeMillis();
		Timestamp ts = getTimestamp(currentTimeMillis);
		System.out.println("Current Time Millis to Timestamp: '" + currentTimeMillis + "' -> " + ts);

		// Test getCurrentTimestamp
		System.out.println("\ngetCurrentTimestamp Test:");
		Timestamp currentTimestamp = getCurrentTimestamp();
		System.out.println("Current Timestamp: " + currentTimestamp);

		// Test getTimestamp(Timestamp)
		System.out.println("\ngetTimestamp(Timestamp) Test:");
		System.out.println("Timestamp to long: " + getTimestamp(currentTimestamp));
	}
}