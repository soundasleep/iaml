package org.openiaml.model.diagram.custom.migrate;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Migrate model version 0.1 to version 0.2
 *
 * In the future most of this functionality should be refactored into an abstract superclass.
 *
 * Changes in model version 0.2:
 * 
 * <ol>
 *   <li>New wires:
 *   <ol>
 *     <li>FilterWire</li>
 *     <li>FindWire</li>
 *     <li>ShowWire</li>
 *     <li>NavigateWire</li>
 *     <li>CommitWire</li>
 *     <li>ConditionWire</li>
 *   </ol></li>
 *   <li>New visual elements:
 *   <ol>
 *     <li>Button</li>
 *     <li>Frame</li>
 *   </ol></li>
 *   <li>New domain elements:
 *   <ol>
 *     <li>abstract AbstractDomainStore</li>
 *     <li>abstract AbstractDomainObject</li>
 *     <li>abstract AbstractDomainAttribute</li>
 *     <li>type FileReference</li>
 *     <li>FileDomainStore</li>
 *   </ol></li>
 *   <li>Domain element changes: (requires migration)
 *   <ol>
 *     <li>DomainStore now extends AbstractDomainStore</li>
 *     <li>DomainObject now extends AbstractDomainObject</li>
 *     <li>DomainAttribute now extends AbstractDomainAttribute</li>
 *   </ol></li>
 *   <li>New elements:
 *   <ol>
 *     <li>abstract Scope</li>
 *     <li>Session</li>
 *     <li>PageRequest</li>
 *     <li>UserAgent</li>
 *     <li>LoginHandler</li>
 *   </ol></li>
 *   <li>
 *   <ol>Renamed elements: (requires migration)
 *     <li>StopNode --> CancelNode</li>
 *   </ol></li>
 * </ol>
 *
 * @see #isVersion(Document) for what defines this model
 * @see #recurseOverDocument(Element, Node, Document) for actual model changes
 * @author jmwright
 *
 */
public class Migrate1To2 extends DomBasedMigrator implements IamlModelMigrator {
	
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
		try {
			// get parameters
			String nsPackage = doc.getDocumentElement().getAttribute("xmlns:iaml");
			String rootId = doc.getDocumentElement().getAttribute("id");

			if (nsPackage.equals("http://openiaml.org/model") && 
					!rootId.isEmpty()) {
				// this is us! (version 0.1) 
				return true;
			}
		} catch (Exception e) {
			throw new MigrationException(e);
		}
			
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#getRenamedNode(java.lang.String, org.w3c.dom.Element, java.util.List)
	 */
	@Override
	public String getRenamedNode(String nodeName, Element element,
			List<ExpectedMigrationException> errors) {
		
		// does nothing
		return nodeName;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#handleAttribute(java.lang.String, java.lang.String, org.w3c.dom.Element, java.util.List)
	 */
	@Override
	public String handleAttribute(String name, String value, Element element,
			List<ExpectedMigrationException> errors) {

		// does nothing		
		return value;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#replaceType(org.w3c.dom.Element, java.lang.String, java.util.List)
	 */
	@Override
	public String replaceType(Element element, String xsiType,
			List<ExpectedMigrationException> errors) {

		// <nodes xsi:type="iaml.operations:StopNode"> 
		// --> <nodes xsi:type="iaml.operations:CancelNode">
		if (element.getNodeName().equals("nodes") && xsiType.equals("iaml.operations:StopNode")) {
			xsiType = "iaml.operations:CancelNode";
		}

		return xsiType;
		
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#shouldDeleteNode(org.w3c.dom.Element, org.w3c.dom.Node, java.util.List)
	 */
	@Override
	public boolean shouldDeleteNode(Element element, Node parent,
			List<ExpectedMigrationException> errors) {

		// does nothing		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#handleElement(org.w3c.dom.Element, org.w3c.dom.Element, java.util.List)
	 */
	@Override
	public void handleElement(Element old, Element element,
			List<ExpectedMigrationException> errors) {
		
		Element parentNode = (old.getParentNode() != null && old.getParentNode() instanceof Element) ?
				(Element) old.getParentNode() : null;

		// <domainStores> --> <domainStores xsi:type="iaml:DomainStore">
		if (old.getNodeName().equals("domainStores") && old.getAttribute("xsi:type").isEmpty()) {
			element.setAttribute("xsi:type", "iaml:DomainStore");
		}

		// <children> --> <children xsi:type="iaml:DomainObject">
		if (old.getNodeName().equals("children") && old.getAttribute("xsi:type").isEmpty()
				&& parentNode != null && parentNode.getNodeName().equals("domainStores")) {
			element.setAttribute("xsi:type", "iaml:DomainObject");
		}

		// <attributes> --> <attributes xsi:type="iaml:DomainAttribute">
		if (old.getNodeName().equals("attributes") && old.getAttribute("xsi:type").isEmpty()) {
			element.setAttribute("xsi:type", "iaml:DomainAttribute");
		}

	}
	
}