/**
 * 
 */
package org.openiaml.model.tests.xpath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Wraps {@link org.w3c.dom.NodeList} with the Iterable interface, 
 * which only selects {@link org.w3c.dom.Element}s. Any Nodes
 * within the NodeList that are not Elements are silently ignored.
 * 
 * @author jmwright
 *
 */
public class IterableElementList implements NodeList, Iterable<Element> {
	private List<Element> elements;

	/**
	 * Takes all {@link org.w3c.dom.Element}s within the NodeList and adds them to the current
	 * list. Silently ignores all Nodes (e.g. Text) that are not 
	 * Elements.
	 * 
	 * @see org.w3c.dom.Element
	 * @param nl
	 */
	public IterableElementList(NodeList nl) {
		elements = new ArrayList<Element>();
		for (int i = 0; i < nl.getLength(); i++) {
			// ignore elements that are not Element
			if (nl.item(i) instanceof Element) {
				elements.add((Element) nl.item(i));
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Element> iterator() {
		return elements.iterator();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.NodeList#getLength()
	 */
	@Override
	public int getLength() {
		return elements.size();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.NodeList#item(int)
	 */
	@Override
	public Element item(int index) {
		return elements.get(index);
	}
	
	/**
	 * Is this NodeList empty? (Additional method.)
	 * 
	 * @return true if this list is empty
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/**
	 * Add all of the elements in the given list to this list.
	 * 
	 * @param list
	 */
	public void addAll(IterableElementList list) {
		for (Element e : list) {
			elements.add(e);
		}
	}
	
	/**
	 * Print out the nodelist contents as an easy-to-read string.
	 * Doesn't print out more than 30 elements.
	 */
	public String toString() {
		int count = 0;
		String result = "[";
		for (Element e : this) {
			if (count != 0)
				result += ", ";
			result += e;
			count++;
			if (count > 30) {
				result += ", ...";
				break;
			}
		}
		result += "]";
		return result;
	}

	/**
	 * Get the size of the list.
	 * 
	 * @return
	 */
	public int size() {
		return getLength();
	}

}
