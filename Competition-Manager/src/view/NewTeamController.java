package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import other.Player;

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
		abbreviation.setPromptText("Schulabkürzung");
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
			playerNumber.setPromptText("Rückennr.");
			playerNumber.setTooltip(new Tooltip("2 stellige Zahl eingeben"));
			RowConstraints row = new RowConstraints(25);
			grid.getRowConstraints().add(row);
			grid.add(playerForename, 0, i);
			grid.add(playerSurname, 1, i);
			grid.add(playerNumber, 2, i);
		}
	}
	
	@FXML
	private void save() {
		TextField playerStuff;
		Player savedPlayer;
		String forename;
		String surname;
		int number;
		int id = 1;
		Node result = null;
		ObservableList<Node> childrens = grid.getChildren();
		for (int i = 2; i < (countRow + currentPlayerNumber); i++) {
			for (Node node : childrens) {
				if (grid.getRowIndex(node) == i) {
					playerStuff = (TextField) node;
					if (grid.getColumnIndex(node) == 0) {
						forename = playerStuff.getText();
					}
					if (grid.getColumnIndex(node) == 1) {
						surname = playerStuff.getText();
					}
					if (grid.getColumnIndex(node) == 2) {
						number = toInteger(playerStuff.getText());
					}
				}
			}
		}
	}
	
	private int toInteger(String string) {
		int result = 0;
		char[] stringArray = string.toCharArray();
		int[] resultArray = {stringArray.length};
		for (int count = 0; count < stringArray.length; count++) {
			for (char c : stringArray) {
				switch(c) {
				case '0': resultArray[count] = 0; break;
				case '1': resultArray[count] = 1; break;
				case '2': resultArray[count] = 2; break;
				case '3': resultArray[count] = 3; break;
				case '4': resultArray[count] = 4; break;
				case '5': resultArray[count] = 5; break;
				case '6': resultArray[count] = 6; break;
				case '7': resultArray[count] = 7; break;
				case '8': resultArray[count] = 8; break;
				case '9': resultArray[count] = 9; break;
				}
			}
		}
		if (resultArray.length == 1) {
			result = resultArray[0];
		} else {
			result = resultArray[0] * 10 + resultArray[1];
		}
		return result;
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
			informationLabel.setText("Sie können nicht mehr als 23 Spieler hinzufügen.");
			
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
