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
 * Migrate model version 0.1 to version 0.2
 *
 * In the future most of this functionality may be refactored into an abstract superclass.
 *
 * Changes in model version 0.2:
 * 
 * <ol>
 *   <li>New wires:
 *     <ol>FilterWire</ol>
 *     <ol>FindWire</ol>
 *     <ol>ShowWire</ol>
 *     <ol>NavigateWire</ol>
 *     <ol>CommitWire</ol>
 *     <ol>ConditionWire</ol>
 *   </li>
 *   <li>New visual emenets:
 *     <ol>Button</ol>
 *     <ol>Frame</ol>
 *   </li>
 *   <li>New elements:
 *     <ol>abstract Scope</ol>
 *     <ol>Session</ol>
 *     <ol>PageRequest</ol>
 *     <ol>UserAgent</ol>
 *   </li>
 * </ol>
 *
 * @see #isVersion(Document) for what defines this model
 * @see #recurseOverDocument(Element, Node, Document) for actual model changes
 * @author jmwright
 *
 */
public class Migrate1To2 implements IamlModelMigrator {

	public String getName() {
		return "Migrator 0.1 to 0.2";
	}
	
	/**
	 * We can identify a version 0.1 model:
	 * 
	 *  <ol>
	 *   <li>The NS package is http://openiaml.org/model</li>
	 *   <li>The root element has an attribute "id"</li>
	 *  </ol>
	 *
	 * 
	 * @see org.openiaml.model.diagram.custom.migrate.IamlModelMigrator#isVersion(org.eclipse.core.resources.IFile)
	 */
	@Override
	public boolean isVersion(Document doc) throws MigrationException {
		// we will always ignore this version for now, until 
		// we actually implement the migration action
		if (true)
			return false;
		
		try {
			// get parameters
			String nsPackage = doc.getDocumentElement().getAttribute("xmlns:iaml");
			String rootId = doc.getDocumentElement().getAttribute("id");

			if (nsPackage.equals("http://openiaml.org/model") && 
					!rootId.isEmpty()) {
				// this is us!
				return true;
			}
		} catch (Exception e) {
			throw new MigrationException(e);
		}
			
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.MigrateModelAction.IamlModelMigrator#migrate(org.eclipse.core.resources.IFile, org.eclipse.core.resources.IFile, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Document migrate(Document inputDoc, IProgressMonitor monitor, List<ExpectedMigrationException> errors)
			throws MigrationException {
		
		// TODO implement migrate code from 0.1 to 0.2
		
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
		String nodeName = element.getNodeName();
		
		// <edges> --> <wires>
		if (element.getNodeName().equals("edges")) {
			nodeName = "wires";
		}
		Element e = document.createElement( nodeName );
		
		// an <operation> can no longer contain <wires>
		if (element.getNodeName().equals("edges") && output.getNodeName().equals("operations")) {
			errors.add( new ExpectedMigrationException( this, element, "<operation> can no longer contain <edges>" ) );
			addDeletedReference(element);
			return;
		}
		
		// cycle over attributes
		for (int i = 0; i < element.getAttributes().getLength(); i++) {
			Node n = element.getAttributes().item(i);
			String value = n.getNodeValue();
			
			// should we replace IDs?
			if (value.indexOf("//@") >= 0) {
				value = replaceReferences(value);
			}
			
			e.setAttribute( n.getNodeName(), value );
		}
		
		// does it have an id?
		if (element.getAttribute("id").isEmpty()) {
			String oldId = calculateOldId(element);
			String newId = getMigratedId(oldId);
			idMap.put(oldId, newId);
			e.setAttribute("id", newId);
		}
		
		// should we replace the xsi:type?
		if (!element.getAttribute("xsi:type").isEmpty()) {
			// RunWire --> RunInstanceWire
			String xsiType = element.getAttribute("xsi:type");
			if (xsiType.equals("iaml.wires:RunWire")) {
				xsiType = "iaml.wires:RunInstanceWire";
			}

			// ProvidedParameterWire --> ParameterWire
			if (xsiType.equals("iaml.wires:ProvidedParameterWire")) {
				xsiType = "iaml.wires:ParameterWire";
			}

			// PropertyToExecutionWire has been removed
			if (xsiType.equals("iaml.wires:PropertyToExecutionWire")) {
				errors.add( new ExpectedMigrationException( this, element, "Type PropertyToExecutionWire no longer exists" ) );
				addDeletedReference(element);
				return;
			}
			
			// we can no longer have operations that are StartNodes:
			// replace with a normal operation
			if (element.getNodeName().equals("operations")) {
				if (xsiType.equals("iaml.operations:StartNode") ||
						xsiType.equals("iaml.operations:StopNode") ||
						xsiType.equals("iaml.operations:FinishNode")) {
					xsiType = "iaml:ChainedOperation";
					// we can't add an attribute that isn't in the EMF model
					// e.setAttribute("migratedWarning", "operations can no longer be StartNode, StopNode or FinishNode");
					errors.add( new ExpectedMigrationException( this, element, "Operations can no longer be of type StartNode, StopNode or FinishNode; operation changed to ChainedOperation" ) );
				}
			}

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
	 * Replace "//@foo.1/@bar.2 //@car.3" with "id1 id2"
	 * 
	 * @param value
	 * @return
	 */
	private String replaceReferences(String value) {
		// multiple references in here?
		String[] references = value.split(" ");
		String newValue = "";
		for (String ref : references) {
			newValue += getMigratedId(ref) + " ";
		}
		return newValue.trim();
	}

	/**
	 * Calculate what would be the old ID for this element.
	 * 
	 * @param element
	 * @return
	 */
	private String calculateOldId(Element element) {
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
	private String getMigratedId(String oldId) {
		if (idMap.containsKey(oldId)) {
			return idMap.get(oldId);
		}
		String newId = "migrated" + idCounter++;
		idMap.put(oldId, newId);
		return newId;
	}

	/**
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
	
}