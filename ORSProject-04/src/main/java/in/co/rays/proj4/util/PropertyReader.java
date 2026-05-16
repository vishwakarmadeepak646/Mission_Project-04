package in.co.rays.proj4.util;

import java.util.ResourceBundle;

/**
 * PropertyReader is a utility class used to extract system properties and string values 
 * from the standard resource bundle (.properties file).
*  @author Deepak Vishwakarma
 */
public class PropertyReader {

	/** ResourceBundle initialized to read the system properties file */
	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.pro"
			+ "j4.bundle.system");

	/**
	 * Returns the value associated with the specified key in the properties file.
	 * * @param key the property key to look up
	 * @return the property value, or the key string itself if not found
	 */
	public static String getValue(String key) {

		String val = null;

		try {
			val = rb.getString(key); // {0} is required
		} catch (Exception e) {
			val = key;
		}
		return val;
	}

	/**
	 * Retrieves a parameterized property message and replaces the first placeholder {0} 
	 * with the given dynamic parameter.
	 * * @param key the property key
	 * @param param the dynamic string to replace {0}
	 * @return formatted string
	 */
	public static String getValue(String key, String param) {
		String msg = getValue(key); // {0} is required
		msg = msg.replace("{0}", param);
		return msg;
	}

	/**
	 * Retrieves a parameterized property message and replaces multiple placeholders 
	 * ({0}, {1}, etc.) with the provided array of parameters.
	 * * @param key the property key
	 * @param params array of strings to replace the placeholders sequentially
	 * @return formatted string
	 */
	public static String getValue(String key, String[] params) {
		String msg = getValue(key); // {0} and {1} are required.
		for (int i = 0; i < params.length; i++) {
			msg = msg.replace("{" + i + "}", params[i]);
		}
		return msg;
	}

	/**
	 * Main method to test PropertyReader functionality.
	 * * @param args command line arguments
	 */
	public static void main(String[] args) {

		System.out.println("Single key example:");
		System.out.println(PropertyReader.getValue("error.require"));

		System.out.println("\nSingle parameter replacement example:");
		System.out.println(PropertyReader.getValue("error.require", "loginId"));

		System.out.println("\nMultiple parameter replacement example:");
		String[] params = { "Roll No", "Student Name" };
		System.out.println(PropertyReader.getValue("error.multipleFields", params));
	}
}