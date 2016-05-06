package match;
import java.util.Arrays;
import java.util.Date;

import other.Team;

public abstract class Match {
	
	/* Variables */
	protected Team teams[] = new Team[2];
	protected int goals[] = new int[2];
	protected Date kickoff;
	
	/* Constructors */
	public Match(Team team0, Team team1, Date kickoff) {
		teams[0] = team0;
		teams[1] = team1;
		this.kickoff = kickoff;
	}
	
	public Match(Team team0, Team team1, Date kickoff, int goals0, int goals1) {
		this(team0, team1, kickoff);
		goals[0] = goals0;
		goals[1] = goals1;
	}
	
	/* Setter */
	public void setGoals(int goals0, int goals1) {
		goals[0] = goals0;
		goals[1] = goals1;
	}
	
	/* Getter */
	public int[] getGoals() {
		return Arrays.copyOf(goals, 2);
	}

	public Team[] getTeams() {
		return Arrays.copyOf(teams, 2);
	}
	
	public Date getKickoff() {
		return kickoff;
	}
}