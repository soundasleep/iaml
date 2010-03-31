/**
 * 
 */
package org.openiaml.model.codegen;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the default runtime properties.
 * 
 * @author jmwright
 *
 */
public class DefaultRuntimeProperties {

	/**
	 * Get the default runtime properties.
	 * 
	 * @return
	 */
	public Map<String, String> getDefaultProperties() {
		Map<String, String> properties = new HashMap<String,String>();
		
		properties.put("include_runtime", "true");
		properties.put("email_handler", "phpmailer");
		properties.put("email_handler_phpmailer_include", "include/phpmailer/");
		properties.put("email_handler_file_destination", "email-output.properties");
		
		return properties;
	}
	
}
