package in.co.rays.proj4.bean;

/**
 * Bean class for College entity.
 * 
 * This bean contains college related information.
 * 
 * @author Deepak Vishwakarma
 */
public class CollegeBean extends BaseBean {

	private String name;
	private String address;
	private String state;
	private String city;
	private String phoneNo;

	/**
	 * Returns college name.
	 * 
	 * @return college name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets college name.
	 * 
	 * @param name college name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns address.
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets address.
	 * 
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns state.
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets state.
	 * 
	 * @param state state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns city.
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets city.
	 * 
	 * @param city city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Returns phone number.
	 * 
	 * @return phone number
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets phone number.
	 * 
	 * @param phoneNo phone number
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Returns display value.
	 * 
	 * @return college name
	 */
	@Override
	public String getValue() {

		return name;
	}

}