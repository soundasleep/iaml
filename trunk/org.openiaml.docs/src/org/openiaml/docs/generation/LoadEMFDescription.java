/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.io.IOException;

import org.openiaml.docs.modeldoc.AdditionalDocumentation;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.FileReference;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * Load additional documentation from HTML files in a given directory.
 * For each EMFClass in the ModelDocumentation, it searches for a
 * file "<code>TypeName.html</code>". If it exists, the file is loaded and
 * saved to the model instance along with a FileReference.
 * 
 * @author jmwright
 *
 */
public class LoadEMFDescription extends DocumentationHelper implements ILoader {

	/**
	 * The base of additional documentation, e.g. "../org.openiaml.model/model/doc"
	 */
	private String docBase;
	
	/**
	 * The plugin the generated package is stored in, e.g. "org.openiaml.model".
	 */
	private String plugin;
	
	/**
	 * The base of the plugin, e.g. "model.doc".
	 */
	private String packageBase;
		
	/**
	 * @param rootPackage
	 * @param plugin
	 * @param packageBase
	 */
	public LoadEMFDescription(String docBase, String plugin,
			String packageBase) {
		super();
		this.docBase = docBase;
		this.plugin = plugin;
		this.packageBase = packageBase;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.docs.generation.IDocGenerator#load(org.openiaml.docs.modeldoc.ModeldocFactory, org.openiaml.docs.modeldoc.ModelDocumentation)
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) throws DocumentationGenerationException {
		
		for (EMFClass cls : root.getClasses()) {
			
			// does a HTML file exist here?
			File f = new File(docBase + File.separator + cls.getName() + ".html");
			if (f.exists()) {
				// it exists; load it in as additional documentation
				try {
					char[] html = readFile(f);
					
					AdditionalDocumentation doc = factory.createAdditionalDocumentation();
					doc.setDescriptionHtml( String.valueOf(html) );
					
					FileReference ref = factory.createFileReference();
					ref.setName(cls.getName() + ".html");
					ref.setPackage(packageBase);
					ref.setPlugin(plugin);
					root.getReferences().add(ref);
					
					doc.setReference(ref);
					cls.getAdditionalDocumentation().add(doc);
					
				} catch (IOException e) {
					throw new DocumentationGenerationException(e); 
				}
				
			}
		}
	
	}
	
}
