package net.dbtc.test;

import java.math.BigDecimal;

import net.dbtc.Day;
import net.dbtc.Hour;
import net.dbtc.Level;
import net.dbtc.Schedule;
import net.dbtc.Scholarship;
import net.dbtc.Section;
import net.dbtc.Standing;
import net.dbtc.Student;
import net.dbtc.Subject;
import net.dbtc.Teacher;
import net.dbtc.exception.UnderMinimumLoadException;

import org.junit.Assert;
import org.junit.Test;

public class TuitionFeeTest {
	
	@Test
	public void calculateTuitionFeeNotScholar() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FIRST_YEAR, Scholarship.NOT_SCHOLAR);

		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Subject math2 = new Subject("MATH2", null, Level.UNDERGRADUATE);
		Subject math3 = new Subject("MATH3", null, Level.UNDERGRADUATE);
		Subject math4 = new Subject("MATH4", null, Level.UNDERGRADUATE);
		Subject math5 = new Subject("MATH5", null, Level.UNDERGRADUATE);
		
		Teacher teacher1 = new Teacher("Alvin", "Teruel1", "3-07-121");
		Teacher teacher2 = new Teacher("Alvin", "Teruel2", "3-07-122");
		Teacher teacher3 = new Teacher("Alvin", "Teruel3", "3-07-123");
		Teacher teacher4 = new Teacher("Alvin", "Teruel4", "3-07-124");
		Teacher teacher5 = new Teacher("Alvin", "Teruel5", "3-07-125");
		
		Schedule sched1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Schedule sched2 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM0830_AM1000);
		Schedule sched3 = new Schedule(Day.WEDNESDAY_SATURDAY, Hour.AM0830_AM1000);
		Schedule sched4 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM1000_AM1130);
		Schedule sched5 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM1000_AM1130);
		
		Section section1 = new Section(math1, teacher1, sched1);
		Section section2 = new Section(math2, teacher2, sched2);
		Section section3 = new Section(math3, teacher3, sched3);
		Section section4 = new Section(math4, teacher4, sched4);
		Section section5 = new Section(math5, teacher5, sched5);
		
		lester.enroll(section1);
		lester.enroll(section2);
		lester.enroll(section3);
		lester.enroll(section4);
		lester.enroll(section5);
		
		BigDecimal expectedTuition = new BigDecimal("12000");
		
		Assert.assertEquals(expectedTuition, lester.calculateTuitionFee());
	}
	
	@Test
	public void calculateTuitionFeeHalfScholar() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FIRST_YEAR, Scholarship.HALF_SCHOLAR);

		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Subject math2 = new Subject("MATH2", null, Level.UNDERGRADUATE);
		Subject math3 = new Subject("MATH3", null, Level.UNDERGRADUATE);
		Subject math4 = new Subject("MATH4", null, Level.UNDERGRADUATE);
		Subject math5 = new Subject("MATH5", null, Level.UNDERGRADUATE);
		
		Teacher teacher1 = new Teacher("Alvin", "Teruel1", "3-07-121");
		Teacher teacher2 = new Teacher("Alvin", "Teruel2", "3-07-122");
		Teacher teacher3 = new Teacher("Alvin", "Teruel3", "3-07-123");
		Teacher teacher4 = new Teacher("Alvin", "Teruel4", "3-07-124");
		Teacher teacher5 = new Teacher("Alvin", "Teruel5", "3-07-125");
		
		Schedule sched1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Schedule sched2 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM0830_AM1000);
		Schedule sched3 = new Schedule(Day.WEDNESDAY_SATURDAY, Hour.AM0830_AM1000);
		Schedule sched4 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM1000_AM1130);
		Schedule sched5 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM1000_AM1130);
		
		Section section1 = new Section(math1, teacher1, sched1);
		Section section2 = new Section(math2, teacher2, sched2);
		Section section3 = new Section(math3, teacher3, sched3);
		Section section4 = new Section(math4, teacher4, sched4);
		Section section5 = new Section(math5, teacher5, sched5);
		
		lester.enroll(section1);
		lester.enroll(section2);
		lester.enroll(section3);
		lester.enroll(section4);
		lester.enroll(section5);
		
		BigDecimal expectedTuition = new BigDecimal("7000.0");
		
		Assert.assertEquals(expectedTuition, lester.calculateTuitionFee());
	}
	
	@Test
	public void calculateTuitionFeeFullScholar() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FIRST_YEAR, Scholarship.FULL_SCHOLAR);

		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Subject math2 = new Subject("MATH2", null, Level.UNDERGRADUATE);
		Subject math3 = new Subject("MATH3", null, Level.UNDERGRADUATE);
		Subject math4 = new Subject("MATH4", null, Level.UNDERGRADUATE);
		Subject math5 = new Subject("MATH5", null, Level.UNDERGRADUATE);
		
		Teacher teacher1 = new Teacher("Alvin", "Teruel1", "3-07-121");
		Teacher teacher2 = new Teacher("Alvin", "Teruel2", "3-07-122");
		Teacher teacher3 = new Teacher("Alvin", "Teruel3", "3-07-123");
		Teacher teacher4 = new Teacher("Alvin", "Teruel4", "3-07-124");
		Teacher teacher5 = new Teacher("Alvin", "Teruel5", "3-07-125");
		
		Schedule sched1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Schedule sched2 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM0830_AM1000);
		Schedule sched3 = new Schedule(Day.WEDNESDAY_SATURDAY, Hour.AM0830_AM1000);
		Schedule sched4 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM1000_AM1130);
		Schedule sched5 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM1000_AM1130);
		
		Section section1 = new Section(math1, teacher1, sched1);
		Section section2 = new Section(math2, teacher2, sched2);
		Section section3 = new Section(math3, teacher3, sched3);
		Section section4 = new Section(math4, teacher4, sched4);
		Section section5 = new Section(math5, teacher5, sched5);
		
		lester.enroll(section1);
		lester.enroll(section2);
		lester.enroll(section3);
		lester.enroll(section4);
		lester.enroll(section5);
		
		BigDecimal expectedTuition = new BigDecimal("2000");
		
		Assert.assertEquals(expectedTuition, lester.calculateTuitionFee());
	}
	
	@Test (expected = UnderMinimumLoadException.class)
	public void calculateTuitionFeeUnderMinimumLoad() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FIRST_YEAR, Scholarship.NOT_SCHOLAR);

		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Subject math2 = new Subject("MATH2", null, Level.UNDERGRADUATE);
		
		Teacher teacher1 = new Teacher("Alvin", "Teruel1", "3-07-121");
		Teacher teacher2 = new Teacher("Alvin", "Teruel2", "3-07-122");
		
		Schedule sched1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Schedule sched2 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM0830_AM1000);
		
		Section section1 = new Section(math1, teacher1, sched1);
		Section section2 = new Section(math2, teacher2, sched2);
		
		lester.enroll(section1);
		lester.enroll(section2);
		
		BigDecimal expectedTuition = new BigDecimal("6000");
		
		Assert.assertEquals(expectedTuition, lester.calculateTuitionFee());
	}
	
	@Test
	public void calculateTuitionFeeFourthYearNoMinimumLoad() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);

		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Subject math2 = new Subject("MATH2", null, Level.UNDERGRADUATE);
		
		Teacher teacher1 = new Teacher("Alvin", "Teruel1", "3-07-121");
		Teacher teacher2 = new Teacher("Alvin", "Teruel2", "3-07-122");
		
		Schedule sched1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Schedule sched2 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM0830_AM1000);
		
		Section section1 = new Section(math1, teacher1, sched1);
		Section section2 = new Section(math2, teacher2, sched2);
		
		lester.enroll(section1);
		lester.enroll(section2);
		
		BigDecimal expectedTuition = new BigDecimal("6000");
		
		Assert.assertEquals(expectedTuition, lester.calculateTuitionFee());
	}
	
}
