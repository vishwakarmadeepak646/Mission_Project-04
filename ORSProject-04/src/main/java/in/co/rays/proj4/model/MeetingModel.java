package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.MeetingBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MeetingModel {

    public Integer nextPk() throws DatabaseException {
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_meeting");
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

    public long add(MeetingBean bean) throws ApplicationException {
        StringBuffer sql = new StringBuffer("insert into st_meeting values(?,?,?,?,?,?,?,?,?)");
        int pk = 0;
        Connection conn = null;

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getHostName());
            pstmt.setString(3, bean.getPlatform());
            pstmt.setInt(4, bean.getDuration());
            pstmt.setInt(5, bean.getParticipants());
            pstmt.setString(6, bean.getCreatedBy());
            pstmt.setString(7, bean.getModifiedBy());
            pstmt.setTimestamp(8, bean.getCreatedDatetime());
            pstmt.setTimestamp(9, bean.getModifiedDatetime());

            pstmt.executeUpdate();
            System.out.println("Meeting record added successfully...");
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add Meeting");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return pk;
    }

    public void update(MeetingBean bean) throws ApplicationException {
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_meeting set host_name = ?, platform = ?, duration = ?, participants = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

            pstmt.setString(1, bean.getHostName());
            pstmt.setString(2, bean.getPlatform());
            pstmt.setInt(3, bean.getDuration());
            pstmt.setInt(4, bean.getParticipants());
            pstmt.setString(5, bean.getCreatedBy());
            pstmt.setString(6, bean.getModifiedBy());
            pstmt.setTimestamp(7, bean.getCreatedDatetime());
            pstmt.setTimestamp(8, bean.getModifiedDatetime());
            pstmt.setLong(9, bean.getId());

            pstmt.executeUpdate();
            System.out.println("Meeting updated successfully...");
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : update rollback Exception" + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating Meeting ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public void delete(MeetingBean bean) throws ApplicationException {
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("delete from st_meeting where id = ?");
            pstmt.setLong(1, bean.getId());
            pstmt.executeUpdate();
            System.out.println("Meeting deleted successfully...");
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : delete rollback Exception" + ex.getMessage());
            }
            throw new ApplicationException("Exception in delete Meeting");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public MeetingBean findByPk(long pk) throws ApplicationException {
        Connection conn = null;
        MeetingBean bean = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_meeting where id = ?");
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new MeetingBean();
                bean.setId(rs.getLong(1));
                bean.setHostName(rs.getString(2));
                bean.setPlatform(rs.getString(3));
                bean.setDuration(rs.getInt(4));
                bean.setParticipants(rs.getInt(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Meeting by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    public List<MeetingBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    public List<MeetingBean> search(MeetingBean bean, int pageNo, int pageSize) throws ApplicationException {
        Connection conn = null;
        List<MeetingBean> list = new ArrayList<MeetingBean>();
        StringBuffer sql = new StringBuffer("select * from st_meeting where 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" and id = " + bean.getId());
            }
            if (bean.getHostName() != null && bean.getHostName().length() > 0) {
                sql.append(" and host_name like '" + bean.getHostName() + "%'");
            }
            if (bean.getPlatform() != null && bean.getPlatform().length() > 0) {
                sql.append(" and platform like '" + bean.getPlatform() + "%'");
            }
            if (bean.getDuration() > 0) {
                sql.append(" and duration = " + bean.getDuration());
            }
            if (bean.getParticipants() > 0) {
                sql.append(" and participants = " + bean.getParticipants());
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
                bean = new MeetingBean();
                bean.setId(rs.getLong(1));
                bean.setHostName(rs.getString(2));
                bean.setPlatform(rs.getString(3));
                bean.setDuration(rs.getInt(4));
                bean.setParticipants(rs.getInt(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
                list.add(bean);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            throw new ApplicationException("Exception : Exception in search meeting");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return list;
    }
}