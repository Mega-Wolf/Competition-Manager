package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowTeamController {
	
	@FXML
	private Label school;
	
	@FXML
	private GridPane pane;
	
	@FXML
	private Button cancel;
	
	@FXML
	private void cancel(ActionEvent e) {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}

}
