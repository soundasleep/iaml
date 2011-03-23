/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openiaml.docs.modeldoc.Metric;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * Calculates file-based metrics.
 * 
 * @author jmwright
 *
 */
public class LoadFileMetrics extends DocumentationHelper implements ILoader {

	/**
	 * The reference to the plugin directory, e.g. "../org.openiaml.model".
	 */
	private File root;
	
	/**
	 * The plugin the generated package is stored in, e.g. "org.openiaml.model".
	 */
	private String plugin;

	public LoadFileMetrics(File root, String plugin) {
		super();
		
		this.root = root;
		this.plugin = plugin;
	}

	/**
	 * Iterate over the given plugin and find out file statistics of all
	 * files.
	 * 
	 * @param factory
	 * @param root
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) {

		// map of (file extensions) to (count)
		Map<String,Long> fileCountMap = new HashMap<String,Long>();
		// map of (file extensions) to (size)
		Map<String,Long> fileSizeMap = new HashMap<String,Long>();
		
		// start recurse
		load(this.root, fileCountMap, fileSizeMap);
		
		// now save all of the metrics back
		// file count
		for (String extension : fileCountMap.keySet()) {
			Metric metric = factory.createMetric();
			// e.g. file.java.count.org.openiaml.model
			metric.setName("fileCount." + extension + "." + plugin);
			metric.setValue(fileCountMap.get(extension).toString());
			root.getMetrics().add(metric);
		}
		
		// file size
		for (String extension : fileSizeMap.keySet()) {
			Metric metric = factory.createMetric();
			// e.g. file.java.size.org.openiaml.model
			metric.setName("fileSize." + extension + "." + plugin);
			metric.setValue(fileSizeMap.get(extension).toString());
			root.getMetrics().add(metric);
		}
		
	}

	/**
	 * Recursively go through the given directory and load file statistics.
	 * 
	 * @param dir
	 * @param fileCountMap
	 * @param fileSizeMap
	 */
	private void load(File dir, Map<String, Long> fileCountMap,
			Map<String, Long> fileSizeMap) {
		
		// find all files
		for (File f : dir.listFiles()) {
			// ignore hidden files (e.g. .svn directories)
			if (f.isHidden())
				continue;
			
			if (f.isDirectory()) {
				// increment count of directories
				increment(fileCountMap, "directories");
				
				// recurse
				load(f, fileCountMap, fileSizeMap);
			} else {
				// get file extension
				String extension = getFileExtension(f);
				
				increment(fileCountMap, extension);
				increment(fileSizeMap, extension, f.length());
			}
		}
		
	}
	
	/**
	 * Get the file extension of the given file.
	 */
	public static String getFileExtension(File f) {
		int index = f.getName().lastIndexOf('.');
		if (index == -1 || index == f.getName().length() - 1) {
			// no file extension
			return "none";
		} else {
			return f.getName().substring(f.getName().lastIndexOf('.') + 1).toLowerCase();
		}
	}

	/**
	 * Increment the given map at the given key value with the given value.
	 * If the key does not exist, creates it.
	 */
	private void increment(Map<String,Long> map, String key, long value) {
		if (map.containsKey(key)) {
			map.put(key, map.get(key) + value);
		} else {
			map.put(key, value);
		}
	}
	/**
	 * Increment the given map at the given key value by 1.
	 * If the key does not exist, creates it.
	 */
	private void increment(Map<String,Long> map, String key) {
		increment(map, key, 1);
	}
	
}
