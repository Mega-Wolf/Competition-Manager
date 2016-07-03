package other;

public class Team implements EqualWildCard {

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
			Team test = (Team) obj;
			return test.school.equals(school) && test.abbreviation.equals(abbreviation);
		}
		return false;
	}

	@Override
	public boolean equalsWC(Object obj) {
		if (obj instanceof Team) {
			Team test = (Team) obj;
			return (test.school == null || test.school.equals(school)) && (test.abbreviation == null || test.abbreviation.equals(abbreviation));
		}
		return false;
	}
}