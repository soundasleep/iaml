package org.openiaml.model.diagram.custom.edit.providers;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.diagram.visual.providers.IamlEditPartProvider;
import org.openiaml.model.model.diagram.visual.edit.parts.PageEditPart;

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
public class CustomVisualEditPartProvider extends IamlEditPartProvider {
	// TODO: does our reference to explicitly IamlEditPartProvider
	// prevent it from displaying on alternative EditParts?
	
	public IGraphicalEditPart createGraphicEditPart(View view) {
		// install our policy
		final IGraphicalEditPart part = super.createGraphicEditPart(view);

		// we only want blank objects to have the expand
		String s = part.getClass().getSimpleName();
		
		if (part.getClass().getSimpleName().equals("PageEditPart")) {
		
		if (part instanceof PageEditPart) {

			// do something
			part.addEditPartListener(new EditPartListener() {

				/**
				 * Automatically refresh shortcuts when a part is activated
				 */
				@Override
				public void partActivated(EditPart editpart) {
					if (editpart instanceof GraphicalEditPart) {
						
						MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Warning", "Not yet implemented");
						
						/*
						ICommand command = new CreateMissingShortcutsCommand((GraphicalEditPart) editpart, Uml3DiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
						
						try {
							OperationHistoryFactory.getOperationHistory().execute(command,
									new NullProgressMonitor(), null);
						} catch (ExecutionException e) {
							Uml3DiagramEditorPlugin.getInstance().logError(
									"Unable to refresh shortcuts view", e); //$NON-NLS-1$
						}
						*/
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
		
		}
		
		return part;
	}
	
}
