package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import other.Manager;
import other.Player;
import other.Team;

/**
 * This class handles a connection between the server and a client.
 */
//Should have been divided into state-pattern first, but this turned out to be even more complicated;
//therefore sticked to the nested switch stuff
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
		case ADD:
			handleAdd(operand);
			break;
		case INSTRUCTION:
			//handleInstruction(operand);
			break;
		case REMOVE:
			//handleRemove(operand);
			break;
		}
	}
	
	/**
	 * Handles the insertion of Players and Teams
	 * @param operand {@link Operand.PLAYER} or {@link Operand.TEAM}
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void handleAdd(Operand operand) throws ClassNotFoundException, IOException {
		
		Manager<Player> playerManager = (Manager<Player>) managerMap.get(Operand.PLAYER);
		Manager<Team> teamManager = (Manager<Team>) managerMap.get(Operand.TEAM);
		switch (operand) {
		case PLAYER:	
			Player addPlayer = (Player) in.readObject();
			// synchronized both, because the teamID must exist and the player number must be unique inside the team
			synchronized (teamManager) {
				if (teamManager.get(addPlayer.getTeam()) != null) {
					// if the teamID does not exist, the players do not have to be checked
					synchronized (playerManager) {
						boolean allowed = true;
						if (!playerManager.getMatching(new Player(addPlayer.getNumber(), addPlayer.getTeam(), null, null)).isEmpty()) {
							allowed = false;
						}
						if (allowed) {
							out.writeObject(playerManager.add(addPlayer));
						} else {
							//TODO; Exception
							out.writeObject(-1);
						}
					}
				} else {
					//TODO; Exception
					out.writeObject(-1);
				}
			}
			break;
		case TEAM:
			Team addTeam = (Team) in.readObject();
			// synchronization needed, because the write must be directly after the read
			synchronized (teamManager) {
				boolean allowed = true;
				if (!teamManager.getMatching(new Team(null, addTeam.getAbbreviation())).isEmpty() || !teamManager.getMatching(new Team(addTeam.getSchool(), null)).isEmpty()) {
					allowed = false;
				}
				if (allowed) {
					out.writeObject(teamManager.add(addTeam));
				} else {
					//TODO; Exception
					out.writeObject(-1);
				}
			}
			break;
		}
	}
	
	

	/* Overrides */
	@Override
	public void run() {
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