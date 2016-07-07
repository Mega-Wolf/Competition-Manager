package other;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import match.GroupExtension;

public class GroupExtensionTest {

	//testing .isValid()
	@Test
	public void isValidTest(){
		GroupExtension game1 = new GroupExtension(1,2,new int[]{0,1});
		game1.isValid();		
	}
	
	@Test
	public void invalidMatchID(){
		GroupExtension game2 = new GroupExtension(-1,4,new int[]{2,0});
		assertFalse(game2.isValid());
	}
	
	@Test
	public void invalidGroupID(){
		GroupExtension game3 = new GroupExtension(3,-1,new int[]{1,1});
		assertFalse(game3.isValid());
	}
	
	//Testing .getPoints()
	@Test
	public void getPointsDraw(){
		GroupExtension game4 = new GroupExtension(1,2,new int[]{1,1});
		int[] expected = new int[2];
		expected[0]=1;
		expected[1]=1;
		assertTrue(Arrays.equals(expected,game4.getPoints()));
	}
	
	@Test
	public void getPointsWin1(){
		GroupExtension game5 = new GroupExtension(1,2,new int[]{2,0});
		int[] expected = new int[2];
		expected[0]=3;
		expected[1]=0;
		assertTrue(Arrays.equals(expected,game5.getPoints()));
	}
	
	@Test
	public void getPointsLose1(){
		GroupExtension game6 = new GroupExtension(1,2,new int[]{0,2});
		int[] expected = new int[2];
		expected[0]=0;
		expected[1]=3;
		assertTrue(Arrays.equals(expected,game6.getPoints()));
	}

}
