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
	 * The absolute path of the checkfile to load, e.g.
	 * "../org.openiaml.model.codegen.oaw/src/metamodel/Extensions.ext".
	 */
	private String extFile;
	
	/**
	 * The plugin the generated package is stored in, e.g. "org.openiaml.model".
	 */
	private String plugin;
	
	/**
	 * The base of the check file, e.g. "src.metamodel".
	 */
	private String packageBase;
	
	/**
	 * The name of the check file, e.g. "Extensions.ext".
	 */
	private String name;

	/**
	 * @param extFile
	 * @param plugin
	 * @param packageBase
	 * @param name
	 */
	public LoadOAWExtensions(String extFile, String plugin, String packageBase,
			String name) {
		super();
		this.extFile = extFile;
		this.plugin = plugin;
		this.packageBase = packageBase;
		this.name = name;
	}

	/**
	 * Load all of the OAW extensions.
	 * TODO needs to be refactored.
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) throws DocumentationGenerationException {

		InputStream in;
		try {
			in = new FileInputStream(extFile);
		} catch (FileNotFoundException e) {
			throw new DocumentationGenerationException(e);
		}
		
		FileReference fr = factory.createFileReference();
		fr.setPlugin(plugin);
		fr.setPackage(packageBase);
		fr.setName(name);
		fr.setParent(root);
		
		ExtensionFile file = ParseFacade.file(new InputStreamReader(in), extFile);
		
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
