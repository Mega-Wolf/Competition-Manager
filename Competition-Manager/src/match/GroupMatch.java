package match;

import java.util.Date;

import other.Team;
import other.Group;

public class GroupMatch extends Match {
	
	/* Consts */
	public static final int POINTS_WINNER = 3;
	public static final int POINTS_LOSER = 0;
	public static final int POINTS_DRAW = 1;
	
	/* Variables */
	private final int groupID;
	
	/* Constructors */
	public GroupMatch(int[] teamIDs, int groupID) {
		super(teamIDs);
		this.groupID = groupID;
	}
	
	/* Getter */
	public int getGroupID() {
		return groupID;
	}
	
	public int[] getPoints() {
		//TODO: TEST
		
		int[] points = new int[2];
		
		if (goalsRegular[0] == goalsRegular[1]) {
			points[0] = POINTS_DRAW;
			points[1] = POINTS_DRAW;
		} else {
			if (goalsRegular[0] > goalsRegular[1]) {
				points[0] = POINTS_WINNER;
				points[1] = POINTS_LOSER;
			} else {
				points[0] = POINTS_LOSER;
				points[1] = POINTS_WINNER;
			}
		}
		
		return points;
	}
	
	/*
	 * int[] ids = {1,2};
	 * int groupId = 1;
	 * int[] goals = {2,1};
	 * GroupMatch g = new GroupMatch(ids, new Date(), groupId, goals);
	 * 
	 * int[] expectedResult = {GroupMatch.POINTS_WINNER, GroupMatch.POINTS_LOSER}
	 * 
	 * Assert.assertEquals( g.getPoints() ,  expectedResult );
	 */
	
	public int getPoints(int teamID) {
		//TODO: TEST
		if (goalsRegular[0] == goalsRegular[1]) {
			if (teamIDs[0] == teamID || teamIDs[1] == teamID) {
				return POINTS_DRAW;
			}
		} else {
			if ((teamIDs[0] == teamID && goalsRegular[0] > goalsRegular[1]) || (teamIDs[1] == teamID && goalsRegular[1] > goalsRegular[0])) {
				return POINTS_WINNER;
			} else {
				return POINTS_LOSER;
			}
		}
		return 0; //Should not be reached
	}

}
