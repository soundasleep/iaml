/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.docs.modeldoc.AdditionalDocumentation;
import org.openiaml.docs.modeldoc.AdditionalLatex;
import org.openiaml.docs.modeldoc.Constraint;
import org.openiaml.docs.modeldoc.DroolsPackage;
import org.openiaml.docs.modeldoc.DroolsRule;
import org.openiaml.docs.modeldoc.EMFAttribute;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.EMFReference;
import org.openiaml.docs.modeldoc.Example;
import org.openiaml.docs.modeldoc.FileLineReference;
import org.openiaml.docs.modeldoc.FileReference;
import org.openiaml.docs.modeldoc.GraphicalRepresentation;
import org.openiaml.docs.modeldoc.ImplementationNote;
import org.openiaml.docs.modeldoc.InferenceSemantic;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.JavaElement;
import org.openiaml.docs.modeldoc.JavaMethod;
import org.openiaml.docs.modeldoc.JavadocClassReference;
import org.openiaml.docs.modeldoc.JavadocFragment;
import org.openiaml.docs.modeldoc.JavadocMethodReference;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.JavadocTextElement;
import org.openiaml.docs.modeldoc.Metric;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModelExtension;
import org.openiaml.docs.modeldoc.ModelReference;
import org.openiaml.docs.modeldoc.ModeldocPackage;
import org.openiaml.docs.modeldoc.OperationalSemantic;
import org.openiaml.docs.modeldoc.Reference;
import org.openiaml.docs.modeldoc.Semantic;
import org.openiaml.docs.modeldoc.Template;
import org.openiaml.docs.modeldoc.TemplateFile;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.openiaml.docs.modeldoc.ModeldocPackage
 * @generated
 */
public class ModeldocSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModeldocPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModeldocSwitch() {
		if (modelPackage == null) {
			modelPackage = ModeldocPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModeldocPackage.MODEL_DOCUMENTATION: {
				ModelDocumentation modelDocumentation = (ModelDocumentation)theEObject;
				T result = caseModelDocumentation(modelDocumentation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.EMF_CLASS: {
				EMFClass emfClass = (EMFClass)theEObject;
				T result = caseEMFClass(emfClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.ADDITIONAL_DOCUMENTATION: {
				AdditionalDocumentation additionalDocumentation = (AdditionalDocumentation)theEObject;
				T result = caseAdditionalDocumentation(additionalDocumentation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.ADDITIONAL_LATEX: {
				AdditionalLatex additionalLatex = (AdditionalLatex)theEObject;
				T result = caseAdditionalLatex(additionalLatex);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.SEMANTIC: {
				Semantic semantic = (Semantic)theEObject;
				T result = caseSemantic(semantic);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.EXAMPLE: {
				Example example = (Example)theEObject;
				T result = caseExample(example);
				if (result == null) result = caseSemantic(example);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.OPERATIONAL_SEMANTIC: {
				OperationalSemantic operationalSemantic = (OperationalSemantic)theEObject;
				T result = caseOperationalSemantic(operationalSemantic);
				if (result == null) result = caseSemantic(operationalSemantic);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.GRAPHICAL_REPRESENTATION: {
				GraphicalRepresentation graphicalRepresentation = (GraphicalRepresentation)theEObject;
				T result = caseGraphicalRepresentation(graphicalRepresentation);
				if (result == null) result = caseSemantic(graphicalRepresentation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.INFERENCE_SEMANTIC: {
				InferenceSemantic inferenceSemantic = (InferenceSemantic)theEObject;
				T result = caseInferenceSemantic(inferenceSemantic);
				if (result == null) result = caseSemantic(inferenceSemantic);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.IMPLEMENTATION_NOTE: {
				ImplementationNote implementationNote = (ImplementationNote)theEObject;
				T result = caseImplementationNote(implementationNote);
				if (result == null) result = caseSemantic(implementationNote);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.CONSTRAINT: {
				Constraint constraint = (Constraint)theEObject;
				T result = caseConstraint(constraint);
				if (result == null) result = caseSemantic(constraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.MODEL_EXTENSION: {
				ModelExtension modelExtension = (ModelExtension)theEObject;
				T result = caseModelExtension(modelExtension);
				if (result == null) result = caseSemantic(modelExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.REFERENCE: {
				Reference reference = (Reference)theEObject;
				T result = caseReference(reference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.JAVA_ELEMENT: {
				JavaElement javaElement = (JavaElement)theEObject;
				T result = caseJavaElement(javaElement);
				if (result == null) result = caseReference(javaElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.JAVA_CLASS: {
				JavaClass javaClass = (JavaClass)theEObject;
				T result = caseJavaClass(javaClass);
				if (result == null) result = caseJavaElement(javaClass);
				if (result == null) result = caseReference(javaClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.JAVA_METHOD: {
				JavaMethod javaMethod = (JavaMethod)theEObject;
				T result = caseJavaMethod(javaMethod);
				if (result == null) result = caseJavaElement(javaMethod);
				if (result == null) result = caseReference(javaMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.MODEL_REFERENCE: {
				ModelReference modelReference = (ModelReference)theEObject;
				T result = caseModelReference(modelReference);
				if (result == null) result = caseReference(modelReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.DROOLS_PACKAGE: {
				DroolsPackage droolsPackage = (DroolsPackage)theEObject;
				T result = caseDroolsPackage(droolsPackage);
				if (result == null) result = caseReference(droolsPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.DROOLS_RULE: {
				DroolsRule droolsRule = (DroolsRule)theEObject;
				T result = caseDroolsRule(droolsRule);
				if (result == null) result = caseJavaElement(droolsRule);
				if (result == null) result = caseReference(droolsRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.FILE_REFERENCE: {
				FileReference fileReference = (FileReference)theEObject;
				T result = caseFileReference(fileReference);
				if (result == null) result = caseReference(fileReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.FILE_LINE_REFERENCE: {
				FileLineReference fileLineReference = (FileLineReference)theEObject;
				T result = caseFileLineReference(fileLineReference);
				if (result == null) result = caseReference(fileLineReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.JAVADOC_FRAGMENT: {
				JavadocFragment javadocFragment = (JavadocFragment)theEObject;
				T result = caseJavadocFragment(javadocFragment);
				if (result == null) result = caseReference(javadocFragment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.JAVADOC_TAG_ELEMENT: {
				JavadocTagElement javadocTagElement = (JavadocTagElement)theEObject;
				T result = caseJavadocTagElement(javadocTagElement);
				if (result == null) result = caseJavadocFragment(javadocTagElement);
				if (result == null) result = caseReference(javadocTagElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.JAVADOC_TEXT_ELEMENT: {
				JavadocTextElement javadocTextElement = (JavadocTextElement)theEObject;
				T result = caseJavadocTextElement(javadocTextElement);
				if (result == null) result = caseJavadocFragment(javadocTextElement);
				if (result == null) result = caseReference(javadocTextElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.JAVADOC_METHOD_REFERENCE: {
				JavadocMethodReference javadocMethodReference = (JavadocMethodReference)theEObject;
				T result = caseJavadocMethodReference(javadocMethodReference);
				if (result == null) result = caseJavadocFragment(javadocMethodReference);
				if (result == null) result = caseReference(javadocMethodReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.JAVADOC_CLASS_REFERENCE: {
				JavadocClassReference javadocClassReference = (JavadocClassReference)theEObject;
				T result = caseJavadocClassReference(javadocClassReference);
				if (result == null) result = caseJavadocFragment(javadocClassReference);
				if (result == null) result = caseReference(javadocClassReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.EMF_ATTRIBUTE: {
				EMFAttribute emfAttribute = (EMFAttribute)theEObject;
				T result = caseEMFAttribute(emfAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.EMF_REFERENCE: {
				EMFReference emfReference = (EMFReference)theEObject;
				T result = caseEMFReference(emfReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.TEMPLATE_FILE: {
				TemplateFile templateFile = (TemplateFile)theEObject;
				T result = caseTemplateFile(templateFile);
				if (result == null) result = caseReference(templateFile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.TEMPLATE: {
				Template template = (Template)theEObject;
				T result = caseTemplate(template);
				if (result == null) result = caseJavaElement(template);
				if (result == null) result = caseReference(template);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModeldocPackage.METRIC: {
				Metric metric = (Metric)theEObject;
				T result = caseMetric(metric);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Documentation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Documentation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelDocumentation(ModelDocumentation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EMF Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EMF Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEMFClass(EMFClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Additional Documentation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Additional Documentation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdditionalDocumentation(AdditionalDocumentation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Additional Latex</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Additional Latex</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdditionalLatex(AdditionalLatex object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Semantic</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Semantic</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSemantic(Semantic object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Example</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Example</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExample(Example object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operational Semantic</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operational Semantic</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationalSemantic(OperationalSemantic object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graphical Representation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graphical Representation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphicalRepresentation(GraphicalRepresentation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Inference Semantic</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Inference Semantic</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInferenceSemantic(InferenceSemantic object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Implementation Note</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Implementation Note</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImplementationNote(ImplementationNote object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraint(Constraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Extension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelExtension(ModelExtension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReference(Reference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Java Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Java Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJavaClass(JavaClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Java Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Java Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJavaMethod(JavaMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelReference(ModelReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Drools Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Drools Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDroolsRule(DroolsRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Drools Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Drools Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDroolsPackage(DroolsPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFileReference(FileReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Line Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Line Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFileLineReference(FileLineReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Javadoc Fragment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Javadoc Fragment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJavadocFragment(JavadocFragment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Javadoc Tag Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Javadoc Tag Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJavadocTagElement(JavadocTagElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Javadoc Text Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Javadoc Text Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJavadocTextElement(JavadocTextElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Java Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Java Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJavaElement(JavaElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Javadoc Method Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Javadoc Method Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJavadocMethodReference(JavadocMethodReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Javadoc Class Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Javadoc Class Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJavadocClassReference(JavadocClassReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EMF Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EMF Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEMFAttribute(EMFAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EMF Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EMF Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEMFReference(EMFReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplateFile(TemplateFile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplate(Template object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Metric</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Metric</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetric(Metric object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ModeldocSwitch
