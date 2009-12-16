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
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * @author jmwright
 *
 */
public class ReferencesWithoutContainmentsRadius extends IterateOverAll {
	private int min = -1;

	/**
	 * @param name
	 */
	public ReferencesWithoutContainmentsRadius(String name, IEMFElementSelector selector) {
		super(name, selector);
	}

	@Override
	public Object evaluate(EObject root) {
		// evaluate as normal
		super.evaluate(root);
		// but return the maximum
		return min;
	}

	@Override
	public int get(final EObject root) {
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
				for (EReference ref : u.eClass().getEAllReferences()) {
					if (ignoreReference(ref))
						continue;	// ignore
					if (ref.isContainment())
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
		int maxPath = -1;
		double count = 0;
		for (EObject source : dj.getEdges()) {
			System.out.println(( count / (dj.getEdges().size() + 1) * 100) + "%");
			count++;
			for (EObject target : dj.getEdges()) {
				// ignoring self
				if (!source.equals(target)) {
					int path = dj.dijkstra(source, target);
					// ignore paths that cannot be found
					if (path > maxPath) {
						maxPath = path;	// select the maximum
					}
				}
			}
		}
		
		// but only return the minimum
		if (maxPath != -1) {
			if (maxPath < min || min == -1) {
				min = maxPath;
			}
		}
		
		return 0;	// ignore return value
	}
}