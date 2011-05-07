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
import org.openiaml.model.model.visual.MapPoint;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class MapPointTextFieldInput extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(MapPointTextFieldInput.class);
	}

	public void testInitial() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		assertNotGenerated(home);

		InputTextField input = assertHasInputTextField(home, "select address");
		assertNotGenerated(input);

		MapPoint point = assertHasMapPoint(home, "target map point");
		assertNotGenerated(point);

		assertNotGenerated(assertHasSetWire(root, input, point, "set"));
	}

	public void testGenerated() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputTextField input = assertHasInputTextField(home, "select address");
		MapPoint point = assertHasMapPoint(home, "target map point");

		// input.onChange -> point.update()
		Event onChange = input.getOnChange();
		Operation update = assertHasOperation(point, "update");
		ECARule run = assertHasRunAction(root, onChange, update);

		Value textValue = assertHasFieldValue(input);
		Value pointValue = assertHasFieldValue(point);

		assertGenerated(assertHasParameter(root, textValue, run));

		assertGenerated(textValue);
		assertGenerated(pointValue);
		assertGenerated(onChange);
		assertGenerated(update);
		assertGenerated(run);

	}

}
