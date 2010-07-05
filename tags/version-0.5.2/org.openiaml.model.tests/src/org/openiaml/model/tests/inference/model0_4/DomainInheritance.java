/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.List;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Inference of access control handlers.
 *
 * @author jmwright
 *
 */
public class DomainInheritance extends InferenceTestCase {

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(DomainInheritance.class);
		
		{
			Frame page = assertHasFrame(root, "get person");
			InputForm form = assertHasInputForm(page, "view person");
			assertHasNone(form, "iaml:children");
			assertNotGenerated(page);
			assertNotGenerated(form);
		}

		{
			Frame page = assertHasFrame(root, "get student");
			InputForm form = assertHasInputForm(page, "view student");
			assertHasNone(form, "iaml:children");
			assertNotGenerated(page);
			assertNotGenerated(form);
		}

		{
			Frame page = assertHasFrame(root, "get teacher by id");
			InputForm form = assertHasInputForm(page, "view teacher");
			assertHasNone(form, "iaml:children");
			assertNotGenerated(page);
			assertNotGenerated(form);
		}

		{
			Frame page = assertHasFrame(root, "get doctoral");
			InputForm form = assertHasInputForm(page, "view doctoral");
			assertHasNone(form, "iaml:children");
			assertNotGenerated(page);
			assertNotGenerated(form);
		}
		
		DomainSchema person = assertHasDomainSchema(root, "Person");
		assertNotGenerated(person);
		DomainSchema q = assertHasDomainSchema(root, "Qualified");
		assertNotGenerated(q);
		DomainSchema t = assertHasDomainSchema(root, "Teacher");
		assertNotGenerated(t);
		DomainSchema s = assertHasDomainSchema(root, "Student");
		assertNotGenerated(s);
		DomainSchema pg = assertHasDomainSchema(root, "Postgraduate");
		assertNotGenerated(pg);
		DomainSchema d = assertHasDomainSchema(root, "Doctoral");
		assertNotGenerated(d);
		DomainSchema cu = assertHasDomainSchema(root, "Completed Undergrad");
		assertNotGenerated(cu);

	}
	
	/**
	 * Test the contents of the "get person" page.
	 * 
	 * @throws Exception
	 */
	public void testPerson() throws Exception {
		root = loadAndInfer(DomainInheritance.class);
		
		Frame page = assertHasFrame(root, "get person");
		InputForm form = assertHasInputForm(page, "view person");
		
		InputTextField name = assertHasInputTextField(form, "name");
		assertGenerated(name);
		
		// the primary key is not rendered
		assertHasNoInputTextField(form, "id");
		
		// direct subclasses
		assertHasNoInputTextField(form, "enrolled");
		assertHasNoInputTextField(form, "title");
	}
	
	/**
	 * Test the contents of the "get student" page.
	 * 
	 * @throws Exception
	 */
	public void testStudent() throws Exception {
		root = loadAndInfer(DomainInheritance.class);
		
		Frame page = assertHasFrame(root, "get student");
		InputForm form = assertHasInputForm(page, "view student");
		
		// direct field
		InputTextField enrolled = assertHasInputTextField(form, "enrolled");
		assertGenerated(enrolled);
		
		// inherited fields
		InputTextField name = assertHasInputTextField(form, "name");
		assertGenerated(name);
		
		// the derived primary key is not rendered
		assertHasNoInputTextField(form, "Person.id");
				
		// primary key
		assertHasNoInputTextField(form, "generated primary key");
		
		// direct subclasses
		assertHasNoInputTextField(form, "title");
		assertHasNoInputTextField(form, "id");
	}
	
	/**
	 * Test the contents of the "get teacher" page.
	 * 
	 * @throws Exception
	 */
	public void testTeacher() throws Exception {
		root = loadAndInfer(DomainInheritance.class);
		
		Frame page = assertHasFrame(root, "get teacher by id");
		InputForm form = assertHasInputForm(page, "view teacher");
		
		// direct field
		InputTextField title = assertHasInputTextField(form, "title");
		assertGenerated(title);
		
		// inherited fields
		InputTextField qualification = assertHasInputTextField(form, "qualification");
		assertGenerated(qualification);
		InputTextField name = assertHasInputTextField(form, "name");
		assertGenerated(name);

		// the derived primary key is not rendered
		assertHasNoInputTextField(form, "Person.id");
		
		// primary key
		assertHasNoInputTextField(form, "generated primary key");
		
		// direct subclasses
		assertHasNoInputTextField(form, "enrolled");
		assertHasNoInputTextField(form, "id");
		assertHasNoInputTextField(form, "Qualified.generated primary key");
	}
	
	/**
	 * Test the contents of the "get doctoral" page.
	 * 
	 * @throws Exception
	 */
	public void testDoctoral() throws Exception {
		root = loadAndInfer(DomainInheritance.class);
		
		Frame page = assertHasFrame(root, "get doctoral");
		InputForm form = assertHasInputForm(page, "view doctoral");
		
		// direct field
		InputTextField thesis = assertHasInputTextField(form, "thesis title");
		assertGenerated(thesis);
		
		// inherited fields
		InputTextField degree = assertHasInputTextField(form, "degree");
		assertGenerated(degree);
		InputTextField enrolled = assertHasInputTextField(form, "enrolled");
		assertGenerated(enrolled);
		InputTextField title = assertHasInputTextField(form, "title");
		assertGenerated(title);
		InputTextField qualification = assertHasInputTextField(form, "qualification");
		assertGenerated(qualification);
		InputTextField name = assertHasInputTextField(form, "name");
		assertGenerated(name);

		// the derived primary key is not rendered
		assertHasNoInputTextField(form, "Person.id");
				
		// primary key
		assertHasNoInputTextField(form, "generated primary key");
		
		// direct subclasses
		assertHasNoInputTextField(form, "id");
		assertHasNoInputTextField(form, "Qualified.generated primary key");
		assertHasNoInputTextField(form, "Teacher.generated primary key");
		assertHasNoInputTextField(form, "Student.generated primary key");
		assertHasNoInputTextField(form, "Postgraduate.generated primary key");
		assertHasNoInputTextField(form, "Doctoral.generated primary key");
	}
	
	/**
	 * Test the contents of the "selected person" instance
	 * 
	 * @throws Exception
	 */
	public void testPersonInstance() throws Exception {
		root = loadAndInfer(DomainInheritance.class);
		
		Frame page = assertHasFrame(root, "get person");
		DomainIterator instance = assertHasDomainIterator(page, "selected person");

		DomainAttributeInstance name = assertHasDomainAttributeInstance(instance, "name");
		assertGenerated(name);
		
		// primary key
		DomainAttributeInstance id = assertHasDomainAttributeInstance(instance, "id");
		assertGenerated(id);
		
		// direct subclasses
		assertHasNoDomainAttributeInstance(instance, "enrolled");
		assertHasNoDomainAttributeInstance(instance, "title");
	}
	
	/**
	 * Test the contents of the "selected student" instance.
	 * 
	 * @throws Exception
	 */
	public void testStudentInstance() throws Exception {
		root = loadAndInfer(DomainInheritance.class);
		
		Frame page = assertHasFrame(root, "get student");
		DomainIterator instance = assertHasDomainIterator(page, "current student");
		
		// direct field
		DomainAttributeInstance enrolled = assertHasDomainAttributeInstance(instance, "enrolled");
		assertGenerated(enrolled);
		
		// inherited field
		DomainAttributeInstance name = assertHasDomainAttributeInstance(instance, "name");
		assertGenerated(name);
				
		// primary key
		DomainAttributeInstance key = assertHasDomainAttributeInstance(instance, "generated primary key");
		assertGenerated(key);
		
		// foreign key
		DomainAttributeInstance Person_id = assertHasDomainAttributeInstance(instance, "Person.id");
		assertGenerated(Person_id);
		assertExtends(Person_id, "Student");
	}
	
	/**
	 * Test the contents of the "selected teacher" instance.
	 * 
	 * @throws Exception
	 */
	public void testTeacherInstance() throws Exception {
		root = loadAndInfer(DomainInheritance.class);
		
		Frame page = assertHasFrame(root, "get teacher by id");
		DomainIterator instance = assertHasDomainIterator(page, "selected teacher");
		
		// direct field
		DomainAttributeInstance title = assertHasDomainAttributeInstance(instance, "title");
		assertGenerated(title);
		
		// inherited field
		DomainAttributeInstance qualification = assertHasDomainAttributeInstance(instance, "qualification");
		assertGenerated(qualification);
		DomainAttributeInstance name = assertHasDomainAttributeInstance(instance, "name");
		assertGenerated(name);
				
		// primary key
		DomainAttributeInstance key = assertHasDomainAttributeInstance(instance, "generated primary key");
		assertGenerated(key);
		
		// foreign keys
		DomainAttributeInstance Person_id = assertHasDomainAttributeInstance(instance, "Person.id");
		assertGenerated(Person_id);
		assertExtends(Person_id, "Teacher");
		DomainAttributeInstance qid = assertHasDomainAttributeInstance(instance, "Qualified.generated primary key");
		assertGenerated(qid);
		assertExtends(qid, "Teacher");
		
	}
	
	/**
	 * Test the contents of the "selected doctoral" instance.
	 * 
	 * @throws Exception
	 */
	public void testDoctoralInstance() throws Exception {
		root = loadAndInfer(DomainInheritance.class);
		
		Frame page = assertHasFrame(root, "get doctoral");
		DomainIterator instance = assertHasDomainIterator(page, "current doctoral");
		
		// direct field
		DomainAttributeInstance thesis = assertHasDomainAttributeInstance(instance, "thesis title");
		assertGenerated(thesis);
		
		// inherited field
		DomainAttributeInstance degree = assertHasDomainAttributeInstance(instance, "degree");
		assertGenerated(degree);
		DomainAttributeInstance enrolled = assertHasDomainAttributeInstance(instance, "enrolled");
		assertGenerated(enrolled);
		DomainAttributeInstance title = assertHasDomainAttributeInstance(instance, "title");
		assertGenerated(title);
		DomainAttributeInstance qualification = assertHasDomainAttributeInstance(instance, "qualification");
		assertGenerated(qualification);
		DomainAttributeInstance name = assertHasDomainAttributeInstance(instance, "name");
		assertGenerated(name);
				
		// primary key
		DomainAttributeInstance key = assertHasDomainAttributeInstance(instance, "generated primary key");
		assertGenerated(key);
		
		// foreign keys
		DomainAttributeInstance Person_id = assertHasDomainAttributeInstance(instance, "Person.id");
		assertGenerated(Person_id);
		assertExtends(Person_id, "Doctoral");
		DomainAttributeInstance qid = assertHasDomainAttributeInstance(instance, "Qualified.generated primary key");
		assertGenerated(qid);
		assertExtends(qid, "Doctoral");
		DomainAttributeInstance pid = assertHasDomainAttributeInstance(instance, "Postgraduate.generated primary key");
		assertGenerated(pid);
		assertExtends(pid, "Doctoral");
		DomainAttributeInstance sid = assertHasDomainAttributeInstance(instance, "Student.generated primary key");
		assertGenerated(sid);
		assertExtends(sid, "Doctoral");
		DomainAttributeInstance tid = assertHasDomainAttributeInstance(instance, "Teacher.generated primary key");
		assertGenerated(tid);
		assertExtends(tid, "Doctoral");
		
	}
	
	/**
	 * Assert that the given attribute extends another attribute contained in a DomainObject 
	 * 
	 * @param attr
	 * @param name
	 * @throws Exception
	 */
	private void assertExtends(DomainAttributeInstance attr, String name) throws Exception {
		List<ExtendsEdge> edges = attr.getOutExtendsEdges();
		assertEquals(1, edges.size());
		ExtendsEdge ext = (ExtendsEdge) edges.iterator().next();
		
		DomainAttribute targetAttr = (DomainAttribute) ext.getTo();
		assertNotNull(targetAttr);
		
		DomainSchema targetObj = (DomainSchema) targetAttr.eContainer();
		assertNotNull(targetObj);		
		assertEquals(name, targetObj.getName());
	}
	
	/**
	 * The inferred model should be valid.
	 * 
	 * @throws Exception
	 */
	public void testInferredModelIsValid() throws Exception {
		checkModelIsValid(loadAndInfer(DomainInheritance.class));
	}
	
}
