package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Model class for Subject operations.
 * 
 * This class handles all database operations related to Subject
 * such as add, update, delete, search, and find operations.
 *  @author Deepak Vishwakarma
 */
public class SubjectModel {

	/**
	 * Generates next primary key for st_subject table.
	 * 
	 * @return next primary key
	 * @throws DatabaseException if database exception occurs
	 */
	public Integer nextPk() throws DatabaseException {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_subject");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception in Subject getting PK");
		} finally {
			JDBCDataSource.getConnection();
		}
		return pk + 1;
	}

	/**
	 * Adds a new subject record into database.
	 * 
	 * @param bean SubjectBean containing subject details
	 * @return generated primary key
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if subject already exists
	 */
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());

		bean.setCourseName(courseBean.getName());

		SubjectBean beanExist = findByName(bean.getName());
		if (beanExist != null && beanExist.getId() !=bean.getId()) {
			throw new DuplicateRecordException("Subject Name already exists");
		}
		

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_subject values(?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCourseName());
			pstmt.setString(5, bean.getDescription());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			pstmt.executeUpdate();

			conn.commit();
			System.out.println("Subject added successfully...");
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
				throw new ApplicationException("add rollback Exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in adding Subject...");
		}

		return pk;
	}

	/**
	 * Updates existing subject record.
	 * 
	 * @param bean SubjectBean containing updated details
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if duplicate subject exists
	 */
	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());

		bean.setCourseName(courseBean.getName());
		
		
		SubjectBean beanExist = findByName(bean.getName());
		if (beanExist != null && beanExist.getId() !=bean.getId()) {
			throw new DuplicateRecordException("Subject Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_subject set name=?,course_id=?, course_Name=?, description = ?,created_by = ? , modified_by = ?, created_datetime=?, modified_datetime= ? where id= ?");

			pstmt.setString(1, bean.getName());
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			pstmt.executeUpdate();

			conn.commit();
			System.out.println("Subject updated successfully...");
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
				throw new ApplicationException("update rollback Exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updated Subject...");
		}

	}

	/**
	 * Deletes subject record from database.
	 * 
	 * @param bean SubjectBean containing subject id
	 * @throws ApplicationException if application error occurs
	 */
	public void delete(SubjectBean bean) throws ApplicationException {
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_subject where id= ?");

			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();

			conn.commit();
			System.out.println("Subject deleted successfully...");
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
				throw new ApplicationException("deleted rollback Exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in deleted Subject...");
		}

	}

	/**
	 * Finds subject by primary key.
	 * 
	 * @param pk subject primary key
	 * @return SubjectBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public SubjectBean findByPk(long pk) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_subject where id = ?");
		Connection conn = null;
		SubjectBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Subject by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	/**
	 * Finds subject by name.
	 * 
	 * @param name subject name
	 * @return SubjectBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public SubjectBean findByName(String name) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_subject where name = ?");
		Connection conn = null;
		SubjectBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Subject by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}
	
	/**
	 * Returns list of all subjects.
	 * 
	 * @return list of subjects
	 * @throws ApplicationException if application error occurs
	 */
	public List<SubjectBean> list() throws ApplicationException{
		return search(null,0,0);
	}

	/**
	 * Searches subject records based on criteria and pagination.
	 * 
	 * @param bean SubjectBean containing search criteria
	 * @param pageNo page number
	 * @param pageSize number of records per page
	 * @return list of matching subjects
	 * @throws ApplicationException if application error occurs
	 */
	public List<SubjectBean> search(SubjectBean bean, int pageNo, int pageSize) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_subject where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getCourseId() > 0) {
				sql.append(" and course_id = " + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" and course_name like '" + bean.getCourseName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}

		}

		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		ArrayList<SubjectBean> list = new ArrayList<SubjectBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}