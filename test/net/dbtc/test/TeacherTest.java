package net.dbtc.test;

import net.dbtc.Teacher;
import net.dbtc.exception.InvalidIDNumberFormatException;

import org.junit.Assert;
import org.junit.Test;


public class TeacherTest {

	@Test
	public void storeAndRetrieveAttributes() {
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		
		Assert.assertEquals("Alvin", alvinTeruel.getFirstName());
		Assert.assertEquals("Teruel", alvinTeruel.getLastName());
		Assert.assertEquals("Alvin Teruel", alvinTeruel.getFullName());
		Assert.assertEquals("2-07-123", alvinTeruel.getIDNumber());
	}

	@Test
	public void returnProperStringFormat() {
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
		
		Assert.assertEquals("Alvin Teruel 2-07-123", alvinTeruel.toString());
	}
	
	@Test (expected = InvalidIDNumberFormatException.class)
	public void rejectIncorrectIDNumberFormat() {
		Teacher alvinTeruel = null;
		
		alvinTeruel = new Teacher("Alvin", "Teruel", "-07-123");
		alvinTeruel = new Teacher("Alvin", "Teruel", "2-7-123");
		alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-23");
		alvinTeruel = new Teacher("Alvin", "Teruel", "12-07-123");
		alvinTeruel = new Teacher("Alvin", "Teruel", "2-107-123");
		alvinTeruel = new Teacher("Alvin", "Teruel", "a2-07-123");
		alvinTeruel = new Teacher("Alvin", "Teruel", "2-a07-a123");
		alvinTeruel = new Teacher("Alvin", "Teruel", "-2-07-123");
		alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123-");
		alvinTeruel = new Teacher("Alvin", "Teruel", "07-123");
		alvinTeruel = new Teacher("Alvin", "Teruel", "2-07");
	}
	
	@Test
	public void acceptCorrectIdNumberFormat() {
		Teacher alvinTeruel = new Teacher("Alvin", "Teruel", "2-07-123");
	}
	
	@Test
	public void checkEquality() {
		Teacher xx = new Teacher("Alvin", "Teruel", "2-07-123");
		Teacher yy = new Teacher("Alvin", "Teruel", "2-07-123");
		Teacher zz = new Teacher("Alvin", "Teruel", "2-07-123");
		
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
