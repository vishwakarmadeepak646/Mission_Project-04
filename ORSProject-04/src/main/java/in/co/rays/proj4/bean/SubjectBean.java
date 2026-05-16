package in.co.rays.proj4.bean;

/**
 * Bean class for Subject entity.
 * 
 * This bean contains subject related information.
 * 
 * @author Deepak Vishwakarma
 */
public class SubjectBean extends BaseBean {

	private String name;
	private long courseId;
	private String courseName;
	private String description;

	/**
	 * Returns subject name.
	 * 
	 * @return subject name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets subject name.
	 * 
	 * @param name subject name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns course id.
	 * 
	 * @return course id
	 */
	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets course id.
	 * 
	 * @param courseId course id
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Returns course name.
	 * 
	 * @return course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets course name.
	 * 
	 * @param courseName course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
	 * @return subject name
	 */
	@Override
	public String getValue() {
		return name;
	}

}