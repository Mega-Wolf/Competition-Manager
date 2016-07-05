package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
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
	private Label informationLabel;
	
	private int countRow = 2;
	private int currentPlayerNumber;
	private int teamID;
	
	private List<HBox> createdTextFields;
	
	
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
		System.out.println("hi :-)");
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
			
			mainBox.getChildren().add(internBox);
		}
	}
	
	
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
		//savedTeam = new Team(teamSchool,teamSchoolAbbr);
		//serverSaveTeam(savedTeam);
		
		ObservableList<Node> childrens = grid.getChildren();
		for (Node n : childrens) {
			if (grid.getColumnIndex(n) == 0 && grid.getRowIndex(n) == 0) {
				System.out.println("hier müsste das erste Feld sein");
			}
		}
		
		for (int i = 2; i < (countRow + currentPlayerNumber); i++) {
			for (Node node : childrens) {
				if (GridPane.getRowIndex(node) == i) {
					playerStuff = (TextField) node;
					if (GridPane.getColumnIndex(node) == 0) {
						forename = playerStuff.getText();
						System.out.println("that worked :-)" + forename);
					}
					if (GridPane.getColumnIndex(node) == 1) {
						surname = playerStuff.getText();
						System.out.println("that worked :-)" + surname);
					}
					if (GridPane.getColumnIndex(node) == 2) {
						number = toInteger(playerStuff.getText());
						System.out.println("that worked :-)" + number);
					}
				}
				//savedPlayer = new Player(number, teamID, forename, surname);
				//serverSavePlayer(savedPlayer);
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
