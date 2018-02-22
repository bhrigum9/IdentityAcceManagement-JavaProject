package epita.fr.java.identity.management.exceptions;

/**
 * Class to handle Handler Exceptions
 * 
 * @author Bhrigu Mahajan
 *
 */
public class HandlerException extends IdentityExceptions {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public HandlerException(int exceptionCode, String message, Throwable cause) {
		super(exceptionCode, message, cause);
	}

	public HandlerException(int exceptionCode, String message) {
		super(exceptionCode, message);
	}

	public HandlerException(int exceptionCode, Throwable cause) {
		super(exceptionCode, cause);
	}
}
