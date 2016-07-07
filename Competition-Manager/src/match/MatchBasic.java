package match;

import java.io.Serializable;

import other.EqualWildCard;

public class MatchBasic implements EqualWildCard, Serializable{
	
	/**
	 * Needed for Serialization
	 */
	private static final long serialVersionUID = 6125016611257734735L;

	/* Variables */
	
	public enum MatchType {
			GROUP_MATCH, ROUND_MATCH, WILDCARD
	}
	
	private final MatchType matchType;
	
	private final int teamIDs[];
	
	private final String name;
	
	/* Constructors */
	public MatchBasic(MatchType matchType, int teamID[], String name) {
		this.matchType = matchType;
		this.teamIDs = teamID;
		this.name = name;
	}
	
	/* Getter */
	public int[] getTeamIDs() {
		return teamIDs;
	}
	
	public MatchType getMatchType() {
		return matchType;
	}
	
	public String getName() {
		return name;
	}
	
	/* Overrides */
	
	@Override
	public boolean equalsWC(Object obj) {
		if (obj instanceof MatchBasic) {
			MatchBasic test = (MatchBasic) obj;
			if (test.teamIDs == null) {
				return true;
			}
			for (Integer i: test.teamIDs) {
				boolean in = false;
				for (Integer j: this.teamIDs) {
					if (i.equals(j)) {
						in = true;
						break;
					}
				}
				if (!in) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isValid() {
		if (teamIDs.length != 2) {
			return false;
		}
		
		if (teamIDs[0] < 0 || teamIDs[1] < 0) {
			return false;
		}
		
		return true;
	}
	
}