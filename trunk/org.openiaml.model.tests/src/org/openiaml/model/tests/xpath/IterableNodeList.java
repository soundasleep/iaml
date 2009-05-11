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
 * Wraps {@link org.w3c.dom.NodeList} with the Iterable interface.
 * 
 * @author jmwright
 *
 */
public class IterableNodeList implements NodeList, Iterable<Element> {
	private List<Element> elements;

	public IterableNodeList(NodeList nl) {
		elements = new ArrayList<Element>();
		for (int i = 0; i < nl.getLength(); i++) {
			elements.add((Element) nl.item(i));
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
	public Node item(int index) {
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
	public void addAll(IterableNodeList list) {
		for (Element e : list) {
			elements.add(e);
		}
	}

}
