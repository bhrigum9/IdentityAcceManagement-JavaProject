package epita.fr.java.identity.management.exceptions;

/**
 * Parent class of all exception classes in application and child of Exception
 * class
 * 
 * @author Bhrigu Mahajan
 *
 */
public class IdentityExceptions extends java.lang.Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int code = IdentityExceptionsCodes.DEFAULT_EXCEPTION_CODE;

	public IdentityExceptions(int exceptionCode, String message) {
		super(message);
		this.code = exceptionCode;
	}

	public IdentityExceptions(int exceptionCode, String message, Throwable cause) {
		super(message, cause);
		this.code = exceptionCode;
	}

	public IdentityExceptions(int exceptionCode, Throwable cause) {
		super(cause);
		this.code = exceptionCode;
	}

	/** */

	/** */
	public int getCode() {
		return this.code;
	}

}
