package epita.fr.java.identity.management.handler;

import java.util.List;

import epita.fr.java.identity.management.common.core.IdentityManagement;
import epita.fr.java.identity.management.common.core.UserManagementVO;
import epita.fr.java.identity.management.exceptions.HandlerException;

/**
 * Class used to Handle DAO Object and to transform it for serialisation
 * 
 * @author Bhrigu Mahajan
 *
 */
public interface IDataManagementHandler {
	/**
	 * method used for search entities
	 * 
	 * @param dataIdentityManagementVO
	 * @return
	 * @throws HandlerException
	 */
	public List<IdentityManagement> search(String searchValue) throws HandlerException;

	/**
	 * * method used for get all entities from database
	 * 
	 * @return
	 * @throws HandlerException
	 */
	public List<IdentityManagement> getAll() throws HandlerException;

	/**
	 * * method used to fetch single entity on basis of ID
	 * 
	 * @param id
	 * @return
	 * @throws HandlerException
	 */
	public IdentityManagement getById(String id) throws HandlerException;

	/**
	 * * method used to create entities
	 * 
	 * @param userManagement
	 * @throws HandlerException
	 */
	public void createUser(UserManagementVO userManagement) throws HandlerException;

	/**
	 * * method used to update entity of IdentityManagement
	 * 
	 * @param userManagement
	 * @throws HandlerException
	 */
	public void updateById(UserManagementVO userManagement) throws HandlerException;

	/**
	 * * method used to delete complete record entity of User
	 * 
	 * @param uid
	 * @throws HandlerException
	 */
	public void deleteById(String uid) throws HandlerException;

}
