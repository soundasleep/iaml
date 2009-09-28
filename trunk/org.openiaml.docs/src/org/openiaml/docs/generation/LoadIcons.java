/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;

import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.FileReference;
import org.openiaml.docs.modeldoc.GraphicalRepresentation;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * @author jmwright
 *
 */
public class LoadIcons extends DocumentationHelper implements ILoader {
	
	/**
	 * Load all of the runtime icons as GraphicalRepresentations.
	 * 
	 * @param factory
	 * @param root
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) {
		
		String plugin = "org.openiaml.model.edit";
		String pkg = "icons.full.obj16";
		File dir = new File("../org.openiaml.model.edit/icons/full/obj16/");
		
		String[] files = dir.list();
		for (String file : files) {
			// does there exist an EMFClass for this file?
			for (EMFClass cls : root.getClasses()) {
				String name = cls.getTargetClass().getName(); 
				if (file.startsWith(name + ".")) {
					// found a reference
					FileReference fr = factory.createFileReference();
					fr.setPlugin(plugin);
					fr.setPackage(pkg);
					fr.setName(file);
					root.getReferences().add(fr);
					
					// add a GraphicalReference
					GraphicalRepresentation gr = factory.createGraphicalRepresentation();
					gr.setReference(fr);
					cls.getGraphicalRepresentations().add(gr);
					
					// stop searching
					break;
				}
			}
		}
		
	}
	
}
