package in.co.rays.proj4.bean;

/**
 * Bean class for Course entity.
 * 
 * This bean contains course related information.
 * 
 * @author Deepak Vishwakarma
 */
public class CourseBean extends BaseBean {

	private String name;
	private String duration;
	private String description;

	/**
	 * Returns course name.
	 * 
	 * @return course name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets course name.
	 * 
	 * @param name course name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns course duration.
	 * 
	 * @return course duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets course duration.
	 * 
	 * @param duration course duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Returns course description.
	 * 
	 * @return course description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets course description.
	 * 
	 * @param description course description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns display value.
	 * 
	 * @return course name
	 */
	@Override
	public String getValue() {

		return name;
	}

}