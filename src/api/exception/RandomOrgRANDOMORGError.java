package api.exception;

/** Exception raised by the RandomOrgClient class when the server
 ** returns a RANDOM.ORG Error.
 **
 ** @see https://api.random.org/json-rpc/1/error-codes
 **/
public class RandomOrgRANDOMORGError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Constructs a new exception with the specified detail message.
	 **
	 ** @param message @see java.lang.Exception#Exception(java.lang.String) 
	 **/
	public RandomOrgRANDOMORGError(String message) {
		super(message);
	}
}