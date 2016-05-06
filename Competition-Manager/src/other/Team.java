package other;
import java.util.ArrayList;
import java.util.List;

public class Team {
	
	/* Consts */
	public static final int MAXIMAL_TEAM_SIZE = 23;
	public static final int MINIMAL_TEAM_SIZE = 11;
	
	/* Variables */
	private List<Player> playerList = new ArrayList<Player>();
	private String school;
	
	/* Constructor */
	public Team(String school) {
		this.school = school;
	}
	
	/* Methods */
	public void addPlayer(Player player) {
		if (playerList.size() < MAXIMAL_TEAM_SIZE) {
			playerList.add(player);
			//TODO; Logging
		} else {
			//TODO; Exception
		}
	}
	
	public void deletePlayer(Player player) {
		
		if (playerList.contains(player)) {
			playerList.remove(player);
			//TODO: logging
		}
		else {
			//TODO: Exception
		}
	}
	
	/* Setter */
	
	/* Getter */
	public String getSchool() {
		return school;
	}
	
	public int getNumberOfPlayers() {
		return playerList.size();
	}
}