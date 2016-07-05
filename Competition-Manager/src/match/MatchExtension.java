package match;

public abstract class MatchExtension {
	
	/* Variables */
	protected final int goalsRegular[];
	
	/* Constructor */
	public MatchExtension(int goalsRegular[]) {
		this.goalsRegular = goalsRegular;
	}
	
	/* Getter */
	public int[] getGoalsRegular() {
		return goalsRegular;
	}
	
}