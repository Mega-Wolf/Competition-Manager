package other;

import static org.junit.Assert.*;

import org.junit.Test;

import match.GroupExtension;

public class GroupExtensionTest {

	@Test
	public void Test1() {
		GroupExtension team1 = new GroupExtension(1,2,new int[]{0,1});
		team1.isValid();		
	}
	
	@Test
	public void Test2(){
		GroupExtension team2 = new GroupExtension(-1,4,new int[]{2,0});
		assertFalse(team2.isValid());
	}
	
	@Test
	public void Test3(){
		GroupExtension team3 = new GroupExtension(3,-1,new int[]{1,1});
		assertFalse(team3.isValid());
	}

}
