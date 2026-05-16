package in.co.rays.proj4.bean;

import java.util.Date;

/**
 * Bean class for Timetable entity.
 * 
 * This bean contains timetable related information.
 * 
 * @author Deepak Vishwakarma
 */
public class TimetableBean extends BaseBean {

	private String semester;
	private String description;
	private Date examDate;
	private String examTime;
	private long courseId;
	private String courseName;
	private long subjectId;
	private String subjectName;

	/**
	 * Returns semester.
	 * 
	 * @return semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * Sets semester.
	 * 
	 * @param semester semester
	 */
	public void setSemester(String semester) {
		this.semester = semester;
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
	 * Returns exam date.
	 * 
	 * @return exam date
	 */
	public Date getExamDate() {
		return examDate;
	}

	/**
	 * Sets exam date.
	 * 
	 * @param examDate exam date
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	/**
	 * Returns exam time.
	 * 
	 * @return exam time
	 */
	public String getExamTime() {
		return examTime;
	}

	/**
	 * Sets exam time.
	 * 
	 * @param examTime exam time
	 */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
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
	 * Returns subject id.
	 * 
	 * @return subject id
	 */
	public long getSubjectId() {
		return subjectId;
	}

	/**
	 * Sets subject id.
	 * 
	 * @param subjectId subject id
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Returns subject name.
	 * 
	 * @return subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * Sets subject name.
	 * 
	 * @param subjectName subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * Returns display value.
	 * 
	 * @return exam date as string
	 */
	@Override
	public String getValue() {
		return examDate + "";
	}

}