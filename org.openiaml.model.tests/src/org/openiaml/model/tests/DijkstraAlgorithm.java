/**
 * 
 */
package org.openiaml.model.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An abstract representation of Djikstra's algorithm. It is up to
 * subclasses to implement both the types involved, and the
 * neighbours/weighting functions.
 * 
 * @author jmwright
 *
 */
public abstract class DijkstraAlgorithm<T> {

	/** Static value for an infinite path (i.e. no path exists) */
	private static final int INFINITE = 2 << 24;
	
	/** Maximum depth that we will try to compile a path */
	protected static final int MAX_COMPILE_PATH = 1024;

	/**
	 * Get all of the edges (nodes) involved in this graph.
	 * 
	 * @return
	 */
	public abstract Collection<T> getEdges();
	
	/**
	 * Allows abstract supertypes to extend getInternalEdges() while
	 * leaving getEdges() abstract.
	 * 
	 * @return
	 */
	protected Collection<T> getInternalEdges() {
		return getEdges();
	}
	
	/**
	 * Get the shortest path distance between source and target.
	 * Returns -1 if no path can be found.
	 * The last path found is stored in {@link #getLastPath()}.
	 * 
	 * @param source
	 * @param target
	 */
	public int dijkstra(T source, T target) {
		Map<T, Integer> distance = new HashMap<T, Integer>();
		// previous is probably not necessary if we don't need to
		// find the path taken
		Map<T, T> previous = new HashMap<T, T>();
		
		// initialise
		for (T c : getInternalEdges()) {
			distance.put(c, INFINITE);
			previous.put(c, null);
		}
		
		// distance from source to source = 0
		distance.put(source, 0);
		
		// queue of all elements in the graph to look through
		List<T> queue = new ArrayList<T>();
		queue.addAll( getInternalEdges() );
		
		while (!queue.isEmpty()) {
			// more elements to process
			T u = smallestDistance(queue, distance);
			queue.remove(u);
			
			// for each neighbour in u
			for (T n : getInternalNeighbours(u)) {
				// where the neighbour is still in the queue
				if (queue.contains(n)) {
					int alt = distance.get(u) + distanceBetween(u, n);	// fixed distance of 1
					if (alt < distance.get(n)) {
						distance.put(n, alt);
						previous.put(n, u);
					}
				}
			}
 		}
		
		// print out the path from source to target
		lastPath = compilePath(source, target, previous); 
		
		if (distance.get(target) == null) {
			throw new NullPointerException("No target named '" + target + "' found.");
		}
		
		if (distance.get(target) == INFINITE) {
			return -1;
		}
		return distance.get(target);
	}
	
	protected String lastPath = null;

	/**
	 * Compile the path from source to target.
	 * 
	 * @param source
	 * @param target
	 * @param previous
	 */
	public String compilePath(T source, T target,
			Map<T, T> previous) {
		T cur = target;
		int i = 0;
		String buf = "";
		while (cur != source && cur != null && i < MAX_COMPILE_PATH) {
			buf = " -> " + translateToString(cur) + buf;
			cur = previous.get(cur);
			i++;
		}
		buf = translateToString(source) + buf;
		if (cur == null)
			buf = "[no path]";
		return buf;
	}
	
	/**
	 * You should override this method if you wish to change
	 * the output of the compiled path.
	 * 
	 * @param element
	 * @return
	 */
	public String translateToString(T element) {
		return element.toString();
	}

	/**
	 * The distance between two elements. By default, this is
	 * constant (1), but can be overridden where necessary.
	 * 
	 * @param u
	 * @param n
	 * @return
	 */
	public int distanceBetween(T u, T n) {
		return 1;
	}

	/**
	 * Get the neighbours to a given edge. Subclasses
	 * must implement this method.
	 * 
	 * @param u
	 * @return
	 */
	public abstract List<T> getNeighbours(T u);
	
	/**
	 * Allows abstract supertypes to extend getInternalNeighbours() while
	 * leaving getNeighbours() abstract.
	 * 
	 * @return
	 */
	public List<T> getInternalNeighbours(T u) {
		return getNeighbours(u);
	}

	/**
	 * Find the element in queue with the smallest distance.
	 * 
	 * @param queue
	 * @param distance
	 * @return
	 */
	private T smallestDistance(List<T> queue,
			Map<T, Integer> distance) {
		T result = queue.get(0);	// first = default
		for (T c : queue) {
			if (distance.get(c) < distance.get(result)) {
				result = c;
			}
		}
		return result;
	}

	/**
	 * Get the last path used (as a string).
	 * 
	 * @return
	 */
	public String getLastPath() {
		return lastPath;
	}

}
