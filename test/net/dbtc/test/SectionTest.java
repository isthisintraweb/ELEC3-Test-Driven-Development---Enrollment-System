package net.dbtc.test;

import net.dbtc.Day;
import net.dbtc.Hour;
import net.dbtc.Level;
import net.dbtc.Schedule;
import net.dbtc.Section;
import net.dbtc.Subject;
import net.dbtc.Teacher;
import net.dbtc.exception.ScheduleConflictException;

import org.junit.Assert;
import org.junit.Test;

public class SectionTest {
	
	@Test
	public void storeAndRetrieveAttributes() {
		Subject math = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Schedule sched = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath1 = new Section(math, alvinTeruel, sched);
		
		Assert.assertEquals(math, csMath1.getSubject());
		Assert.assertEquals(alvinTeruel, csMath1.getTeacher());
		Assert.assertEquals(sched, csMath1.getSchedule());
	}
	
	@Test
	public void createSectionWithoutConflictOfScheduleInTeacher() {
		Subject math = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Schedule sched = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath1 = new Section(math, alvinTeruel, sched);
	}
	
	@Test (expected = ScheduleConflictException.class)
	public void createSectionWithConflictOfScheduleInTeacher() {
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		
		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Schedule schedCSMath1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath1 = new Section(math1, alvinTeruel, schedCSMath1);
		
		Subject engl1 = new Subject("ENGL1", null, Level.UNDERGRADUATE);
		Schedule schedCSEngl1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csEngl1 = new Section(engl1, alvinTeruel, schedCSEngl1);
	}

	@Test
	public void returnProperStringFormat() {
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Schedule schedCSMath1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath1 = new Section(math1, alvinTeruel, schedCSMath1);
		
		Assert.assertEquals("MATH1 Alvin Teruel Mon/Thu 08:30AM-10:00AM", csMath1.toString());
	}
	
	@Test
	public void checkEquality() {
		Teacher alvinTeruel1 = new Teacher("Alvin", "Teruel", "2-07-123");
		Teacher alvinTeruel2 = new Teacher("Alvin", "Teruel", "2-07-123");
		Teacher alvinTeruel3 = new Teacher("Alvin", "Teruel", "2-07-123");
		
		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Schedule schedCSMath1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);

		Section xx = new Section(math1, alvinTeruel1, schedCSMath1);
		Section yy = new Section(math1, alvinTeruel2, schedCSMath1);
		Section zz = new Section(math1, alvinTeruel3, schedCSMath1);
		
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
