/**
 * 
 */
package org.openiaml.model.drools;

import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.PackageBuilder;
import org.drools.event.ObjectInsertedEvent;
import org.drools.event.ObjectRetractedEvent;
import org.drools.event.ObjectUpdatedEvent;
import org.drools.event.WorkingMemoryEventListener;
import org.drools.rule.Package;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.WireEdge;

/**
 * Uses Drools to infer new elements.
 * 
 * @author jmwright
 *
 */
public class CreateMissingElementsWithDrools {
	
	ICreateElements handler;

	public CreateMissingElementsWithDrools(ICreateElements handler) {
		this.handler = handler;
	}

	/**
	 * Do the inference using Drools.
	 * 
	 * @param root
	 * @throws Exception 
	 */
	public void create(InternetApplication root) throws Exception {

    	//load up the rulebase
        RuleBase ruleBase = readRule();
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();
        
        // automatically insert new objects based on a given object
        workingMemory.addEventListener( new WorkingMemoryEventListener() {

        	/**
        	 * When we insert a new element, we automatically insert
        	 * all of its children elements.
        	 * 
        	 * (It's probably possible to do this also in the rulebase
        	 * itself, but this may be cleaner?)
        	 * 
        	 */
			@Override
			public void objectInserted(ObjectInsertedEvent obj) {
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
        
        //go !
        workingMemory.insert( root );        
        workingMemory.setGlobal("handler", handler);
        workingMemory.fireAllRules();   
        
	}

	/**
	 * Copied from sample DroolsTest.java.
	 * 
	 * @return
	 * @throws Exception 
	 */
	private RuleBase readRule() throws Exception {

		//read in the source
		Reader source = new InputStreamReader( CreateMissingElementsWithDrools.class.getResourceAsStream( "/Testrules.drl" ) );
		
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
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage( pkg );
		return ruleBase;

		
	}

}
