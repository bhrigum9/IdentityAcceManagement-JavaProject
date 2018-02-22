package fr.epita.iam.services.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import epita.fr.java.identity.management.common.core.IdentityManagement;
import epita.fr.java.identity.management.common.core.UserManagementVO;
import epita.fr.java.identity.management.dto.DataManagementDTO;
import epita.fr.java.identity.management.exceptions.HandlerException;
import epita.fr.java.identity.management.handler.impl.DataManagementHandlerImpl;

/**
 * Launcher class for to Test application
 * 
 * @author Bhrigu Mahajan
 *
 */
public class TestLaucher {
	private static final Logger logger = Logger.getLogger(DataManagementHandlerImpl.class);

	/**
	 * Main Launcher method of Test class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TestFileOperations fileOperations = new TestFileOperations();
		IdentityManagement identityManagement = DataManagementDTO.transformVOToIdentity(prepareUserManagementObject());
		List<IdentityManagement> identityManagements = new ArrayList<>();
		identityManagements.add(identityManagement);
		try {
			fileOperations.testSaveUserDetails(prepareUserManagementObject());
			fileOperations.testGetUserDetails(identityManagements);
			fileOperations.testSearchUser(prepareUserManagementObject().getDisplayName());
			fileOperations.testDeleteUserDetails(identityManagement);
			fileOperations.testUpdateUserDetails(prepareUserManagementObject());
		} catch (HandlerException e) {
			logger.error("Test Case Failed" + e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return object of user management class for testing purpose only
	 */
	public static UserManagementVO prepareUserManagementObject() {
		UserManagementVO managementVO = new UserManagementVO();
		managementVO.setDisplayName("test name");
		managementVO.setEmail("test@testmail.com");
		managementVO.setAttribute("testing values");
		managementVO.setPassword("testing");
		managementVO.setUsername("testuser");

		return managementVO;

	}
}
