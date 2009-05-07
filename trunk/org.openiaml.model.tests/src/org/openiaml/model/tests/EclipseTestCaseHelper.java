/**
 * 
 */
package org.openiaml.model.tests;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.RunInstanceWire;
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
	 * Look at the editor's children to see if a Page is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasPage(DiagramDocumentEditor root, String pageName) {
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof Page) {
					Page p = (Page) obj;
					if (p.getName().equals(pageName)) {
						assertNotNull(s);
						return s;
					}
				}
			}
		}
		// failed
		fail("assertHasPage: no page '" + pageName + "' found.");
		return null;
	}

	/**
	 * Look at the editor's children to see if an InputForm is being displayed.
	 * 
	 * @param root
	 * @param formName
	 * @return
	 */
	public ShapeNodeEditPart assertHasInputForm(DiagramDocumentEditor root, String formName) {
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof InputForm) {
					InputForm p = (InputForm) obj;
					if (p.getName().equals(formName)) {
						assertNotNull(s);
						return s;
					}
				}
			}
		}
		// failed
		fail("assertHasInputForm: no input form '" + formName + "' found.");
		return null;
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
	public ShapeNodeEditPart assertHasInputTextField(DiagramDocumentEditor root, String textName, boolean checkShortcut, boolean shortcutRequired) {
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				// check for shortcut status if necessary
				if (!checkShortcut || isShortcut(s) == shortcutRequired) {
					EObject obj = s.resolveSemanticElement();
					if (obj instanceof InputTextField) {
						InputTextField p = (InputTextField) obj;
						if (p.getName().equals(textName)) {
							assertNotNull(s);
							return s;
						}
					}
				}
			}
		}
		// failed
		fail("assertHasInputTextField: no input text field '" + textName + "' found.");
		return null;
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
		String found = "";
		
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof DomainStore) {
					DomainStore p = (DomainStore) obj;
					if (p.getName().equals(storeName)) {
						assertNotNull(s);
						return s;
					}
					found += p.getName() + ",";
				}
			}
		}
		// failed
		fail("assertHasDomainStore: no domain store '" + storeName + "' found. Found: " + found);
		return null;
	}

	/**
	 * Look at the editor's children to see if an Event Trigger is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasEventTrigger(DiagramDocumentEditor root, String eventName) {
		String found = "";
		
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof EventTrigger) {
					EventTrigger p = (EventTrigger) obj;
					if (p.getName().equals(eventName)) {
						assertNotNull(s);
						return s;
					}
					found += p.getName() + ",";
				}
			}
		}
		// failed
		fail("assertHasEventTrigger: no event trigger '" + eventName + "' found. Found: " + found);
		return null;
	}
	
	/**
	 * Look at the editor's children to see if a Domain Attribute is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasDomainAttribute(DiagramDocumentEditor root, String attrName) {
		String found = "";
		
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof DomainAttribute) {
					DomainAttribute p = (DomainAttribute) obj;
					if (p.getName().equals(attrName)) {
						assertNotNull(s);
						return s;
					}
					found += p.getName() + ",";
				}
			}
		}
		// failed
		fail("assertHasDomainAttribute: no domain attribute '" + attrName + "' found. Found: " + found);
		return null;
	}

	/**
	 * Look at the editor's children to see if a Domain Object is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasDomainObject(DiagramDocumentEditor root, String objectName) {
		String found = "";
		
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof DomainObject) {
					DomainObject p = (DomainObject) obj;
					if (p.getName().equals(objectName)) {
						assertNotNull(s);
						return s;
					}
					found += p.getName() + ",";
				}
			}
		}
		// failed
		fail("assertHasDomainObject: no domain object '" + objectName + "' found. Found: " + found);
		return null;
	}
	
	/**
	 * Look at the editor's children to see if a Domain Store is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	public ShapeNodeEditPart assertHasOperation(DiagramDocumentEditor root, String operationName) {
		String found = "";
		
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof Operation) {
					Operation p = (Operation) obj;
					if (p.getName().equals(operationName)) {
						assertNotNull(s);
						return s;
					}
					found += p.getName() + ",";
				}
			}
		}
		// failed
		fail("assertHasOperation: no operation '" + operationName + "' found. Found: " + found);
		return null;
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
	 * Assert that a RunInstanceWire exists between two elements in the editor. 
	 */
	public ConnectionNodeEditPart assertHasSyncWire(DiagramDocumentEditor editor, EditPart source, EditPart target, String name) {
		String found = "";
		
		for (Object c : editor.getDiagramEditPart().getConnections()) {
			if (c instanceof ConnectionNodeEditPart) {
				ConnectionNodeEditPart connection = (ConnectionNodeEditPart) c;
				EObject element = connection.resolveSemanticElement();
				if (element instanceof SyncWire) {
					SyncWire w = (SyncWire) element;
					if (connection.getSource().equals(source) && 
							connection.getTarget().equals(target) && w.getName().equals(name))
						return connection;	// found it
					found += ", " + w.getName();
				}
			}
		}
		
		fail("assertHasSyncWire: no connection found between '" + source + "' and '" + target + "'. found: " + found);
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
				"org.openiaml.model.model.diagram.part.IamlDiagramEditor", 
				editor.getClass().getName());
	}
	
	/**
	 * Assert the given editor is from the visual plugin.
	 */
	public void assertEditorVisual(IEditorPart editor) {
		assertEquals("active editor is the visual plugin", 
				"org.openiaml.model.model.diagram.visual.part.IamlDiagramEditor", 
				editor.getClass().getName());
	}
	
	/**
	 * Assert the given editor is from the operation plugin.
	 */
	public void assertEditorOperation(IEditorPart editor) {
		assertEquals("active editor is the operation plugin", 
				"org.openiaml.model.model.diagram.operation.part.IamlDiagramEditor", 
				editor.getClass().getName());
	}
	
	/**
	 * Assert the given editor is from the domainstore plugin.
	 */
	public void assertEditorDomainStore(IEditorPart editor) {
		assertEquals("active editor is the domain store plugin", 
				"org.openiaml.model.model.diagram.domain_store.part.IamlDiagramEditor", 
				editor.getClass().getName());
	}
	
	/**
	 * Assert the given editor is from the domain_object plugin.
	 */
	public void assertEditorDomainObject(IEditorPart editor) {
		assertEquals("active editor is the domain object plugin", 
				"org.openiaml.model.model.diagram.domain_object.part.IamlDiagramEditor", 
				editor.getClass().getName());
	}
	
	/**
	 * Assert the given editor is from the wire plugin.
	 */
	public void assertEditorWire(IEditorPart editor) {
		assertEquals("active editor is the wire plugin", 
				"org.openiaml.model.model.diagram.wire.part.IamlDiagramEditor", 
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
}
