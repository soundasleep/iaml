/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import java.util.Map;

import org.openiaml.model.codegen.DefaultRuntimeProperties;

/**
 * Tests actually sending an e-mail; i.e. not through the "file" EMAIL_HANDLER.
 * 
 */
public class ActuallySendingEmail extends EmailSetWire {

	private static boolean hasResetCodegenCache = false;
	
	@Override
	protected void setUp() throws Exception {
		// since we are extending, we don't want the cache to persist
		if (!hasResetCodegenCache)
			resetCodegenCache();
		hasResetCodegenCache = true;		

		super.setUp();
	}
	
	/**
	 * We don't want to check intercepted e-mails, since we can't get them.
	 */
	@Override
	protected boolean doEmailChecks() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.codegen.model0_5.EmailCodegenTestCase#getRuntimeProperties()
	 */
	@Override
	protected Map<String, String> getRuntimeProperties() {
		Map<String, String> props = super.getRuntimeProperties();
		
		// change back to default
		Map<String, String> defaults = new DefaultRuntimeProperties().getDefaultProperties();
		
		props.put("email_handler", defaults.get("email_handler"));
		
		return props;
	}
	
}
