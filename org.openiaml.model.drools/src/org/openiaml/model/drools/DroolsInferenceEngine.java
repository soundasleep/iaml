/**
 * 
 */
package org.openiaml.model.drools;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.PackageBuilder;
import org.drools.event.DefaultWorkingMemoryEventListener;
import org.drools.event.ObjectInsertedEvent;
import org.drools.event.ObjectRetractedEvent;
import org.drools.event.ObjectUpdatedEvent;
import org.drools.event.WorkingMemoryEventListener;
import org.drools.rule.Package;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DerivedView;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.scopes.Session;

/**
 * Uses Drools to infer new elements. By defining
 * an abstract class, we can specify many different engine
 * implementations that all use different sets of rules,
 * but have the same logic internally.
 * 
 * @author jmwright
 *
 */
public abstract class DroolsInferenceEngine {
	
	ICreateElements handler;

	public DroolsInferenceEngine(ICreateElements handler) {
		this.handler = handler;
	}

	/**
	 * Do the inference using Drools. Does not log the source rule.
	 * 
	 * @see #create(EObject, boolean)
	 * @param model
	 * @throws InferenceException
	 */
	public void create(EObject model, IProgressMonitor monitor) throws InferenceException {
		create(model, false, monitor);
	}
	
	private IProgressMonitor subProgressMonitor;
	
	public IProgressMonitor getSubprogressMonitor() {
		return subProgressMonitor;
	}
	
	/**
	 * Do the inference using Drools.
	 * 
	 * @param model
	 * @param logRuleSource if true, the source rule of inserted elements will be added
	 * @param monitor a progress monitor
	 * @throws Exception 
	 */
	public void create(EObject model, boolean logRuleSource, IProgressMonitor monitor) throws InferenceException {

		monitor.beginTask("Inferring model using Drools", 100);
		monitor.subTask("Loading rulebase");
		
    	// load up the rulebase
        RuleBase ruleBase;
		try {
			ruleBase = readRule();
		} catch (Exception e) {
			throw new InferenceException("Could not load rulebase: " + e.getMessage(), e);
		}
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();
        
        monitor.worked(5);
        monitor.subTask("Inserting initial model");
        
        // automatically insert new objects based on a given object
        workingMemory.addEventListener( new WorkingMemoryEventListener() {

        	/**
        	 * When we insert a new element, we automatically insert
        	 * all of its children elements.
        	 * 
        	 * This is also the method that does all of the work
        	 * when we insert in the root InternetApplication - it constructs
        	 * the entire working memory model.
        	 */
			@Override
			public void objectInserted(ObjectInsertedEvent obj) {
				// increment a progress monitor
				if (getSubprogressMonitor() != null) {
					getSubprogressMonitor().worked(1);
					/*
					if (obj.getObject() instanceof EObject) {
						getSubprogressMonitor().subTask("Inserting " + ((EObject) obj.getObject()).eClass().getName());						
					}
					*/
				}
				
				// ContainsEventTriggers
				if (obj.getObject() instanceof ContainsEventTriggers) {
					ContainsEventTriggers a = (ContainsEventTriggers) obj.getObject();
					for (EventTrigger child : a.getEventTriggers()) {
						workingMemory.insert( child );
					}
				}

				// ContainsOperations
				if (obj.getObject() instanceof ContainsOperations) {
					ContainsOperations a = (ContainsOperations) obj.getObject();
					for (Operation child : a.getOperations()) {
						workingMemory.insert( child );
					}
				}

				// ContainsWires
				if (obj.getObject() instanceof ContainsWires) {
					ContainsWires a = (ContainsWires) obj.getObject();
					for (WireEdge child : a.getWires()) {
						workingMemory.insert( child );
					}
				}
				
				// ContainsConditions
				if (obj.getObject() instanceof ContainsConditions) {
					ContainsConditions a = (ContainsConditions) obj.getObject();
					for (Condition child : a.getConditions()) {
						workingMemory.insert( child );
					}
				}

				// ApplicationElement
				if (obj.getObject() instanceof ApplicationElement) {
					ApplicationElement a = (ApplicationElement) obj.getObject();
					for (ApplicationElementProperty child : a.getProperties()) {
						workingMemory.insert( child );
					}
					for (StaticValue child : a.getValues()) {
						workingMemory.insert( child );
					}
				}

				// ApplicationElementContainer
				if (obj.getObject() instanceof ApplicationElementContainer) {
					ApplicationElementContainer a = (ApplicationElementContainer) obj.getObject();
					for (ApplicationElement child : a.getChildren()) {
						workingMemory.insert( child );
					}
				}
				
				// AbstractDomainStore
				if (obj.getObject() instanceof DomainStore) {
					DomainStore a = (DomainStore) obj.getObject();
					for (DomainObject child : a.getChildren()) {
						workingMemory.insert( child );
					}
					for (DomainAttribute child : a.getAttributes()) {
						workingMemory.insert( child );
					}
					for (ApplicationElementProperty child : a.getProperties()) {
						workingMemory.insert( child );
					}
				}				

				// AbstractDomainObject
				if (obj.getObject() instanceof DomainObject) {
					DomainObject a = (DomainObject) obj.getObject();
					for (DomainAttribute child : a.getAttributes()) {
						workingMemory.insert( child );
					}
				}

				// InternetApplication
				if (obj.getObject() instanceof InternetApplication) {
					InternetApplication a = (InternetApplication) obj.getObject();
					for (ApplicationElementProperty child : a.getProperties()) {
						workingMemory.insert( child );
					}
					for (ApplicationElement child : a.getChildren()) {
						workingMemory.insert( child );
					}
					for (DomainStore child : a.getDomainStores()) {
						workingMemory.insert( child );
					}
					for (Session child : a.getSessions()) {
						workingMemory.insert( child );
					}
				}
				
				// Scope
				if (obj.getObject() instanceof Scope) {
					Scope a = (Scope) obj.getObject();
					for (DomainObjectInstance child : a.getDomainInstances()) {
						workingMemory.insert( child );
					}
					for (DomainObject child : a.getDomainObjects()) {
						workingMemory.insert( child );
					}
					for (DerivedView child : a.getDomainViews()) {
						workingMemory.insert( child );
					}
				}
				
				// Session
				if (obj.getObject() instanceof Session) {
					Session a = (Session) obj.getObject();
					for (ApplicationElement child : a.getComponents()) {
						workingMemory.insert( child );
					}
				}

				// DomainObjectInstance
				if (obj.getObject() instanceof DomainObjectInstance) {
					DomainObjectInstance a = (DomainObjectInstance) obj.getObject();
					for (DomainAttributeInstance child : a.getAttributes()) {
						workingMemory.insert( child );
					}
				}
			}

			@Override
			public void objectRetracted(ObjectRetractedEvent x) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void objectUpdated(ObjectUpdatedEvent x) {
				// TODO Auto-generated method stub
				
			}
        	
        });   
        
        // set up the sub progress monitor
        subProgressMonitor = new InfiniteSubProgressMonitor(monitor, 45);
        
        //go !
        workingMemory.insert( model );
        subProgressMonitor.done();
        subProgressMonitor = null;
        
        workingMemory.setGlobal("handler", handler);
        
        monitor.subTask("Inferring new model elements");
        
        /*
         * This simply adds the Rule source for inserted elements
         * (where possible).
         */
        if (logRuleSource) {
	        workingMemory.addEventListener(new DefaultWorkingMemoryEventListener() {
	
				@Override
				public void objectInserted(ObjectInsertedEvent event) {
					if (event.getObject() instanceof GeneratedElement) {
						GeneratedElement e = (GeneratedElement) event.getObject();
						try {
							handler.setGeneratedRule(e, event.getPropagationContext().getRuleOrigin().getName());
						} catch (InferenceException e1) {
							throw new RuntimeException(e1.getMessage(), e1);
						}
					}
				}
	        });
        }
        
        subProgressMonitor = new InfiniteSubProgressMonitor(monitor, 50);
        workingMemory.fireAllRules();
        subProgressMonitor.done();	// completed
        
        monitor.done();

	}

	public List<String> ruleFiles = Arrays.asList(
			"/rules/base.drl",
			"/rules/sync-wires.drl",
			"/rules/events.drl",
			"/rules/sessions.drl",
			"/rules/operations.drl",
			"/rules/dynamic-sources.drl",
			"/rules/conditions.drl"
			);
	
	/**
	 * Get the list of rule files used. This method
	 * needs to be implemented by subclasses.
	 * 
	 * For example, ["/rules/base.drl", "/rules/conditions.drl"]
	 * 
	 * @see #addRuleFile(String)
	 * @return
	 */
	public abstract List<String> getRuleFiles();
	
	/**
	 * Add a rule file, relative to this loaded class.
	 * 
	 * @see #getRuleFiles()
	 * @deprecated not yet tested
	 * @param filename
	 */
	public void addRuleFile(String filename) {
		ruleFiles.add(filename);
	}
	
	/**
	 * Get the RuleBase from the rules provided.
	 * Copied from sample DroolsTest.java.
	 * 
	 * @see #getRuleFiles()
	 * @return
	 * @throws Exception 
	 */
	private RuleBase readRule() throws Exception {
		
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();

		for (String ruleFile : getRuleFiles()) {
	
			//read in the source
			Reader source = new InputStreamReader( DroolsInferenceEngine.class.getResourceAsStream( ruleFile ) );
			
			//optionally read in the DSL (if you are using it).
			//Reader dsl = new InputStreamReader( DroolsTest.class.getResourceAsStream( "/mylang.dsl" ) );
	
			//Use package builder to build up a rule package.
			//An alternative lower level class called "DrlParser" can also be used...

			PackageBuilder builder = new PackageBuilder();
	
			//this wil parse and compile in one step
			//NOTE: There are 2 methods here, the one argument one is for normal DRL.
			builder.addPackageFromDrl( source );
	
			//Use the following instead of above if you are using a DSL:
			//builder.addPackageFromDrl( source, dsl );
			
			//get the compiled package (which is serializable)
			Package pkg = builder.getPackage();
			
			//add the package to a rulebase (deploy the rule package).
			ruleBase.addPackage( pkg );
	
		}
		
		return ruleBase;
		
	}

	/**
	 * Copied <i>directly</i> from org.eclipse.team.internal.core.InfiniteSubProgressMonitor.
	 * 
	 * Provides an infinite progress monitor by subdividing by half repeatedly.
	 * 
	 * The ticks parameter represents the number of ticks shown in the progress dialog
	 * (or propogated up to a parent IProgressMonitor). The totalWork parameter provided
	 * in actually a hint used to determine how work is translated into ticks.
	 * The number of totalWork that can actually be worked is n*totalWork/2 where
	 * 2^n = totalWork. What this means is that if you provide a totalWork of 32 (2^5) than
	 * the maximum number of ticks is 5*32/2 = 80.
	 * 
	 */
	public class InfiniteSubProgressMonitor extends SubProgressMonitor {

		int totalWork;
		int halfWay;
		int currentIncrement;
		int nextProgress;
		int worked;
			
		/**
		 * Constructor for InfiniteSubProgressMonitor.
		 * @param monitor
		 * @param ticks
		 */
		public InfiniteSubProgressMonitor(IProgressMonitor monitor, int ticks) {
			this(monitor, ticks, 0);
		}

		/**
		 * Constructor for InfiniteSubProgressMonitor.
		 * @param monitor
		 * @param ticks
		 * @param style
		 */
		public InfiniteSubProgressMonitor(IProgressMonitor monitor, int ticks, int style) {
			super(monitor, ticks, style);
		}
		
		public void beginTask(String name, int totalWork) {
			super.beginTask(name, totalWork);
			this.totalWork = totalWork;
			this.halfWay = totalWork / 2;
			this.currentIncrement = 1;
			this.nextProgress = currentIncrement;
			this.worked = 0;
		}
		
		public void worked(int work) {
			if (worked >= totalWork) return;
			if (--nextProgress <= 0) {
				super.worked(1);
				worked++;
				if (worked >= halfWay) {
					// we have passed the current halfway point, so double the
					// increment and reset the halfway point.
					currentIncrement *= 2;
					halfWay += (totalWork - halfWay) / 2;				
				}
				// reset the progress counter to another full increment
				nextProgress = currentIncrement;
			}			
		}

		/**
		 * Don't allow clearing of the subtask. This will stop the flickering
		 * of the subtask in the progress dialogs.
		 * 
		 * @see IProgressMonitor#subTask(String)
		 */
		public void subTask(String name) {
			if(name != null && ! name.equals("")) { //$NON-NLS-1$
				super.subTask(name);
			}
		}

	}
	
}
