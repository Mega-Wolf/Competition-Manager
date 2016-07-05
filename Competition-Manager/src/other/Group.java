package other;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import match.MatchGroup;

public class Group implements EqualWildCard, Serializable {
	
	/* Consts */
	
	/**
	 * Needed for serialization
	 */
	private static final long serialVersionUID = 8696767095231107411L;
	
	/* Variables */
	private GroupStat stats[] = new GroupStat[4];
	
	/**
	 * the teamIDs of the 4 teams in the group
	 */
	private final int teamIDs[]; 
	
	/* Constructor */
	
	/**
	 * 
	 * @param teamIDs the teamIDs of the four teams in the group; must be checked by the caller
	 */
	public Group(int teamIDs[]) {
		this.teamIDs = teamIDs;
	}
	
	// TODO
	public void updateTable(List<MatchGroup> groupMatch) {
		int ids[] = groupMatch.getTeamIDs();
	}
	
	/* Getter */
	public int getTeamID(int position) {
		if (position < 0 || position >= 4) {
			throw new IllegalArgumentException("Only indices 0 to 3 are allowed");
		}
		return teamIDs[position];
	}
	
	
	//TODO: DONO
	private class GroupStat {
		
	}

	/* Overrides */
	
	@Override
	public boolean equalsWC(Object obj) {
		if (obj instanceof Group) {
			Group test = (Group) obj;
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
		if (teamIDs.length != 4) {
			return false;
		}
		Set<Integer> set = new HashSet<Integer>();
		for (Integer i : teamIDs) {
			set.add(i);
		}
		
		if (set.size() != 4) {
			return false;
		}
		
		return true;
	}
	
}