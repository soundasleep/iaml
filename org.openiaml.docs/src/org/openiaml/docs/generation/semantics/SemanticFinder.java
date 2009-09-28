/**
 * 
 */
package org.openiaml.docs.generation.semantics;

import org.openiaml.docs.generation.DocumentationHelper;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.JavadocFragment;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.JavadocTextElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.Reference;

/**
 * Searches through Javadoc tags (both from Java code and Drools files) for
 * semantic references.
 * 
 * @author jmwright
 * @see #tagsToIdentify
 */
public class SemanticFinder {

	/**
	 * Javadog tags that are identified by
	 * {@link #findSemanticReferences(ModelDocumentation, JavadocTagElement, Reference, HandleSemantics)}.
	 */
	protected String[] tagsToIdentify = new String[] {
		"@semantics",
		"@example",
	};

	
	/**
	 * <p>With the given JavaDoc element, should there be any links 
	 * created?</p>
	 * 
	 * <p>Expected: <code>@semantics Model1,Model2 text to describe in semantics</code></p>
	 * <p>Expected: <code>@example Model1,Model2 text to describe the example</code></p>
	 * 
	 * @see #tagsToIdentify
	 * @param description the source element
	 * @param reference a reference to provide later
	 * @param handler the handler to call with matching references
	 */
	public void findSemanticReferences(DocumentationHelper helper, ModelDocumentation root, JavadocTagElement description, Reference reference, HandleSemantics handler) {
		for (String tag : tagsToIdentify) {
			if (tag.equals(description.getName())) {
				// cycle through all model elements
				JavadocTextElement refName = null;
				for (JavadocFragment f : description.getFragments()) {
					// select the first TextElement; this is the target class
					if (f instanceof JavadocTextElement) {
						refName = (JavadocTextElement) f;
						break;
					}						
				}
				if (refName != null) {
					// select the first word as the model element name
					String className = refName.getValue().trim();
					if (className.contains(" ")) {
						className = className.substring(0, className.indexOf(" "));
					}
					// are there multiple elements selected with ','s?
					String[] classNames = className.split(",");
					for (String classNameTarget : classNames) {
						
						EMFClass target = helper.getEMFClassFor(root, classNameTarget);
						if (target != null) {
							
							// get the handler to deal with it
							handler.handleSemanticLink(tag, description, target, reference);
	
						}
					}
				}
			}
		}
	}
	
	
}
