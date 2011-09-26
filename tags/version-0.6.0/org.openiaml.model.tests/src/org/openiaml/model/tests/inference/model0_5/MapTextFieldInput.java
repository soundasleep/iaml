/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Value;
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
		Event onChange = input.getOnChange();
		Operation update = assertHasOperation(map, "update");
		ECARule run = assertHasRunAction(root, onChange, update);

		Value textValue = assertHasFieldValue(input);
		Value mapValue = assertHasFieldValue(map);

		assertGenerated(assertHasParameter(root, textValue, run));

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
