package epita.fr.java.identity.management.exceptions;

/**
 * Class to handle DAO Exceptions
 * 
 * @author Bhrigu Mahajan
 *
 */
public class DataAccessException extends IdentityExceptions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAccessException(int exceptionCode, String message) {
		super(exceptionCode, message);
	}

	public DataAccessException(int exceptionCode, String message, Throwable cause) {
		super(exceptionCode, message, cause);
	}

	public DataAccessException(int exceptionCode, Throwable cause) {
		super(exceptionCode, cause);
	}

}
