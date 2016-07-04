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
		playerManager.add(new Player(3,0,"Herbert","Grönemeier"));
		playerManager.add(new Player(4,0,"Günther","Jauch"));
		Map<Integer,Player> List1 = playerManager.getMatching(new Player(3,0,null,null));
		assertTrue(List1.size() == 2
				&& List1.entrySet().contains("Herbert Grönemeier")
				&& List1.entrySet().contains("Günther Jauch"));
	}
	
	@Test
	public void Test2() {
		Manager<Player> playerManager = new Manager<Player>();
		playerManager.add(new Player(1,1,"Pablo","Picasso"));
		playerManager.add(new Player(2,1,"Salvador","Dali"));
		Map<Integer,Player> List2 = playerManager.getMatching(new Player(1,1,null,null));
		assertTrue(List2.size() == 2
				&& List2.entrySet().contains("Pablo Picasso")
				&& List2.entrySet().contains("Salvador Dali"));
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
