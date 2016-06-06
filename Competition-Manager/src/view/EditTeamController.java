package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import other.Team;

public class EditTeamController extends Thread {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private GridPane grid;
		
	
	@FXML
	private Label nope;
	
	@FXML
	private Button cancel;
	
	private int rows = 11;
	
	@FXML
	private void addPlayerView(ActionEvent e) {
		if (rows < 23) {
			rows++;
			Label player = new Label("Spieler " + rows);
			TextField newPlayerForename = new TextField();
			newPlayerForename.setPromptText("Vorname");
			TextField newPlayerSurname = new TextField();
			newPlayerSurname.setPromptText("Nachname");
			TextField number = new TextField();
			
			//pane.setPrefHeight(pane.getHeight() + grid.getPrefHeight());
			pane.setMinHeight(pane.getHeight() + grid.getPrefHeight());
			grid.add(player, 0, (rows + 2));
			grid.add(newPlayerForename, 1, rows + 2);
			grid.add(newPlayerSurname, 2, rows + 2);
			grid.add(number, 4, rows + 2);
		} else {
			nope.setText("Sie haben bereits die maximal mögliche Anzahl an Spielern erreicht.");
		}
	}
	
	private Stage editDialog;
	private Team team;
	private boolean saved = false;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setEditDialog(Stage editDialog) {
		this.editDialog = editDialog;
	}
	
	public void saving() {
		
	}
	
	@FXML
	private void cancel(ActionEvent e) {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
}
