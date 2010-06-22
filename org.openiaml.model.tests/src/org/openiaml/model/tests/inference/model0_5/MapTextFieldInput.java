/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Map;
import org.openiaml.model.model.visual.MapPoint;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class MapTextFieldInput extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(MapTextFieldInput.class);
	}

	public void testInitial() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		assertNotGenerated(home);

		InputTextField input = assertHasInputTextField(home, "select address");
		assertNotGenerated(input);

		Map map = assertHasMap(home, "Target Map");
		assertNotGenerated(map);

		assertNotGenerated(assertHasSetWire(root, input, map, "set"));
	}

	public void testGenerated() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputTextField input = assertHasInputTextField(home, "select address");
		Map map = assertHasMap(home, "Target Map");

		// input.onChange -> point.update()
		EventTrigger onChange = input.getOnChange();
		CompositeOperation update = assertHasCompositeOperation(map, "update");
		ActionEdge run = assertHasRunAction(root, onChange, update);

		Property textValue = assertHasFieldValue(input);
		Property mapValue = assertHasFieldValue(map);

		assertGenerated(assertHasParameterEdge(root, textValue, run));

		// there should be a start node
		assertGenerated(assertHasStartNode(update));

		assertGenerated(textValue);
		assertGenerated(mapValue);
		assertGenerated(onChange);
		assertGenerated(update);
		assertGenerated(run);

	}

	/**
	 * No {@model MapPoint}s should be generated within the {@model Map}.
	 */
	public void testNoMapPointsInMap() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		Map map = assertHasMap(home, "Target Map");

		// no children
		assertEquals(0, typeSelect(map.getChildren(), MapPoint.class).size());
	}

}
