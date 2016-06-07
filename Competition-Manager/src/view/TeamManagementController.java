package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import network.Operation;
import other.Team;

public class TeamManagementController {
	
	Main main = new Main();
	
	// Switching Scenes
	@FXML
	public void editTeam() throws IOException {
		main.showNewScene("EditTeam.fxml", "Team bearbeiten");
	}
	
	@FXML
	public void newTeam() throws IOException {
		main.showNewScene("EditTeam.fxml", "Neues Team eintragen");
	}
	
	public Team team;
	int teamID;
	
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
							out.writeObject(Operation.GET_TEAM);
							out.writeObject(id);
							
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
	
	private SimpleStringProperty teamSchoolProp;
	private SimpleIntegerProperty teamIDProp;
	
		
	//FXML Stuff
	@FXML
	private TableView<Team> teamTable;
	
	@FXML
	private TableColumn<TeamManagementController, Integer> showTeamID;
	
	@FXML
	private TableColumn<TeamManagementController, String> showTeamSchool;
	
	@FXML
	private Label school;
	
	@FXML
	private Label player1;
	
	@FXML
	private Label player2;
	
	public TeamManagementController() {
	}
	
	private SimpleStringProperty teamSchoolProp() {
		teamSchoolProp.set(team.getSchool());
		return teamSchoolProp;
	}
	
	private SimpleIntegerProperty teamIDProp() {
		teamIDProp.set(teamID);
		return teamIDProp;
	}
	@FXML
	private void setTableContent() {
		
		showTeamSchool.setCellValueFactory(cellData -> cellData.getValue().teamSchoolProp());
	}
	
	
	
	
	

}
