package in.co.rays.proj4.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBCDataSource implements a robust Singleton design pattern to manage database 
 * connectivity. It uses the c3p0 library to maintain a connection pool, enhancing 
 * performance and scalability by reusing established connections.
*  @author Deepak Vishwakarma
 */
public final class JDBCDataSource {

	/** Singleton instance variable */
	private static JDBCDataSource jds = null;

	/** c3p0 Connection Pool DataSource */
	private static ComboPooledDataSource cpds = null;

	/** ResourceBundle for fetching database credentials */
	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.proj4.bundle.system");

	/**
	 * Private constructor to restrict instantiation from outside.
	 * Initializes the c3p0 connection pool with settings from the properties file.
	 */
	private JDBCDataSource() {
		try {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass(rb.getString("driver"));
			cpds.setJdbcUrl(rb.getString("url"));
			cpds.setUser(rb.getString("username"));
			cpds.setPassword(rb.getString("password"));
			cpds.setInitialPoolSize(Integer.parseInt(rb.getString("initialpoolsize")));
			cpds.setAcquireIncrement(Integer.parseInt(rb.getString("acquireincrement")));
			cpds.setMaxPoolSize(Integer.parseInt(rb.getString("maxpoolsize")));
			cpds.setMinPoolSize(Integer.parseInt(rb.getString("minpoolsize")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Provides the global point of access to the JDBCDataSource instance.
	 * * @return the singleton instance of JDBCDataSource
	 */
	public static JDBCDataSource getInstance() {
		if (jds == null) {
			jds = new JDBCDataSource();
		}
		return jds;
	}

	/**
	 * Retrieves an active connection from the connection pool.
	 * * @return a Connection object, or null if an error occurs
	 */
	public static Connection getConnection() {
		try {
			return getInstance().cpds.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Closes the provided database connection, statement, and result set resources safely.
	 * * @param conn the connection to close
	 * @param stmt the statement to close
	 * @param rs the result set to close
	 */
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the provided database connection and statement safely.
	 * * @param conn the connection to close
	 * @param stmt the statement to close
	 */
	public static void closeConnection(Connection conn, Statement stmt) {
		closeConnection(conn, stmt, null);
	}

	/**
	 * Closes the provided database connection safely.
	 * * @param conn the connection to close
	 */
	public static void closeConnection(Connection conn) {
		closeConnection(conn, null);
	}
}