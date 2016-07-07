package other;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gui.controller.NewTeamController;

public class NewTeamControllerTests {
	NewTeamController ntc = new NewTeamController();

	@Test
	public void isValidName() {
		assertTrue(ntc.isValidName("Julia Adamant"));
	}
	
	@Test
	public void isNOTValidName() {
		assertFalse(ntc.isValidName("")); 
	}
	
	@Test
	public void isValidNumber() {
		String x = "12";
		assertTrue(ntc.isValidNumber(x)); 
	}
	
	@Test
	public void isNOTValidNumber() {
		assertFalse(ntc.isValidNumber("0"));
	}
	
	@Test
	public void isNOTValidNumber2() {
		assertFalse(ntc.isValidNumber("101"));
	}
	
	@Test
	public void isObviouslyNOTValidNumber() {
		assertFalse(ntc.isValidNumber("ab"));
	}

}
