package in.co.rays.proj4.bean;

/**
 * Interface for dropdown list beans.
 * 
 * This interface provides methods to get key and value for dropdown list
 * display.
 * 
 * @author Deepak Vishwakarma
 */
public interface DropdownListBean {

	/**
	 * Returns key value.
	 * 
	 * @return key
	 */
	public String getKey();

	/**
	 * Returns display value.
	 * 
	 * @return value
	 */
	public String getValue();

}