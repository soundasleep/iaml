package org.openiaml.model.migrate;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <p>Migrate model version 0.5 to 0.6.
 *
 * <p>In the future most of this functionality should be refactored into an abstract superclass.
 *
 * <p>Changes in model version 0.6:
 * 
 * <ol>
 *   <li>'fieldValue' Property is now a directly referenced Property
 *   <li>Others...
 * </ol>
 * 
 * @see #isVersion(Document) for what defines this model
 * @see #recurseOverDocument(Element, Node, Document) for actual model changes
 * @author jmwright
 *
 */
public class Migrate5To6 extends DomBasedMigrator implements IamlModelMigrator {
	
	public String getName() {
		return "Migrator 0.5 to 0.6";
	}
	
	/**
	 * We can identify a version 0.5 model:
	 * 
	 *  <ol>
	 *   <li>The NS package is http://openiaml.org/model0.5</li>
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

			if (nsPackage.equals("http://openiaml.org/model0.5") && 
					!rootId.isEmpty()) {
				// this is us! (version 0.4) 
				return true;
			}
		} catch (Exception e) {
			throw new MigrationException(e);
		}
			
		return false;
	}

	@Override
	public String replaceType(Element element, String xsiType,
			List<ExpectedMigrationException> errors) {
		return super.replaceType(element, xsiType, errors);
	}

	@Override
	public String getRenamedNode(String nodeName, Element element,
			List<ExpectedMigrationException> errors) {
		
		String xsiType = element.getAttribute("xsi:type");
		
		// <properties name="fieldValue">
		// --> <fieldValue name="fieldValue">
		if (nodeName.equals("properties") && "fieldValue".equals(element.getAttribute("name"))) {
			return "fieldValue";
		}

		return super.getRenamedNode(nodeName, element, errors);
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#handleElement(org.w3c.dom.Element, org.w3c.dom.Element, java.util.List)
	 */
	@Override
	public void handleElement(Element old, Element element,
			List<ExpectedMigrationException> errors) {

		// empty
		
	}
	
	@Override
	public boolean shouldDeleteAttribute(Element element, Element target,
			String name, String value, List<ExpectedMigrationException> errors) {
		
		return super.shouldDeleteAttribute(element, target, name, value, errors);
	}
	
	@Override
	public String getRenamedAttribute(Element oldElement, Element newElement,
			String name, String value) {

		// otherwise, just return the same name
		return super.getRenamedAttribute(oldElement, newElement, name, value);
	}

	/**
	 * TODO Models of version 0.6 will have a different namespace,
	 * <code>http://openiaml.org/model0.6</code>.
	 */
	@Override
	protected String getTargetNamespace() {
		return "http://openiaml.org/model0.5";
	}
	
}