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
	
	private Button ready = new Button("Ergebnisse Runde 1 bestätigen");
	
	Map<Integer,Group> groups;
	Map<Integer,Team> teams;
	
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
		}
	}
	
	private void generateStatsR1() {
		for (int i : groups.keySet()) {
			Team t1 = teams.get(groups.get(i).getTeamID(1));
			Team t2 = teams.get(groups.get(i).getTeamID(2));
			Team t3 = teams.get(groups.get(i).getTeamID(3));
			Team t4 = teams.get(groups.get(i).getTeamID(4));
			
			round1.setSpacing(2);
		
			HBox internBox = new HBox(10);
			Label g1 = new Label(t1.getAbbreviation() + " : " + t2.getAbbreviation());
			TextField scoreg1 = new TextField();
			scoreg1.setPromptText("-:-");
			scoreg1.setPrefWidth(30);
			TextField scoreg2 = new TextField();
			scoreg2.setPromptText("-:-");
			scoreg2.setPrefWidth(30);
			Label g2 = new Label(t3.getAbbreviation() + " : " + t4.getAbbreviation());
			
			Label g3 = new Label(t1.getAbbreviation() + " : " + t3.getAbbreviation());
			TextField scoreg3 = new TextField();
			scoreg3.setPromptText("-:-");
			scoreg3.setPrefWidth(30);
			TextField scoreg4 = new TextField();
			scoreg4.setPromptText("-:-");
			scoreg4.setPrefWidth(30);
			Label g4 = new Label(t2.getAbbreviation() + " : " + t4.getAbbreviation());
			
			Label g5 = new Label(t1.getAbbreviation() + " : " + t4.getAbbreviation());
			TextField scoreg5 = new TextField();
			scoreg5.setPromptText("-:-");
			scoreg5.setPrefWidth(30);
			TextField scoreg6 = new TextField();
			scoreg6.setPromptText("-:-");
			scoreg6.setPrefWidth(30);
			Label g6 = new Label(t3.getAbbreviation() + " : " + t2.getAbbreviation());
			
			internBox.getChildren().addAll(g1,scoreg1,scoreg2,g2);
				
			round1.getChildren().add(internBox);
			
			}
		HBox lastBox = new HBox(10);
		lastBox.setPadding(new Insets(5,5,5,5));
		
		lastBox.getChildren().add(ready);
		round1.getChildren().add(ready);
		
		ready.setOnAction(e -> {save();});
	}
	
	private void save() {
		TextField score;
		
		boolean breakSaving = false;
		
		for (int i = 0; i < round1.getChildren().size(); i++) {
				HBox current = (HBox) round1.getChildren().get(i);
		}
	}
	

}
