/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.openarchitectureware.xtend.ast.Check;
import org.openarchitectureware.xtend.ast.ExtensionFile;
import org.openarchitectureware.xtend.parser.ParseFacade;
import org.openiaml.docs.modeldoc.Constraint;
import org.openiaml.docs.modeldoc.ConstraintType;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.FileLineReference;
import org.openiaml.docs.modeldoc.FileReference;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * @author jmwright
 *
 */
public class LoadOAWConstraints extends DocumentationHelper implements ILoader {
	
	/**
	 * Load all of the OAW constraints.
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) throws DocumentationGenerationException {
		String checkFile = "../org.openiaml.model.codegen.oaw/src/metamodel/Checks.chk";
		InputStream in;
		try {
			in = new FileInputStream(checkFile);
		} catch (FileNotFoundException e) {
			throw new DocumentationGenerationException(e);
		}
		
		FileReference fr = factory.createFileReference();
		fr.setPlugin("org.openiaml.model.codegen.oaw");
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
			constraint.setReference(line);
			
			// add this constraint
			identifier.getConstraints().add(constraint);
		}
	}
	
}
