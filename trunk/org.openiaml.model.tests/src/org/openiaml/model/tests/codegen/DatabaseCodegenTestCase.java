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
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Extends code generation test cases to allow for simpler access
 * to databases. Also includes database initialisation.
 * 
 * @author jmwright
 *
 */
public abstract class DatabaseCodegenTestCase extends CodegenTestCase {
	
	protected InternetApplication root;

	/**
	 * Get initial database values.
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
		refreshProject();
		assertTrue("Database should now exist", db.exists());
	}
	
	/**
	 * Directly execute a SQL query and get the results.
	 * 
	 * @param sql SQL query to execute
	 * @return the result set found
	 * @throws Exception
	 */
	protected ResultSet executeQuery(String sql) throws Exception {
		// refresh the workspace first, in case the database is new
		waitForAjax();
		refreshProject();
		
		// lets create a database
		Class.forName("org.sqlite.JDBC");
		IFile db = getProject().getFile(getDatabaseName());
		
		assertTrue("Database should exist", db.exists());
		Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db.getLocation());
		Statement stat = conn.createStatement();

		return stat.executeQuery(sql);
	}
	
}
