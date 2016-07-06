package match;

import java.io.Serializable;

import other.EqualWildCard;

/**
 * Extends the information in MatchBasic to either a group or a round match
 */
public abstract class MatchExtension implements EqualWildCard, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7178611501302734428L;
	/* Variables */
	
	/**
	 * goals after 90min
	 */
	protected final int goalsRegular[];
	
	/**
	 * the id of the match; see: {@link MatchBasic}
	 */
	protected final int matchID;
	
	
	/* Constructor */
	public MatchExtension(int matchID, int goalsRegular[]) {
		this.goalsRegular = goalsRegular;
		this.matchID = matchID;
	}
	
	/* Getter */
	
	/**
	 * 
	 * @return array wit
	 */
	public int[] getGoalsRegular() {
		return goalsRegular;
	}
	
	/**
	 * 
	 * @return the id of the {@link MatchBasic}
	 */
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