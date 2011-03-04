/**
 * 
 */
package org.openiaml.model.owl.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import l3i.sido.emf4sw.ui.ecore2owl.Ecore2OWLFileAction;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.StructuredSelection;
import org.openiaml.model.tests.ModelTestCase;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.OWLFBRuleReasonerFactory;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

/**
 * Test for using Jena for model completion, in thesis chapter "technology".
 * 
 * @author jmwright
 *
 */
public class TechnologyChapterTest extends ModelTestCase {

	/**
	 * Takes simple.ecore and translates it into simple.owl.
	 * 
	 * @return The generated OWL schema.
	 */
	protected Model setupOwlTransform() throws Exception {

		// copy over ecore file
		File source = new File("model/technology.ecore");
		// File source = new File("model/simple.ecore");
		System.out.println(source.getAbsolutePath());
		assertTrue("Source file exists: " + source, source.exists());
		IFile target = getProject().getFile("technology.ecore");
		assertFalse("Target file should not exist: " + target, target.exists());
		copyFileIntoWorkspace(source, target);
		assertTrue("Target file should exist: " + target, target.exists());
		IFile transformed = getProject().getFile("technology.owl");
		assertFalse("Final file should not exist: " + transformed, transformed.exists());
		
		// try the transformation action
		Ecore2OWLFileAction action = new Ecore2OWLFileAction();
		action.selectionChanged(null, new StructuredSelection(target));
		action.run(null);
		
		// once run, the "target.owl" file should exist
		assertTrue("Final file should exist: " + transformed, transformed.exists());
		
		Model schema = FileManager.get().loadModel( transformed.getLocation().toString() );
		return schema;
	}
	
	public void testLogger() throws Exception {
		
		Logger.getLogger(TechnologyChapterTest.class).warn("test");
		Logger.getLogger(TechnologyChapterTest.class).info("test");
		Logger.getLogger(TechnologyChapterTest.class).debug("test");
	}
	
	/**
	 * Loading a default model instance to the OWL format is valid.
	 * 
	 * @throws Exception
	 */
	public void testDefaultModelIsValid() throws Exception {
		System.setProperty("log4j.configuration", "file:///" + new File("log4j.properties").getAbsolutePath());

		Model schema = setupOwlTransform();		
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPtraceOn, true);
		reasoner = reasoner.bindSchema(schema);
		
		// should be valid
		Model model = ModelFactory.createDefaultModel();
		InfModel inf = ModelFactory.createInfModel(reasoner, model);
		ValidityReport valid = inf.validate();
		assertIsValid(valid);
		
	}
	
	/**
	 * Assert that the given validity report is valid.
	 * 
	 * @param valid
	 */
	protected void assertIsValid(ValidityReport valid) {
		if (valid.isValid()) {
			if (valid.isClean()) {
				// ok
			} else {
				String message = printReports(valid);
				fail("ValidityReport was valid but not clean: " + message);
			}
		} else {
			String message = printReports(valid);
			fail("ValidityReport was not valid: " + message);
		}
	}	
	
	/**
	 * Assert that the given validity report is not valid.
	 * 
	 * @param valid
	 */
	protected void assertNotValid(ValidityReport valid) {
		if (!valid.isValid()) {
			// ok
			// we can't test for clean-ness
		} else {
			String message = printReports(valid);
			fail("ValidityReport was valid: " + message);
		}
	}

	/**
	 * Print validity reports to stdout and return a message
	 * describing the status.
	 * 
	 * @param valid
	 * @return The first validity report
	 */
	private String printReports(ValidityReport valid) {
		Iterator<Report> it = valid.getReports();
		String message = null;
		while (it.hasNext()) {
			Report r = it.next();
			if (message == null)
				message = r.toString();
			System.out.println(r);
		}
		return message;
	}
	
	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 */
	protected EObject loadModelDirectly(String filename) {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(filename);
		org.eclipse.emf.ecore.resource.Resource resource = resourceSet.getResource(uri, true);
		assertNotNull(resource);
		assertEquals("there should only be one contents in the model file", 1, resource.getContents().size());
		return resource.getContents().get(0);
	}

	/**
	 * Test custom class to transform an EObject into .rdf.
	 * 
	 * @return 
	 * 
	 * @throws Exception
	 */
	public IFile testMyRdf() throws Exception {
		// copy over ecore file
		// File source = new File("../org.openiaml.model/model/iaml.ecore");
		File source = new File("tests/TechnologyTest.iamltest");
		assertTrue("Source file exists: " + source, source.exists());
		IFile target = getProject().getFile("TechnologyTest.iamltest");
		assertFalse("Target file should not exist: " + target, target.exists());
		copyFileIntoWorkspace(source, target);
		assertTrue("Target file should exist: " + target, target.exists());
		IFile transformed = getProject().getFile("TechnologyTest.rdf");
		assertFalse("Final file should not exist: " + transformed, transformed.exists());

		// load the model file
		EObject loaded = loadModelDirectly(source.getAbsolutePath());
		System.out.println(loaded);
		
		// transform to RDF
		Model rdf = new MyEcoreRDFWriter().transform(loaded);
		
		// write to file
		ByteArrayOutputStream pipe = new ByteArrayOutputStream();
		rdf.write(pipe);
		transformed.create(getInputStream(pipe), true, new NullProgressMonitor());
		
		// once run, the "target.rdf" file should exist
		assertTrue("Final file should exist: " + transformed, transformed.exists());
		
		// print out target.rdf
		printFile(transformed);
		
		return transformed;
		
	}
	
	/**
	 * Convert an OutputStream into an InputStream.
	 * 
	 * @param pipe
	 * @return
	 */
	private InputStream getInputStream(ByteArrayOutputStream pipe) {
		return new ByteArrayInputStream(pipe.toByteArray());
	}

	/**
	 * Try our custom EObject/RDF writer.
	 * 
	 * Tests that an InternetApplication should have at least 2 children;
	 * should pass.
	 * 
	 * @throws Exception
	 */
	public void testEObjectRdfValidation1() throws Throwable {
		try {
		
		// manually translate EMF instance into XML/RDF
		IFile rdf = testMyRdf();

		PrintUtil.registerPrefix("iaml", "http://openiaml.org/iaml.test#");
		Model model = FileManager.get().loadModel("file:" + rdf.getLocation().toString());
		
		// load rules
		File rulesFile = new File("rules/technology-test.txt");
		assertTrue("Rules file " + rulesFile + " should exist", rulesFile.exists());
		String rules = readFile(rulesFile);
		
		// create reasoner
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		// infer
		InfModel inf = ModelFactory.createInfModel(reason, model);
		ValidityReport valid = inf.validate();
		assertIsValid(valid);
		
		// write out new model
		File output = new File("tests/created.xml");
		inf.write(new FileOutputStream(output));
		
		// print out contents of generated file
		assertTrue("Output file " + output + " should exist", output.exists());
		System.out.println(readFile(output));
		
		// output is RDF/XML; still needs translation back into an
		// EMF instance. (future work)
	
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			t.printStackTrace();
			System.out.println(t.getClass());
			throw t;
		}
	}
	
	/**
	 * Print out the contents of the given IFile.
	 * 
	 * @param file
	 * @throws CoreException 
	 * @throws IOException 
	 */
	protected void printFile(IFile file) throws CoreException, IOException {
		System.out.println("[" + file + "]");
		InputStream s = file.getContents();
		int c;
		while ((c = s.read()) != -1) {
			System.out.print((char) c);
		}
		s.close();
		System.out.println();
	}
	
	/**
	 * <p>A class to take an arbitrary EMF instance and write it out
	 * as RDF, using only the information provided by EMF.</p>
	 * 
	 * <p>Compare this to an ATL information, which requires inputs of
	 * both the instance and the instance's EMF model, and lots of 
	 * hacks that don't correspond to the ATL philosophy.</p>
	 * 
	 * @author jmwright
	 *
	 */
	public static class MyEcoreRDFWriter {
		
		Map<EObject,Resource> resourceMap = new HashMap<EObject,Resource>();
		Map<EStructuralFeature,Property> featureMap = new HashMap<EStructuralFeature,Property>();
		
		public Model transform(EObject source) {
			Model model = ModelFactory.createDefaultModel();
			
			// default namespaces
			model.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
			model.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
			model.setNsPrefix("owl", "http://www.w3.org/2002/07/owl#");
			model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");

			// handle root
			handleEObject(model, source);
			
			// create the model recursively for all contents
			Iterator<EObject> contents = source.eAllContents();
			while (contents.hasNext()) {
				EObject obj = contents.next();
				handleEObject(model, obj);
			}
			
			return model;
		}
		
		private Property rdfType;
		
		/**
		 * Get the Property for rdf:type.
		 * 
		 * @param model
		 * @return
		 */
		protected Property getRdfTypeProperty(Model model) {
			if (rdfType == null) {
				rdfType = model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
			}
			return rdfType;
		}
		
		/**
		 * A statically-instantiated map of EMF types to XML schema types.
		 * From l3l.sido.emf4sw.transformations/ecore2owl.atl
		 */
		protected Map<String,String> typeMap = new HashMap<String,String>();		
		{
			typeMap.put("String", "http://www.w3.org/2001/XMLSchema#string");
			typeMap.put("Integer", "http://www.w3.org/2001/XMLSchema#int");
			typeMap.put("Boolean", "http://www.w3.org/2001/XMLSchema#boolean");
			typeMap.put("UnlimitedNatural", "http://www.w3.org/2001/XMLSchema#integer");
			typeMap.put("Byte", "http://www.w3.org/2001/XMLSchema#byte");
			typeMap.put("Currency", "http://www.w3.org/2001/XMLSchema#decimal");
			typeMap.put("Date", "http://www.w3.org/2001/XMLSchema#date");
			typeMap.put("Double", "http://www.w3.org/2001/XMLSchema#double");
			typeMap.put("Long", "http://www.w3.org/2001/XMLSchema#long");
			typeMap.put("Single", "http://www.w3.org/2001/XMLSchema#short");
			typeMap.put("Variant", "http://www.w3.org/2001/XMLSchema#string");
			typeMap.put("EString", "http://www.w3.org/2001/XMLSchema#string");
			typeMap.put("EInteger", "http://www.w3.org/2001/XMLSchema#int");
			typeMap.put("EInt", "http://www.w3.org/2001/XMLSchema#int");
		}
		
		protected void handleEObject(Model model, EObject obj) {
			Resource res = getResourceFor(model, obj);
			
			// give it an rdf:type
			res.addProperty(getRdfTypeProperty(model), model.createResource(obj.eClass().getEPackage().getNsURI() + "#" + obj.eClass().getName()));

			// all references (containment, references)
			for (EReference feature : obj.eClass().getEAllReferences()) {
				if (feature.isMany()) {
					for (EObject target : ((List<EObject>) obj.eGet(feature))) {
						Property property = getPredicateFor(model, feature);
						Resource targetRes = getResourceFor(model, target);
						
						res.addProperty(property, targetRes);
					}
				} else {
					if (obj.eGet(feature) != null) {
						Property property = getPredicateFor(model, feature);
						Resource targetRes = getResourceFor(model, (EObject) obj.eGet(feature));
						
						res.addProperty(property, targetRes);
					}
				}
			}
			
			// all attributes
			for (EAttribute attr : obj.eClass().getEAllAttributes()) {
				if (obj.eGet(attr) != null) {
					Property property = getPredicateFor(model, attr);
					
					String uri = typeMap.get(attr.getEAttributeType().getName());
					RDFDatatype type = TypeMapper.getInstance().getTypeByName(uri);
					String value = obj.eGet(attr).toString();
					res.addProperty(property, value, type);
				}
			}
		}
		
		/**
		 * Get an ID for the object. If the object has a property with eID = true,
		 * use this ID instead of generating a UUID.
		 * 
		 * @param obj
		 */
		protected String getID(EObject obj) {
			for (EAttribute attr : obj.eClass().getEAllAttributes()) {
				if (attr.isID() && obj.eGet(attr) != null) {
					return obj.eGet(attr).toString();
				}
			}
			
			return EcoreUtil.generateUUID();
		}
		
		protected Resource getResourceFor(Model model, EObject obj) {
			if (!resourceMap.containsKey(obj)) {
				// set ns prefix
				EPackage pkg = obj.eClass().getEPackage();
				model.setNsPrefix(pkg.getNsPrefix(), pkg.getNsURI() + "#");
				
				String uri = pkg.getNsURI() + "#" + getID(obj); 
				Resource res = model.createResource(uri);
				resourceMap.put(obj, res);
			}
			return resourceMap.get(obj);
		}
		
		public Property getPredicateFor(Model model, EStructuralFeature feature) {
			if (!featureMap.containsKey(feature)) {
				// set ns prefix
				EPackage pkg = feature.getEContainingClass().getEPackage();
				model.setNsPrefix(pkg.getNsPrefix(), pkg.getNsURI() + "#");

				String uri = pkg.getNsURI() + "#" + feature.getName(); 
				Property prop = model.createProperty(uri);
				featureMap.put(feature, prop);
			}
			return featureMap.get(feature);
		}
	}

	
	
}
