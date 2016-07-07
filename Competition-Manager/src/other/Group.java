package other;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import match.GroupExtension;

public class Group implements EqualWildCard, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3711357334361749906L;

	/* Consts */
	
	/**
	 * Needed for serialization
	 */
	
	
	/* Variables */
	
	/**
	 * the teamIDs of the 4 teams in the group
	 */
	private final int teamIDs[]; 
	
	/**
	 * name of the group 
	 */
	private final String name;
	
	/* Constructor */
	
	/**
	 * 
	 * @param teamIDs the teamIDs of the four teams in the group; must be checked by the caller
	 */
	public Group(int teamIDs[], String name) {
		this.teamIDs = teamIDs;
		this.name = name;
	}
	
	/* Getter */
	public int getTeamID(int position) {
		if (position < 0 || position >= 4) {
			throw new IllegalArgumentException("Only indices 0 to 3 are allowed");
		}
		return teamIDs[position];
	}
	
	public String getName() {
		return name;
	}

	/* Overrides */
	
	@Override
	public boolean equalsWC(Object obj) {
		if (obj instanceof Group) {
			Group test = (Group) obj;
			
			if (!(test.name == null || this.name.equals(test.name))) {
				return false;
			}
			
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