package other;
public class Player implements EqualWildCard<Player>{
	
	/* Variables */
	private int number;
	private int team;
	private String forename;
	private String surname;
	
	/* Constructor */
	public Player(int number, int team, String forename, String surname) {
		this.number = number;
		this.team = team;
		this.forename = forename;
		this.surname = surname;
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
	public boolean equals(Object arg0) {		
		if (arg0 instanceof Player) {
			Player test = (Player) arg0;
			return (number == test.number) && (team == test.team) && (surname.equals(test.surname)) && (forename.equals(test.forename));
		}
		return false;
	}

	@Override
	public boolean equalsWC(Player obj) {
		return (obj.number == -1 || number == obj.number) && (obj.team == -1 || team == obj.team) && (obj.surname == null || surname.equals(obj.surname)) && (obj.forename == null || forename.equals(obj.forename));
	}
}