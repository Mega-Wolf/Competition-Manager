package other;

import static org.junit.Assert.*;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class ManagerTest {

	@Test
	public void Test1() {
		Manager<Player> playerManager = new Manager<Player>();
		Player p1 = new Player(3,0,"Herbert","Grönemeier");
		Player p2 = new Player(4,0,"Günther","Jauch");
		playerManager.add(p1);
		playerManager.add(p2);
		Map<Integer,Player> List1 = playerManager.getMatching(new Player(-1,1,null,null));
		assertTrue(List1.size() == 2
				&& List1.entrySet().contains(p1)
				&& List1.entrySet().contains(p2));
	}
	
	@Test
	public void Test2() {
		Manager<Player> playerManager = new Manager<Player>();
		Player p3 = new Player(1,1,"Pablo","Picasso");
		Player p4 = new Player(2,1,"Salvador","Dali");
		playerManager.add(p3);
		playerManager.add(p4);
		Map<Integer,Player> List2 = playerManager.getMatching(new Player(-1,1,null,null));
		assertTrue(List2.size() == 2
				&& List2.entrySet().contains(p3)
				&& List2.entrySet().contains(p4));
	}
/*
 	@Test
 	public void Test3() {
		playerManager.add(new Player(-2,3,"Anabel","Lee"));
		playerManager.add(new Player(2,-1,"Walls","of Jericho"));
		playerManager.add(new Player(0,1,"Bastian","Schweini"));
 	}
 */

}
