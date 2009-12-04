/**
 * 
 */
package org.openiaml.model.verification;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.verification.crocopat.VerificationEngine;
import org.openiaml.verification.crocopat.VerificationException;
import org.openiaml.verification.crocopat.VerificationViolation;

/**
 * @author jmwright
 *
 */
public class CrocopatValidator implements EValidator {
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EValidator#validate(org.eclipse.emf.ecore.EObject, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	@Override
	public boolean validate(EObject eObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		System.out.println("eObject: " + eObject);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EValidator#validate(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EObject, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	@Override
	public boolean validate(EClass eClass, EObject eObject,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		
		// we only want to deal with InternetApplications (root)		
		if (!eClass.equals(ModelPackage.eINSTANCE.getInternetApplication())) {
			return true;
		}
		
		VerificationEngine verify = new VerificationEngine();
		try {
			IStatus result = verify.verify(eObject, new NullProgressMonitor());
			
			if (result.isOK()) {
				// everything was fine
				return true;
			}
			
			// add diagnoses to all of the failing objects
			for (VerificationViolation violation : verify.getViolations()) {
				diagnostics.add(new BasicDiagnostic(
						Diagnostic.WARNING, /* severity */
						"org.openiaml.model.actions", /* source */
						0, /* code */
						violation.getMessage(), /* message */
						violation.getObjects().toArray() /* data */
				));
			}
			
		} catch (VerificationException e) {
			diagnostics.add(BasicDiagnostic.toDiagnostic(e));
		}
		
		// if we get here, verification has failed
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EValidator#validate(org.eclipse.emf.ecore.EDataType, java.lang.Object, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	@Override
	public boolean validate(EDataType eDataType, Object value,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		System.out.println("eDataType: " + eDataType + " value: " + value);
		return true;
	}

}
