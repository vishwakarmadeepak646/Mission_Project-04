package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mchange.util.DuplicateElementException;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Model class for Course operations.
 * 
 * This class handles all database operations related to Course
 * such as add, update, delete, search, and find operations.
 *  @author Deepak Vishwakarma
 */
public class CourseModel {

	/**
	 * Generates next primary key for st_course table.
	 * 
	 * @return next primary key
	 * @throws DatabaseException if database exception occurs
	 */
	public Integer nextPk() throws DatabaseException {
		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_course");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}

	/**
	 * Adds new course record into database.
	 * 
	 * @param bean CourseBean containing course details
	 * @return generated primary key
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if duplicate course exists
	 */
	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		StringBuffer sql = new StringBuffer("insert into st_course values(?,?,?,?,?,?,?,?)");
		Connection conn = null;
		int pk = 0;

		CourseBean beanExist = findByName(bean.getName());

		if (beanExist != null && beanExist.getId() !=bean.getId()) {
			throw new DuplicateRecordException("Course name already exists");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.executeUpdate();

			conn.commit();
			System.out.println("Record added successfully");
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception in adding Course...");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	/**
	 * Updates existing course record.
	 * 
	 * @param bean CourseBean containing updated details
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if duplicate course exists
	 */
	public void update(CourseBean bean) throws ApplicationException , DuplicateRecordException{
		StringBuffer sql = new StringBuffer(
				"update st_course set name=?, duration = ?, description = ?, created_by =?, modified_by=?, created_datetime= ? , modified_datetime=? where id= ?");

		Connection conn = null;
		
		CourseBean beanExist = findByName(bean.getName());

		if (beanExist != null && beanExist.getId() !=bean.getId()) {
			throw new DuplicateRecordException("Course name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record updated successfully");

			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();

				throw new ApplicationException("Exception : add rollback exception" + ex.getMessage());
			}
			e.printStackTrace();
			throw new ApplicationException("Exception in adding Course...");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Deletes course record from database.
	 * 
	 * @param bean CourseBean containing course id
	 * @throws ApplicationException if application error occurs
	 */
	public void delete(CourseBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_course where id = ?");

			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record deleted successfully");
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();

				throw new ApplicationException("Exception : add rollback exception" + ex.getMessage());
			}
			e.printStackTrace();
			throw new ApplicationException("Exception in adding Course...");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Finds course by primary key.
	 * 
	 * @param pk course primary key
	 * @return CourseBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public CourseBean findByPk(long pk) throws ApplicationException {
		CourseBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_course where id = ?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Course by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
			;
		}
		return bean;
	}

	/**
	 * Finds course by name.
	 * 
	 * @param name course name
	 * @return CourseBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public CourseBean findByName(String name) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_course where name = ?");
		CourseBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Course by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
			;
		}
		return bean;
	}
	
	/**
	 * Returns list of all courses.
	 * 
	 * @return list of courses
	 * @throws ApplicationException if application error occurs
	 */
	public List<CourseBean> list() throws ApplicationException{
		return search(null,0,0);
	}

	/**
	 * Searches course records based on criteria and pagination.
	 * 
	 * @param bean CourseBean containing search criteria
	 * @param pageNo page number
	 * @param pageSize number of records per page
	 * @return list of matching courses
	 * @throws ApplicationException if application error occurs
	 */
	public List<CourseBean> search(CourseBean bean, int pageNo, int pageSize) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_course where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" and duration like '" + bean.getDuration() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}

		}

		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}

		ArrayList<CourseBean> list = new ArrayList<CourseBean>();
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}

		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

}