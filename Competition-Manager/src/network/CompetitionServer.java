package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import elements.Group;
import elements.GroupStat;
import elements.Manager;
import elements.Player;
import elements.Round;
import elements.Team;
import match.GroupExtension;
import match.RoundExtension;

public class CompetitionServer {
	
	private static Logger log = LogManager.getLogger(CompetitionServer.class);

	public static void main(String[] args) {	
		CompetitionServer cs = new CompetitionServer();
	}

	public static final int PORT_NUMBER = 44532;

	Manager<Player> playerManager = new Manager<Player>();
	Manager<Team> teamManager = new Manager<Team>();
	Manager<Group> groupManager = new Manager<Group>();
	Manager<GroupStat> groupStatManager = new Manager<GroupStat>();
	Manager<Round> roundManager = new Manager<Round>();
	Manager<GroupExtension> groupExtensionManager = new Manager<GroupExtension>();
	Manager<RoundExtension> roundExtensionManager = new Manager<RoundExtension>();

	public CompetitionServer() {
		Map<Operand, Manager<?>> managerMap = new HashMap<>();

		managerMap.put(Operand.PLAYER, playerManager);
		managerMap.put(Operand.TEAM, teamManager);
		managerMap.put(Operand.GROUP, groupManager);
		managerMap.put(Operand.GROUP_STAT,  groupStatManager);
		managerMap.put(Operand.GROUP_EXTENSION, groupExtensionManager);
		managerMap.put(Operand.ROUND_EXTENSION, roundExtensionManager);
		managerMap.put(Operand.ROUND, roundManager);

		managerMap = Collections.unmodifiableMap(managerMap);

		try (ServerSocket server = new ServerSocket(PORT_NUMBER)) {
			log.info("Started Server");
			while (true) {
				new Connection(server.accept(), managerMap).start();
			}

		} catch (IOException e) {
			log.error(e);
		}

	}

}