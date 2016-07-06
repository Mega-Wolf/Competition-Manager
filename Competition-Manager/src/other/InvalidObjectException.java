package other;

public class InvalidObjectException extends Exception {
	/**
	 * Needed for Serialization
	 */
	private static final long serialVersionUID = -8141226994385831608L;
	
	private EqualWildCard invalidObject;
	
	public InvalidObjectException(EqualWildCard invalidObject) {
		super();
		this.invalidObject = invalidObject;
	}
	
	public InvalidObjectException(String message, EqualWildCard invalidObject) {
		super(message);
		this.invalidObject = invalidObject;
	}
	
	public EqualWildCard getInvalidObject() {
		return invalidObject;
	}
}
