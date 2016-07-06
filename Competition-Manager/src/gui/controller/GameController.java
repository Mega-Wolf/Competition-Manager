package gui.controller;

import java.io.IOException;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class GameController {
	
	Main main = new Main();
	
	@FXML
	private Button team1;
	
	@FXML
	private HBox hbox1;
	

		
	@FXML
	private void showTeam() throws IOException {
		main.showNewScene("ShowTeam.fxml", "Teamanzeige");
	}

}
