/**
 * 
 */
package org.openiaml.docs.generation.semantics;

import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.JavadocTextElement;
import org.openiaml.docs.modeldoc.ModeldocFactory;

public class HandleInlineJavadoc {
	private ModeldocFactory factory;
	private JavadocTagElement tagElement;
	
	public HandleInlineJavadoc(ModeldocFactory factory, JavadocTagElement tagElement) {
		this.factory = factory;
		this.tagElement = tagElement;
	}
	
	/**
	 * Handle a fragment of Javadoc text.
	 * 
	 * @param text
	 */
	public void handleText(String text) {
		JavadocTextElement e = factory.createJavadocTextElement();
		e.setValue(text);
		tagElement.getFragments().add(e);
	}
	
	/**
	 * Handle a '@tag text' inline.
	 * 
	 * Expects "<code>@model ClassName</code>" tag for it to add
	 * an InferenceSemantic.
	 * 
	 * @param tag cannot be null.
	 * @param text
	 */
	public void handleTag(String tag, String text) {
		if (tag == null)
			throw new NullPointerException("tag cannot be null");
		
		JavadocTagElement fragment = factory.createJavadocTagElement();
		fragment.setName(tag);
		
		JavadocTextElement text2 = factory.createJavadocTextElement();
		text2.setValue(text);
		fragment.getFragments().add(text2);
		
		tagElement.getFragments().add(fragment);

	}
}
