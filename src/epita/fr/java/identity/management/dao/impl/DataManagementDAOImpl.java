package epita.fr.java.identity.management.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import epita.fr.java.identity.management.common.core.IdentityManagement;
import epita.fr.java.identity.management.common.core.UserDetails;
import epita.fr.java.identity.management.dao.IDataManagementDAO;
import epita.fr.java.identity.management.database.connection.GetJDBCConnection;
import epita.fr.java.identity.management.exceptions.DataAccessException;

/**
 * @author Bhrigu Mahajan
 *
 */
public class DataManagementDAOImpl implements IDataManagementDAO {
	private static final Logger logger = Logger.getLogger(DataManagementDAOImpl.class);

	GetJDBCConnection getConnection = new GetJDBCConnection();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.dao.IDataManagementDAO#search(epita.fr.java
	 * .identity.management.authentication.IdentityManagement)
	 */
	@Override
	public List<IdentityManagement> search(IdentityManagement dataIdentityManagementVO) throws DataAccessException {

		List<IdentityManagement> dataIdentityManagementVOs = new ArrayList<>();

		return dataIdentityManagementVOs;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.dao.IDataManagementDAO#createUser(epita.fr.
	 * java.identity.management.authentication.UserDetails)
	 */
	@Override
	public void createUser(UserDetails userDetails) throws DataAccessException {
		getConnection.insertUserDetails(userDetails);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see epita.fr.java.identity.management.dao.IDataManagementDAO#getAll()
	 */
	@Override
	public List<IdentityManagement> getAll() throws DataAccessException {
		List<IdentityManagement> identityManagementVO = null;
		try {
			identityManagementVO = getConnection.selectRecords();
		} catch (Exception e) {
			logger.error("Failed to get result" + e);
		}
		return identityManagementVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.dao.IDataManagementDAO#getById(java.lang.
	 * String)
	 */
	@Override
	public IdentityManagement getById(String id) throws DataAccessException {
		IdentityManagement identityManagementVO = null;
		try {
			identityManagementVO = getConnection.selectRecordById(id);
		} catch (Exception e) {
			logger.error("Failed to get result" + e);
		}
		return identityManagementVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.dao.IDataManagementDAO#saveIdentityUser(
	 * epita.fr.java.identity.management.authentication.IdentityManagement)
	 */
	@Override
	public void saveIdentityUser(IdentityManagement identityManagement) throws DataAccessException {
		try {
			getConnection.insertRecords(identityManagement);
		} catch (Exception e) {
			logger.error("Failed to save result" + e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.dao.IDataManagementDAO#saveUserDetails(
	 * epita.fr.java.identity.management.authentication.UserDetails)
	 */
	@Override
	public void saveUserDetails(UserDetails userDetails) throws DataAccessException {
		try {
			getConnection.insertUserDetails(userDetails);
		} catch (Exception e) {
			logger.error("Failed to save result" + e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.dao.IDataManagementDAO#updateById(epita.fr.
	 * java.identity.management.authentication.IdentityManagement)
	 */
	@Override
	public void updateById(IdentityManagement identityManagement) throws DataAccessException {
		try {
			getConnection.updateRecords(identityManagement);
		} catch (Exception e) {
			logger.error("Failed to update result" + e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.dao.IDataManagementDAO#deleteById(java.lang
	 * .String)
	 */
	@Override
	public void deleteById(String uid) throws DataAccessException {
		try {
			getConnection.deleteRecord(uid);
		} catch (Exception e) {
			logger.error("Failed to delete result" + e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.dao.IDataManagementDAO#searchRecords(java.
	 * lang.String)
	 */
	@Override
	public List<IdentityManagement> searchRecords(String searchValue) throws DataAccessException {
		List<IdentityManagement> identityManagement = null;
		try {
			identityManagement = getConnection.searchRecords(searchValue);
		} catch (Exception e) {
			logger.error("Failed to delete result" + e);
		}
		return identityManagement;
	}

}
