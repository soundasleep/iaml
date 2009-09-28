/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.openarchitectureware.xtend.ast.Extension;
import org.openarchitectureware.xtend.ast.ExtensionFile;
import org.openarchitectureware.xtend.parser.ParseFacade;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.FileLineReference;
import org.openiaml.docs.modeldoc.FileReference;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModelExtension;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * @author jmwright
 *
 */
public class LoadOAWExtensions extends DocumentationHelper implements ILoader {
	
	/**
	 * Load all of the OAW extensions.
	 * TODO needs to be refactored.
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) throws DocumentationGenerationException {
		{
		String checkFile = "../org.openiaml.model.codegen.oaw/src/metamodel/Extensions.ext";
		InputStream in;
		try {
			in = new FileInputStream(checkFile);
		} catch (FileNotFoundException e) {
			throw new DocumentationGenerationException(e);
		}
		
		FileReference fr = factory.createFileReference();
		fr.setPlugin("org.openiaml.model.codegen.oaw");
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
				extension.setReference(line);

				// add this extension
				identifier.getExtensions().add(extension);

			}
		}
		}
		
		// TODO this needs to be refactored!
		{
			String checkFile = "../org.openiaml.model.codegen.oaw/src/template/GeneratorExtensions.ext";
			InputStream in;
			try {
				in = new FileInputStream(checkFile);
			} catch (FileNotFoundException e) {
				throw new DocumentationGenerationException(e);
			}
			
			FileReference fr = factory.createFileReference();
			fr.setPlugin("org.openiaml.model.codegen.oaw");
			fr.setPackage("src.template");
			fr.setName("GeneratorExtensions.ext");
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
					extension.setReference(line);

					// add this extension
					identifier.getExtensions().add(extension);

				}
			}
			}
	}

	
}
