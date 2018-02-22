/**
 * 
 */
package epita.fr.java.identity.management.database.connection;

import java.sql.SQLException;
import java.util.List;

import epita.fr.java.identity.management.common.core.ReadPropertiesFile;
import epita.fr.java.identity.management.constants.IdentityConstants;

/**
 * CLass is used to prepare SQL queries at runtime by methods provided
 * 
 * @author Bhrigu Mahajan
 *
 */
/*
 * This class is used to generate SQL queries at runtime
 */
public class PrepareSQLQueries {

	private static String identityTable = ReadPropertiesFile.readProperties().get("tableName");
	private static String userTable = ReadPropertiesFile.readProperties().get("userTable");

	private PrepareSQLQueries() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Prepare Select Statement
	 * 
	 * @return
	 */
	public static StringBuilder prepareSelectStatement() {
		StringBuilder sb = new StringBuilder();
		sb.append(ReadPropertiesFile.readProperties().get("select"));
		sb.append(IdentityConstants.SPACE);
		sb.append(identityTable);
		sb.append(IdentityConstants.SPACE);
		return sb;
	}

	/**
	 * Prepare Select Statement for login authentication
	 * 
	 * @return
	 */
	public static StringBuilder prepareAuthSelectStatement() {
		StringBuilder sb = new StringBuilder();
		sb.append(ReadPropertiesFile.readProperties().get("select"));
		sb.append(IdentityConstants.SPACE);
		sb.append(userTable);
		sb.append(IdentityConstants.SPACE);
		return sb;
	}

	/**
	 * Prepare where clause Statement
	 * 
	 * @param value
	 * @return
	 */
	public static StringBuilder prepareWhereClauseId(String value) {
		StringBuilder sb = new StringBuilder();
		sb.append(ReadPropertiesFile.readProperties().get("where"));
		sb.append(IdentityConstants.EQUAL);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(value);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		return sb;
	}

	/**
	 * Prepare Insert Statement
	 * 
	 * @param uid
	 * @param display_name
	 * @param email
	 * @param attribute
	 * @return
	 */
	public static StringBuilder prepareInsertStatement(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append(ReadPropertiesFile.readProperties().get("insert"));
		sb.append(IdentityConstants.SPACE);
		sb.append(tableName);
		sb.append(IdentityConstants.SPACE);
		sb.append(ReadPropertiesFile.readProperties().get("values"));
		sb.append(IdentityConstants.SPACE);
		sb.append(IdentityConstants.OPEN_PARENTHESIS);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		return sb;
	}

	/**
	 * Prepare Select Statement for Identity table
	 * 
	 * @param uid
	 * @param display_name
	 * @param email
	 * @param attribute
	 * @param sb
	 * @return
	 */
	public static StringBuilder insertIdentityStatement(String displayName, String email, String attribute,
			String uid) {
		StringBuilder sb = new StringBuilder();
		sb.append(displayName);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.COMMA);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(email);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.COMMA);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(attribute);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.COMMA);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(uid);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.CLOSE_PARENTHESIS);

		return sb;
	}

	/**
	 * Prepare Insert Statement for user table
	 * 
	 * @param uid
	 * @param userame
	 * @param password
	 * @return
	 */
	public static StringBuilder insertUserDetailStatement(String uid, String userame, String password) {
		StringBuilder sb = new StringBuilder();
		sb.append(userame);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.COMMA);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(password);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.COMMA);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(uid);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.CLOSE_PARENTHESIS);

		return sb;
	}

	/**
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public static StringBuilder insertUserDetailStatement(String user, String password) {
		StringBuilder sb = new StringBuilder();
		sb.append(user);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.COMMA);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(password);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.CLOSE_PARENTHESIS);

		return sb;
	}

	/**
	 * 
	 * @return
	 */
	public static StringBuilder prepareDeleteStatement() {
		StringBuilder sb = new StringBuilder();
		sb.append(ReadPropertiesFile.readProperties().get("delete"));
		sb.append(IdentityConstants.SPACE);
		sb.append(userTable);
		sb.append(IdentityConstants.SPACE);
		return sb;
	}

	/**
	 * Prepare update Statement for user table
	 * 
	 * @param uid
	 * @param display_name
	 * @param email
	 * @param attribute
	 * @return
	 * @throws SQLException
	 */
	public static StringBuilder prepareUpdateStatement(String displayName, String email, String attribute)
			throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append(ReadPropertiesFile.readProperties().get("update"));
		sb.append(IdentityConstants.SPACE);
		sb.append(identityTable);
		sb.append(IdentityConstants.SPACE);
		sb.append(ReadPropertiesFile.readProperties().get("set"));
		sb.append(IdentityConstants.SPACE);
		List<String> columNames = GetJDBCConnection.getColumnList();

		sb.append(columNames.get(0));
		sb.append(IdentityConstants.EQUAL);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(displayName);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.COMMA);

		sb.append(columNames.get(1));
		sb.append(IdentityConstants.EQUAL);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(email);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.COMMA);

		sb.append(IdentityConstants.DOUBLE_QUOTES);
		sb.append(columNames.get(2));
		sb.append(IdentityConstants.DOUBLE_QUOTES);
		sb.append(IdentityConstants.EQUAL);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(attribute);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(IdentityConstants.SPACE);

		return sb;
	}

	/**
	 * Prepare select Statement for search
	 * 
	 * @param searchString
	 * @return
	 */
	public static StringBuilder prepareSearchStatement(String searchString) {
		StringBuilder sb = new StringBuilder();
		sb.append(ReadPropertiesFile.readProperties().get("select"));
		sb.append(IdentityConstants.SPACE);
		sb.append(identityTable);
		sb.append(IdentityConstants.SPACE);
		sb.append(ReadPropertiesFile.readProperties().get("whereName"));
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(searchString);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(ReadPropertiesFile.readProperties().get("orEmail"));
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(searchString);
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(ReadPropertiesFile.readProperties().get("orAttribute"));
		sb.append(IdentityConstants.SINGLE_QUOTE);
		sb.append(searchString);
		sb.append(IdentityConstants.SINGLE_QUOTE);

		return sb;
	}

}
