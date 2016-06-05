package other;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerManager {

	/* Variables */
	private ConcurrentHashMap<Integer, Player> playerMap = new ConcurrentHashMap<>();
	private int idCounter;

	/* Methods */
	private synchronized boolean checkPlayer(Player newPlayer) {
		
		//parallelStream used, because up to 368 players
		List<Player> teamMember = playerMap.values().parallelStream().filter(p -> p.getTeam() == newPlayer.getTeam()).collect(Collectors.toList());
		
		if (teamMember.size() < Team.TEAM_SIZE_MAX) {
			return teamMember.stream().noneMatch(p -> p.getNumber() == newPlayer.getNumber());
		}
		
		return false;
	}

	/* Getter */
	public Player getPlayer(int id) {
		return playerMap.get(id);
	}

	public Map<Integer, Player> getMatchingPlayers(Player matchingPlayer) {
		return playerMap.entrySet().stream().filter(p -> p.equals(matchingPlayer)).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
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