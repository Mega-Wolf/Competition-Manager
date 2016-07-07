package other;

import static org.junit.Assert.*;

import org.junit.Test;

import match.RoundExtension;

public class RoundExtensionTests {

	//getGoalsOvertime test
	@Test
	public void testGetGoalsOvertime() {
		int[]expected={2,2};
		RoundExtension roundEx1 = new RoundExtension(1,2, new int[]{1,2} , new int[]{2,2} );
		assertTrue(expected.equals(roundEx1.getGoalsOvertime())); //failure
	}
	
	//getGoalsPenaly test
	@Test
	public void testGetPenalty(){
		int[] expected = {3,5};
		RoundExtension roundEx2 = new RoundExtension(1,2, new int[]{0,1}, new int[]{1,2}, new int[]{3,5});
		assertTrue(expected.equals(roundEx2.getGoalsPenalty())); //failure
	}
}
