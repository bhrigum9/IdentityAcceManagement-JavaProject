package epita.fr.java.identity.management.database.connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import epita.fr.java.identity.management.common.core.IdentityManagement;
import epita.fr.java.identity.management.common.core.ReadPropertiesFile;
import epita.fr.java.identity.management.common.core.UserDetails;
import epita.fr.java.identity.management.constants.IdentityConstants;

/**
 * CLass used to create JDBC Connection and provide methods to perform CRUD
 * operations
 * 
 * @author Bhrigu Mahajan
 *
 */
public final class GetJDBCConnection {
	private static Connection conn = null;
	private static Statement stmt = null;
	private static String dbURL = ReadPropertiesFile.readProperties().get("dbURL");
	private static String identityTable = ReadPropertiesFile.readProperties().get("tableName");
	private static String userTable = ReadPropertiesFile.readProperties().get("userTable");
	private static String schema = ReadPropertiesFile.readProperties().get("schemaName");

	private static final Logger logger = Logger.getLogger(GetJDBCConnection.class);

	/**
	 * 
	 * @return connection
	 */
	public static Connection createConnection() {
		try {
			String driver = ReadPropertiesFile.readProperties().get("driver");
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception except) {
			logger.error("Failed to create Connection" + except);
		}
		return conn;
	}

	/**
	 * shutdown connection
	 */
	public static void shutdown() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.setAutoCommit(true);
				DriverManager.getConnection(dbURL + ";shutdown=true");
				conn.close();
			}
		} catch (SQLException sqlExcept) {
			logger.error("Failed to shutdown Connection" + sqlExcept);
		}
	}

	/**
	 * Get the list of Database columns
	 * 
	 * @throws SQLException
	 */
	public static List<String> getColumnList() throws SQLException {
		DatabaseMetaData md = null;
		ResultSet rset = null;
		List<String> columnList = new ArrayList<>();
		try {
			createConnection();
			md = conn.getMetaData();
			rset = md.getColumns(null, schema, identityTable, IdentityConstants.PERCENT);
			while (rset.next()) {
				// 1: none
				// 2: schema
				// 3: table name
				// 4: column name
				// 5: length
				// 6: data type (CHAR, VARCHAR, TIMESTAMP, ...)
				columnList.add(rset.getString(4));
			}
		} catch (SQLException e) {
			logger.error("Failed to get column list" + e);
		} finally {
			if (rset != null) {
				rset.close();
			}
			shutdown();
		}
		return columnList;
	}

	/**
	 * 
	 * @param identityManagement
	 * @throws SQLException
	 */
	public void insertRecords(IdentityManagement identityManagement) throws SQLException {
		try {
			createConnection();
			StringBuilder sb = PrepareSQLQueries.prepareInsertStatement(identityTable)
					.append(PrepareSQLQueries.insertIdentityStatement(identityManagement.getDisplayName(),
							identityManagement.getEmail(), identityManagement.getAttribute(),
							identityManagement.getUid()));

			stmt = conn.createStatement();
			stmt.executeUpdate(sb.toString());
			conn.commit();
		} catch (SQLException sqlExcept) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error(IdentityConstants.ROLLBACK_ERR + e);
			}
			logger.error("Failed to insert identity records" + sqlExcept);
		} finally {
			shutdown();
		}
	}

	/**
	 * 
	 * @param userDetails
	 */
	public void insertUserDetails(UserDetails userDetails) {
		try {
			createConnection();
			StringBuilder sb = PrepareSQLQueries.prepareInsertStatement(userTable)
					.append(PrepareSQLQueries.insertUserDetailStatement(userDetails.getUid(), userDetails.getUsername(),
							userDetails.getPassword()));

			stmt = conn.createStatement();
			stmt.executeUpdate(sb.toString());
			conn.commit();
		} catch (SQLException sqlExcept) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error(IdentityConstants.ROLLBACK_ERR + e);
			}
			logger.error("Failed to insert user records" + sqlExcept);
		} finally {
			shutdown();
		}
	}

	/**
	 * 
	 * @return list IdentityManagement object
	 */
	public List<IdentityManagement> selectRecords() {
		List<IdentityManagement> dataList = new ArrayList<>();
		try {
			createConnection();
			StringBuilder sb = PrepareSQLQueries.prepareSelectStatement();

			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sb.toString());

			while (results.next()) {
				IdentityManagement identityManagement = new IdentityManagement();
				String uid = results.getString(4);
				identityManagement.setUid(uid);
				String displayName = results.getString(1);
				identityManagement.setDisplayName(displayName);
				String email = results.getString(2);
				identityManagement.setEmail(email);
				String attribute = results.getString(3);
				identityManagement.setAttribute(attribute);
				dataList.add(identityManagement);
			}
			results.close();
		} catch (SQLException sqlExcept) {
			logger.error("Failed to fetch list of records" + sqlExcept);
		} finally {
			shutdown();
		}
		return dataList;
	}

	/**
	 * 
	 * @param id
	 * @return IdentityManagement
	 */
	public IdentityManagement selectRecordById(String id) {
		IdentityManagement identityManagement = new IdentityManagement();
		try {
			createConnection();
			StringBuilder sb = PrepareSQLQueries.prepareSelectStatement();
			sb.append(PrepareSQLQueries.prepareWhereClauseId(id));
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sb.toString());

			while (results.next()) {
				String uid = results.getString(4);
				identityManagement.setUid(uid);
				String displayName = results.getString(1);
				identityManagement.setDisplayName(displayName);
				String email = results.getString(2);
				identityManagement.setEmail(email);
				String attribute = results.getString(3);
				identityManagement.setAttribute(attribute);
			}
			results.close();
		} catch (SQLException sqlExcept) {
			logger.error("Failed to fetch record" + sqlExcept);
		} finally {
			shutdown();
		}
		return identityManagement;
	}

	/**
	 * 
	 * @param identityManagementVO
	 */
	public void updateRecords(IdentityManagement identityManagementVO) {
		try {
			StringBuilder updateRecordById = PrepareSQLQueries
					.prepareUpdateStatement(identityManagementVO.getDisplayName(), identityManagementVO.getEmail(),
							identityManagementVO.getAttribute())

					.append(PrepareSQLQueries.prepareWhereClauseId(identityManagementVO.getUid()));
			createConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(updateRecordById.toString());
			conn.commit();
		} catch (SQLException sqlExcept) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error(IdentityConstants.ROLLBACK_ERR + e);
			}
			logger.error("Failed to update record" + sqlExcept);
		} finally {
			shutdown();
		}
	}

	/**
	 * 
	 * @param uid
	 */
	public void deleteRecord(String uid) {
		try {
			createConnection();
			stmt = conn.createStatement();
			StringBuilder delete = PrepareSQLQueries.prepareDeleteStatement()
					.append(PrepareSQLQueries.prepareWhereClauseId(uid));
			stmt.executeUpdate(delete.toString());
			conn.commit();
		} catch (SQLException sqlExcept) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error(IdentityConstants.ROLLBACK_ERR + e);
			}
			logger.error("Failed to delete record" + sqlExcept);
		} finally {
			shutdown();
		}
	}

	public List<IdentityManagement> searchRecords(String searchValue) {
		List<IdentityManagement> searchList = new ArrayList<>();
		try {
			createConnection();
			StringBuilder searchRecords = PrepareSQLQueries.prepareSearchStatement(searchValue);

			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(searchRecords.toString());

			while (results.next()) {
				IdentityManagement identityManagement = new IdentityManagement();
				String uid = results.getString(4);
				identityManagement.setUid(uid);
				String displayName = results.getString(1);
				identityManagement.setDisplayName(displayName);
				String email = results.getString(2);
				identityManagement.setEmail(email);
				String attribute = results.getString(3);
				identityManagement.setAttribute(attribute);
				searchList.add(identityManagement);
			}
			results.close();
		} catch (SQLException sqlExcept) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error(IdentityConstants.ROLLBACK_ERR + e);
			}
			logger.error("Failed to update record" + sqlExcept);
		} finally {
			shutdown();
		}
		return searchList;
	}

}