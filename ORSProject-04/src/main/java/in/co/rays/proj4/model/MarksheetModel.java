package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mchange.util.DuplicateElementException;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Model class for Marksheet operations.
 * 
 * This class handles all database operations related to Marksheet such as add,
 * update, delete, search, and find operations.
 *  @author Deepak Vishwakarma
 */
public class MarksheetModel {

	/**
	 * Generates next primary key for st_marksheet table.
	 * 
	 * @return next primary key
	 * @throws DatabaseException if database exception occurs
	 */
	public Integer nextPk() throws DatabaseException {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_Marksheet");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception in Marksheet getting PK");
		} finally {
			JDBCDataSource.getConnection();
		}
		return pk + 1;
	}

	/**
	 * Adds new marksheet record into database.
	 * 
	 * @param bean MarksheetBean containing marksheet details
	 * @return generated primary key
	 * @throws ApplicationException     if application error occurs
	 * @throws DuplicateRecordException if duplicate roll number exists
	 */
	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		StudentModel studentModel = new StudentModel();
		StudentBean studentBean = studentModel.findByPk(bean.getStudentId());
		bean.setName(studentBean.getFirstName() + " " + studentBean.getLastName());

		MarksheetBean beanExist = findByRollNo(bean.getRollNo());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Roll Number already exists");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_marksheet values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, bean.getName());
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.executeUpdate();

			conn.commit();
			System.out.println("Marksheet added successfully...");
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
				throw new ApplicationException("add rollback Exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in adding Marksheet...");
		}

		return pk;
	}

	/**
	 * Updates existing marksheet record.
	 * 
	 * @param bean MarksheetBean containing updated details
	 * @throws ApplicationException     if application error occurs
	 * @throws DuplicateRecordException if duplicate roll number exists
	 */
	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		MarksheetBean beanExist = findByRollNo(bean.getRollNo());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Roll Number already exists");
		}

		StudentModel studentModel = new StudentModel();
		StudentBean studentBean = studentModel.findByPk(bean.getStudentId());

		bean.setName(studentBean.getFirstName() + " " + studentBean.getLastName());

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_marksheet set roll_no= ?, student_id=?, name =?, physics = ?, chemistry = ?, maths=?, created_by = ?, modified_by=?, created_datetime= ?, modified_datetime=? where id= ?");

			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, bean.getName());
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			pstmt.executeUpdate();

			conn.commit();
			System.out.println("Marksheet updated successfully...");
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
				throw new ApplicationException("update rollback Exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Marksheet...");
		}

	}

	/**
	 * Deletes marksheet record from database.
	 * 
	 * @param bean MarksheetBean containing marksheet id
	 * @throws ApplicationException if application error occurs
	 */
	public void delete(MarksheetBean bean) throws ApplicationException {
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_marksheet where id= ?");

			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();

			conn.commit();
			System.out.println("Marksheet Deleted successfully...");
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
				throw new ApplicationException("Delete rollback Exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in delete Marksheet...");
		}

	}

	/**
	 * Finds marksheet by primary key.
	 * 
	 * @param id marksheet primary key
	 * @return MarksheetBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public MarksheetBean findByPk(long id) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_marksheet where id = ?");
		Connection conn = null;
		MarksheetBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting marksheet by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	/**
	 * Finds marksheet by roll number.
	 * 
	 * @param rollNo roll number
	 * @return MarksheetBean object if found otherwise null
	 * @throws ApplicationException if application error occurs
	 */
	public MarksheetBean findByRollNo(String rollNo) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_marksheet where roll_no = ?");
		Connection conn = null;
		MarksheetBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, rollNo);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting marksheet by roll number");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	/**
	 * Returns list of all marksheets.
	 * 
	 * @return list of marksheets
	 * @throws ApplicationException if application error occurs
	 */
	public List<MarksheetBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	/**
	 * Searches marksheet records based on criteria and pagination.
	 * 
	 * @param bean     MarksheetBean containing search criteria
	 * @param pageNo   page number
	 * @param PageSize number of records per page
	 * @return list of matching marksheets
	 * @throws ApplicationException if application error occurs
	 */
	public List<MarksheetBean> search(MarksheetBean bean, int pageNo, int PageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_marksheet where 1=1");

		if (bean != null) {

			if (bean != null) {
				if (bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}
				if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
					sql.append(" and roll_no like '" + bean.getRollNo() + "%'");
				}
				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" and name like '" + bean.getName() + "%'");
				}
				if (bean.getPhysics() != 0 && bean.getPhysics() > 0) {
					sql.append(" and physics = " + bean.getPhysics());
				}
				if (bean.getChemistry() != 0 && bean.getChemistry() > 0) {
					sql.append(" and chemistry = " + bean.getChemistry());
				}
				if (bean.getMaths() != 0 && bean.getMaths() > 0) {
					sql.append(" and maths = '" + bean.getMaths());
				}
			}
		}
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * PageSize;
			sql.append(" limit " + pageNo + " , " + PageSize);
		}
		Connection conn = null;
		List<MarksheetBean> list = new ArrayList<MarksheetBean>();

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in search Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;

	}

	public List<MarksheetBean> getMeritList(int pageNo, int pageSize) throws ApplicationException {

		ArrayList<MarksheetBean> list = new ArrayList<MarksheetBean>();
		StringBuffer sql = new StringBuffer(
				"select id, roll_no, name, physics, chemistry, maths, (physics + chemistry + maths) as total from st_marksheet where physics > 33 and chemistry > 33 and maths > 33 order by total desc");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MarksheetBean bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setPhysics(rs.getInt(4));
				bean.setChemistry(rs.getInt(5));
				bean.setMaths(rs.getInt(6));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception in getting merit list of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}