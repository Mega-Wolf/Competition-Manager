package gui.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import gui.Main;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import network.Operand;
import network.Operation;
import other.Group;
import other.Player;
import other.Team;

public class GameManagementController {
	
	Main main = new Main();
	
	TeamManagementController tmc = new TeamManagementController();
	
	@FXML
	VBox round1 = new VBox();
	
	private Button ready = new Button("Ergebnisse Runde 1 best�tigen");
	
	public static boolean goToSecondRound;
	
	Map<Integer,Group> groups;
	Map<Integer,Team> teams;
	
	public static void setSecondRound(boolean finished) {
		goToSecondRound = finished;
	}
	
	
	public static boolean isFinished() {
		return goToSecondRound;
	}
	/**
	 * Initializes the scene; first it loads all data from the server, then it checks
	 * if there already are enough Teams added in the Team-Management. If not, the pages
	 * stay empty, else the first matches are shown. 
	 * @throws InterruptedException
	 */
	@FXML
	public void initialize() throws InterruptedException {
		List<SendData> sendDataList = new ArrayList<SendData>();
		sendDataList.add(new SendData(Operation.GET_MATCHING, Operand.GROUP, new Group(null,null)));
		sendDataList.add(new SendData(Operation.GET_MATCHING, Operand.TEAM, new Team(null,null)));
		ClientConnection cc2 = new ClientConnection();
		cc2.sendToServer(sendDataList);
		groups = (Map<Integer,Group>) sendDataList.get(0).getReturnValue();
		teams = (Map<Integer,Team>) sendDataList.get(1).getReturnValue();
		
		if(tmc.isFinished()) {
			generateStatsR1();
			if(isFinished()) {
				//load next Round
			}
		}
	}
	
	/**
	 * Method to generate the fields for the group matches.
	 */
	private void generateStatsR1() {
		for (int i : groups.keySet()) {
			Team t1 = teams.get(groups.get(i).getTeamID(1));
			Team t2 = teams.get(groups.get(i).getTeamID(2));
			Team t3 = teams.get(groups.get(i).getTeamID(3));
			Team t4 = teams.get(groups.get(i).getTeamID(4));
			
			round1.setSpacing(2);
		
			HBox firstBox = new HBox(10);
			Label g1 = new Label(t1.getAbbreviation() + " : " + t2.getAbbreviation());
			TextField scoreg1 = new TextField();
			scoreg1.setPromptText("-:-");
			scoreg1.setPrefWidth(30);
			TextField scoreg2 = new TextField();
			scoreg2.setPromptText("-:-");
			scoreg2.setPrefWidth(30);
			Label g2 = new Label(t3.getAbbreviation() + " : " + t4.getAbbreviation());
			
			HBox secondBox = new HBox(10);
			Label g3 = new Label(t1.getAbbreviation() + " : " + t3.getAbbreviation());
			TextField scoreg3 = new TextField();
			scoreg3.setPromptText("-:-");
			scoreg3.setPrefWidth(30);
			TextField scoreg4 = new TextField();
			scoreg4.setPromptText("-:-");
			scoreg4.setPrefWidth(30);
			Label g4 = new Label(t2.getAbbreviation() + " : " + t4.getAbbreviation());
			
			HBox thirdBox = new HBox(10);
			Label g5 = new Label(t1.getAbbreviation() + " : " + t4.getAbbreviation());
			TextField scoreg5 = new TextField();
			scoreg5.setPromptText("-:-");
			scoreg5.setPrefWidth(30);
			TextField scoreg6 = new TextField();
			scoreg6.setPromptText("-:-");
			scoreg6.setPrefWidth(30);
			Label g6 = new Label(t3.getAbbreviation() + " : " + t2.getAbbreviation());
			
			firstBox.getChildren().addAll(g1,scoreg1,scoreg2,g2);
			secondBox.getChildren().addAll(g3,scoreg3,scoreg4,g4);
			thirdBox.getChildren().addAll(g5,scoreg5,scoreg6,g6);
				
			round1.getChildren().addAll(firstBox,secondBox,thirdBox);	
		}
		
		HBox lastBox = new HBox(10);
		lastBox.setPadding(new Insets(5,5,5,5));
		
		lastBox.getChildren().add(ready);
		round1.getChildren().add(ready);
		
		ready.setOnAction(e -> {save();});
	}
	
	/**
	 * Action that's performed after klicking the saving button.
	 * Checks if all fields are filled out. If not, the user gets an error.
	 */
	private void save() {
		TextField score;
		TextField secScore;
		boolean breakSaving = false;
		
		for (int i = 0; i < round1.getChildren().size(); i++) {
				HBox current = (HBox) round1.getChildren().get(i);
				score = (TextField) current.getChildren().get(1);
				secScore = (TextField) current.getChildren().get(2);
				if (!validScore(score.getText())||!validScore(secScore.getText())) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Fehler");
					alert.setHeaderText("Bitte �berpr�fen Sie Ihre Eingaben.");
					alert.setContentText("Hinweis: F�llen Sie bitte jedes Feld aus. Beachten Sie, dass Sie die Tore mit einem"
							+ " Doppelpunkt und ohne Leerzeichen trennen.");
					alert.showAndWait();
					breakSaving = true;
				}
		}
		
		if (!breakSaving) {
			//save
			for (int i = 0; i < round1.getChildren().size(); i++) {
				
			}
			setSecondRound(true);
		}
	}
	
	/**
	 * Checks if given String which should represent the score of a match is valid.
	 * @param string given String
	 * @return true if String matches the scheme, false otherwise
	 */
	public static boolean validScore(String string) {
		return string.matches("[0-9]+[:]+[0-9]");
	}
	
	/**
	 * Transforms a given String to the goals of a match
	 * @param string given String in the format GOALS:GOALS
	 * @return an integer array of the length 2 with the goals
	 */
	private int[] toGoals(String string) {
		int[] goals = new int[2];
		int count = 0;
		String[] splitted = string.split(":");
		for (String s : splitted) {
			goals[count] = Integer.parseInt(s);
			count++;
		}
		return goals;
	}
	

}
