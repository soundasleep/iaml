/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Map;
import org.openiaml.model.model.visual.MapPoint;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class MapFormInput extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(MapFormInput.class);
	}

	public void testInitial() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		assertNotGenerated(home);

		InputForm form = assertHasInputForm(home, "type in some addresses");
		assertNotGenerated(form);

		InputTextField field1 = assertHasInputTextField(form, "address 1");
		InputTextField field2 = assertHasInputTextField(form, "address 2");
		InputTextField field3 = assertHasInputTextField(form, "address 3");

		assertNotGenerated(field1);
		assertNotGenerated(field2);
		assertNotGenerated(field3);

		Map map = assertHasMap(home, "target map");
		assertNotGenerated(map);

		assertNotGenerated(assertHasSetWire(root, form, map, "set"));
	}

	/**
	 * MapPoints are generated in the Map.
	 *
	 * @throws Exception
	 */
	public void testMapPoints() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "type in some addresses");
		InputTextField field1 = assertHasInputTextField(form, "address 1");
		InputTextField field2 = assertHasInputTextField(form, "address 2");
		InputTextField field3 = assertHasInputTextField(form, "address 3");
		Map map = assertHasMap(home, "target map");

		// following generated
		MapPoint point1 = assertHasMapPoint(map, "address 1");
		MapPoint point2 = assertHasMapPoint(map, "address 2");
		MapPoint point3 = assertHasMapPoint(map, "address 3");

		// generated
		assertGenerated(point1);
		assertGenerated(point2);
		assertGenerated(point3);

		// and connected by set wires
		assertGenerated(assertHasSetWire(root, field1, point1));
		assertGenerated(assertHasSetWire(root, field2, point2));
		assertGenerated(assertHasSetWire(root, field3, point3));

		// not the other way around
		assertHasNoSetWire(root, point1, field1);
		assertHasNoSetWire(root, point2, field2);
		assertHasNoSetWire(root, point3, field3);

		// not sync wires
		assertHasNoSyncWire(root, field1, point1);
		assertHasNoSyncWire(root, field2, point2);
		assertHasNoSyncWire(root, field3, point3);
	}

	/**
	 * Field1.edit runs Point1.update
	 *
	 * @throws Exception
	 */
	public void testEventUpdate() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "type in some addresses");
		InputTextField field1 = assertHasInputTextField(form, "address 1");
		Map map = assertHasMap(home, "target map");
		MapPoint point1 = assertHasMapPoint(map, "address 1");

		// following generated
		Property value1 = assertHasFieldValue(field1);
		assertGenerated(value1);

		EventTrigger change = field1.getOnChange();
		assertNotNull(change);

		Operation update = assertHasOperation(point1, "update");

		ActionEdge run = assertHasRunAction(root, change, update);
		assertGenerated(run);

		ParameterEdge param = assertHasParameterEdge(root, value1, run);
		assertGenerated(param);

	}

}
