package network;

/**
 * Provides an error code for the client
 */
public class ServerException extends Exception{
	//decided to use just this Exception instead of e.g. AddException - AddPlayerException - AddPlayerTeamFoundException etc.
	
	/**
	 * Needed for serialization
	 */
	private static final long serialVersionUID = -8374396847073225713L;
	
	/**
	 * operation in which the exception occurred
	 */
	private Operation operation;
	
	/**
	 * operand, with which the exception occurred
	 */
	private Operand operand;
	
	public ServerException(Operation operation, Operand operand, String message) {
		super(message);
		this.operation = operation;
		this.operand = operand;
	}
	

	/**
	 * Returns the operation, in which the exception occurred
	 * @return the {@link Operation}
	 */
	public Operation getOperation() {
		return operation;
	}
	
	/**
	 * Returns the operand, with which the exception occurred
	 * @return the {@link Operand}
	 */
	public Operand getOperand() {
		return operand;
	}
	
	/* Overrides */ 
	
}