package in.co.rays.proj4.bean;

/**
 * Bean class for Marksheet entity.
 * 
 * This bean contains marksheet related information.
 * 
 * @author Deepak Vishwakarma
 */
public class MarksheetBean extends BaseBean {

	private String rollNo;
	private long studentId;
	private String name;
	private int physics;
	private int chemistry;
	private int maths;

	/**
	 * Returns roll number.
	 * 
	 * @return roll number
	 */
	public String getRollNo() {
		return rollNo;
	}

	/**
	 * Sets roll number.
	 * 
	 * @param rollNo roll number
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/**
	 * Returns student id.
	 * 
	 * @return student id
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * Sets student id.
	 * 
	 * @param studentId student id
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * Returns student name.
	 * 
	 * @return student name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets student name.
	 * 
	 * @param name student name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns physics marks.
	 * 
	 * @return physics marks
	 */
	public int getPhysics() {
		return physics;
	}

	/**
	 * Sets physics marks.
	 * 
	 * @param physics physics marks
	 */
	public void setPhysics(int physics) {
		this.physics = physics;
	}

	/**
	 * Returns chemistry marks.
	 * 
	 * @return chemistry marks
	 */
	public int getChemistry() {
		return chemistry;
	}

	/**
	 * Sets chemistry marks.
	 * 
	 * @param chemistry chemistry marks
	 */
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	/**
	 * Returns maths marks.
	 * 
	 * @return maths marks
	 */
	public int getMaths() {
		return maths;
	}

	/**
	 * Sets maths marks.
	 * 
	 * @param maths maths marks
	 */
	public void setMaths(int maths) {
		this.maths = maths;
	}

	/**
	 * Returns display value.
	 * 
	 * @return display value
	 */
	@Override
	public String getValue() {
		return null;
	}

}