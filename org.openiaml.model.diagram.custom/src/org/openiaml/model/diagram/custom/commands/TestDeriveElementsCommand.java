/**
 * More information on this code: http://www.jevon.org/wiki/GMF_Code_Samples
 */
package org.openiaml.model.diagram.custom.commands;

import iaml.generated2.ExternalFactStore4InternetApplication_Name;
import iaml.generated2.ExternalFactStore4app_children;
import iaml.generated2.ExternalFactStore4element_children;
import iaml.generated2.ExternalFactStore4input_form_name;
import iaml.generated2.ExternalFactStore4page_name;
import iaml.generated2.FactStores;
import iaml.generated2.GeneratedAppChildren;
import iaml.generated2.InternetApplication_Name;
import iaml.generated2.KBGenerated;
import iaml.generated2.element_children;
import iaml.generated2.input_form_name;
import iaml.generated2.page_name;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nz.org.take.rt.ResourceIterator;
import nz.org.take.rt.ResultSet;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.jaxen.JaxenException;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.diagram.edit.parts.InternetApplicationEditPart;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.Page;

import ca.ecliptical.emf.xpath.EMFXPath;


/**
 * 
 * @author jmwright
 *
 */
public class TestDeriveElementsCommand 
 	implements IObjectActionDelegate {

	public class ResourceIteratorWrapper<T> implements
			ResourceIterator<T> {

		private Iterator<T> it;
		
		public ResourceIteratorWrapper(Iterator<T> it) {
			this.it = it;
		}
		
		public ResourceIteratorWrapper(T object) {
			ArrayList<T> temp = new ArrayList<T>();
			temp.add(object);
			this.it = temp.iterator();
		}

		@Override
		public void close() {
			// do nothing
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public T next() {
			return it.next();
		}

		@Override
		public void remove() {
			it.remove();
		}

	}

	private InternetApplicationEditPart selectedElement;

	private class Impl_ExternalFactStore4element_children
		extends FactStoreToEMF<Page,InputForm,element_children>
		implements ExternalFactStore4element_children {

		private InternetApplication ia;
		public Impl_ExternalFactStore4element_children(
				InternetApplication ia) {
			this.ia = ia;
		}
			
		@Override
		public ResourceIterator<iaml.generated2.element_children> fetch(
				ApplicationElementContainer slot1, ApplicationElement slot2) {
			List<iaml.generated2.element_children> listResults = new ArrayList<iaml.generated2.element_children>();

			if (slot1 == null) {
				// get all InputForms in Pages
				// 1. get all children
				List<?> results;
				try {
					results = query(ia, "//iaml:children");
				} catch (JaxenException e) {
					e.printStackTrace();
					results = new ArrayList(); // empty
				}
				
				// 2. get all Pages
				List<Page> listPages = new ArrayList<Page>();
				for (Object o : results) {
					if (o instanceof Page) {
						listPages.add((Page) o);
					}
				}
				
				// 3. get all InputForms in these pages
				for (Page p : listPages) {
					for (EObject eo : p.getChildren()) {
						if (eo instanceof InputForm) {
							// 4. filter to slot2 if necessary
							if (slot2 == null || slot2.equals(eo)) {
								listResults.add(new iaml.generated2.element_children(p, (InputForm) eo));
							}
						}
					}
				}
			} else {
				// get all InputForms in a certain Page
				for (EObject eo : slot1.getChildren()) {
					if (eo instanceof InputForm) {
						// 4. filter to slot2 if necessary
						if (slot2 == null || slot2.equals(eo)) {
							listResults.add(new iaml.generated2.element_children(slot1, (InputForm) eo));
						}
					}
				}
			}
			
			// now return iterator
			return new ResourceIteratorWrapper<iaml.generated2.element_children>(listResults.iterator());
		}

	}

		
		private class Impl_ExternalFactStore4InternetApplication_Children
			extends FactStoreToEMF<InternetApplication,Page,iaml.generated2.app_children>
			implements ExternalFactStore4app_children {

				private InternetApplication ia;
				public Impl_ExternalFactStore4InternetApplication_Children(
						InternetApplication ia) {
					this.ia = ia;
				}
					
				@Override
				public ResourceIterator<iaml.generated2.app_children> fetch(
						InternetApplication slot1, ApplicationElement slot2) {
					List<iaml.generated2.app_children> listResults = new ArrayList<iaml.generated2.app_children>();

					if (slot1 == null) {
						// get all InputForms in Pages
						// 1. get all children
						List<?> results;
						try {
							results = query(ia, ".");
						} catch (JaxenException e) {
							e.printStackTrace();
							results = new ArrayList(); // empty
						}
						
						// 2. get all Pages
						List<InternetApplication> listPages = new ArrayList<InternetApplication>();
						for (Object o : results) {
							if (o instanceof InternetApplication) {
								listPages.add((InternetApplication) o);
							}
						}
						
						// 3. get all InputForms in these pages
						for (InternetApplication p : listPages) {
							for (EObject eo : p.getChildren()) {
								if (eo instanceof Page) {
									// 4. filter to slot2 if necessary
									if (slot2 == null || slot2.equals(eo)) {
										listResults.add(new iaml.generated2.app_children(p, (Page) eo));
									}
								}
							}
						}
					} else {
						// get all InputForms in a certain Page
						for (EObject eo : slot1.getChildren()) {
							if (eo instanceof Page) {
								// 4. filter to slot2 if necessary
								if (slot2 == null || slot2.equals(eo)) {
									listResults.add(new iaml.generated2.app_children(slot1, (Page) eo));
								}
							}
						}
					}
					
					// now return iterator
					return new ResourceIteratorWrapper<iaml.generated2.app_children>(listResults.iterator());
				}

		}
					
		
	private class Impl_ExternalFactStore4NamedElement_Name
		extends FactStoreToEMF<InputForm,String,input_form_name>
		implements ExternalFactStore4input_form_name {

			private InternetApplication ia;
			public Impl_ExternalFactStore4NamedElement_Name(
					InternetApplication ia) {
				this.ia = ia;
			}
				
			@Override
			public ResourceIterator<iaml.generated2.input_form_name> fetch(
					InputForm slot1, String slot2) {
				List<iaml.generated2.input_form_name> listResults = new ArrayList<iaml.generated2.input_form_name>();

				if (slot1 == null) {
					// get all names in InputForms
					// 1. get all children
					List<?> results;
					try {
						results = query(ia, "//iaml:children");
					} catch (JaxenException e) {
						e.printStackTrace();
						results = new ArrayList(); // empty
					}
					
					// 2. get all Pages
					List<InputForm> listPages = new ArrayList<InputForm>();
					for (Object o : results) {
						if (o instanceof InputForm) {
							listPages.add((InputForm) o);
						}
					}
					
					// 3. get all InputForms in these pages
					for (InputForm p : listPages) {
						// 4. filter to slot2 if necessary
						if (slot2 == null || slot2.equals(p.getName())) {
							listResults.add(new iaml.generated2.input_form_name(p, p.getName()));
						}
					}
				} else {
					// get all InputForms in a certain Page
					if (slot2 == null || slot2.equals(slot1.getName())) {
						listResults.add(new iaml.generated2.input_form_name(slot1, slot1.getName()));
					}

				}
				
				// now return iterator
				return new ResourceIteratorWrapper<iaml.generated2.input_form_name>(listResults.iterator());
			}
		
	}
		
	private class Impl_ExternalFactStore4page_name 
		extends FactStoreToEMF<Page,String,iaml.generated2.page_name>
		implements ExternalFactStore4page_name  {

			private InternetApplication ia;
			public Impl_ExternalFactStore4page_name(
					InternetApplication ia) {
				this.ia = ia;
			}
				
			@Override
			public ResourceIterator<page_name> fetch(Page slot1,
					String slot2) {
				List<page_name> listResults = new ArrayList<page_name>();

				if (slot1 == null) {
					// get all names in InputForms
					// 1. get all children
					List<?> results;
					try {
						results = query(ia, "//iaml:children");
					} catch (JaxenException e) {
						e.printStackTrace();
						results = new ArrayList(); // empty
					}
					
					// 2. get all Pages
					List<Page> listPages = new ArrayList<Page>();
					for (Object o : results) {
						if (o instanceof Page) {
							listPages.add((Page) o);
						}
					}
					
					// 3. get all InputForms in these pages
					for (Page p : listPages) {
						// 4. filter to slot2 if necessary
						if (slot2 == null || slot2.equals(p.getName())) {
							listResults.add(new page_name(p, p.getName()));
						}
					}
				} else {
					// get all InputForms in a certain Page
					if (slot2 == null || slot2.equals(slot1.getName())) {
						listResults.add(new page_name(slot1, slot1.getName()));
					}

				}
				
				// now return iterator
				return new ResourceIteratorWrapper<page_name>(listResults.iterator());
			}

	}

	private class Impl_ExternalFactStore4app_name
		extends FactStoreToEMF<InternetApplication,String,InternetApplication_Name>
		implements ExternalFactStore4InternetApplication_Name {


			private InternetApplication ia;
			public Impl_ExternalFactStore4app_name(
					InternetApplication ia) {
				this.ia = ia;
			}
				
			@Override
			public ResourceIterator<iaml.generated2.InternetApplication_Name> fetch(
					InternetApplication slot1, String slot2) {
				List<iaml.generated2.InternetApplication_Name> listResults = new ArrayList<iaml.generated2.InternetApplication_Name>();

				if (slot1 == null) {
					// get all names in InputForms
					// 1. get all children
					List<?> results;
					try {
						results = query(ia, ".");
					} catch (JaxenException e) {
						e.printStackTrace();
						results = new ArrayList<Object>(); // empty
					}
					
					// 2. get all Pages
					List<InternetApplication> listPages = new ArrayList<InternetApplication>();
					for (Object o : results) {
						if (o instanceof InternetApplication) {
							listPages.add((InternetApplication) o);
						}
					}
					
					// 3. get all InputForms in these pages
					for (InternetApplication p : listPages) {
						// 4. filter to slot2 if necessary
						if (slot2 == null || slot2.equals(p.getName())) {
							listResults.add(new iaml.generated2.InternetApplication_Name(p, p.getName()));
						}
					}
				} else {
					// get all InputForms in a certain Page
					if (slot2 == null || slot2.equals(slot1.getName())) {
						listResults.add(new iaml.generated2.InternetApplication_Name(slot1, slot1.getName()));
					}

				}
				
				// now return iterator
				return new ResourceIteratorWrapper<iaml.generated2.InternetApplication_Name>(listResults.iterator());
			}

	}
		
	private abstract class FactStoreToEMF<Slot1,Slot2,TakeElement> {
		/*
		private InternetApplication root;
		private String query;
		private String childrenMethod;
		private Class slot1class = null;
		private Class slot2class;
		public FactStoreToEMF(InternetApplication root, String query, String childrenMethod, Class slot2class) {
			this.root = root;
			this.query = query;
			this.childrenMethod = childrenMethod;
			this.slot2class = slot2class;
		}
		public FactStoreToEMF(InternetApplication root, String query, Class slot1class, String childrenMethod, Class slot2class) {
			this(root, query, childrenMethod, slot2class);
			this.slot1class = slot1class;
		}

		public ResourceIterator<TakeElement> fetch(
				Slot1 slot1, Slot2 slot2) {
			List results;
			if (slot1 == null) {
				// find all input forms
				try {
					results = query(root, query);
				} catch (JaxenException e) {
					results = new ArrayList(); // empty
					e.printStackTrace();
				}

				// reduce results to particular classes?
				if (slot1class != null) {
					results = filterOut(results, slot1class);
					
					// for each of the results, we want to apply the children method to
					// get our desired value out
					for (Object o : results) {
						Class c = o.getClass();
						Method m = c.getMethod(childrenMethod, null);
						
					}
				}

			} else {
				// find only local input forms
				try {
					Class c = slot1.getClass();
					Method m = c.getMethod(childrenMethod, null);
					results = filterOut(m.invoke(slot1), slot2class);
				} catch (Exception e) {
					results = new ArrayList(); // empty
					e.printStackTrace();
				}
			}
			
			// now filter out ones that don't match slot2, if specified
			if (slot2 != null)
				results = filterOutInstances(results, slot2);
			
			return new ResourceIteratorWrapper<TakeElement>(results.iterator());
		}
		*/

		/**
		 * If we only have one result (e.g. getName() method), we can't really
		 * sort through a list of them! So we have to wrap it in a list.
		 * 
		 * @todo if obj isnt an instanceof class1, we can probably stop right here
		 * @param obj
		 * @param class1
		 * @return
		 */
		private List filterOut(Object obj, Class class1) {
			if (!(obj instanceof List)) {
				ArrayList a = new ArrayList();
				a.add(obj);
				return filterOut(a, class1);
			} else {
				return filterOut((List) obj, class1);
			}
		}

		/**
		 * Filter out instances from a list that match a comparison.			 * 
		 * 
		 * @param results
		 * @param cmp
		 * @return
		 */
		@SuppressWarnings("unchecked")
		private List filterOutInstances(List results, Object cmp) {
			ArrayList a = new ArrayList();
			for (Object o : results) {
				if (o != null && o.equals(cmp)) {
					a.add(o);
				}
			}
			return a;
		}

		/**
		 * Filter out elements in a list that are not assignable
		 * to a given class (i.e. instanceof)
		 * @param children
		 * @param class1
		 * @return
		 */
		@SuppressWarnings("unchecked")
		private List filterOut(List children,
				Class class1) {
			ArrayList a = new ArrayList();
			for (Object o : children) {
				if (o.getClass().isAssignableFrom(class1)) {
					a.add(o);
				}
			}
			return a;
		}
		
	}

	
	private class DeriveElementsCommand extends AbstractTransactionalCommand {
		private EObject rootObject;
		private View parentView;

		public DeriveElementsCommand(
				EObject rootObject,
				TransactionalEditingDomain editingDomain, 
				View parentView) {			
			super(editingDomain, "Refresh element view", getWorkspaceFiles(parentView)); //$NON-NLS-1$
			this.rootObject = rootObject;
			this.parentView = parentView;
		}
		
		//private class TakeToEMFWrapper {
		//}
		
		private KBGenerated kb = null;
		public KBGenerated getKB() {
			return this.kb;
		}

		protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
				IAdaptable info) throws ExecutionException {
			
			// do something here
			MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Warning", "Not yet implemented. But here are the objects we have: object=" + this.rootObject + " view=" + this.parentView);

			// do the rules
			InternetApplication ia = (InternetApplication) rootObject;
			kb = runRules(ia);

			// get out results
			System.out.println("results:");
			ResultSet<GeneratedAppChildren> rs = kb.getApplicationChildren(ia);
			while (rs.hasNext()) {
				System.out.println("+ child: " + rs.next());
			}

			/*
			// get out results
			ResultSet<NamedElement_Name> rs2 = kb.getElementNames();
			while (rs2.hasNext()) {
				System.out.println("+ named element: " + rs2.next());
			}		
			*/

			return CommandResult.newOKCommandResult();
		}

	}
	
	public KBGenerated runRules(InternetApplication rootObject) {
		BasicConfigurator.resetConfiguration();		// config log4j
		BasicConfigurator.configure();		// config log4j

		KBGenerated kb = new KBGenerated();
		
		// set up fact stores
		final InternetApplication ia = (InternetApplication) rootObject;
		FactStores.factsApplicationElementContainer_Children =
			new Impl_ExternalFactStore4element_children(ia);
		FactStores.factsInputForm_Name =
			new Impl_ExternalFactStore4NamedElement_Name(ia);
		FactStores.factsPage_Name = 
			new Impl_ExternalFactStore4page_name(ia);
		FactStores.factsInternetApplication_Children = 
			new Impl_ExternalFactStore4InternetApplication_Children(ia);
		FactStores.factsInternetApplication_Name = 
			new Impl_ExternalFactStore4app_name(ia);
		
		// the rule compilation and evaluation is done lazily
		return kb;
	}

	public static List query(final EObject root, String query) throws JaxenException {
		EMFXPath xpath = new EMFXPath(query);
		xpath.addNamespace("iaml", ModelPackage.eNS_URI);
		xpath.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		return xpath.selectNodes(root);
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// empty
	}

	@Override
	public void run(IAction action) {
		if (selectedElement != null) {
			ICommand command = new DeriveElementsCommand(
					selectedElement.resolveSemanticElement(), 
					selectedElement.getEditingDomain(), 
					selectedElement.getDiagramView());
			
			try {
				OperationHistoryFactory.getOperationHistory().execute(command,
						new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				IamlDiagramEditorPlugin.getInstance().logError(
						"Unable to derive elements", e); //$NON-NLS-1$
			}
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		selectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof InternetApplicationEditPart) {
				selectedElement = (InternetApplicationEditPart) structuredSelection.getFirstElement();
			}
		}
		
	}
	
	/**
	 * for test cases
	 */
	public KBGenerated executeKnowledgeBase(InternetApplication root) {
		KBGenerated kb = this.runRules(root);  
		
		// return the changed information
		return kb;
		
	}
		
}