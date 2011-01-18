/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * @author jmwright
 *
 */
public class DatabaseCodegenHelper extends Assert {
	
	private CodegenTestCase parent;
	private DatabaseCodegenTest dataHandler;
	
	public DatabaseCodegenHelper(CodegenTestCase parent, DatabaseCodegenTest dataHandler) {
		this.parent = parent;
		this.dataHandler = dataHandler;
	}
	
	/**
	 * Get the database filename, e.g. 'output/model_12109331eea_e3e.db'
	 *  
	 * @return
	 */
	protected String getDatabaseName() {
		// by default, databases now save to default.db (r2152)
		return "output/default.db";
	}
	
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
		IFile db = parent.getProject().getFile(getDatabaseName());
		
		assertFalse("Database should not exist yet", db.exists());
		Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db.getLocation());
		Statement stat = conn.createStatement();

		// initialise the database
		for (String s : dataHandler.getDatabaseInitialisers1()) {
			try {
				stat.execute(s);
			} catch (Exception e) {
				throw new RuntimeException("Could not execute '" + s + "': " + e.getMessage(), e);
			}
		}
		
		// close the connection and make sure the database exists
		conn.close();
		parent.getProject().refreshProject();
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
	 * Uses the database name defined in {@link #getDatabaseName()}.
	 * 
	 * @param sql SQL query to execute
	 * @return the result set found
	 * @throws Exception
	 */
	public ResultSet executeQuery(String sql) throws Exception {
		return parent.loadDatabaseQuery(getDatabaseName(), sql);
	}
	
}
