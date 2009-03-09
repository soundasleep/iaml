/**
 * 
 */
package org.openiaml.model.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.model.model.ModelPackage;


/**
 * @author jmwright
 *
 */
public class TestGranularity extends XmlTestCase {

	public static final String ROOT = "../org.openiaml.model/model/";

	List<EClass> loaded = new ArrayList<EClass>();
	Map<EClass, Integer> granularity = new HashMap<EClass, Integer>();

	private EReference lastReference;
	
	protected void loadAllPackages(EPackage p) {
		for (EObject o : p.eContents()) {
			if (o instanceof EPackage) {
				loadAllPackages((EPackage) o);
			} else if (o instanceof EClass) {
				loaded.add((EClass) o);
			} else if (o instanceof EAnnotation || o instanceof EEnum || o instanceof EDataType) {
				// ignore
			} else {
				throw new RuntimeException("Cannot load package element of type " + o.getClass() + ": " + o);
			}
		}
	}
	
	public void testGranularity() throws Exception {
		loadAllPackages(ModelPackage.eINSTANCE);

		// distance from InternetApplication to Operation
		int d = dijkstra("InternetApplication", "AbstractDomainAttribute");
		System.out.println("Distance from InternetApplication to AbstractDomainAttribute: " + d);
		/*
		int d = dijkstra("CompositeOperation", "InternetApplication");
		System.out.println("Distance from CompositeOperation to InternetApplication: " + d);
		*/

		/*
		int d2 = dijkstra("InternetApplication", "LoginHandler");
		System.out.println("Distance from InternetApplication to LoginHandler: " + d2);
		*/

		/*
		// start with InternetApplication
		granularise("InternetApplication", 0);
		
		// print everything out
		for (EClass c : granularity.keySet()) {
			int g = granularity.get(c);
			System.out.println(c.getName() + ": " + g);
		}
		*/
		
	}

	/**
	 * @param string
	 * @param string2
	 * @return
	 */
	private int dijkstra(String string, String string2) {
		return dijkstra(findLoaded(string), findLoaded(string2));
	}

	/**
	 * @param source
	 * @param target
	 */
	private int dijkstra(EClass source, EClass target) {
		int infinity = 5000;
		Map<EClass, Integer> distance = new HashMap<EClass, Integer>();
		// previous is probably not necessary if we don't need to
		// find the path taken
		Map<EClass, EClass> previous = new HashMap<EClass, EClass>();
		
		// initialise
		for (EClass c : loaded) {
			distance.put(c, infinity);
			previous.put(c, null);
		}
		
		// distance from source to source = 0
		distance.put(source, 0);
		
		// queue of all elements in the graph to look through
		List<EClass> queue = new ArrayList<EClass>();
		queue.addAll( loaded );
		
		while (!queue.isEmpty()) {
			// more elements to process
			EClass u = smallestDistance(queue, distance);
			queue.remove(u);
			
			// for each neighbour in u
			for (EClass n : getNeighbours(u)) {
				// where the neighbour is still in the queue
				if (queue.contains(n)) {
					System.out.println(u + " is connected to " + n);
					int alt = distance.get(u) + distanceBetween(u, n);	// fixed distance of 1
					if (alt < distance.get(n)) {
						distance.put(n, alt);
						previous.put(n, u);
					}
				}
			}
 		}
		
		for (EClass src : previous.keySet()) {
			if (previous.get(src) != null) {
				System.out.println("[ " + src.getName() + " -> " + previous.get(src).getName() + " ] = " + distance.get(src));
			} else {
				System.out.println("[ " + src.getName() + " -> null ] = " + distance.get(src));
			}
		}
		for (EClass src : distance.keySet()) {
			System.out.println("< " + src.getName() + " -> " + distance.get(src));
		}
		return distance.get(target);
	}

	/**
	 * @param u
	 * @param n
	 * @return
	 */
	private Integer distanceBetween(EClass u, EClass n) {
		return 1;
	}

	/**
	 * @param u
	 * @param n
	 * @return
	 */
	private Integer distanceBetweenOld(EClass u, EClass n) {
		// make sure n actually belongs to u
		for (EClass supertypes : u.getEAllSuperTypes()) {
			if (supertypes.equals(n))
				return 1;
		}
		
		for (EReference ref : u.getEAllReferences()) {
			if (ref.getUpperBound() == -1 && ref.getEReferenceType().equals(n)) {
				return 1;
			}
		}
		
		throw new RuntimeException(n.getName() + " is not connected to " + u.getName());
	}

	/**
	 * the "neighbours" of a class are those that are more concrete
	 * than the given class.
	 * 
	 * @param u
	 * @return
	 */
	private List<EClass> getNeighbours(EClass u) {
		List<EClass> r = new ArrayList<EClass>();
		
		// neighbours of a class include all of the supertypes...
		/*
		 * no they don't. this would say that an Operation is
		 * more concrete than a CompositeOperation
		r.addAll( u.getEAllSuperTypes() );
		*/
		//r.addAll( u.getEAllSuperTypes() );
		
		// they actually include the subtypes...
		for (EClass c : loaded) {
			if (c.getEAllSuperTypes().contains(u) ) {
				r.add(c);
			}
		}
		
		// ... and all types of contained multiple references
		for (EReference ref : u.getEAllReferences()) {
			// that actually BELONG to this class
			//if (ref.getContainerClass().equals(u)) {
				if (ref.getUpperBound() == -1) {
					r.add( ref.getEReferenceType() );
				}
			//}
		}
		
		// remove all GeneratedElement/GeneratesElements
		r.remove(ModelPackage.eINSTANCE.getGeneratedElement());
		r.remove(ModelPackage.eINSTANCE.getGeneratesElements());
		
		return r;
	}

	/**
	 * Find the element in queue with the smallest distance.
	 * 
	 * @param queue
	 * @param distance
	 * @return
	 */
	private EClass smallestDistance(List<EClass> queue,
			Map<EClass, Integer> distance) {
		EClass result = queue.get(0);	// first = default
		for (EClass c : queue) {
			if (distance.get(c) < distance.get(result)) {
				result = c;
			}
		}
		return result;
	}

	/**
	 * Find a loaded class.
	 * 
	 * @param name
	 * @return
	 */
	private EClass findLoaded(String name) {
		for (EClass c : loaded) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		throw new RuntimeException("Could not find loaded class with name " + name);
	}

	/**
	 * @param name name of class to granularise
	 * @param i
	 */
	private void granularise(String name, int i) {
		for (EClass c : loaded) {
			if (c.getName().equals(name)) {
				// found it
				granularise(c, i);
			}
		}
	}

	/**
	 * @param c
	 * @param i
	 */
	private void granularise(EClass c, int i) {
		List<EClass> stack = new ArrayList<EClass>();
		granularise(c, i, stack);
	}

	/**
	 * @param c
	 * @param i
	 */
	private void granularise(EClass c, int i, List<EClass> stack) {
		// ignore GeneratedElement and GeneratesElements
		if (c.equals( ModelPackage.eINSTANCE.getGeneratedElement() ) ||
				c.equals( ModelPackage.eINSTANCE.getGeneratesElements() )) {
			// skip
			return;
		}

		// bail on infinite loops
		if (!stack.isEmpty() && c.equals( stack.get(stack.size() - 1) )) {
			for (EClass s : stack) {
				System.out.println("> " + s);
			}
			System.out.println("last reference: " + lastReference + " belonging to " + lastReference.getContainerClass());
			throw new RuntimeException("Double loop with class " + c);
		}
		
		if (i > 150) {
			for (EClass s : stack) {
				System.out.println("> " + s);
			}
			throw new RuntimeException("Infinite granularity loop with class " + c);
		}
		
		// if non-abstract, increase granularity
		if (!c.isAbstract() && !c.isInterface()) {
			i++;
		}

		// do we already have it?
		if (granularity.containsKey(c)) {
			// only replace it if the granularity is lower
			if (granularity.get(c) > i) {
				System.out.println("replaced granularity for " + c + " from " + granularity.get(c) + " to " + i);
				granularity.put(c, i);
			}
		} else {
			// put it in
			granularity.put(c, i);
		}
		
		// all super types should be +1
		for (EClass type : c.getEAllSuperTypes()) {
			List<EClass> s2 = new ArrayList<EClass>(stack);
			s2.add(c);
			granularise(type, i + 1, s2);
		}

		// find all subtypes
		/*
		 * possibly not necessary to force?
		 * 
		for (EClass type : loaded) {
			if (!type.equals(c) && type.getEAllSuperTypes().contains(c) ) {
				// a subtype
				List<EClass> s2 = new ArrayList<EClass>(stack);
				s2.add(c);
				granularise(type, i, s2);
			}
		}
		*/
		
		// get all references
		for (EReference r : c.getEAllReferences()) {
			
			// don't do references of multiplicity 1 that have
			// EOpposite set.
			
			// reasoning: this is the 
			// GeneratedElement/GeneratesElements problem.
			
			if (!(r.getUpperBound() == 1 && r.getEOpposite() != null)) {
				
				// don't do self-contained references (e.g. a Session can contain Sessions)
				if (!r.getEReferenceType().equals(c)) {
					
					List<EClass> s2 = new ArrayList<EClass>(stack);
					s2.add(c);
					lastReference = r;
					granularise(r.getEReferenceType(), i + 1, s2);
					
				}
			}
		}
	}

	
}
