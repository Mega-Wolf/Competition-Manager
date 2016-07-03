package other;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Manages entries in a map with an auto-increment id
 * Hides direct access to the map
 * @param <V> must extend {@link EqualWildCard}
 */
public class Manager<V extends EqualWildCard> {
	
	/* Variables */
	
	/**
	 * counter for the entries' ids; works like auto_increment in a database
	 */
	private int idCounter = 0;
	
	/**
	 * Associates entries with an id
	 * Uses {@link ConcurrentHashMap}, so iterations can be done without a {@link java.util.ConcurrentModificationException ConcurrentModificationException}
	 */
	private Map<Integer, V> map = new ConcurrentHashMap<>();
	
	/* Methods */
	
	/**
	 * Adds a new entry to the map
	 * @param newV the new entry
	 * @return the id of the new entry
	 */
	public /*synchronized*/ int add(V newV/*, Predicate<V> pred*/) {
		// must be synchronized, because the put must happen directly after the check
		//if (pred.test(newV)) {
			map.put(idCounter, newV);
			return idCounter++;
		//}
	}
	
	/**
	 * Returns a map of all entries matching the passed Object according to {@link EqualWildCard#equalsWC(Object obj) equalsWC(Object obj)}
	 * @param matchingObject the Object witch is compared to the entries
	 * @return a map of the matching entries with ids
	 */
	public Map<Integer, V> getMatching(Object matchingObject){
		return map.entrySet().parallelStream().filter(p -> p.getValue().equalsWC(matchingObject)).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
	}
	
	/**
	 * Returns the entry with the given id
	 * @param id the entry's id
	 * @return entry with given id
	 */
	public V get(int id) {
		return map.get(id);
	}
	
	/**
	 * Removes the entry with the given id
	 * @param id the entry's is
	 */
	public void remove(int id) {
		map.remove(id);
	}
	
	/**
	 * locks map; nothing can be added or removed anymore
	 */
	public void lock() {
		map = Collections.unmodifiableMap(map);
	}
}