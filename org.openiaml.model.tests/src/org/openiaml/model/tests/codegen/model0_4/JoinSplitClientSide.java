/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * A JoinNode executing on the client-side.
 * 
 * @operational JoinNode,SplitNode A {@model SplitNode} executing on 
 * 		the client will split execution into separate threads until 
 * 		each thread meets up at a {@model JoinNode}. 
 * @example JoinNode,SplitNode,ExecutionEdge {@model SplitNode Splitting} execution into
 * 		different threads on the client.
 * @author jmwright
 *
 */
public class JoinSplitClientSide extends CodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(JoinSplitClientSide.class);
	}
	
	/**
	 * Initially the three fields are empty.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		{
			String field = getLabelIDForText("field1");
			assertLabeledFieldEquals(field, "");
		}

		{
			String field = getLabelIDForText("field2");
			assertLabeledFieldEquals(field, "");
		}

		{
			String field = getLabelIDForText("field3");
			assertLabeledFieldEquals(field, "");
		}
	}
	
	/**
	 * Press the button to start the operation, which should
	 * set all of the values.
	 * 
	 * @throws Exception
	 */
	public void testButton() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		clickButtonWithText("target");
		assertNoProblem();
		
		{
			String field = getLabelIDForText("field1");
			assertLabeledFieldEquals(field, "foo");
		}

		{
			String field = getLabelIDForText("field2");
			assertLabeledFieldEquals(field, "bar");
		}

		{
			String field = getLabelIDForText("field3");
			assertLabeledFieldEquals(field, "baz");
		}
	}

}
