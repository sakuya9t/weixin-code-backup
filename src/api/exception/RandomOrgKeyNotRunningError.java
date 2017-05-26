package api.exception;

/** Exception raised by the RandomOrgClient class when its API key
 ** has been stopped. Requests will not complete while API key is 
 ** in the stopped state.
 **/
public class RandomOrgKeyNotRunningError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Constructs a new exception with the specified detail message.
	 **
	 ** @param message @see java.lang.Exception#Exception(java.lang.String) 
	 **/
	public RandomOrgKeyNotRunningError(String message) {
		super(message);
	}
}