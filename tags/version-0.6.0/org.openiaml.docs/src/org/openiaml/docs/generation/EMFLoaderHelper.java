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
	 * Get particular information for the given EMF model element; the
	 * parsed information (if any) is returned.
	 * 
	 * <p>Following EMF's documentation approach, we get the tagline from
	 * an {@link EAnnotation} under the given source URI, and search for
	 * the particular documentation key.
	 * 
	 * @param factory the factory to create the {@link JavadocTagElement}
	 * @param source the element to investigate
	 * @param annotationURI e.g. <code>http://www.eclipse.org/emf/2002/GenModel</code>
	 * @param annotationKey e.g. <code>documentation</code>
	 * @return the parsed documentation, or <code>null</code> if there was none (or it could not be parsed)
	 */
	protected JavadocTagElement getAnnotationValue(ModeldocFactory factory, EModelElement source, String annotationURI, String annotationKey) {
		EAnnotation ann = source.getEAnnotation(annotationURI);
		if (ann != null) {
			EMap<String, String> details = ann.getDetails();
			for (String key : details.keySet()) {
				if (key.equals(annotationKey)) {
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
		return getAnnotationValue(factory, source, "http://www.eclipse.org/emf/2002/GenModel", "documentation");
	}
	
	/**
	 * Get rationale information for the given EMF model element; the
	 * parsed information (if any) is returned.
	 * 
	 * <p>Annotation URI: <code>http://openiaml.org/modeldoc</code>,
	 * annotation key: <code>rationale</code>.</p>
	 * 
	 * @param factory the factory to create the {@link JavadocTagElement}
	 * @param source the element to investigate
	 * @return the parsed documentation, or <code>null</code> if there was none (or it could not be parsed)
	 */
	protected JavadocTagElement getRationaleForEMFElement(ModeldocFactory factory, EModelElement source) {
		return getAnnotationValue(factory, source, "http://openiaml.org/modeldoc", "rationale");
	}

	public abstract List<ITagHandler> getSemanticTagHandlers();
	
}
