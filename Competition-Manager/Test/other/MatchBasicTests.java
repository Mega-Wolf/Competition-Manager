package other;

import static org.junit.Assert.*;

import org.junit.Test;

import match.MatchBasic;
import match.MatchBasic.MatchType;

public class MatchBasicTests {

	//getTeamIDs Test
	@Test
	public void testGetTeamIDs() {
		int[] IDs1 = {1,2,3,4};
		int[] expected = {1,2,3,4};
		
		MatchBasic match1 = new MatchBasic(MatchType.GROUP_MATCH, IDs1, "Barbara");
		assertEquals(match1.getTeamIDs(),expected);//failure
	}

	//getMatchType Test
	@Test
	public void testGetMatchType() {
		int[] IDs1 = {1,2,3,4};
		MatchType expected = MatchType.GROUP_MATCH;
		
		MatchBasic match1 = new MatchBasic(MatchType.GROUP_MATCH, IDs1,"Barbara");
		assertEquals(match1.getMatchType(),expected);
	}

	@Test
	public void testEqualsWC() {
		
	}

	//isValid Tests: True, False, False
	@Test
	public void testIsValid() {
		int[] IDs1 = {1,3};
		MatchBasic match1 = new MatchBasic(MatchType.GROUP_MATCH, IDs1,"Barbara");
		match1.isValid();
	}
	
	@Test
	public void testIsNOTValid1() {
		int[] IDs1 = {-1,3};
		MatchBasic match1 = new MatchBasic(MatchType.GROUP_MATCH, IDs1,"Barbara");
		assertFalse(match1.isValid());
	}
	
	@Test
	public void testIsNOTValid2() {
		int[] IDs1 = {1,3,4};
		MatchBasic match1 = new MatchBasic(MatchType.GROUP_MATCH, IDs1,"Barbara");
		assertFalse(match1.isValid());
	}

}
