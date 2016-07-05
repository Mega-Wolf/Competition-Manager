package match;

import java.io.Serializable;

import other.EqualWildCard;

public class MatchBasic implements EqualWildCard, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6125016611257734735L;

	/* Variables */
	private final int teamIDs[];
	
	private final boolean groupGame;
	
	/* Constructors */
	public MatchBasic(int teamID[], boolean groupGame) {
		this.teamIDs = teamID;
		this.groupGame = groupGame;
	}
	
	/* Getter */
	public int[] getITeamIDs() {
		return teamIDs;
	}
	
	public boolean isGroupGame() {
		return groupGame;
	}

	@Override
	public boolean equalsWC(Object obj) {
		
		return false;
	}

	@Override
	public boolean isValid() {
		if (teamIDs.length != 2) {
			return false;
		}
		
		return true;
	}
	
}