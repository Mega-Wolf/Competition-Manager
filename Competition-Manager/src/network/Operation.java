package network;

public enum Operation {
	
	//operation				//input values												//output values
	
	/* PlayerManager */
	GET_PLAYER,				//Integer id												//Player player
	MATCHING_PLAYERS,		//Player matchingPlayer; -1 / null as wildcard				//Map<Integer, Player>
	ADD_PLAYER,				//Player newPlayer											//void
	REMOVE_PLAYER,			//Integer id												//void
	UPDATE_PLAYER,			//Integer id, Player changedPlayer							//void
	
	/* TeamManager */
	GET_TEAM,				//Integer id												//Team team
	MATCHING_TEAMS,			//Team matchingTeam; -1 / null as wildcard					//Map<Integer, Team>
	UPDATE_TEAM,			//Integer id, Team newTeam									//Boolean updated
	
	/* MatchManager*/
	GET_MATCH,				//Integer id												//Match match
	MATCHING_MATCHES,		//Match matchingMatch; -1 / null as wildcard				//Map<Integer, Match>
	UPDATE_MATCH,			//TODO
	
	/* GroupManager*/
	GET_GROUP,  			//Integer id												//Group group
	MATCHING_GROUPS, 		//Group matchingGroup; -1 / null as wildcard				//Map<Integer, Group>
	
	/* other (TODO) */  
	
	CREATE_GROUPS
}