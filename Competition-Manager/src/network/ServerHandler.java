package network;

import java.util.ArrayList;
import java.util.List;

import match.MatchBasic;
import other.Group;

public class ServerHandler {
	
	/**
	 * class is just a helper, so it should not be instanciable
	 */
	private ServerHandler() {
	}
	
	/**
	 * Creates 4 groups, each containing 4 teams
	 * @param teamList a list of 16 team IDs; the caller must check this restriction
	 * @return list of the created groups
	 */
	public static List<Group> createGroups(List<Integer> teamListIDs) {
		List<Group> groupList = new ArrayList<Group>();
		
		List<ArrayList<Integer>> groupIDs = new ArrayList<ArrayList<Integer>>(); 
		
		for (int group_cnt = 0; group_cnt < 4; group_cnt++) {
			groupIDs.add(new ArrayList<Integer>());
			for (int team_cnt = 0; team_cnt < 4; team_cnt++) {
				int rndNumber = (int) (Math.random() * teamListIDs.size());
				groupIDs.get(group_cnt).add(teamListIDs.get(rndNumber));
				teamListIDs.remove(rndNumber);
			}
			int[] dummy = new int[4];
			for (int i = 0; i < 4; i++) {
				dummy[i] = groupIDs.get(group_cnt).get(i);
			}
			
			groupList.add(new Group(dummy));
		}
		
		return groupList;
	}
	
	public static List<MatchBasic> createGroupMatches(List<Group> groupList) {
		List<MatchBasic> matchList = new ArrayList<MatchBasic>();
		for (Group g : groupList) {
			matchList.add(new MatchBasic(new int[]{g.getTeamID(0),g.getTeamID(1)}, true));
			matchList.add(new MatchBasic(new int[]{g.getTeamID(2),g.getTeamID(3)}, true));
			
			matchList.add(new MatchBasic(new int[]{g.getTeamID(0),g.getTeamID(3)}, true));
			matchList.add(new MatchBasic(new int[]{g.getTeamID(1),g.getTeamID(2)}, true));
			
			matchList.add(new MatchBasic(new int[]{g.getTeamID(2),g.getTeamID(0)}, true));
			matchList.add(new MatchBasic(new int[]{g.getTeamID(3),g.getTeamID(1)}, true));
		}
		
		return matchList;
	}

}
