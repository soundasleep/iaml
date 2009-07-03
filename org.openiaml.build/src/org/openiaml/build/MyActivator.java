/**
 * 
 */
package org.openiaml.build;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.gmf.codegen.util.CodegenEmitters;
import org.eclipse.gmf.codegen.util.EmitterSource;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class MyActivator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "ca.ecliptical.gmf.ant";

	// The shared instance
	private static MyActivator plugin;

	private EmitterSource<GenEditorGenerator, CodegenEmitters> emitterSource;
	
	/**
	 * The constructor
	 */
	public MyActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static MyActivator getDefault() {
		return plugin;
	}
	
	public CodegenEmitters getEmitters(GenEditorGenerator genModel) {
		if (emitterSource == null)
			emitterSource = new EmitterSource<GenEditorGenerator, CodegenEmitters>() {
				@Override
				protected CodegenEmitters newEmitters(GenEditorGenerator genModel) {
					return new CodegenEmitters(!genModel.isDynamicTemplates(), genModel.getTemplateDirectory());
				}
			};
		
		return emitterSource.getEmitters(genModel, genModel.isDynamicTemplates());
	}
}
