package match;
import java.util.Arrays;
import java.util.Date;

import other.Team;

public abstract class Match {
	
	/* Variables */
	protected int teamIDs[] = new int[2];
	protected int goalsRegular[] = new int[2];
	
	/* Constructors */
	public Match(int teamIDs[]) {
		this.teamIDs = teamIDs;
	}
	
	/* Setter */
	public void setGoalsRegular(int goalsRegular0, int goalsRegular1) {
		//passed as ints instead of array; therefore no check is needed
		//does not check for negative goals, so other games could be supported as well
		//TODO; should thnk about that again
		this.goalsRegular[0] = goalsRegular0;
		this.goalsRegular[1] = goalsRegular1;
	}
	
	/* Getter */
	public int[] getGoalsRegular() {
		return Arrays.copyOf(goalsRegular, 2);			// TODO; only used by server; should be no problem to pass the reference
	}
	
	public int getGoalsRegular(int teamID) {
		if (teamIDs[0] == teamID) {
			return goalsRegular[0];
		}
		if (teamIDs[1] == teamID) {
			return goalsRegular[1];
		}
		
		return 0;	//team was not part of the match
	}

	public int[] getTeamIDs() {
		return Arrays.copyOf(teamIDs, 2);				// TODO; only used by server; should be no problem to pass the reference
	}

}