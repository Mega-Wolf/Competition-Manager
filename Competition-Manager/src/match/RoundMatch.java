package match;

import java.util.Date;

import other.Round;
import other.Team;

public class RoundMatch extends Match{

	/* Variables */
	private int roundID;
	private int[] goalsAfterOvertime = new int[2];
	private int[] goalsAfterPenalty = new int[2];
	
	/* Constructor */
	public RoundMatch(int[] teamIDs, int roundID) {
		super(teamIDs);
		this.roundID = roundID;
	}
	
	/* Setter */
	//TODO: Set everything at once
	/*
	public void setGoalsAfterOvertime(int goals0, int goals1) {
		if (goalsRegular[0] == goalsRegular[1]) {
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
	*/
	/* Getter */
	public int getRoundID() {
		return roundID;
	}
	
	public int getWinner() {
		//TODO: TODO
	}

}