package network;

public enum Operation {
	
	/* PlayerManager */
	GET_PLAYER,				//Integer id
	MATCHING_PLAYERS,		//Player matchingPlayer; -1 / null as wildcard
	ADD_PLAYER,				//Player newPlayer
	REMOVE_PLAYER,			//Player oldPlayer
	UPDATE_PLAYER,			//Player oldPlayer, Player changedPlayer
	
	/* TeamManager */
	GET_TEAM,				//Integer id
	MATCHING_TEAMS,			//Team matchingTeam; -1 / null as wildcard
	UPDATE_TEAM,			//Team oldTeam, Team newTeam
	
	/* MatchManager*/
	GET_MATCH,				//Integer id
	MATCHING_MATCHES,		//Match matchingMatch; -1 / null as wildcard
	UPDATE_MATCH,			//TODO
	
	/* GroupManager*/
	GET_GROUP,  			//Integer id
	MATCHING_GROUPS, 		//Group matchingGroup; -1 / null as wildcard
	
	/* other (TODO) */  
	
	CREATE_GROUPS
}