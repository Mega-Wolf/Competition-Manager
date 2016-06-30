package other;
public class Player {
	
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
			return (test.number == -1 || number == test.number) && (test.team == -1 || team == test.team) && (test.surname == null || surname == test.surname) && (test.forename == null || forename == test.forename);
		}
		return false;
	}
}