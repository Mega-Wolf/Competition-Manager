package other;

import java.io.Serializable;

/**
 * Player class; holds trikot-number, teamID, surename and forename
 */
public class Player implements EqualWildCard, Serializable {
	
	/* Consts */
	
	/**
	 * needed for serialization
	 */
	private static final long serialVersionUID = 4487365076046935560L;
	
	/* Variables */
	
	/**
	 * trikot-number of player; 1 - 99
	 */
	private int number;

	/**
	 * id of the player's team
	 */
	private int team;

	/**
	 * forename of the player; not checked, whether sensible or not
	 */
	private String forename;

	/**
	 * surname of the player; not checked, whether sensible or not
	 */
	private String surname;

	/* Constructor */

	/**
	 * Create a player; the values are checked with {@link #isValid()}
	 * 
	 * @param number
	 *            trikot-number between 1 and 99
	 * @param team
	 *            team ID; 0 or above; references {@link Team}
	 * @param forename
	 *            neither null nor empty
	 * @param surname
	 *            neither null nor empty
	 */
	public Player(int number, int team, String forename, String surname) {
		this.number = number;
		this.team = team;
		this.forename = forename;
		this.surname = surname;
	}

	/* Getter */

	/**
	 * 
	 * @return trikot-number; between 1 and 99 (if valid)
	 * @see #isValid()
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * 
	 * @return teamID; 0 or above (if valid)
	 * @see #isValid()
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * 
	 * @return forename; neither null nor empty (if valid)
	 * @see #isValid()
	 */
	public String getForename() {
		return forename;
	}

	/**
	 * 
	 * @return surname; neither null nor empty (if valid)
	 * @see #isValid()
	 */
	public String getSurname() {
		return surname;
	}
	
	/* Overrides */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player) {
			Player test = (Player) obj;
			return (number == test.number) && (team == test.team) && (surname.equals(test.surname))
					&& (forename.equals(test.forename));
		}
		return false;
	}

	@Override
	public boolean equalsWC(Object obj) {
		if (obj instanceof Player) {
			Player test = (Player) obj;
			return (test.number == -1 || number == test.number) && (test.team == -1 || team == test.team)
					&& (test.surname == null || surname.equals(test.surname))
					&& (test.forename == null || forename.equals(test.forename));
		}
		return false;
	}
	
	@Override
	public boolean isValid() {
		if (number <= 0 || number >= 100) {
			return false;
		}
		if (team < 0) {
			return false;
		}
		if (forename == null || forename.length() == 0) {
			return false;
		}
		if (surname == null || surname.length() == 0) {
			return false;
		}
		return true;
	}
}