package org.openiaml.model.migrate;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Migrate model version 0.0 to version 0.1.
 *
 * In the future most of this functionality may be refactored into an abstract superclass.
 *
 * @see #isVersion(Document) for what defines this model
 * @see #recurseOverDocument(Element, Node, Document) for actual model changes
 * @author jmwright
 *
 */
public class Migrate0To1 extends DomBasedMigrator implements IamlModelMigrator {

	public String getName() {
		return "Migrator 0.0 to 0.1";
	}
	
	/**
	 * We can identify a version 0.0 model:
	 * 
	 *  <ol>
	 *   <li>The NS package is http://openiaml.org/model</li>
	 *   <li>The root element does not have an attribute "id"</li>
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

			if (nsPackage.equals(getTargetNamespace()) && 
					rootId.isEmpty()) {
				// this is us! (version 0.0)
				return true;
			}
		} catch (Exception e) {
			throw new MigrationException(e);
		}
			
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#getRenamedNode(java.lang.String, org.w3c.dom.Element)
	 */
	@Override
	public String getRenamedNode(String nodeName, Element element, List<ExpectedMigrationException> errors) {
		// <edges> --> <wires>
		if (nodeName.equals("edges")) {
			return "wires";
		}
		
		return nodeName;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#shouldDeleteNode(org.w3c.dom.Element, org.w3c.dom.Node)
	 */
	@Override
	public boolean shouldDeleteNode(Element element, Node parent, List<ExpectedMigrationException> errors) {
		// an <operation> can no longer contain <wires> (<edges>)
		if (element.getNodeName().equals("edges") && parent.getNodeName().equals("operations")) {
			return true;
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#handleAttribute(java.lang.String, java.lang.String, org.w3c.dom.Element)
	 */
	@Override
	public String handleAttribute(String name, String value, Element element, List<ExpectedMigrationException> errors) {
		// should we replace IDs?
		if (value.indexOf("//@") >= 0) {
			value = replaceReferences(value);
		}

		return value;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#replaceType(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	public String replaceType(Element element, String xsiType, List<ExpectedMigrationException> errors) {
		// RunWire --> RunInstanceWire
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
			return null;
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
		
		return xsiType;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.migrate.DomBasedMigrator#handleElement(org.w3c.dom.Element, org.w3c.dom.Element, java.util.List)
	 */
	@Override
	public void handleElement(Element old, Element element,
			List<ExpectedMigrationException> errors) {
		// does nothing
	}

	@Override
	protected String getTargetNamespace() {
		return "http://openiaml.org/model";
	}

}