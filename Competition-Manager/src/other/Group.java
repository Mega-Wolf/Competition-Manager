package other;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import match.GroupMatch;
import match.Match;

public class Group {
	
	/* Variables */
	private List<Team> teamList = new ArrayList<Team>();
	
	private int id;
	
	protected final Match[] matches = new Match[6];
	//protected final String name;
	
	/* Methods */
	private void createMatches() {
		//TODO: use kickoff dates
	/*	matches[0] = new GroupMatch(teams[0], teams[1], new Date());
		matches[1] = new GroupMatch(teams[2], teams[3], new Date());
		matches[2] = new GroupMatch(teams[0], teams[2], new Date());
		matches[3] = new GroupMatch(teams[1], teams[3], new Date());
		matches[4] = new GroupMatch(teams[3], teams[0], new Date());
		matches[5] = new GroupMatch(teams[1], teams[2], new Date());
		
		*/
	}
	
	/* Setter */
	public void addTeam(Team team) {
		if (!teamList.contains(team)) {
			teamList.add(team);
			//TODO: logging
		} else {
			//TODO: Excpetion
		}
	}
	
	
	/* Getter */
	
	public Team[] getPassingTeams() {
		int[] points = new int[4];
		int[] goals = new int[4];
		
		for (Match m : matches) {
			
		}
		
		return null;
		
	}
	
	public boolean isFinished() {
		return false; //TODO
	}
}