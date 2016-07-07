package elements;

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
	
	/**
	 * the id of the {@link Team}, to which the GroupStat belongs
	 */
	private int teamID = 0;
	
	/**
	 * the goals of the team in all group games
	 */
	private int goals = 0;
	
	/**
	 * the points of the team in all group games
	 */
	private int points = 0;

	/**
	 * 
	 * @param teamID
	 */
	public GroupStat(int teamID) {
		this.teamID = teamID;
	}
	
	/**
	 * 
	 * @return the goals of the team in all group games
	 */
	public int getGoals() {
		return goals;
	}
	
	/**
	 * 
	 * @return the points of the team in all group games
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * 
	 * @return the id of the {@link Team}
	 */
	public int getTeam() {
		return teamID;
	}

	/**
	 * Adds points and goals to the GroupStat Synchronized, so that there is no
	 * race condition, when 2 matches are added parallel
	 * 
	 * @param points
	 *            the points to add to the GroupStat
	 * @param goals
	 *            the goals to add to the GroupStat
	 */
	public synchronized void addMatchInfo(int points, int goals) {
		this.points += points;
		this.goals += goals;
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