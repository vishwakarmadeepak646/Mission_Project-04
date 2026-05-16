package in.co.rays.proj4.bean;

import java.util.Date;

/**
 * Bean class for Student entity.
 * 
 * This bean contains student related information.
 * 
 * @author Deepak Vishwakarma
 */
public class StudentBean extends BaseBean {

	private String firstName;
	private String lastName;
	private Date dob;
	private String gender;
	private String mobileNo;
	private String email;
	private long collegeId;
	private String collegeName;

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
	 * Returns email.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 * 
	 * @param email email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns college id.
	 * 
	 * @return college id
	 */
	public long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets college id.
	 * 
	 * @param collegeId college id
	 */
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Returns college name.
	 * 
	 * @return college name
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets college name.
	 * 
	 * @param collegeName college name
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * Returns display value.
	 * 
	 * @return student full name
	 */
	@Override
	public String getValue() {

		return firstName + " " + lastName;
	}
}