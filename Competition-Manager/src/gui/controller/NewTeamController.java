package gui.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import network.Operand;
import network.Operation;
import other.Player;
import other.Team;

public class NewTeamController {
	
	//FXML Stuff
	@FXML
	private TextField school;
	
	@FXML
	private TextField abbreviation;
	
	@FXML
	private GridPane grid;
	
	@FXML
	private VBox mainBox;
	
	@FXML
	private Button cancel;
	
	@FXML
	private Button addPlayer;
	
	@FXML
	private Button deletePlayer;
	
	@FXML
	private Label informationLabel;
	
	private int countRow = 2;
	private int currentPlayerNumber;
	private int teamID;
	
	// initializes scene, starts method to "print" textfields
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
	
	/**
	 * A method to generate the textfields in which the user can type the players of his team.
	 * It creates a defined number of HBoxes each with three textfields for the forename, surname and number of the player.
	 * @param startWith The index of the mainbox, where the first Hbox should be added
	 * @param howMany Defines how many HBoxes the user want to create
	 */
	private void generateTextFields(int startWith, int howMany) {
		for (int i = startWith; i < startWith+howMany; i++) {
			
			HBox internBox = new HBox(5);
			internBox.setPadding(new Insets(5,5,5,5));
			internBox.setPrefHeight(30);
			TextField forename = new TextField();
			TextField surname = new TextField();
			TextField number = new TextField();
			forename.setPromptText("Vorname Spieler " + (i-1));
			surname.setPromptText("Nachname Spieler " + (i-1));
			number.setPromptText("Rückennr.");
			number.setTooltip(new Tooltip("2 stellige Zahl eingeben"));
			internBox.getChildren().addAll(forename, surname, number);
			
			mainBox.getChildren().add((i-1),internBox);
		}
	}
	
	/**
	 * The method which is executed when pressing the "Save"-Button.
	 * First the method checks the validity of the given Strings and finally saves - if no errors occur - the players.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@FXML
	private void save() throws UnknownHostException, IOException {
		TextField playerStuff;
		Player savedPlayer;
		Team savedTeam;
		String forename = null;
		String surname = null;
		int number = 0;
		String teamSchool = school.getText();
		String teamSchoolAbbr = abbreviation.getText();
		savedTeam = new Team(teamSchool,teamSchoolAbbr);
		//serverSaveTeam(savedTeam);
		boolean breakSaving = false;
		
		for (int i = 2; i < (countRow + currentPlayerNumber); i++) {
				HBox current = (HBox) mainBox.getChildren().get(i-1);
				TextField fn = (TextField) current.getChildren().get(0);
				TextField sn = (TextField) current.getChildren().get(1);
				TextField nb = (TextField) current.getChildren().get(2);
				
				if(!isValidName(teamSchool) || !isValidName(teamSchoolAbbr) || !isValidName(fn.getText()) || !isValidName(sn.getText()) || !isValidNumber(nb.getText())) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Fehler");
					alert.setHeaderText("Bitte überprüfen Sie Ihre Eingaben.");
					alert.setContentText("Hinweis: Füllen Sie bitte jedes Feld aus. Beachten Sie, dass die Namen nur Buchstaben (und Minus) enthalten dürfen, "
							+ "und die Rückennummer zwischen 1 und 99 liegen muss.");
					alert.showAndWait();
					breakSaving = true;
					break;
				}
		}
		
		if(breakSaving == false) {
			for (int i = 2; i < (countRow + currentPlayerNumber); i++) {
				HBox current = (HBox) mainBox.getChildren().get(i-1);
				TextField fn = (TextField) current.getChildren().get(0);
				TextField sn = (TextField) current.getChildren().get(1);
				TextField nb = (TextField) current.getChildren().get(2);
				
				forename = fn.getText();
				surname = sn.getText();
				number = Integer.parseInt(nb.getText());
				savedPlayer = new Player(number,teamID,forename,surname);
				//serverSavePlayer(savedPlayer);
				Stage stage = (Stage) cancel.getScene().getWindow();
				stage.close();
			}
		}
	}
	
	/**
	 * Checks the validity of the given String (meant to check forename and surname of the player as well as the
	 * school and the school abbreviation). String should not contain any characters which aren't letters despite
	 * for space and minus(-), but has to contain at least one letter.
	 * @param string Given String to check if valid;
	 * @return returns false if the String contains non-letters (not including minus and space) or if String doesn't contain letters;
	 * else the method returns true;
	 */
	public static boolean isValidName(String string) {
		//return string.matches("[A-Z][a-z]+([- ][A-Z][a-z]+)*");

		if (string == null || string.length() == 0) {
			return false;
		}
		
		char[] charArray = string.toCharArray();
		int letterFound = 0;
		for (char c : charArray) {
			if (Character.isLetter(c)) {
				letterFound++;
				continue;
			} else if (c == ' ' || c == '-') {
				continue;
			} else {
				return false;
			}
		}
		if (letterFound == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks the validity of the given String which is representing an Integer (the number of the player) and which also shall be
	 * parsed into an Integer after this check. The wanted number (and therefore the given String) must be between 1 and 99, should
	 * have no leading zero and every char must be a digit to enable the parsing later on.
	 * @param string Given String to check if valid;
	 * @return false if the String extends other characters than digits, has the wrong length or a leading zero; else the method returns true; 
	 */
	public static boolean isValidNumber(String string) {
		//return string.matches("[1-9][0-9]?");

		if (string == null || string.length() == 0 || string.length() > 2) {
			return false;
		}
		char[] charArray = string.toCharArray();
		if (charArray[0] == '0') {
			return false;
		}
		for (char c : charArray) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method to add more players. Checks the team size with the variable "currentPlayerNumber" and throws an Exception if the team already has 23 slots.  
	 */
	@FXML
	private void addMorePlayers() {
		int startWith = countRow + currentPlayerNumber;
		
		try {
			if(currentPlayerNumber==23) {
				throw new WrongNumberOfPlayersException();
			}
			generateTextFields(startWith, 1);
			currentPlayerNumber++;
		} catch(WrongNumberOfPlayersException e) {
			System.err.println("You already added 23 players in your Team.");
			informationLabel.setText("Sie können nicht mehr als 23 Spieler hinzufügen.");
		}
	}
	
	/**
	 * Method to delete playerslots. Checks the team size with the variable "currentPlayerNumber" and throws an Exception if the team has only 11 slots.
	 */
	@FXML
	private void deletePlayers() {
		try {
			if(currentPlayerNumber==11) {
				throw new WrongNumberOfPlayersException();
			}
			mainBox.getChildren().remove(mainBox.getChildren().size()-1);
			currentPlayerNumber--;
		} catch(WrongNumberOfPlayersException e) {
			System.err.println("You need at least 11 players in your Team.");
			informationLabel.setText("Sie benötigen mindestens 11 Spieler in Ihrem Team.");
		}
	}
	//to 
	public void serverSavePlayer(Player player) throws UnknownHostException, IOException {
		Thread fu = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int id = 0;
				int PORT_NUMBER = 44532;
				
				Socket server;
				try {
					server = new Socket("127.0.0.1", PORT_NUMBER);
					
					try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());)  {
						out.writeObject(Operation.ADD);
						out.writeObject(Operand.PLAYER);
						out.writeObject(player);
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
	
	public void serverSaveTeam(Team team) throws UnknownHostException, IOException {
		Thread fu = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int id = 0;
				int PORT_NUMBER = 44532;
				
				Socket server;
				try {
					server = new Socket("127.0.0.1", PORT_NUMBER);
					
					try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream()); ObjectInputStream in = new ObjectInputStream(server.getInputStream());)  {
						out.writeObject(Operation.ADD);
						out.writeObject(Operand.TEAM);
						teamID = (int) in.readObject();
						out.writeObject(team);
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

	// Exception: if too many players are added or deleted
	private class WrongNumberOfPlayersException extends Exception {
		public WrongNumberOfPlayersException() {
		}
		public WrongNumberOfPlayersException(String message) {
			super(message);
		}
	}

	/** Cancel
	 * Method that is called when pressing the "Verwerfen" button. Shows a dialog to the user, informing him
	 * about the loss of data when pressing OK. If user confirms, the window closes.
	 * @param e Action event
	 */
	@FXML
	private void cancel(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Verwerfen");
		alert.setHeaderText("Sind Sie sich sicher, das aktuelle Team zu verwerfen?");
		alert.setContentText("Hinweis: Die Änderungen werden nicht gespeichert!");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) cancel.getScene().getWindow();
			stage.close();
		}
	}
}
