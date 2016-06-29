package other;

public class Team{
	
	/* Consts */
	public static final int TEAM_SIZE_MAX = 23;
	public static final int TEAM_SIZE_MIN = 11;
	
	/* Variables */
	private final String school;
	private final String abbreviation;
	
	/* Constructor */
	public Team(String school, String abbreviation) {
		this.school = school;
		this.abbreviation = abbreviation;
	}
	
	/* Getter */
	public String getSchool() {
		return school;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	
	/* Overrides */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Team) {
			return ((Team) obj).school.equals(school);
		}
		return false;
	}
}