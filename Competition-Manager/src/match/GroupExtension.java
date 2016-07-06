package match;

public class GroupExtension extends MatchExtension {
	
	
	/* Consts */
	
	/**
	 * Needed for Serialization
	 */
	private static final long serialVersionUID = 4373066656751276656L;
	
	public static final int POINTS_WINNER = 3;
	public static final int POINTS_LOSER = 0;
	public static final int POINTS_DRAW = 1;
	
	/* Variables */
	
	/**
	 * id of the group; see: {@link other.Group Group}
	 */
	private final int groupID;
	
	/* Constructor */
	
	/**
	 * Creates a GroupExtension
	 * @param matchID the id of the match; see {@link MatchBasic}
	 * @param id of the group; see: {@link other.Group Group}
	 * @param goalsRegular goals after 90min
	 */
	public GroupExtension(int matchID, int groupID, int[] goalsRegular) {
		super(matchID, goalsRegular);
		this.groupID = groupID;
	}
	
	/* Getter */
	
	/**
	 * 
	 * @return the id of the group
	 * @see other.Group Group
	 */
	public int getGroupID() {
		return groupID;
	}
	
	/**
	 * 
	 * @return int array with two entries; containing the number of points each team got
	 */
	public int[] getPoints() {
		int[] points = new int[2];
		
		if (goalsRegular[0] == goalsRegular[1]) {
			points[0] = POINTS_DRAW;
			points[1] = POINTS_DRAW;
		} else {
			if (goalsRegular[0] > goalsRegular[1]) {
				points[0] = POINTS_WINNER;
				points[1] = POINTS_LOSER;
			} else {
				points[0] = POINTS_LOSER;
				points[1] = POINTS_WINNER;
			}
		}
		
		return points;
	}
	
	@Override
	public boolean isValid() {
		if (!super.isValid()) {
			return false;
		}
		if (matchID < 0) {
			return false;
		}
		
		if (groupID < 0) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean equalsWC(Object obj) {
		if (obj instanceof GroupExtension) {
			GroupExtension test = (GroupExtension) obj;
			if ((test.matchID == -1 || test.matchID == matchID) && (test.groupID == -1 || test.groupID == this.groupID)) {
				if (test.goalsRegular == null) {
					return true;
				}
				for (Integer i: test.goalsRegular) {
					boolean in = false;
					for (Integer j: this.goalsRegular) {
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
		}
		return false;
	}
	
}