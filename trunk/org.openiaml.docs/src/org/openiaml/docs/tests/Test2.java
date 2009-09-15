/**
 * 
 */
package org.openiaml.docs.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openarchitectureware.expression.ast.DeclaredParameter;
import org.openarchitectureware.expression.ast.Identifier;
import org.openarchitectureware.xtend.ast.Check;
import org.openarchitectureware.xtend.ast.Extension;
import org.openarchitectureware.xtend.ast.ExtensionFile;
import org.openarchitectureware.xtend.parser.ParseFacade;
import org.openiaml.docs.modeldoc.Constraint;
import org.openiaml.docs.modeldoc.ConstraintType;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.FileLineReference;
import org.openiaml.docs.modeldoc.FileReference;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModelExtension;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.visual.VisualPackage;

/**
 * Tries to generate a model documentation instance from IAML.
 * 
 * @author jmwright
 *
 */
public class Test2 extends TestCase {

	public ModelDocumentation createDocumentation() throws Exception {
		ModeldocFactory factory = ModeldocFactory.eINSTANCE;
		
		ModelDocumentation root = factory.createModelDocumentation();
		
		// load all EMF classes
		loadEMFClasses(ModelPackage.eINSTANCE, factory, root);

		// get all constraints
		loadOAWChecks(factory, root);
		
		// get all model extensions
		loadOAWExtensions(factory, root);
	
		return root;
	}
	
	/**
	 * Load all of the OAW constraints.
	 */
	private void loadOAWChecks(ModeldocFactory factory, ModelDocumentation root) throws Exception {
		String checkFile = "../org.openiaml.model.codegen.oaw/src/metamodel/Checks.chk";
		InputStream in = new FileInputStream(checkFile);
		
		FileReference fr = factory.createFileReference();
		fr.setPackage("org.openiaml.model.codegen.oaw");
		fr.setPackage("src.metamodel");
		fr.setName("Checks.chk");
		fr.setParent(root);
		
		ExtensionFile file = ParseFacade.file(new InputStreamReader(in), checkFile);
		
		for (Check check : file.getChecks()) {
			
			// map the Identifier (the context) to an EMFClass
			EMFClass identifier = mapOAWIdentifier(root, check.getType());
			if (identifier == null)
				continue;	// unidentified type (e.g. emf::EObject)

			// make a new Constraint for this check
			Constraint constraint = factory.createConstraint();
			constraint.setConstraint(check.getConstraint().toString());
			constraint.setType( check.isErrorCheck() ? ConstraintType.ERROR : ConstraintType.WARNING );
			constraint.setMessage( check.getMsg().toString() );

			// make a new FileReference
			FileLineReference line = factory.createFileLineReference();
			line.setFile(fr);
			line.setLine(check.getLine());
			constraint.setReference(fr);
			
			// add this constraint
			identifier.getConstraints().add(constraint);
			
			System.out.println(check);
			System.out.println(identifier);
		}
	}

	
	/**
	 * Load all of the OAW extensions.
	 */
	private void loadOAWExtensions(ModeldocFactory factory, ModelDocumentation root) throws Exception {
		String checkFile = "../org.openiaml.model.codegen.oaw/src/metamodel/Extensions.ext";
		InputStream in = new FileInputStream(checkFile);
		
		FileReference fr = factory.createFileReference();
		fr.setPackage("org.openiaml.model.codegen.oaw");
		fr.setPackage("src.metamodel");
		fr.setName("Extensions.ext");
		fr.setParent(root);
		
		ExtensionFile file = ParseFacade.file(new InputStreamReader(in), checkFile);
		
		for (Extension ext : file.getExtensions()) {
		
			// is there at least one type?
			if (ext.getFormalParameters() != null && ext.getFormalParameters().size() > 0) {
				
				// map the Type (the first parameter) to an EMFClass
				EMFClass identifier = mapOAWType(root, ext.getFormalParameters().get(0));				
				if (identifier == null)
					continue;	// unidentified type (e.g. emf::EObject)
				
				// make a new Extension
				ModelExtension extension = factory.createModelExtension();
				extension.setName(ext.getName());
				extension.setValue(ext.toString());
				
				// make a new FileReference
				FileLineReference line = factory.createFileLineReference();
				line.setFile(fr);
				line.setLine(ext.getLine());
				extension.setReference(fr);

				// add this extension
				identifier.getExtensions().add(extension);

			}
		}
	}

	/**
	 * @param root
	 * @param declaredParameter
	 * @return
	 */
	private EMFClass mapOAWType(ModelDocumentation root, DeclaredParameter declaredParameter) {
		return mapOAWIdentifier(root, declaredParameter.getType());
	}

	/**
	 * Map the Identifier (the context) to an EMFClass in the root.
	 * 
	 * @param type
	 * @return
	 */
	private EMFClass mapOAWIdentifier(ModelDocumentation root, Identifier type) {
		for (EMFClass cls : root.getClasses()) {
			if (identifierMatches(type, cls.getTargetClass())) {
				return cls;
			}
		}
		
		// could not find
		System.err.println("Could not identify type '" + type + "'");
		return null;
	}

	/**
	 * @param type
	 * @param cls
	 * @return
	 */
	private boolean identifierMatches(Identifier type, EClass ref) {
		String[] bits = type.getValue().split("::");
		
		if (ref.getName().equals(bits[bits.length - 1])) {
			// same name
			EPackage currentPackage = ref.getEPackage();
			for (int i = bits.length - 2; i >= 0; i--) {
				if (currentPackage == null) {
					// bail 
					return false;
				}
				if (!currentPackage.getName().equals(bits[i])) {
					// bail
					return false;
				}
				currentPackage = currentPackage.getESuperPackage();
			}
			
			// we should be at the root of the package heirarchy
			if (currentPackage != null)
				return false;
			
			// everything matches!
			return true;
		}
		
		return false;
	}
	
	public void testIdentifierMatches() {
		
		assertTrue(identifierMatches(new Identifier("model::InternetApplication"), ModelPackage.eINSTANCE.getInternetApplication()));
		assertTrue(identifierMatches(new Identifier("model::visual::Page"), VisualPackage.eINSTANCE.getPage()));
		assertFalse(identifierMatches(new Identifier("model::InternetApplication"), VisualPackage.eINSTANCE.getPage()));
		assertFalse(identifierMatches(new Identifier("model::visual::Page"), ModelPackage.eINSTANCE.getInternetApplication()));
		
	}

	/**
	 * Load all EMF classes in the given package into the given documentation.
	 * 
	 * @param root
	 */
	protected void loadEMFClasses(EPackage pkg, ModeldocFactory factory, ModelDocumentation root) {
		for (EClassifier classifier : pkg.getEClassifiers()) {
			if (classifier instanceof EClass) {
				
				EClass cls = (EClass) classifier;
				
				EMFClass c = factory.createEMFClass();
				c.setTargetClass(cls);
				c.setDescription("TODO Description");
				c.setTagline("TODO Tagline");
				c.setParent(root);
				
				// link up the source java class
				getJavaClassFor(c, cls, factory, root);
				
			}
		}
	
		// load all subpackages
		for (EPackage sub : pkg.getESubpackages()) {
			loadEMFClasses(sub, factory, root);
		}
	}
	
	/**
	 * Link up a Java reference for the given EMFClass.
	 * 
	 * @param c
	 * @param cls
	 * @param factory
	 * @param root
	 */
	protected void getJavaClassFor(EMFClass c, EClass cls,
			ModeldocFactory factory, ModelDocumentation root) {
		
		JavaClass jc = factory.createJavaClass();
		jc.setPlugin("org.openiaml.model");				
		jc.setPackage("org.openiaml.model." + getJavaPackageFor(cls.getEPackage()));
		jc.setName(cls.getName());
		jc.setParent(root);
		
		c.setRuntimeClass(jc);
		
	}
	
	private String getJavaPackageFor(EPackage c) {
		if (c == null)
			return "";
		String parent = getJavaPackageFor(c.getESuperPackage());
		
		return parent + (parent.isEmpty() ? "" : ".") + c.getName();
	}

	/**
	 * Tries to create a new instance of ModelDocumentation and
	 * save it to a file.
	 * 
	 * @throws Exception
	 */
	public void testDump() throws Exception {
		EObject root = createDocumentation();
		
		ResourceSet resourceSet = new ResourceSetImpl();
        URI fileURI = URI.createFileURI(new File("test.modeldoc")
                .getAbsolutePath());
        Resource resource = resourceSet.createResource(fileURI);
        resource.getContents().add(root);
        resource.save(Collections.EMPTY_MAP);
	}
	
	/*
	public void testNewModel() throws Exception {
		
		// get the EClass from IAML
		EClass page = VisualPackage.eINSTANCE.getPage();
		
		EMFClass c = ModeldocFactory.eINSTANCE.createEMFClass();
		c.setTargetClass(page);

		
        
        // try loading it!
		EMFClass c2 = null;
		{
			ResourceSet resourceSet = new ResourceSetImpl();
	        URI fileURI = URI.createFileURI(new File("test.modeldoc")
	                .getAbsolutePath());
	        Resource resource = resourceSet.getResource(fileURI, true);
	        c2 = (EMFClass) resource.getContents().get(0);
		}
		
		assertEquals(page, c2.getTargetClass());
		System.out.println(c2.getTargetClass());
		System.out.println(((EClass) c2.getTargetClass()).getEAllContainments());
        
	}
	*/
		
}
