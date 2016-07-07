package other;

import static org.junit.Assert.*;

import org.junit.Test;

public class GroupTest {

	//getTeamID tests, True and False
	@Test
	public void testGetTeamID() {
		int[] ID1 = {1,2,3,4};
		Group group1 = new Group(ID1);
		int pos1 = 1;
		assertEquals(2,group1.getTeamID(pos1));
	}
	
	@Test
	public void testGetNOTeamID() {
		int[] ID1 = {1,2,3,4};
		Group group1 = new Group(ID1);
		int pos1 = 1;
		assertFalse(group1.getTeamID(pos1)==3);
	}

	//equalsWC tests, True and False
	@Test
	public void testEqualsWC() {
		int[] ID1 = {1,2,3,4};
		int[] ID2 = {1,2,3,4};
		Group group1 = new Group(ID1);
		Group group2 = new Group(ID2);
		assertTrue(group1.equalsWC(group2));
	}
	
	@Test
	public void testEqualsNOTWC() {
		int[] ID1 = {1,2,3,4};
		int[] ID2 = {4,3,2};
		Group group1 = new Group(ID1);
		Group group2 = new Group(ID2);
		group1.equalsWC(group2);
	}	
	
	//isValid() tests, True and false
	@Test
	public void testIsValid() {
		int[] ID1 = {1,2,3,4};
		Group group1 = new Group(ID1);
		assertTrue(group1.isValid());
	}

	@Test
	public void testIsNOTValid() {
		int[] ID1 = {1,2,3};
		Group group1 = new Group(ID1);
		assertFalse(group1.isValid());
	}

}
