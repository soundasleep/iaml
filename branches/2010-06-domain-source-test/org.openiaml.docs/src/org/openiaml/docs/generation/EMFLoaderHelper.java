/**
 * 
 */
package org.openiaml.docs.generation;

import java.util.List;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.openiaml.docs.generation.semantics.ITagHandler;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * @author jmwright
 *
 */
public abstract class EMFLoaderHelper {
	
	/**
	 * Get tagline information for the given EMF model element; the
	 * parsed information (if any) is returned.
	 * 
	 * <p>Following EMF's documentation approach, we get the tagline from
	 * an {@link EAnnotation} under source '<code>http://www.eclipse.org/emf/2002/GenModel</code>'.
	 * 
	 * @param factory the factory to create the {@link JavadocTagElement}
	 * @param source the element to investigate
	 * @return the parsed documentation, or <code>null</code> if there was none (or it could not be parsed)
	 */
	protected JavadocTagElement getTaglineForEMFElement(ModeldocFactory factory, EModelElement source) {
		EAnnotation ann = source.getEAnnotation("http://www.eclipse.org/emf/2002/GenModel");
		if (ann != null) {
			EMap<String, String> details = ann.getDetails();
			for (String key : details.keySet()) {
				if (key.equals("documentation")) {
					// found a tag line
					// parse for JavaDoc
					JavadocTagElement e = factory.createJavadocTagElement();
					
					// parse the line into javadoc elements
					// (the line needs to be parsed into fragments before we can find semantic references)
					new BasicJavadocParser(getSemanticTagHandlers()).parseSemanticLineIntoFragments(details.get(key), factory, e);
					
					// save this parsed detail as a tagline
					return e;
				}
			}
		}
		
		// none found
		return null;
	}

	public abstract List<ITagHandler> getSemanticTagHandlers();
	
}
