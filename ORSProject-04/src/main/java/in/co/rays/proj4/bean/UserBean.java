package in.co.rays.proj4.bean;

import java.util.Date;

/**
 * Bean class for User entity.
 * 
 * This bean contains user related information.
 * 
 * @author Deepak Vishwakarma
 */
public class UserBean extends BaseBean {

	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private String confirmPassword;
	private String gender;
	private Date dob;
	private String mobileNo;
	private long roleId;
	private String roleName;

	/**
	 * Returns first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name.
	 * 
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name.
	 * 
	 * @param lastName last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns login id.
	 * 
	 * @return login id
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets login id.
	 * 
	 * @param login login id
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Returns password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 * 
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns confirm password.
	 * 
	 * @return confirm password
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets confirm password.
	 * 
	 * @param confirmPassword confirm password
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * Returns gender.
	 * 
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets gender.
	 * 
	 * @param gender gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns date of birth.
	 * 
	 * @return date of birth
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets date of birth.
	 * 
	 * @param dob date of birth
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Returns mobile number.
	 * 
	 * @return mobile number
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets mobile number.
	 * 
	 * @param mobileNo mobile number
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Returns role id.
	 * 
	 * @return role id
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * Sets role id.
	 * 
	 * @param roleId role id
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Returns role name.
	 * 
	 * @return role name
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets role name.
	 * 
	 * @param roleName role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Returns display value.
	 * 
	 * @return user full name
	 */
	@Override
	public String getValue() {

		return firstName + " " + lastName;
	}

}