package match;

public class MatchRound extends MatchExtension{

	private int[] goalsOvertime;
	private int[] goalsPenalty;
	
	/* Constructor */
	public MatchRound(int[] goalsRegular) {
		super(goalsRegular);
		//TODO; Exception, if g0 == g1; if ![2]
	}
	
	public MatchRound(int[] goalsRegular, int[] goalsOvertime) {
		this(goalsRegular);
		//TODO; Eception, if g0 != g1; if ![2]; go0+gO0 == g1+gO1
	}
	public MatchRound(int[] goalsRegular, int[] goalsOvertime, int[] goalsPenalty) {
		this(goalsRegular, goalsOvertime);
		//TODO; Eception, if g0 != g1; if ![2]; go0+gO0 != g1+gO1 
	}
	
}
