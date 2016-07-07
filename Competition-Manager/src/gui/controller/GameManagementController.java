package gui.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import gui.Main;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import network.Operand;
import network.Operation;
import other.Group;
import other.Team;

public class GameManagementController {
	
	Main main = new Main();
	
	TeamManagementController tmc = new TeamManagementController();
	
	@FXML
	VBox round1 = new VBox();
	
	private Button ready = new Button("Ergebnisse Runde 1 bestätigen");
	
	Map<Integer,Group> groups = new ConcurrentHashMap<>();
	Map<Integer,Team> teams = new ConcurrentHashMap<>();
	
	@FXML
	public void initialize() {
		if(tmc.isFinished()) {
			generateStatsR1(groups);
		}
	}
	
	//TODO @Tobi: Hier muss ich alle meine Gruppen als Map laden können :-)
	public void loadingGroup() throws UnknownHostException, IOException {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int PORT_NUMBER = 44532;
				
				Socket server;
				try {
					server = new Socket("127.0.0.1",PORT_NUMBER);
					
					try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream()); ObjectInputStream in = new ObjectInputStream(server.getInputStream());) {
							out.writeObject(Operation.GET_MATCHING);
							out.writeObject(Operand.GROUP);
							
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
	
	//TODO @Tobi: Und hiiiier will ich die zugehörigen Teams einer Gruppe anhand kp was
	public void loadingTeam(int id) throws UnknownHostException, IOException {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int PORT_NUMBER = 44532;
				
				Socket server;
				try {
					server = new Socket("127.0.0.1",PORT_NUMBER);
					
					try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream()); ObjectInputStream in = new ObjectInputStream(server.getInputStream());) {
							out.writeObject(Operation.GET_MATCHING);
							out.writeObject(Operand.TEAM);
							
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
	

	private void generateStatsR1(Map<Integer,Group> group) {
		round1.setSpacing(2);
		for (int i = 0; i < 12; i++) {
			
			HBox internBox = new HBox(10);
			Label g1 = new Label("hi");
			TextField scoreg1 = new TextField();
			scoreg1.setPromptText("-:-");
			scoreg1.setPrefWidth(30);
			TextField scoreg2 = new TextField();
			scoreg2.setPromptText("-:-");
			scoreg2.setPrefWidth(30);
			Label g2 = new Label("hi");
			internBox.getChildren().addAll(g1,scoreg1,scoreg2,g2);
			
			round1.getChildren().add(internBox);
		}
		HBox lastBox = new HBox(10);
		lastBox.setPadding(new Insets(5,5,5,5));
		
		lastBox.getChildren().add(ready);
		round1.getChildren().add(ready);
		
	}
	
	

}
