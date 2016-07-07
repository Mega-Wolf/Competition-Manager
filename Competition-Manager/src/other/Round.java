package other;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Round implements EqualWildCard, Serializable {
	/* Consts */
	
	/* Variables */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5736401939599581231L;

	private final int teamIDs[];
	
	private final String name;
	
	/* Contructor */
	public Round(int teamIDs[], String name) {
		this.teamIDs = teamIDs;
		this.name = name;
	}
	
	/* Getter */
	public int getTeamID(int position) {
		if (position < 0 || position >= teamIDs.length) {
			throw new IllegalArgumentException("Only indices 0 to " + teamIDs.length + " are allowed");
		}
		return teamIDs[position];
	}
	
	public int[] getTeamIDs() {
		return teamIDs;
	}
	
	public String getName() {
		return name;
	}
	
	/* Overrides */
	
	@Override
	public boolean equalsWC(Object obj) {
		if (obj instanceof Round) {
			Round test = (Round) obj;

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
		
		if (teamIDs.length % 2 != 0) {
			return false;
		}
		Set<Integer> set = new HashSet<Integer>();
		for (Integer i : teamIDs) {
			set.add(i);
		}
		
		if (set.size() != teamIDs.length) {
			return false;
		}
		
		return true;
	}
}