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
	
	/* Setter */
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setTeam(int team) {
		this.team = team;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public void setSurname(String surname) {
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
	
}