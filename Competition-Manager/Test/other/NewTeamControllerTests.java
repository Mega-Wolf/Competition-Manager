package other;

import static org.junit.Assert.*;

import org.junit.Test;

public class NewTeamControllerTests {

	@Test
	public void isValidName() {
		assertTrue(isValidName("Julia Adamant")); //please change to correct syntax
	}
	
	@Test
	public void isNOTValidName() {
		assertFalse(isValidName("")); //see above
	}
	
	@Test
	public void isValidNumber() {
		String x = "12";
		assertTrue(isValidNumber(x)); //this really confuses me because it defies what I know about this stuff
	}
	
	@Test
	public void isNOTValidNumber() {
		assertFalse(isValidNumber("0"));
	}
	
	@Test
	public void isNOTValidNumber2() {
		assertFalse(isValidNumber("101"));
	}
	
	@Test
	public void isObviouslyNOTValidNumber() {
		assertFalse(isValidNumber("ab"));
	}

}
