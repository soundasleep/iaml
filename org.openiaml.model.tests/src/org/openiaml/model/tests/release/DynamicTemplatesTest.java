/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.tests.model.ModelTestCase;

/**
 * Issue 154: Check that NodeEditPart.xpt Template is synchronized with the metamodel
 * 
 * @author jmwright
 *
 */
public class DynamicTemplatesTest extends XmlTestCase {

	public static final String TEMPLATE_DIR = ParentNamesTestCase.GMF_ROOT + "../templates/";

	File output = new File(TEMPLATE_DIR + "aspects/impl/diagram/editparts/NodeEditPart.xpt");
	File template = new File(TEMPLATE_DIR + "aspects/impl/diagram/editparts/NodeEditPart.template.xpt");

	/**
	 * Check the NodeEditPart.xpt template.
	 * @throws IOException 
	 * 
	 * @see #getExpectedContent(String)
	 */
	public void testNodeEditPartTest() throws IOException {
		
		String expected = getExpectedContent(readFile(template), template);
		
		if (!output.exists() || !readFile(output).equals(expected)) {
			System.out.println("Writing '" + output + "'...");
			
			FileWriter fw = new FileWriter(output);
			fw.write(expected);
			fw.close();
		}
		
	}
	
	/**
	 * Make sure that the files actually exist.
	 */
	public void testNodeEditPartExists() {
		assertTrue(output.exists());
		assertTrue(template.exists());
	}

	/**
	 * Replace content within the given template string with new
	 * template content, and return the compiled template.
	 * 
	 * @param template the template file
	 * @return
	 */
	private String getExpectedContent(String template, File input) {
		
		String guilOpen = "\u00c2\u00ab";
		String guilClose = "\u00c2\u00bb";
		
		Map<String, String> replace = new HashMap<String, String>();
		replace.put("{template-warning}", "WARNING: This file has been automatically generated from '" + input + "'.");
		
		// create {template-classname-list}
		{
			StringBuffer buffer = new StringBuffer();
			for (String node : ParentNamesTestCase.getParentNameElements()) {
				if (buffer.length() != 0) {
					buffer.append(" or ");
				}
				
				buffer.append("className = \"").append(node).append("Figure\"");
			}
			for (String node : ParentNamesTestCase.getContainmentFeatureElements()) {
				if (buffer.length() != 0) {
					buffer.append(" or ");
				}
				
				buffer.append("className = \"").append(node).append("Figure\"");
			}
			for (String node : ParentNamesTestCase.getTypedElements()) {
				if (buffer.length() != 0) {
					buffer.append(" or ");
				}
				
				buffer.append("className = \"").append(node).append("Figure\"");
			}
			replace.put("{template-classname-list}", buffer.toString());
		}
		
		// create {template-parents}
		{
			StringBuffer buffer = new StringBuffer();
			for (String node : ParentNamesTestCase.getParentNameElements()) {
				buffer.append(guilOpen).append("IF className=\"").append(node).append("Figure\"").append(guilClose).append("\n");
				buffer.append("getFigure").append(node).append("ParentNameFigure().setText(parentName);\n");
				buffer.append(guilOpen).append("ENDIF").append(guilClose).append("\n");
			}
			replace.put("{template-parents}", buffer.toString());
		}

		// create {template-containments}
		{
			StringBuffer buffer = new StringBuffer();
			for (String node : ParentNamesTestCase.getContainmentFeatureElements()) {
				buffer.append(guilOpen).append("IF className=\"").append(node).append("Figure\"").append(guilClose).append("\n");
				buffer.append("getFigure").append(node).append("ContainmentFeatureFigure().setText(containmentName);\n");
				buffer.append(guilOpen).append("ENDIF").append(guilClose).append("\n");
			}
			replace.put("{template-containments}", buffer.toString());
		}
		
		// create {template-types}
		{
			StringBuffer buffer = new StringBuffer();
			for (EClass cls : ParentNamesTestCase.getTypedElementClasses()) {
				String node = cls.getName();
				Class<?> resolved = cls.getInstanceClass();
				String fqn = resolved.getPackage().getName() + "." + resolved.getSimpleName();
				buffer.append(guilOpen).append("IF className=\"").append(node).append("Figure\"").append(guilClose).append("\n");
				buffer.append(guilOpen).append("EXPAND getTypeName(\"").append(fqn).append("\", \"").append(node).append("\")").append(guilClose).append("\n");
				buffer.append(guilOpen).append("ENDIF").append(guilClose).append("\n");
			}
			replace.put("{template-types}", buffer.toString());
		}
		
		// create {template-stereotypes}
		{
			StringBuffer buffer = new StringBuffer();
			for (EClass cls : ModelTestCase.getAllEClasses()) {
				String node = cls.getName();		// EventTrigger
				buffer.append(guilOpen).append("IF className=\"").append(node).append("Figure\"").append(guilClose).append("\n");
				buffer.append("getFigure").append(node).append("StereotypeFigure().setText(\": \" + resolvedObject.eClass().getName());\n");
				buffer.append(guilOpen).append("ENDIF").append(guilClose).append("\n");
				
			}
			replace.put("{template-stereotypes}", buffer.toString());
		}
		
		for (String key : replace.keySet()) {
			template = template.replace(key, replace.get(key));
		}
		
		return template;
		
	}
	
}
