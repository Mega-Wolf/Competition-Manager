package network;

import java.util.ArrayList;
import java.util.List;

import match.MatchBasic;
import other.Group;
import other.GroupStat;

public class ServerHelper {
	
	/**
	 * class is just a helper, so it should not be instanciable
	 */
	private ServerHelper() {
	}
	
	/**
	 * Creates 4 groups, each containing 4 teams
	 * @param teamList a list of 16 team IDs; the caller must check this restriction
	 * @return list of the created groups
	 */
	public static List<Group> createGroups(List<Integer> teamIDs) {
		List<Group> groupList = new ArrayList<Group>();
		
		List<ArrayList<Integer>> groupIDs = new ArrayList<ArrayList<Integer>>(); 
		
		for (int group_cnt = 0; group_cnt < 4; group_cnt++) {
			groupIDs.add(new ArrayList<Integer>());
			for (int team_cnt = 0; team_cnt < 4; team_cnt++) {
				int rndNumber = (int) (Math.random() * teamIDs.size());
				groupIDs.get(group_cnt).add(teamIDs.get(rndNumber));
				teamIDs.remove(rndNumber);
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
			matchList.add(new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[]{g.getTeamID(0),g.getTeamID(1)}));
			matchList.add(new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[]{g.getTeamID(2),g.getTeamID(3)}));
			
			matchList.add(new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[]{g.getTeamID(0),g.getTeamID(3)}));
			matchList.add(new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[]{g.getTeamID(1),g.getTeamID(2)}));
			
			matchList.add(new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[]{g.getTeamID(2),g.getTeamID(0)}));
			matchList.add(new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[]{g.getTeamID(3),g.getTeamID(1)}));
		}
		
		return matchList;
	}
	
	public static List<GroupStat> createGroupStats(List<Integer> teamIDs) {
		List<GroupStat> groupStatList = new ArrayList<GroupStat>();
		
		for (Integer i : teamIDs) {
			groupStatList.add(new GroupStat(i));
		}
		
		return groupStatList;
	}

}
