package org.openiaml.model.verification;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.ui.IStartup;
import org.openiaml.model.model.ModelPackage;

/**
 * Registers the Crocopat validator.
 * 
 * @author jmwright
 *
 */
public class Startup implements IStartup {

	@Override
	public void earlyStartup() {
		EValidator.Registry.INSTANCE.put(ModelPackage.eINSTANCE, new CrocopatValidator());

	}

}
