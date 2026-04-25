package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.util.JDBCDataSource;

public class StudentModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_student where id = ?");
			pstmt.setInt(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(pk);
			}

			rs.close();
			conn.close();

		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}
	
	public long add(StudentBean bean) throws ApplicationException {
		Connection conn = null;
		int pk = 0;
		
		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getId());
		
		
		return pk;
	}
}
