package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import network.OperationOld;
import other.Player;
import other.Team;

public class TeamManagementController {
	
	Main main = new Main();
	
	@FXML
	public void newTeam() throws IOException {
		main.showNewScene("NewTeam.fxml", "Neues Team eintragen");
	}
	
	//Trying to read in team
	public void loadingPlayer() throws UnknownHostException, IOException {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int id = 0;
				int PORT_NUMBER = 44532;
				
				Socket server;
				try {
					server = new Socket("127.0.0.1",PORT_NUMBER);
					
					try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream()); ObjectInputStream in = new ObjectInputStream(server.getInputStream());) {
							out.writeObject(OperationOld.GET_TEAM);
							out.writeObject(id);
							out.writeObject(OperationOld.GET_PLAYER);
							//team = (Team) in.readObject();
					} catch (IOException e) {
						e.printStackTrace();
					}
					server.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Trying some simpleStringPropertyStuff with an inner class - propably useless but stays till its uselessness is proved
	public class PlayerProp extends Player{
		private final SimpleStringProperty forename;
		private final SimpleStringProperty surname;
		
		public PlayerProp(int number, int team, String forename, String surname) {
			super(number, team, forename, surname);
			this.forename = new SimpleStringProperty(forename);
			this.surname = new SimpleStringProperty(surname);
		}
		
		// GETTER
		public String getForename() {
			return forename.get();
		}
		
		public String getSurname() {
			return surname.get();
		}
		
		// SETTER
		public void setForename(String forename) {
			this.forename.set(forename);
		}
		
		public void setSurname(String surname) {
			this.surname.set(surname);
		}
	}
	
	// shitty inner class to get team into table view
	public class TeamProp extends Team {
		private final SimpleStringProperty school;
		private final SimpleStringProperty shortSchool;
		private String id;

		public TeamProp(String id, String school, String shortSchool) {
			super(id, school);
			this.school = new SimpleStringProperty(school);
			this.id = id;
			this.shortSchool = new SimpleStringProperty(shortSchool);
		}
		
		// GETTER
		public String getSchool() {
			return school.get();
		}
		
		public String getShortSchool() {
			return shortSchool.get();
		}
		
		public String getId() {
			return id;
		}
		
		// SETTER
		public void setSchool(String school) {
			this.school.set(school);
		}
		
		public void setShortSchool(String shortSchool) {
			this.shortSchool.set(shortSchool);
		}
	}
	
	// Test list of teams - have to be read in by 
	private final ObservableList<TeamProp> teamData = FXCollections.observableArrayList(new TeamProp("GDG", "Gottlieb-Daimler-Gymnasium", "GDG"), new TeamProp("WG", "Wagenburg-Gymnasium", "WG"));
	
	// Own method to store saved teams
	public void addToTeamData(TeamProp team) {
		teamData.add(team);
	}
	
	
	
	// Test list of some players from different teams
	private final List<Player> playerData = new ArrayList<Player>(Arrays.asList(new Player(66, 1, "Heinz Heinrich", "Heinzer"), new Player(88, 1, "Conrad", "Ültje"),
			new Player(23, 1, "Stefan", "Pigman"), new Player(4, 1, "Manuel", "Fokussieren"), new Player(24, 1, "Bore", "Dom"), new Player(6, 1, "Hans", "Wurst"),
			new Player(13, 1, "Faber", "Castell"), new Player(8, 1, "Pudel S.", "Kern"), new Player(9, 1, "Ketch", "Up"), new Player(10, 1, "Spool", "Mittel"), 
			new Player(11, 1, "Dis", "Turbed"), new Player(2, 2, "Sergej", "Jegres")));
	
	// Own method to store saved players
	public void addToPlayerData(Player player) {
		playerData.add(player);
	}
	
	//FXML Stuff
	@FXML
	private TableView<TeamProp> teamTable;
	
	@FXML
	private TableColumn<TeamProp, String> showTeamSchool;
	
	@FXML
	private GridPane grid;
	
	@FXML
	private Button newTeam;
	
	
	public TeamManagementController() {
	}
	
	@FXML
	private void initialize() {
		teamTable.setItems(teamData);
		
		showTeamSchool.setCellValueFactory(new PropertyValueFactory<>("school"));
		teamTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setPlayerData(newValue));
	}
	
	
	private void setPlayerData(TeamProp selectedTeam) {
		int countRow = 2;
		grid.getChildren().clear();
		Label school = new Label("Schule:");
		Label schoolLabel = new Label(selectedTeam.getSchool());
		Label numberLabel = new Label("Rückennr.");
		
		grid.add(school, 0, 0);
		grid.add(schoolLabel, 2, 0);
		grid.add(numberLabel, 1, 1);
		
		for (Player p : playerData) {
			if (p.getTeam() == selectedTeam.getId()) {
				Label currentPlayer = new Label("Spieler " + (countRow-1));
				
				SimpleIntegerProperty number = new SimpleIntegerProperty(p.getNumber());
				Label playerNumber = new Label();
				playerNumber.textProperty().bind(number.asString());
				
				Label playerName = new Label(p.getForename() + " " + p.getSurname());
				
				grid.add(currentPlayer, 0, countRow);
				grid.add(playerNumber, 1, countRow);
				grid.add(playerName, 2, countRow);
				
				countRow++;
			}
		}
	}
	
}
