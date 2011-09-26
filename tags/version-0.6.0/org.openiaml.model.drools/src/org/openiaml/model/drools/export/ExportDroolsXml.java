/**
 * 
 */
package org.openiaml.model.drools.export;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.compiler.DrlParser;
import org.drools.compiler.DroolsParserException;
import org.drools.lang.descr.PackageDescr;
import org.drools.xml.XmlDumper;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.InferenceException;

/**
 * <p>This helper method extracts drools rules and exports them as XML.
 * However, it only exports the LHS into XML, as the RHS is stored
 * by Drools as direct Java code.</p>
 * 
 * @author jmwright
 *
 */
public class ExportDroolsXml {

	/**
	 * Dump all the rule files into XML using XmlDumper, into a map
	 * of source files to generated XML strings.
	 * 
	 * @see org.drools.xml.XmlDumper
	 * @see CreateMissingElementsWithDrools#getRuleFiles()
	 * @return a map of filename to XML strings
	 * @throws InferenceException 
	 */
	public Map<String,String> getRuleXmls() throws InferenceException {
		CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(null, false);
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
