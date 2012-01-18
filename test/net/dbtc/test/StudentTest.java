package net.dbtc.test;

import java.util.HashSet;
import java.util.Set;

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
import net.dbtc.exception.DuplicateSectionException;
import net.dbtc.exception.EnrollmentLoadExceededException;
import net.dbtc.exception.InvalidIDNumberFormatException;
import net.dbtc.exception.PrerequisiteConflictException;
import net.dbtc.exception.ScheduleConflictException;
import net.dbtc.exception.SectionNoSlotAvailableException;

import org.junit.Assert;
import org.junit.Test;

public class StudentTest {

	@Test
	public void storeAndRetrieveAttributes() {
		Set<Subject> creditedSubjects = new HashSet<Subject>();
		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Subject engl1 = new Subject("ENGL1", null, Level.UNDERGRADUATE);
		creditedSubjects.add(math1);
		creditedSubjects.add(engl1);
		Student lester = new Student("Lester", "Manansala", "3-08-239", creditedSubjects, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		
		Assert.assertEquals("Lester", lester.getFirstName());
		Assert.assertEquals("Manansala", lester.getLastName());
		Assert.assertEquals("Lester Manansala", lester.getFullName());
		Assert.assertEquals("3-08-239", lester.getIDNumber());
		Assert.assertEquals(creditedSubjects, lester.getCreditedSubjects());
	}
	
	@Test
	public void returnProperStringFormat() {
		Set<Subject> creditedSubjects = new HashSet<Subject>();
		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		creditedSubjects.add(math1);
		Student lester = new Student("Lester", "Manansala", "3-08-239", creditedSubjects, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		
		Assert.assertEquals("Lester Manansala 3-08-239", lester.toString());
	}
	
	@Test (expected = InvalidIDNumberFormatException.class)
	public void rejectIncorrectIDNumberFormat() {
		Student lester = null;
		
		lester = new Student("Lester", "Manansala", "-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "3-8-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "3-08-39", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "13-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "3-108-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "a3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "3-a08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "3-08-a239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "-3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "3-08-239-", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester = new Student("Lester", "Manansala", "08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
	}
	
	@Test
	public void acceptCorrectIdNumberFormat() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
	}
	
	@Test
	public void enrollUniqueSection() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		
		Subject math = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Schedule sched = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath1 = new Section(math, alvinTeruel, sched);
		lester.enroll(csMath1);
	}
	
	@Test (expected = DuplicateSectionException.class)
	public void enrollDuplicateSection() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		
		Subject math = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Schedule sched = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath1 = new Section(math, alvinTeruel, sched);
		
		lester.enroll(csMath1);
		lester.enroll(csMath1);
	}
	
	@Test
	public void enrollNoScheduleConflict() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		
		Subject math = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Schedule schedMath = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath1 = new Section(math, alvinTeruel, schedMath);
		lester.enroll(csMath1);
		
		Schedule schedEngl = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM0830_AM1000);
		Section csEngl1 = new Section(math, alvinTeruel, schedEngl);
		lester.enroll(csEngl1);
	}
	
	@Test (expected = ScheduleConflictException.class)
	public void enrollWithScheduleConflict() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		
		Subject math = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Schedule schedMath = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath1 = new Section(math, alvinTeruel, schedMath);
		lester.enroll(csMath1);
		
		Schedule schedEngl = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csEngl1 = new Section(math, alvinTeruel, schedEngl);
		lester.enroll(csEngl1);
	}
	
	@Test
	public void enrollNoPrerequisiteConflict() {
		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Set<Subject> creditedSubjects = new HashSet<Subject>();
		creditedSubjects.add(math1);
		
		Student lester = new Student("Lester", "Manansala", "3-08-239", creditedSubjects, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		
		Subject math2 = new Subject("MATH2", null, Level.UNDERGRADUATE);
		Set<Subject> math2Prereq = new HashSet<Subject>();
		math2Prereq.add(math1);
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Schedule schedMath2 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath2 = new Section(math2, alvinTeruel, schedMath2);
		
		lester.enroll(csMath2);
	}
	
	@Test (expected = PrerequisiteConflictException.class)
	public void enrollWithPrerequisiteConflict() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		
		Subject math1 = new Subject("MATH2", null, Level.UNDERGRADUATE);
		Set<Subject> math2Prereq = new HashSet<Subject>();
		math2Prereq.add(math1);
		Subject math2 = new Subject("MATH2", math2Prereq, Level.UNDERGRADUATE);
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Schedule schedMath2 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath2 = new Section(math2, alvinTeruel, schedMath2);
		
		lester.enroll(csMath2);
	}
	
	@Test (expected = SectionNoSlotAvailableException.class)
	public void enrollSectionWithNoAvailableSlot() {
		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Schedule schedMath1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath1 = new Section(math1, alvinTeruel, schedMath1);
		
		for (int i = 1; i <= Section.MAX_STUDENTS; i++) {
			Student dummyStudent = new Student("Dummy", "Student"+ i, "3-08-12"+ i, null, Standing.FIRST_YEAR, Scholarship.NOT_SCHOLAR);
			dummyStudent.enroll(csMath1);
		}
		
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester.enroll(csMath1);
	}
	
	@Test
	public void enrollSectionWithAvailableSlot() {
		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		Schedule schedMath1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Section csMath1 = new Section(math1, alvinTeruel, schedMath1);
		
		for (int i = 1; i <= Section.MAX_STUDENTS - 1; i++) {
			Student dummyStudent = new Student("Dummy", "Student", "3-08-12"+ i, null, Standing.FIRST_YEAR, Scholarship.NOT_SCHOLAR);
			dummyStudent.enroll(csMath1);
		}
		
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		lester.enroll(csMath1);
	}
	
	@Test (expected = EnrollmentLoadExceededException.class)
	public void enrollSectionWhileFullLoad() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FIRST_YEAR, Scholarship.NOT_SCHOLAR);
		
		Subject subject1 = new Subject("SUB1", null, Level.UNDERGRADUATE);
		Subject subject2 = new Subject("SUB2", null, Level.UNDERGRADUATE);
		Subject subject3 = new Subject("SUB3", null, Level.UNDERGRADUATE);
		Subject subject4 = new Subject("SUB4", null, Level.UNDERGRADUATE);
		Subject subject5 = new Subject("SUB5", null, Level.UNDERGRADUATE);
		Subject subject6 = new Subject("SUB6", null, Level.UNDERGRADUATE);
		
		Teacher teacher1 = new Teacher("Teacher", "1", "2-07-001");
		Teacher teacher2 = new Teacher("Teacher", "2", "2-07-002");
		Teacher teacher3 = new Teacher("Teacher", "3", "2-07-003");
		Teacher teacher4 = new Teacher("Teacher", "4", "2-07-004");
		Teacher teacher5 = new Teacher("Teacher", "5", "2-07-005");
		Teacher teacher6 = new Teacher("Teacher", "6", "2-07-006");
		
		Schedule sched1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Schedule sched2 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM1000_AM1130);
		Schedule sched3 = new Schedule(Day.WEDNESDAY_SATURDAY, Hour.AM1130_PM0100);
		Schedule sched4 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM1130_PM0100);
		Schedule sched5 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM0830_AM1000);
		Schedule sched6 = new Schedule(Day.WEDNESDAY_SATURDAY, Hour.AM0830_AM1000);
		
		Section sec1 = new Section(subject1, teacher1, sched1);
		Section sec2 = new Section(subject2, teacher2, sched2);
		Section sec3 = new Section(subject3, teacher3, sched3);
		Section sec4 = new Section(subject4, teacher4, sched4);
		Section sec5 = new Section(subject5, teacher5, sched5);
		Section sec6 = new Section(subject6, teacher6, sched6);
		
		lester.enroll(sec1);
		lester.enroll(sec2);
		lester.enroll(sec3);
		lester.enroll(sec4);
		lester.enroll(sec5);
		lester.enroll(sec6);
		
		Subject overSubject = new Subject("OVER1", null, Level.UNDERGRADUATE);
		Teacher overTeacher = new Teacher("Over", "Teacher", "2-07-123");
		Schedule overSched = new Schedule(Day.WEDNESDAY_SATURDAY, Hour.PM0100_PM0230);
		Section overSection = new Section(overSubject, overTeacher, overSched);
		
		lester.enroll(overSection);
	}
	
	@Test
	public void enrollSectionNotFullLoad() {
		Student lester = new Student("Lester", "Manansala", "3-08-239", null, Standing.FIRST_YEAR, Scholarship.NOT_SCHOLAR);
		
		Subject subject1 = new Subject("SUB1", null, Level.UNDERGRADUATE);
		Subject subject2 = new Subject("SUB2", null, Level.UNDERGRADUATE);
		Subject subject3 = new Subject("SUB3", null, Level.UNDERGRADUATE);
		Subject subject4 = new Subject("SUB4", null, Level.UNDERGRADUATE);
		Subject subject5 = new Subject("SUB5", null, Level.UNDERGRADUATE);
		
		Teacher teacher1 = new Teacher("Teacher", "1", "2-07-001");
		Teacher teacher2 = new Teacher("Teacher", "2", "2-07-002");
		Teacher teacher3 = new Teacher("Teacher", "3", "2-07-003");
		Teacher teacher4 = new Teacher("Teacher", "4", "2-07-004");
		Teacher teacher5 = new Teacher("Teacher", "5", "2-07-005");
		
		Schedule sched1 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM0830_AM1000);
		Schedule sched2 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM1000_AM1130);
		Schedule sched3 = new Schedule(Day.WEDNESDAY_SATURDAY, Hour.AM1130_PM0100);
		Schedule sched4 = new Schedule(Day.MONDAY_THURSDAY, Hour.AM1130_PM0100);
		Schedule sched5 = new Schedule(Day.TUESDAY_FRIDAY, Hour.AM0830_AM1000);
		
		Section sec1 = new Section(subject1, teacher1, sched1);
		Section sec2 = new Section(subject2, teacher2, sched2);
		Section sec3 = new Section(subject3, teacher3, sched3);
		Section sec4 = new Section(subject4, teacher4, sched4);
		Section sec5 = new Section(subject5, teacher5, sched5);
		
		lester.enroll(sec1);
		lester.enroll(sec2);
		lester.enroll(sec3);
		lester.enroll(sec4);
		lester.enroll(sec5);
		
		Subject subject = new Subject("OVER1", null, Level.UNDERGRADUATE);
		Teacher teacher = new Teacher("Over", "Teacher", "2-07-123");
		Schedule sched = new Schedule(Day.WEDNESDAY_SATURDAY, Hour.PM0100_PM0230);
		Section section = new Section(subject, teacher, sched);
		
		lester.enroll(section);
	}
	
	@Test
	public void checkEquality() {
		Student xx = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		Student yy = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		Student zz = new Student("Lester", "Manansala", "3-08-239", null, Standing.FOURTH_YEAR, Scholarship.NOT_SCHOLAR);
		
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
