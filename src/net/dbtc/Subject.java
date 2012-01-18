package net.dbtc;

import java.util.HashSet;
import java.util.Set;

public class Subject {
	
	public static final int NUMBER_OF_UNITS = 3;
	
	private String subjectCode;
	private Set<Subject> prerequisites;
	private Level level;
	
	public Subject(String subjectCode, Set<Subject> prerequisites, Level level) {
		this.subjectCode = subjectCode;
		this.prerequisites = prerequisites;
		this.level = level;
		
		if (prerequisites == null) {
			this.prerequisites = new HashSet<Subject>();
		}
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}
	
	public Set<Subject> getPrerequisites() {
		return prerequisites;
	}
	
	public Level getLevel() {
		return level;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(subjectCode).append(' ').append(level);
		return buffer.toString();
	}
	
	@Override
	public int hashCode() {
		return subjectCode.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Subject)) {
			return false;
		}
		
		Subject subject = (Subject)obj;
		if (this.hashCode() == subject.hashCode() &&
			(this.subjectCode).equals(subject.getSubjectCode()) &&
			(this.prerequisites).equals(subject.getPrerequisites()) &&
			(this.level).equals(subject.getLevel())) {
			return true;
		}
		return false;
	}
	
}
