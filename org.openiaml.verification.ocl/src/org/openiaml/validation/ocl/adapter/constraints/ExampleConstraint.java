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

package org.openiaml.validation.ocl.adapter.constraints;

import org.eclipse.core.runtime.IStatus;

import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;


/**
 * A simple example constraint to demonstrate delegation from EMF to the
 * EMF Validation Framework.  This constraint will always fail, in order
 * to emit an informational message proving that it was invoked.
 * 
 * <p>Based on the generated EMF Validator Adapter example.
 */
public class ExampleConstraint
	extends AbstractModelConstraint {

	/**
	 * Initializes me.
	 */
	public ExampleConstraint() {
		super();
	}

	/**
	 * I fail on every object that I see.
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		return ctx.createFailureStatus(ctx.getTarget());
	}
}
