package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import match.MatchBasic;
import network.exceptions.ServerException;
import other.Group;
import other.Manager;
import other.Player;
import other.Team;

/**
 * This class handles a connection between the server and a client.
 */
public class Connection extends Thread {
	// Should have been divided into state-pattern first, but this turned out to
	// be
	// even more complicated;
	// therefore sticked to the nested switch stuff

	/* Variables */

	/**
	 * several Managers organized in a map and associated with the corresponding
	 * Operand; created by the CompetitionServer
	 */
	protected Map<Operand, Manager<?>> managerMap;

	/**
	 * output; write output from requests here
	 */
	protected ObjectOutputStream out;

	/**
	 * input; read the client instructions from here
	 */
	protected ObjectInputStream in;

	/* Constructor */

	/**
	 * 
	 * @param socket
	 * @param managerMap
	 * @throws IOException
	 */
	public Connection(Socket socket, Map<Operand, Manager<?>> managerMap) throws IOException {
		this.managerMap = managerMap;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}

	/* Methods */

	/**
	 * Reacts on input of the client
	 * 
	 * @param operation
	 *            the {@link Operation} to perform
	 * @param operand
	 *            the {@link Operand}, with which to perform the operation
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	protected void reactOnInput(Operation operation, Operand operand) throws IOException, ClassNotFoundException {

		Manager<?> manager = managerMap.get(operand);
		switch (operation) {
		case GET:
			out.writeObject(manager.get((int) in.readObject()));
			break;
		case GET_MATCHING:
			out.writeObject(manager.getMatching(in.readObject()));
			break;
		case ADD:
			try {
				handleAdd(operand);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServerException e) {
				out.writeObject(-1);
				out.writeObject(e);
			}
			break;
		case INSTRUCTION:
			try {
				handleInstruction(operand);
				out.writeObject(true);
			} catch (ServerException e) {
				out.writeObject(false);
				out.writeObject(e);
			}
			break;
		case REMOVE:
			try {
				handleRemove(operand);
				out.writeObject(true);
			} catch (UnsupportedOperationException e) {
				out.writeObject(false);
				out.writeObject(new ServerException(Operation.REMOVE, operand, "Tournament already startet. You cannot remove teams or players anymore."));
			}
			break;
		}
	}
	
	/**
	 * Handles the insertion of Players and Teams
	 * 
	 * @param operand
	 *            {@link Operand.PLAYER} or {@link Operand.TEAM}
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws ServerException 
	 */
	@SuppressWarnings({ "unchecked", "incomplete-switch" })
	private void handleAdd(Operand operand) throws IOException, ServerException, ClassNotFoundException {
		Manager<Player> playerManager = (Manager<Player>) managerMap.get(Operand.PLAYER);
		Manager<Team> teamManager = (Manager<Team>) managerMap.get(Operand.TEAM);
		switch (operand) {
		case PLAYER:
			Player addPlayer = (Player) in.readObject();
			// synchronized both, because the teamID must exist and the player
			// number must be unique inside the team
			synchronized (teamManager) {
				if (teamManager.get(addPlayer.getTeam()) != null) {
					// if the teamID does not exist, the players do not have to
					// be checked
					synchronized (playerManager) {
						boolean allowed = true;
						if (!playerManager
								.getMatching(new Player(addPlayer.getNumber(), addPlayer.getTeam(), null, null))
								.isEmpty()) {
							allowed = false;
						}
						if (allowed) {
							out.writeObject(playerManager.add(addPlayer));
						} else {
							throw new ServerException(Operation.ADD, operand, "Trikot-number allready in use.");
						}
					}
				} else {
					throw new ServerException(Operation.ADD, operand, "Team does not exist.");
				}
			}
			break;
		case TEAM:
			Team addTeam = (Team) in.readObject();
			// synchronization needed, because the write must be directly after
			// the read
			synchronized (teamManager) {
				boolean allowed = true;
				if (!teamManager.getMatching(new Team(null, addTeam.getAbbreviation())).isEmpty()
						|| !teamManager.getMatching(new Team(addTeam.getSchool(), null)).isEmpty()) {
					allowed = false;
				}
				if (allowed) {
					out.writeObject(teamManager.add(addTeam));
				} else {
					throw new ServerException(Operation.ADD, operand, "School and school abbreviation of a tem must be unique.");
				}
			}
			break;
		}
	}
	
	/**
	 * 
	 * @param operand
	 */
	private void handleInstruction(Operand operand) throws ServerException{
		switch (operand) {
		case STATE2:
			Manager<Team> teamManager = (Manager<Team>) managerMap.get(Operand.TEAM);
			Manager<Player> playerManager = (Manager<Player>) managerMap.get(Operand.PLAYER);
			synchronized (teamManager) {
				synchronized (playerManager) {
					Set<Integer> teamIDSet = teamManager.getMatching(new Team(null, null)).keySet();

					if (teamIDSet.size() != 16) {
						throw new ServerException(Operation.INSTRUCTION, operand, "Not enough teams");
					}

					for (Integer teamID : teamIDSet) {
						int teamSize = playerManager.getMatching(new Player(-1, teamID, null, null))
								.size();
						if (teamSize < Team.TEAM_SIZE_MIN || teamSize > Team.TEAM_SIZE_MAX) {
							throw new ServerException(Operation.INSTRUCTION, operand, "Wrong number of players in team " + teamManager.get(teamID).getAbbreviation());
						}
					}

					teamManager.lock();
					playerManager.lock();
				}
			}
			Manager<Group> groupManager = (Manager<Group>) managerMap.get(Operand.GROUP);
			//synchronization so only one thread can create groups and group matches
			synchronized (groupManager) {
				List<Integer> teamListIDs = groupManager.getMatching(new Group(null)).keySet().stream().collect(Collectors.toList()); 
				
				if (teamListIDs.isEmpty()) {
					List<Group> groupList = ServerHandler.createGroups(teamListIDs);
					List<MatchBasic> matchList = ServerHandler.createGroupMatches(groupList);
					for (Group g : groupList) {
						groupManager.add(g);
					}
					Manager<MatchBasic> matchManager = (Manager<MatchBasic>) managerMap.get(Operand.MATCH_BASIC);
					for (MatchBasic m : matchList) {
						matchManager.add(m);
					}
				} else {
					throw new ServerException(Operation.INSTRUCTION, operand, "Tournament already started");
				}
			}
			break;
		}
	}
	
	@SuppressWarnings({ "unchecked", "incomplete-switch" })
	private void handleRemove(Operand operand) throws ClassNotFoundException, IOException, UnsupportedOperationException{
		Manager<Player> playerManager = (Manager<Player>) managerMap.get(Operand.PLAYER);
		switch(operand) {
		case PLAYER:
			//synchronized because shall not remove player during group generation
			synchronized (playerManager) {
				playerManager.remove((int) in.readObject());
			}
			break;
		case TEAM:
			Manager<Team> teamManager = (Manager<Team>) managerMap.get(Operand.TEAM);
			//synchronized because shall not remove team or player during group generation
			synchronized (teamManager) {
				synchronized (playerManager) {
					int teamID = (int) in.readObject();
					teamManager.remove(teamID);
					Set<Integer> removePlayerSet = playerManager.getMatching(new Player(-1, teamID, null, null)).keySet();
					for (Integer playerID : removePlayerSet) {
						playerManager.remove(playerID);
					}
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