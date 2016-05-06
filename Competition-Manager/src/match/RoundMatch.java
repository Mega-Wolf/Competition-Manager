package match;

import java.util.Date;

import other.Round;
import other.Team;

public class RoundMatch extends Match{

	/* Variables */
	private Round round;
	private int[] goalsAfterOvertime = new int[2];
	private int[] goalsAfterPenalty = new int[2];
	
	/* Constructor */
	public RoundMatch(Team team0, Team team1, Date kickoff, Round round) {
		super(team0, team1, kickoff);
		this.round = round;
	}
	
	public RoundMatch(Team team0, Team team1, Date kickoff, Round round, int goals0, int goals1) {
		this(team0, team1, kickoff, round);
		goals[0] = goals0;
		goals[1] = goals1;
	}
	
	/* Setter */
	public void setGoalsAfterOvertime(int goals0, int goals1) {
		if (goals[0] == goals[1]) {
			goalsAfterOvertime[0] = goals0;
			goalsAfterOvertime[1] = goals1;
		} else {
			//TODO; Exception
		}
	}
	
	public void setGoalsAfterPenalty(int goals0, int goals1) {
		if (goalsAfterOvertime[0] == goalsAfterOvertime[1]) {
			goalsAfterPenalty[0] = goals0;
			goalsAfterPenalty[1] = goals1;
		} else {
			//TODO Exception
		}
		
	}
	
	/* Getter */
	public Round getRound() {
		return round;
	}

	public Team getWinner() {
		return teams[0]; //TODO
	}
}