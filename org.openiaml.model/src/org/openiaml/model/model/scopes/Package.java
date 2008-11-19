/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.scopes;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.openiaml.model.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.scopes.Factory
 * @model kind="package"
 *        annotation="http://openiaml.org/comment added='0.2' comment='other scopes: iaml:InternetApplication, [...], iaml.visual:Page, iaml.visual:Frame, iaml:ApplicationElement, iaml:Operation' comment2='(it makes sense to put these scopes in here, because they are scoped in terms of visual terms)' comment3='some scopes aren\'t here: Host, Organisation, we can add these in later.'"
 * @generated
 */
public interface Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "scopes";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://openiaml.org/model/scopes";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "iaml.scopes";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Package eINSTANCE = org.openiaml.model.model.scopes.impl.PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.scopes.Scope <em>Scope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.scopes.Scope
	 * @see org.openiaml.model.model.scopes.impl.PackageImpl#getScope()
	 * @generated
	 */
	int SCOPE = 0;

	/**
	 * The feature id for the '<em><b>Domain Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE__DOMAIN_OBJECTS = 0;

	/**
	 * The feature id for the '<em><b>Domain Views</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE__DOMAIN_VIEWS = 1;

	/**
	 * The feature id for the '<em><b>Domain Instances</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE__DOMAIN_INSTANCES = 2;

	/**
	 * The number of structural features of the '<em>Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.scopes.impl.SiteScopeImpl <em>Site Scope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.scopes.impl.SiteScopeImpl
	 * @see org.openiaml.model.model.scopes.impl.PackageImpl#getSiteScope()
	 * @generated
	 */
	int SITE_SCOPE = 1;

	/**
	 * The feature id for the '<em><b>Generated By</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE__GENERATED_BY = ModelPackage.NAMED_ELEMENT__GENERATED_BY;

	/**
	 * The feature id for the '<em><b>Is Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE__IS_GENERATED = ModelPackage.NAMED_ELEMENT__IS_GENERATED;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE__ID = ModelPackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE__NAME = ModelPackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Domain Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE__DOMAIN_OBJECTS = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Domain Views</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE__DOMAIN_VIEWS = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Domain Instances</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE__DOMAIN_INSTANCES = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE__OPERATIONS = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Sessions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE__SESSIONS = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE__COMPONENTS = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Site Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_SCOPE_FEATURE_COUNT = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.scopes.impl.SessionImpl <em>Session</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.scopes.impl.SessionImpl
	 * @see org.openiaml.model.model.scopes.impl.PackageImpl#getSession()
	 * @generated
	 */
	int SESSION = 2;

	/**
	 * The feature id for the '<em><b>Generated By</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__GENERATED_BY = ModelPackage.NAMED_ELEMENT__GENERATED_BY;

	/**
	 * The feature id for the '<em><b>Is Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__IS_GENERATED = ModelPackage.NAMED_ELEMENT__IS_GENERATED;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__ID = ModelPackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__NAME = ModelPackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Domain Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__DOMAIN_OBJECTS = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Domain Views</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__DOMAIN_VIEWS = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Domain Instances</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__DOMAIN_INSTANCES = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__OPERATIONS = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__AGENT = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__COMPONENTS = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Session</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_FEATURE_COUNT = ModelPackage.NAMED_ELEMENT_FEATURE_COUNT + 6;


	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.scopes.Scope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope</em>'.
	 * @see org.openiaml.model.model.scopes.Scope
	 * @generated
	 */
	EClass getScope();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.scopes.Scope#getDomainObjects <em>Domain Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Domain Objects</em>'.
	 * @see org.openiaml.model.model.scopes.Scope#getDomainObjects()
	 * @see #getScope()
	 * @generated
	 */
	EReference getScope_DomainObjects();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.scopes.Scope#getDomainViews <em>Domain Views</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Domain Views</em>'.
	 * @see org.openiaml.model.model.scopes.Scope#getDomainViews()
	 * @see #getScope()
	 * @generated
	 */
	EReference getScope_DomainViews();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.scopes.Scope#getDomainInstances <em>Domain Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Domain Instances</em>'.
	 * @see org.openiaml.model.model.scopes.Scope#getDomainInstances()
	 * @see #getScope()
	 * @generated
	 */
	EReference getScope_DomainInstances();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.scopes.SiteScope <em>Site Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Site Scope</em>'.
	 * @see org.openiaml.model.model.scopes.SiteScope
	 * @generated
	 */
	EClass getSiteScope();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.scopes.SiteScope#getSessions <em>Sessions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sessions</em>'.
	 * @see org.openiaml.model.model.scopes.SiteScope#getSessions()
	 * @see #getSiteScope()
	 * @generated
	 */
	EReference getSiteScope_Sessions();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.scopes.SiteScope#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Components</em>'.
	 * @see org.openiaml.model.model.scopes.SiteScope#getComponents()
	 * @see #getSiteScope()
	 * @generated
	 */
	EReference getSiteScope_Components();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.scopes.Session <em>Session</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Session</em>'.
	 * @see org.openiaml.model.model.scopes.Session
	 * @generated
	 */
	EClass getSession();

	/**
	 * Returns the meta object for the containment reference '{@link org.openiaml.model.model.scopes.Session#getAgent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Agent</em>'.
	 * @see org.openiaml.model.model.scopes.Session#getAgent()
	 * @see #getSession()
	 * @generated
	 */
	EReference getSession_Agent();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.scopes.Session#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Components</em>'.
	 * @see org.openiaml.model.model.scopes.Session#getComponents()
	 * @see #getSession()
	 * @generated
	 */
	EReference getSession_Components();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Factory getFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.scopes.Scope <em>Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.scopes.Scope
		 * @see org.openiaml.model.model.scopes.impl.PackageImpl#getScope()
		 * @generated
		 */
		EClass SCOPE = eINSTANCE.getScope();

		/**
		 * The meta object literal for the '<em><b>Domain Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE__DOMAIN_OBJECTS = eINSTANCE.getScope_DomainObjects();

		/**
		 * The meta object literal for the '<em><b>Domain Views</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE__DOMAIN_VIEWS = eINSTANCE.getScope_DomainViews();

		/**
		 * The meta object literal for the '<em><b>Domain Instances</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE__DOMAIN_INSTANCES = eINSTANCE.getScope_DomainInstances();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.scopes.impl.SiteScopeImpl <em>Site Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.scopes.impl.SiteScopeImpl
		 * @see org.openiaml.model.model.scopes.impl.PackageImpl#getSiteScope()
		 * @generated
		 */
		EClass SITE_SCOPE = eINSTANCE.getSiteScope();

		/**
		 * The meta object literal for the '<em><b>Sessions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE_SCOPE__SESSIONS = eINSTANCE.getSiteScope_Sessions();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE_SCOPE__COMPONENTS = eINSTANCE.getSiteScope_Components();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.scopes.impl.SessionImpl <em>Session</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.scopes.impl.SessionImpl
		 * @see org.openiaml.model.model.scopes.impl.PackageImpl#getSession()
		 * @generated
		 */
		EClass SESSION = eINSTANCE.getSession();

		/**
		 * The meta object literal for the '<em><b>Agent</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SESSION__AGENT = eINSTANCE.getSession_Agent();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SESSION__COMPONENTS = eINSTANCE.getSession_Components();

	}

} //Package
