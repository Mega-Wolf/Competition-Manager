package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

import match.Match;
import other.Player;
import other.Team;
import test.Manager;

public class CompetitionServer {

	public static final int PORT_NUMBER = 44532;
	
	private Manager<Player> playerManager = new Manager<Player>();
	private Manager<Team> teamManager = new Manager<Team>();
	private Manager<Match> matchManager = new Manager<Match>();
	
	public CompetitionServer() {
		try (ServerSocket server = new ServerSocket(PORT_NUMBER)) {

			while (true) {
				new Connection(server.accept()).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private class Connection extends Thread {
		/* Variables */
		private Socket socket;

		/* Constructor */
		public Connection(Socket socket) {
			this.socket = socket;
		}

		/* Overrides */
		@Override
		public void run() {
			try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {

				while (true) {

					Operation operation = (Operation) in.readObject();

					switch (operation) {
					case ADD_PLAYER:
						Player newPlayer = (Player) in.readObject();
						playerManager.add(newPlayer, (p) -> {
							List<Player> teamList = playerManager.getMatching(new Player(-1, p.getTeam(), null, null)).values().stream()
									.collect(Collectors.toList());
							return teamList.size() < Team.TEAM_SIZE_MAX
									&& teamList.stream().noneMatch(p2 -> p2.getNumber() == newPlayer.getNumber());
						});
						break;
					case CREATE_GROUPS:
						break;
					case GET_GROUP:
						break;
					case GET_MATCH:
						break;
					case GET_PLAYER:
						break;
					case GET_TEAM:
						break;
					case MATCHING_GROUPS:
						break;
					case MATCHING_MATCHES:
						break;
					case MATCHING_PLAYERS:
						Player matchingPlayer = (Player) in.readObject();
						out.writeObject(playerManager.getMatching(matchingPlayer));
						break;
					case MATCHING_TEAMS:
						break;
					case REMOVE_PLAYER:
						break;
					case UPDATE_MATCH:
						break;
					case UPDATE_PLAYER:
						break;
					case UPDATE_TEAM:
						break;
					default:
						break;
					}

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			super.run();
		}
	}

}