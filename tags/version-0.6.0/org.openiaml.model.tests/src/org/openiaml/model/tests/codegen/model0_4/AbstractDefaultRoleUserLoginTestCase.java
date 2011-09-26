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
		s.add("INSERT INTO User (generated_primary_key, root_user_id, name, email, password) VALUES (66, 66, 'Another Role', 'another@openiaml.org', 'test123')");
		s.add("INSERT INTO User (generated_primary_key, root_user_id, name, email, password) VALUES (77, 77, 'Both Permissions', 'both@openiaml.org', 'test123')");

		s.add("UPDATE iaml_user_root SET a_permission=1 WHERE id=2");
		s.add("UPDATE iaml_user_root SET inherited_permission=1 WHERE id=2");
		s.add("INSERT INTO iaml_user_root (id, inherited_permission_with_role, permission_1, a_permission, a_different_permission, inherited_permission) VALUES (66, 0, 0, 0, 1, 1)");
		s.add("INSERT INTO iaml_user_root (id, inherited_permission_with_role, permission_1, a_permission, a_different_permission, inherited_permission) VALUES (77, 0, 0, 1, 1, 1)");

		s.add("CREATE TABLE default_role (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, User_generated_primary_key INTEGER)");
		s.add("INSERT INTO default_role (generated_primary_key, User_generated_primary_key) VALUES (1, 22)");
		return s;
	}
		
}
