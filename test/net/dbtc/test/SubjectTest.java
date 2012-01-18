package net.dbtc.test;

import java.util.HashSet;
import java.util.Set;

import net.dbtc.Level;
import net.dbtc.Subject;

import org.junit.Assert;
import org.junit.Test;

public class SubjectTest {

	@Test
	public void storeAndRetrieveAttributes() {
		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Set<Subject> math2Prereq = new HashSet<Subject>();
		math2Prereq.add(math1);
		Subject math2 = new Subject("MATH2", math2Prereq, Level.UNDERGRADUATE);
		
		Assert.assertEquals("MATH2", math2.getSubjectCode());
		Assert.assertEquals(math2Prereq, math2.getPrerequisites());
		Assert.assertEquals(Level.UNDERGRADUATE, math2.getLevel());
		Assert.assertEquals(3, Subject.NUMBER_OF_UNITS);
	}

	@Test
	public void returnProperStringFormat() {
		String expected = "MATH3 UNDERGRADUATE";

		Subject math1 = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Subject math2 = new Subject("MATH2", null, Level.UNDERGRADUATE);
		Set<Subject> math3Prereq = new HashSet<Subject>();
		math3Prereq.add(math1);
		math3Prereq.add(math2);
		
		Subject math3 = new Subject("MATH3", math3Prereq, Level.UNDERGRADUATE);
		
		Assert.assertEquals(expected, math3.toString());
	}

	@Test
	public void checkEquality() {
		Subject xx = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Subject yy = new Subject("MATH1", null, Level.UNDERGRADUATE);
		Subject zz = new Subject("MATH1", null, Level.UNDERGRADUATE);
		
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
