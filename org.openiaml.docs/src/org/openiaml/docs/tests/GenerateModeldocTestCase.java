/**
 * 
 */
package org.openiaml.docs.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
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
import org.openiaml.docs.generation.LoadFileMetrics;
import org.openiaml.docs.generation.TranslateHTMLToLatex;
import org.openiaml.docs.generation.codegen.ModeldocCodeGenerator;
import org.openiaml.docs.modeldoc.DroolsPackage;
import org.openiaml.docs.modeldoc.DroolsRule;
import org.openiaml.docs.modeldoc.EMFAttribute;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.EMFReference;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.JavaElement;
import org.openiaml.docs.modeldoc.JavaMethod;
import org.openiaml.docs.modeldoc.JavadocFragment;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.JavadocTextElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.Reference;
import org.openiaml.docs.modeldoc.TemplateFile;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;
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
		// first, translate HTML to LaTeX
		testTranslateHTMLToLaTeX();
		
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
		IStatus status = codegen.generateCode(modelFile);
		assertTrue("Status was not OK: " + status, status.isOK());
		
	}
	
	/**
	 * Translates all of the HTML additional documentation into
	 * LaTeX format, overwriting the contents if necessary. 
	 * 
	 * @throws Exception
	 */
	public void testTranslateHTMLToLaTeX() throws Exception {
		File root = new File("../org.openiaml.model/model/docs/");
		assertTrue(root + " should exist", root.exists());
		assertTrue(root + " should be a directory", root.isDirectory());
		
		TranslateHTMLToLatex converter = new TranslateHTMLToLatex();
		
		// for each .html file
		for (File html : root.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".html");
			}} )) {

			// translate it
			System.out.println("Reading in " + html.getName() + "...");
			try {
				String output = converter.convertToTex(html);
			
				// write it out
				File tex = new File(html.getAbsolutePath().replace(".html", ".tex"));
				System.out.println("Writing to " + tex.getName() + "...");
				
				FileWriter writer = new FileWriter(tex);
				writer.write(output);
				writer.close();
			} catch (Exception e) {
				throw new Exception("Could not translate " + html.getName() + ": " + e.getMessage(), e);
			}
		}
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
	
	public void testFileMetricsMethods() {
		assertEquals("txt", LoadFileMetrics.getFileExtension(new File("foo.txt")));
		assertEquals("txt", LoadFileMetrics.getFileExtension(new File("foo.TXT")));
		assertEquals("none", LoadFileMetrics.getFileExtension(new File("foo.")));
		assertEquals("none", LoadFileMetrics.getFileExtension(new File("foo")));
	}
	
	/**
	 * Tests {@link ModeldocCodeGenerator#formatFloat(String)}.
	 */
	public void testFormatFloat() {
		assertEquals("test", ModeldocCodeGenerator.formatFloat("test"));
		assertEquals("123.45", ModeldocCodeGenerator.formatFloat("123.45"));
		assertEquals("123.40", ModeldocCodeGenerator.formatFloat("123.4"));
		assertEquals("123", ModeldocCodeGenerator.formatFloat("123"));
		assertEquals("123.00", ModeldocCodeGenerator.formatFloat("123.00"));
		assertEquals("0.01", ModeldocCodeGenerator.formatFloat("0.01"));
		assertEquals("0", ModeldocCodeGenerator.formatFloat("0"));
		assertEquals("0.00", ModeldocCodeGenerator.formatFloat("0.000000"));
		assertEquals("0.01", ModeldocCodeGenerator.formatFloat("0.009"));
		assertEquals("9999990", ModeldocCodeGenerator.formatFloat("9999990"));
		assertEquals("9,999,990.00", ModeldocCodeGenerator.formatFloat("9999990.0"));
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
		// get EObject path
		EObject cur = tag;
		String parentPath = "";
		while (cur != null) {
			String extra = " ";
			if (cur instanceof JavaClass) {
				extra = ((JavaClass) cur).getName();
			} else if (cur instanceof TemplateFile) {
				extra = ((TemplateFile) cur).getName();
			} else if (cur instanceof DroolsPackage) {
				extra = ((DroolsPackage) cur).getName();
			}
			parentPath = " > " + cur.eClass().getName() + "[" + extra + "]" + parentPath; 				
			cur = cur.eContainer();
		}
		
		if (tag.getJavaParent() == null) {
			return tag.toString() + parentPath;
		} else {
			return getDescription(tag.getJavaParent()) + " @ " + tag + parentPath;
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
	
	/**
	 * Issue 167: make sure all model elements have ModelDoc. 
	 * @throws IOException 
	 */
	public void testAllElementsHaveJavadocs() throws ModelLoadException, IOException {
		
		EObject root = ModelLoader.load("test.modeldoc");
		ModelDocumentation doc = (ModelDocumentation) root;
		
		List<String> violations = new ArrayList<String>();
		
		for (EMFClass cls : doc.getClasses()) {
			if (isTaglineEmpty(cls.getTagline())) {
				violations.add("Class: " + cls.getName());
			}
			
			for (EMFReference ref : cls.getReferences()) {
				if (isTaglineEmpty(ref.getTagline())) {
					violations.add("Reference: " + cls.getName() + "#" + ref.getName());
				}
			}
			
			for (EMFAttribute attr : cls.getAttributes()) {
				if (isTaglineEmpty(attr.getTagline())) {
					violations.add("Attribute: " + cls.getName() + "#" + attr.getName());
				}
			}

		}
		
		// print out all violations for easy debugging
		StringBuffer buf = new StringBuffer();
		for (String v : violations) {
			System.out.println(v);
			buf.append(v).append("\n");
		}
		File f = new File("missing-emf-documentation.txt");
		FileWriter writer = new FileWriter(f);
		writer.write(buf.toString());
		writer.close();
		
		assertEquals("Not all model elements had documentation, written to " + f, 0, violations.size());
				
	}
	
	/**
	 * Issue 240: Make sure that all inference rules have associated
	 * documentation.
	 * 
	 * @throws IOException 
	 */
	public void testAllInferenceRulesHaveModeldoc() throws ModelLoadException, IOException {
		
		EObject root = ModelLoader.load("test.modeldoc");
		ModelDocumentation doc = (ModelDocumentation) root;

		StringBuffer buf = new StringBuffer();
		int violations = 0;
		for (Reference ref : doc.getReferences()) {
			if (ref instanceof DroolsPackage) {
				DroolsPackage pkg = (DroolsPackage) ref;
				
				// get the count of all @inference tags
				// (we need to ignore tags such as @implementation)
				int inferenceTags = getInferenceTags(pkg.getRules());
				
				// count the unique number of all rules
				Set<String> allRules = new HashSet<String>();
				for (DroolsRule r : pkg.getRules()) {
					allRules.add(r.getName());
				}
				
				// ModelDoc does not load all rules, but only
				// those with Javadoc tags. therefore, we can't
				// check that each rule has documentation; but rather,
				// check that the number of rules saved == the number
				// of unique rules in the package
				if (pkg.getUniqueRules() != inferenceTags) {
					violations++;
					buf.append("Package ")
						.append(pkg.getPlugin())
						.append("/")
						.append(pkg.getPackage())
						.append("/")
						.append(pkg.getName())
						.append(".drl: Expected ")
						.append(pkg.getUniqueRules())
						.append(" tags for ")
						.append(allRules.size())
						.append(" rules, found ")
						.append(inferenceTags)
						.append(" @inference tags out of ")
						.append(pkg.getRules().size())
						.append(" total tags")
						.append("\n");
				}
				
			}
		}
		
		// print out all violations for easy debugging
		File f = new File("missing-inference-documentation.txt");
		FileWriter writer = new FileWriter(f);
		writer.write(buf.toString());
		writer.close();
		System.out.println(buf.toString());
	
		assertEquals("Not all inference rules had documentation, written to " + f, 0, violations);
				
	}

	/**
	 * Get a count of all tags that are of type @inference, 
	 * restricted to unique rule names.
	 * 
	 * @param rules
	 * @return
	 */
	private int getInferenceTags(EList<DroolsRule> rules) {
		Set<String> rulesWithInferenceTags = new HashSet<String>();
		
		for (DroolsRule r : rules) {
			for (JavadocTagElement j : r.getJavadocs()) {
				if ("@inference".equals(j.getName())) {
					rulesWithInferenceTags.add(r.getName());
				}
			}
		}
		
		return rulesWithInferenceTags.size();
	}

	/**
	 * @param tagline
	 * @return
	 */
	private boolean isTaglineEmpty(JavadocTagElement tagline) {
		if (tagline == null)
			return true;
		
		StringBuffer buf = new StringBuffer();
		for (JavadocFragment f : tagline.getFragments()) {
			if (f instanceof JavadocTextElement) {
				buf.append(((JavadocTextElement) f).getValue());
			} else if (f instanceof JavadocTagElement) {
				buf.append(((JavadocTagElement) f).getName());
			}
		}
		
		return buf.toString().trim().isEmpty();
	}
	
	/**
	 * Check that all inference rules have expected @modeldoc
	 * tags.
	 * 
	 * @throws IOException 
	 */
	public void testAlInferenceRulesUseExpectedModeldocTags() throws ModelLoadException, IOException {
		
		// expected tags
		List<String> expectedTags = Arrays.asList(
				"@inference", 
				"@implementation", 
				"@notModelCompletion");
		
		EObject root = ModelLoader.load("test.modeldoc");
		ModelDocumentation doc = (ModelDocumentation) root;

		for (Reference ref : doc.getReferences()) {
			if (ref instanceof DroolsPackage) {
				DroolsPackage pkg = (DroolsPackage) ref;
				
				for (DroolsRule r : pkg.getRules()) {
					for (JavadocTagElement j : r.getJavadocs()) {
						assertTrue("Expected tags did not contain " + j.getName(), 
								expectedTags.contains(j.getName()));
					}
				}

			}
		}
		
	}
	
}
