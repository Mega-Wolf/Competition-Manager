package match;

import java.io.Serializable;

import other.EqualWildCard;

public abstract class MatchExtension implements EqualWildCard, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7178611501302734428L;
	/* Variables */
	protected final int goalsRegular[];
	protected final int matchID;
	
	
	/* Constructor */
	public MatchExtension(int matchID, int goalsRegular[]) {
		this.goalsRegular = goalsRegular;
		this.matchID = matchID;
	}
	
	/* Getter */
	public int[] getGoalsRegular() {
		return goalsRegular;
	}
	
	public int getMatchID() {
		return matchID;
	}
	
	@Override
	public boolean isValid() {
		
		if (goalsRegular.length != 2) {
			return false;
		}
		
		if (goalsRegular[0] < 0 || goalsRegular[1] < 0) {
			return false;
		}
		
		return true;
	}
	
}