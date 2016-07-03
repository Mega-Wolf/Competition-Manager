package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import other.Manager;

public class Connection extends Thread {
	/* Variables */
	protected Map<Operand, Manager<?>> managerMap;
	protected ObjectOutputStream out;
	protected ObjectInputStream in;

	/* Constructor */
	public Connection(Socket socket, Map<Operand, Manager<?>> managerMap) throws IOException {
		this.managerMap = managerMap;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}

	/* Methods */
	protected void reactOnInput(Operation operation, Operand operand) throws ClassNotFoundException, IOException {
		
		Manager<?> manager = managerMap.get(operand);
		switch(operation) {
		case GET:
			out.writeObject(manager.get((int) in.readObject()));
			break;
		case GET_MATCHING:
			out.writeObject(manager.getMatching(in.readObject()));
			break;
		}
		
		/*
		
		switch (operation) {
		case ADD_PLAYER:
			Player newPlayer = (Player) in.readObject();
			playerManager.add(newPlayer, (p) -> {
				List<Player> teamList = playerManager.getMatching(new Player(-1, p.getTeam(), null, null)).values()
						.stream().collect(Collectors.toList());
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
		
		*/
	}

	/* Overrides */
	@Override
	public final void run() {
		while (true) {
			try {
				Operation operation = (Operation) in.readObject();
				Operand operand = (Operand) in.readObject();
				
				reactOnInput(operation, operand);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}