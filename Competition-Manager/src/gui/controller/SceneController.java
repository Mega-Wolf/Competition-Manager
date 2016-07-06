package gui.controller;

import java.io.IOException;

import gui.Main;
import javafx.fxml.FXML;

public class SceneController {

	private Main main = new Main();
	
	@FXML
	public void setTeamManagementView() throws IOException {
		main.setCenterContent("view/TeamManagement.fxml");
	}
	
	@FXML
	public void setGame() throws IOException {
		main.setCenterContent("view/Game.fxml");
	}
	
	@FXML
	public void setGameManagement() throws IOException {
		main.setCenterContent("view/GameManagement.fxml");
	}
	
	@FXML
	public void setContactView() throws IOException {
		main.setCenterContent("view/Contact.fxml");
	}

	@FXML
	public void setHelpView() throws IOException {
		main.setCenterContent("view/Help.fxml");
	}

}
