/**
 * More information on this code: http://www.jevon.org/wiki/GMF_Code_Samples
 */
package org.openiaml.model.diagram.custom.commands;

import iaml.generated2.ApplicationElementContainer_Children;
import iaml.generated2.ExternalFactStore4ApplicationElementContainer_Children;
import iaml.generated2.ExternalFactStore4InternetApplication_Children;
import iaml.generated2.ExternalFactStore4NamedElement_Name;
import iaml.generated2.ExternalFactStore4app_name;
import iaml.generated2.ExternalFactStore4page_name;
import iaml.generated2.FactStores;
import iaml.generated2.InternetApplication_Children;
import iaml.generated2.InternetApplication_Name;
import iaml.generated2.KBGenerated;
import iaml.generated2.NamedElement_Name;
import iaml.generated2.app_name;
import iaml.generated2.page_name;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.script.SimpleBindings;

import nz.org.take.KnowledgeBase;
import nz.org.take.TakeException;
import nz.org.take.compiler.reference.DefaultCompiler;
import nz.org.take.compiler.util.DefaultLocation;
import nz.org.take.compiler.util.DefaultNameGenerator;
import nz.org.take.compiler.util.jalopy.JalopyCodeFormatter;
import nz.org.take.deployment.KnowledgeBaseManager;
import nz.org.take.nscript.ScriptKnowledgeSource;
import nz.org.take.rt.ResourceIterator;
import nz.org.take.rt.ResultSet;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
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
import org.openiaml.model.model.visual.VisualPackage;

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

		private class Impl_ExternalFactStore4ApplicationElementContainer_Children
			extends FactStoreToEMF<Page,InputForm,ApplicationElementContainer_Children>
			implements ExternalFactStore4ApplicationElementContainer_Children {

			private InternetApplication ia;
			public Impl_ExternalFactStore4ApplicationElementContainer_Children(
					InternetApplication ia) {
				this.ia = ia;
			}
				
			@Override
			public ResourceIterator<ApplicationElementContainer_Children> fetch(
					Page slot1, InputForm slot2) {
				List<ApplicationElementContainer_Children> listResults = new ArrayList<ApplicationElementContainer_Children>();

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
									listResults.add(new ApplicationElementContainer_Children(p, (InputForm) eo));
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
								listResults.add(new ApplicationElementContainer_Children(slot1, (InputForm) eo));
							}
						}
					}
				}
				
				// now return iterator
				return new ResourceIteratorWrapper<ApplicationElementContainer_Children>(listResults.iterator());
			}

		}

			
			private class Impl_ExternalFactStore4InternetApplication_Children
				extends FactStoreToEMF<InternetApplication,Page,iaml.generated2.InternetApplication_Children>
				implements ExternalFactStore4InternetApplication_Children {

					private InternetApplication ia;
					public Impl_ExternalFactStore4InternetApplication_Children(
							InternetApplication ia) {
						this.ia = ia;
					}
						
					@Override
					public ResourceIterator<InternetApplication_Children> fetch(
							InternetApplication slot1, Page slot2) {
						List<InternetApplication_Children> listResults = new ArrayList<InternetApplication_Children>();

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
											listResults.add(new InternetApplication_Children(p, (Page) eo));
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
										listResults.add(new InternetApplication_Children(slot1, (Page) eo));
									}
								}
							}
						}
						
						// now return iterator
						return new ResourceIteratorWrapper<InternetApplication_Children>(listResults.iterator());
					}

			}
						
			
		private class Impl_ExternalFactStore4NamedElement_Name
			extends FactStoreToEMF<InputForm,String,NamedElement_Name>
			implements ExternalFactStore4NamedElement_Name {

				private InternetApplication ia;
				public Impl_ExternalFactStore4NamedElement_Name(
						InternetApplication ia) {
					this.ia = ia;
				}
					
				@Override
				public ResourceIterator<NamedElement_Name> fetch(
						InputForm slot1, String slot2) {
					List<NamedElement_Name> listResults = new ArrayList<NamedElement_Name>();

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
								listResults.add(new NamedElement_Name(p, p.getName()));
							}
						}
					} else {
						// get all InputForms in a certain Page
						if (slot2 == null || slot2.equals(slot1.getName())) {
							listResults.add(new NamedElement_Name(slot1, slot1.getName()));
						}

					}
					
					// now return iterator
					return new ResourceIteratorWrapper<NamedElement_Name>(listResults.iterator());
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
			extends FactStoreToEMF<InternetApplication,String,iaml.generated2.app_name>
			implements ExternalFactStore4app_name {


				private InternetApplication ia;
				public Impl_ExternalFactStore4app_name(
						InternetApplication ia) {
					this.ia = ia;
				}
					
				@Override
				public ResourceIterator<app_name> fetch(
						InternetApplication slot1, String slot2) {
					List<app_name> listResults = new ArrayList<app_name>();

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
								listResults.add(new app_name(p, p.getName()));
							}
						}
					} else {
						// get all InputForms in a certain Page
						if (slot2 == null || slot2.equals(slot1.getName())) {
							listResults.add(new app_name(slot1, slot1.getName()));
						}

					}
					
					// now return iterator
					return new ResourceIteratorWrapper<app_name>(listResults.iterator());
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

		protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
				IAdaptable info) throws ExecutionException {
			
			// do something here
			MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Warning", "Not yet implemented. But here are the objects we have: object=" + this.rootObject + " view=" + this.parentView);

			BasicConfigurator.resetConfiguration();		// config log4j
			BasicConfigurator.configure();		// config log4j

			KBGenerated kb;
			kb = new KBGenerated();
			
			// set up fact stores
			final InternetApplication ia = (InternetApplication) rootObject;
			FactStores.factsApplicationElementContainer_Children =
				new Impl_ExternalFactStore4ApplicationElementContainer_Children(ia);
			FactStores.factsInputForm_Name =
				new Impl_ExternalFactStore4NamedElement_Name(ia);
			FactStores.factsPage_Name = 
				new Impl_ExternalFactStore4page_name(ia);
			FactStores.factsInternetApplication_Children = 
				new Impl_ExternalFactStore4InternetApplication_Children(ia);
			FactStores.factsInternetApplication_Name = 
				new Impl_ExternalFactStore4app_name(ia);

			/*
			FactStores.factsApplicationElementContainer_Children = new ExternalFactStore4ApplicationElementContainer_Children() {

				@Override
				public ResourceIterator<ApplicationElementContainer_Children> fetch(
						Page slot1, InputForm slot2) {
					List results;
						if (slot1 == null) {
							// find all input forms
							try {
								results = query(ia, "//iaml:page[iaml:form]");
							} catch (JaxenException e) {
								results = new ArrayList(); // empty
								e.printStackTrace();
							}	
						} else {
							// find only local input forms
							results = filterOut(slot1.getChildren(), InputForm.class);
						}
						// now filter out ones that don't match
						if (slot2 != null)
							results = filterOutInstances(results, slot2);
						
					return new ResourceIteratorWrapper(results);
				}

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
				
			};
			/*
			FactStores.facts1 = new ExternalFactStore4page_name(ia);
			FactStores.facts2 = new EMFInternetApplication_ChildrenFactStore(ia);
			FactStores.facts3 = new EMFApplicationElementContainer_ChildrenFactStore(ia);
			FactStores.facts4 = new EMFInternetApplication_NameFactStore(ia);
			FactStores.facts_form_element_name = new ExternalFactStore4input_form_name();
			*/
			
			// get out results
			System.out.println("results:");
			ResultSet<InternetApplication_Children> rs = kb.getAppChildren(ia);
			while (rs.hasNext()) {
				System.out.println("+ child: " + rs.next());
			}

			// get out results
			ResultSet<NamedElement_Name> rs2 = kb.getElementNames();
			while (rs2.hasNext()) {
				System.out.println("+ named element: " + rs2.next());
			}
			
			// xpath test
			/*
			List r = new ArrayList();
			try {
				EMFXPath xpath;
				xpath = new EMFXPath("//iaml:children[iaml.visual:Page]");	// get root InternetApplication
				xpath.addNamespace("iaml", ModelPackage.eNS_URI);
				xpath.addNamespace("iaml.visual", VisualPackage.eNS_URI);
				xpath.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
				r = xpath.selectNodes(ia);
				for (Object ro : r) {
					System.out.println("xpath: " + ro);
				}
				
				// dump
				System.out.println("[dump]");
				xpath = new EMFXPath("//*");	// "." = get root InternetApplication
				xpath.addNamespace("iaml", ModelPackage.eNS_URI);
				xpath.addNamespace("iaml.visual", VisualPackage.eNS_URI);
				xpath.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
				r = xpath.selectNodes(ia);
				xpath.dump(ia, System.out);
			} catch (JaxenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/

			/*
			// from http://wiki.eclipse.org/FAQ_How_do_I_use_the_context_class_loader_in_Eclipse%3F
			// change the class loader
			Thread current = Thread.currentThread();
			ClassLoader oldLoader = current.getContextClassLoader();
			
			/*
			try {
				current.setContextClassLoader( getClass().getClassLoader() );

				// prepare knowledgebase
				KnowledgeBaseManager<KB> kbm = new KnowledgeBaseManager<KB>();
				// this does nothing!
				kbm.setBaseClassLoader( getClass().getClassLoader() );
				Map<String,Object> factStores = new HashMap<String,Object>();
				factStores.put("facts1", new InternetApplicationFactStore((InternetApplication) rootObject));
				File source = new File("workspace-iaml-branch/org.openiaml.model.diagram.custom/take-rules/test.take");
				KB kb;

				// kbm.setBaseClassLoader( oldLoader );
				kb = kbm.getKnowledgeBase(
						KB.class, 
						new ScriptKnowledgeSource(source),
						new HashMap<String,Object>(),
						factStores
				);

				// do query
				ResultSet<InternetApplication_Children> results = kb.getAppChildren();
				while (results.hasNext()) {
					InternetApplication_Children e = results.next();
					System.out.println("child = " + e);
				}
			
			} catch (FileNotFoundException e1) {
				throw new ExecutionException("file not found: " + e1.getLocalizedMessage(), e1);
			} catch (TakeException e1) {
				throw new ExecutionException("take exception: " + e1.getLocalizedMessage(), e1);
			} finally {
				current.setContextClassLoader(oldLoader);
			}
			*/
			
			if (true)
				return CommandResult.newOKCommandResult();;
			
			// prepare
			InternetApplicationDerivePolicy KB = null; // this is the generated interface
			BasicConfigurator.resetConfiguration();		// config log4j
			BasicConfigurator.configure();		// config log4j

			// compile and bind constants referenced in rules
			// KnowledgeBaseManager<InternetApplicationDerivePolicy> kbm = new KnowledgeBaseManager<InternetApplicationDerivePolicy>(parentView.getClass().getClassLoader());
			KnowledgeBaseManager<InternetApplicationDerivePolicy> kbm = new KnowledgeBaseManager<InternetApplicationDerivePolicy>();
			SimpleBindings bindings = new SimpleBindings();
			bindings.put("app", rootObject);
			File source = new File("workspace-iaml-branch/org.openiaml.model.diagram.custom/take-rules/test.take");
			KnowledgeBase foo = null;
			String base = "C:\\Documents and Settings\\jmwright.MASSEY\\workspace-iaml-branch\\";
			/*
			kbm.setClassPath( kbm.getClassPath() + ":" 
					+ base + "org.openiaml.model.diagram.custom\\lib\\take-rt-1.5.jar:" 
					+ base + "org.openiaml.update\\plugins\\*.jar" );
			// System.out.println("current classPath>> " + kbm.getClassPath());
			 * 
			 */
			try {
				//System.out.println("old cl >> " + kbm.getBaseClassLoader());
				// kbm.setFormatted(false);	// turn off code formatter
				kbm.setWorkingDirRoot("workspace-iaml-branch/org.openiaml.model.diagram.custom/take-generated/");
				// kbm.setBaseClassLoader(this.getClass().getClassLoader());
				//System.out.println("new cl >> " + kbm.getBaseClassLoader());
				/*
				KB = kbm.getKnowledgeBase(
						InternetApplicationDerivePolicy.class, 
						new ScriptKnowledgeSource(source),
						bindings);
						
						*/
				
                nz.org.take.compiler.Compiler compiler = new DefaultCompiler();
                compiler.add(new JalopyCodeFormatter());
                compiler.setNameGenerator(new DefaultNameGenerator());
                // compiler.setLocation(new DefaultLocation());
                // generate kb
                InputStream script = new FileInputStream(source);
                ScriptKnowledgeSource ksource = new ScriptKnowledgeSource(script);
                
                compiler.setLocation(new DefaultLocation("workspace-iaml-branch/org.openiaml.model.diagram.custom/take-generated/"));
                compiler.setPackageName("iaml.generated");
                compiler.setClassName("IAMLRules");
                
                long before = System.currentTimeMillis();
                compiler.compile(ksource.getKnowledgeBase());
                foo = ksource.getKnowledgeBase();
                System.out.println("Compilation took "+(System.currentTimeMillis()-before)+"ms");
                
			} catch (FileNotFoundException e1) {
				throw new ExecutionException("file not found: " + e1.getLocalizedMessage(), e1);
			} catch (TakeException e1) {
				throw new ExecutionException("take exception: " + e1.getLocalizedMessage(), e1);
			}
			
			// now use these derived properties
			/*
			IAMLRules r = new IAMLRules();
			ResultSet<iaml.generated.ApplicationElement> rs = r.getChildren();
			
			while (rs.hasNext()) {
				iaml.generated.ApplicationElement e = rs.next();
				System.out.println("IApp=" + e.slot1 + " AppElem=" + e.slot2);
			}
			System.out.println("done");
			
			// now use the generated classes to query the kb
			// get derived property
			/*
			InternetApplication app = (InternetApplication) rootObject;
			ResultSet<ApplicationElement> elements = KB.getChildren();
			// ResultSet<?> result = KB.getComplete(app);
			// Customer john = new Customer("John");
			// john.setCategory(new CustomerCategory("gold"));
			// ResultSet<CustomerDiscount> result =  KB.getDiscount(john);
			// System.out.println("The discount for John is: " + result.next().discount);
			// print out the pages in result
			String out = "";
			for (ApplicationElement e : app.getChildren()) {
				out += e + "\n";
			}
			MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Information", "Generated elements: " + out);
			    
			// print rules used
			/*
			System.out.println("The following rules have been used to calculate the discount: ");
			for (DerivationLogEntry e:result.getDerivationLog()) {
			        System.out.print(e.getCategory());
			    	System.out.print(" : ");
			    	System.out.println(e.getName());
			}
			*/  
			
			return CommandResult.newOKCommandResult();
		}

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
		
}