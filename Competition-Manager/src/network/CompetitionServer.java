package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import match.GroupMatch;
import match.RoundMatch;
import other.Group;
import other.Manager;
import other.Player;
import other.Team;

public class CompetitionServer {

	public static final int PORT_NUMBER = 44532;
	
	//state variable needed
	
	Manager<Player> playerManager = new Manager<Player>();
	Manager<Team> teamManager = new Manager<Team>();
	Manager<Group> groupManager = new Manager<Group>();
	Manager<GroupMatch> groupMatchManager = new Manager<GroupMatch>();
	Manager<RoundMatch> roundMatchManager = new Manager<RoundMatch>();
	
	
	public CompetitionServer() {
		Map<Operand, Manager<?>> managerMap = new HashMap<>();
		
		managerMap.put(Operand.PLAYER, playerManager);
		managerMap.put(Operand.TEAM, teamManager);
		managerMap.put(Operand.GROUP, groupManager);
		managerMap.put(Operand.GROUP_MATCH, groupMatchManager);
		managerMap.put(Operand.ROUND_MATCH, roundMatchManager);
		
		managerMap = Collections.unmodifiableMap(managerMap);
		
		try (ServerSocket server = new ServerSocket(PORT_NUMBER)) {
			while (true) {
				new Connection(server.accept(), managerMap).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}