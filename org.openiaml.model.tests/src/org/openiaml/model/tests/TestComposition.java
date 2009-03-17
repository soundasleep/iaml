/**
 * 
 */
package org.openiaml.model.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.model.model.ModelPackage;


/**
 * Like {@link TestGranularity}, but with only composition, no 
 * abstraction/extends.
 * 
 * @author jmwright
 *
 */
public class TestComposition extends XmlTestCase {

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
	
	public void testComposition() throws Exception {
		loadAllPackages(ModelPackage.eINSTANCE);

		// lets find out all distances from InternetApplication
		/*
		for (EClass c : loaded) {
			int d = dijkstra("InternetApplication", c); 
			System.out.println("InternetApplication -> " + c.getName() + ": " + d);
		}
		*/
		String a = "InputTextField";
		String b = "CompositeOperation";
		System.out.println(a + " -> " + b + ": " + dijkstra(a, b));
		System.out.println(b + " -> " + a + ": " + dijkstra(b, a));
		
	}

	/**
	 * @param string
	 * @param c
	 * @return
	 */
	private int dijkstra(String string, EClass c) {
		return dijkstra(findLoaded(string), c);
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
					int alt = distance.get(u) + distanceBetween(u, n);	// fixed distance of 1
					if (alt < distance.get(n)) {
						distance.put(n, alt);
						previous.put(n, u);
					}
				}
			}
 		}
		
		// print out the path from source to target
		printOutPath(source, target, previous);
		
		return distance.get(target);
	}

	/**
	 * Print out the path from source to target
	 * 
	 * @param source
	 * @param target
	 * @param previous
	 */
	private void printOutPath(EClass source, EClass target,
			Map<EClass, EClass> previous) {
		EClass cur = target;
		String buf = source.getName();
		int i = 0;
		while (cur != source && cur != null && i < 50) {
			buf = cur.getName() + " -> " + buf;
			cur = previous.get(cur);
			i++;
		}
		if (cur == null)
			buf = "[no path]";
		System.out.println(buf);
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
		Set<EClass> r = new HashSet<EClass>();
		
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
				/*
				 * We are no longer interested in the subtypes directly.
				 * But instead of the reference types involved in these
				 * subtypes.
				 * 
				 * r.add(c);
				 */
			}
		}
		
		// ... and all types of contained multiple references
		for (EReference ref : u.getEAllReferences()) {
			// that actually BELONG to this class
			//if (ref.getContainerClass().equals(u)) {
				if (!ref.getEReferenceType().equals(ModelPackage.eINSTANCE.getGeneratesElements())
						&& !ref.getEReferenceType().equals(ModelPackage.eINSTANCE.getGeneratedElement())
						&& ref.isContainment() /* no reverse inEdges etc */
						&& ref.getUpperBound() == -1 ) {
					r.add( ref.getEReferenceType() );
					
					/**
					 * We are also interested in all the subtypes of
					 * these reference types.
					 */
					for (EClass c : loaded) {						
						if ( c.getEAllSuperTypes().contains(ref.getEReferenceType()) ) {
							r.add(c);
						}
					}
				}
			//}
		}
		
		// remove all GeneratedElement/GeneratesElements
		r.remove(ModelPackage.eINSTANCE.getGeneratedElement());
		r.remove(ModelPackage.eINSTANCE.getGeneratesElements());
		
		return new ArrayList<EClass>(r);
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


	
}
