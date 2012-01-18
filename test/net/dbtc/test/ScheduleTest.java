package net.dbtc.test;

import net.dbtc.Day;
import net.dbtc.Hour;
import net.dbtc.Schedule;

import org.junit.Assert;
import org.junit.Test;

public class ScheduleTest {
	
	@Test
	public void storeAndRetrieveAttributes() {
		Schedule sched = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		
		Assert.assertEquals(Day.MONDAY_THURSDAY, sched.getDay());
		Assert.assertEquals(Hour.AM0830_AM1000, sched.getHour());
	}

	@Test
	public void returnProperStringFormat() {
		Schedule sched = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		
		Assert.assertEquals("Mon/Thu 08:30AM-10:00AM", sched.toString());
	}

	@Test
	public void checkEquality() {
		Schedule xx = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Schedule yy = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Schedule zz = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		
		// reflexive
		Assert.assertTrue(xx.equals(xx));
		
		// symmetric
		Assert.assertTrue(xx.equals(yy));
		Assert.assertTrue(yy.equals(xx));
		
		// transitive
		Assert.assertTrue(xx.equals(yy));
		Assert.assertTrue(yy.equals(zz));
		Assert.assertTrue(xx.equals(zz));
		
		// null
		Assert.assertFalse(xx.equals(null));
	}
	
}
