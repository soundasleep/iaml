/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openiaml.model.tests.XmlTestCase;

/**
 * Exports the Eclipse plug-in dependency graph.
 * 
 * @author jmwright
 *
 */
public class ExportDependencyGraph extends XmlTestCase {

	/**
	 * Trim the string, remove any information following ';'.
	 * 
	 * @param s
	 * @return
	 */
	private String cleanUpRequirementString(String s) {
		s = s.trim();
		if (s.contains(";"))
			s = s.substring(0, s.indexOf(";")).trim();
		return s;
	}
	
	public void testExportDependencyGraph() throws Exception {
		Map<String, List<String>> dependencies = new HashMap<String, List<String>>();
		
		// load all bundle dependencies
		for (String manifest : PluginsTestCase.getAllManifests()) {
			File f = new File(manifest);	// guaranteed to exist
			String[] file = readFile(f).split("\n");
			
			List<String> bundles = new ArrayList<String>();
			String bundleName = null;
			boolean inBundle = true;
			for (String line : file) {
				if (line.startsWith("Bundle-SymbolicName: ")) {
					bundleName = cleanUpRequirementString(line.substring(line.indexOf(" ") + 1));
				}
				
				line = line.trim();
				if (line.startsWith("Require-Bundle: ")) {
					line = line.substring(line.indexOf(" ") + 1);
					if (line.endsWith(",")) {
						line = line.substring(0, line.length() - 1);
						inBundle = true;
					}
					bundles.add(cleanUpRequirementString(line));
				} else if (inBundle) {
					if (line.endsWith(",")) {
						bundles.add(cleanUpRequirementString(line.substring(0, line.length() - 1)));
					} else {
						inBundle = false;
					}
				}
				
			}
			
			// sort it
			sort(bundles);
			
			assertNotNull(bundleName);
			dependencies.put(bundleName, bundles);
		}

		// now export this out into dot
		{
			File outFile = new File("dot/all-dependencies.dot.txt");
			Writer out = new FileWriter(outFile);
			exportAllDependencies(dependencies, out);
			out.close();
			
			System.out.println("Wrote out dot to " + outFile);
		}
		
		{
			File outFile = new File("dot/local-dependencies.dot.txt");
			Writer out = new FileWriter(outFile);
			exportLocalDependencies(dependencies, out);
			out.close();
			
			System.out.println("Wrote out dot to " + outFile);
		}

		{
			File outFile = new File("dot/local-dependencies-notests.dot.txt");
			Writer out = new FileWriter(outFile);
			exportLocalDependenciesNoTests(dependencies, out);
			out.close();
			
			System.out.println("Wrote out dot to " + outFile);
		}
	}

	/**
	 * Sort out the list of bundles into a predictable order.
	 * 
	 * @param bundles the list of strings to sort; is modified
	 */
	private void sort(List<String> bundles) {
		Collections.sort(bundles);
	}

	public void exportAllDependencies(Map<String, List<String>> dependencies, Writer out) throws IOException {
		out.write("digraph {\n");
		out.write("  size=\"52,52\";\n");
		out.write("  ratio=expand;\n");
		
		Set<String> allBundles2 = new HashSet<String>();
		for (String id : dependencies.keySet()) {
			allBundles2.add(id);
			for (String req : dependencies.get(id)) {
				allBundles2.add(req);
			}
		}
		
		// convert to a list and sort
		List<String> allBundles = new ArrayList<String>(allBundles2);
		Collections.sort(allBundles);
		
		// print out all nodes that we are using
		out.write("  node [style=filled, fillcolor=\"#6677cc\"];\n");
		for (String bundleName : allBundles) {
			if (isOpeniaml(bundleName) && !isDiagram(bundleName) && !isEclipse(bundleName) && !isEmf(bundleName) && !isGmf(bundleName))
				out.write("\"" + bundleName + "\";\n");
		}
		out.write("  node [style=filled, fillcolor=\"#cc7766\"];\n");
		for (String bundleName : allBundles) {
			if (isOpeniaml(bundleName) && isDiagram(bundleName) && !isEclipse(bundleName) && !isEmf(bundleName) && !isGmf(bundleName))
				out.write("\"" + bundleName + "\";\n");
		}
		out.write("  node [style=filled, fillcolor=\"#ffee66\"];\n");
		for (String bundleName : allBundles) {
			if (!isOpeniaml(bundleName) && isEclipse(bundleName) && !isEmf(bundleName) && !isGmf(bundleName))
				out.write("\"" + bundleName + "\";\n");
		}
		out.write("  node [style=filled, fillcolor=\"#cc6677\"];\n");
		for (String bundleName : allBundles) {
			if (!isOpeniaml(bundleName) && !isEclipse(bundleName) && isEmf(bundleName) && !isGmf(bundleName))
				out.write("\"" + bundleName + "\";\n");
		}
		out.write("  node [style=filled, fillcolor=\"#66cc77\"];\n");
		for (String bundleName : allBundles) {
			if (!isOpeniaml(bundleName) && !isEclipse(bundleName) && !isEmf(bundleName) && isGmf(bundleName))
				out.write("\"" + bundleName + "\";\n");
		}
		
		// normal nodes
		out.write("  node [style=filled, fillcolor=\"#ffffff\"];\n");
		
		// sort out the key set
		List<String> bundleSorted = new ArrayList<String>(dependencies.keySet());
		Collections.sort(bundleSorted);
		for (String bundleName : bundleSorted) {
			for (String requires : dependencies.get(bundleName)) {
				out.write("  \"" + bundleName + "\" -> \"" + requires + "\";\n");
			}
		}
		out.write("}");
	}

	public void exportLocalDependencies(Map<String, List<String>> dependencies, Writer out) throws IOException {
		out.write("digraph {\n");
		out.write("  size=\"48,72\";\n");
		out.write("  ratio=expand;\n");
		
		// print out all nodes that we are using
		out.write("  node [style=filled, fillcolor=\"#6677cc\"];\n");
		{
			// sort out the key set
			List<String> bundleSorted = new ArrayList<String>(dependencies.keySet());
			Collections.sort(bundleSorted);
			for (String bundleName : bundleSorted) {
				if (!isDiagram(bundleName))
					out.write("\"" + bundleName + "\";\n");
			}
		}
		out.write("  node [style=filled, fillcolor=\"#cc7766\"];\n");
		{
			// sort out the key set
			List<String> bundleSorted = new ArrayList<String>(dependencies.keySet());
			Collections.sort(bundleSorted);
			for (String bundleName : bundleSorted) {
				if (isDiagram(bundleName))
					out.write("\"" + bundleName + "\";\n");
			}
		}
		
		// normal nodes
		out.write("  node [style=filled, fillcolor=\"#ffffff\"];\n");
		
		// sort out the key set
		List<String> bundleSorted = new ArrayList<String>(dependencies.keySet());
		Collections.sort(bundleSorted);
		for (String bundleName : bundleSorted) {
			for (String requires : dependencies.get(bundleName)) {
				// only print out a link if it exists locally
				if (dependencies.containsKey(requires)) {
					out.write("  \"" + bundleName + "\" -> \"" + requires + "\";\n");
				}
			}
		}
		out.write("}");
	}

	public void exportLocalDependenciesNoTests(Map<String, List<String>> dependencies, Writer out) throws IOException {
		out.write("digraph {\n");
		out.write("  size=\"32,32\";\n");
		out.write("  ratio=expand;\n");
		
		// print out all nodes that we are using
		out.write("  node [style=filled, fillcolor=\"#6677cc\"];\n");
		{
			// sort out the key set
			List<String> bundleSorted = new ArrayList<String>(dependencies.keySet());
			Collections.sort(bundleSorted);
			for (String bundleName : bundleSorted) {
				// skip tests
				if (isTest(bundleName)) continue;
				if (!isDiagram(bundleName))
					out.write("\"" + bundleName + "\";\n");
			}
		}
		out.write("  node [style=filled, fillcolor=\"#cc7766\"];\n");
		{
			// sort out the key set
			List<String> bundleSorted = new ArrayList<String>(dependencies.keySet());
			Collections.sort(bundleSorted);
			for (String bundleName : bundleSorted) {
				// skip tests
				if (isTest(bundleName)) continue;
				if (isDiagram(bundleName))
					out.write("\"" + bundleName + "\";\n");
			}
		}
		
		// normal nodes
		out.write("  node [style=filled, fillcolor=\"#ffffff\"];\n");
		
		// sort out the key set
		List<String> bundleSorted = new ArrayList<String>(dependencies.keySet());
		Collections.sort(bundleSorted);
		for (String bundleName : bundleSorted) {			
			// skip tests
			if (isTest(bundleName)) continue;
			
			for (String requires : dependencies.get(bundleName)) {
				// skip tests
				if (isTest(bundleName)) continue;

				// only print out a link if it exists locally
				if (dependencies.containsKey(requires)) {
					out.write("  \"" + bundleName + "\" -> \"" + requires + "\";\n");
				}
			}
		}
		out.write("}");
	}

	/**
	 * Is the given bundle ID a test?
	 * 
	 * @param bundleName
	 * @return
	 */
	private boolean isTest(String bundleName) {
		return bundleName.contains(".tests");
	}
	
	private boolean isDiagram(String bundleName) {
		return (bundleName.contains(".diagram.") || bundleName.endsWith(".diagram"))
			&& !bundleName.contains(".diagram.custom") && !bundleName.contains(".diagram.helpers")
			&& !bundleName.contains(".diagram.tests");
	}
	
	private boolean isEmf(String bundleName) {
		return bundleName.startsWith("org.eclipse.emf");
	}

	private boolean isGmf(String bundleName) {
		return bundleName.startsWith("org.eclipse.gmf");
	}

	private boolean isEclipse(String bundleName) {
		return bundleName.startsWith("org.eclipse.") && !isEmf(bundleName) && !isGmf(bundleName);
	}
	
	private boolean isOpeniaml(String bundleName) {
		return bundleName.startsWith("org.openiaml");
	}
	
}
