package net.dbtc.exception;

public class ScheduleConflictException extends RuntimeException {
	
	public ScheduleConflictException() {
		super();
	}
	
	public ScheduleConflictException(String msg) {
		super(msg);
	}
	
}
