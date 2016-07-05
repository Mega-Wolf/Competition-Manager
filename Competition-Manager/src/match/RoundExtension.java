package match;

public class RoundExtension extends MatchExtension{

	private int[] goalsOvertime;
	private int[] goalsPenalty;
	
	/* Constructor */
	public RoundExtension(int[] goalsRegular) {
		super(goalsRegular);
		//TODO; Exception, if g0 == g1; if ![2]
	}
	
	public RoundExtension(int[] goalsRegular, int[] goalsOvertime) {
		this(goalsRegular);
		//TODO; Eception, if g0 != g1; if ![2]; go0+gO0 == g1+gO1
	}
	public RoundExtension(int[] goalsRegular, int[] goalsOvertime, int[] goalsPenalty) {
		this(goalsRegular, goalsOvertime);
		//TODO; Eception, if g0 != g1; if ![2]; go0+gO0 != g1+gO1 
	}
	
}
