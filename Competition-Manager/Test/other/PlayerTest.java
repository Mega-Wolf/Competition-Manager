package other;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.model.RunnerBuilder;

import elements.Player;

@RunWith(PlayerTest.class)
@SuiteClasses({PlayerTest.GeneralTest.class, PlayerTest.WildcardTest.class})
public class PlayerTest extends Suite {
	
	public PlayerTest(Class<?> _class, RunnerBuilder builder) throws org.junit.runners.model.InitializationError {
        super(_class, builder);
    }
	
	public static class GeneralTest {
		@Test
		public void testEquals() {
			Player p1 = new Player(12, 2, "Max", "Mustermann");
			Player p2 = new Player(12, 2, "Max", "Mustermann");
			assertEquals(p1, p2);
		}
	}
	
	@RunWith(WildcardTest.class)
	@SuiteClasses({WildcardTest.WildcardTestTrue.class, WildcardTest.WildcardTestFalse.class})
	public static class WildcardTest extends Suite {
		
		public WildcardTest(Class<?> _class, RunnerBuilder builder) throws org.junit.runners.model.InitializationError {
	        super(_class, builder);
	    }
		
		public static class WildcardTestTrue {
			@Test
			public void wildcardTestTrue1() {
				Player p1 = new Player(12, 2, "Max", "Mustermann");
				Player p2 = new Player(-1, 2, "Max", "Mustermann");
				assertTrue(p1.equalsWC(p2));
			}
			
			@Test
			public void wildcardTestTrue2() {
				Player p1 = new Player(12, 2, "Max", "Mustermann");
				Player p2 = new Player(12, -1, "Max", "Mustermann");
				assertTrue(p1.equalsWC(p2));
			}
			
			@Test
			public void wildcardTestTrue3() {
				Player p1 = new Player(12, 2, "Max", "Mustermann");
				Player p2 = new Player(12, 2, null, "Mustermann");
				assertTrue(p1.equalsWC(p2));
			}
			
			@Test
			public void wildcardTestTrue4() {
				Player p1 = new Player(12, 2, "Max", "Mustermann");
				Player p2 = new Player(12, 2, "Max", null);
				assertTrue(p1.equalsWC(p2));
			}
		}
		
		public static class WildcardTestFalse  {
			@Test
			public void wildcardTestFalse1() {
				Player p1 = new Player(-1, 2, "Max", "Mustermann");
				Player p2 = new Player(12, 2, "Max", "Mustermann");
				assertFalse(p1.equalsWC(p2));
			}
			
			@Test
			public void wildcardTestFalse2() {
				Player p1 = new Player(12, -1, "Max", "Mustermann");
				Player p2 = new Player(12, 2, "Max", "Mustermann");
				assertFalse(p1.equalsWC(p2));
			}
			
			@Test(expected=NullPointerException.class)
			public void wildcardTestFalse3() {
				Player p1 = new Player(12, 2, null, "Mustermann");
				Player p2 = new Player(12, 2, "Max", "Mustermann");
				p1.equalsWC(p2);
			}
			
			@Test(expected=NullPointerException.class)
			public void wildcardTestFalse4() {
				Player p1 = new Player(12, 2, "Max", null);
				Player p2 = new Player(12, 2, "Max", "Mustermann");
				p1.equalsWC(p2);
			}
		}
	}
}
