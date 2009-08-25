/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.openiaml.model.runtime.IFileCopyListener;
import org.openiaml.model.runtime.IamlRuntimeLibrariesPlugin;

/**
 * Extends the OawCodeGenerator to also include runtime files
 * which would normally be included through the webserver.
 * 
 * See issue 81.
 * 
 * @author jmwright
 *
 */
public class OawCodeGeneratorWithRuntime extends OawCodeGenerator implements IFileCopyListener {

	@Override
	public IStatus generateCode(IFile file, IProgressMonitor monitor,
			Map<String, String> runtimeProperties) {
		
		monitor.beginTask("Generating code (with runtime)", 100);
		
		boolean needToCopy = "true".equals(runtimeProperties.get("include_runtime")); 
		
		// set our custom properties, if necessary
		if (needToCopy) {
			// need to copy it over
			runtimeProperties.put("config_runtime", "runtime/include/");
			runtimeProperties.put("config_web", "runtime/web/");
		}
		
		// generate like normal
		IStatus result = super.generateCode(file, new SubProgressMonitor(monitor, 90), runtimeProperties);
		if (!result.isOK())
			return result;		// bail early
		
		// copy over files
		// we copy over ALL files from the org.openiaml.model.runtime folder
		// into a runtime/ folder
		if (needToCopy) {
			IamlRuntimeLibrariesPlugin runtime = IamlRuntimeLibrariesPlugin.getInstance();
			
			// add a listener
			runtime.addFileCopyListener(this);

			try {
				runtime.copyRuntimeFiles(file.getProject(), new SubProgressMonitor(monitor, 10));
			} catch (IOException e) {
				return new Status(Status.ERROR, PLUGIN_ID, "Cannot yet copy over runtime files: " + e.getMessage(), e);
			} catch (CoreException e) {
				return new Status(Status.ERROR, PLUGIN_ID, "Cannot yet copy over runtime files: " + e.getMessage(), e);
			}
		}
		
		monitor.done();
		
		// return old result
		return result;
		
	}

	/**
	 * Notify the {@link IACleanerBeautifier} that a file was copied from
	 * the runtime (for caching purposes).
	 * 
	 * @see org.openiaml.model.runtime.IFileCopyListener#fileCopied(java.io.File, java.io.File)
	 */
	@Override
	public void fileCopied(File source, File target) {
		if (IACleanerBeautifier.isTracingEnabled()) {
			IACleanerBeautifier.getTracingCache().add( target );
		}
	}

}
