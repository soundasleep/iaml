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
 *      <li>ParameterWire is now ParamaterEdge, and no longer a WireEdge
 *      <li>ExtendsWire is now ExtendsEdge, and no longer a WireEdge
 *      <li>RequiresWire is now RequiresEdge, and no longer a WireEdge
 *      <li>ProvidesWire is now ProvidesEdge, and no longer a WireEdge
 *      <li>ConstraintWire is now ConstraintEdge, and no longer a WireEdge
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
	
	private List<String> parameterWires = null;
	private List<String> extendsWires = null;
	private List<String> requiresWires = null;
	private List<String> providesWires = null;
	private List<String> constraintWires = null;
	
	/**
	 * We will cycle over the document, and find out which edges are
	 * iaml.wires:ParameterWire, so we can change their IDs to 
	 * ParameterEdges. 
	 */	
	@Override
	public void prepareDocument(Element documentElement,
			List<ExpectedMigrationException> errors) {
		
		parameterWires = new ArrayList<String>();
		extendsWires = new ArrayList<String>();
		requiresWires = new ArrayList<String>();
		providesWires = new ArrayList<String>();
		constraintWires = new ArrayList<String>();
		prepareDocumentRecurse(documentElement, errors);

	}
	
	/**
	 * Actually looks for ParameterWires.
	 * 
	 * @param e
	 * @param errors
	 */
	private void prepareDocumentRecurse(Element e,
			List<ExpectedMigrationException> errors) {
		
		if ("wires".equals(e.getNodeName())) {
			if ("iaml.wires:ParameterWire".equals(e.getAttribute("xsi:type"))) {
				String id = e.getAttribute("id");
				if (id != null) {
					parameterWires.add(id);
				} else {
					errors.add(new ExpectedMigrationException(this, e, "Element was a ParameterWire but did not have an id attribute."));
				}
			}
			
			if ("iaml.wires:ExtendsWire".equals(e.getAttribute("xsi:type"))) {
				String id = e.getAttribute("id");
				if (id != null) {
					extendsWires.add(id);
				} else {
					errors.add(new ExpectedMigrationException(this, e, "Element was a ExtendsWire but did not have an id attribute."));
				}
			}

			if ("iaml.wires:RequiresWire".equals(e.getAttribute("xsi:type"))) {
				String id = e.getAttribute("id");
				if (id != null) {
					requiresWires.add(id);
				} else {
					errors.add(new ExpectedMigrationException(this, e, "Element was a RequiresWire but did not have an id attribute."));
				}
			}

			if ("iaml.wires:ProvidesWire".equals(e.getAttribute("xsi:type"))) {
				String id = e.getAttribute("id");
				if (id != null) {
					providesWires.add(id);
				} else {
					errors.add(new ExpectedMigrationException(this, e, "Element was a ProvidesWire but did not have an id attribute."));
				}
			}

			if ("iaml.wires:ConstraintWire".equals(e.getAttribute("xsi:type"))) {
				String id = e.getAttribute("id");
				if (id != null) {
					constraintWires.add(id);
				} else {
					errors.add(new ExpectedMigrationException(this, e, "Element was a ConstraintWire but did not have an id attribute."));
				}
			}
		}
		
		// recurse
		NodeList list = e.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i) instanceof Element) 
				prepareDocumentRecurse((Element) list.item(i), errors);
		}
		
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
		
		if (xsiType.equals("iaml.wires:ParameterWire")) {
			return "iaml.wires:ParameterEdge";
		}

		if (xsiType.equals("iaml.wires:ExtendsWire")) {
			return "iaml.wires:ExtendsEdge";
		}

		if (xsiType.equals("iaml.wires:RequiresWire")) {
			return "iaml.wires:RequiresEdge";
		}

		if (xsiType.equals("iaml.wires:ProvidesWire")) {
			return "iaml.wires:ProvidesEdge";
		}

		if (xsiType.equals("iaml.wires:ConstraintWire")) {
			return "iaml.wires:ConstraintEdge";
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
		
		if (nodeName.equals("wires")) {
			// <wires xsi:type="iaml.wires:ParameterWire"> 
			// --> <parameterEdges>
			if ("iaml.wires:ParameterWire".equals(xsiType)) {
				return "parameterEdges";
			}
			
			// <wires xsi:type="iaml.wires:ExtendsWire"> 
			// --> <extendsEdges>
			if ("iaml.wires:ExtendsWire".equals(xsiType)) {
				return "extendsEdges";
			}
			
			// <wires xsi:type="iaml.wires:RequiresWire"> 
			// --> <requiresEdges>
			if ("iaml.wires:RequiresWire".equals(xsiType)) {
				return "requiresEdges";
			}
			
			// <wires xsi:type="iaml.wires:ProvidesWire"> 
			// --> <providesEdges>
			if ("iaml.wires:ProvidesWire".equals(xsiType)) {
				return "providesEdges";
			}

			// <wires xsi:type="iaml.wires:ConstraintWire"> 
			// --> <constraintEdges>
			if ("iaml.wires:ConstraintWire".equals(xsiType)) {
				return "constraintEdges";
			}
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
		
		// any inEdges or outEdges to an old ParameterWire?
		if (!old.getAttribute("inEdges").isEmpty()) {
			splitReferences(old, "inEdges", parameterWires, "inParameterEdges");
		}

		if (!old.getAttribute("outEdges").isEmpty()) {
			splitReferences(old, "outEdges", parameterWires, "outParameterEdges");
		}

		// any inEdges or outEdges to an old ExtendsWire?
		if (!old.getAttribute("inEdges").isEmpty()) {
			splitReferences(old, "inEdges", extendsWires, "inExtendsEdges");
		}

		if (!old.getAttribute("outEdges").isEmpty()) {
			splitReferences(old, "outEdges", extendsWires, "outExtendsEdges");
		}

		// any inEdges or outEdges to an old RequiresWire?
		if (!old.getAttribute("inEdges").isEmpty()) {
			splitReferences(old, "inEdges", requiresWires, "inRequiresEdges");
		}

		if (!old.getAttribute("outEdges").isEmpty()) {
			splitReferences(old, "outEdges", requiresWires, "outRequiresEdges");
		}

		// any inEdges or outEdges to an old ProvidesWire?
		if (!old.getAttribute("inEdges").isEmpty()) {
			splitReferences(old, "inEdges", providesWires, "inProvidesEdges");
		}

		if (!old.getAttribute("outEdges").isEmpty()) {
			splitReferences(old, "outEdges", providesWires, "outProvidesEdges");
		}

		// any inEdges or outEdges to an old ConstraintWire?
		if (!old.getAttribute("inEdges").isEmpty()) {
			splitReferences(old, "inEdges", constraintWires, "inConstraintEdges");
		}

		if (!old.getAttribute("outEdges").isEmpty()) {
			splitReferences(old, "outEdges", constraintWires, "outConstraintEdges");
		}
		
		// remove an attribute?
		if ("iaml.wires:RequiresWire".equals(old.getAttribute("xsi:type")) || "requiresEdges".equals(old.getNodeName())) {
			// make sure it's empty
			if (old.getAttribute("outEdges") != null) {
				if (old.getAttribute("outEdges").isEmpty()) {
					// delete it
					old.removeAttribute("outEdges");
				} else {
					// it's not empty; throw a warning
					errors.add(new ExpectedMigrationException(this, old, "Element needs to lose attribute 'outEdges' but was not empty."));
				}
			}

			// make sure it's empty
			if (old.getAttribute("inEdges") != null) {
				if (old.getAttribute("inEdges").isEmpty()) {
					// delete it
					old.removeAttribute("inEdges");
				} else {
					// it's not empty; throw a warning
					errors.add(new ExpectedMigrationException(this, old, "Element needs to lose attribute 'inEdges' but was not empty."));
				}
			}
		}

	}

	/**
	 * Split all references in e[@oldAttribute] that are in
	 * refs into the new attribute e[@newAttribute].
	 * 
	 */
	private void splitReferences(Element e, String oldAttribute,
			List<String> refs, String newAttribute) {
		
		List<String> newRefs = new ArrayList<String>();
		List<String> oldRefs = new ArrayList<String>();
		String[] allRefs = e.getAttribute(oldAttribute).split(" ");
		for (String s : allRefs) {
			if (refs.contains(s)) {
				newRefs.add(s);
			} else {
				oldRefs.add(s);
			}
		}
		
		// join it back together
		if (!newRefs.isEmpty()) {
			StringBuffer newRef = new StringBuffer();
			for (String s : newRefs) {
				newRef.append(s).append(' ');
			}
			e.setAttribute(newAttribute, newRef.toString().trim());
		}
			{
			StringBuffer oldRef = new StringBuffer();
			for (String s : oldRefs) {
				oldRef.append(s).append(' ');
			}
			e.setAttribute(oldAttribute, oldRef.toString().trim());
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