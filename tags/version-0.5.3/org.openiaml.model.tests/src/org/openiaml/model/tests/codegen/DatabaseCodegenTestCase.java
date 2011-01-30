/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.sql.ResultSet;
import java.util.List;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * <p>
 * Extends code generation test cases to allow for simpler access
 * to databases. Also includes database initialisation through {@link #getDatabaseInitialisers()}.
 * </p>
 * 
 * <p><b>NOTE:</b> {@link #initialiseDatabase()} must be called in order to actually initialise the database.</b>
 * 
 * @see #initialiseDatabase()
 * @see #getDatabaseInitialisers()
 * @author jmwright
 *
 */
public abstract class DatabaseCodegenTestCase extends CodegenTestCase 
	implements DatabaseCodegenTest {
	
	private DatabaseCodegenHelper helper = new DatabaseCodegenHelper(this, this);
	
	/**
	 * Get initial database values. The results returned from here are
	 * queries executed against the initialised database.
	 * 
	 * @return
	 */
	protected abstract List<String> getDatabaseInitialisers();
	
	/**
	 * We use the protected version to enable database initialisers
	 * without breaking the API.
	 */
	@Override
	public List<String> getDatabaseInitialisers1() {
		return getDatabaseInitialisers();
	}

	protected String getDatabaseName() {
		return helper.getDatabaseName();
	}

	public void initialiseDatabase() throws Exception {
		helper.initialiseDatabase();
	}

	protected boolean hasDatabaseBeenInitialised() {
		return helper.hasDatabaseBeenInitialised();
	}

	public ResultSet executeQuery(String sql) throws Exception {
		return helper.executeQuery(sql);
	}
	
}
