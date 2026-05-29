package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.InsuranceBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class InsuranceModel {

    public Integer nextPk() throws DatabaseException {
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_insurance");
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
    

    public long add(InsuranceBean bean) throws ApplicationException, DuplicateRecordException {
        StringBuffer sql = new StringBuffer("insert into st_insurance values(?,?,?,?,?,?,?,?,?)");
        int pk = 0;
        Connection conn = null;

     InsuranceBean existBean = findByCustomerName(bean.getCustomerName());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Customer Name already exists");
        }

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getCustomerName());
            pstmt.setString(3, bean.getPolicyType());
            pstmt.setLong(4, bean.getPremiumAmount());
            pstmt.setString(5, bean.getClaimStatus());
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
            throw new ApplicationException("Exception : Exception in add Insurance Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return pk;
    }

    public void update(InsuranceBean bean) throws ApplicationException, DuplicateRecordException {
        Connection conn = null;

     
        InsuranceBean existBean = findByCustomerName(bean.getCustomerName());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Customer Name already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_insurance set customer_name = ?, policy_type = ?, premium_amount = ?, claim_status = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

            pstmt.setString(1, bean.getCustomerName());
            pstmt.setString(2, bean.getPolicyType());
            pstmt.setLong(3, bean.getPremiumAmount());
            pstmt.setString(4, bean.getClaimStatus());
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
            throw new ApplicationException("Exception in updating Insurance Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public void delete(InsuranceBean bean) throws ApplicationException {
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("delete from st_insurance where id = ?");
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
            throw new ApplicationException("Exception in delete Insurance Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public InsuranceBean findByPk(long pk) throws ApplicationException {
        Connection conn = null;
        InsuranceBean bean = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_insurance where id = ?");
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new InsuranceBean();
                bean.setId(rs.getLong(1));
                bean.setCustomerName(rs.getString(2));
                bean.setPolicyType(rs.getString(3));
                bean.setPremiumAmount(rs.getLong(4));
                bean.setClaimStatus(rs.getString(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Insurance Record by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    public InsuranceBean findByCustomerName(String customerName) throws ApplicationException {
        Connection conn = null;
        InsuranceBean bean = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_insurance where customer_name = ?");
            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new InsuranceBean();
                bean.setId(rs.getLong(1));
                bean.setCustomerName(rs.getString(2));
                bean.setPolicyType(rs.getString(3));
                bean.setPremiumAmount(rs.getLong(4));
                bean.setClaimStatus(rs.getString(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Insurance Record by Customer Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    public List<InsuranceBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    public List<InsuranceBean> search(InsuranceBean bean, int pageNo, int pageSize) throws ApplicationException {
        Connection conn = null;
        List<InsuranceBean> list = new ArrayList<InsuranceBean>();
        StringBuffer sql = new StringBuffer("select * from st_insurance where 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" and id = " + bean.getId());
            }
            if (bean.getCustomerName() != null && bean.getCustomerName().length() > 0) {
                sql.append(" and customer_name like '" + bean.getCustomerName() + "%'");
            }
            if (bean.getPolicyType() != null && bean.getPolicyType().length() > 0) {
                sql.append(" and policy_type like '" + bean.getPolicyType() + "%'");
            }
            if (bean.getPremiumAmount() > 0) {
                sql.append(" and premium_amount = " + bean.getPremiumAmount());
            }
            if (bean.getClaimStatus() != null && bean.getClaimStatus().length() > 0) {
                sql.append(" and claim_status like '" + bean.getClaimStatus() + "%'");
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
                bean = new InsuranceBean();
                bean.setId(rs.getLong(1));
                bean.setCustomerName(rs.getString(2));
                bean.setPolicyType(rs.getString(3));
                bean.setPremiumAmount(rs.getLong(4));
                bean.setClaimStatus(rs.getString(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
                list.add(bean);
            }
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search Insurance Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return list;
    }
}