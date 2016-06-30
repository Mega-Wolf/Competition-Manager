package other;
import match.GroupMatch;

public class Group {
	
	/* Variables */
	private GroupStat stats[] = new GroupStat[4];
	private final int teamIDs[]; 
	
	/* Constructor */
	public Group(int teamIDs[]) {
		this.teamIDs = teamIDs;
		checkIDs();
	}
	
	/* Methods */
	private void checkIDs() {
		if (teamIDs.length == 4) {
			if (teamIDs[0] == teamIDs[1] || teamIDs[0] == teamIDs[2] || teamIDs[0] == teamIDs[3] || teamIDs[1] == teamIDs[2] || teamIDs[1] == teamIDs[3] || teamIDs[2] == teamIDs[3]) {
				//TODO: Exception
			}
		}
	}
	
	public void updateTable(GroupMatch match) {
		int ids[] = match.getTeamIDs();
	}
	
	
	//TODO: DONO
	private class GroupStat {
		
	}
	
}