package other;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import match.GroupExtension;

public class GroupExtensionTest {

	@Test
	public void Test1(){
		GroupExtension game1 = new GroupExtension(1,2,new int[]{0,1});
		game1.isValid();		
	}
	
	@Test
	public void Test2(){
		GroupExtension game2 = new GroupExtension(-1,4,new int[]{2,0});
		assertFalse(game2.isValid());
	}
	
	@Test
	public void Test3(){
		GroupExtension game3 = new GroupExtension(3,-1,new int[]{1,1});
		assertFalse(game3.isValid());
	}
	
	@Test
	public void Test4(){
		GroupExtension game4 = new GroupExtension(1,2,new int[]{1,1});
		int[] expected = new int[2];
		expected[0]=1;
		expected[1]=1;
		assertTrue(Arrays.equals(expected,game4.getPoints()));
	}

}
