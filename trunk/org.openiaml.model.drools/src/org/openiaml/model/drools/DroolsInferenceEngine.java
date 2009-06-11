/**
 * 
 */
package org.openiaml.model.drools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.PackageBuilder;
import org.drools.event.ActivationCancelledEvent;
import org.drools.event.ActivationCreatedEvent;
import org.drools.event.AfterActivationFiredEvent;
import org.drools.event.AfterFunctionRemovedEvent;
import org.drools.event.AfterPackageAddedEvent;
import org.drools.event.AfterPackageRemovedEvent;
import org.drools.event.AfterRuleAddedEvent;
import org.drools.event.AfterRuleBaseLockedEvent;
import org.drools.event.AfterRuleBaseUnlockedEvent;
import org.drools.event.AfterRuleRemovedEvent;
import org.drools.event.AgendaEventListener;
import org.drools.event.AgendaGroupPoppedEvent;
import org.drools.event.AgendaGroupPushedEvent;
import org.drools.event.BeforeActivationFiredEvent;
import org.drools.event.BeforeFunctionRemovedEvent;
import org.drools.event.BeforePackageAddedEvent;
import org.drools.event.BeforePackageRemovedEvent;
import org.drools.event.BeforeRuleAddedEvent;
import org.drools.event.BeforeRuleBaseLockedEvent;
import org.drools.event.BeforeRuleBaseUnlockedEvent;
import org.drools.event.BeforeRuleRemovedEvent;
import org.drools.event.DefaultWorkingMemoryEventListener;
import org.drools.event.ObjectInsertedEvent;
import org.drools.event.ObjectRetractedEvent;
import org.drools.event.ObjectUpdatedEvent;
import org.drools.event.RuleBaseEventListener;
import org.drools.event.RuleFlowCompletedEvent;
import org.drools.event.RuleFlowEventListener;
import org.drools.event.RuleFlowGroupActivatedEvent;
import org.drools.event.RuleFlowGroupDeactivatedEvent;
import org.drools.event.RuleFlowStartedEvent;
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
	
	/**
	 * How many iterations of inserting new elements (and revaluating
	 * the rules) should we limit ourselves to?
	 * 
	 * Also: The value of 'k' from the upcoming paper.
	 */
	protected static int INSERTION_ITERATION_LIMIT = 20;
	
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

        PrintingArrayList queue = new PrintingArrayList();
	    workingMemory.setGlobal("handler", handler);
		workingMemory.setGlobal("queue", queue );
		
		// logging
		try {
			// InferenceQueueLog log = new InferenceQueueLog();
			// turn off
			InferenceQueueLog log = new InferenceQueueLogSilent();
	
		    // subProgressMonitor = new InfiniteSubProgressMonitor(monitor, 50);
			// we can actually use a real monitor, now that we have a limit
			subProgressMonitor = new SubProgressMonitor(monitor, INSERTION_ITERATION_LIMIT);
	        for (int k = 0; k < INSERTION_ITERATION_LIMIT; k++) {
	        	System.out.println("[step " + k + "]");
		        
		        workingMemory.fireAllRules();
		        
		        // once the rules have been completed,
		        // insert in the new elements
		        // but first reset the queue
		        PrintingArrayList oldQueue = queue;
		        queue = new PrintingArrayList();
				workingMemory.setGlobal("queue", queue );
				
				for (EObject o : oldQueue) {
					workingMemory.insert(o);

					// increment all the types in the log
					log.increment("step " + k + " type " + o.eClass().getName(), 1);
				}
		        
				// increment the log
				log.increment("step " + k, oldQueue.size());
	        }
	        
	        // are there any elements left in the queue?
	        // if so, this might be an infinite loop
	        if (!queue.isEmpty()) {
	        	throw new InferenceException("Expected an empty queue at the end of k iterations, but had: " + queue);
	        }
	        
	        // finish the log
	        log.save();
		} catch (IOException e) {
			throw new InferenceException(e);
		} catch (NumberFormatException e) {
			throw new InferenceException(e);
		}
	        
        subProgressMonitor.done();	// completed
        
        monitor.done();

	}
	
	/**
	 * Log inference queue results.
	 * Will usually output to the source directory of the executing
	 * plugin, e.g. org.openiaml.tests/inference.log 
	 * 
	 * @author jmwright
	 *
	 */
	public class InferenceQueueLog {

		File logFile = new File("inference.log");
		private Map<String, Integer> log = new HashMap<String, Integer>();

		/**
		 * Load the log.
		 * @throws IOException 
		 * @throws NumberFormatException 
		 */
		public InferenceQueueLog() throws NumberFormatException, IOException {
			if (logFile.exists()) {
				BufferedReader read = new BufferedReader(new FileReader(logFile));
				String line;
				while ((line = read.readLine()) != null) {
					String[] bits = line.split(": ", 2);
					log.put(bits[0], Integer.parseInt(bits[1]));
				}
				read.close();
			}
		}
		
		/**
		 * Increment a log value.
		 * @param key
		 */
		public void increment(String key, int value) {
			if (log.get(key) == null) {
				log.put(key, value);
			} else {
				log.put(key, log.get(key) + value);
			}
		}
		
		/**
		 * Save the log.
		 * @throws IOException 
		 */
		public void save() throws IOException {
			BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
			for (String key : log.keySet()) {
				writer.write(key + ": " + log.get(key) + "\n");
			}
			writer.close();
		}
		
	}
	
	/**
	 * Extends {@link InferenceQueueLog} to never actually do
	 * anything, i.e. be silent.
	 * 
	 * @author jmwright
	 *
	 */
	public class InferenceQueueLogSilent extends InferenceQueueLog {

		public InferenceQueueLogSilent() throws NumberFormatException, IOException {
			// do nothing
		}
		
		@Override
		public void increment(String key, int value) {
			// do nothing
		}
		
		@Override
		public void save() throws IOException {
			// do nothing
		}
		
	}
	
	public class PrintingArrayList extends ArrayList<EObject> {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean add(EObject e) {
			System.out.println("queueing object: " + e);
			return super.add(e);
		}
    	
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
