package other;
import java.io.Serializable;

import match.GroupMatch;

public class Group implements Serializable {
	
	/* Consts */
	
	/**
	 * Needed for serialization
	 */
	private static final long serialVersionUID = 8696767095231107411L;
	
	/* Variables */
	private GroupStat stats[] = new GroupStat[4];
	
	/**
	 * the teamIDs of the 4 teams in the group
	 */
	private final int teamIDs[]; 
	
	/* Constructor */
	
	/**
	 * 
	 * @param teamIDs the teamIDs of the four teams in the group; must be checked by the caller
	 */
	public Group(int teamIDs[]) {
		this.teamIDs = teamIDs;
		//checkIDs();
	}
	
	/* Methods */
	/*private void checkIDs() {
		if (teamIDs.length == 4) {
			if (teamIDs[0] == teamIDs[1] || teamIDs[0] == teamIDs[2] || teamIDs[0] == teamIDs[3] || teamIDs[1] == teamIDs[2] || teamIDs[1] == teamIDs[3] || teamIDs[2] == teamIDs[3]) {
				//TODO: Exception
			}
		}
	}*/
	
	public void updateTable(GroupMatch match) {
		int ids[] = match.getTeamIDs();
	}
	
	
	//TODO: DONO
	private class GroupStat {
		
	}
	
}