package epita.fr.java.identity.management.dao;

import java.util.List;

import epita.fr.java.identity.management.common.core.IdentityManagement;
import epita.fr.java.identity.management.common.core.UserDetails;
import epita.fr.java.identity.management.exceptions.DataAccessException;

/**
 * DAO Interface to store records in database
 * 
 * @author Bhrigu Mahajan
 *
 */
public interface IDataManagementDAO {
	/**
	 * method used for search entities
	 * 
	 * @param dataIdentityManagementVO
	 * @return
	 */
	public List<IdentityManagement> search(IdentityManagement dataIdentityManagementVO) throws DataAccessException;

	/**
	 * method used to create entities
	 * 
	 * @param userDetails
	 * @throws DataAccessException
	 */
	public void createUser(UserDetails userDetails) throws DataAccessException;

	/**
	 * 
	 * method used for get all entities from database
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<IdentityManagement> getAll() throws DataAccessException;

	/**
	 * method used to fetch single entity on basis of ID
	 * 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public IdentityManagement getById(String id) throws DataAccessException;

	/**
	 * method used to entity of IdentityManagement
	 * 
	 * @param identityManagement
	 * @throws DataAccessException
	 */
	public void saveIdentityUser(IdentityManagement identityManagement) throws DataAccessException;

	/**
	 * 
	 * @param userDetails
	 * @throws DataAccessException
	 */
	public void saveUserDetails(UserDetails userDetails) throws DataAccessException;

	/**
	 * method used to update entity of IdentityManagement
	 * 
	 * @param identityManagement
	 * @throws DataAccessException
	 */
	public void updateById(IdentityManagement identityManagement) throws DataAccessException;

	/**
	 * method used to delete complete record entity of User
	 * 
	 * @param uid
	 * @throws DataAccessException
	 */
	public void deleteById(String uid) throws DataAccessException;

	/**
	 * method used to serach entity of IdentityManagement
	 * 
	 * @param searchValue
	 * @return
	 * @throws DataAccessException
	 */
	public List<IdentityManagement> searchRecords(String searchValue) throws DataAccessException;

}
