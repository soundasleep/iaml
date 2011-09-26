/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.util.List;

/**
 * @author jmwright
 *
 */
public interface DatabaseCodegenTest {
	
	/**
	 * Get initial database values. The results returned from here are
	 * queries executed against the initialised database.
	 * 
	 * @return
	 */
	public List<String> getDatabaseInitialisers1();
	
}
