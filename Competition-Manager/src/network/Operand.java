package network;

/**
 * The operand, with which the server shall do something
 * @see Operation
 */
public enum Operand {
	PLAYER,
	TEAM,
	GROUP,
	GROUP_STAT,
	GROUP_MATCH,
	ROUND_EXTENSION,
	MATCH_BASIC,
	
	START_TOURNAMENT			//instruction
}
