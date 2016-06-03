package other;

import java.util.ArrayList;
import java.util.List;

import match.Match;

public class Competition {
	/* Consts */
	public static final int TEAM_MAX = 16;
	
	/* Variables */
	private List<Team> teamList = new ArrayList<Team>();
	private List<Group> groupList = new ArrayList<Group>();
	private List<Match> matchList = new ArrayList<Match>();
	private List<Player> playerList = new ArrayList<Player>();
	
	/* Constructor */
	public Competition() {
		
	}
	
	/* Methods */
	
	/**
	 * Creates 4 groups; each containing 4 teams.
	 * Teams are grouped randomly
	 */
	public void createGroups() {
		if(groupList.size() == 0) {
			List<Team> dummyTeamList = new ArrayList<Team>(teamList);
			
			for (int i = 0; i < 4; i++) {
				groupList.add(new Group());
			}
			
			for (int group_cnt = 0; group_cnt < 4; group_cnt++) {
				for (int team_cnt = 0; team_cnt < 4; team_cnt++) {
					int rndNumber = (int) (Math.random() * dummyTeamList.size());
					groupList.get(group_cnt).addTeam(dummyTeamList.get(rndNumber));
					dummyTeamList.remove(rndNumber);
				}
			}
			
			//TODO: logging
		} else {
			//TODO: Exception
		}
		
		
		
	}
	
	
	
	
}