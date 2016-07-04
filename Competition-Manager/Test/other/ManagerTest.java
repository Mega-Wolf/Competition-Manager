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
		Map<Integer,Player> meineDummeBehinderteListe = playerManager.getMatching(new Player(-1,1,null,null));
		assertTrue(meineDummeBehinderteListe.size() == 2
				&& meineDummeBehinderteListe.entrySet().contains("Herbert Grönemeier")
				&& meineDummeBehinderteListe.entrySet().contains("Günther Jauch"));
	}
	
	@Test
	public void Test2() {
		Manager<Player> playerManager = new Manager<Player>();
		playerManager.add(new Player(1,1,"Pablo","Picasso"));
		playerManager.add(new Player(2,1,"Salvador","Dali"));
		Map<Integer,Player> meineAndereDummeBehinderteListe = playerManager.getMatching(new Player(-1,1,null,null));
		assertTrue(meineAndereDummeBehinderteListe.size() == 2
				&& meineAndereDummeBehinderteListe.entrySet().contains("Pablo Picasso")
				&& meineAndereDummeBehinderteListe.entrySet().contains("Salvador Dali"));
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
