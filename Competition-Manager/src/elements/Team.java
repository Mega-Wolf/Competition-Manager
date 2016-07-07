package elements;

import java.io.Serializable;

/**
 * Team class; holds schoolname, and abbreviation of the school
 */
public class Team implements EqualWildCard, Serializable {
	
	/* Consts */
	
	/**
	 * needed for serialization
	 */
	private static final long serialVersionUID = 1742263971782013992L;

	/**
	 * maximum number of players in a team
	 */
	public static final int TEAM_SIZE_MAX = 23;
	
	/**
	 * minimum number of players in a team
	 */
	public static final int TEAM_SIZE_MIN = 11;

	/* Variables */
	
	/**
	 * the full name of the school
	 */
	private final String school;
	
	/**
	 * an abbreviation of the school
	 */
	private final String abbreviation;

	/* Constructor */
	/**
	 * Creates a Team object; the values are checked with {@link #isValid()}
	 * @param school the full name of the school; neither null nor empty
	 * @param abbreviation abbreviation of the school's name; neither null nor empty; should be 2-5 characters
	 */
	public Team(String school, String abbreviation) {
		this.school = school;
		this.abbreviation = abbreviation;
	}

	/* Getter */
	
	/**
	 * 
	 * @return schoolname; neither null nor empty (if valid)
	 * @see #isValid()
	 */
	public String getSchool() {
		return school;
	}
	
	/**
	 * 
	 * @return abbreviation; neither null nor empty (if valid)
	 * @see #isValid()
	 */
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
	
	@Override
	public boolean isValid() {
		if (school == null || school.length() == 0) {
			return false;
		}
		if (abbreviation == null || abbreviation.length() == 0) {
			return false;
		}
		return true;
	}
}