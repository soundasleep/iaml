/**
 * 
 */
package org.openiaml.model.diagram.helpers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Wraps the generated GMF DiagramUpdater with an interface for common
 * generated methods (issue 151).
 * 
 * @author jmwright
 */
public interface IDiagramUpdater {
	
	public boolean considerElementForShortcut(EObject childElement,
			EObject wire, View view, EObject source, List doneAlready,
			List result, List edges);
	
}
