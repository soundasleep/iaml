/**
 * 
 */
package org.openiaml.emf.properties.library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.openiaml.emf.DijkstraAlgorithmWithPaths;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IterateOverAll;

/**
 * Cycles in references.
 * 
 * @author jmwright
 *
 */
public class ReferencesCycles extends IterateOverAll {
	
	/**
	 * The actual root of the entire hierarchy, so we can access
	 * <em>all</em> children in the entire graph, and not just those
	 * directly contained within each iterated object. 
	 */
	private EObject actualRoot = null;
	
	@Override
	public Object evaluate(EObject root) {
		actualRoot = root;
		
		// continue iteration
		return super.evaluate(root);
	}

	/**
	 * If we find a cycle A->B->C->A, it is obvious that 
	 * there is a cycle B->B and C->C. 
	 * 
	 * We save all of the elements found in the cycle so we
	 * may ignore these cycles when they are requested later. 
	 */
	private Set<EObject> cyclesFound = new HashSet<EObject>();

	/**
	 * @param name
	 */
	public ReferencesCycles(String name, IEMFElementSelector selector) {
		super(name, selector);
	}
	
	/**
	 * The cycle checker is achieved by creating a "pseudo-object"
	 * which has all the same incoming references and outgoing references
	 * of the source element (root), and seeing if a path exists
	 * between these two objects.
	 * 
	 * @param root the source element
	 */
	@Override
	public int get(final EObject root) {

		// have we already found a cycle for this element somewhere else?
		if (cyclesFound.contains(root)) {
			// if so, we have already marked this as a cycle
			return 0;
		}	
				
		// a pseudo-object
		final EObject pseudo = new BasicEObjectImpl() {
			
		};
		
		// use dijkstra's algorithm to find the shortest path between any vertices
		DijkstraAlgorithmWithPaths<EObject> dj = new DijkstraAlgorithmWithPaths<EObject>() {

			/**
			 * Necessary to override this, because BasicEObjectImpl.toString()
			 * will throw an UnsupportedOperationException.
			 */
			@Override
			public String translateToString(EObject element) {
				if (element == pseudo)
					return "(psuedo)";

				return super.translateToString(element);
			}

			@Override
			public Collection<EObject> getEdges() {
				// vertices = all EObjects in the <em>actual</em> root							
				Collection<EObject> nodes = toCollection(actualRoot.eAllContents());
				// add self
				nodes.add(actualRoot);
				nodes = removeIgnoredClasses(nodes);
				
				// add a pseudo-element for the target
				nodes.add(pseudo);
				return nodes;
			}

			@Override
			public List<EObject> getNeighbours(EObject u) {
				// is this the pseudo object?
				if (u == pseudo)
					u = root;
				
				// edges = all EReferences (including containments)
				List<EObject> neighbours = new ArrayList<EObject>();
				for (EReference ref : u.eClass().getEAllReferences()) {
					if (ignoreReference(ref))
						continue;	// ignore

					if (ref.isMany()) {
						List<?> r = (List<?>) u.eGet(ref);
						for (Object rr : r) {
							neighbours.add((EObject) rr);
							if (rr == root)	// add a reference to the target
								neighbours.add(pseudo);
						}
					} else {
						if (u.eGet(ref) != null) {
							neighbours.add((EObject) u.eGet(ref));
							if (u.eGet(ref) == root)	// add a reference to the target
								neighbours.add(pseudo);
						}
					}
				}
				return neighbours;
			}
			
		};
		
		int path = dj.dijkstra(root, pseudo);
		if (path == -1)
			return 0;	// no path
		
		// add of the elements found in the cycle
		for (EObject cycleElement : dj.getLastPathElements()) {
			if (cycleElement != pseudo)
				cyclesFound.add(cycleElement);
		}
		
		return 1; // found a cycle
	}
}