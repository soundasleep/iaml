package org.openiaml.model.diagram.custom.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
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
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.impl.ModelFactoryImpl;
import org.openiaml.model.model.operations.impl.OperationsFactoryImpl;
import org.openiaml.model.model.visual.impl.VisualFactoryImpl;
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
	 * @see org.openiaml.model.inference.ICreateElements#setValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void setValue(EObject element, EStructuralFeature reference,
			Object value) throws InferenceException {

		// we will just let ecore do it
		element.eSet(reference, value);		
	}
	
	private List<EFactory> factories = null;
	private List<EFactory> getFactories() {
		if (factories == null) {
			factories = new ArrayList<EFactory>();
			factories.add(ModelFactoryImpl.init());
			factories.add(WiresFactoryImpl.init());
			factories.add(OperationsFactoryImpl.init());
			factories.add(VisualFactoryImpl.init());
		}
		return factories;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createElement(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public EObject createElement(EObject container, EClass elementType, EStructuralFeature feature)
			throws InferenceException {
		
		// sanity check
		Assert.isNotNull(container);
		Assert.isNotNull(elementType);
		Assert.isNotNull(feature);
		
		// try all the factories
		EObject object = null;
		for (EFactory factory : getFactories()) {
			try {
				object = factory.create(elementType);
				break;
			} catch (IllegalArgumentException e) {
				// continue
			}
		}
		
		if (object == null)
			throw new IllegalArgumentException("Unknown class type '" + elementType + "' - am I missing a factory?");
		
		// we now need to set its container
		Object f = container.eGet(feature);
		if (!(f instanceof List)) {
			throw new IllegalArgumentException("The structural feature '" + feature + "' of object '" + container + "' cannot contain anything - it isn't a list.");
		}
		List containerList = (List) f;
		containerList.add(object);
		
		return object;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createRelationship(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public EObject createRelationship(EObject container, EClass elementType,
			EObject source, EObject target, EStructuralFeature containerFeature, EStructuralFeature sourceFeature, EStructuralFeature targetFeature) throws InferenceException {
		
		// sanity check
		Assert.isNotNull(container);
		Assert.isNotNull(elementType);
		Assert.isNotNull(source);
		Assert.isNotNull(target);
		Assert.isNotNull(containerFeature);
		Assert.isNotNull(sourceFeature);
		Assert.isNotNull(targetFeature);
		
		// try all the factories
		EObject object = null;
		for (EFactory factory : getFactories()) {
			try {
				object = factory.create(elementType);
				break;
			} catch (IllegalArgumentException e) {
				// continue
			}
		}
		
		if (object == null)
			throw new IllegalArgumentException("Unknown class type '" + elementType + "' - am I missing a factory?");
		
		// we now need to set its container
		Object f = container.eGet(containerFeature);
		if (!(f instanceof List)) {
			throw new IllegalArgumentException("The container structural feature '" + containerFeature + "' of object '" + container + "' cannot contain anything - it isn't a list.");
		}
		List containerList = (List) f;
		containerList.add(object);

		// we now need to set its source feature
		object.eSet(sourceFeature, source);
		
		// we now need to set its target feature
		object.eSet(targetFeature, target);

		return object;
		
	}

}
