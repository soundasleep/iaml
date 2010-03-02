/**
 *
 */
package org.openiaml.model.tests.inference.model0_3;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;

/**
 * Tests automatic mapping of DomainStores when connected to
 * Properties files
 *
 * @author jmwright
 *
 */
public class PropertiesFileMapping extends EclipseInheritanceInterface {

	@Override
	public Class<? extends EclipseInheritanceInterface> getTestClass() {
		return PropertiesFileMapping.class;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.inference.EclipseInheritanceInterface#checkNotInferredKnowledge(org.openiaml.model.model.InternetApplication)
	 */
	@Override
	public void checkNotInferredKnowledge(InternetApplication root)
			throws Exception {
		
		DomainStore ds = assertHasDomainStore(root, "my domain store");
		assertEquals(0, ds.getChildren().size());
		assertEquals(0, ds.getAttributes().size());
		assertEquals(0, ds.getOperations().size());
		
	}
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.inference.EclipseInheritanceInterface#checkInferredKnowledge(org.openiaml.model.model.InternetApplication)
	 */
	@Override
	public void checkInferredKnowledge(InternetApplication root)
			throws Exception {

		DomainStore ds = assertHasDomainStore(root, "my domain store");
		assertEquals(1, ds.getChildren().size());	// one domain object called 'properties'
		assertEquals(0, ds.getAttributes().size());
		assertEquals(0, ds.getOperations().size());

		DomainObject dobj = assertHasDomainObject(ds, "properties");
		System.out.println(dobj);
		System.out.println(dobj.getAttributes());
		assertEquals(4, dobj.getAttributes().size());	// four attributes (one is a generated primary key)
		assertEquals(0, dobj.getOperations().size());
		assertEquals(0, dobj.getEventTriggers().size());

		{
			DomainAttribute attribute = assertHasDomainAttribute(dobj, "fruit");
			assertEquals(attribute.getName(), "fruit");
		}
		{
			DomainAttribute attribute = assertHasDomainAttribute(dobj, "animal");
			assertEquals(attribute.getName(), "animal");
		}
		{
			DomainAttribute attribute = assertHasDomainAttribute(dobj, "empty");
			assertEquals(attribute.getName(), "empty");
		}
		{
			DomainAttribute attribute = assertHasDomainAttribute(dobj, "generated primary key");
			assertEquals(attribute.getName(), "generated primary key");
			assertTrue(attribute.isPrimaryKey());
		}

	}


}
