package in.co.rays.proj4.util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.rays.proj4.bean.DropdownListBean;
import in.co.rays.proj4.model.RoleModel;

/**
 * HTMLUtility is a utility class used to construct HTML UI components 
 * (like drop-down lists) dynamically from backend data sources like Maps and Lists.
*  @author Deepak Vishwakarma
 */
public class HTMLUtility {

	/**
	 * Generates an HTML Select drop-down menu based on a provided HashMap.
	 * * @param name the HTML "name" attribute for the select tag
	 * @param selectedVal the value that should be pre-selected
	 * @param map the HashMap containing key-value pairs for the options
	 * @return a string containing the complete HTML select element
	 */
	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select style=\"width: 169px;text-align-last: center;\"; class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		Set<String> keys = map.keySet();
		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Generates an HTML Select drop-down menu based on a provided List of beans 
	 * implementing the DropdownListBean interface.
	 * * @param name the HTML "name" attribute for the select tag
	 * @param selectedVal the value that should be pre-selected
	 * @param list the list of DropdownListBean implementations
	 * @return a string containing the complete HTML select element
	 */
	public static String getList(String name, String selectedVal, List list) {

		// Collections.sort(list);

		List<DropdownListBean> dd = (List<DropdownListBean>) list;

		StringBuffer sb = new StringBuffer("<select style=\"width: 169px;text-align-last: center;\"; "
				+ "class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		String key = null;
		String val = null;

		for (DropdownListBean obj : dd) {
			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Test method to generate and print a list populated by a Map.
	 */
	public static void testGetListByMap() {

		HashMap<String, String> map = new HashMap<>();
		map.put("male", "male");
		map.put("female", "female");

		String selectedValue = null;
		String htmlSelectFromMap = HTMLUtility.getList("gender", selectedValue, map);

		System.out.println(htmlSelectFromMap);
	}

	/**
	 * Test method to generate and print a list populated by a List from a database model.
	 * * @throws Exception if model extraction fails
	 */
	public static void testGetListByList() throws Exception {

		RoleModel model = new RoleModel();

		// UserModel model = new UserModel();

		List list = model.list();

		String selectedValue = "1";

		String htmlSelectFromList = HTMLUtility.getList("role", selectedValue, list);

		System.out.println(htmlSelectFromList);
	}

	/**
	 * Main method to trigger HTMLUtility tests.
	 * * @param args command line arguments
	 * @throws Exception if an error occurs during execution
	 */
	public static void main(String[] args) throws Exception {

		// testGetListByMap();

		testGetListByList();

	}
}