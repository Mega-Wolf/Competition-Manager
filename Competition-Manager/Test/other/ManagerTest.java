package other;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class ManagerTest {

	@Test
	public void Test1() throws InvalidObjectException {
		Manager<Player> playerManager = new Manager<Player>();
		Player p1 = new Player(3,0,"Herbert","Grönemeier");
		Player p2 = new Player(4,0,"Günther","Jauch");
		playerManager.add(p1);
		playerManager.add(p2);
		Map<Integer,Player> Map1 = playerManager.getMatching(new Player(-1,0,null,null));
		assertTrue(Map1.size() == 2
				&& Map1.containsValue(p1)
				&& Map1.containsValue(p2));
	}
	
	@Test
	public void Test2() throws InvalidObjectException {
		Manager<Player> playerManager = new Manager<Player>();
		Player p3 = new Player(1,1,"Pablo","Picasso");
		Player p4 = new Player(2,1,"Salvador","Dali");
		playerManager.add(p3);
		playerManager.add(p4);
		Map<Integer,Player> Map2 = playerManager.getMatching(new Player(-1,1,null,null));
				
		assertTrue(Map2.size() == 2
				&& Map2.containsValue(p3)
				&& Map2.containsValue(p4));
	}

 	@Test
 	public void Test3() throws InvalidObjectException {
 		Manager<Player> playerManager = new Manager<Player>();
		Player p1 = new Player(3,0,"Herbert","Grönemeier");
		Player p3 = new Player(1,1,"Pablo","Picasso");
		playerManager.add(p1);
		playerManager.add(p3);
		Map<Integer,Player> Map3 = playerManager.getMatching(new Player(-1,0,null,null));
		assertFalse(Map3.size() == 2
				&& Map3.containsValue(p1)
				&& Map3.containsValue(p3));
		
 	}
 	
 	@Test (expected = UnsupportedOperationException.class)
 	public void Test4() throws InvalidObjectException {
 		Manager<Player> playerManager = new Manager<Player>();
 		playerManager.lock();
 		playerManager.add(new Player(8,9,"Tobias","Jordine"));
 	}
 	
 	@Test (expected = UnsupportedOperationException.class)
 	public void Test5() throws InvalidObjectException {
 		Manager<Player> playerManager = new Manager<Player>();
 		Player p = new Player(1,2,"Miomi","Wyder");
  		playerManager.add(p);
  		playerManager.lock();
  		Map<Integer, Player> map = playerManager.getMatching(p);
  		int playerID = map.keySet().stream().collect(Collectors.toList()).get(0);
  		playerManager.remove(playerID);
 	}
 	
 	@Test
 	public void Test6() throws InvalidObjectException {
 		Manager<Player> playerManager = new Manager<Player>();
 		Player p = new Player(2,3,"Tony","Wiommi");
  		playerManager.add(p);
  		Map<Integer, Player> map = playerManager.getMatching(p);
  		int playerID = map.keySet().stream().collect(Collectors.toList()).get(0);
  		playerManager.remove(playerID);
  		assertTrue(playerManager.getMatching(new Player(-1,-1,null,null)).size() == 0);
 	}
 	
 	@Test
 	public void Test7() throws InvalidObjectException{
 		Manager<Player> playerManager = new Manager<Player>();
 		Player p = new Player(4,5,"Nanu","Nana");
  		int playerId1 = playerManager.add(p);
  		Map<Integer, Player> map = playerManager.getMatching(p);
  		int playerID2 = map.keySet().stream().collect(Collectors.toList()).get(0);
  		assertTrue(playerId1 == playerID2);
 	}
 	
 	@Test
 	public void Test8() throws InvalidObjectException{
 		Manager<Player> playerManager = new Manager<Player>();
 		Player p = new Player(6,7,"Ozzy","Osbourne");
  		int playerId1 = playerManager.add(p);
  		assertEquals(playerManager.get(playerId1),p);
 	}
 	
 	@Test
 	public void Test9() throws InvalidObjectException{
 		Manager<Player> playerManager = new Manager<Player>();
 		Player p = new Player(1,11,"Manuel","Neuer");
   		int playerId = playerManager.add(p);
  		playerManager.lock();
  		playerManager.get(playerId);
 	}
}
