package org.openiaml.model.diagram.custom.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jet.JET2Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.openiaml.model.inference.CreateMissingElements;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.model.wires.WiresFactory;
import org.openiaml.model.model.wires.impl.WiresFactoryImpl;

/**
 * Action to generate code from an .iaml file
 * 
 * @see org.openiaml.model.codegen.jet
 * @author jmwright
 *
 */
public class GenerateCodeAction implements IViewActionDelegate, ICreateElements {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					IStatus status = generateCodeFrom((IFile) o, action, new NullProgressMonitor());
					if (!status.isOK()) {
						// TODO remove this reference to the plugin and remove the reference in plugin.xml
						IamlDiagramEditorPlugin.getInstance().logError(
								"Could not generate code for " + o, status.getException()); //$NON-NLS-1$
					}
				}
			}
		}

	}
	
	/**
	 * @param o
	 * @param monitor 
	 * @return 
	 */
	private IStatus generateCodeFrom(IFile o, IAction action, IProgressMonitor monitor) {
		try {
			if (o.getFileExtension().equals("iaml")) {
				
				// try and load the file directly
				ResourceSet resourceSet = new ResourceSetImpl();
				Resource resource = resourceSet.getResource(URI.createFileURI(o.getLocation().toString()), true);
				
				// do inference on the model
				for (EObject model : resource.getContents()) {
					CreateMissingElements ce = new CreateMissingElements(this);
					ce.create(model);
				}
	
				Map<String,Object> variables = new HashMap<String,Object>();
				
				// we have to set this manually as well
				variables.put("org.eclipse.jet.resource.project.name", o.getProject().getName());
				
				return JET2Platform.runTransformOnObject("org.openiaml.model.codegen.jet", resource, variables, monitor);
			}
		} catch (InferenceException e) {
			// int severity, String pluginId, String message, Throwable exception
			return new Status(IStatus.ERROR, "org.openiaml.model.diagram.custom", "Inference failed", e);
		}
		
		return null;
	}

	Object[] selection;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = null;
		if (selection instanceof IStructuredSelection) {
			this.selection = ((IStructuredSelection) selection).toArray();
		}
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createSyncWire(org.openiaml.model.model.ContainsWires, org.openiaml.model.model.WireEdgesSource, org.openiaml.model.model.WireEdgeDestination)
	 */
	@Override
	public SyncWire createSyncWire(ContainsWires container,
			WireEdgesSource source, WireEdgeDestination target)
			throws InferenceException {
		
		// we will just let ecore do it
		WiresFactory factory = WiresFactoryImpl.init();
		SyncWire wire = factory.createSyncWire();
		wire.setFrom(source);
		wire.setTo(target);
		
		container.getWires().add(wire);
		
		return wire;
		
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#setValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void setValue(EObject element, EStructuralFeature reference,
			Object value) throws InferenceException {

		// we will just let ecore do it
		element.eSet(reference, value);		
	}

}
