package other;

import java.io.Serializable;

/**
 * Stores points and goals of on team in a group
 */
public class GroupStat implements EqualWildCard, Serializable {
	// this is an extra class, because
	// - one shall be able to filter by groupStat (therefore not in group)
	// - a team shall not be modified after it was added to the team manager
	// - this way, data of the client and of the server is seperated
	
	/* Consts */
	
	/**
	 * Needed for Serialization
	 */
	private static final long serialVersionUID = -4601109759366282454L;
	
	private int teamID = 0;
	
	private int goals = 0;
	private int points = 0;
	
	public GroupStat(int teamID) {
		this.teamID = teamID;
	}
	
	public int getGoals() {
		return goals;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getTeam() {
		return teamID;
	}

	@Override
	public boolean equalsWC(Object obj) {
		if (obj instanceof GroupStat) {
			GroupStat test = (GroupStat) obj;
			if ((test.points == -1 || test.points == this.points) && (test.goals == -1 || test.goals == this.goals)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isValid() {
		return (points >= 0 && goals >= 0);
	}
	
	
	
}