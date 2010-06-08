/**
 * 
 */
package org.openiaml.javadoc;

import java.util.Map;

import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

/**
 * @author jmwright
 *
 */
public class MyDoclet implements Taglet {

	/* (non-Javadoc)
	 * @see com.sun.tools.doclets.Taglet#getName()
	 */
	@Override
	public String getName() {
		return "semantics";
	}

	/* (non-Javadoc)
	 * @see com.sun.tools.doclets.Taglet#inConstructor()
	 */
	@Override
	public boolean inConstructor() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sun.tools.doclets.Taglet#inField()
	 */
	@Override
	public boolean inField() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sun.tools.doclets.Taglet#inMethod()
	 */
	@Override
	public boolean inMethod() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sun.tools.doclets.Taglet#inOverview()
	 */
	@Override
	public boolean inOverview() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sun.tools.doclets.Taglet#inPackage()
	 */
	@Override
	public boolean inPackage() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sun.tools.doclets.Taglet#inType()
	 */
	@Override
	public boolean inType() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sun.tools.doclets.Taglet#isInlineTag()
	 */
	@Override
	public boolean isInlineTag() {
		return false;
	}

    /**
     * Given the <code>Tag</code> representation of this custom
     * tag, return its string representation.
     * 
     * @param tag   the <code>Tag</code> representation of this custom tag.
	 * @see com.sun.tools.doclets.Taglet#toString(com.sun.javadoc.Tag)
     */
	@Override
	public String toString(Tag tag) {
		return "<i>My tag!: " + tag.text() + "</i>";
	}

    /**
     * Given an array of <code>Tag</code>s representing this custom
     * tag, return its string representation.
     * 
     * @param tags  the array of <code>Tag</code>s representing of this custom tag.
	 * @see com.sun.tools.doclets.Taglet#toString(com.sun.javadoc.Tag[])
     */
	@Override
	public String toString(Tag[] tags) {
		String r = "";
		for (Tag t : tags)
			r += toString(t);
		return r;
	}

	/**
	 * Register this Taglet. If an exception is thrown,
	 * print it out to stderr as well.
	 * 
	 * @param tagletMap
	 *            the map to register this tag to.
	 */
	@SuppressWarnings("unchecked")
	public static void register(Map tagletMap) {
		try {
			MyDoclet tag = new MyDoclet();
			Taglet t = (Taglet) tagletMap.get(tag.getName());
			if (t != null) {
				tagletMap.remove(tag.getName());
			}
			tagletMap.put(tag.getName(), tag);
		} catch (Throwable t) {
			t.printStackTrace(System.err);
			throw new RuntimeException(t);
		}
	}

}
