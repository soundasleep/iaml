/**
 * 
 */
package org.openiaml.docs.tests;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openarchitectureware.expression.ast.Identifier;
import org.openiaml.docs.generation.BasicJavadocParser;
import org.openiaml.docs.generation.DocumentationGenerationException;
import org.openiaml.docs.generation.DocumentationGenerator;
import org.openiaml.docs.generation.DocumentationHelper;
import org.openiaml.docs.generation.codegen.ModeldocCodeGenerator;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.JavaElement;
import org.openiaml.docs.modeldoc.JavaMethod;
import org.openiaml.docs.modeldoc.JavadocFragment;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.JavadocTextElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.tests.model.ContainmentTestCase;

/**
 * Tries to generate a model documentation instance from IAML.
 * 
 * @author jmwright
 *
 */
public class GenerateModeldocTestCase extends TestCase {

	public void testIdentifierMatches() {
		
		DocumentationHelper helper = new DocumentationHelper() {

			public void load(ModeldocFactory factory, ModelDocumentation root)
					throws DocumentationGenerationException {
				// do nothing
			}
			
		};
		
		assertTrue(helper.identifierMatches(new Identifier("model::InternetApplication"), ModelPackage.eINSTANCE.getInternetApplication()));
		assertTrue(helper.identifierMatches(new Identifier("model::visual::Frame"), VisualPackage.eINSTANCE.getFrame()));
		assertFalse(helper.identifierMatches(new Identifier("model::InternetApplication"), VisualPackage.eINSTANCE.getFrame()));
		assertFalse(helper.identifierMatches(new Identifier("model::visual::Frame"), ModelPackage.eINSTANCE.getInternetApplication()));
		
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
	 * save it to a file. It also executes the actual documentation
	 * generation through OAW.
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
		ModeldocCodeGenerator codegen = new ModeldocCodeGenerator();
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
	
	public class BasicJavadocParserMock extends BasicJavadocParser {

		public BasicJavadocParserMock() {
			super(null);
		}
		public String[] getJavadocTagsMock(String[] lines, int line) {
			return getJavadocTags(lines, line);
		}
		
	}
	
	/**
	 * Test the extraction of Javadoc block comments.
	 */
	public void testJavadocBlockExtract() {
		BasicJavadocParserMock mock = new BasicJavadocParserMock();
		String[] result = mock.getJavadocTagsMock(
				new String[] {
						"foo",
						"bar",
						"/**",
						" * ",
						" * @foo bar ",
						" */"
				},
				2
		);
		assertEquals(result.length, 1);
		assertEquals("@foo bar", result[0]);
		
		result = mock.getJavadocTagsMock(
				new String[] {
						"foo",
						"bar",
						"/**",
						" * ",
						" * @foo bar ",
						" * @bar foo ",
						" */"
				},
				2
		);
		assertEquals(result.length, 2);
		assertEquals("@foo bar", result[0]);
		assertEquals("@bar foo", result[1]);
		
		result = mock.getJavadocTagsMock(
				new String[] {
						"foo",
						"bar",
						"/**",
						" * ",
						" * @foo bar ",
						" *  multiple line",
						" * @bar foo ",
						" */"
				},
				2
		);
		assertEquals(result.length, 2);
		assertEquals("@foo bar multiple line", result[0]);
		assertEquals("@bar foo", result[1]);
		
		result = mock.getJavadocTagsMock(
				new String[] {
						"foo",
						"bar",
						"/**",
						" * ignore this ",
						" * @foo {@model kittens} bar",
						" *\tmultiple line",
						" *\tmultiple line",
						" * @bar foo",
						" *\tmultiple line",
						" */"
				},
				2
		);
		assertEquals(result.length, 2);
		assertEquals("@foo {@model kittens} bar multiple line multiple line", result[0]);
		assertEquals("@bar foo multiple line", result[1]);				
	}
	
	/**
	 * Load the generated .modeldoc and check that all <code>@model</code> links
	 * are valid.
	 * 
	 * @throws Exception
	 */
	public void testAllModelLinksAreValid() throws Exception {
		
	 	EObject root = ModelLoader.load("test.modeldoc");
	 	
	 	Map<String,String> foundModelElements = new HashMap<String,String>();
	 	
	 	// find all md:JavadocTagElement
	 	Iterator<EObject> it = root.eAllContents();
	 	while (it.hasNext()) {
	 		EObject obj = it.next();
	 		
	 		if (obj instanceof JavadocTagElement) {
	 			JavadocTagElement tag = (JavadocTagElement) obj;
	 			if ("@model".equals(tag.getName())) {
	 				// get the description
	 				String desc = "";
	 				for (JavadocFragment frag : tag.getFragments()) {
	 					if (frag instanceof JavadocTextElement) {
	 						desc += ((JavadocTextElement) frag).getValue();
	 					}
	 				}
	 				
	 				// get out the class name
	 				String className = desc;
	 				if (className.contains(" ")) {
	 					String[] split = className.trim().split(" ", 2);
	 					className = split[0];
	 				}
	 				
	 				// skip any hash tags
	 				if (className.contains("#")) {
	 					String[] split = className.trim().split("#", 2);
	 					className = split[0];
	 				}
	 				
	 				String description = getDescription(tag);
	 				
	 				// put this class name into the hash set
	 				foundModelElements.put(className, description);	 				
	 			}
	 		}
	 	}

	 	// should have found at least one @model tag
	 	assertFalse("Should have found at least one @model tag", foundModelElements.isEmpty());

	 	// now, cycle through the found elements and check that the IAML
	 	// element exists
	 	Map<EClass, EFactory> exists = ContainmentTestCase.getAllClassesIncludingAbstract();
	 	// convert to a set of strings
	 	Set<String> existsNames = new HashSet<String>();
	 	for (EClass cls : exists.keySet()) {
	 		existsNames.add(cls.getName());
	 	}
	 	
	 	Set<String> notFound = new HashSet<String>();
	 	for (String className : foundModelElements.keySet()) {
	 		if (!existsNames.contains(className)) {
	 			System.out.println(className + " => " + foundModelElements.get(className));
	 			notFound.add(className);
	 		}
	 	}
	 	
	 	assertEquals("Found some @model elements that could not be resolved: " + notFound, 0, notFound.size());
	 	
	}

	/**
	 * Compose a description of where a specific tag is in the workspace.
	 * 
	 * @param tag
	 * @return
	 */
	private String getDescription(JavadocTagElement tag) {
		if (tag.getJavaParent() == null) {
			return tag.toString();
		} else {
			return getDescription(tag.getJavaParent()) + " @ " + tag;
		}
	}

	private String getDescription(JavaElement p) {
		if (p instanceof JavaClass) {
			JavaClass jc = (JavaClass) p;
			return jc.getPlugin() + "/" + jc.getPackage() + "/" + jc.getName();
		} else if (p instanceof JavaMethod) {
			JavaMethod m = (JavaMethod) p;
			 return getDescription(m.getJavaClass()) + "#" + m.getName() + "()";
		}
		return p.toString();
	}
	
}
