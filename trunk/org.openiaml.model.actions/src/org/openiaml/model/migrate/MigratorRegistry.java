/**
 * 
 */
package org.openiaml.model.migrate;

import java.util.ArrayList;
import java.util.List;

/**
 * A storage of migrators available in the system.
 * 
 * @author jmwright
 *
 */
public class MigratorRegistry {
	/**
	 * Get all of the migrators registered in the system.
	 * They should be returned in order of migration, e.g. a
	 * version 3-4 migrator should appear before a 5-6 migrator,
	 * and after a 2-3 migrator.
	 * 
	 * In the future this might be implemented as an extension point.
	 * 
	 * @return A list of available model migrators.
	 */
	public static List<IamlModelMigrator> getMigrators() {
		List<IamlModelMigrator> migrators = new ArrayList<IamlModelMigrator>();
		migrators.add(new Migrate0To1());
		migrators.add(new Migrate1To2());
		migrators.add(new Migrate2To3());
		return migrators;
	}
}
