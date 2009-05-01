/**
 * 
 */
package org.openiaml.model.diagram.custom.migrate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.runtime.IProgressMonitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * An XML DOM-based migrator. This doesn't do anything
 * fancy like loading up the old model in memory and traversing it;
 * instead, it modifies the elements themselves.
 * 
 * The concrete subclasses of this abstract class specify the logic
 * involved.
 * 
 * @author jmwright
 *
 */
public abstract class DomBasedMigrator implements IamlModelMigrator {


	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.MigrateModelAction.IamlModelMigrator#migrate(org.eclipse.core.resources.IFile, org.eclipse.core.resources.IFile, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Document migrate(Document inputDoc, IProgressMonitor monitor, List<ExpectedMigrationException> errors)
			throws MigrationException {
		try {
			// create the new document (output)
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document newDoc = db.newDocument();
			
			// cycle through each element and add "id" attributes
			// and map their "id" attribute to their original position
			idMap = new HashMap<String,String>();
			recurseOverDocument(inputDoc, newDoc, errors);
			
			// we now have our new model
			return newDoc;

		} catch (Exception e) {
			throw new MigrationException(e);
		}
	}

	/**
	 * @see #removeDeletedReferences(Element)
	 */
	protected List<String> deletedIds;
	
	/**
	 * Do the model migration, and then remove referenced
	 * element attributes that have been deleted during the
	 * migration process.
	 * 
	 * @see #recurseOverDocument(Element, Node, Document)
	 * @see #removeDeletedReferences(Element)
	 * @param input
	 * @param output
	 * @param errors The list of errors to insert errors into
	 */
	protected void recurseOverDocument(Document input, Document output, List<ExpectedMigrationException> errors) {
		// reset deleted list
		deletedIds = new ArrayList<String>();
		
		// recurse over the document, adding elements and translating
		// where necessary
		recurseOverDocument( input.getDocumentElement(), output, output, errors );
		
		// go over all deleted elements and remove them from all
		// referenced attributes
		removeDeletedReferences(output.getDocumentElement());
	}
	
	/**
	 * We need to be able to handle removed references. Because
	 * a reference may be deleted either at the start or end of the document,
	 * and before or after we have translated any references to it,
	 * we must remove deleted references <em>after</em> the translation
	 * process has been completed.
	 * 
	 * This may be quite slow.
	 * 
	 * @param element
	 */
	protected void removeDeletedReferences(Element element) {
		for (String id : deletedIds) {
			for (int i = 0; i < element.getAttributes().getLength(); i++) {
				String attrName = element.getAttributes().item(i).getNodeName();
				String attrValue = element.getAttributes().item(i).getNodeValue();
				
				// does this attribute have it?
				if (attrValue.equals(id)) {
					element.removeAttribute(attrName);
				} else if (attrValue.indexOf(id + " ") == 0) {
					// starts with it
					element.setAttribute(attrName, attrValue.substring( attrValue.indexOf(id + " " )));
				} else if (attrValue.indexOf(" " + id) > 0) {
					// ends with it
					element.setAttribute(attrName, attrValue.substring( 0, attrValue.indexOf(" " + id )));
				}
				
			}
		}
		
		// process over child nodes
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			Node n = element.getChildNodes().item(i);
			if (n instanceof Element) {
				removeDeletedReferences((Element) n);
			}
		}
	}
	
	/**
	 * Calculate the old and new IDs for this element, add the
	 * mapping (for future references), and add it as a deleted reference.
	 * 
	 * @param element
	 */
	protected void addDeletedReference(Element element) {
		String oldId = calculateOldId(element);
		String newId = getMigratedId(oldId);
		idMap.put(oldId, newId);
		deletedIds.add(newId);
	}

	/**
	 * For generating new migration IDs
	 */
	private int idCounter = 0;
	
	/**
	 * Here we recurse over a document, doing the actual migration.
	 * We can rename, remove or add nodes, and change attributes or
	 * types (xsi:type).
	 * 
	 * <b>NOTE:</b> This is where all the logic for migration is stored.
	 * 
	 * @param element
	 * @param output
	 * @param document
	 */
	protected void recurseOverDocument(Element element,
			Node output, Document document, List<ExpectedMigrationException> errors) {
		
		// should the element be deleted?
		if (shouldDeleteNode(element, output, errors)) {
			errors.add( new ExpectedMigrationException( this, element, "<operation> can no longer contain <edges>" ) );
			addDeletedReference(element);
			return;
		}

		String nodeName = element.getNodeName();
		
		// replace the node name?
		nodeName = getRenamedNode(nodeName, element, errors);

		Element e = createElement(document, nodeName);
		
		// do anything fancy with the element?
		handleElement(element, e, errors);

		// cycle over attributes
		for (int i = 0; i < element.getAttributes().getLength(); i++) {
			Node n = element.getAttributes().item(i);
			String name = n.getNodeName();
			String value = n.getNodeValue();
			
			// should we modify it somehow?
			value = handleAttribute(name, value, element, errors);
			if (value == null)
				return;
			
			e.setAttribute( name, value );
		}
		
		// does it have an id?
		// if not, create one
		if (element.getAttribute("id").isEmpty()) {
			String oldId = calculateOldId(element);
			String newId = getMigratedId(oldId);
			idMap.put(oldId, newId);
			e.setAttribute("id", newId);
		}
		
		// should we replace the xsi:type?
		if (!element.getAttribute("xsi:type").isEmpty()) {
			String xsiType = element.getAttribute("xsi:type");
			
			xsiType = replaceType(element, xsiType, errors);
			if (xsiType == null)
				return;
			
			e.setAttribute("xsi:type", xsiType);
		}
		
		// recurse over children
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			Node n = element.getChildNodes().item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				recurseOverDocument((Element) n, e, document, errors);
			} else {					
				// e.appendChild(document.adoptNode(n));	// this also adds the children of the node, which we don't want 
			}
		}
		
		output.appendChild(e);
	}
	
	/**
	 * Create a new element. By default, just creates an element
	 * with the default namespace.
	 * 
	 * @param nodeName
	 * @return
	 */
	protected Element createElement(Document document, String nodeName) {
		return document.createElement( nodeName );
	}

	/**
	 * Do anything special to this element. Doesn't have to do anything.
	 * 
	 * @param old The old element
	 * @param element The new element
	 * @param errors The list of errors to insert errors into
	 */
	public abstract void handleElement(Element old, Element element,
			List<ExpectedMigrationException> errors);

	/**
	 * Should we replace the xsi:type of this element?
	 * 
	 * @param element The original element
	 * @param xsiType The current xsi:type
	 * @param errors The list of errors to insert errors into
	 * @return The new xsi:type, or null if the element has been deleted
	 */
	public abstract String replaceType(Element element, String xsiType, List<ExpectedMigrationException> errors) ;

	/**
	 * Should we do something with this attribute?
	 * 
	 * @param name The attribute name
	 * @param value The old attribute value
	 * @param element The original element
	 * @param errors The list of errors to insert errors into
	 * @return The new attribute value
	 */
	public abstract String handleAttribute(String name, String value, Element element, List<ExpectedMigrationException> errors);

	/**
	 * Should this node be deleted? If it is deleted, a warning
	 * will be logged.
	 * 
	 * @param element The original element
	 * @param parent The destination parent node
	 * @param errors The list of errors to insert errors into
	 * @return True if it should be deleted; false otherwise.
	 */
	public abstract boolean shouldDeleteNode(Element element, Node parent, List<ExpectedMigrationException> errors);

	/**
	 * Should the node be renamed?
	 * 
	 * @param nodeName The element name (equal to element.getNodeName())
	 * @param element The original element
	 * @param errors The list of errors to insert errors into
	 * @return the new name of the node
	 */
	public abstract String getRenamedNode(String nodeName, Element element, List<ExpectedMigrationException> errors);

	/**
	 * Calculate what would be the old ID for this element.
	 * 
	 * @param element
	 * @return
	 */
	protected String calculateOldId(Element element) {
		Node n = element.getParentNode();
		if (n == null || n instanceof Document || !(n instanceof Element)) {
			return "/";		// root
		}
		return calculateOldId((Element) n) + "/@" + element.getNodeName() + "." + getNodeIndex(element);
	}
	
	/**
	 * Get the migrated ID for this element. In some cases the element
	 * will be referenced before the "id" attribute can be added;
	 * in others, the references will occur after the "id" attribute
	 * has been added.
	 * 
	 * @param oldId
	 * @return
	 */
	protected String getMigratedId(String oldId) {
		if (idMap.containsKey(oldId)) {
			return idMap.get(oldId);
		}
		String newId = "migrated" + idCounter++;
		idMap.put(oldId, newId);
		return newId;
	}

	/**
	 * Find the index of this node in its parent.
	 * 
	 * @throws RuntimeException if the node does not have a parent, or the node tree is inconsistent.
	 * @param element
	 * @return
	 */
	private int getNodeIndex(Element element) {
		Node n = element.getParentNode();
		int count = 0;
		for (int i = 0; i < n.getChildNodes().getLength(); i++) {
			Node nn = n.getChildNodes().item(i);
			if (nn.getNodeName().equals(element.getNodeName())) {
				if (nn.equals(element))
					return count;
				count++;
			}
		}
		// should never get this far
		throw new RuntimeException("Could not find frequency of node '" + element + "'");
	}

	/**
	 * A map of old IDs ("//@foo.0") to new IDs ("foo1")
	 */
	protected Map<String,String> idMap;

	/**
	 * Replace "//@foo.1/@bar.2 //@car.3" with "id1 id2"
	 * 
	 * @param value
	 * @return
	 */
	protected String replaceReferences(String value) {
		// multiple references in here?
		String[] references = value.split(" ");
		String newValue = "";
		for (String ref : references) {
			newValue += getMigratedId(ref) + " ";
		}
		return newValue.trim();
	}
	
}
