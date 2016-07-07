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
		
		MatchBasic match1 = new MatchBasic(MatchType.GROUP_MATCH, IDs1);
		assertEquals(match1.getTeamIDs(),expected);//failure
	}

	//getMatchType Test
	@Test
	public void testGetMatchType() {
		int[] IDs1 = {1,2,3,4};
		MatchType expected = MatchType.GROUP_MATCH;
		
		MatchBasic match1 = new MatchBasic(MatchType.GROUP_MATCH, IDs1);
		assertEquals(match1.getMatchType(),expected);
	}

	@Test
	public void testEqualsWC() {
		
	}

	@Test
	public void testIsValid() {
		
	}

}
