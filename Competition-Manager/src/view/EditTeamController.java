package view;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import network.Operation;
import other.Player;
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
	
	@FXML
	private TextField player1Forename;
	
	@FXML
	private TextField player1Surname;
	
	@FXML
	private TextField player1Number;
	
	@FXML
	private TextField school; 
	
	@FXML
	private TextField player2;
	
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
			
			pane.setPrefHeight(pane.getPrefHeight()+grid.getVgap()+30);
			//grid.setVgap(grid.getVgap());
			grid.add(player, 1, (rows + 2));
			grid.add(newPlayerForename, 2, rows + 2);
			grid.add(newPlayerSurname, 3, rows + 2);
			grid.add(number, 5, rows + 2);
			grid.setVgap(grid.getVgap());
		} else {
			nope.setText("Sie haben bereits die maximal m�gliche Anzahl an Spielern erreicht.");
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
	
	public void saving() throws UnknownHostException, IOException {
		
		Thread fu = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int id = 0;
				int PORT_NUMBER = 44532;
				
				Socket server;
				try {
					server = new Socket("127.0.0.1", PORT_NUMBER);
					
					try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());)  {
						out.writeObject(Operation.ADD_PLAYER);
						out.writeObject(new Player(Integer.valueOf(player1Number.getText()), id, player1Forename.getText(), player1Surname.getText()));
						out.writeObject(new Team(id, school.getText()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					server.close();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
		
		
	}
	
	@FXML
	private void cancel(ActionEvent e) {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
}
