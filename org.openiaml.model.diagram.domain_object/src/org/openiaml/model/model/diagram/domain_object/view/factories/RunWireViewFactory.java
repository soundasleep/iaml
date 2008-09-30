package org.openiaml.model.model.diagram.domain_object.view.factories;

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
import org.openiaml.model.model.diagram.domain_object.edit.parts.DomainObjectEditPart;
import org.openiaml.model.model.diagram.domain_object.edit.parts.RunWireEditPart;
import org.openiaml.model.model.diagram.domain_object.edit.parts.RunWireNameEditPart;
import org.openiaml.model.model.diagram.domain_object.part.IamlVisualIDRegistry;

/**
 * @generated
 */
public class RunWireViewFactory extends ConnectionViewFactory {

	/**
	 * @generated NOT
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createConnectorStyle());
		styles.add(NotationFactory.eINSTANCE.createFontStyle());
		// start manual
		{
			HintedDiagramLinkStyle diagramFacet = NotationFactory.eINSTANCE
					.createHintedDiagramLinkStyle();
			diagramFacet.setHint("Iaml_Wire"); // $NON-NLS-1$
			styles.add(diagramFacet);
		}
		// end manual
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
					.getType(RunWireEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
		// start manual
		if (!DomainObjectEditPart.MODEL_ID.equals(IamlVisualIDRegistry
				.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
					.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put(
					"modelID", DomainObjectEditPart.MODEL_ID); //$NON-NLS-1$
			view.getEAnnotations().add(shortcutAnnotation);
		}
		// end manual
		IAdaptable eObjectAdapter = null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			eObjectAdapter = new EObjectAdapter(eObject);
		}
		getViewService()
				.createNode(
						eObjectAdapter,
						view,
						IamlVisualIDRegistry
								.getType(RunWireNameEditPart.VISUAL_ID),
						ViewUtil.APPEND, true, getPreferencesHint());
	}
}
