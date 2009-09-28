/**
 * 
 */
package org.openiaml.docs.tests;

import java.io.File;
import java.util.Collections;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openarchitectureware.expression.ast.Identifier;
import org.openiaml.docs.generation.DocumentationGenerationException;
import org.openiaml.docs.generation.DocumentationGenerator;
import org.openiaml.docs.generation.DocumentationHelper;
import org.openiaml.docs.modeldoc.ModelDocumentation;
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

	public void testIdentifierMatches() {
		
		DocumentationHelper helper = new DocumentationHelper() {

			public void load(ModeldocFactory factory, ModelDocumentation root)
					throws DocumentationGenerationException {
				// do nothing
			}
			
		};
		
		assertTrue(helper.identifierMatches(new Identifier("model::InternetApplication"), ModelPackage.eINSTANCE.getInternetApplication()));
		assertTrue(helper.identifierMatches(new Identifier("model::visual::Page"), VisualPackage.eINSTANCE.getPage()));
		assertFalse(helper.identifierMatches(new Identifier("model::InternetApplication"), VisualPackage.eINSTANCE.getPage()));
		assertFalse(helper.identifierMatches(new Identifier("model::visual::Page"), ModelPackage.eINSTANCE.getInternetApplication()));
		
	}

	/**
	 * Use the {@link DocumentationGenerator} to generate the model documentation.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ModelDocumentation createDocumentation() throws Exception {
		DocumentationGenerator gen = new DocumentationGenerator();
		return gen.createDocumentation();
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
		File modelFile = new File("test.modeldoc");
        URI fileURI = URI.createFileURI(modelFile
                .getAbsolutePath());
        Resource resource = resourceSet.createResource(fileURI);
        resource.getContents().add(root);
        resource.save(Collections.EMPTY_MAP);
      
		// generate code
		OawCodeGenerator codegen = new OawCodeGenerator();
		codegen.generateCode(modelFile);
        
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
