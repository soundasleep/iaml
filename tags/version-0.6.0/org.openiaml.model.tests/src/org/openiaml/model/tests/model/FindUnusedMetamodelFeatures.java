/**
 * 
 */
package org.openiaml.model.tests.model;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.tests.XmlTestCase;

/**
 * Investigate all model instances and locate unused metamodel features.
 * 
 * @author jmwright
 *
 */
public class FindUnusedMetamodelFeatures extends XmlTestCase {

	/**
	 * Get all possible model instances. This is done by:
	 * 
	 * <ol>
	 * 	<li>Getting all .iaml files in <code>/infer-output</code>
	 *  <li>Recursing over all folders in the <code>org.openiaml.model.tests</code> plugin for .iaml files
	 *  <li>Recursing over all folders in the <code>org.openiaml.model.tests.diagram</code> plugin for .iaml files
	 * </ol>
	 * 
	 * @return
	 */
	public List<File> getModelInstances() {
		List<File> instances = new ArrayList<File>();
		
		// first, /infer-output
		instances.addAll(recurseOver(new File("infer-output/"), "iaml"));
		
		// then, org.openiaml.model.tests
		instances.addAll(recurseOver(new File("src/"), "iaml"));
		
		// then, org.openiaml.model.tests.diagram
		instances.addAll(recurseOver(new File("../org.openiaml.model.tests.diagram/src/"), "iaml"));
		
		// remove any file that is stored in /examples/, to reduce problems
		// also remove any migration models
		// TODO this code will not be necessary when the /examples/ model folder is removed
		{
			List<File> i2 = new ArrayList<File>(); 
			for (File f : instances) {
				if (!f.getAbsolutePath().contains("\\examples\\")
						&& !f.getAbsolutePath().contains("\\eclipse\\migration\\"))
					i2.add(f);
			}
			instances = i2;
		}
		
		return instances;
		
	}
	
	/**
	 * We should have at least one model instance.
	 */
	public void testModelInstancesNotEmpty() {
		assertFalse(getModelInstances().isEmpty());
	}
	
	public void testFindUnusedFeatures() throws ModelLoadException, IOException {
		
		// first, make a map of all non-abstract classes to all superclasses
		Map<EClass,Set<EClass>> unusedSuperClassFeaturesMap = 
			new HashMap<EClass,Set<EClass>>();
		for (EClass cls : ContainmentTestCase.getAllClasses().keySet()) {
			Set<EClass> supers = new HashSet<EClass>();
			supers.addAll(cls.getEAllSuperTypes());
			unusedSuperClassFeaturesMap.put(cls, supers);
		}
		
		int count = 0;
		List<File> instances = getModelInstances();
		/*
		{
			// get only the first 10
			List<File> i2 = new ArrayList<File>();
			for (int i = 0; i < 10; i++) {
				i2.add(instances.get(i));
			}
			instances = i2;			
		}
		*/
		
		for (File f : instances) {
			// print out the progress
			count++;
			System.out.println(f.getName() + ": (" + count + "/" + instances.size() + ")");
			
			// load the file
			EObject root = ModelLoader.load(f.getAbsolutePath());			
			assertNotNull("Could not load file '" + f + "'", root);
			
			// now find unused features
			TreeIterator<EObject> it = root.eAllContents();
			while (it.hasNext()) {
				EObject e = it.next();
				EClass cls = e.eClass();
				// check all attributes
				for (EAttribute attr : cls.getEAllAttributes()) {
					// is it non-default?
					if (e.eGet(attr) != attr.getDefaultValue()) {
						// yes; remove it from the unused features (if it exists)
						EClass container = attr.getEContainingClass();
						unusedSuperClassFeaturesMap.get(cls).remove(container);
					}
				}
				
				// check all references
				for (EReference ref : cls.getEAllReferences()) {
					if (ref.isMany()) {
						// is it empty?
						if (!((List<?>) e.eGet(ref)).isEmpty()) {
							// no; remove it from the unused features (if it exists)
							EClass container = ref.getEContainingClass();
							unusedSuperClassFeaturesMap.get(cls).remove(container);
						}
					} else {
						// is it null?
						if (e.eGet(ref) != null) {
							// no; remove it from the unused features (if it exists)
							EClass container = ref.getEContainingClass();
							unusedSuperClassFeaturesMap.get(cls).remove(container);
						}
					}
				}
			}
			
			// finally, print out all unused types, to an HTML file
			printOutUnusedSuperClassFeatures(unusedSuperClassFeaturesMap);
		}
		
	}
	
	/**
	 * Print out the given map to an HTML file.
	 * 
	 * @param unusedSuperClassFeaturesMap
	 * @throws IOException 
	 */
	private void printOutUnusedSuperClassFeatures(
			Map<EClass, Set<EClass>> map) throws IOException {

		FileWriter unused = new FileWriter(new File("unused.html"));
		unused.write("<html><style>html { font-family: Arial; }</style><body>\n\n");
		for (EClass cls : map.keySet()) {
			// get a color
			int unusedCount = map.get(cls).size();
			int totalCount = cls.getEAllSuperTypes().size();
			String color = "";
			if (totalCount != 0) {
				double ratio = (double) unusedCount / (double) totalCount;
				if (ratio >= .75) {
					color = "background-color: #ff6666;";
				} else if (ratio >= .5) {
					color = "background-color: #ffff66;";
				} else if (ratio >= .25) {
					color = "background-color: #66ff66;";
				}
			}
			
			unused.write("<a name=\"");
			unused.write(cls.getName());
			unused.write("\"><h1 style=\"");
			unused.write(color);
			unused.write("\">");
			unused.write(cls.getName());
			unused.write("</h1></a>\n");
			
			// unused features
			unused.write("Unused supertypes (");
			unused.write(Integer.toString(unusedCount));
			unused.write("/");
			unused.write(Integer.toString(totalCount));
			unused.write("):\n");
			unused.write("<ul>");
			for (EClass u : map.get(cls)) {
				unused.write("<li><a href=\"#");
				unused.write(u.getName());
				unused.write("\">");
				if (u.isAbstract()) {
					unused.write("<i>");
				}
				unused.write(u.getName());
				if (u.isAbstract()) {
					unused.write("</i>");
				}
				unused.write("</a>");
				if (u.isInterface() && u.getEReferences().isEmpty() && u.getEAttributes().isEmpty()) {
					unused.write(" (empty interface)");
				}
				// do any of this classes supertypes use the reference?
				EClass usedBy = subTypeUses(map, cls, u); 
				if (usedBy != null) {
					unused.write(" (used by <a href=\"#");
					unused.write(usedBy.getName());
					unused.write("\">");
					unused.write(usedBy.getName());
					unused.write("</a>)");
				}
				unused.write("</li>");
			}
			unused.write("</ul>");
		}
	
		unused.write("\n\n</body></html>");
		unused.close();
		
	}

	/**
	 * Do any subtypes of this class use the given unused class?
	 * 
	 * @return
	 */
	private EClass subTypeUses(Map<EClass, Set<EClass>> map, EClass source, EClass unused) {
		for (EClass cls : map.keySet()) {
			if (cls.getEAllSuperTypes().contains(source)) {
				if (!map.get(cls).contains(unused)) {
					// this class uses it
					return cls;
				}
			}
		}
		
		return null;
	}

	/**
	 * Recurse over the given directory, and find all files that have the
	 * given extension. Throws an exception if the given file is not a directory.
	 * 
	 * @param f
	 * @param extension
	 * @return
	 */
	private Collection<? extends File> recurseOver(File f,
			final String extension) {
		assertTrue("File '" + f + "' did not exist", f.exists());
		assertTrue("File '" + f + "' is not a directory", f.isDirectory());
		assertFalse("Extension '" + extension + "' should not start with a .", extension.startsWith("."));
		
		// for all files in this folder
		File[] results = f.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("." + extension);
			}
		});
		
		List<File> out = new ArrayList<File>();
		for (File f2 : results) {
			assertTrue("Found file '" + f2 + "' does not exist.", f2.exists());
			out.add(f2);
		}
		
		// recurse over subfolders
		File[] sub = f.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		for (File s : sub) {
			out.addAll(recurseOver(s, extension));
		}
		
		return out;
	}

}
