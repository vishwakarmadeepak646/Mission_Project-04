package in.co.rays.proj4.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.JDBCDataSource;

public class StudentModel {

	public Integer nextPk() throws DatabaseException {
		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_student");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new DatabaseException("Exception :Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(StudentBean bean) throws ApplicationException {
		int pk = 0;
		Connection conn = null;

		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());

		bean.setCollegeName(collegeBean.getName());

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_student values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setLong(8, bean.getCollegeId());
			pstmt.setString(9, bean.getCollegeName());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Student added successfully");
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void update(StudentBean bean) throws ApplicationException {
		int pk = 0;
		Connection conn = null;

		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());

		bean.setCollegeName(collegeBean.getName());

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_student set first_name = ?, last_name=?, dob =?, gender = ?, mobile_no = ?, email=?, college_id = ?, college_name=?, created_by = ?, modified_by=?, created_datetime= ?, modified_datetime=? where id= ?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getEmail());
			pstmt.setLong(7, bean.getCollegeId());
			pstmt.setString(8, bean.getCollegeName());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Student updated successfully");
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
				throw new ApplicationException("Exception : update rollback exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in updating Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

}
