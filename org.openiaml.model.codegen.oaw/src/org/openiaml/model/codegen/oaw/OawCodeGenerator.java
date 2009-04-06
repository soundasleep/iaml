/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.util.ProgressMonitorAdapter;
import org.openiaml.model.codegen.ICodeGenerator;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.domain.DomainPackage;

import ca.ecliptical.emf.xpath.EMFXPath;

/**
 * @author jmwright
 *
 */
public class OawCodeGenerator implements ICodeGenerator {
	
	/**
	 * Generate code for a given model file into a given output directory.
	 * Does NOT deal with inference.
	 * 
	 */
	public IStatus generateCode(IFile file, IProgressMonitor monitor) {
		
		// we can't set the property to get the correct logger, because Eclipse
		// instantiates the logger before we even have the chance to.
		// and we can't have a .properties file, because this is the .propertiues
		// from the context of the classloader (Eclipse)
		
		// System.setProperty("org.apache.commons.logging.LogFactory", "org.openiaml.model.codegen.oaw.OawCodeGenerator.MyLogFactory");
		
		String wfFile = "src/workflow/generator.oaw";
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("model", file.getFullPath().toString());
		properties.put("src-gen", file.getProject().getLocation().toString());	// have to get absolute filename for output dir
		Map<String,Object> slotContents = new HashMap<String,Object>();
		new WorkflowRunner().run(wfFile,
			new ProgressMonitorAdapter(monitor), properties, slotContents);
		
		// refresh output folder
		try {
			file.getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
		} catch (CoreException e) {
			return new Status(Status.WARNING, "org.openiaml.model.codegen.oaw", "Could not refresh local project", e);
		}
		
		return Status.OK_STATUS;
			
	}
	
	/**
	 * Construct a RuntimeException with the given message, and throw it.
	 * Useful in templates, as we can get a stack trace to problems, rather
	 * than using OAW's ERROR code, which only prints out text.
	 * 
	 * @param message
	 */
	public static void throwException(String message) {
		throw new RuntimeException(message);
	}
	
	/**
	 * Here we evaluate the given query over the existing InternetApplication,
	 * and return all model elements that match.
	 * 
	 * @param root
	 * @param set
	 * @return
	 * @throws JaxenException 
	 */
	public static Set<EObject> resolveDynamicSet(InternetApplication root, DynamicApplicationElementSet set) throws JaxenException {
		if (set.getQuery().startsWith("xpath:")) {
			String query = set.getQuery();

			// remove prefix		
			query = query.substring("xpath:".length());
			
			// TODO move this into Java code (to reduce redunancy of the following code)
			EMFXPath xpath = new EMFXPath(query);
			xpath.addNamespace("iaml", ModelPackage.eNS_URI);
			xpath.addNamespace("iaml.domain", DomainPackage.eNS_URI);
			xpath.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
			List<?> results = xpath.selectNodes(root);
			
			Set<EObject> out = new HashSet<EObject>();
			for (Object o : results) {
				if (o instanceof EObject) {
					out.add((EObject) o);
				}
			}
			return out;
		}
		
		// TODO make into a real Exception
		throw new RuntimeException("Cannot resolve Dynamic Set for set query: " + set.getQuery() + " (set=" + set + ")");
	}
	
}
