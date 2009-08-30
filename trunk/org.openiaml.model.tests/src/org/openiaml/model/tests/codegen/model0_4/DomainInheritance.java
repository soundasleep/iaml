/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * Test the code generation of domain inheritance.
 * 
 * @author jmwright
 *
 */
public class DomainInheritance extends DatabaseCodegenTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(DomainInheritance.class);
		initialiseDatabase();
	}
	
	@Override
	protected String getDatabaseName() {
		return "output/model_1236407970c_5.db";
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE Person (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO Person (id, name) VALUES (1, 'John')");
		s.add("INSERT INTO Person (id, name) VALUES (2, 'Jane')");
		s.add("INSERT INTO Person (id, name) VALUES (3, 'Bob')");
		s.add("INSERT INTO Person (id, name) VALUES (4, 'Alice')");
		s.add("INSERT INTO Person (id, name) VALUES (5, 'Sam')");
		s.add("INSERT INTO Person (id, name) VALUES (6, 'Mike')");
		s.add("INSERT INTO Person (id, name) VALUES (100, 'Test Name')");
		s.add("INSERT INTO Person (id, name) VALUES (101, 'David')");
		s.add("INSERT INTO Person (id, name) VALUES (102, 'Sue')");
		s.add("INSERT INTO Person (id, name) VALUES (24, 'Tracy')");
		
		s.add("CREATE TABLE Student (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, Person_id INTEGER NOT NULL, enrolled VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO Student (generated_primary_key, Person_id, enrolled) VALUES (7, 1, 'today')");
		s.add("INSERT INTO Student (generated_primary_key, Person_id, enrolled) VALUES (8, 2, 'yesterday')");
		s.add("INSERT INTO Student (generated_primary_key, Person_id, enrolled) VALUES (9, 3, 'Friday')");
		s.add("INSERT INTO Student (generated_primary_key, Person_id, enrolled) VALUES (106, 24, 'Thursday')");
		
		s.add("CREATE TABLE Qualified (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, qualification VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO Qualified (generated_primary_key, qualification) VALUES (10, 'bsc')");
		s.add("INSERT INTO Qualified (generated_primary_key, qualification) VALUES (11, 'ba')");
		s.add("INSERT INTO Qualified (generated_primary_key, qualification) VALUES (12, 'binfsci')");
		s.add("INSERT INTO Qualified (generated_primary_key, qualification) VALUES (118, 'bsc')");

		s.add("CREATE TABLE Teacher (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, Person_id INTEGER NOT NULL, Qualified_generated_primary_key INTEGER NOT NULL, title VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO Teacher (generated_primary_key, Person_id, Qualified_generated_primary_key, title) VALUES (15, 4, 10, 'ms')");
		s.add("INSERT INTO Teacher (generated_primary_key, Person_id, Qualified_generated_primary_key, title) VALUES (16, 5, 11, 'mr')");
		s.add("INSERT INTO Teacher (generated_primary_key, Person_id, Qualified_generated_primary_key, title) VALUES (17, 6, 12, 'mr')");
		s.add("INSERT INTO Teacher (generated_primary_key, Person_id, Qualified_generated_primary_key, title) VALUES (107, 24, 118, 'dr')");

		s.add("CREATE TABLE Undergraduate (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, Student_generated_primary_key INTEGER NOT NULL, degree VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO Undergraduate (generated_primary_key, Student_generated_primary_key, degree) VALUES (18, 8, 'nutrition')");
		s.add("INSERT INTO Undergraduate (generated_primary_key, Student_generated_primary_key, degree) VALUES (19, 9, 'music')");

		s.add("CREATE TABLE Postgraduate (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, Student_generated_primary_key INTEGER NOT NULL, degree VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO Postgraduate (generated_primary_key, Student_generated_primary_key, degree) VALUES (20, 8, 'art')");
		s.add("INSERT INTO Postgraduate (generated_primary_key, Student_generated_primary_key, degree) VALUES (21, 106, 'science')");

		s.add("CREATE TABLE Doctoral (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, Postgraduate_generated_primary_key INTEGER NOT NULL, Teacher_generated_primary_key INTEGER NOT NULL, thesis_title VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO Doctoral (generated_primary_key, Postgraduate_generated_primary_key, Teacher_generated_primary_key, thesis_title) VALUES (119, 21, 107, 'complicated database inheritances')");

		return s;
	}

	/**
	 * Select person: name = 'Test User'.
	 * This should work without any major problems because it is a
	 * standard query on the root object of the heirarchy.
	 * 
	 * @throws Exception
	 */
	public void testPerson() throws Exception {
		beginAtSitemapThenPage("get person");
		assertNoProblem();
		
		String name = getLabelIDForText("name");
		assertLabeledFieldEquals(name, "Test Name");
	}

	/**
	 * Select student: enrolled = 'yesterday'
	 * 
	 * @throws Exception
	 */
	public void testStudent() throws Exception {
		beginAtSitemapThenPage("get student");
		assertNoProblem();

		String enrolled = getLabelIDForText("enrolled");
		assertLabeledFieldEquals(enrolled, "yesterday");

		String name = getLabelIDForText("name");
		assertLabeledFieldEquals(name, "Jane");
	}
	
	/**
	 * Select teacher: id = 24
	 * 
	 * @throws Exception
	 */
	public void testTeacherById() throws Exception {
		beginAtSitemapThenPage("get teacher by id");
		assertNoProblem();

		String title = getLabelIDForText("title");
		assertLabeledFieldEquals(title, "dr");
		
		// derived one way
		String qualification = getLabelIDForText("qualification");
		assertLabeledFieldEquals(qualification, "bsc");
		
		// derived another way
		String name = getLabelIDForText("name");
		assertLabeledFieldEquals(name, "Tracy");
		
		// even though this instance is an instance of Doctoral,
		// we should not have any labelled fields of the Doctoral fields
		assertHasNotLabelIDForText("thesis title");
	}
	
	/**
	 * Select doctoral: qualification = 'bsc' and degree = 'science'
	 * 
	 * @throws Exception
	 */
	public void testDoctoral() throws Exception {
		beginAtSitemapThenPage("get doctoral");
		assertNoProblem();
		
		// it should now be rendered
		String thesis = getLabelIDForText("thesis title");
		assertLabeledFieldEquals(thesis, "complicated database inheritances");
		
		// derived fields
		String title = getLabelIDForText("title", "thesis title");
		assertLabeledFieldEquals(title, "dr");
		
		// derived one way
		String qualification = getLabelIDForText("qualification");
		assertLabeledFieldEquals(qualification, "bsc");
		
		// derived another way
		String name = getLabelIDForText("name");
		assertLabeledFieldEquals(name, "Tracy");
		
	}
	
}
