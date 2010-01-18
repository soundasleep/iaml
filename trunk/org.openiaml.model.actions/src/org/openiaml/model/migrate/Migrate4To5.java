package org.openiaml.model.migrate;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Migrate model version 0.4 to version 0.5; <strong>not yet implemented</strong>
 *
 * In the future most of this functionality should be refactored into an abstract superclass.
 *
 * Changes in model version 0.4.2:
 * 
 * <ol>
 *   <li>Removed elements: (requires migration)
 *   <ol>
 *     <li>DerivedView</li>
 *     <li>PageRequest</li>
 *     <li>VisitorAgent</li>
 *     <li>Frame</li>
 *     <li>ShowWire</li>
 *     <li>CommitWire</li>
 *   </ol></li>
 *   <li>Changed elements:
 *   <ol>
 *   	<li>WireEdge is now abstract
 *   	<li>SingleWire is now abstract
 *   </ol></li>
 * </ol>
 * 
 * @see #isVersion(Document) for what defines this model
 * @see #recurseOverDocument(Element, Node, Document) for actual model changes
 * @author jmwright
 *
 */
public class Migrate4To5 extends DomBasedMigrator implements IamlModelMigrator {
	
	public String getName() {
		return "Migrator 0.4 to 0.5";
	}
	
	/**
	 * We can identify a version 0.4 model:
	 * 
	 *  <ol>
	 *   <li>The NS package is http://openiaml.org/model0.4</li>
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
			String nsPackage2 = doc.getDocumentElement().getNamespaceURI();
			if (nsPackage2 != null)
				nsPackage = nsPackage2;
			String rootId = doc.getDocumentElement().getAttribute("id");

			if (nsPackage.equals("http://openiaml.org/model0.4") && 
					!rootId.isEmpty()) {
				// this is us! (version 0.2) 
				return true;
			}
		} catch (Exception e) {
			throw new MigrationException(e);
		}
			
		return false;
	}

	@Override
	public boolean shouldDeleteAttribute(Element element, Element target,
			String name, String value, List<ExpectedMigrationException> errors) {

		// if the type is FileDomain*, return null, so the attribute
		// is not added
		if (name.equals("xsi:type") && (value.equals("iaml.domain:FileDomainStore") ||
				value.equals("iaml.domain:FileDomainObject") || 
				value.equals("iaml.domain:FileDomainAttribute"))) {
			// delete
			return true;
		}
		
		return super.shouldDeleteAttribute(element, target, name, value, errors);
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#handleElement(org.w3c.dom.Element, org.w3c.dom.Element, java.util.List)
	 */
	@Override
	public void handleElement(Element old, Element element,
			List<ExpectedMigrationException> errors) {

		throw new UnsupportedOperationException("Not yet implemented");

	}

	/**
	 * Models of version 0.5 have a different namespace.
	 */
	@Override
	protected Element createElement(Document document, String nodeName) {
		return document.createElementNS("http://openiaml.org/model0.5", nodeName);
	}
	
}