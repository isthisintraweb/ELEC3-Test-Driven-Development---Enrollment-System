package net.dbtc;

import java.util.HashSet;
import java.util.Set;

import net.dbtc.exception.InvalidIDNumberFormatException;
import net.dbtc.exception.ScheduleConflictException;

public class Teacher {
	
	private String firstName;
	private String lastName;
	private String idNumber;
	
	private Set<Section> currentSections;
	
	/**
	 * Construct a Teacher object.
	 * 
	 * @param firstName The first name of the Teacher.
	 * @param lastName The last name of the Teacher.
	 * @param idNumber The unique identifier of the Teacher with the format D-DD-DDD, where D is any digit.
	 * @throws InvalidIDNumberFormatException If the ID Number does not follow the required format.
	 */
	public Teacher(String firstName, String lastName, String idNumber) throws InvalidIDNumberFormatException {
		if (!isIDNumberValid(idNumber)) {
			throw new InvalidIDNumberFormatException("Invalid ID Number format!");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.currentSections = new HashSet<Section>();
	}
	
	/**
	 * Checks if the given ID Number follows the format D-DD-DDD, where D is any digit.
	 * 
	 * @param idNumber The ID Number.
	 * @return <code>true</code> if the ID number is matches the required format.
	 */
	private boolean isIDNumberValid(String idNumber) {
		return idNumber.matches("\\d{1}-\\d{2}-\\d{3,}");
	}
	
	/**
	 * Returns the first name of this Teacher.
	 * 
	 * @return the first name of the Teacher
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Returns the last name of this Teacher.
	 * 
	 * @return the last name of the Teacher.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Returns the full name of this Teacher.
	 * 
	 * @return the full name of the Teacher.
	 */
	public String getFullName() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(firstName).append(' ').append(lastName);
		return buffer.toString();
	}
	
	/**
	 * Returns the ID Number of this Teacher.
	 * 
	 * @return the ID Number of the Teacher.
	 */
	public String getIDNumber() {
		return idNumber;
	}
	
	/**
	 * Adds a section to the Teacher.
	 * 
	 * @param section
	 */
	protected void addSection(Section section) throws ScheduleConflictException {
		Schedule sched = section.getSchedule();
		if (hasScheduleConflict(sched)) {
			throw new ScheduleConflictException("Schedule Conflict!");
		}
		currentSections.add(section);
	}
	
	/**
	 * Checks whether the Teacher have a conflict with the given Section.
	 * 
	 * @param section
	 * @return
	 */
	protected boolean hasScheduleConflict(Schedule sched) {
		for (Section s : currentSections) {
			if ((s.getSchedule()).equals(sched)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the <code>String</code> representation of this Teacher.
	 * Format: &lt;firstname&gt; &lt;lastname&gt; &lt;idnumber&gt;
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(firstName).append(' ').append(lastName).append(' ').append(idNumber);
		return buffer.toString();
	}
	
	/**
	 * Returns the unique hash code of this Object.
	 */
	@Override
	public int hashCode() {
		return idNumber.hashCode();
	}
	
	/**
	 * Checks whether this Teacher is equals to another Object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Teacher)) {
			return false;
		}
		
		Teacher teacher = (Teacher)obj;
		if (this.hashCode() == teacher.hashCode() &&
			(this.firstName).equals(teacher.getFirstName()) &&
			(this.lastName).equals(teacher.getLastName()) &&
			(this.idNumber).equals(teacher.getIDNumber())) {
			return true;
		}
		return false;
	}

}
