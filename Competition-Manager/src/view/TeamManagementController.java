package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import other.Team;

public class TeamManagementController {

	

	
	
	
	
	
	Main main = new Main();
	
	
	@FXML
	public void editTeam() throws IOException {
		main.showNewScene("EditTeam.fxml", "Team bearbeiten");
	}
	
	@FXML
	public void newTeam() throws IOException {
		main.showNewScene("EditTeam.fxml", "Neues Team eintragen");
	}
	
	@FXML
	private TableView<Team> teamTable;
	
	@FXML
	private TableColumn<Team, Integer> showTeamID;
	
	@FXML
	private TableColumn<Team, String> showTeamSchool;
	
	@FXML
	private Label school;
	
	@FXML
	private Label player1;
	
	@FXML
	private Label player2;
	
	public TeamManagementController() {
	}
	
	@FXML
	private void setTableContent() {
		
	}
	
	
	
	
	

}
