package net.dbtc.exception;

public class UnderMinimumLoadException extends RuntimeException {
	
	public UnderMinimumLoadException() {
		super();
	}
	
	public UnderMinimumLoadException(String msg) {
		super(msg);
	}
	
}
