/**
 * More information on this code: http://www.jevon.org/wiki/GMF_Code_Samples
 */
package org.openiaml.model.diagram.custom.commands;

import iaml.generated2.ExternalFactStore4InternetApplication_Children;
import iaml.generated2.InternetApplication_Children;
import iaml.generated2.KB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
import org.openiaml.model.diagram.custom.takeproxy.ApplicationElementProxy;
import org.openiaml.model.diagram.custom.takeproxy.InternetApplicationProxy;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.diagram.edit.parts.InternetApplicationEditPart;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;

import test.nz.org.take.compiler.scenario8.FactStore;
import test.nz.org.take.compiler.scenario8.Tests;


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

	public class InternetApplicationFactStore implements ExternalFactStore4InternetApplication_Children {

		private InternetApplicationProxy rootObject = null;
		
		public InternetApplicationFactStore(InternetApplication rootObject) {
			this.rootObject = new InternetApplicationProxy(rootObject);
		}

		@Override
		public ResourceIterator<InternetApplication_Children> fetch(
				InternetApplicationProxy slot1,
				ApplicationElementProxy slot2) {
			ArrayList<InternetApplication_Children> it = new ArrayList<InternetApplication_Children>();
			for (ApplicationElementProxy c : rootObject.getChildren())
				it.add(new InternetApplication_Children(rootObject, c));
			return new ResourceIteratorWrapper<InternetApplication_Children>(it.iterator());
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

		protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
				IAdaptable info) throws ExecutionException {
			
			// do something here
			MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Warning", "Not yet implemented. But here are the objects we have: object=" + this.rootObject + " view=" + this.parentView);

			BasicConfigurator.resetConfiguration();		// config log4j
			BasicConfigurator.configure();		// config log4j

			// from http://wiki.eclipse.org/FAQ_How_do_I_use_the_context_class_loader_in_Eclipse%3F
			// change the class loader
			Thread current = Thread.currentThread();
			ClassLoader oldLoader = current.getContextClassLoader();
			
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