package net.dbtc;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import net.dbtc.exception.DuplicateSectionException;
import net.dbtc.exception.EnrollmentLoadExceededException;
import net.dbtc.exception.InvalidIDNumberFormatException;
import net.dbtc.exception.PrerequisiteConflictException;
import net.dbtc.exception.ScheduleConflictException;
import net.dbtc.exception.SectionNoSlotAvailableException;
import net.dbtc.exception.UnderMinimumLoadException;

public class Student {
	
	private String firstName;
	private String lastName;
	private String idNumber;
	private Set<Subject> creditedSubjects;
	private Standing standing;
	private Scholarship scholarship;
	
	private Set<Section> currentSections;
	
	public Student(String firstName, String lastName, String idNumber, Set<Subject> creditedSubjects, Standing standing, Scholarship scholarship) {
		if (!isIDNumberValid(idNumber)) {
			throw new InvalidIDNumberFormatException("Invalid ID Number format!");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.creditedSubjects = creditedSubjects;
		this.standing = standing;
		this.scholarship = scholarship;
		
		this.currentSections = new HashSet<Section>();
		
		if (creditedSubjects == null) {
			this.creditedSubjects = new HashSet<Subject>();
		}
	}
	
	private boolean isIDNumberValid(String idNumber) {
		return idNumber.matches("\\d{1}-\\d{2}-\\d{3,}");
	}
	
	public String getFirstName() {
		return firstName;	
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFullName() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(firstName).append(" ").append(lastName);
		return buffer.toString();
	}
	
	public String getIDNumber() {
		return idNumber;
	}
	
	public Set<Subject> getCreditedSubjects() {
		return creditedSubjects;
	}
	
	public Standing getStanding() {
		return standing;
	}
	
	public Scholarship getScholarship() {
		return scholarship;
	}
	
	public void enroll(Section section) throws DuplicateSectionException, ScheduleConflictException, PrerequisiteConflictException, SectionNoSlotAvailableException {
		if (currentSections.contains(section)) {
			throw new DuplicateSectionException("Duplicate Section!");
		}
		if (hasScheduleConflict(section)) {
			throw new ScheduleConflictException("Schedule conflict!");
		}
		if (hasPrerequisiteConflict(section.getSubject())) {
			throw new PrerequisiteConflictException("Prerequisite conflict!");
		}
		if (!section.hasSlot()) {
			throw new SectionNoSlotAvailableException("Section is full!");
		}
		if (isFullLoad()) {
			throw new EnrollmentLoadExceededException("Enrollment load exceeded!");
		}
		currentSections.add(section);
		section.addStudent(this);
	}
	
	private boolean hasPrerequisiteConflict(Subject subject) {
		return !creditedSubjects.containsAll(subject.getPrerequisites());
	}
	
	private boolean hasScheduleConflict(Section section) {
		Schedule newSched = section.getSchedule();
		for (Section s : currentSections) {
			Schedule mySched = s.getSchedule();
			if (mySched.equals(newSched)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isFullLoad() {
		int maxLoad = 0;
		if (standing == Standing.FIRST_YEAR) {
			maxLoad = 18;
		} else if (standing == Standing.SECOND_YEAR) {
			maxLoad = 24;
		} else if (standing == Standing.THIRD_YEAR) {
			maxLoad = 24;
		} else if (standing == Standing.FOURTH_YEAR) {
			maxLoad = 21;
		}
		
		return currentLoad() >= maxLoad;
	}
	
	private int currentLoad() {
		return currentSections.size() * Subject.NUMBER_OF_UNITS;
	}
	
	public BigDecimal calculateTuitionFee() {
		if (currentLoad() < minimumLoad()) {
			throw new UnderMinimumLoadException("Minimum Load not reached!");
		}
		
		BigDecimal tuition = new BigDecimal("1");;
		BigDecimal feePerSubject = new BigDecimal("2000");
		BigDecimal numberOfEnrolledSubjects = new BigDecimal(currentSections.size());
		BigDecimal misc = new BigDecimal("2000");
		
		BigDecimal factor = BigDecimal.ONE;
		if (scholarship == Scholarship.HALF_SCHOLAR) {
			factor = new BigDecimal("0.5");
		} else if (scholarship == Scholarship.FULL_SCHOLAR) {
			factor = new BigDecimal("0");
		}
		
		tuition = numberOfEnrolledSubjects.multiply(feePerSubject);
		tuition = tuition.multiply(factor);
		tuition = tuition.add(misc);
		tuition.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return tuition;
	}
	
	private int minimumLoad() {
		if (standing == Standing.FIRST_YEAR) {
			return 15;
		} else if (standing == Standing.SECOND_YEAR || standing == Standing.THIRD_YEAR) {
			return 18;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(firstName).append(" ").append(lastName).append(" ").append(idNumber);
		return buffer.toString();
	}
	
	@Override
	public int hashCode() {
		return idNumber.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		
		Student student = (Student)obj;
		if (this.hashCode() == student.hashCode() &&
			(this.firstName).equals(student.getFirstName()) &&
			(this.lastName).equals(student.getLastName()) &&
			(this.idNumber).equals(student.getIDNumber()) &&
			(this.creditedSubjects).equals(student.getCreditedSubjects()) &&
			(this.standing).equals(student.getStanding()) &&
			(this.scholarship).equals(student.getScholarship())) {
			return true;
		}
		
		return false;
	}
	
}
