package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.LibraryBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class LibraryModel {

    public Integer nextPk() throws DatabaseException {
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_library");
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

    
    

    public long add(LibraryBean bean) throws ApplicationException, DuplicateRecordException {
        StringBuffer sql = new StringBuffer("insert into st_library values(?,?,?,?,?)");
        int pk = 0;
        Connection conn = null;

        LibraryBean existBean = findByBookName(bean.getBookName());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Book Name already exists");
        }

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getBookName());
            pstmt.setString(3, bean.getAuthorName());
            pstmt.setDate(4, new java.sql.Date(bean.getIssueDate().getTime()));
            pstmt.setDouble(5, bean.getPrice());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add Library Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return pk;
    }

    public void update(LibraryBean bean) throws ApplicationException, DuplicateRecordException {
        Connection conn = null;

        LibraryBean existBean = findByBookName(bean.getBookName());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Book Name already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_library set book_name = ?, author_name = ?, issue_date = ?, price = ? where id = ?");

            pstmt.setString(1, bean.getBookName());
            pstmt.setString(2, bean.getAuthorName());
            pstmt.setDate(3, new java.sql.Date(bean.getIssueDate().getTime()));
            pstmt.setDouble(4, bean.getPrice());
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
            throw new ApplicationException("Exception in updating Library Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public void delete(LibraryBean bean) throws ApplicationException {
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("delete from st_library where id = ?");
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
            throw new ApplicationException("Exception in delete Library Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    public LibraryBean findByPk(long pk) throws ApplicationException {
        Connection conn = null;
        LibraryBean bean = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_library where id = ?");
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new LibraryBean();
                bean.setId(rs.getLong(1));
                bean.setBookName(rs.getString(2));
                bean.setAuthorName(rs.getString(3));
                bean.setIssueDate(rs.getDate(4));
                bean.setPrice(rs.getDouble(5));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Library Record by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    public List<LibraryBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }
    public LibraryBean findByBookName(String bookName) throws ApplicationException {
        Connection conn = null;
        LibraryBean bean = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from st_library where book_name = ?");
            pstmt.setString(1, bookName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new LibraryBean();
                bean.setId(rs.getLong(1));
                bean.setBookName(rs.getString(2));
                bean.setAuthorName(rs.getString(3));
                bean.setIssueDate(rs.getDate(4));
                bean.setPrice(rs.getDouble(5));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Book by Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    public List<LibraryBean> search(LibraryBean bean, int pageNo, int pageSize) throws ApplicationException {
        Connection conn = null;
        List<LibraryBean> list = new ArrayList<LibraryBean>();
        StringBuffer sql = new StringBuffer("select * from st_library where 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" and id = " + bean.getId());
            }
            if (bean.getBookName() != null && bean.getBookName().length() > 0) {
                sql.append(" and book_name like '" + bean.getBookName() + "%'");
            }
            if (bean.getAuthorName() != null && bean.getAuthorName().length() > 0) {
                sql.append(" and author_name like '" + bean.getAuthorName() + "%'");
            }
            if (bean.getIssueDate() != null) {
                sql.append(" and issue_date = '" + new java.sql.Date(bean.getIssueDate().getTime()) + "'");
            }
            if (bean.getPrice() > 0) {
                sql.append(" and price = " + bean.getPrice());
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
                bean = new LibraryBean();
                bean.setId(rs.getLong(1));
                bean.setBookName(rs.getString(2));
                bean.setAuthorName(rs.getString(3));
                bean.setIssueDate(rs.getDate(4));
                bean.setPrice(rs.getDouble(5));
                list.add(bean);
            }
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search Library Record");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return list;
    }
}