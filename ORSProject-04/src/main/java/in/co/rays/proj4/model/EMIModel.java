package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.EMIBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.util.JDBCDataSource;

public class EMIModel {

    public Integer nextPk() throws DatabaseException {
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_emi");
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

    public long add(EMIBean bean) throws ApplicationException {
        StringBuffer sql = new StringBuffer("insert into st_emi values(?,?,?,?,?,?,?,?)");
        int pk = 0;
        Connection conn = null;

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            pstmt.setInt(1, pk);
            pstmt.setLong(2, bean.getAmount());
            pstmt.setDate(3, new java.sql.Date(bean.getDueDate().getTime()));
            pstmt.setString(4, bean.getStatus());
            pstmt.setString(5, bean.getCreatedBy());
            pstmt.setString(6, bean.getModifiedBy());
            pstmt.setTimestamp(7, bean.getCreatedDatetime());
            pstmt.setTimestamp(8, bean.getModifiedDatetime());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add EMI Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return pk;
    }

    public void update(EMIBean bean) throws ApplicationException {
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_emi set amount = ?, due_date = ?, status = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

            pstmt.setLong(1, bean.getAmount());
            pstmt.setDate(2, new java.sql.Date(bean.getDueDate().getTime()));
            pstmt.setString(3, bean.getStatus());
            pstmt.setString(4, bean.getCreatedBy());
            pstmt.setString(5, bean.getModifiedBy());
            pstmt.setTimestamp(6, bean.getCreatedDatetime());
            pstmt.setTimestamp(7, bean.getModifiedDatetime());
            pstmt.setLong(8, bean.getId());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : update rollback Exception" + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating EMI Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public void delete(EMIBean bean) throws ApplicationException {
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("delete from st_emi where id = ?");
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
            throw new ApplicationException("Exception in delete EMI Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public EMIBean findByPk(long pk) throws ApplicationException {
        Connection conn = null;
        EMIBean bean = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_emi where id = ?");
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new EMIBean();
                bean.setId(rs.getLong(1));
                bean.setAmount(rs.getLong(2));
                bean.setDueDate(rs.getDate(3));
                bean.setStatus(rs.getString(4));
                bean.setCreatedBy(rs.getString(5));
                bean.setModifiedBy(rs.getString(6));
                bean.setCreatedDatetime(rs.getTimestamp(7));
                bean.setModifiedDatetime(rs.getTimestamp(8));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting EMI Record by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    public List<EMIBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    public List<EMIBean> search(EMIBean bean, int pageNo, int pageSize) throws ApplicationException {
        Connection conn = null;
        List<EMIBean> list = new ArrayList<EMIBean>();
        StringBuffer sql = new StringBuffer("select * from st_emi where 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" and id = " + bean.getId());
            }
            if (bean.getAmount() > 0) {
                sql.append(" and amount = " + bean.getAmount());
            }
            if (bean.getDueDate() != null) {
                sql.append(" and due_date = '" + new java.sql.Date(bean.getDueDate().getTime()) + "'");
            }
            if (bean.getStatus() != null && bean.getStatus().length() > 0) {
                sql.append(" and status like '" + bean.getStatus() + "%'");
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
                bean = new EMIBean();
                bean.setId(rs.getLong(1));
                bean.setAmount(rs.getLong(2));
                bean.setDueDate(rs.getDate(3));
                bean.setStatus(rs.getString(4));
                bean.setCreatedBy(rs.getString(5));
                bean.setModifiedBy(rs.getString(6));
                bean.setCreatedDatetime(rs.getTimestamp(7));
                bean.setModifiedDatetime(rs.getTimestamp(8));
                list.add(bean);
            }
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search EMI Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return list;
    }
}