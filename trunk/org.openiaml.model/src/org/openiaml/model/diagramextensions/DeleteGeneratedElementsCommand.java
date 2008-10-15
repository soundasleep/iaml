/**
 * 
 */
package org.openiaml.model.diagramextensions;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * @author jmwright
 *
 */
public class DeleteGeneratedElementsCommand extends CompoundCommand {

	/**
	 * @param req
	 */
	public DeleteGeneratedElementsCommand(DestroyElementRequest req) {
		System.out.println("yay, we are in a DeleteGeneratedElementsCommand! req = " + req);
	}

}
