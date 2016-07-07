package network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import match.MatchBasic;
import match.MatchBasic.MatchType;
import match.RoundExtension;
import other.Group;
import other.GroupStat;
import other.Round;
import other.Team;

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
	
	/**
	 * Creates a round
	 * @param teamIDs the IDs of all rounds in the game 
	 * @return the created round
	 */
	public static Round createRound(List<Integer> teamIDs) {
		int[] teamIDArray = new int[teamIDs.size()];
		
		for (int i = 0; i < teamIDs.size(); i++) {
			teamIDArray[i] = teamIDs.get(i);
		}
		
		String roundname = null;
		switch(teamIDs.size()) {
		case 8:
			roundname = "Runde der letzten 8";
			break;
		case 4:
			roundname = "Runde der letzten 4";
			break;
		case 2:
		roundname = "Finalrunde";
		break;
		}
		
		Round round = new Round(teamIDArray, roundname); 		
		
		return round;
	}
	
	/**
	 * Creates the MatchBasics for the group matches
	 * @param groupList
	 * @return
	 */
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
	
	/**
	 * Creates a groupStat for all Teams
	 * @param teamIDs
	 * @return a list of the GroupStats
	 */
	public static List<GroupStat> createGroupStats(List<Integer> teamIDs) {
		List<GroupStat> groupStatList = new ArrayList<GroupStat>();

		for (Integer i : teamIDs) {
			groupStatList.add(new GroupStat(i));
		}

		return groupStatList;
	}

	/**
	 * Creates the round matches after the group matches, therefore different parameters are needed
	 * @return
	 */
	public static List<MatchBasic> createFirstRoundMatch(List<Group> groupList, List<Team> teamList, List<GroupStat> groupStatList) {
		List<MatchBasic> matchBasicList = new ArrayList<MatchBasic>();
		
		List<List<Integer>> teamsInGroup = new ArrayList<List<Integer>>();
		
		for (Group g : groupList) {
			int[] teamIDs = g.getTeamIDs();
			
			Arrays.asList(teamIDs);
			
			List<Integer> groupStatListInGroup = groupStatList
					.stream()
					.filter(gs -> Arrays.asList(teamIDs).contains(gs.getTeam()))
					.sorted((t1,t2) -> Integer.compare(t1.getTeam(), t2.getTeam()))			//if two teams have the same points, the one with the lower ID is preferred (first comes; first servs)
//forgot to include goals of opponent; therefore comparing goalsvalues is skipped :P					//.sorted((t1,t2) -> Integer.compare(t2.getPoints(), t1.getPoints()))
					.sorted((t1,t2) -> Integer.compare(t2.getPoints(), t1.getPoints()))
					.map(gs -> gs.getTeam())
					.collect(Collectors.toList());
			teamsInGroup.add(groupStatListInGroup);
		}
		
		matchBasicList.add(new MatchBasic(MatchType.ROUND_MATCH, new int[]{teamsInGroup.get(0).get(0), teamsInGroup.get(1).get(1)}, "Viertelfinale 1"));
		matchBasicList.add(new MatchBasic(MatchType.ROUND_MATCH, new int[]{teamsInGroup.get(2).get(0), teamsInGroup.get(3).get(1)}, "Viertelfinale 2"));
		matchBasicList.add(new MatchBasic(MatchType.ROUND_MATCH, new int[]{teamsInGroup.get(1).get(0), teamsInGroup.get(0).get(1)}, "Viertelfinale 3"));
		matchBasicList.add(new MatchBasic(MatchType.ROUND_MATCH, new int[]{teamsInGroup.get(3).get(0), teamsInGroup.get(2).get(1)}, "Viertelfinale 4"));
		
		return matchBasicList;
	}
	
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
		
		//this is handled by createFirstRoundMatch
		/*case 8:
			matchname = "Viertelfinale";
			break;*/
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
		
		return matchBasicReturnList;
	}

}
