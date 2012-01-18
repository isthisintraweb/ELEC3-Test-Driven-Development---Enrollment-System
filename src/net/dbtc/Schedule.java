package net.dbtc;

public class Schedule {
	
	private Day day;
	private Hour hour;
	
	public Schedule(Day day, Hour hour) {
		this.day = day;
		this.hour = hour;
	}
	
	public Day getDay() {
		return day;
	}
	
	public Hour getHour() {
		return hour;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		if (day == Day.MONDAY_THURSDAY) {
			buffer.append("Mon/Thu ");
		} else if (day == Day.TUESDAY_FRIDAY) {
			buffer.append("Tue/Fri ");
		} else if (day == Day.WEDNESDAY_SATURDAY) {
			buffer.append("Wed/Sat ");
		}
		
		if (hour == Hour.AM0830_AM1000) {
			buffer.append("08:30AM-10:00AM");
		} else if (hour == Hour.AM1000_AM1130) {
			buffer.append("10:00AM-11:30AM");
		} else if (hour == Hour.AM1130_PM0100) {
			buffer.append("11:30AM-01:00PM");
		} else if (hour == Hour.PM0100_PM0230) {
			buffer.append("01:00PM-02:30PM");
		} else if (hour == Hour.PM0230_PM0400) {
			buffer.append("02:30PM-04:00PM");
		} else if (hour == Hour.PM0400_PM0530) {
			buffer.append("04:00PM-05:30PM");
		}
		
		return buffer.toString();
	}
	
	@Override
	public int hashCode() {
		return day.hashCode() * hour.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Schedule)) {
			return false;
		}
		
		Schedule sched = (Schedule)obj;
		if ((this.hashCode() == sched.hashCode()) &&
			(this.day).equals(sched.getDay()) &&
			(this.hour).equals(sched.getHour())) {
			return true;
		}
		
		return false;
	}
	
}
