/**
 * 
 */
package org.openiaml.verification.crocopat;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * Represents a violation of a verification.
 * 
 * @author jmwright
 *
 */
public class VerificationViolation {

	private List<EObject> objects;
	private String message;

	public VerificationViolation(List<EObject> objects, String message) {
		this.objects = objects;
		this.message = message;
	}

	public VerificationViolation(EObject object, String message) {
		this(Collections.singletonList(object), message);
	}

	public List<EObject> getObjects() {
		return objects;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return message + ": " + objects;
	}
	
	
	
}
