package other;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Manager<V extends EqualWildCard> {
	
	/* Variables */
	private boolean lock = false;
	private int idCounter = 0;
	private Map<Integer, V> map = /*Collections.synchronizedMap(new HashMap<Integer, V>());*/ new ConcurrentHashMap<>();
	
	/* Methods */
	public /*synchronized*/ void add(V newV/*, Predicate<V> pred*/) {
		// must be synchronized, because the put must happen directly after the check
		//if (pred.test(newV)) {
		if (!lock) {
			map.put(idCounter++, newV);
		}
		//}
	}
	
	public Map<Integer, V> getMatching(Object matchingObject){
		return map.entrySet().parallelStream().filter(p -> p.getValue().equalsWC(matchingObject)).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
	}
	
	public V get(int id) {
		return map.get(id);
	}
	
	public void remove(Integer id) {
		map.remove(id);
	}
	
	public void lock() {
		//map = Collections.unmodifiableMap(map);
		lock = true;
	}
}