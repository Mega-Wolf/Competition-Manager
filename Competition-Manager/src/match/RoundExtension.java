package match;

/**
 * Holds the information of a round match - goals after regular 90min, after
 * overtime and after penalty
 */
public class RoundExtension extends MatchExtension {
	
	/* Consts */
	
	/**
	 * Needed for Serialization
	 */
	private static final long serialVersionUID = 7183528941846558038L;
	
	/* Variables */
	
	/**
	 * goals after overtime
	 */
	private int[] goalsOvertime;
	
	/**
	 * goals after shoot-out
	 */
	private int[] goalsPenalty;

	/* Constructors */
	
	/**
	 * Creates a RoundExtension; the game ended after regular time
	 * @param matchID the id of the match; see {@link MatchBasic}
	 * @param goalsRegular goals after 90min
	 */
	public RoundExtension(int matchID, int[] goalsRegular) {
		super(matchID, goalsRegular);
	}
	
	/**
	 * Creates a RoundExtension; the game ended after overtime
	 * @param matchID the id of the match; see {@link MatchBasic}
	 * @param goalsRegular goals after 90min
	 * @param goalsOvertime goals after 120min
	 */
	public RoundExtension(int matchID, int[] goalsRegular, int[] goalsOvertime) {
		this(matchID, goalsRegular);
		this.goalsOvertime = goalsOvertime;
	}
	
	/**
	 * Creates a RoundExtension; the game ended after penalty shoot-out
	 * @param matchID the id of the match; see {@link MatchBasic}
	 * @param goalsRegular goals after 90min
	 * @param goalsOvertime goals after 120min
	 * @param goalsPenalty goals after shoot-out
	 */
	public RoundExtension(int matchID, int[] goalsRegular, int[] goalsOvertime, int[] goalsPenalty) {
		this(matchID, goalsRegular, goalsOvertime);
		this.goalsPenalty = goalsPenalty;
	}
	
	/* Getter */
	
	public int[] getGoalsOvertime() {
		return goalsOvertime;
	}
	
	public int[] getGoalsPenalty() {
		return goalsPenalty;
	}
	
	/**
	 * 
	 * @return the winner of the match; either {@code 0} or {@code 1}; attention: NOT the id of the team 
	 */
	public int getWinner() {
		if (goalsPenalty != null) {
			return (goalsPenalty[1] > goalsPenalty[0]) ? 1 : 0; 
		}
		if (goalsOvertime != null) {
			return (goalsOvertime[1] > goalsOvertime[0]) ? 1 : 0; 
		}
		return (goalsRegular[1] > goalsRegular[0]) ? 1 : 0;
	}
	
	/**
	 * 
	 * @return the loser of the match; either {@code 0} or {@code 1}; attention: NOT the id of the team
	 */
	public int getLoser() {
		return getWinner() == 1 ? 0 : 1;
	}

	@Override
	public boolean isValid() {
		if (!super.isValid()) {
			return false;
		}

		if (goalsRegular[0] != goalsRegular[1]) {
			if (goalsOvertime == null && goalsPenalty == null) {
				return true;
			}
		} else {
			if (goalsOvertime.length != 2) {
				return false;
			}

			if (goalsOvertime[0] < goalsRegular[0] || goalsOvertime[1] < goalsRegular[1]) {
				return false;
			}

			if (goalsOvertime[0] != goalsOvertime[1]) {
				if (goalsPenalty == null) {
					return true;
				}
			} else {
				if (goalsPenalty.length != 2) {
					return false;
				}

				if (goalsPenalty[0] < goalsOvertime[0] || goalsPenalty[1] < goalsOvertime[1]) {
					return false;
				}

				return true;
			}
		}

		return false;
	}

	/**
	 * Checks whether two RoundExtensions are equal, despite some wildcards
	 * note: only matchID and roundID are checked here; not the goals
	 */
	@Override
	public boolean equalsWC(Object obj) {
		if (obj instanceof RoundExtension) {
			RoundExtension test = (RoundExtension) obj;
			if (test.matchID == -1 || test.matchID == matchID) {
				return true;			
			}
		}
		return false;
	}

}
