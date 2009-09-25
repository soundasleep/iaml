/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.DijkstraAlgorithm;
import org.openiaml.emf.properties.DefaultPropertyInvestigator;
import org.openiaml.emf.properties.IEMFElementSelector;

/**
 * The height from the root, i.e. the longest children height from the root.
 * 
 * @author jmwright
 *
 */
public class RootContainmentsHeight extends DefaultPropertyInvestigator {

	/**
	 * @param name
	 */
	public RootContainmentsHeight(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	public Object evaluate(final EObject root) {
		
		// use dijkstra's algorithm to find the shortest path between any vertices
		DijkstraAlgorithm<EObject> dj = new DijkstraAlgorithm<EObject>() {

			@Override
			public Collection<EObject> getEdges() {
				// vertices = all EObjects in the root							
				Collection<EObject> nodes = toCollection(root.eAllContents());
				// add self
				nodes.add(root);
				return removeIgnoredClasses(nodes);
			}

			@Override
			public List<EObject> getNeighbours(EObject u) {
				// edges = all EReferences (including containments)
				List<EObject> neighbours = new ArrayList<EObject>();
				for (EReference ref : u.eClass().getEAllContainments()) {
					if (ignoreReference(ref))
						continue;	// ignore

					if (ref.isMany()) {
						List<?> r = (List<?>) u.eGet(ref);
						for (Object rr : r) {
							neighbours.add((EObject) rr);
						}
					} else {
						if (u.eGet(ref) != null) {
							neighbours.add((EObject) u.eGet(ref));
						}
					}
				}
				return neighbours;
			}
			
		};
		
		// for all nodes
		int max = -1;
		for (EObject target : dj.getEdges()) {
			// ignoring self
			if (!root.equals(target)) {
				int path = dj.dijkstra(root, target);
				// ignore paths that cannot be found
				if (path > max) {
					max = path;	// select the maximum
				}
			}
		}

		return max;	// return the maximum value
	}
}