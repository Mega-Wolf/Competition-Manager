package other;
import java.util.ArrayList;
import java.util.List;

public class Team{
		
	/* Consts */
	public static final int TEAM_SIZE_MAX = 23;
	public static final int TEAM_SIZE_MIN = 11;
	
	/* Variables */
	private final int id;
	private String school;
	
	/* Constructor */
	public Team(int id, String school) {
		this.id = id;
		this.school = school;
	}
	
	/* Methods */
	/*
	public void addPlayer(Player player) {
		if (playerList.size() < TEAM_SIZE_MAX) {
			
			boolean numberFree = true;
			
			for (Player p : playerList) {
				if (p.getNumber() == player.getNumber()) {
					numberFree = false;
					break;
				}
			}
			
			if (numberFree) {
				playerList.add(player);
				//TODO; Logging
			}
			else {
				//TODO; Exception, bzw Fehler
			}
			
			

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
	}*/
	
	/* Setter */
	
	/* Getter */
	public String getSchool() {
		return school;
	}
	/*
	public int getNumberOfPlayers() {
		return playerList.size();
	}
	*/
}