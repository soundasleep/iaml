/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Extends the OawCodeGenerator to also include runtime files
 * which would normally be included through the webserver.
 * 
 * See issue 81.
 * 
 * @author jmwright
 *
 */
public class OawCodeGeneratorWithRuntime extends OawCodeGenerator {

	@Override
	public IStatus generateCode(IFile file, IProgressMonitor monitor,
			Map<String, String> runtimeProperties) {
		
		boolean needToCopy = "true".equals(runtimeProperties.get("include_runtime")); 
		
		// set our custom properties, if necessary
		if (needToCopy) {
			// need to copy it over
			runtimeProperties.put("config_runtime", "runtime/include/");
			runtimeProperties.put("config_web", "runtime/web/");
		}
		
		// generate like normal
		IStatus result = super.generateCode(file, monitor, runtimeProperties);
		if (!result.isOK())
			return result;		// bail early
		
		// copy over files
		// we copy over ALL files from the org.openiaml.model.runtime folder
		// into a runtime/ folder
		if (needToCopy) {
			return new Status(Status.ERROR, PLUGIN_ID, "Cannot yet copy over runtime files");
		}
		
		// return old result
		return result;
		
	}

}
