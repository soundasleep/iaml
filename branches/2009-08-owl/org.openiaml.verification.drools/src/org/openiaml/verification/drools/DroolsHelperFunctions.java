/**
 * 
 */
package org.openiaml.verification.drools;


/**
 * Helper functions for Drools rules.
 * 
 * This is necessary because:
 * 
 * <ul>
 * 	<li>The same rules are used in multiple files (such as {@link #connects(WireEdge, Object, Object)}.</li>
 *  <li>Function names cannot be shared across multiple rule files (so each rule file needs its own package).</li>
 * </ul>
 * 
 * @author jmwright
 *
 */
public class DroolsHelperFunctions {

	// no functions defined for Validation

}
