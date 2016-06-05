package other;

import java.util.HashMap;
import java.util.Map;
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

	public Map<Integer, Player> getMatchingPlayers(Player matchingPlayer) {
		Map<Integer, Player> retMap = new HashMap<>();
		for (Entry<Integer, Player> p : playerMap.entrySet()) {
			if (p.equals(matchingPlayer)) {
				retMap.put(p.getKey(), p.getValue());
			}
		}
		return retMap;
	}

	/* "Setter" */
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