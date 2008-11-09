/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.operations.StopNode;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests inference of sync wires between a domain attribute
 * and a single text field.
 * 
 * @model SyncFieldDomainAttribute.iaml
 * @author jmwright
 *
 */
public class SyncFieldDomainAttribute extends InferenceTestCase {

	protected InternetApplication root;
	
	// TODO refactor out
	protected void setUp() throws Exception {
		String modelFile = ROOT + "inference/SyncFieldDomainAttribute.iaml";
		EObject model = loadModelDirectly(modelFile);
		assertTrue("the model file '" + modelFile + "' should be of type InternetApplication", model instanceof InternetApplication);
		assertNotNull(model);
		
		root = (InternetApplication) model;
		
		// we now try to do inference
		ICreateElements handler = new EcoreInferenceHandler(resource);
		CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(handler);
		ce.create(root);
		
		// write out this inferred model for reference
		saveInferredModel();
	}
	
	protected void tearDown() throws Exception {
		// empty
	}
	
	public void testInference() throws JaxenException {
		Page page = (Page) queryOne(root, "//iaml:children[iaml:name='page']");
		InputTextField field = (InputTextField) queryOne(page, "//iaml:children[iaml:name='single-text-field']");
		SyncWire wire = (SyncWire) queryOne(page, "//iaml:wires[iaml:name='syncField']");
		DomainStore store = (DomainStore) queryOne(root, "//iaml:domainStores[iaml:name='store']");
		DomainObject obj = (DomainObject) queryOne(store, "//iaml:children[iaml:name='domain']");
		DomainAttribute attribute = (DomainAttribute) queryOne(obj, "//iaml:attributes[iaml:name='attribute']");
		
		// field should now have an edit event
		EventTrigger editEvent = (EventTrigger) queryOne(field, "iaml:eventTriggers[iaml:name='edit']");
		
		// this event should have a run wire
		RunInstanceWire runWire = (RunInstanceWire) getWireFrom(page, editEvent, "run");
		
		// the attribute should have an operation 'update'
		Operation opUpdate = (Operation) queryOne(attribute, "iaml:operations[iaml:name='update']");
		
		// the run wire should go to the operation
		assertEquals(runWire.getFrom(), editEvent);
		assertEquals(runWire.getTo(), opUpdate);
		
		// there should be a parameter on the field
		ApplicationElementProperty fieldValue = (ApplicationElementProperty) queryOne(field, "iaml:properties[iaml:name='fieldValue']");
		
		// there should be a parameter wire from fieldValue to the operation
		ParameterWire paramWire = (ParameterWire) getWireFromTo(page, fieldValue, runWire);
		assertEquals(paramWire.getFrom(), fieldValue);
		assertEquals(paramWire.getTo(), runWire);
	}

	/**
	 * It's not possible to do something like //iaml:wire[iaml:from='id']
	 * so we need to parse them manually?
	 * 
	 * @param container
	 * @param fromElement
	 * @param wireName
	 * @return the wire found or null
	 * @throws JaxenException 
	 */
	private WireEdge getWireFrom(Page container, EObject fromElement,
			String wireName) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires[iaml:name='" + wireName + "']");
		for (Object o : wires) {
			if (o instanceof WireEdge && ((WireEdge) o).getFrom().equals(fromElement))
				return (WireEdge) o;
		}
		
		fail("no wire found");
		return null;
	}

	/**
	 * It's not possible to do something like //iaml:wire[iaml:from='id']
	 * so we need to parse them manually?
	 * 
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @return the wire found or null
	 * @throws JaxenException 
	 */
	private WireEdge getWireFromTo(Page container, EObject fromElement, EObject toElement) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof WireEdge) {
				WireEdge w = (WireEdge) o;
				if (w.getFrom().equals(fromElement) && w.getTo().equals(toElement))
					return w;
			}
		}
		
		fail("no wire found");
		return null;
	}
	
	
}
