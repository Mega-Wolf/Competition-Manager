package view;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import other.Player;
import view.TeamManagementController.TeamProp;

public class EditTeamController {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private GridPane grid;
		
	@FXML
	private TextField school;
	
	@FXML
	private TextField shortSchool;
	
	@FXML
	private Label nope;
	
	@FXML
	private Button cancel;
	
	private boolean okClicked = false;
	
	private int rows = 11;
	
	/*TO BE IGNORED
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
	}*/
	
	private Stage editDialog;
	private TeamProp team;
	private List<Player> players;
	private boolean saved = false;
	
	
	@FXML
	private void initialize() {
	}
	
	public void setEditDialog(Stage editDialog) {
		this.editDialog = editDialog;
	}
	
	public void setTeam(TeamProp team, List<Player> players) {
		this.team = team;
		this.players = players;
		int countRow = 2;
		
		school.setText(team.getSchool());
		shortSchool.setText(team.getShortSchool());
		
		// Fill Grid with enough fields
		for (Player p : players) {
			if (p.getTeam() == team.getId()) {
				Label currentPlayer = new Label("Spieler " + (countRow-1));
				
				TextField foreNameField = new TextField(p.getForename());
				TextField surNameField = new TextField(p.getSurname());
				
				//TODO: Playernumber
				
				grid.add(currentPlayer, 0, countRow);
				grid.add(foreNameField, 1, countRow);
				grid.add(surNameField, 2, countRow);
				
				countRow++;
			}
		}
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	/* SERVER STUFF
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
	}*/
	
	/*
	@FXML
	private void handleOk() {
		int startingWith = 2;
		int countRow = 2;
		for (Player p : players) {
			if (p.getTeam() == team.getId()) {
				p.setForename(((TextField) getNodeAtPosition(countRow,1,grid)).getText()); 
				p.setSurname(((TextField) getNodeAtPosition(countRow,2,grid)).getText()); 
				countRow++;
			}
		}

		okClicked = true;
		
	}
	
	//Method to get node of gridpane at given position (row and column)
	private Node getNodeAtPosition(int row, int column, GridPane grid) {
		Node result = null;
		ObservableList<Node> children = grid.getChildren();
		
		for (Node node : children) {
			if (grid.getRowIndex(node) == row && grid.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}
		
		return result;
	}*/
	
	@FXML
	private void cancel(ActionEvent e) {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
}
