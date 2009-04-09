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

	private CompositionDijkstra dj = new CompositionDijkstra();

	Map<EClass, Integer> granularity = new HashMap<EClass, Integer>();
	
	public void testComposition() throws Exception {
		// lets find out all distances from InternetApplication
		/*
		for (EClass c : loaded) {
			int d = dijkstra("InternetApplication", c); 
			System.out.println("InternetApplication -> " + c.getName() + ": " + d);
		}
		*/
		String a = "InputTextField";
		String b = "CompositeOperation";
		System.out.println(a + " -> " + b + ": " + dj.dijkstra(a, b));
		System.out.println(b + " -> " + a + ": " + dj.dijkstra(b, a));
		
	}
	
	/**
	 * {@link org.openiaml.model.tests.inference.DumpDroolsXml} instantiates
	 * this class and uses this method to check that there is a path
	 * from head to body, but no path from body to head.
	 * 
	 * @param source
	 * @param target
	 */
	public void checkDijkstra(String source, String target) {
		int d1 = dj.dijkstra(source, target);
		assertNotEquals(source + " -> " + target + " should not be infinite. " + dj.getLastPath(), d1, -1);

		int d2 = dj.dijkstra(target, source);
		assertEquals(target + " -> " + source + " should be infinite. " + dj.getLastPath(), -1, d2);
		
	}

	/**
	 * @param string
	 * @param a
	 * @param b
	 */
	public void assertNotEquals(String message, int a, int b) {
		if (a == b)
			fail(message + ": " + a + " should not have been " + b);
	}
	
	/**
	 * Implement EClass composition as a graph.
	 * 
	 * @author jmwright
	 *
	 */
	protected class CompositionDijkstra extends DijkstraAlgorithm<EClass> {

		/**
		 * @param string
		 * @param c
		 * @return
		 */
		public int dijkstra(String string, EClass c) {
			return dijkstra(findLoaded(string), c);
		}

		/**
		 * @param string
		 * @param string2
		 * @return
		 */
		public int dijkstra(String string, String string2) {
			return dijkstra(findLoaded(string), findLoaded(string2));
		}

		/**
		 * Singleton pattern that loads all elements in the root package.
		 *  
		 * @return
		 */
		public List<EClass> getLoadedClasses() {
			if (_loaded.isEmpty()) {
				loadPackage(ModelPackage.eINSTANCE);
			}
			
			return _loaded;
		}
		
		private void loadPackage(EPackage p) {
			for (EObject o : p.eContents()) {
				if (o instanceof EPackage) {
					loadPackage((EPackage) o);
				} else if (o instanceof EClass) {
					_loaded.add((EClass) o);
				} else if (o instanceof EAnnotation || o instanceof EEnum || o instanceof EDataType) {
					// ignore
				} else {
					throw new RuntimeException("Cannot load package element of type " + o.getClass() + ": " + o);
				}
			}		
		}

		private List<EClass> _loaded = new ArrayList<EClass>();

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.DjikstraAlgorithm#getEdges()
		 */
		@Override
		protected List<EClass> getEdges() {
			return getLoadedClasses();
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.DjikstraAlgorithm#getNeighbours(java.lang.Object)
		 */
		@Override
		public List<EClass> getNeighbours(EClass u) {
			List<EClass> loaded = getLoadedClasses();

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
		 * Find a loaded class.
		 * 
		 * @param name
		 * @return
		 */
		private EClass findLoaded(String name) {
			List<EClass> loaded = getLoadedClasses();
			
			for (EClass c : loaded) {
				if (c.getName().equals(name)) {
					return c;
				}
			}
			
			System.err.println(loaded);
			throw new RuntimeException("Could not find loaded class with name " + name);
		}

	}

	/**
	 * For {@link org.openiaml.model.tests.inference.DumpDroolsXml#checkRulesCompositionGraph}.
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public int dijkstra(String source, String target) {
		return dj.dijkstra(source, target);
	}

}
