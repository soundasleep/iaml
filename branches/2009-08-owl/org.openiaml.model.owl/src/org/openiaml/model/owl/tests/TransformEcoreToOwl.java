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
import com.hp.hpl.jena.util.FileManager;

/**
 * @author jmwright
 *
 */
public class TransformEcoreToOwl extends ModelTestCase {

	public void testTransform() throws Exception {

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
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner = reasoner.bindSchema(schema);
		
		{
			// should be valid
			Model model = ModelFactory.createDefaultModel();
			InfModel inf = ModelFactory.createInfModel(reasoner, model);
			ValidityReport valid = inf.validate();
			assertTrue(valid.isValid());
		}
		
		{
			// try loading an invalid model
			Model model = FileManager.get().loadModel("file:tests/invalid.rdf");
			InfModel inf = ModelFactory.createInfModel(reasoner, model);
			ValidityReport valid = inf.validate();
			assertFalse(valid.isValid());
			Iterator<Report> it = valid.getReports();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}
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
		assertFalse(valid.isValid());
		Iterator<Report> it = valid.getReports();
		while (it.hasNext()) {
			Report r = it.next();
			System.out.println(r);
		}
	}
	
	// we can use Jena to take a model represented in RDF and
	// check it against the OWL model
	
	
}
