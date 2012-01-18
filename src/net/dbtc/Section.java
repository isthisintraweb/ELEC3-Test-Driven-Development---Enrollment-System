package net.dbtc;

import java.util.HashSet;
import java.util.Set;

import net.dbtc.exception.ScheduleConflictException;
import net.dbtc.exception.SectionNoSlotAvailableException;

public class Section {
	
	public static final int MAX_STUDENTS = 40;
	
	private Subject subject;
	private Teacher teacher;
	private Schedule schedule;
	private Set<Student> students;
	
	public Section(Subject subject, Teacher teacher, Schedule schedule) throws ScheduleConflictException {
		if (teacher.hasScheduleConflict(schedule)) {
			throw new ScheduleConflictException();
		}
		this.subject = subject;
		this.teacher = teacher;
		this.schedule = schedule;
		
		this.teacher.addSection(this);
		this.students = new HashSet<Student>();
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
	
	protected boolean hasSlot() {
		if (students.size() < MAX_STUDENTS) {
			return true;
		}
		return false;
	}
	
	protected void addStudent(Student student) throws SectionNoSlotAvailableException {
		students.add(student);
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(subject.getSubjectCode()).append(" ");
		buffer.append(teacher.getFullName()).append(" ");
		buffer.append(schedule.toString());
		
		return buffer.toString();
	}
	
	@Override
	public int hashCode() {
		return subject.hashCode() + teacher.hashCode() + schedule.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Section)) {
			return false;
		}
		
		Section section = (Section)obj;
		if (this.hashCode() == section.hashCode() &&
			(this.subject).equals(section.getSubject()) &&
			(this.teacher).equals(section.getTeacher()) &&
			(this.schedule).equals(section.getSchedule())) {
			return true;
		}
		return false;
	}
	
}
