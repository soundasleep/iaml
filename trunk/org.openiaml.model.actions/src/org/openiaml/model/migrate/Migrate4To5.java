package org.openiaml.model.migrate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <p>Migrate model version 0.4 to version 0.5.
 *
 * <p>In the future most of this functionality should be refactored into an abstract superclass.
 *
 * <p>Changes in model version 0.4.2:
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
 *   	<li>Page is now Frame
 *   </ol></li>
 *   <li><strong>Many other modifications, too many to list... this migrator only implements
 *   	those changed necessary for the model migration test to pass.</strong></li>
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
		
		if (xsiType.equals("iaml.visual:Page")) {
			return "iaml.visual:Frame";
		}

		return super.replaceType(element, xsiType, errors);
	}

	@Override
	public String getRenamedNode(String nodeName, Element element,
			List<ExpectedMigrationException> errors) {
		
		String xsiType = element.getAttribute("xsi:type");

		// <sessions> 
		// --> <scopes xsi:type="iaml.scopes:Session">
		if (nodeName.equals("sessions")) {
			return "scopes";
		}
		
		// <children xsi:type="iaml.visual:Page"> 
		// --> <scopes xsi:type="iaml.visual:Page">
		if (nodeName.equals("children") && "iaml.visual:Page".equals(xsiType)) {
			return "scopes";
		}
		
		// <children xsi:type="iaml.visual:Page"> or <sessions>
		//    <children xsi:type="iaml.components:LoginHandler">
		// -> <elements xsi:type="iaml.components:LoginHandler">
		if (element.getParentNode() != null && element.getParentNode() instanceof Element) {
			Element parent = (Element) element.getParentNode();
			if ((nodeName.equals("children") 
					&& parent.getNodeName().equals("children")
					&& "iaml.visual:Page".equals(parent.getAttribute("xsi:type")))
				|| (nodeName.equals("children") 
					&& parent.getNodeName().equals("sessions"))) {
				
				if ("iaml.components:LoginHandler".equals(xsiType))
					return "elements";
				if ("iaml.components:AccessControlHandler".equals(xsiType))
					return "elements";
				if ("iaml:DomainObject".equals(xsiType))
					return "elements";
				if ("iaml:DomainObjectInstance".equals(xsiType))
					return "elements";
				if ("iaml:DomainAttribute".equals(xsiType))
					return "elements";
				if ("iaml:DomainAttributeInstance".equals(xsiType))
					return "elements";
				if ("iaml:UserInstance".equals(xsiType))
					return "elements";
				
			}
		}
		
		return super.getRenamedNode(nodeName, element, errors);
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#handleElement(org.w3c.dom.Element, org.w3c.dom.Element, java.util.List)
	 */
	@Override
	public void handleElement(Element old, Element element,
			List<ExpectedMigrationException> errors) {

		// <sessions> 
		// --> <scope xsi:type="iaml.scopes:Session">
		if (old.getNodeName().equals("sessions")) {
			element.setAttribute("xsi:type", "iaml.scopes:Session");
		}
		
	}

	/**
	 * TODO Models of version 0.5 will have a different namespace,
	 * 	 but currently have the same as 0.4.
	 */
	@Override
	protected String getTargetNamespace() {
		return "http://openiaml.org/model0.4";
	}

	@Override
	public Map<String, String> getNewNamespaces() {
		Map<String, String> r = new HashMap<String, String>();
		r.put("iaml.scopes", "http://openiaml.org/model/scopes");
		return r;
	}
	
	
	
}