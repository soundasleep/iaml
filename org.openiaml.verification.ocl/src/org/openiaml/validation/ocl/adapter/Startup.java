/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id$
 */

package org.openiaml.validation.ocl.adapter;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.ui.IStartup;
import org.openiaml.model.model.ModelPackage;

/**
 * Installs an EMF <code>EValidator</code> on the Library package when we
 * start up.  This validator adapts EMF's <code>EValidator</code> API to the
 * EMF Model Validation Service API.
 * 
 * <p>Based on the generated EMF Validator Adapter example.
 */
public class Startup
	implements IStartup {

	/**
	 * Initializes me.
	 */
	public Startup() {
		super();
	}

	/**
	 * Install the validator.
	 */
	public void earlyStartup() {
		EValidator.Registry.INSTANCE.put(
			ModelPackage.eINSTANCE,
			new EValidatorAdapter());
	}
}
