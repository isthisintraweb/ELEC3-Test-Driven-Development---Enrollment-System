package net.dbtc.exception;

public class InvalidIDNumberFormatException extends RuntimeException {
	
	public InvalidIDNumberFormatException() {
		super();
	}
	
	public InvalidIDNumberFormatException(String msg) {
		super(msg);
	}
	
}
