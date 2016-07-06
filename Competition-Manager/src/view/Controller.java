package view;

import java.io.IOException;

import javafx.fxml.FXML;

public class Controller {

	private Main main = new Main();
	
	@FXML
	public void setTeamManagementView() throws IOException {
		main.setCenterContent("view/TeamManagement.fxml");
	}
	
	@FXML
	public void setHelpView() throws IOException {
		main.setCenterContent("view/Help.fxml");
	}
	
	@FXML
	public void editTeam() throws IOException {
		main.showNewScene("view/EditTeam.fxml", "Team bearbeiten");
	}
	
	@FXML
	public void setGame() throws IOException {
		main.setCenterContent("view/Game.fxml");
	}

}
