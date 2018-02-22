package epita.fr.java.identity.management.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import epita.fr.java.identity.management.common.core.IdentityManagement;
import epita.fr.java.identity.management.common.core.UserDetails;
import epita.fr.java.identity.management.common.core.UserManagementVO;
import epita.fr.java.identity.management.dao.IDataManagementDAO;
import epita.fr.java.identity.management.dao.impl.DataManagementDAOImpl;
import epita.fr.java.identity.management.dto.DataManagementDTO;
import epita.fr.java.identity.management.exceptions.HandlerException;
import epita.fr.java.identity.management.handler.IDataManagementHandler;

public class DataManagementHandlerImpl implements IDataManagementHandler {
	private static final Logger logger = Logger.getLogger(DataManagementHandlerImpl.class);

	IDataManagementDAO userDao = new DataManagementDAOImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.handler.IDataManagementHandler#search(java.
	 * lang.String)
	 */
	@Override
	public List<IdentityManagement> search(String searchValue) {
		List<IdentityManagement> identityManagements = new ArrayList<>();
		try {
			identityManagements = userDao.searchRecords(searchValue);
		} catch (Exception e) {
			logger.error("Failed to get search records" + e);

		}
		return identityManagements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see epita.fr.java.identity.management.handler.IDataManagementHadler#getAll()
	 */
	@Override
	public List<IdentityManagement> getAll() throws HandlerException {
		List<IdentityManagement> identityManagements = new ArrayList<>();
		try {
			identityManagements = userDao.getAll();
		} catch (Exception e) {
			logger.error("Failed to get details" + e);

		}
		return identityManagements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.handler.IDataManagementHadler#getById(java.
	 * lang.String)
	 */
	@Override
	public IdentityManagement getById(String id) throws HandlerException {
		IdentityManagement identityManagement = new IdentityManagement();
		try {
			identityManagement = userDao.getById(id);
		} catch (Exception e) {
			logger.error("Failed to get details" + e);
		}
		return identityManagement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.handler.IDataManagementHadler#createUser(
	 * epita.fr.java.identity.management.vo.UserManagementVO)
	 */
	@Override
	public void createUser(UserManagementVO userManagementVO) throws HandlerException {
		IdentityManagement identityManagement = new IdentityManagement();
		try {
			identityManagement = DataManagementDTO.transformVOToIdentity(userManagementVO);
			UserDetails userDetails = DataManagementDTO.transformVOToUserDetail(userManagementVO);
			userDao.saveUserDetails(userDetails);
			userDao.saveIdentityUser(identityManagement);
		} catch (Exception e) {
			logger.error("Failed to create object" + e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.handler.IDataManagementHadler#updateById(
	 * epita.fr.java.identity.management.vo.UserManagementVO)
	 */
	@Override
	public void updateById(UserManagementVO userManagementVO) throws HandlerException {
		IdentityManagement identityManagement = new IdentityManagement();
		try {
			identityManagement = DataManagementDTO.transformVOToUpdateIdentity(userManagementVO);
			userDao.updateById(identityManagement);
		} catch (Exception e) {
			logger.error("Failed to update" + e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * epita.fr.java.identity.management.handler.IDataManagementHandler#deleteById(
	 * java.lang.String)
	 */
	@Override
	public void deleteById(String uid) throws HandlerException {
		try {
			userDao.deleteById(uid);
		} catch (Exception e) {
			logger.error("Failed to delete" + e);
		}
	}

}
