package network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import match.MatchBasic;
import match.RoundExtension;
import other.Group;
import other.GroupStat;
import other.Round;

public class ServerHelper {

	/**
	 * class is just a helper, so it should not be instanciable
	 */
	private ServerHelper() {
	}

	/**
	 * Creates 4 groups, each containing 4 teams
	 * 
	 * @param teamList
	 *            a list of 16 team IDs; the caller must check this restriction
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
			
			groupList.add(new Group(dummy, "Gruppe " +(char) ('A' + group_cnt))) ;
		}

		return groupList;
	}
	
	public static Round createNextRound(List<Integer> teamIDs) {
		int[] teamIDArray = new int[teamIDs.size()];
		
		for (int i = 0; i < teamIDs.size(); i++) {
			teamIDArray[i] = teamIDs.get(i);
		}
		
		Round round = new Round(teamIDArray, "fdf"); 		
		
		return round;
	}
	

	public static List<MatchBasic> createGroupMatches(List<Group> groupList) {
		List<MatchBasic> matchList = new ArrayList<MatchBasic>();
		for (Group g : groupList) {
			matchList.add(
					new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[] { g.getTeamID(0), g.getTeamID(1) }, g.getName() + ": Spiel 1"));
			matchList.add(
					new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[] { g.getTeamID(2), g.getTeamID(3) }, g.getName() + ": Spiel 2"));

			matchList.add(
					new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[] { g.getTeamID(0), g.getTeamID(3) }, g.getName() + ": Spiel 3"));
			matchList.add(
					new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[] { g.getTeamID(1), g.getTeamID(2) }, g.getName() + ": Spiel 4"));

			matchList.add(
					new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[] { g.getTeamID(2), g.getTeamID(0) }, g.getName() + ": Spiel 5"));
			matchList.add(
					new MatchBasic(MatchBasic.MatchType.GROUP_MATCH, new int[] { g.getTeamID(3), g.getTeamID(1) }, g.getName() + ": Spiel 6"));
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

	/*
	 * public static List<MatchBasic> createRoundMatch(List<Round> teamIDs) {
	 * List<MatchBasic> matchBasicList = new ArrayList<MatchBasic>(); for (int i
	 * = 0; i < teamIDs.size() / 2; i++) { matchBasicList.add(new
	 * MatchBasic(MatchBasic.MatchType.ROUND_MATCH, new int[]{teamIDs.remove(0),
	 * teamIDs.remove(0)})); }
	 * 
	 * 
	 * return matchBasicList; }
	 */
	
	/**
	 * Creates new matches for the next round; takes round matches as input
	 * @param matchBasicList
	 * @param roundExtensionList
	 * @return
	 */
	public static List<MatchBasic> createRoundMatch(List<MatchBasic> matchBasicList,
			List<RoundExtension> roundExtensionList) {
		List<MatchBasic> matchBasicReturnList = new ArrayList<MatchBasic>();

		String matchname = null;
		switch (matchBasicList.size()) {
		case 8:
			matchname = "Viertelfinale";
			break;
		case 4:
			matchname = "Halbfinale";
			break;
		case 2:
			matchname = "Finale";
			break;
		}
		
		for (int i = 0; i < matchBasicList.size(); i += 2) {
			matchBasicList.add(new MatchBasic(MatchBasic.MatchType.ROUND_MATCH,
					new int[] { matchBasicList.get(i).getTeamIDs()[roundExtensionList.get(i).getWinner()],
							matchBasicList.get(i + 1).getTeamIDs()[roundExtensionList.get(i + 1).getWinner()] }, matchname + " " + (matchBasicList.size() != 2 ? String.valueOf(i + 1) : "")));
		}
		
		if (matchBasicList.size() == 2) {
			matchBasicList.add(new MatchBasic(MatchBasic.MatchType.ROUND_MATCH,
					new int[] { matchBasicList.get(0).getTeamIDs()[roundExtensionList.get(0).getLoser()],
							matchBasicList.get(1).getTeamIDs()[roundExtensionList.get(1).getLoser()] }, "Spiel um Platz 3"));
		}

		return matchBasicReturnList;
	}

}
