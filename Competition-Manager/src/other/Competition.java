package other;

import java.util.ArrayList;
import java.util.List;

public class Competition {
	
	public static final int TEAM_MAX = 16;
	
	private List<Team> teamList = new ArrayList<Team>();
	private List<Group> groupList = new ArrayList<Group>();
	
	public void addTeam(Team team) {
		if (teamList.size() < 16) {
			teamList.add(team);
			//TODO: logging
		} else {
			//TODO: exception
		}
	}
	
	public void deleteTeam(Team team) {	
		if(teamList.contains(team)) {
			teamList.remove(team);
			//TODO: logging
		} else {
			//TODO: Exception
		}
	}
	
	public void createGroups() {
		if(teamList.size() == 16 && groupList.size() == 0) {
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