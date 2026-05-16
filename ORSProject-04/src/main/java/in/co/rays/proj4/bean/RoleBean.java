package in.co.rays.proj4.bean;

/**
 * Bean class for Role entity.
 * 
 * This bean contains role related information.
 * 
 * @author Deepak Vishwakarma
 */
public class RoleBean extends BaseBean {

	public static final int ADMIN = 1;
	public static final int STUDENT = 2;
	public static final int COLLEGE = 3;
	public static final int FACULTY = 5;
	public static final int KIOSK = 4;

	private String name;
	private String description;

	/**
	 * Returns role name.
	 * 
	 * @return role name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets role name.
	 * 
	 * @param name role name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description.
	 * 
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns display value.
	 * 
	 * @return role name
	 */
	@Override
	public String getValue() {

		return name;
	}

}