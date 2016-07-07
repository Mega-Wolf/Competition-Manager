package other;

import static org.junit.Assert.*;

import org.junit.Test;

import match.RoundExtension;

public class RoundExtensionTests {

	//isValid tests
	@Test
	public void testIsValid(){
		RoundExtension roundEx2 = new RoundExtension(1, new int[]{1,1}, new int[]{2,2}, new int[]{3,5});
		assertTrue(roundEx2.isValid());
	}
	
	@Test
	public void testIsValidNoOvertime(){
		RoundExtension roundEx2 = new RoundExtension(1, new int[]{1,3}, null, null);
		assertTrue(roundEx2.isValid());
	}
	
	@Test
	public void isNotValidGroupID(){
		RoundExtension roundEx2 = new RoundExtension(-1, new int[]{1,1}, new int[]{2,2}, new int[]{3,5});
		assertFalse(roundEx2.isValid());
	}
	
	@Test
	public void isNotValidOvertime(){
		RoundExtension roundEx2 = new RoundExtension(1, new int[]{0,1}, new int[]{2,2}, new int[]{0,0});
		assertFalse(roundEx2.isValid());
	}
	
	@Test
	public void isNotValidPenalty(){
		RoundExtension roundEx2 = new RoundExtension(1, new int[]{1,1}, new int[]{1,2}, new int[]{3,5});
		assertFalse(roundEx2.isValid());
	}
	
	/*
	//getGoalsOvertime test
	@Test
	public void testGetGoalsOvertime() {
		int[]expected={2,3};
		RoundExtension roundEx1 = new RoundExtension(1, new int[]{1,1}, new int[]{2,3});
		assertTrue(expected == roundEx1.getGoalsOvertime()); //failure because private nvm
	}
	
	//getGoalsPenaly test
	@Test
	public void testGetPenalty(){
		int[] expected = {3,5};
		RoundExtension roundEx2 = new RoundExtension(1, new int[]{1,1}, new int[]{2,2}, new int[]{3,5});
		assertTrue(expected.equals(roundEx2.getGoalsPenalty())); //failure
	}
	
	*/
}
