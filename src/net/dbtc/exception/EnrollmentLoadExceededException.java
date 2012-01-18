package net.dbtc.exception;

public class EnrollmentLoadExceededException extends RuntimeException {
	
	public EnrollmentLoadExceededException() {
		super();
	}
	
	public EnrollmentLoadExceededException(String msg) {
		super(msg);
	}
	
}
