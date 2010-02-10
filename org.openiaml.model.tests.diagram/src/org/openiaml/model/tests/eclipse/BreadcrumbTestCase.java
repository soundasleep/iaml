/**
 *
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;
import org.openiaml.model.diagram.edit.parts.InternetApplicationEditPart;
import org.openiaml.model.diagram.part.IamlDiagramEditor;

/**
 * Tests opening sub-diagrams of a model diagram in Eclipse and
 * checking the generation of the breadcrumb.
 *
 * @author jmwright
 *
 */
public class BreadcrumbTestCase extends EclipseTestCaseHelper {

	public void testLoadModel() throws Exception {
		// register errors
		addLogListener();

		// copy our local file into the project
		IFile targetModel = project.getFile("generation-sync-multiple.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/generation-sync-multiple.iaml",
				targetModel);
		IFile targetDiagram = project.getFile("generation-sync-multiple.iaml_diagram");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/generation-sync-multiple.iaml_diagram",
				targetDiagram);

		// load up the editor
		IEditorPart ep = loadDiagramFile(targetDiagram);

		// if this is actually an ErrorEditPart, then an error has occured
		// (but it may not be obvious in the log what it is)
		assertEditorRoot(ep);

		// find what elements are displayed
		IamlDiagramEditor editor = (IamlDiagramEditor) ep;
		{
			InternetApplicationEditPart iep = (InternetApplicationEditPart) editor.getDiagramEditPart();
			assertNotNull(iep);
			assertEquals("InternetApplication", iep.getBreadcrumb()); // it has no name
		}

		// check the contents
		ShapeNodeEditPart page2 = assertHasFrame(editor, "page2");

		// open the domain store
		org.openiaml.model.diagram.frame.part.IamlDiagramEditor ep2
			= (org.openiaml.model.diagram.frame.part.IamlDiagramEditor) openDiagram(page2);

		// if this is actually an ErrorEditPart, then an error has occured
		// (but it may not be obvious in the log what it is)
		assertEditorFrame(ep2);

		{
			org.openiaml.model.diagram.frame.edit.parts.FrameEditPart iep
				= (org.openiaml.model.diagram.frame.edit.parts.FrameEditPart) ep2.getDiagramEditPart();
			assertNotNull(iep);
			assertEquals("InternetApplication > Frame: 'page2'", iep.getBreadcrumb()); // it has no name
		}

		// check the contents
		ShapeNodeEditPart form = assertHasInputForm((DiagramDocumentEditor) ep2, "form");

		// open the domain store
		org.openiaml.model.diagram.visual.part.IamlDiagramEditor formd
			= (org.openiaml.model.diagram.visual.part.IamlDiagramEditor) openDiagram(form);

		// if this is actually an ErrorEditPart, then an error has occured
		// (but it may not be obvious in the log what it is)
		assertEditorVisual(formd);

		{
			org.openiaml.model.diagram.visual.edit.parts.VisibleThingEditPart iep
				= (org.openiaml.model.diagram.visual.edit.parts.VisibleThingEditPart) formd.getDiagramEditPart();
			assertNotNull(iep);
			assertEquals("InternetApplication > Frame: 'page2' > InputForm: 'form'", iep.getBreadcrumb()); // it has no name
		}

		// check the contents
		ShapeNodeEditPart form2 = assertHasInputForm((DiagramDocumentEditor) formd, "test form for BreadcrumbTestCase");

		// open the domain store
		org.openiaml.model.diagram.visual.part.IamlDiagramEditor formd2
			= (org.openiaml.model.diagram.visual.part.IamlDiagramEditor) openDiagram(form2);

		// if this is actually an ErrorEditPart, then an error has occured
		// (but it may not be obvious in the log what it is)
		assertEditorVisual(formd2);

		{
			org.openiaml.model.diagram.visual.edit.parts.VisibleThingEditPart iep
				= (org.openiaml.model.diagram.visual.edit.parts.VisibleThingEditPart) formd2.getDiagramEditPart();
			assertNotNull(iep);
			assertEquals("... > Frame: 'page2' > InputForm: 'form' > InputForm: 'test form for BreadcrumbTestCase'", iep.getBreadcrumb()); // it has no name
		}

		// close editors
		((DiagramDocumentEditor) formd2).close(false);
		((DiagramDocumentEditor) formd).close(false);
		((DiagramDocumentEditor) ep2).close(false);
		((DiagramDocumentEditor) editor).close(false);

	}


}
