package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import other.Group;
import other.Manager;
import other.Player;
import other.Team;

public class CompetitionServer {

	public static void main(String[] args) {

		/*
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				int PORT_NUMBER = 44532;
				System.out.println("Vorm Try");
				Socket server;
				try {
					server = new Socket("127.0.0.1", PORT_NUMBER);
					try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
							ObjectInputStream in = new ObjectInputStream(server.getInputStream());) {
						out.writeObject(Operation.ADD);
						out.writeObject(Operand.PLAYER);
						Player p = new Player(1, 1, "f", "sfs");
						out.writeObject(p);
						
						out.writeObject(Operation.ADD);
						out.writeObject(Operand.PLAYER);
						out.writeObject(p);
						
						//out.flush();
						System.out.println(in.readObject());
						// team = (Team) in.readObject();
						server.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		t.start();
		*/
		
		CompetitionServer cs = new CompetitionServer();
	}

	public static final int PORT_NUMBER = 44532;

	// state variable needed

	Manager<Player> playerManager = new Manager<Player>();
	Manager<Team> teamManager = new Manager<Team>();
	// Manager<Group> groupManager = new Manager<Group>();
	// Manager<GroupMatch> groupMatchManager = new Manager<GroupMatch>();
	// Manager<RoundMatch> roundMatchManager = new Manager<RoundMatch>();

	public CompetitionServer() {
		Map<Operand, Manager<?>> managerMap = new HashMap<>();

		managerMap.put(Operand.PLAYER, playerManager);
		managerMap.put(Operand.TEAM, teamManager);
		// managerMap.put(Operand.GROUP, groupManager);
		// managerMap.put(Operand.GROUP_MATCH, groupMatchManager);
		// managerMap.put(Operand.ROUND_MATCH, roundMatchManager);

		managerMap = Collections.unmodifiableMap(managerMap);

		try (ServerSocket server = new ServerSocket(PORT_NUMBER)) {
			System.out.println("Started server");
			while (true) {
				new Connection(server.accept(), managerMap).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}