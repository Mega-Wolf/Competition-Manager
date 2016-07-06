package network;

/**
 * The Operation, the server shall do; followed by an {@link Operand} 
 */
public enum Operation {
	
	//Operation			3rd parameter		returns					//Exception
	GET,				//int id			//Object				---
	GET_MATCHING,		//Object			//Map<Integer, Object>	---
	ADD,				//Object			//int id				-1
	REMOVE,				//int id			//boolean				false
	SET,				//Object			//int id				-1
	
	INSTRUCTION			// ---				//boolean				false
}