package kana.compass.util;

public class MyRuntimeException extends RuntimeException {

	public MyRuntimeException(String message) {
		super(message);
	}
	public MyRuntimeException(Throwable cause) {
		super(cause);
	}

}
