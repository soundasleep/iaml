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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.compiler.PackageBuilder;
import org.drools.event.DefaultWorkingMemoryEventListener;
import org.drools.event.ObjectInsertedEvent;
import org.drools.event.ObjectRetractedEvent;
import org.drools.event.ObjectUpdatedEvent;
import org.drools.event.WorkingMemoryEventListener;
import org.drools.rule.Package;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.inference.InfiniteSubProgressMonitor;
import org.openiaml.model.model.GeneratedElement;

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
	public static int INSERTION_ITERATION_LIMIT = 20;
	
	protected ICreateElements handler;
	
	/**
	 * Stores a summary of queue elements added.
	 * (This should really be added through a mock object instead. TODO)
	 */
	private Map<Integer, Integer> queueElementsAdded;

	/**
	 * 
	 * @param handler The handler to create elements
	 * @param trackInsertions Should the DroolsInsertionQueue track the activations/elements inserted?
	 * @see DroolsInsertionQueue#DroolsInsertionQueue(boolean)
	 * @see DroolsInsertionQueue#getActivationFor(EObject)
	 */
	public DroolsInferenceEngine(ICreateElements handler, boolean trackInsertions) {
		this.handler = handler;
		this.trackInsertions = trackInsertions;
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

	private DroolsInsertionQueue queue;

	/**
	 * Should we ask the DroolsInsertionQueue to track insertions?
	 */
	private boolean trackInsertions;
	
	/**
	 * The insertion queue. We make this available so that Partial
	 * Inference through {@link #InferContainedElementsAction} can identify
	 * which elements needs to be properly deleted.
	 */
	public DroolsInsertionQueue getDroolsInsertionQueue() {
		return queue;
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
		
		queueElementsAdded = new HashMap<Integer,Integer>();
		
    	// load up the rulebase
        RuleBase ruleBase;
		try {
			ruleBase = readRule();
		} catch (Exception e) {
			throw new InferenceException("Could not load rulebase: " + e.getMessage(), e);
		}
		
		/*
		 * We use a StatefulSession, because we may add additional facts
		 * after the initial insertion.
		 * 
		 * But creating a StatefulSession adds a reference to the RuleBase,
		 * which can cause memory leaks. This is so that if the
		 * RuleBase has changed rules, they can be updated in the working
		 * memory. As a result, we have to call dispose() on the 
		 * StatefulSession once we are finished with it.  
		 */
        final StatefulSession workingMemory = ruleBase.newStatefulSession();
        
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
				
				if (obj.getObject() instanceof EObject) {
					// get all objects within 
					TreeIterator<EObject> it = ((EObject) obj.getObject()).eAllContents();
					while (it.hasNext()) {
						workingMemory.insert( it.next() );
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

        // need to set up these variables before we insert the model,
        // otherwise the agenda cannot be built (as the rule heads use
        // {@link #getHelperFunctions()}).
        queue = new DroolsInsertionQueue(trackInsertions);
	    workingMemory.setGlobal("handler", handler);
		workingMemory.setGlobal("queue", queue);
		workingMemory.setGlobal("functions", getHelperFunctions());		

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
        
		// logging
		try {
			// turn off
			// InferenceQueueLog log = new InferenceQueueLogSilent();
			InferenceQueueLog log = new InferenceQueueLog();
	
		    // subProgressMonitor = new InfiniteSubProgressMonitor(monitor, 50);
			// we can actually use a real monitor, now that we have a limit
			subProgressMonitor = new SubProgressMonitor(monitor, INSERTION_ITERATION_LIMIT);
	        for (int k = 0; k < INSERTION_ITERATION_LIMIT; k++) {
	        	// check for monitor cancel
	        	if (monitor.isCanceled()) {
	        		return;
	        	}
	        	
	        	// actually do the work
		        workingMemory.fireAllRules();
		        
		        // once the rules have been completed,
		        // insert in the new elements
		        // but first reset the queue
		        DroolsInsertionQueue oldQueue = queue;
		        queue = new DroolsInsertionQueue(trackInsertions);
		        if (trackInsertions) {
		        	queue.addPreviousInsertions(oldQueue);
		        }
				workingMemory.setGlobal("queue", queue );
				
				// apply the new objects
				oldQueue.apply(workingMemory, k, log);

				// increment the log
				log.increment("step " + k, oldQueue.size());
				queueElementsAdded.put(k, oldQueue.size());
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
		
		// force the dispose of the working memory;
		// all of our rules have been fired, all of the working
		// memory has been saved to an EObject, so we should be
		// able to remove it from memory
		workingMemory.dispose();
		
        subProgressMonitor.done();	// completed
        
        monitor.done();

	}
	
	/**
	 * Get the DroolsHelperFunctions to use within the rules.
	 * 
	 * @return
	 */
	public DroolsHelperFunctions getHelperFunctions() {
		return new DroolsHelperFunctions();
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
	 * Load the given resource filename as a stream. By default,
	 * uses {@link DroolsInferenceEngine}'s classLoader to load it.
	 * 
	 * @param filename
	 * @return the loaded stream, or null if it could not be loaded
	 */
	public InputStream loadResourceAsStream(String filename) {
		return DroolsInferenceEngine.class.getResourceAsStream( filename );
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
	
			// load the stream
			InputStream stream = loadResourceAsStream(ruleFile);
			if (stream == null) {
				throw new InferenceException("Could not load the resource '" + ruleFile + "' as a stream."); 
			}
			
			//read in the source
			Reader source = new InputStreamReader( stream );
			
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
	 * Get a list of elements added per iteration step.
	 * 
	 * @return A map of elements added in each iteration step.
	 */
	public Map<Integer, Integer> getQueueElementsAdded() {
		return queueElementsAdded;
	}
	
}
