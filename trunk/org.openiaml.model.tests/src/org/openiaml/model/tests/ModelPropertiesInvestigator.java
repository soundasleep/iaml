/**
 * 
 */
package org.openiaml.model.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.model.model.ModelPackage;

/**
 * For an upcoming paper, we need to investigate the properties
 * of the test models.
 * 
 * Given an input model, this will return a list of 
 * properties loaded from the model.
 * 
 * @author jmwright
 *
 */
public class ModelPropertiesInvestigator {

	/**
	 * Interface for a property investigator.
	 * 
	 * @author jmwright
	 *
	 */
	public interface IPropertyInvestigator {
		public String getName();
		public Object evaluate(EObject root);
	}
	
	/**
	 * Default abstract implementation.
	 * 
	 * @author jmwright
	 *
	 */
	public abstract class DefaultPropertyInvestigator implements IPropertyInvestigator {
		private String name;
		
		public DefaultPropertyInvestigator(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
		
	}
	
	/**
	 * Iterates over the root element and eAllContents and accumulates
	 * a value. 
	 * 
	 * @author jmwright
	 *
	 */
	public abstract class IterateOverAll extends DefaultPropertyInvestigator {

		public IterateOverAll(String name) {
			super(name);
		}

		@Override
		public Object evaluate(EObject root) {
			int result = get(root);
			TreeIterator<EObject> it = root.eAllContents();
			while (it.hasNext()) {
				result += get(it.next());
			}
			return result;
		}
		
		/**
		 * Get the property value for just the current object.
		 */
		public abstract int get(EObject obj);
		
	}
		
	private List<IPropertyInvestigator> investigators = null;
	
	public List<IPropertyInvestigator> getInvestigators() {
		if (investigators == null) {
			investigators = new ArrayList<IPropertyInvestigator>();
			// initialise
			
			investigators.add(new DefaultPropertyInvestigator("elements-count") {
				@Override
				public Object evaluate(EObject root) {
					return 1 + getSize(root.eAllContents());
				}
			});
			investigators.add(new IterateOverAll("attributes-count") {
				@Override
				public int get(EObject obj) {
					int result = 0;
					List<EAttribute> attributes = obj.eClass().getEAllAttributes();
					for (EAttribute attr : attributes) {
						if (obj.eGet(attr) != null) {
							result++;
						}
					}
					return result;
				}
			});
			investigators.add(new IterateOverAll("attributes-count-no-default") {
				@Override
				public int get(EObject obj) {
					int result = 0;
					List<EAttribute> attributes = obj.eClass().getEAllAttributes();
					for (EAttribute attr : attributes) {
						if (obj.eGet(attr) != null) {
							if (!obj.eGet(attr).equals( attr.getDefaultValue() )) { 
								result++;
							}
						}
					}
					return result;
				}
			});
			investigators.add(new IterateOverAll("references-count") {
				@Override
				public int get(EObject obj) {
					int result = 0;
					List<EReference> refs = obj.eClass().getEAllReferences();
					for (EReference ref : refs) {
						if (obj.eGet(ref) != null) {
							result++;
						}
					}
					return result;
				}
			});
			investigators.add(new IterateOverAll("references-count-no-empty") {
				@Override
				public int get(EObject obj) {
					int result = 0;
					List<EReference> refs = obj.eClass().getEAllReferences();
					for (EReference ref : refs) {
						if (obj.eGet(ref) != null) {
							if (!(ref.isMany() && ((List<?>) obj.eGet(ref)).size() == 0)) {
								result++;
							}
						}
					}
					return result;
				}
			});
			investigators.add(new IterateOverAll("references-sum") {
				@Override
				public int get(EObject obj) {
					int result = 0;
					List<EReference> refs = obj.eClass().getEAllReferences();
					for (EReference ref : refs) {
						if (obj.eGet(ref) != null) {
							if (obj.eGet(ref) instanceof List<?>)
								result += ((List<?>) obj.eGet(ref)).size();
							else
								result ++;
						}
					}
					return result;
				}
			});
			investigators.add(new IterateOverAll("containments-count") {
				@Override
				public int get(EObject obj) {
					int result = 0;
					List<EReference> refs = obj.eClass().getEAllContainments();
					for (EReference ref : refs) {
						if (obj.eGet(ref) != null) {
							result++;
						}
					}
					return result;
				}
			});
			investigators.add(new IterateOverAll("containments-count-no-empty") {
				@Override
				public int get(EObject obj) {
					int result = 0;
					List<EReference> refs = obj.eClass().getEAllContainments();
					for (EReference ref : refs) {
						if (obj.eGet(ref) != null) {
							if (!(ref.isMany() && ((List<?>) obj.eGet(ref)).size() == 0)) {
								result++;
							}
						}
					}
					return result;
				}
			});
			investigators.add(new IterateOverAll("containments-sum") {
				@Override
				public int get(EObject obj) {
					int result = 0;
					List<EReference> refs = obj.eClass().getEAllContainments();
					for (EReference ref : refs) {
						if (obj.eGet(ref) != null) {
							if (obj.eGet(ref) instanceof List<?>)
								result += ((List<?>) obj.eGet(ref)).size();
							else
								result ++;
						}
					}
					return result;
				}
			});
			investigators.add(new IterateOverAll("distinct-types") {				
				private Set<EClass> types = new HashSet<EClass>();
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the distinct types
					return types.size();
				}

				@Override
				public int get(EObject obj) {
					EClass type = obj.eClass();
					if (!types.contains(type)) {
						types.add(type);
					}
					return 0;	// ignored
				}
			});
			investigators.add(new IterateOverAll("supertype-count") {				
				@Override
				public int get(EObject obj) {
					return obj.eClass().getEAllSuperTypes().size();
				}
			});
			investigators.add(new IterateOverAll("distinct-supertypes") {				
				private Set<EClass> types = new HashSet<EClass>();
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the distinct types
					return types.size();
				}

				@Override
				public int get(EObject obj) {
					if (!types.contains(obj.eClass())) {
						types.add(obj.eClass());
					}
					List<EClass> supertypes = obj.eClass().getEAllSuperTypes();
					for (EClass type : supertypes) {
						if (!types.contains(type)) {
							types.add(type);
						}
					}
					return 0;	// ignored
				}
			});
			investigators.add(new IterateOverAll("distinct-attribute-values") {				
				private Set<Object> values = new HashSet<Object>();
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the distinct types
					return values.size();
				}

				@Override
				public int get(EObject obj) {
					List<EAttribute> attributes = obj.eClass().getEAllAttributes();
					for (EAttribute attr : attributes) {
						Object r = obj.eGet(attr);
						if (r != null && !values.contains(obj)) {
							values.add(r);
						}
					}
					return 0;	// ignored
				}
			});
			investigators.add(new IterateOverAll("distinct-references") {				
				private Set<Object> values = new HashSet<Object>();
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the distinct types
					return values.size();
				}

				@Override
				public int get(EObject obj) {
					List<EReference> refs = obj.eClass().getEAllReferences();
					for (EReference ref : refs) {
						Object r = obj.eGet(ref);
						if (r == null)
							continue;
						
						if (!(r instanceof List<?>)) {
							// turn single value into singleton list
							r = Collections.singletonList(r);
						}
						List<?> rl = (List<?>) r;
						for (Object o : rl) {
							if (!values.contains(o)) {
								values.add(o);
							}
						}
					}
					return 0;	// ignored
				}
			});
			investigators.add(new IterateOverAll("distinct-containments") {				
				private Set<Object> values = new HashSet<Object>();
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the distinct types
					return values.size();
				}

				@Override
				public int get(EObject obj) {
					List<EReference> refs = obj.eClass().getEAllContainments();
					for (EReference ref : refs) {
						Object r = obj.eGet(ref);
						if (r == null)
							continue;
						
						if (!(r instanceof List<?>)) {
							// turn single value into singleton list
							r = Collections.singletonList(r);
						}
						List<?> rl = (List<?>) r;
						for (Object o : rl) {
							if (!values.contains(o)) {
								values.add(o);
							}
						}
					}
					return 0;	// ignored
				}
			});
			investigators.add(new IterateOverAll("max-node-attributes") {
				private int max = -1;
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the maximum
					return max;
				}

				@Override
				public int get(EObject obj) {
					List<EAttribute> attributes = obj.eClass().getEAllAttributes();
					int thisValue = 0;
					for (EAttribute attr : attributes) {
						Object r = obj.eGet(attr);
						if (r != null)
							thisValue++;
					}
					if (thisValue > max)
						max = thisValue;	// set max
					return 0;	// ignored
				}
			});
			investigators.add(new IterateOverAll("max-node-references") {
				private int max = -1;
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the maximum
					return max;
				}

				@Override
				public int get(EObject obj) {
					List<EReference> refs = obj.eClass().getEAllReferences();
					int thisValue = 0;
					for (EReference ref : refs) {
						Object r = obj.eGet(ref);
						if (r == null)
							continue;
						
						if (!(r instanceof List<?>)) {
							r = Collections.singletonList(r);
						}
						
						thisValue += ((List<?>) r).size();
					}
					if (thisValue > max)
						max = thisValue;	// set max
					return 0;	// ignored
				}
			});
			investigators.add(new IterateOverAll("max-node-containments") {
				private int max = -1;
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the maximum
					return max;
				}

				@Override
				public int get(EObject obj) {
					List<EReference> refs = obj.eClass().getEAllContainments();
					int thisValue = 0;
					for (EReference ref : refs) {
						Object r = obj.eGet(ref);
						if (r == null)
							continue;
						
						if (!(r instanceof List<?>)) {
							r = Collections.singletonList(r);
						}
						
						thisValue += ((List<?>) r).size();
					}
					if (thisValue > max)
						max = thisValue;	// set max
					return 0;	// ignored
				}
			});
			investigators.add(new IterateOverAll("max-inheritance-height") {
				private int max = -1;
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the maximum
					return max;
				}

				@Override
				public int get(EObject obj) {
					int thisValue = obj.eClass().getEAllSuperTypes().size();
					if (thisValue > max)
						max = thisValue;	// set max
					return 0;	// ignored
				}
			});
			investigators.add(new IterateOverAll("radius") {
				private int min = -1;
				
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
							return nodes;
						}

						@Override
						public List<EObject> getNeighbours(EObject u) {
							// edges = all EReferences (including containments)
							List<EObject> neighbours = new ArrayList<EObject>();
							for (EReference ref : u.eClass().getEAllReferences()) {
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
					for (EObject source : dj.getEdges()) {
						for (EObject target : dj.getEdges()) {
							// ignoring self
							if (!source.equals(target)) {
								int path = dj.dijkstra(source, target);
								// ignore paths that cannot be found
								if (path != -1) {
									if (path < min)
										min = path;
								}
							}
						}
					}
					
					return 0;	// ignore return value
				}
				
			});
			investigators.add(new IterateOverAll("diameter") {
				private int max = -1;
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the maximum
					return max;
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
							return nodes;
						}

						@Override
						public List<EObject> getNeighbours(EObject u) {
							// edges = all EReferences (including containments)
							List<EObject> neighbours = new ArrayList<EObject>();
							for (EReference ref : u.eClass().getEAllReferences()) {
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
					for (EObject source : dj.getEdges()) {
						for (EObject target : dj.getEdges()) {
							// ignoring self
							if (!source.equals(target)) {
								int path = dj.dijkstra(source, target);
								if (path > max)
									max = path;
							}
						}
					}
					
					return 0;	// ignore return value
				}
				
			});
			investigators.add(new IterateOverAll("radius-without-generated") {
				private int min = -1;
				
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
							return nodes;
						}

						@Override
						public List<EObject> getNeighbours(EObject u) {
							// edges = all EReferences (including containments)
							List<EObject> neighbours = new ArrayList<EObject>();
							for (EReference ref : u.eClass().getEAllReferences()) {
								// ignore generated references
								if (isGeneratedReference(ref))
									continue;
								
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
					for (EObject source : dj.getEdges()) {
						for (EObject target : dj.getEdges()) {
							// ignoring self
							if (!source.equals(target)) {
								int path = dj.dijkstra(source, target);
								// ignore paths that cannot be found
								if (path != -1) {
									if (path < min)
										min = path;
								}
							}
						}
					}
					
					return 0;	// ignore return value
				}
				
			});
			investigators.add(new IterateOverAll("diameter-without-generated") {
				private int max = -1;
				
				@Override
				public Object evaluate(EObject root) {
					// evaluate as normal
					super.evaluate(root);
					// but return the maximum
					return max;
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
							return nodes;
						}

						@Override
						public List<EObject> getNeighbours(EObject u) {
							// edges = all EReferences (including containments)
							List<EObject> neighbours = new ArrayList<EObject>();
							for (EReference ref : u.eClass().getEAllReferences()) {
								// ignore generated references
								if (isGeneratedReference(ref))
									continue;
								
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
					for (EObject source : dj.getEdges()) {
						for (EObject target : dj.getEdges()) {
							// ignoring self
							if (!source.equals(target)) {
								int path = dj.dijkstra(source, target);
								if (path > max)
									max = path;
							}
						}
					}
					
					return 0;	// ignore return value
				}
				
			});
			
		}
		return investigators;
	}
	
	/**
	 * Is the given reference a 'generated' reference?
	 * 
	 * @param ref
	 * @return
	 */
	protected boolean isGeneratedReference(EReference ref) {
		return ref.equals(ModelPackage.eINSTANCE.getGeneratedElement_GeneratedBy()) ||
			ref.equals(ModelPackage.eINSTANCE.getGeneratesElements_GeneratedElements());
	}
	
	
	/**
	 * Helper method: turn an iterator of objects into a collection of objects.
	 * 
	 * @param it
	 * @return
	 */
	private Collection<EObject> toCollection(
			TreeIterator<EObject> it) {
		Collection<EObject> result = new ArrayList<EObject>();
		while (it.hasNext())
			result.add(it.next());
		return result;
	}
	
	/**
	 * Helper method: get the size of all elements in the iterator
	 * 
	 * @param eAllContents
	 * @return 
	 */
	protected int getSize(TreeIterator<EObject> it) {
		return toCollection(it).size();
	}

	/**
	 * Return a list of all the property names that we will investigate.
	 * 
	 * @return
	 */
	public List<String> getModelProperties() {
		List<String> result = new ArrayList<String>();
		for (IPropertyInvestigator p : getInvestigators()) {
			result.add(p.getName());
		}
		return result;
	}
	
	/**
	 * Investigate the given EObject for all of the model
	 * properties. 
	 * 
	 * @param root
	 * @return
	 */
	public List<Object> investigate(EObject root) {
		List<Object> result = new ArrayList<Object>();
		for (IPropertyInvestigator p : getInvestigators()) {
			result.add(p.evaluate(root));
		}
		return result;
	}
	
}
