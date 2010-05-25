package org.openiaml.model.custom.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.GeneratedElement;

/**
 * Issue 86: a command to refresh all element IDs to new IDs that
 * are more meaningful.
 * 
 * @author jmwright
 *
 */
public class RewriteElementIDs extends IamlFileAction {

	private EObject model;

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.IamlFileAction#doExecute(org.eclipse.core.resources.IFile, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus doExecute(IFile o, IProgressMonitor monitor)
			throws InferenceException, IOException, CoreException {
		
		monitor.beginTask("Rewriting generated IDs", 150);
				
		try {
			monitor.subTask("Loading model " + o.getName());
			model = ModelLoader.load(o);
		} catch (ModelLoadException e) {
			return errorStatus("Could not load " + o.getName() + ": " + e.getMessage(), e);
		}
		monitor.worked(25);
		
		int count = 1;		// include root InternetApplication
		monitor.subTask("Counting existing IDs");
		{
			Iterator<EObject> it = model.eAllContents();
			while (it.hasNext()) {
				EObject obj = it.next();
				if (obj instanceof GeneratedElement) {
					count++;
				}
			}
		}
		
		// halfway there
		monitor.worked(50);

		// now, rewrite all the IDs
		IProgressMonitor sub = new SubProgressMonitor(monitor, 50);
		sub.beginTask("Rewriting IDs", count);

		Set<String> existingIDs = new HashSet<String>();
		sub.subTask("Rewriting existing IDs");
		{
			// first the root InternetApplication
			if (model instanceof GeneratedElement) {
				GeneratedElement e = (GeneratedElement) model;
				
				// rewrite					
				String newID = generateNewID(e, existingIDs);
				e.setId(newID);
				existingIDs.add(newID);
			}
			sub.worked(1);
			
			Iterator<EObject> it = model.eAllContents();
			while (it.hasNext()) {
				EObject obj = it.next();
				if (obj instanceof GeneratedElement) {
					GeneratedElement e = (GeneratedElement) obj;
					
					// rewrite					
					String newID = generateNewID(e, existingIDs);
					e.setId(newID);
					existingIDs.add(newID);
				}
				sub.worked(1);
			}
		}
		sub.done();
		
		// now save
		monitor.subTask("Saving model");
		model.eResource().save(ModelLoader.getSaveOptions());
		
		// done
		monitor.done();
		
		return Status.OK_STATUS;
	}
	
	/**
	 * A mapping to simplify certain common names, e.g.
	 * 'InputTextField' -> text
	 */
	private static final Map<String, String> rewriteClassNameMap = new HashMap<String, String>();
	static {
		rewriteClassNameMap.put("inputtextfield", "text");
		rewriteClassNameMap.put("inputform", "form");
		rewriteClassNameMap.put("syncwire", "sync");
		rewriteClassNameMap.put("setwire", "set");
		rewriteClassNameMap.put("detailwire", "detail");
		rewriteClassNameMap.put("autocompletewire", "acw");
		rewriteClassNameMap.put("internetapplication", "root");
		rewriteClassNameMap.put("domainiterator", "iterator");
		rewriteClassNameMap.put("domainattribute", "attribute");
		rewriteClassNameMap.put("domainattributeinstance", "ai");
		rewriteClassNameMap.put("domainschema", "schema");
		rewriteClassNameMap.put("domainsource", "source");
	}

	/**
	 * Generate a new ID for the given element, that is not within the
	 * given set of IDs.
	 * 
	 * <p>In particular, we will only rewrite for IDs that contain '.' (i.e.
	 * that look generated).
	 * 
	 * @param e element to generate for
	 * @param existingIDs a set of already used IDs
	 * @return a new ID, if necessary, or the same ID
	 */
	public String generateNewID(GeneratedElement e, Set<String> existingIDs) {
		// if it doesn't look generated, don't rewrite it
		if (!e.getId().contains("."))
			return e.getId();
		
		// InputForm -> inputform
		String className = e.eClass().getName().toLowerCase();
		
		// rewrite mapping?
		if (rewriteClassNameMap.containsKey(className)) {
			className = rewriteClassNameMap.get(className);
		}
		
		int i;
		if (existingCount.containsKey(className)) {
			i = existingCount.get(className);
		} else {
			i = 1;
		}

		// find an unused ID
		while (existingIDs.contains( className + i )) {
			i++;
			if (i > 100000) {
				// ran out! bail early
				throw new IllegalArgumentException("Could not find a new ID for class name" + className);
			}
		}
		
		// save the current count
		existingCount.put(className, i);
		
		// return it
		return className + i;
	}
	
	private Map<String, Integer> existingCount = new HashMap<String, Integer>();

	@Override
	protected EObject getLoadedModel() {
		return model;
	}

	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not rewrite generated IDs in '" + individual.getName() + "': " + message;
	}

	@Override
	public String getProgressMessage() {
		return "Rewriting generated IDs";
	}

	/**
	 * @return the loaded model
	 */
	public EObject getModel() {
		return model;
	}
	
}
