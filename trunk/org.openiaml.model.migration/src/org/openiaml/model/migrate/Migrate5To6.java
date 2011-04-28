package org.openiaml.model.migrate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <p>Migrate model version 0.5 to 0..
 *
 * <p>In the future most of this functionality should be refactored into an abstract superclass.
 *
 * <p>Changes in model version 0.6:
 * 
 * <ol>
 *   <li>'fieldValue' Property is now a directly referenced Property
 *   <li>DecisionOperation has been merged into DecisionNode ({@issue 160})
 *   <li>DecisionCondition has been merged into DecisionNode ({@issue 160})
 *   <li>"setPropertyToValue" PrimitiveOperation is now merged into "set" ({@issue 143})
 *   <li>InternetApplication.children is now merged into InternetApplication.elements (as it is now a Scope)
 *   <li>NavigateAction and RunAction merged into {@model ActionEdge}
 *   <li>Hidden is now a {@model Label}[visible=false] ({@issue 224})
 *   <li>StaticValue is merged into {@model Property}[readOnly=true] ({@issue 179})
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
		
		if (xsiType.equals("iaml.operations:DecisionOperation")) {
			return "iaml.operations:DecisionNode";
		}

		if (xsiType.equals("iaml.operations:DecisionCondition")) {
			return "iaml.operations:DecisionNode";
		}

		if (xsiType.equals("iaml.users:UserInstance") || xsiType.equals("iaml:DomainObjectInstance")) {
			return "iaml.domain:DomainIterator";
		}
		
		// NavigateAction, RunAction --> ActionEdge
		// (EMF reloading/resaving will remove the xsiType entirely)
		if (xsiType.equals("iaml.wires:NavigateAction") || xsiType.equals("iaml.wires:RunAction")) {
			return "iaml:ActionEdge";
		}

		// <children xsi:type="iaml.visual:Hidden">
		// --> <children xsi:type="iaml.visual:Label" visible="false">
		if (xsiType.equals("iaml.visual:Hidden")) {
			return "iaml.visual:Label";
		}
		
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
		
		// <values name="static">
		// --> <properties name="static" readOnly="true">
		if (nodeName.equals("values")) {
			return "properties";
		}
		
		// <conditions xsi:type="iaml.operations:DecisionCondition">
		// --> <nodes xsi:type="iaml.operations:DecisionNode">
		if (nodeName.equals("conditions") && "iaml.operations:DecisionCondition".equals(xsiType)) {
			return "nodes";
		}

		// <operations xsi:type="iaml.operations:DecisionOperation">
		// --> <nodes xsi:type="iaml.operations:DecisionNode">
		if (nodeName.equals("operations") && "iaml.operations:DecisionOperation".equals(xsiType)) {
			return "nodes";
		}
		
		// <InternetApplication><children>
		// --> <InternetApplication><elements>
		if (nodeName.equals("children") && element.getParentNode() != null && element.getParentNode() instanceof Element) {
			Element parent = (Element) element.getParentNode();
			if ("InternetApplication".equals(parent.getNodeName()) ||
					"iaml:InternetApplication".equals(parent.getNodeName())) {
				return "elements";
			}
		}
		
		// <attributes>
		// --> <featureInstances xsi:type="iaml.domain:DomainAttributeInstance">
		if ("attributes".equals(element.getNodeName())
				&& element.getParentNode() != null
				&& element.getParentNode() instanceof Element 
				&& "iaml.domain:DomainIterator".equals(getXsiType((Element) element.getParentNode()))) {
			return "featureInstances";
		}

		return super.getRenamedNode(nodeName, element, errors);
	}
	
	@Override
	public String getRenamedAttribute(Element oldElement, Element newElement,
			String name, String value) {

		// <values value="static">
		// --> <properties defaultValue="static" readOnly="true">
		if (("properties".equals(oldElement.getNodeName()) || "values".equals(oldElement.getNodeName())) &&
				"value".equals(name)) {
			return "defaultValue";
		}
		
		// otherwise, just return the same name
		return super.getRenamedAttribute(oldElement, newElement, name, value);
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.migrate.DomBasedMigrator#handleAttribute(java.lang.String, java.lang.String, org.w3c.dom.Element, java.util.List)
	 */
	@Override
	public String handleAttribute(String name, String value, Element element,
			List<ExpectedMigrationException> errors) {
		
		// "setPropertyToValue" -> "set"
		if ("operations".equals(element.getNodeName()) && "name".equals(name) && "setPropertyToValue".equals(value)) {
			return "set"; 
		}
		
		// is this a list of IDs?
		if (value.equals(value.trim())) {
			StringBuffer newValue = new StringBuffer();
			String[] values = value.split(" ");
			
			for (String v : values) {
				if (!idsToDelete.contains(v)) {
					if (newValue.length() != 0)
						newValue.append(' ');
					newValue.append(v);
				}
			}
			
			return newValue.toString();
		}
		
		// otherwise, just return the same value
		return super.handleAttribute(name, value, element, errors);
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.migrate.DomBasedMigrator#shouldDeleteNode(org.w3c.dom.Element, org.w3c.dom.Node, java.util.List)
	 */
	@Override
	public boolean shouldDeleteNode(Element element, Node parent,
			List<ExpectedMigrationException> errors) {

		String xsiType = element.getAttribute("xsi:type");
		if ("iaml:DomainObject".equals(xsiType)) {
			errors.add(new ExpectedMigrationException(this, element, "DomainObject has been removed, and needs to be reimplemented as a DomainSchema manually."));
			return true;
		}

		if ("iaml:DomainStore".equals(xsiType)) {
			errors.add(new ExpectedMigrationException(this, element, "DomainStore has been removed, and needs to be reimplemented as a DomainSource manually."));
			return true;
		}

		if ("iaml.wires:SelectWire".equals(xsiType)) {
			errors.add(new ExpectedMigrationException(this, element, "SelectWire has been removed, and needs to be reimplemented as a DomainIterator manually."));
			return true;
		}

		if ("iaml.wires:NewInstanceWire".equals(xsiType)) {
			errors.add(new ExpectedMigrationException(this, element, "NewInstanceWire has been removed, and needs to be reimplemented as a DomainIterator manually."));
			return true;
		}

		if (element.getNodeName().equals("domainStores")) {
			errors.add(new ExpectedMigrationException(this, element, "DomainStore has been removed, and needs to be reimplemented as a DomainSource manually."));
			return true;
		}
		
		// super call
		return super.shouldDeleteNode(element, parent, errors);
	}
	
	private List<String> idsToDelete;
	
	protected void findDeletedElements(Element e, Node parent, boolean isDeleting) {
		if (isDeleting || shouldDeleteNode(e, parent, new ArrayList<ExpectedMigrationException>())) {
			// add this ID to the list
			if (e.getAttribute("id") != null && !e.getAttribute("id").isEmpty()) {
				idsToDelete.add(e.getAttribute("id"));
				isDeleting = true;
			}
		}
		
		// for all children elements, call
		for (int i = 0; i < e.getChildNodes().getLength(); i++) {
			Node n = e.getChildNodes().item(i);
			if (n instanceof Element) {
				findDeletedElements((Element) n, e, isDeleting);
			}
		}
	}
	
	@Override
	public void prepareDocument(Element documentElement,
			List<ExpectedMigrationException> errors) {
		
		// find all IDs to delete
		idsToDelete = new ArrayList<String>();
		findDeletedElements(documentElement, null, false);

		// call super
		super.prepareDocument(documentElement, errors);
	}

	/**
	 * TODO Models of version 0.6 will have a different namespace,
	 * <code>http://openiaml.org/model0.6</code>.
	 */
	@Override
	protected String getTargetNamespace() {
		return "http://openiaml.org/model0.5";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.migrate.DomBasedMigrator#handleElement(org.w3c.dom.Element, org.w3c.dom.Element, java.util.List)
	 */
	@Override
	public void handleElement(Element old, Element element,
			List<ExpectedMigrationException> errors) {
		
		// <children xsi:type="iaml.visual:Hidden">
		// --> <children xsi:type="iaml.visual:Label" visible="false">
		if ("iaml.visual:Hidden".equals(getXsiType(old))) {
			element.setAttribute("visible", "false");
		}
		
		// <values name="static">
		// --> <properties name="static" readOnly="true">
		if ("values".equals(old.getNodeName())) {
			element.setAttribute("readOnly", "true");
		}
		
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.migrate.DomBasedMigrator#getNewNamespaces()
	 */
	@Override
	public Map<String, String> getNewNamespaces() {
		Map<String, String> map = super.getNewNamespaces();
		map.put("iaml.domain", "http://openiaml.org/model/domain");
		return map;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.migrate.DomBasedMigrator#shouldCopyTextContent(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public boolean shouldCopyTextContent(Element oldElement, Element newElement) {
		if ("overriddenNames".equals(oldElement.getNodeName())) {
			return true;
		}
		
		// call super
		return super.shouldCopyTextContent(oldElement, newElement);
	}

	/**
	 * DomainIterator/DomainAttributeInstance has been changed
	 * to DomainIterator/DomainInstance/DomainAttributeInstance
	 * 
	 * @see org.openiaml.model.migrate.DomBasedMigrator#appendElementToParent(org.w3c.dom.Node, org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public void appendElementToParent(Node parent, Element oldElement,
			Element newElement, Document document) {
		if ("attributes".equals(oldElement.getNodeName()) 
				&& parent instanceof Element
				&& "iaml.domain:DomainIterator".equals(getXsiType((Element) parent))) {
			
			// DomainIterator > DomainAttributeInstance
			// --> DomainIterator > DomainInstance > DomainAttributeInstance
			
			// does a DomainInstance exist yet?
			Element domainInstance = hasDomainInstance((Element) parent);
			if (domainInstance == null) {
				// add a new DomainInstance
				domainInstance = createElement(document, "currentInstance");
				domainInstance.setAttribute("id", generateMigratedId());
				domainInstance.setAttribute("name", "Current instance");
				parent.appendChild(domainInstance);
			}
			domainInstance.appendChild(newElement);
			return;	// we're done
		}
			
		// otherwise, do normal work
		super.appendElementToParent(parent, oldElement, newElement, document);
	}

	/**
	 * Does the given element have an &lt;currentInstance&gt; element?
	 * If none is found, returns <code>null</code>.
	 */
	private Element hasDomainInstance(Element element) {
		// recurse over children
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			Node n = element.getChildNodes().item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				if ("currentInstance".equals(n.getNodeName())) {
					return (Element) n;
				}
			} else {					
				// e.appendChild(document.adoptNode(n));	// this also adds the children of the node, which we don't want 
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.migrate.DomBasedMigrator#shouldAddType(org.w3c.dom.Element, java.util.List)
	 */
	@Override
	protected String shouldAddType(Element oldElement,
			List<ExpectedMigrationException> errors) {
		// <attributes>
		// --> <featureInstances xsi:type="iaml.domain:DomainAttributeInstance">
		if ("attributes".equals(oldElement.getNodeName())
				&& oldElement.getParentNode() != null
				&& oldElement.getParentNode() instanceof Element 
				&& "iaml.domain:DomainIterator".equals(getXsiType((Element) oldElement.getParentNode()))) {
			return "iaml.domain:DomainAttributeInstance";
		}

		// otherwise, call super
		return super.shouldAddType(oldElement, errors);
	}
	
	
	
}