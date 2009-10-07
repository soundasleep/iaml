package org.openiaml.verification.drools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.FactHandle;
import org.drools.WorkingMemory;
import org.drools.event.ObjectInsertedEvent;
import org.drools.event.WorkingMemoryEventListener;
import org.drools.reteoo.ReteTuple;
import org.drools.rule.Rule;
import org.drools.spi.Activation;
import org.drools.spi.KnowledgeHelper;
import org.drools.spi.PropagationContext;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.verification.drools.VerificationEngine.InferenceQueueLog;

/**
 * <p>To support the iterative way of adding new elements to the
 * model, this class supports the queueing up of elements for
 * addition later.</p> 
 * 
 * <p>Essentially, instead of using
 * <code>insert(object);</code>, 
 * the Drools rule must instead use
 * <code>queue.add(object, drools);</code>, 
 * where <code>drools</code> is an automatically populated 
 * object referring to the Drools instance.</p>
 * 
 * <p>Once the queue has been completely populated, the
 * apply() method must be called to insert the queued elements.</p>
 * 
 * <p>It also deals with throwing {@link ObjectInsertionEvent}s when
 * adding the queued elements, to allow for other listeners in the system
 * to function as normal.</p>
 * 
 * @author jmwright
 */
public class DroolsInsertionQueue {

	/**
	 * This is a bit of a hack to a real {@link PropogationContext}, because
	 * the default one we get is not actually populated properly (elements
	 * remain null; I think they are being reset to help GC).
	 * 
	 * Essentially it's only real use is {@link #getRuleOrigin()}; most
	 * of the other methods throw an {@link UnsupportedOperationException}.
	 * 
	 * @author jmwright
	 *
	 */
	public class MyPropogationContext implements PropagationContext {

		private static final long serialVersionUID = 1L;
		
		private Activation activation;

		/**
		 * Initialise this with a given activation. This activation
		 * will contain the actual rule origin.
		 * 
		 * @param activation
		 */
		public MyPropogationContext(Activation activation) {
			this.activation = activation;
		}

		/**
		 * Not supported.
		 * 
		 * @throws UnsupportedOperationException
		 * @see org.drools.spi.PropagationContext#addRetractedTuple(org.drools.rule.Rule, org.drools.spi.Activation)
		 */
		@Override
		public void addRetractedTuple(Rule rule, Activation activation) {
			throw new UnsupportedOperationException("addRetractedTuple() is not supported");
		}

		/**
		 * Not supported.
		 * 
		 * @throws UnsupportedOperationException
		 * @see org.drools.spi.PropagationContext#clearRetractedTuples()
		 */
		@Override
		public void clearRetractedTuples() {
			throw new UnsupportedOperationException("clearRetractedTuples() is not supported");
		}

		/* (non-Javadoc)
		 * @see org.drools.spi.PropagationContext#getActivationOrigin()
		 */
		@Override
		public Activation getActivationOrigin() {
			return activation;
		}

		/**
		 * Not supported.
		 * 
		 * @throws UnsupportedOperationException
		 * @see org.drools.spi.PropagationContext#getActiveActivations()
		 */
		@Override
		public int getActiveActivations() {
			throw new UnsupportedOperationException("getActiveActivations() is not supported");
		}

		/**
		 * Not supported.
		 * 
		 * @throws UnsupportedOperationException
		 * @see org.drools.spi.PropagationContext#getDormantActivations()
		 */
		@Override
		public int getDormantActivations() {
			throw new UnsupportedOperationException("getDormantActivations() is not supported");
		}

		/**
		 * Not supported.
		 * 
		 * @throws UnsupportedOperationException
		 * @see org.drools.spi.PropagationContext#getPropagationNumber()
		 */
		@Override
		public long getPropagationNumber() {
			throw new UnsupportedOperationException("getDormantActivations() is not supported");
		}

		/* (non-Javadoc)
		 * @see org.drools.spi.PropagationContext#getRuleOrigin()
		 */
		@Override
		public Rule getRuleOrigin() {
			return activation.getRule();
		}

		/**
		 * Not sure what this should actually be, but this returns
		 * a type ASSERTION. 
		 * 
		 * @see org.drools.spi.PropagationContext#getType()
		 */
		@Override
		public int getType() {
			return PropagationContext.ASSERTION;
		}

		/**
		 * Does nothing.
		 * 
		 * @see org.drools.spi.PropagationContext#releaseResources()
		 */
		@Override
		public void releaseResources() {
			// does nothing
		}

		/**
		 * Not supported.
		 * 
		 * @throws UnsupportedOperationException
		 * @see org.drools.spi.PropagationContext#removeRetractedTuple(org.drools.rule.Rule, org.drools.reteoo.ReteTuple)
		 */
		@Override
		public Activation removeRetractedTuple(Rule rule, ReteTuple tuple) {
			throw new UnsupportedOperationException("removeRetractedTuple() is not supported");
		}

	}
	
	public List<EObject> objects = new ArrayList<EObject>();
	public List<KnowledgeHelper> helpers = new ArrayList<KnowledgeHelper>();
	public List<Activation> activations = new ArrayList<Activation>();
	
	private boolean trackInsertions;
	// data storage structures
	private Map<EObject,Activation> tracked = new HashMap<EObject,Activation>();
	private Map<Activation,List<EObject>> activationObjects = new HashMap<Activation,List<EObject>>();
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param trackInsertions should we keep track of which activations
	 * 	various elements were added?
	 * 
	 * @see #getActivationFor(EObject)
	 */
	public DroolsInsertionQueue(boolean trackInsertions) {
		super();
		this.trackInsertions = trackInsertions;
	}
	
	/**
	 * Load additional information from the drools object.
	 * This is the method called from the Drools rules to 
	 * add it to the insertion queue.
	 * 
	 * @param e
	 * @param drools
	 * @return
	 */
	public void add(EObject e, KnowledgeHelper drools) {
		objects.add(e);
		helpers.add(drools);
		Activation a = drools.getActivation();
		activations.add(a);
		
		// should we track the insertions for each activation?
		if (trackInsertions) {
			// map from EObject to Activation
			tracked.put(e, a);
			// list map from Activation to EObject
			if (activationObjects.get(a) == null) {
				activationObjects.put(a, new ArrayList<EObject>());
			}
			activationObjects.get(a).add(e);
		}
	}
	
	/**
	 * Is this queue empty?
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return objects.isEmpty();
	}

	/**
	 * The number of objects queued in here.
	 * 
	 * @return
	 */
	public int size() {
		return objects.size();
	}
	
	/**
	 * Remove the existing WorkingMemoryEventListeners from the
	 * WorkingMemory, so that these are not called while we insert
	 * objects. 
	 * 
	 * @param memory
	 * @return
	 */
	private List<WorkingMemoryEventListener> removeExistingListeners(WorkingMemory memory) {
		List<WorkingMemoryEventListener> listeners = new ArrayList<WorkingMemoryEventListener>();
		List<?> oldListeners = memory.getWorkingMemoryEventListeners();
		for (Object obj : oldListeners) {
			listeners.add((WorkingMemoryEventListener) obj);
		}
		
		for (WorkingMemoryEventListener listener : listeners) {
			memory.removeEventListener((WorkingMemoryEventListener) listener);
		}

		return listeners;
	}
	
	private void addExistingListeners(List<WorkingMemoryEventListener> listeners, WorkingMemory memory) {
		for (WorkingMemoryEventListener listener : listeners) {
			memory.addEventListener(listener);
		}
	}

	/**
	 * Apply all the new objects to the given working memory.
	 * Also throws WorkingMemoryEvents for each added event.
	 * 
	 * @param memory
	 * @param log 
	 */
	public void apply(WorkingMemory memory, int step, InferenceQueueLog log) {
		assert(objects.size() == helpers.size());
		
		// first, remove all WorkingMemoryEventListeners
		// (or else memory.insert() below will still call them)
		List<WorkingMemoryEventListener> listeners = removeExistingListeners(memory);
		
		for (int i = 0; i < objects.size(); i++) {
			FactHandle handle = memory.insert(objects.get(i));

			// log it
			log.increment("step " + step + " type " + objects.get(i).eClass().getName(), 1);

			// throw a new event for all listeners
			KnowledgeHelper drools = helpers.get(i);
			for (WorkingMemoryEventListener listener : listeners) {
				listener.objectInserted(new ObjectInsertedEvent(
						drools.getWorkingMemory(),
						new MyPropogationContext(activations.get(i)),
						handle,
						objects.get(i)
					));
			}
		}
		
		// add all the listeners back again
		addExistingListeners(listeners, memory);
	}
	
	/**
	 * If we have been tracking activation insertions, this will get the
	 * activation for a particular object insertion, or null if none
	 * was found.
	 * 
	 * @param object
	 */
	public Object getActivationFor(EObject object) {
		return tracked.get(object);
	}
	
	/**
	 * If we have been tracking activation insertions, this will get all 
	 * of the objects that were inserted for a particular activation, or
	 * null if this activation was not found.
	 * 
	 * @param object
	 */
	public List<EObject> getInsertedObjectsForActivation(Object a) {
		if (!(a instanceof Activation)) {
			throw new IllegalArgumentException("Expected object type '" + a + "' to be an Activation, was: " + a.getClass().getSimpleName());
		}
		return activationObjects.get(a);
	}

	/**
	 * Since we create a new queue for each iteration step, this will
	 * add all the activations and EObjects from the old queue.
	 * 
	 * @throws IllegalArugmentException if we are not tracking insertions
	 * @throws IllegalStateException if the current tracking queues aren't empty
	 * 
	 * @param oldQueue
	 */
	public void addPreviousInsertions(DroolsInsertionQueue oldQueue) {
		if (!trackInsertions) {
			throw new IllegalArgumentException("Cannot add previous insertions - tracking is not enabled.");
		}
		if (!tracked.isEmpty()) {
			throw new IllegalStateException("The tracking map is not empty; this method should be called before any element insertion occurs."); 
		}
		if (!activationObjects.isEmpty()) {
			throw new IllegalStateException("The activation objects map is not empty; this method should be called before any element insertion occurs."); 
		}
		// we don't really need to copy them all; just assigning them will be enough
		tracked = oldQueue.tracked;
		activationObjects = oldQueue.activationObjects;
		
	}

}