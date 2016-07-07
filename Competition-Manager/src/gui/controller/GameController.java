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
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import network.Operand;
import network.Operation;
import other.Group;
import other.Team;

public class GameController {
	
	Main main = new Main();
	TeamManagementController tmc = new TeamManagementController();
	
	private Map<Integer, Team> teamMap = new ConcurrentHashMap<Integer,Team>();
	
	//TODO: Wildcard?
	private Team team = new Team("","");
	
	public void initialize() {
		if(tmc.isFinished()) {
			
		}
	}

	
	public void setGroupPlays(Group group) {
		// take teams out of group
		
		
	}
	
	//TODO: read in team
		public void loadingTeam() throws UnknownHostException, IOException {
			
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
								team = (Team) in.readObject();
								out.writeObject(teamMap);
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

}
