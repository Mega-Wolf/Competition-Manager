package other;

import java.io.Serializable;

public class Team implements EqualWildCard, Serializable {
	
	/* Consts */
	
	/**
	 * needed for serialization
	 */
	private static final long serialVersionUID = 2189452111107525742L;
	
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
	 * Creates a Team object
	 * @param school the full name of the school; neither null nor empty
	 * @param abbreviation abbreviation of the school's name; neither null nor empty; should be 2-5 characters
	 */
	public Team(String school, String abbreviation) {
		this.school = school;
		this.abbreviation = abbreviation;
		
		if (school == null || school.length() == 0) {
			throw new IllegalArgumentException("Empty schoolname is not allowed");
		}
		if (abbreviation == null || abbreviation.length() == 0) {
			throw new IllegalArgumentException("Empty abbreviation is not allowed");
		}
	}

	/* Getter */
	
	/**
	 * 
	 * @return schoolname; neither null nor empty
	 */
	public String getSchool() {
		return school;
	}
	
	/**
	 * 
	 * @return abbreviation; neither null nor empty
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
}