package test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Manager<V> {
	
	/* Variables */
	private int idCounter = 0;
	private Map<Integer, V> map = Collections.synchronizedMap(new HashMap<Integer, V>()); //new ConcurrentHashMap<>();
	
	
	
	
	/* Methods */
	public synchronized void add(V newV, Predicate<V> pred) {
		// must be synchronized, because the put must happen directly after the check
		if (pred.test(newV)) {
			map.put(idCounter++, newV);
		}
	}
	
	public Map<Integer, V> getMatching(V matchingV) {
		//TODO; parallelStream?
		
		return map.entrySet().parallelStream().filter(p -> p.equals(matchingV)).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
	}
	
	public void remove(Integer id) {
		map.remove(id);
	}
	
	public void update(Integer id, V changedV) {
		map.replace(id, changedV);
	}
}