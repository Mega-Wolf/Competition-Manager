package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

	/* Variables */
	private ConcurrentHashMap<Integer, Player> playerMap = new ConcurrentHashMap<>();
	private int idCounter;

	/* Methodes */
	private synchronized boolean checkPlayer(Player newPlayer) {

		int teamSize = 0;

		for (Entry<Integer, Player> p : playerMap.entrySet()) {
			if (p.getValue().getTeam() == newPlayer.getTeam()) {
				teamSize++;
				if (p.getValue().getNumber() == newPlayer.getNumber()) {
					return false;
				}
			}
		}
		if (teamSize < Team.TEAM_SIZE_MAX) {
			return true;
		}
		return false;
	}

	/* Getter */
	public Player getPlayer(int id) {
		return playerMap.get(id);
	}

	public List<Player> getMatchingPlayers(Player matchingPlayer) {
		List<Player> retList = new ArrayList<>();
		for (Entry<Integer, Player> p : playerMap.entrySet()) {
			if (p.equals(matchingPlayer)) {
				retList.add(p.getValue());
			}
		}
		return retList;
	}

	/* Setter */
	public synchronized void addPlayer(Player newPlayer) {
		if (checkPlayer(newPlayer)) {
			playerMap.put(idCounter++, newPlayer);
		}
	}
	
	public void removePlayer(Integer id) {
		playerMap.remove(id);
	}
	
	public void updatePlayer(Integer id, Player changedPlayer) {
		playerMap.replace(id, changedPlayer);
	}

}