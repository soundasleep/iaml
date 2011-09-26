/**
 * 
 */
package org.openiaml.model.codegen.php;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.openarchitectureware.xtend.ast.Check;
import org.openarchitectureware.xtend.ast.ExtensionFile;
import org.openarchitectureware.xtend.parser.ParseFacade;

/**
 * @author jmwright
 *
 */
public class TestChecksParser extends TestCase {

	/**
	 * copied from 
	 * {@link org.openarchitectureware.check.CheckFacade#checkAll(String, InputStream, Collection<?>, ExecutionContext, Issues, boolean)}
	 */
	public void test1() throws Exception {
		String checkFile = "src/metamodel/Checks.chk";
		InputStream in = new FileInputStream(checkFile);
		
		ExtensionFile file = ParseFacade.file(new InputStreamReader(in), checkFile);
		
		for (Check check : file.getChecks()) {
			System.out.println("Message: " + check.getMsg());
			System.out.println(check.getConstraint().getClass());
			System.out.println("Constraint: " + check.getConstraint());
			System.out.println();
		}
	}
	
}
