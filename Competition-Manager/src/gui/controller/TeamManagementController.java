package gui.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import gui.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import network.Operand;
import network.Operation;
import other.Player;
import other.Team;

public class TeamManagementController {
	
	Main main = new Main();
	
	@FXML
	private ButtonBar buttonbar;
	
	boolean finishedManagement = false;
	
	public boolean isFinished() {
		return finishedManagement;
	}
	
	/**
	 * Method of the event that happens when pressing the "Neues Team"-Button. It opens a
	 * new window in which the user gets the possibility to type in his team's information.
	 * @throws IOException
	 */
	@FXML
	public void newTeam() throws IOException {
		if (teamMap.size() < 16) {
			main.showNewScene("view/NewTeam.fxml", "Neues Team eintragen");
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText("Sie haben bereits genug Teams eingetragen!");
			alert.setContentText("Hinweis: Sie dürfen nur 16 Teams für das diesjährige Fußballturnier eintragen; sind Sie mit "
					+ "Ihren Eingaben zufrieden, können Sie die Teaminitialisierung mit \"Fertig\" abschließen."
					+ " Möchten Sie noch Änderungen vornehmen, können Sie Teams löschen und neu eintragen.");
			alert.showAndWait();
		}
	}
	
	/**
	 * Checks if the user already added 16 teams which are needed to start the game. If there aren't enough teams, the user gets
	 * an error dialog shown which gives a hint about how to interact with the program correctly. Otherwise the user gets an
	 * information that the saving of enough teams was successful and that the competition is ready to start.
	 */
	@FXML
	public void handleFertig() {
		if (teamMap.keySet().size() == 16) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Teamverwaltung abschließen");
			alert.setHeaderText("Haben Sie alle Teams korrekt eingetragen und sind bereit, den Wettkampf zu starten?");
			alert.setContentText("Hinweis: Die Teams können nachträglich nicht mehr geändert werden!");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				buttonbar.getButtons().clear();
				finishedManagement = true;
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText("Sie haben noch nicht genug Teams eingetragen!");
			alert.setContentText("Hinweis: Sie benötigen 16 Teams, um die Teamverwaltung abzuschließen und den Wettkampf"
					+ " zu starten!");
			alert.showAndWait();
		}
	}
	
	/**
	 * Method to delete teams. Checks the selected team in the team-table and deletes it from the table and from the 
	 * 
	 */
	@FXML
	public void handleDelete() {
		if(teamTable.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setContentText("Sie haben kein Team ausgewählt.");
			alert.showAndWait();
		} else {
			int selectedTeamIndex = teamTable.getSelectionModel().getSelectedIndex();
			Team teamToDelete = teamTable.getItems().get(selectedTeamIndex);
			Set<Integer> keySet = teamMap.keySet();
			int indexToDelete = -1;
			for (int k : keySet) {
				if (teamMap.get(k).equals(teamToDelete)) {
					indexToDelete = k;
				}
			}
			//deleteTeam(indexToDelete);
			teamTable.getItems().remove(selectedTeamIndex);
		}
	}
	
	
	private Team team;
	private Map<Integer, Team> teamMap = new ConcurrentHashMap<Integer,Team>();
	private int teamID;
	
	//TODO: read in team
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
	
	//TODO: delete Team
	public void deleteTeam(int deleteID) throws UnknownHostException, IOException {
		
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
	private final ObservableList<Team> teamTestData = FXCollections.observableArrayList();
	
	private void readInTeams(Map<Integer,Team> team) {
		teamTestData.clear();
		team.put(1, new Team("Geschwister-Scholl-Schuhe","GSS"));
		team.put(2, new Team("Wagenburg-Gymnasium","WG"));
		team.put(3, new Team("Geschwister-Scholl-Schuheb","GSSB"));
		team.put(4, new Team("Wagenburg-Gymnasiumb","WGB"));
		team.put(5, new Team("Geschwister-Scholl-Schuhec","GSSC"));
		team.put(6, new Team("Wagenburg-Gymnasiumc","WGC"));
		team.put(7, new Team("Geschwister-Scholl-Schuhed","GSSD"));
		team.put(8, new Team("Wagenburg-Gymnasiumd","WGD"));
		team.put(9, new Team("Geschwister-Scholl-Schuhee","GSSE"));
		team.put(10, new Team("Wagenburg-Gymnasiume","WGE"));
		team.put(11, new Team("Geschwister-Scholl-Schuhef","GSSF"));
		team.put(12, new Team("Wagenburg-Gymnasiumf","WGF"));
		team.put(13, new Team("Geschwister-Scholl-Schuheg","GSSG"));
		team.put(14, new Team("Wagenburg-Gymnasiumg","WGG"));
		team.put(15, new Team("Geschwister-Scholl-Schuheh","GSSH"));
		team.put(16, new Team("Wagenburg-Gymnasiumh","WGH"));
		teamTestData.addAll(team.values());
	}
	
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
	
	/**
	 * Initializes the scene; fills in the team-table with the schools and abbreviations of all teams.
	 */
	@FXML
	private void initialize() {
		readInTeams(teamMap);
		teamTable.setItems(teamTestData);
		
		showTeamSchool.setCellValueFactory(new PropertyValueFactory<>("school"));
		showTeamAbbreviation.setCellValueFactory(new PropertyValueFactory<>("abbreviation"));
		teamTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setPlayerData(newValue));
	}
	
	/**
	 * Sets the player data of a chosen team in a gridpane on the right; with clicking on a team in the team-table you get all needed 
	 * informations about the team's members such as names, numbers of the players, and school.
	 * @param selectedTeam The team which is selected in the team-table.
	 */
	private void setPlayerData(Team selectedTeam) {
		int countRow = 2;
		teamID = -1;
		grid.getChildren().clear();
		Label school = new Label("Schule:");
		Label schoolLabel = new Label(selectedTeam.getSchool());
		Label numberLabel = new Label("Rückennr.");
		
		grid.add(school, 0, 0);
		grid.add(schoolLabel, 2, 0);
		grid.add(numberLabel, 1, 1);
		
		Set<Integer> keySet = teamMap.keySet();
		for (int k : keySet) {
			if (teamMap.get(k).equals(selectedTeam)) {
				teamID = k;
			} 
		}
	
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
