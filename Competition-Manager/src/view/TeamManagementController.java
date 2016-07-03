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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import network.Operand;
import network.Operation;
import network.OperationOld;
import other.Player;
import other.Team;

public class TeamManagementController {
	
	Main main = new Main();
	
	@FXML
	public void newTeam() throws IOException {
		main.showNewScene("NewTeam.fxml", "Neues Team eintragen");
	}
	
	private Team team;
	private int teamID;
	
	//Trying to read in team
	public void loadingTeam() throws UnknownHostException, IOException {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int PORT_NUMBER = 44532;
				
				Socket server;
				try {
					server = new Socket("127.0.0.1",PORT_NUMBER);
					
					try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream()); ObjectInputStream in = new ObjectInputStream(server.getInputStream());) {
							out.writeObject(Operation.GET);
							out.writeObject(Operand.TEAM);
							out.writeObject(teamID);
							
							team = (Team) in.readObject();
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
	
	// Test list of teams - have to be read in by 
	private final ObservableList<Team> teamTestData = FXCollections.observableArrayList(new Team("Gottlieb-Daimler-Gymnasium","GDG"), new Team("Wagenburg-Gymnasium","WG"));
	
	// Own method to store saved teams
	public void addToTeamData(Team team) {
		teamTestData.add(team);
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
	private TableView<Team> teamTable;
	
	@FXML
	private TableColumn<Team, String> showTeamSchool;
	
	@FXML
	private TableColumn<Team, String> showTeamAbbreviation;
	
	@FXML
	private GridPane grid;
	
	@FXML
	private Button newTeam;
	
	
	public TeamManagementController() {
	}
	
	@FXML
	private void initialize() {
		teamTable.setItems(teamTestData);
		
		showTeamSchool.setCellValueFactory(new PropertyValueFactory<>("school"));
		showTeamAbbreviation.setCellValueFactory(new PropertyValueFactory<>("abbreviation"));
		teamTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setPlayerData(newValue));
	}
	
	
	private void setPlayerData(Team selectedTeam) {
		int countRow = 2;
		grid.getChildren().clear();
		Label school = new Label("Schule:");
		Label schoolLabel = new Label(selectedTeam.getSchool());
		Label numberLabel = new Label("Rückennr.");
		
		grid.add(school, 0, 0);
		grid.add(schoolLabel, 2, 0);
		grid.add(numberLabel, 1, 1);
		
		for (Player p : playerData) {
			if (p.getTeam() == teamID) {
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
