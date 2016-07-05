package match;

public class GroupExtension extends MatchExtension {
	
	/* Consts */
	public static final int POINTS_WINNER = 3;
	public static final int POINTS_LOSER = 0;
	public static final int POINTS_DRAW = 1;
	
	/* Variables */
	private final int groupID;
	
	/* Constructor */
	public GroupExtension(int groupID, int[] goalsRegular) {
		super(goalsRegular);
		this.groupID = groupID;
	}
	
	/* Getter */
	public int getGroupID() {
		return groupID;
	}
	
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
	
}