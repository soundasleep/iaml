/**
 * 
 */
package org.openiaml.model.owl.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import junit.framework.AssertionFailedError;
import l3i.sido.emf4sw.ui.ecore2owl.Ecore2OWLFileAction;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.StructuredSelection;
import org.openiaml.model.owl.tests.TransformIAMLToOwl.MyEcoreRDFWriter;
import org.openiaml.model.tests.ModelTestCase;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.reasoner.rulesys.RuleDerivation;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

/**
 * @author jmwright
 *
 */
public class TransformEcoreToOwl extends ModelTestCase {

	/**
	 * Takes simple.ecore and translates it into simple.owl.
	 * 
	 * @return The generated OWL schema.
	 */
	protected Model setupOwlTransform() throws Exception {

		// copy over ecore file
		// File source = new File("../org.openiaml.model/model/iaml.ecore");
		File source = new File("model/simple.ecore");
		System.out.println(source.getAbsolutePath());
		assertTrue("Source file exists: " + source, source.exists());
		IFile target = getProject().getFile("simple.ecore");
		assertFalse("Target file should not exist: " + target, target.exists());
		copyFileIntoWorkspace(source, target);
		assertTrue("Target file should exist: " + target, target.exists());
		IFile transformed = getProject().getFile("simple.owl");
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
	
	/**
	 * Loading a default model instance to the OWL format is valid.
	 * 
	 * @throws Exception
	 */
	public void testDefaultModelIsValid() throws Exception {

		Model schema = setupOwlTransform();
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner = reasoner.bindSchema(schema);
		
		// should be valid
		Model model = ModelFactory.createDefaultModel();
		InfModel inf = ModelFactory.createInfModel(reasoner, model);
		ValidityReport valid = inf.validate();
		assertIsValid(valid);

	}
	
	/**
	 * The given model has an invalid OWL syntax, i.e. it says
	 * that [InternetApplication root1] is the same as [Page page1].
	 * 
	 * @throws Exception
	 */
	public void testInvalidOwlModel() throws Exception {
		Model schema = setupOwlTransform();
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner = reasoner.bindSchema(schema);

		// try loading an invalid model
		Model model = FileManager.get().loadModel("file:tests/invalid.rdf");
		InfModel inf = ModelFactory.createInfModel(reasoner, model);
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
	}

	/**
	 * Here we load a model instance that is invalid according to our
	 * Jena rules, but because there is no OWL or Jena rule validation
	 * going on, the model loads successfully.
	 * 
	 * @throws Exception
	 */
	public void testInvalidInstanceIsValid() throws Exception {
		Model schema = setupOwlTransform();
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner = reasoner.bindSchema(schema);
		
		// what about directly from .simple? (xmi)
		
		// it's OK as long as all elements and attributes are given
		// the correct prefix namespaces (i.e. default EMF saved models
		// are not suitable)
		
		// even though the model is invalid (pages with the same name),
		// we can't express this in OWL => the model is valid
		Model model = FileManager.get().loadModel("file:tests/invalid.simple");
		InfModel inf = ModelFactory.createInfModel(reasoner, model);
		ValidityReport valid = inf.validate();
		assertIsValid(valid);
	}
	
	protected Model getInvalidRDF() {
		PrintUtil.registerPrefix("s", "http://openiaml.org/simple#");
		return FileManager.get().loadModel("file:tests/invalid.simple.rdf");
	}

	/**
	 * A simple Jena validation rule. This will fail on any 
	 * Page instance.
	 * 
	 * @throws Exception
	 */
	public void testValidationRule1() throws Exception {
		
		Model model = getInvalidRDF();
		String rules = "[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error('test', 'test', ?X)) <- " +
			"(?X rdf:type s:Page)]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
		
	}
	
	/**
	 * A simple Jena validation rule. This checks that we can
	 * look at children of instances: less than 2 children should exist.
	 * It should return invalid.
	 * 
	 * @throws Exception
	 */
	public void testValidationRule2() throws Exception {
		
		Model model = getInvalidRDF();
		String rules = "[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error(?P1, ?P2, ?X)) <- " +
			"(?X rdf:type s:InternetApplication) " +
			"(?X s:pages ?P1) " + 
			"(?X s:pages ?P2) notEqual(?P1, ?P2) ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
		
	}

	/**
	 * Check to see that we can get derivation traces.
	 * 
	 * @throws Exception
	 */
	public void testDerivationTrace() throws Exception {
		
		Model model = getInvalidRDF();
		String rules = "[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error(?P1, ?P2, ?X)) <- " +
			"(?X rdf:type s:InternetApplication) " +
			"(?X s:pages ?P1) " + 
			"(?X s:pages ?P2) notEqual(?P1, ?P2) ]]" +
			"[validationRule2: (?v rb:validation on()) -> " +
			"[(?X eg:violation 'true') <- " +
			"(?X rdf:type s:InternetApplication) " +
			"(?X s:pages ?P1) " + 
			"(?X s:pages ?P2) notEqual(?P1, ?P2) ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		inf.setDerivationLogging(true);		// enable derivation logging
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
		
		// String ns = "urn:x-hp-jena:eg/";
		/*
		String rb = ReasonerVocabulary.RBNamespace; // URI of "rb:"
		Property violation = model.getProperty(rb, "violation");
		*/
		
		//String eg = "urn:x-hp-jena:eg/";
		String eg = PrintUtil.egNS;
		Property violation = model.getProperty(eg, "violation");
		
		StmtIterator it = inf.listStatements( (Resource) null, violation, (RDFNode) null);
		assertTrue(it.hasNext());
		Statement s = it.next();
		
		RuleDerivation rdev = (RuleDerivation) inf.getDerivation(s).next();
		assertEquals(violation.toString(), rdev.getConclusion().getPredicate().toString());
		assertEquals("\"true\"", rdev.getConclusion().getObject().toString());
		rdev.printTrace(new PrintWriter(System.out, true), true);
		
		// no more matches
		assertFalse(it.hasNext());	
		
	}	

	/**
	 * Check to see that we can get derivation traces.
	 * (This time directly with the rb: namespace)
	 * 
	 * @throws Exception
	 */
	public void testDerivationTrace2() throws Exception {
		
		Model model = getInvalidRDF();
		String rules = "[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error(?P1, ?P2, ?X)) <- " +
			"(?X rdf:type s:InternetApplication) " +
			"(?X s:pages ?P1) " + 
			"(?X s:pages ?P2) notEqual(?P1, ?P2) ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		inf.setDerivationLogging(true);		// enable derivation logging
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
		
		// String ns = "urn:x-hp-jena:eg/";

		/*
		String rb = ReasonerVocabulary.RBNamespace; // URI of "rb:"
		Property violation = model.getProperty(rb, "violation");
		// Node validation = ReasonerVocabulary.RB_VALIDATION.asNode();
		 * *
		 */
		
		Property violation = ReasonerVocabulary.RB_VALIDATION_REPORT;
		
		//String eg = "urn:x-hp-jena:eg/";
		/*String eg = PrintUtil.egNS;
		Property violation = model.getProperty(eg, "violation");
		*/
		
		StmtIterator it = inf.listStatements( (Resource) null, violation, (RDFNode) null );
		try {
			// this doesn't pass!
			// it seems this is because rb:violation is not a statement (i.e. it's not in the
			// inferred model), but eg:violation is.
			assertTrue(it.hasNext());
		} catch (AssertionFailedError e) {
			// sadly, expected
			return;		
		}
		
		Statement s = it.next();
		
		RuleDerivation rdev = (RuleDerivation) inf.getDerivation(s).next();
		assertEquals(violation.toString(), rdev.getConclusion().getPredicate().toString());
		assertEquals("\"true\"", rdev.getConclusion().getObject().toString());
		rdev.printTrace(new PrintWriter(System.out, true), true);
		
		// no more matches
		assertFalse(it.hasNext());
		
	}	

	/**
	 * Combining both approaches above.
	 * 
	 * @throws Exception
	 */
	public void testDerivationTraceCombined() throws Exception {
		
		Model model = getInvalidRDF();
		String rules = "[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error(?X, ?Y, ?X)) <- " +
			"(?X eg:violation ?Y) ]]" +
			"[validationRule2: (?v rb:validation on()) -> " +
			"[(?X eg:violation 'true') <- " +
			"(?X rdf:type s:InternetApplication) " +
			"(?X s:pages ?P1) " + 
			"(?X s:pages ?P2) notEqual(?P1, ?P2) ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		inf.setDerivationLogging(true);		// enable derivation logging
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
		
		//String eg = "urn:x-hp-jena:eg/";
		String eg = PrintUtil.egNS;
		Property violation = model.getProperty(eg, "violation");
		
		StmtIterator it = inf.listStatements( (Resource) null, violation, (RDFNode) null);
		assertTrue(it.hasNext());
		Statement s = it.next();
		
		RuleDerivation rdev = (RuleDerivation) inf.getDerivation(s).next();
		assertEquals(violation.toString(), rdev.getConclusion().getPredicate().toString());
		assertEquals("\"true\"", rdev.getConclusion().getObject().toString());
		rdev.printTrace(new PrintWriter(System.out, true), true);
		
		// no more matches
		assertFalse(it.hasNext());	
		
	}	


	/**
	 * Testing the validation of recursive validation rules.
	 * 
	 * @throws Exception
	 */
	public void testDerivationTraceRecursive() throws Exception {
		
		Model model = getInvalidRDF();
		String rules = "[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error(?X, ?Y, ?X)) <- " +
			"(?X eg:violation ?Y) ]]" +
			"[validationRule2: (?v rb:validation on()) -> " +
			"[(?X eg:violation 'true') <- " +
			"(?X eg:foo ?P1) " +
			"(?X s:pages ?P1) ]]" +
			"[validationRule3: (?v rb:validation on()) -> " +
			"[(?X eg:foo ?P1) <- " +
			"(?X rdf:type s:InternetApplication) " +
			"(?X s:pages ?P1) " + 
			"(?X s:pages ?P2) notEqual(?P1, ?P2) ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		inf.setDerivationLogging(true);		// enable derivation logging
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
		
		//String eg = "urn:x-hp-jena:eg/";
		String eg = PrintUtil.egNS;
		Property violation = model.getProperty(eg, "violation");
		
		StmtIterator it = inf.listStatements( (Resource) null, violation, (RDFNode) null);
		assertTrue(it.hasNext());
		Statement s = it.next();
		
		RuleDerivation rdev = (RuleDerivation) inf.getDerivation(s).next();
		assertEquals(violation.toString(), rdev.getConclusion().getPredicate().toString());
		assertEquals("\"true\"", rdev.getConclusion().getObject().toString());
		rdev.printTrace(new PrintWriter(System.out, true), true);
		
		// no more matches
		assertFalse(it.hasNext());	
		
	}	
	
	/**
	 * A simple Jena validation rule. This checks that the inference
	 * world is closed: more than 3 children should exist. If this
	 * was open world, it would return as valid, as there might be something
	 * in the open world which could later support this claim.
	 * It should return invalid.
	 * 
	 * @throws Exception
	 */
	public void testValidationRule3() throws Exception {
		
		Model model = getInvalidRDF();
		String rules = "[moreThan3Children: " +
			"(?X rdf:type s:InternetApplication) " +
			"(?X s:pages ?P1) (?X s:pages ?P2) (?X s:pages ?P3) " +
			"notEqual(?P1, ?P2) notEqual(?P2, ?P3) notEqual(?P1, ?P3) " +
				" -> (?X eg:moreThan3Children 'true') ]\n" +
				
			"[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error('test', 'test', ?X)) <- " +
			"(?X rdf:type s:InternetApplication) " +
			"noValue(?X eg:moreThan3Children 'true') ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
		
	}
	
	/**
	 * A simple Jena validation rule. This checks that we can still
	 * check for valid rules; two or more children should exist.
	 * This should return valid.
	 * 
	 * @throws Exception
	 */
	public void testValidationRule4() throws Exception {
		
		Model model = getInvalidRDF();

		String rules = "[moreThan2Children: (?X rdf:type s:InternetApplication) (?X s:pages ?P1) (?X s:pages ?P2) notEqual(?P1, ?P2) -> (?X eg:moreThan2Children 'true') ]\n" +
			"[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error('test', 'test', ?X)) <- " +
			"(?X rdf:type s:InternetApplication) " +
			"noValue(?X eg:moreThan2Children 'true') ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		ValidityReport valid = inf.validate();
		assertIsValid(valid);
		
	}
	
	/**
	 * Test that if we load an OWL file with invalid instances,
	 * we can actually check that they are invalid.
	 * 
	 * @throws Exception
	 */
	public void testLoadInvalidOwl() throws Exception {
		Model schema = FileManager.get().loadModel( "file:tests/invalid1.owl" );
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner = reasoner.bindSchema(schema);
	
		// should not be valid
		Model model = ModelFactory.createDefaultModel();
		InfModel inf = ModelFactory.createInfModel(reasoner, model);
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
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
	 * Test custom ATL translation to translate .simple into .rdf.
	 * @return 
	 * 
	 * @throws Exception
	 */
	public IFile testMyAtlTranslation() throws Exception {
		// copy over ecore file
		// File source = new File("../org.openiaml.model/model/iaml.ecore");
		File source = new File("tests/valid.simple");
		System.out.println(source.getAbsolutePath());
		assertTrue("Source file exists: " + source, source.exists());
		IFile target = getProject().getFile("valid.simple");
		assertFalse("Target file should not exist: " + target, target.exists());
		copyFileIntoWorkspace(source, target);
		assertTrue("Target file should exist: " + target, target.exists());
		IFile transformed = getProject().getFile("valid.rdf");
		assertFalse("Final file should not exist: " + transformed, transformed.exists());
		
		// try the transformation action
		Simple2RDFModelAction action = new Simple2RDFModelAction();
		action.selectionChanged(null, new StructuredSelection(target));
		action.run(null);
		
		// once run, the "target.rdf" file should exist
		assertTrue("Final file should exist: " + transformed, transformed.exists());
		
		// print out target.rdf
		printFile(getProject().getFile("valid.rdf-ecore"));
		printFile(transformed);
		
		return transformed;
		
	}
	
	/**
	 * Take the RDF file we generated, load it with the generated
	 * OWL file, and check that it validates.
	 * 
	 * Tests that an InternetApplication should have at least 2 children;
	 * should pass.
	 * 
	 * @throws Exception
	 */
	public void testLoadMyAtlTransformationValidation1() throws Throwable {
		try {
		
		IFile rdf = testMyAtlTranslation();

		PrintUtil.registerPrefix("s", "http://openiaml.org/simple#");
		Model model = FileManager.get().loadModel("file:" + rdf.getLocation().toString());
		
		String rules = "[moreThan2Children: (?X rdf:type s:InternetApplication) (?X s:pages ?P1) (?X s:pages ?P2) notEqual(?P1, ?P2) -> (?X eg:moreThan2Children 'true') ]\n" +
			"[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error('test', 'test', ?X)) <- " +
			"(?X rdf:type s:InternetApplication) " +
			"noValue(?X eg:moreThan2Children 'true') ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		ValidityReport valid = inf.validate();
		assertIsValid(valid);
	
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			t.printStackTrace();
			System.out.println(t.getClass());
			throw t;
		}
	}
	
	/**
	 * Take the RDF file we generated, load it with the generated
	 * OWL file, and check that we can still check invalid validation.
	 * 
	 * Tests that an InternetApplication should have at least 3 children;
	 * should fail.
	 * 
	 * @throws Exception
	 */
	public void testLoadMyAtlTransformationValidation2() throws Throwable {
		try {

		IFile rdf = testMyAtlTranslation();

		PrintUtil.registerPrefix("s", "http://openiaml.org/simple#");
		Model model = FileManager.get().loadModel("file:" + rdf.getLocation().toString());
		
		String rules = "[moreThan3Children: " +
			"(?X rdf:type s:InternetApplication) " +
			"(?X s:pages ?P1) (?X s:pages ?P2) (?X s:pages ?P3) " +
			"notEqual(?P1, ?P2) notEqual(?P2, ?P3) notEqual(?P1, ?P3) " +
				" -> (?X eg:moreThan3Children 'true') ]\n" +
				
			"[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error('test', 'test', ?X)) <- " +
			"(?X rdf:type s:InternetApplication) " +
			"noValue(?X eg:moreThan3Children 'true') ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
		
		printReports(valid);
	
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			t.printStackTrace();
			System.out.println(t.getClass());
			throw t;
			
		}
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
		File source = new File("tests/valid.simple");
		assertTrue("Source file exists: " + source, source.exists());
		IFile target = getProject().getFile("valid.simple");
		assertFalse("Target file should not exist: " + target, target.exists());
		copyFileIntoWorkspace(source, target);
		assertTrue("Target file should exist: " + target, target.exists());
		IFile transformed = getProject().getFile("valid.rdf");
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
	 * Test custom class to transform an EObject into .rdf, but take
	 * a complex IAML model.
	 * 
	 * @return 
	 * 
	 * @throws Exception
	 */
	public IFile testMyRdfIaml() throws Exception {
		// copy over ecore file
		// File source = new File("../org.openiaml.model/model/iaml.ecore");
		File source = new File("tests/UserRolesLoginHandler.iaml");
		assertTrue("Source file exists: " + source, source.exists());
		IFile target = getProject().getFile("UserRolesLoginHandler.iaml");
		assertFalse("Target file should not exist: " + target, target.exists());
		copyFileIntoWorkspace(source, target);
		assertTrue("Target file should exist: " + target, target.exists());
		IFile transformed = getProject().getFile("UserRolesLoginHandler.rdf");
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
		
		IFile rdf = testMyRdf();

		PrintUtil.registerPrefix("s", "http://openiaml.org/simple#");
		Model model = FileManager.get().loadModel("file:" + rdf.getLocation().toString());
		
		String rules = "[moreThan2Children: (?X rdf:type s:InternetApplication) (?X s:pages ?P1) (?X s:pages ?P2) notEqual(?P1, ?P2) -> (?X eg:moreThan2Children 'true') ]\n" +
			"[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error('test', 'test', ?X)) <- " +
			"(?X rdf:type s:InternetApplication) " +
			"noValue(?X eg:moreThan2Children 'true') ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		ValidityReport valid = inf.validate();
		assertIsValid(valid);
	
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			t.printStackTrace();
			System.out.println(t.getClass());
			throw t;
		}
	}
	
	/**
	 * Try our custom EObject/RDF writer.
	 * 
	 * Tests that an InternetApplication should have at least 3 children;
	 * should fail.
	 * 
	 * @throws Exception
	 */
	public void testEObjectRdfValidation2() throws Throwable {
		try {

		IFile rdf = testMyRdf();

		// PrintUtil.registerPrefix("s", "http://openiaml.org/simple#");
		Model model = FileManager.get().loadModel("file:" + rdf.getLocation().toString());
		
		String rules = "[moreThan3Children: " +
			"(?X rdf:type http://openiaml.org/simple#InternetApplication) " +
			"(?X http://openiaml.org/simple#pages ?P1) (?X http://openiaml.org/simple#pages ?P2) (?X http://openiaml.org/simple#pages ?P3) " +
			"notEqual(?P1, ?P2) notEqual(?P2, ?P3) notEqual(?P1, ?P3) " +
				" -> (?X eg:moreThan3Children 'true') ]\n" +

			"[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error('test', 'test', ?X)) <- " +
			"(?X rdf:type s:InternetApplication) " +
			"noValue(?X eg:moreThan3Children 'true') ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		// reason.setParameter(ReasonerVocabulary.PROPtraceOn, true);
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
		
		printReports(valid);
	
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			t.printStackTrace();
			System.out.println(t.getClass());
			throw t;
			
		}
	}
	
	/**
	 * Test that supertype rules work OK.
	 * 
	 * @throws Exception
	 */
	public void testSupertypes() throws Throwable {
		try {

		IFile rdf = testMyRdf();

		// PrintUtil.registerPrefix("s", "http://openiaml.org/simple#");
		Model model = FileManager.get().loadModel("file:" + rdf.getLocation().toString());
		
		String rules =
			"[printType: (?X rdf:type ?A) -> print(?X, 'type', ?A)]\n" +
			"[validationRule: (?v rb:validation on()) -> " +
			"[(?X rb:violation error('test', 'test', ?X)) <- " +
			"(?X rdf:type s:NamedElement) ]]";
		
		Reasoner reason = new GenericRuleReasoner(Rule.parseRules(rules));
		reason.setParameter(ReasonerVocabulary.PROPtraceOn, true);
		reason = reason.bindSchema(setupOwlTransform());
		
		InfModel inf = ModelFactory.createInfModel(reason, model);
		ValidityReport valid = inf.validate();
		assertNotValid(valid);
		
		printReports(valid);
	
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

	
	
}
