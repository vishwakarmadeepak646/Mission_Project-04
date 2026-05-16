package in.co.rays.proj4.bean;

import java.sql.Timestamp;

/**
 * Abstract base bean class.
 * 
 * This class contains common properties shared by all beans
 * such as id, createdBy, modifiedBy, createdDatetime,
 * and modifiedDatetime.
 * @author Deepak Vishwakarma
 */
public abstract class BaseBean implements DropdownListBean {

	protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp createdDatetime;
	protected Timestamp modifiedDatetime;

	/**
	 * Returns bean id.
	 * 
	 * @return bean id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets bean id.
	 * 
	 * @param id bean id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns created by user.
	 * 
	 * @return created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets created by user.
	 * 
	 * @param createdBy created by user
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Returns modified by user.
	 * 
	 * @return modified by
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets modified by user.
	 * 
	 * @param modifiedBy modified by user
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Returns created datetime.
	 * 
	 * @return created datetime
	 */
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	/**
	 * Sets created datetime.
	 * 
	 * @param createdDatetime created datetime
	 */
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	/**
	 * Returns modified datetime.
	 * 
	 * @return modified datetime
	 */
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	/**
	 * Sets modified datetime.
	 * 
	 * @param modifiedDatetime modified datetime
	 */
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	/**
	 * Returns key value.
	 * 
	 * @return key value
	 */
	@Override
	public String getKey() {
		return id + "";
	}

}