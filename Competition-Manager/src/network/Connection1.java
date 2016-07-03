package network;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

import other.Manager;
import other.Player;
import other.Team;

public class Connection1 extends Connection {

	/* Constructor */
	public Connection1(Socket socket, Map<Operand, Manager<?>> managerMap) throws IOException {
		super(socket, managerMap);
	}

	@SuppressWarnings({ "incomplete-switch", "unchecked" })
	@Override
	protected void reactOnInput(Operation operation, Operand operand) throws ClassNotFoundException, IOException {
		super.reactOnInput(operation, operand);

		Manager<?> manager = managerMap.get(operand);
		switch (operation) {
		case ADD:
			switch (operand) {
			case PLAYER:
				Player addPlayer = (Player) in.readObject();
				// synchronized both, because the teamID must exist and the player number must be unique inside the team
				synchronized (managerMap.get(Operand.TEAM)) {
					if (managerMap.get(Operand.TEAM).get(addPlayer.getTeam()) != null) {
						// if the teamID does not exist, the players do not have to be checked
						synchronized (managerMap.get(Operand.PLAYER)) {
							boolean allowed = true;
							if (!managerMap.get(Operand.PLAYER)
									.getMatching(new Player(addPlayer.getNumber(), addPlayer.getTeam(), null, null)).isEmpty()) {
								allowed = false;
							}
							if (allowed) {
								((Manager<Player>) managerMap.get(Operand.PLAYER)).add(addPlayer);
							}
						}
					}
				}
				break;
			case TEAM:
				Team addTeam = (Team) in.readObject();
				// synchronization needed, because the write must be directly after the read
				synchronized (managerMap.get(Operand.TEAM)) {
					boolean allowed = true;
					if (!managerMap.get(Operand.TEAM).getMatching(new Team(null, addTeam.getAbbreviation())).isEmpty()
							|| !managerMap.get(Operand.TEAM).getMatching(new Team(addTeam.getSchool(), null))
									.isEmpty()) {
						allowed = false;
					}
					if (allowed) {
						((Manager<Team>) managerMap.get(Operand.TEAM)).add(addTeam);
					}
				}
				break;
			}
			break;
		case REMOVE:
			switch (operand) {
			case PLAYER:
				managerMap.get(Operand.PLAYER).remove((int) in.readObject());
				break;
			case TEAM:
				int teamID = (int) in.readObject();
				// must synchronize the player manager, because no player i
				// allowed to reference this team anymore
				synchronized (managerMap.get(Operand.PLAYER)) {
					for (Integer i : managerMap.get(Operand.PLAYER).getMatching(new Player(-1, teamID, null, null)).keySet()) {
						managerMap.get(Operand.PLAYER).remove(i);
					}
				}
				managerMap.get(Operand.TEAM).remove(teamID);
				break;
			}
			break;
		case INSTRUCTION:
			switch (operand) {
			case STATE2:
				//synchronization order must be same as above (ADD; PLAYER) to prevent deadlock
				synchronized (managerMap.get(Operand.TEAM)) {
					synchronized (managerMap.get(Operand.PLAYER)) {
						Set<Integer> teamIDSet = managerMap.get(Operand.TEAM).getMatching(new Team(null, null)).keySet();
						
						if (teamIDSet.size() != 16) {
							//TODO; Exception
						}
						
						for (Integer teamID : teamIDSet) {
							int teamSize = managerMap.get(Operand.PLAYER).getMatching(new Player(-1, teamID, null, null)).size(); 
							if (teamSize < Team.TEAM_SIZE_MIN || teamSize > Team.TEAM_SIZE_MAX) {
								//TODO; Exception
							}
						}
						
						managerMap.get(Operand.TEAM).lock();
						managerMap.get(Operand.PLAYER).lock();
					}
				}
				break;
		}
			break;
		}
	}
}