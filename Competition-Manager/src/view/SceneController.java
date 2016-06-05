package view;

import java.io.IOException;

import javafx.fxml.FXML;

public class SceneController {

	private Main main = new Main();
	
	@FXML
	public void setTeamManagementView() throws IOException {
		main.setCenterContent("TeamManagement.fxml");
	}
	
	@FXML
	public void setHelpView() throws IOException {
		main.setCenterContent("Help.fxml");
	}
	
	@FXML
	public void editTeam() throws IOException {
		main.showNewScene("EditTeam.fxml", "Team bearbeiten");
	}
	
	@FXML
	public void setGame() throws IOException {
		main.setCenterContent("Game.fxml");
	}

}
