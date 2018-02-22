package epita.fr.java.identity.management.authentication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import epita.fr.java.identity.management.common.core.UserDetails;
import epita.fr.java.identity.management.database.connection.GetJDBCConnection;
import epita.fr.java.identity.management.database.connection.PrepareSQLQueries;

public class Auth {
	private static final Logger logger = Logger.getLogger(GetJDBCConnection.class);

	/**
	 * Method used for login authentication
	 * 
	 * @param userDetails
	 * @return authentication
	 * @throws SQLException
	 */
	public String validateUsers(UserDetails userDetails) throws SQLException {
		String id = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		try {
			conn = GetJDBCConnection.createConnection();
			stmt = conn.createStatement();
			StringBuilder queryString = PrepareSQLQueries.prepareAuthSelectStatement();
			results = stmt.executeQuery(queryString.toString());
			while (results.next()) {
				if ((userDetails.getUsername().equals(results.getString("user")))
						&& (userDetails.getPassword().equals(results.getString("password")))) {
					id = results.getString("uid");
				}
			}

		} catch (SQLException sql) {
			logger.error("Failed to validate user" + sql);
		} finally {
			if (results != null) {
				results.close();
			}
			GetJDBCConnection.shutdown();
		}

		return id;
	}

}
