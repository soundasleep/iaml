/**
 *
 */
package org.openiaml.model.tests.eclipse;

import java.util.List;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.HintedDiagramLinkStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.TemporaryVariable;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Adds some helper assert...() methods to the EclipseTestCase.
 *
 * This also allows errors to be logged directly to the console,
 * set up in {@link #setUp()}.
 *
 * @see EclipseTestCase
 * @author jmwright
 *
 */
public abstract class EclipseTestCaseHelper extends EclipseTestCase {

	/**
	 * Add an error log listener.
	 */
	public void setUp() throws Exception {
		super.setUp();

		// register errors
		addLogListener();
	}

	/**
	 * Close all active editors.
	 */
	public void tearDown() throws Exception {
		// close all editors
		PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);

		super.tearDown();
	}

	public void assertExists(IFile file) {
		assertTrue("File '" + file + "' should exist", file.exists());
	}

	public void assertNotExists(IFile file) {
		assertFalse("File '" + file + "' should not exist", file.exists());
	}

	public void assertExists(IFolder folder) {
		assertTrue("Folder '" + folder + "' should exist", folder.exists());
	}

	public void assertNotExists(IFolder folder) {
		assertFalse("Folder '" + folder + "' should not exist", folder.exists());
	}

	/**
	 * Is the given part a shortcut element?
	 *
	 * @param part
	 * @return
	 */
	public boolean isShortcut(ShapeNodeEditPart part) {
		return null != ((View) part.getModel()).getEAnnotation("Shortcut");
	}

	/**
	 * Assert that the given edit part is a shortcut element.
	 */
	public void assertShortcut(ShapeNodeEditPart part) {
		assertTrue("part '" + part + "' does not have a shortcut annotation", isShortcut(part));
	}

	/**
	 * Assert that the given edit part is a shortcut element.
	 */
	public void assertNotShortcut(ShapeNodeEditPart part) {
		assertFalse("part '" + part + "' has a shortcut annotation", isShortcut(part));
	}

	/**
	 * Assert that the given edit part is openable, that is, it has an Open Diagram Hint
	 */
	public void assertOpenable(ShapeNodeEditPart part) {
		View view = (View) part.getModel();
		Style link = view.getStyle(NotationPackage.eINSTANCE
				.getHintedDiagramLinkStyle());
		if (link == null || !(link instanceof HintedDiagramLinkStyle))
			fail("part '" + part + "' is not a shortcut, it should be (link=" + link + ")");
	}

	/**
	 * Assert that the given edit part is not openable, that is, it does not have an Open Diagram Hint
	 */
	public void assertNotOpenable(ShapeNodeEditPart part) {
		View view = (View) part.getModel();
		Style link = view.getStyle(NotationPackage.eINSTANCE
				.getHintedDiagramLinkStyle());
		if (!(link == null || !(link instanceof HintedDiagramLinkStyle)))
			fail("part '" + part + "' is not a shortcut, it should be (link=" + link + ")");
	}

	/**
	 * An abstract method which checks an editors children to see
	 * if the editor contains a given model element, with the given
	 * shortcut parameters.
	 *
	 * @param root the editor to search
	 * @param objectClass the EObject class to look for
	 * @param name the name of the NamedElement
	 * @param checkShortcut
	 * @param shortcutRequired
	 * @return
	 */
	protected ShapeNodeEditPart assertHasRenderedNamedObject(DiagramDocumentEditor root,
			Class<? extends NamedElement> objectClass,
			String name,
			boolean checkShortcut,
			boolean shortcutRequired) {
		// debug
		String found = "";

		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				// check for shortcut status if necessary
				if (!checkShortcut || isShortcut(s) == shortcutRequired) {
					EObject obj = s.resolveSemanticElement();
					if (objectClass.isInstance(obj)) {
						NamedElement e = (NamedElement) obj;
						if (e.getName().equals(name)) {
							assertNotNull(s);
							return s;
						}
						found += e.getName() + ",";
					}
				}
			}
		}

		// failed
		fail("No " + objectClass.getSimpleName() + " named '" + name + "' found in editor " + root + ". Found: " + found);
		return null;
	}

	/**
	 * Look at the editor's children to see if a Session is being displayed.
	 *
	 * @param root
	 * @param sessionName
	 * @return
	 */
	public ShapeNodeEditPart assertHasSession(DiagramDocumentEditor root, String sessionName) {
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof Session) {
					Session p = (Session) obj;
					if (p.getName() != null && p.getName().equals(sessionName)) {
						assertNotNull(s);
						return s;
					}
				}
			}
		}
		// failed
		fail("assertHasSession: no Session '" + sessionName + "' found.");
		return null;
	}

	/**
	 * Look at the editor's children to see if an InputForm is being displayed.
	 *
	 * @param editor
	 * @param name
	 * @return
	 */
	public ShapeNodeEditPart assertHasInputForm(DiagramDocumentEditor editor, String name, boolean checkShortcut, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, InputForm.class, name, checkShortcut, shortcutRequired);
	}

	/**
	 * Look at the editor's children to see if an Page is being displayed.
	 *
	 * @param editor
	 * @param name
	 * @return
	 */
	public ShapeNodeEditPart assertHasPage(DiagramDocumentEditor editor, String name, boolean checkShortcut, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, Page.class, name, checkShortcut, shortcutRequired);
	}

	/**
	 * Look at the editor's children to see if an InputForm is being displayed.
	 *
	 * @see #isShortcut(ShapeNodeEditPart)
	 * @param root
	 * @param textName
	 * @param checkShortcut should we check to see if the element has a shortcut?
	 * @param shortcutRequired if checkShortcut is true, only search for parts where isShortcut(part) = shortcutRequired
	 * @return
	 */
	public ShapeNodeEditPart assertHasInputTextField(DiagramDocumentEditor root, String textName,
			boolean checkShortcut, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(root, InputTextField.class, textName, checkShortcut, shortcutRequired);
	}

	/**
	 * Simply calls {@link #assertHasInputTextField(DiagramDocumentEditor, String, boolean, boolean)},
	 * does not check the EditPart for shortcuts.
	 *
	 * @see #assertHasInputTextField(DiagramDocumentEditor, String, boolean, boolean)
	 * @param root
	 * @param textName
	 * @return
	 */
	public ShapeNodeEditPart assertHasInputTextField(DiagramDocumentEditor root, String textName) {
		return assertHasInputTextField(root, textName, false, false);
	}

	/**
	 * Look at the editor's children to see if a StartNode is being displayed.
	 * Note that we can't specify which StartNode to look for.
	 *
	 * @param root
	 * @return
	 */
	public ShapeNodeEditPart assertHasStartNode(DiagramDocumentEditor root) {
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof StartNode) {
					assertNotNull(s);
					return s;
				}
			}
		}
		// failed
		fail("assertHasStartNode: no start node found.");
		return null;
	}

	/**
	 * Look at the editor's children to see if a Domain Store is being displayed.
	 *
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasDomainStore(DiagramDocumentEditor root, String storeName) {
		return assertHasRenderedNamedObject(root, DomainStore.class, storeName, false, false);
	}

	/**
	 * Look at the editor's children to see if an Event Trigger is being displayed.
	 *
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasEventTrigger(DiagramDocumentEditor root, String eventName, boolean checkShortcut, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(root, EventTrigger.class, eventName, checkShortcut, shortcutRequired);
	}

	/**
	 * Look at the editor's children to see if a Domain Attribute is being displayed.
	 *
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasDomainAttribute(DiagramDocumentEditor root, String attrName, boolean checkShortcut, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(root, DomainAttribute.class, attrName, checkShortcut, shortcutRequired);
	}

	/**
	 * Look at the editor's children to see if a Domain Object is being displayed.
	 *
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasDomainObject(DiagramDocumentEditor root, String objectName, boolean checkShortcut, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(root, DomainObject.class, objectName, checkShortcut, shortcutRequired);
	}

	/**
	 * Look at the editor's children to see if a Domain Object Instance is being displayed.
	 *
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasDomainObjectInstance(DiagramDocumentEditor root, String objectName, boolean checkShortcut, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(root, DomainObjectInstance.class, objectName, checkShortcut, shortcutRequired);
	}

	/**
	 * Look at the editor's children to see if an Operation is being displayed.
	 *
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasOperation(DiagramDocumentEditor root, String operationName,
			boolean checkShortcut, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(root, Operation.class, operationName, checkShortcut, shortcutRequired);
	}

	/**
	 * Look at the editor's children to see if a PrimitiveOperation is being displayed.
	 *
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasPrimitiveOperation(DiagramDocumentEditor root, String operationName,
			boolean checkShortcut, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(root, PrimitiveOperation.class, operationName, checkShortcut, shortcutRequired);
	}

	/**
	 * Look at the editor's children to see if a CompositeCondition is being displayed.
	 *
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasCompositeCondition(DiagramDocumentEditor root, String operationName,
			boolean checkShortcut, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(root, CompositeCondition.class, operationName, checkShortcut, shortcutRequired);
	}

	/**
	 * Assert that a RunInstanceWire exists between two elements in the editor.
	 */
	public ConnectionNodeEditPart assertHasRunInstanceWire(DiagramDocumentEditor editor, EditPart source, EditPart target, String name) {
		String found = "";

		for (Object c : editor.getDiagramEditPart().getConnections()) {
			if (c instanceof ConnectionNodeEditPart) {
				ConnectionNodeEditPart connection = (ConnectionNodeEditPart) c;
				EObject element = connection.resolveSemanticElement();
				if (element instanceof RunInstanceWire) {
					RunInstanceWire w = (RunInstanceWire) element;
					if (connection.getSource().equals(source) &&
							connection.getTarget().equals(target) && w.getName().equals(name))
						return connection;	// found it
					found += ", " + w.getName();
				}
			}
		}

		fail("assertHasRunInstanceWire: no connection found between '" + source + "' and '" + target + "' with name '" + name + "'. found: " + found);
		return null;
	}

	/**
	 * Assert that a SelectWire exists between two elements in the editor.
	 */
	public ConnectionNodeEditPart assertHasSelectWire(DiagramDocumentEditor editor, EditPart source, EditPart target, String name) {
		String found = "";

		for (Object c : editor.getDiagramEditPart().getConnections()) {
			if (c instanceof ConnectionNodeEditPart) {
				ConnectionNodeEditPart connection = (ConnectionNodeEditPart) c;
				EObject element = connection.resolveSemanticElement();
				if (element instanceof SelectWire) {
					SelectWire w = (SelectWire) element;
					if (connection.getSource().equals(source) &&
							connection.getTarget().equals(target) && w.getName().equals(name))
						return connection;	// found it
					found += ", " + w.getName();
				}
			}
		}

		fail("assertHasSelectWire: no connection found between '" + source + "' and '" + target + "' with name '" + name + "'. found: " + found);
		return null;
	}

	/**
	 * Assert that a DataFlowEdge exists between two elements in the editor.
	 */
	public ConnectionNodeEditPart assertHasDataFlowEdge(DiagramDocumentEditor editor, EditPart source, EditPart target) {
		for (Object c : editor.getDiagramEditPart().getConnections()) {
			if (c instanceof ConnectionNodeEditPart) {
				ConnectionNodeEditPart connection = (ConnectionNodeEditPart) c;
				EObject element = connection.resolveSemanticElement();
				if (element instanceof DataFlowEdge) {
					if (connection.getSource().equals(source) &&
							connection.getTarget().equals(target))
						return connection;	// found it
				}
			}
		}

		fail("assertHasDataFlowEdge: no data flow edge found between '" + source + "' and '" + target + "'");
		return null;
	}

	/**
	 * Assert that a SyncWire exists between two elements in the editor.
	 *
	 * SyncWires are bidirectional, so the order of elements in the parameters
	 * do not matter.
	 */
	public ConnectionNodeEditPart assertHasSyncWire(DiagramDocumentEditor editor,
			EditPart part1, EditPart part2, String name) {
		String found = "";

		for (Object c : editor.getDiagramEditPart().getConnections()) {
			if (c instanceof ConnectionNodeEditPart) {
				ConnectionNodeEditPart connection = (ConnectionNodeEditPart) c;
				EObject element = connection.resolveSemanticElement();
				if (element instanceof SyncWire) {
					SyncWire w = (SyncWire) element;
					// SyncWires are bidirectional
					if (connection.getSource().equals(part1) &&
							connection.getTarget().equals(part2) && w.getName().equals(name))
						return connection;	// found it (a->b)
					if (connection.getSource().equals(part2) &&
							connection.getTarget().equals(part1) && w.getName().equals(name))
						return connection;	// found it (b->a)
					found += ", " + w.getName();
				}
			}
		}

		fail("assertHasSyncWire: no connection found between '" + part1 + "' and '" + part2 + "'. found: " + found);
		return null;
	}

	/**
	 * Assert that an ExecutionEdge exists between two elements in the editor.
	 */
	public ConnectionNodeEditPart assertHasExecutionEdge(DiagramDocumentEditor editor, EditPart source, EditPart target) {

		for (Object c : editor.getDiagramEditPart().getConnections()) {
			if (c instanceof ConnectionNodeEditPart) {
				ConnectionNodeEditPart connection = (ConnectionNodeEditPart) c;
				EObject element = connection.resolveSemanticElement();
				if (element instanceof ExecutionEdge) {
					if (connection.getSource().equals(source) &&
							connection.getTarget().equals(target))
						return connection;	// found it
				}
			}
		}

		fail("assertHasExecutionEdge: no connection found between '" + source + "' and '" + target + "'");
		return null;
	}

	/**
	 * Assert that these two EditParts refer to the same semantic element.
	 */
	public void assertSameReferencedElement(EditPart a, EditPart b) {
		// TODO remove XXX checks. even though the two elements DO
		// refer to the same semantic element, because we do not yet
		// share editing domains, they are loaded as different instances
		// and thus cannot be considered equal.
		// or at the very least, .equals() returns false because they are different instances.

		if (a instanceof ConnectionEditPart && b instanceof ConnectionEditPart) {
			assertEquals(
					((GeneratedElement) ((ConnectionEditPart) a).resolveSemanticElement()).getId(),
					((GeneratedElement) ((ConnectionEditPart) b).resolveSemanticElement()).getId());
			// XXX assertEquals(((ConnectionEditPart) a).resolveSemanticElement(), ((ConnectionEditPart) b).resolveSemanticElement());
		} else if (a instanceof ShapeNodeEditPart && b instanceof ShapeNodeEditPart) {
			assertEquals(
					((GeneratedElement) ((ShapeNodeEditPart) a).resolveSemanticElement()).getId(),
					((GeneratedElement) ((ShapeNodeEditPart) b).resolveSemanticElement()).getId());
			// XXX assertEquals(((ShapeNodeEditPart) a).resolveSemanticElement(), ((ShapeNodeEditPart) b).resolveSemanticElement());
		} else {
			fail("a and b are not of the same type of part. a='" + a + "', b='" + b + "'");
		}
	}

	/**
	 * Assert the given editor is from the root plugin.
	 */
	public void assertEditorRoot(IEditorPart editor) {
		assertEquals("active editor is the root plugin",
				"org.openiaml.model.diagram.part.IamlDiagramEditor",
				editor.getClass().getName());
	}

	/**
	 * Assert the given editor is from the visual plugin.
	 */
	public void assertEditorVisual(IEditorPart editor) {
		assertEquals("active editor is the visual plugin",
				"org.openiaml.model.diagram.visual.part.IamlDiagramEditor",
				editor.getClass().getName());
	}

	/**
	 * Assert the given editor is from the operation plugin.
	 */
	public void assertEditorOperation(IEditorPart editor) {
		assertEquals("active editor is the operation plugin",
				"org.openiaml.model.diagram.operation.part.IamlDiagramEditor",
				editor.getClass().getName());
	}

	/**
	 * Assert the given editor is from the domainstore plugin.
	 */
	public void assertEditorDomainStore(IEditorPart editor) {
		assertEquals("active editor is the domain store plugin",
				"org.openiaml.model.diagram.domain_store.part.IamlDiagramEditor",
				editor.getClass().getName());
	}

	/**
	 * Assert the given editor is from the condition plugin.
	 */
	public void assertEditorCondition(IEditorPart editor) {
		assertEquals("active editor is the condition plugin",
				"org.openiaml.model.diagram.condition.part.IamlDiagramEditor",
				editor.getClass().getName());
	}

	/**
	 * Assert the given editor is from the domain_object plugin.
	 */
	public void assertEditorDomainObject(IEditorPart editor) {
		assertEquals("active editor is the domain object plugin",
				"org.openiaml.model.diagram.domain_object.part.IamlDiagramEditor",
				editor.getClass().getName());
	}

	/**
	 * Assert the given editor is from the wire plugin.
	 */
	public void assertEditorWire(IEditorPart editor) {
		assertEquals("active editor is the wire plugin",
				"org.openiaml.model.diagram.wire.part.IamlDiagramEditor",
				editor.getClass().getName());
	}

	/**
	 * Before jumping into an Eclipse test, this method should be called
	 * so we can catch any exceptions that occur during loading, and
	 * print them out to the log.
	 */
	protected void addLogListener() {
		Platform.addLogListener(new ILogListener() {
			@Override
			public void logging(IStatus status, String plugin) {
				// rethrow if exception is caught
				if (status.getSeverity() == IStatus.ERROR) {
					// JUnit won't actually catch this, because the Platform is
					// in a different thread. however we will still get the
					// stack trace in System.err so this remains somewhat useful.
					//throw new RuntimeException(status.getMessage(), status.getException());
					System.err.println(status);
					status.getException().printStackTrace(System.err);
				} else {
					// otherwise just print out the error
					System.err.println(status);
				}
				setLastError(status);
			}});
	}

	private IStatus lastErrorStatus = null;

	/**
	 * When we add a log listener ({@link #addLogListener()}, this
	 * method keeps track of the last reported error, hopefully
	 * so we can add stack traces and the like.
	 *
	 * @param status the last status found
	 */
	protected void setLastError(IStatus status) {
		lastErrorStatus = status;
	}

	/**
	 * Check that the given part is not an ErrorPart.
	 * If it is, see whatever status was thrown last ({@link #setLastError(IStatus)},
	 * and throw an exception so we can trace it.
	 *
	 * @param part
	 */
	protected void checkNotErrorPart(IEditorPart part) {
		// we cannot directly 'instanceof', because ErrorEditorPart is
		// in an internal package
		if (part.getClass().getSimpleName().equals("ErrorEditorPart")) {
			if (lastErrorStatus == null) {
				throw new RuntimeException("Loaded part is an ErrorEditorPart (no exception): " + part);
			} else {
				throw new RuntimeException(lastErrorStatus.getMessage() + ": " + part, lastErrorStatus.getException());
			}
		}
	}

	/**
	 * Load a diagram file in Eclipse.
	 * Also checks that it is not an ErrorEditorPart.
	 * @see EclipseTestCase#loadDiagramFile(IFile)
	 */
	protected IEditorPart loadDiagramFile(IFile diagramFile) throws Exception {
		IEditorPart part = super.loadDiagramFile(diagramFile);
		checkNotErrorPart(part);
		return part;
	}

	/**
	 * Check the number of children nodes in this editor.
	 * The "number of children" are the number of nodes, not the number of
	 * edges, visible in the current editor.
	 *
	 * @param i
	 * @param sub
	 */
	public void assertEditorHasChildren(int i, DiagramDocumentEditor sub) {
		assertEquals("There should be " + i + " children in editor '" + sub.getTitle() + "'", i, sub.getDiagramEditPart().getChildren().size());
	}

	/**
	 * @see #assertHasDomainObject(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasDomainObject(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, DomainObject.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasDomainObjectInstance(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasDomainObjectInstance(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, DomainObjectInstance.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasDomainAttribute(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasDomainAttribute(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, DomainAttribute.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasEventTrigger(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasEventTrigger(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, EventTrigger.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasInputTextField(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasInputTextField(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, InputTextField.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasInputForm(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasInputForm(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, InputForm.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasPage(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasPage(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, Page.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasOperation(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasOperation(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, Operation.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasCompositeCondition(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasCompositeCondition(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, CompositeCondition.class, name, true, shortcutRequired);
	}
	
	/**
	 * @see #assertHasCondition(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasCondition(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, Condition.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasApplicationElementProperty(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasApplicationElementProperty(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, ApplicationElementProperty.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasTemporaryVariable(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasTemporaryVariable(
			DiagramDocumentEditor editor, String name, boolean shortcutRequired) {
		return assertHasRenderedNamedObject(editor, TemporaryVariable.class, name, true, shortcutRequired);
	}

	/**
	 * @see #assertHasDomainObject(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasDomainObject(
			DiagramDocumentEditor editor, String name) {
		return assertHasDomainObject(editor, name, false, false);
	}

	/**
	 * @see #assertHasDomainAttribute(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasDomainAttribute(
			DiagramDocumentEditor editor, String name) {
		return assertHasDomainAttribute(editor, name, false, false);
	}

	/**
	 * @see #assertHasEventTrigger(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasEventTrigger(
			DiagramDocumentEditor editor, String name) {
		return assertHasEventTrigger(editor, name, false, false);
	}

	/**
	 * @see #assertHasInputForm(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasInputForm(
			DiagramDocumentEditor editor, String name) {
		return assertHasInputForm(editor, name, false, false);
	}

	/**
	 * @see #assertHasPage(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasPage(
			DiagramDocumentEditor editor, String name) {
		return assertHasPage(editor, name, false, false);
	}

	/**
	 * @see #assertHasOperation(DiagramDocumentEditor, String, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasOperation(
			DiagramDocumentEditor editor, String name) {
		return assertHasOperation(editor, name, false, false);
	}


	/**
	 * Assert that a ParameterWire exists between two elements in the editor.
	 *
	 * @param editor
	 * @param source
	 * @param target
	 * @return
	 */
	public ConnectionNodeEditPart assertHasParameterWire(DiagramDocumentEditor editor,
			ShapeNodeEditPart source, ConnectionNodeEditPart target) {

		String found = "";

		for (Object c : editor.getDiagramEditPart().getConnections()) {
			if (c instanceof ConnectionNodeEditPart) {
				ConnectionNodeEditPart connection = (ConnectionNodeEditPart) c;
				EObject element = connection.resolveSemanticElement();
				if (element instanceof ParameterWire) {
					ParameterWire w = (ParameterWire) element;
					if (connection.getSource().equals(source) &&
							connection.getTarget().equals(target))
						return connection;	// found it
					found += ", " + w.getName();
				}
			}
		}

		fail("assertHasParameterWire: no connection found between '" + source + "' and '" + target + "'. found: " + found);
		return null;

	}

	/**
	 * The given edit part must not be generated.
	 *
	 * @param part
	 */
	public void assertNotGenerated(ShapeNodeEditPart part) {
		assertFalse("EditPart '" + part + "' should not be generated", ((GeneratedElement) part.resolveSemanticElement()).isIsGenerated());
	}

	/**
	 * The given edit part must be generated.
	 *
	 * @param part
	 */
	public void assertGenerated(ShapeNodeEditPart part) {
		assertTrue("EditPart '" + part + "' should be generated", ((GeneratedElement) part.resolveSemanticElement()).isIsGenerated());
	}

	/**
	 * The given edit part must not be generated.
	 *
	 * @param part
	 */
	public void assertNotGenerated(ConnectionNodeEditPart part) {
		assertFalse("EditPart '" + part + "' should not be generated", ((GeneratedElement) part.resolveSemanticElement()).isIsGenerated());
	}

	/**
	 * The given edit part must be generated.
	 *
	 * @param part
	 */
	public void assertGenerated(ConnectionNodeEditPart part) {
		assertTrue("EditPart '" + part + "' should be generated", ((GeneratedElement) part.resolveSemanticElement()).isIsGenerated());
	}

	/**
	 * @see #assertHasFieldValue(DiagramDocumentEditor, boolean, boolean)
	 */
	public ShapeNodeEditPart assertHasFieldValue(DiagramDocumentEditor editor, boolean shortcutRequired) {
		return assertHasFieldValue(editor, true, shortcutRequired);
	}

	/**
	 * The editor should contain an ApplicationElementProperty called
	 * 'fieldValue' that is shortcut=requiredShortcut.
	 *
	 * @param editor
	 * @param checkShortcut should we check if it's a shortcut?
	 * @param shortcutRequired the required value of shortcut
	 * @return
	 */
	public ShapeNodeEditPart assertHasFieldValue(
			DiagramDocumentEditor editor, boolean checkShortcut,
			boolean shortcutRequired) {
		String found = "";

		for (Object o : editor.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				if (!checkShortcut || isShortcut(s) == shortcutRequired) {
					EObject obj = s.resolveSemanticElement();
					if (obj instanceof ApplicationElementProperty) {
						ApplicationElementProperty p = (ApplicationElementProperty) obj;
						if (p.getName().equals("fieldValue")) {
							assertNotNull(s);
							return s;
						}
						found += p.getName() + ",";
					}
				}
			}
		}
		// failed
		fail("assertHasFieldValue: no fieldValue found. Found: " + found);
		return null;

	}

	/**
	 * Force the deletion of the given file, and halt until it is.
	 *
	 * @param diagram
	 * @throws Exception
	 */
	public void forceDelete(IFile diagram) throws Exception {
		diagram.delete(true, monitor);

		for (int i = 0; i < 300; i++) {
			refreshProject();
			if (!diagram.exists())
				return;		// done
			Thread.sleep(300);	// wait
		}

		fail("Could not successfully force delete diagram file '" + diagram + "'");

	}

	public void assertNotHasPage(DiagramDocumentEditor editor, String string) {
		boolean failed = false;
		try {
			assertHasPage(editor, string);
			failed = true;
		} catch (AssertionFailedError e) {
			// expected
		}
		assertFalse("Editor had unexpected page '" + string + "'", failed);
	}

	public void assertNotHasInputTextField(DiagramDocumentEditor editor_page2, String string) {
		boolean failed = false;
		try {
			assertHasInputTextField(editor_page2, string);
			failed = true;
		} catch (AssertionFailedError e) {
			// expected
		}
		assertFalse("Editor had unexpected text field '" + string + "'", failed);
	}

	public void assertNotHasInputForm(DiagramDocumentEditor editor_page2, String string) {
		boolean failed = false;
		try {
			assertHasInputForm(editor_page2, string);
			failed = true;
		} catch (AssertionFailedError e) {
			// expected
		}
		assertFalse("Editor had unexpected input form '" + string + "'", failed);
	}

	/**
	 * Assert that the given list contains the given object
	 */
	public void assertContains(Object obj,
			List<? extends Object> list) {

		assertTrue("List should contain '" + obj + "': current list " + list, list.contains(obj));

	}

}
