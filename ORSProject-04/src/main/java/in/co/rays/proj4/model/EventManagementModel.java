package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.EventManagementBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class EventManagementModel {

    public Integer nextPk() throws DatabaseException {
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_event_management");
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

    
      public long add(EventManagementBean bean) throws ApplicationException, DuplicateRecordException {
        StringBuffer sql = new StringBuffer("insert into st_event_management values(?,?,?,?,?)");
        int pk = 0;
        Connection conn = null;

        EventManagementBean existBean = findByEventName(bean.getEventName());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Event Name already exists");
        }

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getEventName());
            pstmt.setString(3, bean.getOrganizerName());
            pstmt.setString(4, bean.getVenue());
            pstmt.setDouble(5, bean.getBudget());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add Event Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return pk;
    }

    public void update(EventManagementBean bean) throws ApplicationException, DuplicateRecordException {
        Connection conn = null;

        EventManagementBean existBean = findByEventName(bean.getEventName());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Event Name already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_event_management set event_name = ?, organizer_name = ?, venue = ?, budget = ? where id = ?");

            pstmt.setString(1, bean.getEventName());
            pstmt.setString(2, bean.getOrganizerName());
            pstmt.setString(3, bean.getVenue());
            pstmt.setDouble(4, bean.getBudget());
            pstmt.setLong(5, bean.getId());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : update rollback Exception" + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating Event Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public void delete(EventManagementBean bean) throws ApplicationException {
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("delete from st_event_management where id = ?");
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
            throw new ApplicationException("Exception in delete Event Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public EventManagementBean findByPk(long pk) throws ApplicationException {
        Connection conn = null;
        EventManagementBean bean = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_event_management where id = ?");
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new EventManagementBean();
                bean.setId(rs.getLong(1));
                bean.setEventName(rs.getString(2));
                bean.setOrganizerName(rs.getString(3));
                bean.setVenue(rs.getString(4));
                bean.setBudget(rs.getDouble(5));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Event Record by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }
    
    public EventManagementBean findByEventName(String eventName) throws ApplicationException {
        Connection conn = null;
        EventManagementBean bean = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_event_management where event_name = ?");
            pstmt.setString(1, eventName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new EventManagementBean();
                bean.setId(rs.getLong(1));
                bean.setEventName(rs.getString(2));
                bean.setOrganizerName(rs.getString(3));
                bean.setVenue(rs.getString(4));
                bean.setBudget(rs.getDouble(5));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Event by Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    public List<EventManagementBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    public List<EventManagementBean> search(EventManagementBean bean, int pageNo, int pageSize) throws ApplicationException {
        Connection conn = null;
        List<EventManagementBean> list = new ArrayList<EventManagementBean>();
        StringBuffer sql = new StringBuffer("select * from st_event_management where 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" and id = " + bean.getId());
            }
            if (bean.getEventName() != null && bean.getEventName().length() > 0) {
                sql.append(" and event_name like '" + bean.getEventName() + "%'");
            }
            if (bean.getOrganizerName() != null && bean.getOrganizerName().length() > 0) {
                sql.append(" and organizer_name like '" + bean.getOrganizerName() + "%'");
            }
            if (bean.getVenue() != null && bean.getVenue().length() > 0) {
                sql.append(" and venue like '" + bean.getVenue() + "%'");
            }
            if (bean.getBudget() > 0) {
                sql.append(" and budget = " + bean.getBudget());
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
                bean = new EventManagementBean();
                bean.setId(rs.getLong(1));
                bean.setEventName(rs.getString(2));
                bean.setOrganizerName(rs.getString(3));
                bean.setVenue(rs.getString(4));
                bean.setBudget(rs.getDouble(5));
                list.add(bean);
            }
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search Event Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return list;
    }
}