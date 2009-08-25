/**
 * 
 */
package org.openiaml.model.owl.tests;

import java.io.File;
import java.util.Iterator;

import l3i.sido.emf4sw.ui.ecore2owl.Ecore2OWLFileAction;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.StructuredSelection;
import org.openiaml.model.tests.ModelTestCase;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.PrintUtil;

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
	
}
