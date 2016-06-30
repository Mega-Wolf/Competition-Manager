package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class NewTeamController {
	
	//FXML Stuff
	@FXML
	private TextField school;
	
	@FXML
	private TextField abbreviation;
	
	@FXML
	private GridPane grid;
	
	@FXML
	private Button cancel;
	
	@FXML
	private Button addPlayer;
	
	@FXML
	private Label informationLabel;
	
	private int countRow = 2;
	private int currentPlayerNumber;
	
	@FXML
	private void initialize() {
		school.setPromptText("Schule eintragen");
		abbreviation.setPromptText("Schulabk�rzung");
		abbreviation.setTooltip(new Tooltip("Max. 4 Buchstaben"));
		
		initializeTextFields();
	}

	// Creates enough TextFields for 11 players (minimum size)
	private void initializeTextFields() {
		generateTextFields(countRow, 11);
		currentPlayerNumber = 11;
	}
	
	private void generateTextFields(int startWith, int howMany) {
		for (int i = startWith; i < startWith+howMany; i++) {
			TextField playerForename = new TextField();
			playerForename.setPromptText("Vorname Spieler " + (i-1));
			TextField playerSurname = new TextField();
			playerSurname.setPromptText("Nachname Spieler " + (i-1));
			TextField playerNumber = new TextField();
			playerNumber.setPromptText("R�ckennr.");
			playerNumber.setTooltip(new Tooltip("2 stellige Zahl eingeben"));
			RowConstraints row = new RowConstraints(25);
			grid.getRowConstraints().add(row);
			grid.add(playerForename, 0, i);
			grid.add(playerSurname, 1, i);
			grid.add(playerNumber, 2, i);
		}
	}
	
	@FXML
	private void addMorePlayers() {
		int startWith = countRow + currentPlayerNumber;
		
		try {
			if(currentPlayerNumber==23) {
				throw new TooManyPlayersException();
			}
			generateTextFields(startWith, 1);
			currentPlayerNumber++;
		} catch(TooManyPlayersException e) {
			System.err.println("You already added 23 players in your Team.");
			informationLabel.setText("Sie k�nnen nicht mehr als 23 Spieler hinzuf�gen.");
			
		}
		
	}
	

	// Exception: if too many players are added
	private class TooManyPlayersException extends Exception {
		public TooManyPlayersException() {
		}
		public TooManyPlayersException(String message) {
			super(message);
		}
	}

	@FXML
	private void cancel(ActionEvent e) {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
}
