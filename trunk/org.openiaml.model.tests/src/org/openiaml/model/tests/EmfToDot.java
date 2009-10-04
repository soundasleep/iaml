/**
 * 
 */
package org.openiaml.model.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.properties.IEMFElementSelector.DefaultElementSelector;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.NamedElement;

/**
 * Generate DOT graphs representing EMF models.
 * 
 * @author jmwright
 *
 */
public class EmfToDot extends DefaultElementSelector {

	public Iterable<EObject> wrap(final Iterator<EObject> o) {
		return new Iterable<EObject>() {
			@Override
			public Iterator<EObject> iterator() {
				return o;
			}	
		};
	}
	
	public String toDot(EObject initialModel, EObject finalModel) {
		
		String initialColor = "\"#6677cc\""; 
		String finalColor = "white"; 

		List<String> initialNodes = new ArrayList<String>();
		List<String> initialEdges = new ArrayList<String>();

		// initial nodes
		//initialNodes.add(getID(initialModel));
		for (EObject e : wrap(initialModel.eAllContents())) {
			initialNodes.add(getID(e));
		}
		
		// initial references
		putReferencesIntoList(initialModel, initialEdges);
		for (EObject e : wrap(initialModel.eAllContents())) {
			putReferencesIntoList(e, initialEdges);
		}
		
		// final nodes and edges
		List<String> finalNodes = new ArrayList<String>();
		List<String> finalEdges = new ArrayList<String>();

		// final nodes
		//finalNodes.add(getID(finalModel));
		for (EObject e : wrap(finalModel.eAllContents())) {
			finalNodes.add(getID(e));
		}
		
		// final references
		putReferencesIntoList(finalModel, finalEdges);
		for (EObject e : wrap(finalModel.eAllContents())) {
			putReferencesIntoList(e, finalEdges);
		}
		
		// now output the .dot graph
		StringBuffer buf = new StringBuffer();
		buf.append("digraph output {\n");
		buf.append("  size=\"18,18\";\n");
		buf.append("  ratio=expand;\n");

		// root node
		buf.append("  node [shape=doublecircle, label=\"\", style=filled, fillcolor=")
			.append(initialColor)
			.append("];");
		buf.append(getID(initialModel)).append(";\n");

		// initial nodes
		buf.append("  node [shape=circle, label=\"\", penwidth=2, style=filled, fillcolor=")
			.append(initialColor)
			.append("];\n");
		buf.append("  node [width=.5];\n");
		
		for (String n : initialNodes)
			buf.append("    ").append(n).append(";\n");

		// initial edges
		buf.append("  edge [penwidth=2];\n");
		
		for (String n : initialEdges)
			buf.append("    ").append(n).append(";\n");
		
		// final nodes
		buf.append("  node [shape=circle, label=\"\", penwidth=1, style=filled, fillcolor=")
			.append(finalColor)
			.append("];\n");
		buf.append("  node [width=.3];\n");
		
		for (String n : finalNodes)
			if (!initialNodes.contains(n))
				buf.append("    ").append(n).append(";\n");
	
		// initial edges
		buf.append("  edge [penwidth=.5];\n");
		
		for (String n : finalEdges)
			if (!initialEdges.contains(n))
				buf.append("    ").append(n).append(";\n");
	
		buf.append("}");
		
		return buf.toString();
		
	}
	
	protected void putReferencesIntoList(EObject e, List<String> edges) {
		for (EReference ref : e.eClass().getEAllReferences()) {
			if (ignoreReference(ref))
				continue;	// ignore
			
			Object o = e.eGet(ref);
			if (!(o instanceof List<?>))
				o = Collections.singletonList(o);
			
			List<?> refList = (List<?>) o;
			for (Object target : refList) {
				String s = getID(e) + " -> " + getID(target);
				s += " [";
				if (!ref.isContainment()) {
					s += " style=dashed, arrowhead=vee, arrowsize=.5";
				}
				s += " ]";
				edges.add(s);
			}
		}
	}
		
	public String getID(Object obj) {
		if (obj instanceof EObject) {
			if (obj instanceof NamedElement) {
				return ((NamedElement) obj).getId().replace(".", "_");
			}
			return "O" + obj.hashCode();	
		}
		return "unknown";
	}
	
	@Override
	public boolean ignoreReference(EReference ref) {
		return ModelPropertiesInvestigator.isGeneratedReference(ref);
	}

}
