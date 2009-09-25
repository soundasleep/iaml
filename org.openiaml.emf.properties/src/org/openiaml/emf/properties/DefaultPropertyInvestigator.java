/**
 * 
 */
package org.openiaml.emf.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;


/**
 * Default abstract implementation.
 * 
 * @author jmwright
 *
 */
public abstract class DefaultPropertyInvestigator implements IPropertyInvestigator, IEMFElementSelector {

	private String name;
	private IEMFElementSelector selector;
	
	public DefaultPropertyInvestigator(String name, IEMFElementSelector selector) {
		this.name = name;
		this.selector = selector;
	}

	public String getName() {
		return name;
	}
	
	public IEMFElementSelector getSelector() {
		return selector;
	}

	/**
	 * Helper method: turn an iterator of objects into a collection of objects.
	 * 
	 * @param it
	 * @return
	 */
	protected Collection<EObject> toCollection(TreeIterator<EObject> it) {
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
	
	public boolean ignoreAttribute(EAttribute ref) {
		return getSelector().ignoreAttribute(ref);
	}

	public boolean ignoreClass(EClass ref) {
		return getSelector().ignoreClass(ref);
	}

	public boolean ignoreReference(EReference ref) {
		return getSelector().ignoreReference(ref);
	}

	/**
	 * Remove all ignored classes in {@link #ignoreClass(org.eclipse.emf.ecore.EClass)}
	 * from the given list. 
	 * 
	 * @param nodes
	 * @return
	 */
	protected Collection<EObject> removeIgnoredClasses(Collection<EObject> nodes) {
		Collection<EObject> result = new ArrayList<EObject>(nodes.size());
		Iterator<EObject> it = nodes.iterator();
		while (it.hasNext()) {
			EObject n = it.next();
			if (ignoreClass(n.eClass()))
				continue;	// ignore
			
			result.add(n);
		}
		return result;
	}
	
}
