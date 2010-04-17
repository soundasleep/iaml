/**
 * 
 */
package org.openiaml.model.diagram.helpers.inference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * Wraps {@link AbstractTransactionalCommand} with some helper and
 * convenience methods.
 * 
 * @author jmwright
 *
 */
public abstract class AbstractTransactionalCommandHelper extends AbstractTransactionalCommand {

	public AbstractTransactionalCommandHelper(
			TransactionalEditingDomain domain, String label, Map options,
			List affectedFiles) {
		super(domain, label, options, affectedFiles);
	}

	public AbstractTransactionalCommandHelper(
			TransactionalEditingDomain domain, String label, List affectedFiles) {
		super(domain, label, affectedFiles);
	}	

	public AbstractTransactionalCommandHelper(
			TransactionalEditingDomain domain, String label, EObject object) {
		super(domain, label, getWorkspaceFiles(object));
	}	

    /**
     * Convenience method to get a list of workspaces files associated with
     * <code>eObject</code>.
     * 
     * <p>This method is copied directly from {@link AbstractTransactionalCommand}.
     * 
     * @param eObject
     *            the model object, may be <code>null</code>
     * @return the list of {@link IFile}s
     */
    protected static List getWorkspaceFiles(EObject eObject) {
        List result = new ArrayList();

        if (eObject != null) {
            Resource resource = eObject.eResource();
            
            if (resource != null) {
                IFile file = WorkspaceSynchronizer.getFile(resource);
    
                if (file != null) {
                    result.add(file);
                }
            }
        }

        return result;
    }
    
	/**
	 * Create a new {@link EmfInferenceHandler} instance.
	 */
	protected static EmfInferenceHandler newHandler(TransactionalEditingDomain editingDomain, List workspaceFiles, Resource resource) {
		return new EmfInferenceHandler(
				editingDomain, 
				workspaceFiles, 
				null, 
				null, 
				resource); 
	}
	
	
}
