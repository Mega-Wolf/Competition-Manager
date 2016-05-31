package other;
public class Player {
	
	/* Variables */
	private String forename;
	private String surname;
	private int number;
	//private 
	
	/* Constructor */
	public Player(String forename, String surname, int number) {
		this.forename = forename;
		this.surname = surname;
		this.number = number;
	}
	
	/* Setter */
	
	/* Getter */
	public String getForename() {
		return forename;
	}
	
	public String getSurname() {
		return surname;
	}

	public int getNumber() {
		return number;
	}
}