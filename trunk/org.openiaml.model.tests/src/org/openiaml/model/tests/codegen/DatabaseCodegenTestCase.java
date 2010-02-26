/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.eclipse.core.resources.IFile;
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
public abstract class DatabaseCodegenTestCase extends CodegenTestCase {
	
	/**
	 * Get initial database values. The results returned from here are
	 * queries executed against the initialised database.
	 * 
	 * @return
	 */
	protected abstract List<String> getDatabaseInitialisers();
	
	/**
	 * Get the database filename, e.g. 'output/model_12109331eea_e3e.db'
	 *  
	 * @return
	 */
	protected abstract String getDatabaseName();
	
	/**
	 * Initialise the database given the values specified in 
	 * {@link #getDatabaseInitialisers()} and {@link #getDatabaseName()}.
	 * 
	 * This must be called by subclasses if they wish to have the database
	 * initialised.
	 */
	public void initialiseDatabase() throws Exception {
		// lets create a database
		Class.forName("org.sqlite.JDBC");
		IFile db = getProject().getFile(getDatabaseName());
		
		assertFalse("Database should not exist yet", db.exists());
		Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db.getLocation());
		Statement stat = conn.createStatement();

		// initialise the database
		for (String s : getDatabaseInitialisers()) {
			stat.execute(s);
		}
		
		// close the connection and make sure the database exists
		conn.close();
		getProject().refreshProject();
		assertTrue("Database should now exist", db.exists());
		
		databaseInitialised = true;
	}
	
	private boolean databaseInitialised = false;
	
	/**
	 * Has {@link #initialiseDatabase()} been successfully
	 * called?
	 * 
	 * @return
	 */
	protected boolean hasDatabaseBeenInitialised() {
		return databaseInitialised;
	}
	
	/**
	 * Directly execute a SQL query and get the results.
	 * 
	 * @param sql SQL query to execute
	 * @return the result set found
	 * @throws Exception
	 */
	protected ResultSet executeQuery(String sql) throws Exception {
		return loadDatabaseQuery(getDatabaseName(), sql);
	}
	
}
