package fr.epita.iam.services.test;

import java.util.List;

import epita.fr.java.identity.management.common.core.IdentityManagement;
import epita.fr.java.identity.management.common.core.UserManagementVO;
import epita.fr.java.identity.management.exceptions.HandlerException;
import epita.fr.java.identity.management.handler.IDataManagementHandler;
import epita.fr.java.identity.management.handler.impl.DataManagementHandlerImpl;

/**
 * Class test all methods used in applications
 * 
 * @author Bhrigu Mahajan
 *
 */
public class TestFileOperations {

	/**
	 * 
	 * @param userManagementVO
	 * @throws HandlerException
	 */
	public void testSaveUserDetails(UserManagementVO userManagementVO) throws HandlerException {
		UserManagementVO userDetails = new UserManagementVO();

		userDetails.setDisplayName(userManagementVO.getDisplayName());
		userDetails.setEmail(userManagementVO.getEmail());
		userDetails.setAttribute(userManagementVO.getAttribute());
		userDetails.setUsername(userManagementVO.getUsername());
		userDetails.setPassword(userManagementVO.getPassword());

		IDataManagementHandler handler = new DataManagementHandlerImpl();
		handler.createUser(userDetails);
	}

	/**
	 * 
	 * @param userManagementVO
	 * @throws HandlerException
	 */
	public void testUpdateUserDetails(UserManagementVO userManagementVO) throws HandlerException {
		UserManagementVO userDetails = new UserManagementVO();

		userDetails.setDisplayName(userManagementVO.getDisplayName());
		userDetails.setEmail(userManagementVO.getEmail());
		userDetails.setAttribute(userManagementVO.getAttribute());

		IDataManagementHandler handler = new DataManagementHandlerImpl();
		handler.updateById(userDetails);
	}

	/**
	 * 
	 * @param identityManagement
	 * @throws HandlerException
	 */
	public void testDeleteUserDetails(IdentityManagement identityManagement) throws HandlerException {
		IDataManagementHandler handler = new DataManagementHandlerImpl();
		handler.deleteById(identityManagement.getUid());
	}

	/**
	 * 
	 * @param identityManagement
	 * @return
	 * @throws HandlerException
	 */
	public List<IdentityManagement> testGetUserDetails(List<IdentityManagement> identityManagement)
			throws HandlerException {
		IDataManagementHandler handler = new DataManagementHandlerImpl();
		identityManagement = handler.getAll();
		return identityManagement;
	}

	/**
	 * 
	 * @param test
	 * @return
	 * @throws HandlerException
	 */
	public List<IdentityManagement> testSearchUser(String test) throws HandlerException {
		IDataManagementHandler handler = new DataManagementHandlerImpl();
		List<IdentityManagement> identityManagement = handler.search(test);
		return identityManagement;
	}

}
