/**
 * 
 */
package org.openiaml.model.diagram.custom.migrate;

import java.io.InputStream;

import org.eclipse.core.runtime.IProgressMonitor;
import org.w3c.dom.Document;

/**
 * Represents an XML-based model migrator.
 * It can load XML Documents and work out whether it can
 * upgrade it to the next version {@link #isVersion(Document)}
 * and then do the actual upgrade {@link #migrate(Document, IProgressMonitor)}.
 * 
 * @author jmwright
 *
 */
public interface IamlModelMigrator {

	/**
	 * Can this migrator upgrade this model version,
	 * i.e. is this migrator the same version as this model?
	 * 
	 * @param doc The model to inspect
	 * @return True if this migrator is the same version as doc
	 * @throws MigrationException
	 */
	public boolean isVersion(Document doc) throws MigrationException;
	
	/**
	 * Do the actual migration into an InputStream.
	 * 
	 * TODO it may make more sense to return a Document
	 * rather than an InputStream.
	 * 
	 * @param doc The model to migrate
	 * @param monitor A progress monitor (unused)
	 * @return The newly created XML model
	 * @throws MigrationException
	 */
	public Document migrate(Document doc, IProgressMonitor monitor)
		throws MigrationException;
}