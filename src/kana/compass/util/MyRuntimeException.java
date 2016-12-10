package kana.compass.util;

public class MyRuntimeException extends RuntimeException {

	/**
	 * generated serial version UID.
	 */
	private static final long serialVersionUID = -1318738786306253132L;
	
	public MyRuntimeException(String message) {
		super(message);
	}
	public MyRuntimeException(Throwable cause) {
		super(cause);
	}
	public MyRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
