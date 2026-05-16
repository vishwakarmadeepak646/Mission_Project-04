package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.jdbc.JdbcConnection;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.bean.TimetableBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Model class for Timetable operations.
 * 
 * This class performs database operations related to timetable
 * management such as add, update, delete, search, and validation checks.
 * @author Deepak Vishwakarma
 */
public class TimetableModel {

	/**
	 * Generates the next primary key for timetable table.
	 * 
	 * @return next primary key
	 * @throws DatabaseException if database exception occurs
	 */
	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_timetable");
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
	 * Adds new timetable record into database.
	 * 
	 * @param bean TimetableBean containing timetable details
	 * @return generated primary key
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if duplicate record found
	 */
	public long add(TimetableBean bean) throws ApplicationException , DuplicateRecordException{

		Connection conn = null;
		int pk = 0;

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectModel subModel = new SubjectModel();
		SubjectBean subBean = subModel.findByPk(bean.getSubjectId());
		bean.setSubjectName(subBean.getName());

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_timetable values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getSemester());
			pstmt.setString(3, bean.getDescription());
			pstmt.setDate(4, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(5, bean.getExamTime());
			pstmt.setLong(6, bean.getCourseId());
			pstmt.setString(7, bean.getCourseName());
			pstmt.setLong(8, bean.getSubjectId());
			pstmt.setString(9, bean.getSubjectName());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Time-table added successfully...");

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception in add time-table rollback..." + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add time-table");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	/**
	 * Updates existing timetable record.
	 * 
	 * @param bean TimetableBean containing updated data
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if duplicate record found
	 */
	public void update(TimetableBean bean) throws ApplicationException , DuplicateRecordException{

		Connection conn = null;

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectModel subModel = new SubjectModel();
		SubjectBean subBean = subModel.findByPk(bean.getSubjectId());
		bean.setSubjectName(subBean.getName());

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_timetable set semester= ? , description = ? , exam_date=?, exam_time=?,  course_id= ?, course_name=?, subject_id=?, subject_name=?, created_by=? , modified_by=?, created_datetime=?, modified_datetime=? where id=?");

			pstmt.setString(1, bean.getSemester());
			pstmt.setString(2, bean.getDescription());
			pstmt.setDate(3, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(4, bean.getExamTime());
			pstmt.setLong(5, bean.getCourseId());
			pstmt.setString(6, bean.getCourseName());
			pstmt.setLong(7, bean.getSubjectId());
			pstmt.setString(8, bean.getSubjectName());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Time-table updated successfully...");

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception in update time-table  rollback..." + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in update time-table");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Deletes timetable record from database.
	 * 
	 * @param bean TimetableBean containing timetable id
	 * @throws ApplicationException if application error occurs
	 */
	public void delete(TimetableBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_timetable where id=?");

			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Time-table deleted successfully...");

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception in delete time-table  rollback..." + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete time-table");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Finds timetable by primary key.
	 * 
	 * @param pk primary key
	 * @return TimetableBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public TimetableBean findByPk(long pk) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_timetable where id = ?");
		TimetableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setSemester(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setExamDate(rs.getDate(4));
				bean.setExamTime(rs.getString(5));
				bean.setCourseId(rs.getLong(6));
				bean.setCourseName(rs.getString(7));
				bean.setSubjectId(rs.getLong(8));
				bean.setSubjectName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Timetable by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	/**
	 * Checks timetable by course and exam date.
	 * 
	 * @param courseId course id
	 * @param examDate exam date
	 * @return TimetableBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public TimetableBean checkByCourseName(Long courseId, Date examDate) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_timetable where course_id = ? and exam_date = ?");
		TimetableBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, courseId);
			pstmt.setDate(2, new java.sql.Date(examDate.getTime()));

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setSemester(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setExamDate(rs.getDate(4));
				bean.setExamTime(rs.getString(5));
				bean.setCourseId(rs.getLong(6));
				bean.setCourseName(rs.getString(7));
				bean.setSubjectId(rs.getLong(8));
				bean.setSubjectName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in get Timetable");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	/**
	 * Checks timetable by course, subject and exam date.
	 * 
	 * @param courseid course id
	 * @param subjectId subject id
	 * @param examDate exam date
	 * @return TimetableBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public TimetableBean checkBySubjectName(Long courseid, Long subjectId, Date examDate) throws ApplicationException {

		StringBuffer sql = new StringBuffer(
				"select * from st_timetable where course_id = ? and subject_id =? and exam_date=?");

		Connection conn = null;
		TimetableBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, courseid);
			pstmt.setLong(2, subjectId);
			pstmt.setDate(3, new java.sql.Date(examDate.getTime()));

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TimetableBean();

				bean.setId(rs.getLong(1));
				bean.setSemester(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setExamDate(rs.getDate(4));
				bean.setExamTime(rs.getString(5));
				bean.setCourseId(rs.getLong(6));
				bean.setCourseName(rs.getString(7));
				bean.setSubjectId(rs.getLong(8));
				bean.setSubjectName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in get Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	/**
	 * Checks timetable by semester details.
	 * 
	 * @param courseId course id
	 * @param subjectId subject id
	 * @param semester semester
	 * @param examDate exam date
	 * @return TimetableBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public TimetableBean checkBySemester(Long courseId, Long subjectId, String semester, Date examDate)
			throws ApplicationException {
		StringBuffer sql = new StringBuffer(
				"select * from st_timetable where course_id = ? and subject_id=? and semester = ? and exam_date=?");

		Connection conn = null;
		TimetableBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, courseId);
			pstmt.setLong(2, subjectId);
			pstmt.setString(3, semester);
			pstmt.setDate(4, new java.sql.Date(examDate.getTime()));

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TimetableBean();

				bean.setId(rs.getLong(1));
				bean.setSemester(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setExamDate(rs.getDate(4));
				bean.setExamTime(rs.getString(5));
				bean.setCourseId(rs.getLong(6));
				bean.setCourseName(rs.getString(7));
				bean.setSubjectId(rs.getLong(8));
				bean.setSubjectName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in get Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	/**
	 * Checks timetable by exam time and description.
	 * 
	 * @param courseId course id
	 * @param subjectId subject id
	 * @param semester semester
	 * @param examDate exam date
	 * @param examTime exam time
	 * @param description description
	 * @return TimetableBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public TimetableBean checkByExamTime(Long courseId, Long subjectId, String semester, Date examDate, String examTime,
			String description) throws ApplicationException {
		StringBuffer sql = new StringBuffer(
				"select * from st_timetable where course_id = ? and subject_id = ? and semester = ? and exam_date = ? and exam_time = ? and description = ?");
		TimetableBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, courseId);
			pstmt.setLong(2, subjectId);
			pstmt.setString(3, semester);
			pstmt.setDate(4, new java.sql.Date(examDate.getTime()));
			pstmt.setString(5, examTime);
			pstmt.setString(6, description);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setSemester(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setExamDate(rs.getDate(4));
				bean.setExamTime(rs.getString(5));
				bean.setCourseId(rs.getLong(6));
				bean.setCourseName(rs.getString(7));
				bean.setSubjectId(rs.getLong(8));
				bean.setSubjectName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in get Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	
	/**
	 * Returns list of all timetable records.
	 * 
	 * @return list of timetable records
	 * @throws Exception if exception occurs
	 */
	public List<TimetableBean> list() throws Exception{
		return search(null,0,0);
	}

	/**
	 * Searches timetable records based on criteria and pagination.
	 * 
	 * @param bean TimetableBean containing search criteria
	 * @param PageNo page number
	 * @param PageSize number of records per page
	 * @return list of matching timetable records
	 * @throws ApplicationException if application error occurs
	 */
	public List<TimetableBean> search(TimetableBean bean, int PageNo, int PageSize) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_timetable where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getCourseId() > 0) {
				sql.append("and course_id = " + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" and course_name like'" + bean.getCourseName() + " %'");
			}

			if (bean.getSubjectId() > 0) {
				sql.append(" and subject_id = " + bean.getSubjectId());
			}

			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" and subject_name like'" + bean.getSubjectName() + "%'");
			}

			if (bean.getSemester() != null && bean.getSemester().length() > 0) {
				sql.append(" and semester like'" + bean.getSemester() + "%'");
			}

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like'" + bean.getDescription() + "%'");
			}
			if (bean.getExamDate() != null && bean.getExamDate().getTime()> 0) {
				sql.append(" and exam_date = " + new java.sql.Date(bean.getExamDate().getTime()));
			}
			if (bean.getExamTime() != null && bean.getExamTime().length() > 0) {
				sql.append(" and exam_time like'" + bean.getExamTime() + "%'");
			}

		}

		if (PageNo > 0) {
			PageNo = (PageNo - 1) * PageSize;
			sql.append(" limit " + PageNo + " , " + PageSize);

		}
		System.out.println("SQL Query" + sql.toString());

		ArrayList<TimetableBean> list = new ArrayList<TimetableBean>();
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TimetableBean();

				bean.setId(rs.getLong(1));
				bean.setSemester(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setExamDate(rs.getDate(4));
				bean.setExamTime(rs.getString(5));
				bean.setCourseId(rs.getLong(6));
				bean.setCourseName(rs.getString(7));
				bean.setSubjectId(rs.getLong(8));
				bean.setSubjectName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				list.add(bean);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Timetable");
		}finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

}