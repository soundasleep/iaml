/**
 * 
 */
package org.openiaml.model.diagram.helpers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author jmwright
 *
 */
public interface IDiagramUpdater {
	
	public boolean considerElementForShortcut(EObject childElement,
			EObject wire, View view, EObject source, List doneAlready,
			List result, List edges);
	
}
