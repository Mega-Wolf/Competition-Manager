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
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import network.Operand;
import network.Operation;
import other.Group;
import other.Team;

public class GameController {
	
	Main main = new Main();
	TeamManagementController tmc = new TeamManagementController();
	
	private Map<Integer, Team> teamMap;
	private Map<Integer, Group> groupMap;
	
	@FXML
	public void initialize() throws InterruptedException {
		List<SendData> sendDataList = new ArrayList<SendData>();
		sendDataList.add(new SendData(Operation.GET_MATCHING, Operand.GROUP, new Group(null,null)));
		sendDataList.add(new SendData(Operation.GET_MATCHING, Operand.TEAM, new Team(null,null)));
		ClientConnection cc2 = new ClientConnection();
		cc2.sendToServer(sendDataList);
		groupMap = (Map<Integer,Group>) sendDataList.get(0).getReturnValue();
		teamMap = (Map<Integer,Team>) sendDataList.get(1).getReturnValue();
		
		if(tmc.isFinished()) {
			
		}
	}

	
	public void setGroupPlays(Group group) {
		// take teams out of group
		
		
	}

}
