package org.openiaml.model.migrate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <p>Migrate model version 0.5 to 0..
 *
 * <p>In the future most of this functionality should be refactored into an abstract superclass.
 *
 * <p>Changes in model version 0.5.3:
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
		if (xsiType.equals("iaml.wires:NavigateAction") || xsiType.equals("iaml.wires:RunAction")
				|| /* issue 244 */ xsiType.equals("iaml:ActionEdge")) {
			return "iaml:ECARule";
		}

		// <children xsi:type="iaml.visual:Hidden">
		// --> <children xsi:type="iaml.visual:Label" visible="false">
		if (xsiType.equals("iaml.visual:Hidden")) {
			return "iaml.visual:Label";
		}
		
		// "iaml:DomainAttribute"
		// --> "iaml.domain:DomainAttribute"
		if (xsiType.equals("iaml:DomainAttribute")) {
			return "iaml.domain:DomainAttribute";
		}
		
		// issue 244
		if (xsiType.equals("iaml.wires:ParameterEdge")) {
			return "iaml:Parameter";
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
		
		// <attributes type="xsd_string">
		// --> <eStructuralFeatures eType="xsd_string">
		if ("attributes".equals(element.getNodeName()) &&
				"schemas".equals(element.getParentNode().getNodeName())) {
			return "eStructuralFeatures";
		}
		
		/* issue 244 */
		if ("conditions".equals(element.getNodeName())) {
			return "functions";
		}
		if ("actions".equals(element.getNodeName())) {
			return "ecaRules";
		}
		
		return super.getRenamedNode(nodeName, element, errors);
	}
	
	@Override
	public String getRenamedAttribute(Element oldElement, Element newElement,
			String name, String value) {
		
		String nodeName = oldElement.getNodeName();

		// <values value="static">
		// --> <properties defaultValue="static" readOnly="true">
		if (("properties".equals(nodeName) || "values".equals(nodeName)) &&
				"value".equals(name)) {
			return "defaultValue";
		}
		
		// <attributes type="xsd_string">
		// --> <eStructuralFeatures eType="xsd_string">
		if ("attributes".equals(nodeName) && 
				"type".equals(name)) {
			return "eType";
		}
		
		/* issue 244 */
		
		if ("parameterEdges".equals(nodeName) && "from".equals(name)) {
			return "term";
		}
		if ("parameterEdges".equals(nodeName) && "to".equals(name)) {
			return "value";
		}

		if ("actions".equals(nodeName) && "from".equals(name)) {
			return "trigger";
		}
		if ("actions".equals(nodeName) && "to".equals(name)) {
			return "target";
		}

		if ("conditionEdges".equals(nodeName) && "from".equals(name)) {
			return "function";
		}
		if ("conditionEdges".equals(nodeName) && "to".equals(name)) {
			return "conditioned";
		}

		if ("inConditionEdges".equals(name)) {
			return "conditions";
		}
		if ("outConditionEdges".equals(name)) {
			return "conditioned";
		}
		if ("inActions".equals(name)) {
			return "rules";
		}
		if ("outActions".equals(name)) {
			return "listeners";
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
		
		if (("iaml.domain:DomainAttribute".equals(xsiType) || "iaml:DomainAttribute".equals(xsiType))
				&& "scopes".equals(parent.getNodeName())) {
			errors.add(new ExpectedMigrationException(this, element, "Scopes can no longer contain DomainAttributes directly, and needs to be reimplemented as a DomainAttributeInstance manually."));
			return true;
		}
		
		// various properties of attributes are no longer permitted
		if (parent != null && ("attributes".equals(parent.getNodeName()) || "eStructuralFeatures".equals(parent.getNodeName()))) {
			if ("operations".equals(element.getNodeName())) {
				errors.add(new ExpectedMigrationException(this, element, "DomainAttributes can no longer contain Operations directly."));
				return true;
			}

			if ("conditions".equals(element.getNodeName())) {
				errors.add(new ExpectedMigrationException(this, element, "DomainAttributes can no longer contain Conditions directly."));
				return true;
			}

			if ("onChange".equals(element.getNodeName())) {
				errors.add(new ExpectedMigrationException(this, element, "DomainAttributes can no longer contain onChange directly."));
				return true;
			}

			if ("fieldValue".equals(element.getNodeName())) {
				errors.add(new ExpectedMigrationException(this, element, "DomainAttributes can no longer contain fieldValue directly."));
				return true;
			}
		}
		
		// issue 242: replace <attribute><type href="..."></attribute>
		// --> with <attribute type="..." />
		if (isOldTypedElement(element)) {
			// this is not an error
			return true;
		}
		
		// issue 253: DynamicApplicationElementSet removed from iaml
		if ("iaml:DynamicApplicationElementSet".equals(xsiType)) {
			errors.add(new ExpectedMigrationException(this, element, "DynamicApplicationElementSets are no longer supported in IAML."));
			return true;
		}
		
		// super call
		return super.shouldDeleteNode(element, parent, errors);
	}
	
	private boolean isOldTypedElement(Element element) {
		Node parent = element.getParentNode();
		
		if ("type".equals(element.getNodeName()) &&
				element.hasAttribute("href")) {
			String parentName = (parent != null ? parent.getNodeName() : null);
			if ("attributes".equals(parentName) || "fieldValue".equals(parentName) 
					|| "properties".equals(parentName) || "children".equals(parentName)
					|| "labels".equals(parentName) || "variables".equals(parentName)) {
				
				return true;
			}
		}
		
		return false;
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
			Document document,
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
		
		// <internetApplication>:
		// need to add lots of builtin datatypes
		if ("InternetApplication".equals(old.getNodeName()) ||
				"iaml:InternetApplication".equals(old.getNodeName())) {
			
			// XSD data types
			{
				Map<String,String> map = getXSDTypeMap();
				for (String key : map.keySet()) {
					String uri = map.get(key);
					
					// check if such an element already exists
					boolean alreadyExists = false;
					for (Element e : getChildElements(old)) {
						if ("xsdDataTypes".equals(e.getNodeName()) && 
								e.hasAttribute("id") &&
								key.equals(e.getAttribute("id")))
						alreadyExists = true;
					}
					if (alreadyExists) continue;

					Element et = createElement(document, "xsdDataTypes");
					et.setAttribute("name", key.replace("_", ":"));
					et.setAttribute("id", key);
					Element def = createElement(document, "definition");
					def.setAttribute("href", uri);
					et.appendChild(def);
					element.appendChild(et);
				}
			}
			
			// builtin data types
			{
				Map<String,String> map = getBuiltinTypeMap();
				for (String key : map.keySet()) {
					String uri = map.get(key);

					// check if such an element already exists
					boolean alreadyExists = false;
					for (Element e : getChildElements(old)) {
						if ("xsdDataTypes".equals(e.getNodeName()) && 
								e.hasAttribute("id") &&
								key.equals(e.getAttribute("id")))
						alreadyExists = true;
					}
					if (alreadyExists) continue;
					
					Element et = createElement(document, "xsdDataTypes");
					et.setAttribute("name", key.replace("_", ":"));
					et.setAttribute("id", key);
					Element def = createElement(document, "definition");
					def.setAttribute("href", uri);
					et.appendChild(def);
					element.appendChild(et);
				}
			}

		}
		
		// issue 242: replace <attribute><type href="..."></attribute>
		// --> with <attribute type="..." />
		{
			NodeList nl = old.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				Node n = nl.item(i);
				if (n instanceof Element) {
					Element e = (Element) n;
					if (isOldTypedElement(e)) {
						// we found an old typed element
						// need to add a new "type" attribute
						String type = null;
						String href = e.hasAttribute("href") ? e.getAttribute("href") : "";
						if (href.contains(";")) {
							href = href.substring(0, href.indexOf(";"));	// remove ; (hash)
						}
						
						{
							Map<String,String> map = getXSDTypeMap();
							for (String key : map.keySet()) {
								String value = map.get(key);
								if (value.contains(";"))
									value = value.substring(0, value.indexOf(";"));	// remove ; (hash)
								if (value.equals(href)) {
									type = key;
								}
							}
						}

						{
							Map<String,String> map = getBuiltinTypeMap();
							for (String key : map.keySet()) {
								String value = map.get(key);
								if (value.contains(";"))
									value = value.substring(0, value.indexOf(";"));	// remove ; (hash)
								if (value.equals(href)) {
									type = key;
								}
							}
						}
						
						if (type != null) {
							if (old.getParentNode() != null &&
									"schemas".equals(old.getParentNode().getNodeName())) {
								element.setAttribute("eType", type);
							} else {
								element.setAttribute("type", type);
							}
						}
					}
				}
			}
		}
		
	}

	public Map<String, String> getXSDTypeMap() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("xsd_boolean", XSD_URI + "boolean;XSDSimpleTypeDefinition=10");
		map.put("xsd_integer", XSD_URI + "integer;XSDSimpleTypeDefinition=40");
		map.put("xsd_string", XSD_URI + "string;XSDSimpleTypeDefinition=9");
		return map;
	}
	
	public Map<String, String> getBuiltinTypeMap() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("builtin_iamlAddress", BUILTIN_URI + "iamlAddress;XSDSimpleTypeDefinition=2");
		map.put("builtin_iamlDateTime", BUILTIN_URI + "iamlDateTime;XSDSimpleTypeDefinition=4");
		map.put("builtin_iamlEmail", BUILTIN_URI + "iamlEmail;XSDSimpleTypeDefinition=3");
		map.put("builtin_iamlInteger", BUILTIN_URI + "iamlInteger;XSDSimpleTypeDefinition");
		map.put("builtin_iamlOpenIDURL", BUILTIN_URI + "iamlOpenIDURL;XSDSimpleTypeDefinition=6");
		map.put("builtin_iamlString", BUILTIN_URI + "iamlString;XSDSimpleTypeDefinition=1");
		map.put("builtin_iamlURL", BUILTIN_URI + "iamlURL;XSDSimpleTypeDefinition=5");
		return map;
	}
	
	public static final String XSD_URI = "platform:/plugin/org.eclipse.xsd/cache/www.w3.org/2001/XMLSchema.xsd#//";
	public static final String BUILTIN_URI = "platform:/plugin/org.openiaml.model/model/datatypes.xsd#//";

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
		
		// <attributes type="xsd_string">
		// --> <eStructuralFeatures xsi:type="iaml.domain:DomainAttribute" eType="xsd_string">
		if ("attributes".equals(oldElement.getNodeName()) 
				&& "schemas".equals(oldElement.getParentNode().getNodeName())) {
			return "iaml.domain:DomainAttribute";
		}

		// otherwise, call super
		return super.shouldAddType(oldElement, errors);
	}
	
	
}