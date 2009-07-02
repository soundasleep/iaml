/**
 * 
 */
package org.openiaml.model.drools;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.compiler.DrlParser;
import org.drools.compiler.DroolsParserException;
import org.drools.lang.descr.PackageDescr;
import org.drools.xml.XmlDumper;
import org.openiaml.model.inference.InferenceException;

/**
 * @author jmwright
 *
 */
public class DroolsXmlDumper {

	/**
	 * Dump all the rule files into XML using XmlDumper.
	 * 
	 * @see org.drools.xml.XmlDumper
	 * @return a map of filename to XML
	 * @throws InferenceException 
	 */
	public Map<String,String> getRuleXmls() throws InferenceException {
		CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(null);
		List<String> files = ce.getRuleFiles();
		Map<String,String> result = new HashMap<String,String>();
		
		for (String f : files) {
			Reader source = new InputStreamReader( 
					CreateMissingElementsWithDrools.class.getResourceAsStream( f ) );
			
			DrlParser parser = new DrlParser();
			try {
				PackageDescr pkg2 = parser.parse( source );
				
				String out = new XmlDumper().dump(pkg2);
				
				result.put(f, out);
			} catch (DroolsParserException e) {
				throw new InferenceException(e);
			}
		}
		
		return result;		
	}
	
}
