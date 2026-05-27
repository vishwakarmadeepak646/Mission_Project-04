package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.util.JDBCDataSource;

public class HospitalModel {

    public Integer nextPk() throws DatabaseException {
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_hospital");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
        } catch (Exception e) {
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return pk + 1;
    }

    public long add(HospitalBean bean) throws ApplicationException {
        StringBuffer sql = new StringBuffer("insert into st_hospital values(?,?,?,?,?,?,?,?,?,?)");
        int pk = 0;
        Connection conn = null;

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            pstmt.setInt(1, pk);
            pstmt.setInt(2, bean.getPatientId());
            pstmt.setString(3, bean.getPatientName());
            pstmt.setString(4, bean.getDoctorName());
            pstmt.setString(5, bean.getDisease());
            pstmt.setInt(6, bean.getRoomNumber());
            pstmt.setString(7, bean.getCreatedBy());
            pstmt.setString(8, bean.getModifiedBy());
            pstmt.setTimestamp(9, bean.getCreatedDatetime());
            pstmt.setTimestamp(10, bean.getModifiedDatetime());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add Hospital Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return pk;
    }

    public void update(HospitalBean bean) throws ApplicationException {
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_hospital set patient_id = ?, patient_name = ?, doctor_name = ?, disease = ?, room_number = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

            pstmt.setInt(1, bean.getPatientId());
            pstmt.setString(2, bean.getPatientName());
            pstmt.setString(3, bean.getDoctorName());
            pstmt.setString(4, bean.getDisease());
            pstmt.setInt(5, bean.getRoomNumber());
            pstmt.setString(6, bean.getCreatedBy());
            pstmt.setString(7, bean.getModifiedBy());
            pstmt.setTimestamp(8, bean.getCreatedDatetime());
            pstmt.setTimestamp(9, bean.getModifiedDatetime());
            pstmt.setLong(10, bean.getId());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : update rollback Exception" + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating Hospital Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public void delete(HospitalBean bean) throws ApplicationException {
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("delete from st_hospital where id = ?");
            pstmt.setLong(1, bean.getId());
            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : delete rollback Exception" + ex.getMessage());
            }
            throw new ApplicationException("Exception in delete Hospital Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public HospitalBean findByPk(long pk) throws ApplicationException {
        Connection conn = null;
        HospitalBean bean = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_hospital where id = ?");
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new HospitalBean();
                bean.setId(rs.getLong(1));
                bean.setPatientId(rs.getInt(2));
                bean.setPatientName(rs.getString(3));
                bean.setDoctorName(rs.getString(4));
                bean.setDisease(rs.getString(5));
                bean.setRoomNumber(rs.getInt(6));
                bean.setCreatedBy(rs.getString(7));
                bean.setModifiedBy(rs.getString(8));
                bean.setCreatedDatetime(rs.getTimestamp(9));
                bean.setModifiedDatetime(rs.getTimestamp(10));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Hospital Record by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    public List<HospitalBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    public List<HospitalBean> search(HospitalBean bean, int pageNo, int pageSize) throws ApplicationException {
        Connection conn = null;
        List<HospitalBean> list = new ArrayList<HospitalBean>();
        StringBuffer sql = new StringBuffer("select * from st_hospital where 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" and id = " + bean.getId());
            }
            if (bean.getPatientId() > 0) {
                sql.append(" and patient_id = " + bean.getPatientId());
            }
            if (bean.getPatientName() != null && bean.getPatientName().length() > 0) {
                sql.append(" and patient_name like '" + bean.getPatientName() + "%'");
            }
            if (bean.getDoctorName() != null && bean.getDoctorName().length() > 0) {
                sql.append(" and doctor_name like '" + bean.getDoctorName() + "%'");
            }
            if (bean.getDisease() != null && bean.getDisease().length() > 0) {
                sql.append(" and disease like '" + bean.getDisease() + "%'");
            }
            if (bean.getRoomNumber() > 0) {
                sql.append(" and room_number = " + bean.getRoomNumber());
            }
        }

        if (pageNo > 0) {
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + " ," + pageSize);
        }

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new HospitalBean();
                bean.setId(rs.getLong(1));
                bean.setPatientId(rs.getInt(2));
                bean.setPatientName(rs.getString(3));
                bean.setDoctorName(rs.getString(4));
                bean.setDisease(rs.getString(5));
                bean.setRoomNumber(rs.getInt(6));
                bean.setCreatedBy(rs.getString(7));
                bean.setModifiedBy(rs.getString(8));
                bean.setCreatedDatetime(rs.getTimestamp(9));
                bean.setModifiedDatetime(rs.getTimestamp(10));
                list.add(bean);
            }
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search Hospital Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return list;
    }
}