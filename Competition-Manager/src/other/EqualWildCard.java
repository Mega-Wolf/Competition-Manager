package other;

/**
 * Allows to compare two objects with wildcards
 */
public interface EqualWildCard {
	
	/**
	 * Checks whether two objects are equal, despite some wildcards
	 * @param obj the object to compare to; by convention wildcards should be {@literal -1} for ints and {@literal null} for Objects
	 * @return {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise. 
	 */
	public boolean equalsWC(Object obj);
}