package org.openiaml.model.model.diagram.wire.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.HintedDiagramLinkStyle;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.openiaml.model.model.diagram.wire.edit.parts.CompositeWireEditPart;
import org.openiaml.model.model.diagram.wire.edit.parts.RunInstanceWireEditPart;
import org.openiaml.model.model.diagram.wire.edit.parts.RunInstanceWireNameEditPart;
import org.openiaml.model.model.diagram.wire.part.IamlVisualIDRegistry;

/**
 * @generated
 */
public class RunInstanceWireViewFactory extends ConnectionViewFactory {

	/**
	 * @generated NOT
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createConnectorStyle());
		styles.add(NotationFactory.eINSTANCE.createFontStyle());
		// add link to wire diagram (added manually)
		{
			HintedDiagramLinkStyle diagramFacet = NotationFactory.eINSTANCE
					.createHintedDiagramLinkStyle();
			diagramFacet.setHint("Iaml_Wire"); // $NON-NLS-1$
			styles.add(diagramFacet);
		}
		return styles;
	}

	/**
	 * @generated NOT
	 */
	protected void decorateView(View containerView, View view,
			IAdaptable semanticAdapter, String semanticHint, int index,
			boolean persisted) {
		if (semanticHint == null) {
			semanticHint = IamlVisualIDRegistry
					.getType(RunInstanceWireEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
		// start manual
		if (!CompositeWireEditPart.MODEL_ID.equals(IamlVisualIDRegistry
				.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
					.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put(
					"modelID", CompositeWireEditPart.MODEL_ID); //$NON-NLS-1$
			view.getEAnnotations().add(shortcutAnnotation);
		}
		// end manual
		IAdaptable eObjectAdapter = null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			eObjectAdapter = new EObjectAdapter(eObject);
		}
		getViewService().createNode(
				eObjectAdapter,
				view,
				IamlVisualIDRegistry
						.getType(RunInstanceWireNameEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
	}
}
