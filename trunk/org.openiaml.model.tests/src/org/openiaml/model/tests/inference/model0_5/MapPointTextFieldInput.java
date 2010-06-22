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
		EventTrigger onChange = input.getOnChange();
		CompositeOperation update = assertHasCompositeOperation(point, "update");
		ActionEdge run = assertHasRunAction(root, onChange, update);

		Property textValue = assertHasFieldValue(input);
		Property pointValue = assertHasFieldValue(point);

		assertGenerated(assertHasParameterEdge(root, textValue, run));

		// there should be a start node
		assertGenerated(assertHasStartNode(update));

		assertGenerated(textValue);
		assertGenerated(pointValue);
		assertGenerated(onChange);
		assertGenerated(update);
		assertGenerated(run);

	}

}
