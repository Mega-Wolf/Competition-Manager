package match;

import java.util.Date;

import other.Team;
import other.Group;

public class GroupMatch extends Match {

	/* Variables */
	private Group group;
	
	/* Constructors */
	public GroupMatch(Team team0, Team team1, Date kickoff, Group group) {
		super(team0, team1, kickoff);
		this.group = group;
	}
	
	public GroupMatch(Team team0, Team team1, Date kickoff, Group group, int goals0, int goals1) {
		this(team0, team1, kickoff, group);
		goals[0] = goals0;
		goals[1] = goals1;
	}
	
	/* Getter */
	public Group getGroup() {
		return group;
	}

}
