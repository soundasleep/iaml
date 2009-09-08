/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.List;

/**
 * Adds additional database initialisation to set up permissions and
 * 'default role'.
 * 
 * @author jmwright
 *
 */
public abstract class AbstractDefaultRoleUserLoginTestCase extends AbstractUserLoginTestCase {
		
	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = super.getDatabaseInitialisers();
		s.add("INSERT INTO User (generated_primary_key, name, email, password) VALUES (66, 'Another Role', 'another@openiaml.org', 'test123')");
		s.add("INSERT INTO User (generated_primary_key, name, email, password) VALUES (77, 'Both Permissions', 'both@openiaml.org', 'test123')");

		s.add("CREATE TABLE Permissions_User (generated_primary_key INTEGER PRIMARY KEY, a_permission INTEGER, a_different_permission INTEGER)");
		s.add("INSERT INTO Permissions_User (generated_primary_key, a_permission, a_different_permission) VALUES (22, 1, 0)");
		s.add("INSERT INTO Permissions_User (generated_primary_key, a_permission, a_different_permission) VALUES (66, 0, 1)");
		s.add("INSERT INTO Permissions_User (generated_primary_key, a_permission, a_different_permission) VALUES (77, 1, 1)");

		s.add("CREATE TABLE default_role (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, User_generated_primary_key INTEGER)");
		s.add("INSERT INTO default_role (generated_primary_key, User_generated_primary_key) VALUES (1, 22)");
		return s;
	}
		
}
