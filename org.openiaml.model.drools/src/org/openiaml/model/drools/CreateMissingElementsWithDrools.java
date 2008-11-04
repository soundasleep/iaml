/**
 * 
 */
package org.openiaml.model.drools;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.rule.Package;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.InternetApplication;

import com.sample.DroolsTest;
import com.sample.DroolsTest.Message;

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
        WorkingMemory workingMemory = ruleBase.newStatefulSession();
        
        //go !
        workingMemory.insert( root );
        workingMemory.insert( handler );
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
		Reader source = new InputStreamReader( DroolsTest.class.getResourceAsStream( "/Testrules.drl" ) );
		
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
