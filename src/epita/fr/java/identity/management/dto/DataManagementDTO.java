package epita.fr.java.identity.management.dto;

import epita.fr.java.identity.management.common.core.IdentityManagement;
import epita.fr.java.identity.management.common.core.UserDetails;
import epita.fr.java.identity.management.common.core.UserManagementVO;

/**
 * Class used to transform object while Serialisation and deserialisation
 * 
 * @author Bhrigu Mahajan
 *
 */
public class DataManagementDTO {

	private DataManagementDTO() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * transform UI to Data object
	 * 
	 * @param userManagementVO
	 * @return
	 */
	public static IdentityManagement transformVOToIdentity(UserManagementVO userManagementVO) {

		IdentityManagement identityManagement = new IdentityManagement();
		identityManagement.setAttribute(userManagementVO.getAttribute());
		identityManagement.setDisplayName(userManagementVO.getDisplayName());
		identityManagement.setEmail(userManagementVO.getEmail());
		identityManagement.setUid(userManagementVO.getUsername().concat("-").concat(userManagementVO.getEmail()));

		return identityManagement;
	}

	/**
	 * transform UI to Data object
	 * 
	 * @param userManagementVO
	 * @return
	 */
	public static IdentityManagement transformVOToUpdateIdentity(UserManagementVO userManagementVO) {

		IdentityManagement identityManagement = new IdentityManagement();
		identityManagement.setAttribute(userManagementVO.getAttribute());
		identityManagement.setDisplayName(userManagementVO.getDisplayName());
		identityManagement.setEmail(userManagementVO.getEmail());
		identityManagement.setUid(userManagementVO.getUid());

		return identityManagement;
	}

	/**
	 * transform UI to Data object
	 * 
	 * @param userManagementVO
	 * @return
	 */
	public static UserDetails transformVOToUserDetail(UserManagementVO userManagementVO) {

		UserDetails userDetails = new UserDetails();
		userDetails.setUsername(userManagementVO.getUsername());
		userDetails.setPassword(userManagementVO.getPassword());
		userDetails.setUid(userManagementVO.getUsername().concat("-").concat(userManagementVO.getEmail()));

		return userDetails;
	}
}
