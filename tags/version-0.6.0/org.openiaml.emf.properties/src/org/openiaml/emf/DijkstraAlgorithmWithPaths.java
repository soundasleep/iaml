/**
 * 
 */
package org.openiaml.emf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Extends {@link DijkstraAlgorithm} to also provide a method to get
 * the actual elements used in the path. i.e.:
 *
 * int length = d.dijkstra(a, b);
 * List<T> path = d.getLastPathElements();
 *
 * @author jmwright
 *
 * @param <T>
 */
public abstract class DijkstraAlgorithmWithPaths<T> extends DijkstraAlgorithm<T> {

	private List<T> lastPath;

	/**
	 * We override compilePath to also create a copy of the
	 * path.
	 *
	 * @see #getLastPathElements()
	 */
	@Override
	public String compilePath(T source, T target, Map<T, T> previous) {

		lastPath = new ArrayList<T>();

		T cur = target;
		int i = 0;
		while (cur != source && cur != null && i < MAX_COMPILE_PATH) {
			// buf = " -> " + translateToString(cur) + buf;
			lastPath.add(cur);
			cur = previous.get(cur);
			i++;
		}
		// buf = translateToString(source) + buf;
		lastPath.add(source);

		// we need to reverse the list
		Collections.reverse(lastPath);

		// this method actually needs to return a string
		return super.compilePath(source, target, previous);
	}

	/**
	 * Get the last path called by {@link #dijkstra(Object, Object)}.
	 *
	 * This path is compiled in {@link #compilePath(Object, Object, Map)}.
	 *
	 * @return the last path found. this path may be empty.
	 */
	public List<T> getLastPathElements() {
		return lastPath;
	}

}