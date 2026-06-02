package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class ATMModel {

    public Integer nextPk() throws DatabaseException {
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_atm");
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

    /**
     * Duplicate check using Location
     */
    public ATMBean findByLocation(String location) throws ApplicationException {
        Connection conn = null;
        ATMBean bean = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_atm where location = ?");
            pstmt.setString(1, location);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new ATMBean();
                bean.setId(rs.getLong(1));
                bean.setBankName(rs.getString(2));
                bean.setLocation(rs.getString(3));
                bean.setCashAvailable(rs.getDouble(4));
                bean.setSecurityCode(rs.getInt(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting ATM Record by Location");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    public long add(ATMBean bean) throws ApplicationException, DuplicateRecordException {
        StringBuffer sql = new StringBuffer("insert into st_atm values(?,?,?,?,?,?,?,?,?)");
        int pk = 0;
        Connection conn = null;

        // Duplicate Record Validation
        ATMBean existBean = findByLocation(bean.getLocation());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Location already exists");
        }

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getBankName());
            pstmt.setString(3, bean.getLocation());
            pstmt.setDouble(4, bean.getCashAvailable());
            pstmt.setInt(5, bean.getSecurityCode());
            pstmt.setString(6, bean.getCreatedBy());
            pstmt.setString(7, bean.getModifiedBy());
            pstmt.setTimestamp(8, bean.getCreatedDatetime());
            pstmt.setTimestamp(9, bean.getModifiedDatetime());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add ATM Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return pk;
    }

    public void update(ATMBean bean) throws ApplicationException, DuplicateRecordException {
        Connection conn = null;

        // Duplicate Record Validation
        ATMBean existBean = findByLocation(bean.getLocation());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Location already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_atm set bank_name = ?, location = ?, cash_available = ?, security_code = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

            pstmt.setString(1, bean.getBankName());
            pstmt.setString(2, bean.getLocation());
            pstmt.setDouble(3, bean.getCashAvailable());
            pstmt.setInt(4, bean.getSecurityCode());
            pstmt.setString(5, bean.getCreatedBy());
            pstmt.setString(6, bean.getModifiedBy());
            pstmt.setTimestamp(7, bean.getCreatedDatetime());
            pstmt.setTimestamp(8, bean.getModifiedDatetime());
            pstmt.setLong(9, bean.getId());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : update rollback Exception" + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating ATM Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public void delete(ATMBean bean) throws ApplicationException {
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("delete from st_atm where id = ?");
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
            throw new ApplicationException("Exception in delete ATM Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public ATMBean findByPk(long pk) throws ApplicationException {
        Connection conn = null;
        ATMBean bean = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_atm where id = ?");
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new ATMBean();
                bean.setId(rs.getLong(1));
                bean.setBankName(rs.getString(2));
                bean.setLocation(rs.getString(3));
                bean.setCashAvailable(rs.getDouble(4));
                bean.setSecurityCode(rs.getInt(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting ATM Record by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    public List<ATMBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    public List<ATMBean> search(ATMBean bean, int pageNo, int pageSize) throws ApplicationException {
        Connection conn = null;
        List<ATMBean> list = new ArrayList<ATMBean>();
        StringBuffer sql = new StringBuffer("select * from st_atm where 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" and id = " + bean.getId());
            }
            if (bean.getBankName() != null && bean.getBankName().length() > 0) {
                sql.append(" and bank_name like '" + bean.getBankName() + "%'");
            }
            if (bean.getLocation() != null && bean.getLocation().length() > 0) {
                sql.append(" and location like '" + bean.getLocation() + "%'");
            }
            if (bean.getCashAvailable() > 0) {
                sql.append(" and cash_available = " + bean.getCashAvailable());
            }
            if (bean.getSecurityCode() > 0) {
                sql.append(" and security_code = " + bean.getSecurityCode());
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
                bean = new ATMBean();
                bean.setId(rs.getLong(1));
                bean.setBankName(rs.getString(2));
                bean.setLocation(rs.getString(3));
                bean.setCashAvailable(rs.getDouble(4));
                bean.setSecurityCode(rs.getInt(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
                list.add(bean);
            }
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search ATM Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return list;
    }
}