package other;
public class Player implements EqualWildCard{
	
	/* Variables */
	private int number;
	private int team;
	private String forename;
	private String surname;
	
	/* Constructor */
	public Player(int number, int team, String forename, String surname) {
		
		//if ((number > 0 && number < 100) && forename.) {
			this.number = number;
			this.team = team;
			this.forename = forename;
			this.surname = surname;
		//}
		
		
		
	}
		
	/* Getter */
	public int getNumber() {
		return number;
	}
	
	public int getTeam() {
		return team;
	}
	
	public String getForename() {
		return forename;
	}
	
	public String getSurname() {
		return surname;
	}
	
	/* Overrides */
	@Override
	public boolean equals(Object obj) {		
		if (obj instanceof Player) {
			Player test = (Player) obj;
			return (number == test.number) && (team == test.team) && (surname.equals(test.surname)) && (forename.equals(test.forename));
		}
		return false;
	}

	@Override
	public boolean equalsWC(Object obj) {
		if (obj instanceof Player) {
			Player test = (Player) obj;
			return (test.number == -1 || number == test.number) && (test.team == -1 || team == test.team) && (test.surname == null || surname.equals(test.surname)) && (test.forename == null || forename.equals(test.forename));
		}
		return false;
	}
}