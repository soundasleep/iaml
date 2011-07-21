/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IncrementalProjectBuilder;
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
	 * This is a helper class that accumulates the results of a number of
	 * {@link LoadFileMetrics} helpers into a single set of accumulated metrics.
	 * For example, this can be used to identify the <em>total</em> number of
	 * HTML files across <em>all</em> loaded plugins. 
	 * 
	 * @author jmwright
	 *
	 */
	public static class FileMetricAccumulator {
		
		private final String prefix;
		
		/**
		 * 
		 * @param prefix a prefix to append to accumulated metrics; can be the empty string.
		 */
		public FileMetricAccumulator(String prefix) {
			this.prefix = prefix;
		}

		public FileMetricAccumulator() {
			this("");
		}
		
		/**
		 * The loaded map of total file counts.
		 */
		private Map<String,Long> totalCountMap = new HashMap<String,Long>();
		
		/**
		 * The loaded map of total file sizes.
		 */
		private Map<String,Long> totalSizeMap = new HashMap<String,Long>();
		
		
		/**
		 * Load all of the file metrics loaded via the given helper into the
		 * accumulator.
		 * 
		 * @param helper
		 */
		public void add(LoadFileMetrics helper) {
			if (helper.fileCountMap == null || helper.fileSizeMap == null) {
				throw new RuntimeException("LoadFileMetrics has not yet been evaluated on a plugin.");
			}
			
			for (String extension : helper.fileCountMap.keySet()) {
				increment(totalCountMap, extension, helper.fileCountMap.get(extension));
			}
			
			for (String extension : helper.fileSizeMap.keySet()) {
				increment(totalSizeMap, extension, helper.fileCountMap.get(extension));
			}
		}
		
		/**
		 * Publish the accumulated metrics into the given modeldoc instance.
		 */
		public void publish(ModeldocFactory factory, ModelDocumentation root) {
			// now save all of the metrics back
			// file count
			for (String extension : totalCountMap.keySet()) {
				Metric metric = factory.createMetric();
				// e.g. file.java.count.org.openiaml.model
				metric.setName("fileCount." + extension + prefix + ".total");
				metric.setValue(totalCountMap.get(extension).toString());
				root.getMetrics().add(metric);
			}
			
			// file size
			for (String extension : totalSizeMap.keySet()) {
				Metric metric = factory.createMetric();
				// e.g. file.java.size.org.openiaml.model
				metric.setName("fileSize." + extension + prefix + ".total");
				metric.setValue(totalSizeMap.get(extension).toString());
				root.getMetrics().add(metric);
			}
		}
		
	}
	
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
	 * The loaded map of file counts.
	 */
	private Map<String,Long> fileCountMap;
	
	/**
	 * The loaded map of file sizes.
	 */
	private Map<String,Long> fileSizeMap;

	/**
	 * Iterate over the given plugin and find out file statistics of all
	 * files.
	 * 
	 * @param factory
	 * @param root
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) {

		// map of (file extensions) to (count)
		fileCountMap = new HashMap<String,Long>();
		// map of (file extensions) to (size)
		fileSizeMap = new HashMap<String,Long>();
		
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
	private static void increment(Map<String,Long> map, String key, long value) {
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
	private static void increment(Map<String,Long> map, String key) {
		increment(map, key, 1);
	}
	
}
