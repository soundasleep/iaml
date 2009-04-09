package org.openiaml.model.diagram.custom.edit.providers;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.openiaml.model.diagram.custom.commands.generation.InferMissingElementsCommand;
import org.openiaml.model.diagram.custom.commands.shortcuts.CreateMissingWireShortcutsCommand;
import org.openiaml.model.model.diagram.wire.edit.parts.CompositeWireEditPart;
import org.openiaml.model.model.diagram.wire.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.diagram.wire.providers.IamlEditPartProvider;

/**
 * We add some additional functionality to our default edit provider
 * This functionality is added since we set our custom EditProvider extension point priority to "Low", which is
 * higher than the "Lowest" specified by the generated provider
 * 
 * Most of this code is based on http://www.jevon.org/wiki/GMF_Code_Samples
 * 
 * @note this may only work for plugins in the visual diagram editor,
 * because we are using that specific PageEditPart.
 * 
 * @author jmwright
 *
 */
public class CustomWireEditPartProvider extends IamlEditPartProvider {
	// Q: does our reference to explicitly visual.IamlEditPartProvider
	// prevent it from displaying on alternative EditParts?
	// A: yes it does.
	
	public IGraphicalEditPart createGraphicEditPart(View view) {
		// install our policy
		final IGraphicalEditPart part = super.createGraphicEditPart(view);

		// we only want the root element to have this functionality
		if (part instanceof CompositeWireEditPart) {

			// do something
			part.addEditPartListener(new EditPartListener() {
				
				/**
				 * Automatically refresh shortcuts when a part is activated
				 */
				@Override
				public void partActivated(EditPart editpart) {
					if (editpart instanceof GraphicalEditPart) {
						
						// MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Warning", "Not yet implemented");
						
						ICommand command = new CreateMissingWireShortcutsCommand((GraphicalEditPart) editpart, 
									IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT, 
									CompositeWireEditPart.MODEL_ID);
						
						try {
							OperationHistoryFactory.getOperationHistory().execute(command,
									new NullProgressMonitor(), null);
						} catch (ExecutionException e) {
							IamlDiagramEditorPlugin.getInstance().logError(
									"Unable to refresh shortcuts view", e); //$NON-NLS-1$
						}
						
						// generate missing elements
						ICommand command2 = new InferMissingElementsCommand((GraphicalEditPart) editpart, 
								IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
								IamlDiagramEditorPlugin.ID
								);
					
						try {
							OperationHistoryFactory.getOperationHistory().execute(command2,
									new NullProgressMonitor(), null);
						} catch (ExecutionException e) {
							IamlDiagramEditorPlugin.getInstance().logError(
									"Unable to create missing elements", e); //$NON-NLS-1$
						}

					}
				}

				@Override
				public void partDeactivated(EditPart editpart) {
				}

				@Override
				public void removingChild(EditPart child, int index) {
				}

				@Override
				public void selectedStateChanged(EditPart editpart) {
				}

				@Override
				public void childAdded(EditPart child, int index) {
					// TODO Auto-generated method stub
					
				}});
			
			// it MAY be possible to install the LinkComponentEditPart functionality 
			// through a CreationEditPolicy (like OpenEditPolicy in the Three test)
			// but in this case, we still wouldn't have a handle to the newly created object yet.
			// so we will ignore this option and just do it through the pseudo-hack below.
			
		}
				
		return part;
	}
	
}
