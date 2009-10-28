/**
 * 
 */
package org.openiaml.verification.crocopat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.sosy_lab.crocopat.cli.ExecuteCrocopat;
import org.sosy_lab.crocopat.cli.ExecuteCrocopat.CrocopatException;

/**
 * An abstract engine to verify with Crocopat.
 * This way we can specify different types of verification inputs
 * with different validation rules, etc; similar to Drools.
 * 
 * @author jmwright
 *
 */
public class VerificationEngine {
	
	private List<VerificationViolation> violations;
	
	/**
	 * Get all of the violations found from the last execution of
	 * {@link #verify(EObject)}.
	 * 
	 * @return
	 */
	public List<VerificationViolation> getViolations() {
		return violations;
	}
	
	/**
	 * Verify a given model. Violations can then be obtained by
	 * {@link #getViolations()}.
	 * 
	 * @param model
	 * @return the status of the verification (error if any other type of exception was thrown)
	 * @throws VerificationException if verification could not be executed
	 */
	public IStatus verify(EObject model, IProgressMonitor monitor) throws VerificationException {
		
		try {
			monitor.beginTask("Verifying with Crocopat", 10);
			
			// we want to write the model and rules to files
			File rsfFile = File.createTempFile("temp", ".rsf");
			FileWriter rsf = new FileWriter(rsfFile);
			File rmlFile = File.createTempFile("temp", ".rml");
			FileWriter rml = new FileWriter(rmlFile);
			
			if (!deleteTemporaryFiles()) {
				// print out the locations of the files
				System.out.println(rsfFile);
				System.out.println(rmlFile);
			}
			
			// construct the instance map
			monitor.subTask("Constructing instance map");
			TwoWayMap<String, EObject> instanceMap = 
				constructIDMap(model);
			monitor.worked(1);
			
			// export types to rsf
			monitor.subTask("Exporting meta-model to RSF");
			exportTypes(model.eClass().getEPackage(), rsf);
			
			// export references
			exportReferences(model.eClass().getEPackage(), rsf);
			
			// export the rest of the universe to rsf
			exportUniverses(rsf);
			monitor.worked(1);

			// export model instance to rsf
			monitor.subTask("Exporting model instance to RSF");
			exportInstance(model, instanceMap, rsf);
			Iterator<EObject> it = model.eAllContents();
			while (it.hasNext()) {
				exportInstance(it.next(), instanceMap, rsf);
			}
			monitor.worked(1);
						
			// export inheritance to rml
			monitor.subTask("Exporting RML");
			exportInheritance(model.eClass().getEPackage(), rml);
			
			// export all the rules to rml
			exportRules(rml);
			monitor.worked(1);
			
			// save the files
			rsf.close();
			rml.close();
			
			// get the executor
			ExecuteCrocopat exec = new ExecuteCrocopat();
			InputStream rsfin = new FileInputStream(rsfFile);
			InputStream rmlin = new FileInputStream(rmlFile);
	
			// execute
			monitor.subTask("Calling Crocopat");
			List<String> results;
			try {
				results = exec.execute(rmlin, rsfin);
			} catch (CrocopatException e) {
				throw new VerificationException("Could not execute Crocopat: " + e.getMessage(), e);
			}
			monitor.worked(5);
			
			// parse for violations
			monitor.subTask("Parsing output");
			violations = parseViolations(model, results, instanceMap);
			monitor.worked(1);
			
			// delete temporary files
			if (deleteTemporaryFiles()) {
				rsfFile.delete();
				rmlFile.delete();
			}
			
			// return
			monitor.done();
			return Status.OK_STATUS;
			
		} catch (IOException e) {
			return errorStatus(e);
		}
	}
	
	/**
	 * Should the generated .rml and .rsf files be deleted?
	 * 
	 * @return
	 */
	public boolean deleteTemporaryFiles() {
		return false;
	}

	public IStatus errorStatus(String message, Throwable e) {
		return new Status(IStatus.ERROR, VerificationCrocopatPlugin.PLUGIN_ID, message, e);
	}
	
	public IStatus errorStatus(Throwable e) {
		return new Status(IStatus.ERROR, VerificationCrocopatPlugin.PLUGIN_ID, e.getMessage(), e);
	}

	/**
	 * From a list of output from Crocopat, generate the list of violations.
	 * 
	 * @param model 
	 * @param results
	 * @param instanceMap 
	 * @return
	 * @throws VerificationException if parsing failed
	 */
	protected List<VerificationViolation> parseViolations(EObject model, List<String> results, TwoWayMap<String, EObject> instanceMap) throws VerificationException {
		List<VerificationViolation> violations = new ArrayList<VerificationViolation>();
		for (String s : results) {
			// expected format: [message] tab [id1] tab [...] tab [idn] (tab)
			
			String[] bits = s.trim().split("\t");
			// needs to be at least two bits to add a violation
			if (bits.length > 1) {
				// a new violation
				List<EObject> objects = new ArrayList<EObject>();
				
				// where is this object in the model?
				for (int i = 1; i < bits.length; i++) {
					String key = bits[i].trim();
					// remove quotes
					if (key.startsWith("\""))
						key = key.substring(1);
					if (key.endsWith("\""))
						key = key.substring(0, key.length() - 1);
					
					if (instanceMap.containsKey(key)) {
						objects.add(instanceMap.get(key));
					}
				}
				
				// construct the new violation
				VerificationViolation v = new VerificationViolation(objects, bits[0]);
				violations.add(v);
			}
		}
		
		// return
		return violations;
	}
	
	/**
	 * Represents a two-way hash map, so that operations going
	 * either way are fast.
	 * 
	 * @author jmwright
	 *
	 * @param <A>
	 * @param <B>
	 */
	public class TwoWayMap<A, B> {
		
		private Map<A, B> aToB = new HashMap<A, B>();
		private Map<B, A> bToA = new HashMap<B, A>();
		
		public void put(A key, B value) {
			aToB.put(key, value);
			bToA.put(value, key);
		}
		
		public A getKey(B value) {
			return bToA.get(value);
		}
		
		public B get(A key) {
			return aToB.get(key);
		}

		public int size() {
			return aToB.size();
		}

		public boolean containsKey(A key) {
			return aToB.containsKey(key);
		}

		public boolean containsValue(B value) {
			return bToA.containsKey(value);
		}
		
	}
	
	/**
	 * Take the given model and turn it into a map of unique EObjects. 
	 * 
	 * @param model
	 * @return
	 */
	private TwoWayMap<String, EObject> constructIDMap(EObject model) {
		
		TwoWayMap<String, EObject> map = new TwoWayMap<String, EObject>();
		
		map.put(getMapID(map, model), model);
		Iterator<EObject> it = model.eAllContents();
		while (it.hasNext()) {
			EObject obj = it.next();
			map.put(getMapID(map, obj), obj);
		}
		
		return map;
		
	}
	
	private String getMapID(TwoWayMap<String, EObject> map, EObject obj) {
		return "_unique_" + map.size(); 
	}
	
	/**
	 * Export all of the rules necessary.
	 * 
	 * @param rml
	 * @throws IOException 
	 */
	protected void exportRules(FileWriter rml) throws IOException {
		for (String file : getRuleFiles()) {
			// open rule file from bundle
			URL url;
			try {
				url = VerificationCrocopatPlugin.getInstance().getResolvedFile(file);
			} catch (NullPointerException e) {
				// don't throw it if it was from getInstance()
				if (VerificationCrocopatPlugin.getInstance() == null)
					throw e;
				
				throw new RuntimeException("Unexpectedly got a NullPointerException while finding bundle file '" + file + "': perhaps the file does not exist?: " + e.getMessage(), e);
			}
			
			// open input stream
			InputStream is = url.openStream();
			int c;
			while ((c = is.read()) != -1) {
				rml.write(c);
			}
			is.close();
			
		}
	}
	
	/**
	 * Get all of the rule files in the current bundle.
	 * 
	 * @return
	 */
	public List<String> getRuleFiles() {
		List<String> result = new ArrayList<String>();
		
		result.add("rules/infinite_redirection.rml");
		
		return result;
	}

	
	/**
	 * Export all of the universes necessary.
	 * 
	 * @param rml
	 * @throws IOException 
	 */
	protected void exportUniverses(FileWriter rml) throws IOException {
		for (String file : getUniverseFiles()) {
			// open rule file from bundle
			URL url;
			try {
				url = VerificationCrocopatPlugin.getInstance().getResolvedFile(file);
			} catch (NullPointerException e) {
				// don't throw it if it was from getInstance()
				if (VerificationCrocopatPlugin.getInstance() == null)
					throw e;
				
				throw new RuntimeException("Unexpectedly got a NullPointerException while finding bundle file '" + file + "': perhaps the file does not exist?: " + e.getMessage(), e);
			}
			
			// open input stream
			InputStream is = url.openStream();
			int c;
			while ((c = is.read()) != -1) {
				rml.write(c);
			}
			is.close();
			
		}
	}
	
	/**
	 * Get all of the rule files in the current bundle.
	 * 
	 * @return
	 */
	public List<String> getUniverseFiles() {
		List<String> result = new ArrayList<String>();
		
		result.add("rules/infinite_redirection.rsf");
		
		return result;
	}

	/**
	 * Export the model instance. Is not recursive.
	 * 
	 * @param instanceMap 
	 * @throws IOException 
	 * 
	 */
	private void exportInstance(EObject model, TwoWayMap<String, EObject> instanceMap, Writer rsf) throws IOException {
		// define that it exists
		rsf.write("\n");
		rsf.write("instance_");
		rsf.write(model.eClass().getName());
		rsf.write(" \"");
		rsf.write(getID(model, instanceMap));
		rsf.write("\"\n");;
		
		// iterate over all references
		for (EReference ref : model.eClass().getEAllReferences()) {
			Object result = model.eGet(ref);
			if (!ref.isMany()) {
				result = Collections.singletonList(result);
			}
			// for every result
			List<?> resultList = (List<?>) result;
			for (Object obj : resultList) {
				if (obj != null) {
					rsf.append(getReferenceName(ref));
					rsf.write(" \"");
					rsf.write(getID(model, instanceMap));
					rsf.write("\" \"");
					rsf.write(getID(obj, instanceMap));
					rsf.write("\"\n");
				}
			}
		}
		
		// iterate over all attributes
		for (EAttribute attr : model.eClass().getEAllAttributes()) {
			Object result = model.eGet(attr);
			if (!attr.isMany()) {
				result = Collections.singletonList(result);
			}

			// for every result
			List<?> resultList = (List<?>) result;
			for (Object obj : resultList) {
				if (obj != null) {
					rsf.append(getAttributeName(attr));
					rsf.write(" \"");
					rsf.write(getID(model, instanceMap));
					rsf.write("\" \"");
					rsf.write(getID(obj, instanceMap));
					rsf.write("\"\n");
				}
			}
		}
	}

	/**
	 * @param ref
	 * @return
	 */
	private String getReferenceName(EReference ref) {
		return ref.getName();
	}

	/**
	 * @param ref
	 * @return
	 */
	private String getAttributeName(EAttribute ref) {
		return ref.getName();
	}

	/**
	 * @param model
	 * @return
	 */
	private String getID(Object model, TwoWayMap<String,EObject> instanceMap) {
		if (model instanceof EObject) {
			// is it in the instance map?
			if (instanceMap.containsValue((EObject) model)) {
				return instanceMap.getKey((EObject) model); 
			}
		}
		
		if (model instanceof String) {
			return model.toString();
		} else if (model instanceof Number) {
			return model.toString();
		} else if (model instanceof Boolean) {
			return model.toString();
		} else {
			return new StringBuffer().append("_o").append(model.hashCode()).toString();
		}
	}

	/**
	 * Construct a map of all classes to subclasses.
	 * @param map
	 * @param pkg
	 */
	private void constructInheritanceMap(Map<EClass,Set<EClass>> map, EPackage ePackage) {
		for (EClassifier clsfr : ePackage.getEClassifiers()) {
			if (clsfr instanceof EClass) {
				EClass cls = (EClass) clsfr;

				// add this one directly
				if (!map.containsKey(cls)) {
					map.put(cls, new HashSet<EClass>());
				}

				// does this class have any direct supertypes?
				for (EClass sup : cls.getESuperTypes()) {
					if (!map.containsKey(sup)) {
						map.put(sup, new HashSet<EClass>());
					}
					// add this as a subtype
					map.get(sup).add(cls);
				}
			}
		}
		
		for (EPackage pkg : ePackage.getESubpackages()) {
			constructInheritanceMap(map, pkg);
		}
	}
	
	/**
	 * Export the given class. If the given classes supertypes
	 * have not been exported yet, export them first, to prevent
	 * warnings in Crocopat.
	 * 
	 * @param cls
	 * @param map
	 * @throws IOException 
	 */
	private void exportInheritance(Set<EClass> exported, EClass cls, Map<EClass,Set<EClass>> map, Writer rml) throws IOException {
		// check all subtypes first, to make sure they are exported
		for (EClass sub : map.get(cls)) {
			if (!exported.contains(sub)) {
				// export this subtype first
				exportInheritance(exported, sub, map, rml);
			}
		}
		
		// we have exported this class
		exported.add(cls);
		
		rml.write(cls.getName());
		rml.write("(x) := x != \"null\" & (");
	
		// add current instances
		rml.write("instance_");
		rml.write(cls.getName());
		rml.write("(x)");
		
		// print out a list of its direct subtypes
		for (EClass sub : map.get(cls)) {
			rml.write(" | ");
			rml.write(sub.getName());
			rml.write("(x)");
		}
		
		rml.write(");\n");
	}
	
	/**
	 * @param ePackage
	 * @param rml
	 * @throws IOException 
	 */
	private void exportInheritance(EPackage ePackage, Writer rml) throws IOException {
		// first, construct the map
		Map<EClass,Set<EClass>> map = new HashMap<EClass,Set<EClass>>();
		constructInheritanceMap(map, ePackage);
		
		// now for every supertype
		Set<EClass> exported = new HashSet<EClass>();
		for (EClass cls : map.keySet()) {
			exportInheritance(exported, cls, map, rml);
		}
	}

	/**
	 * @param ePackage
	 * @param rsf
	 * @throws IOException 
	 */
	private void exportTypes(EPackage ePackage, Writer rsf) throws IOException {
		for (EClassifier clsfr : ePackage.getEClassifiers()) {
			if (clsfr instanceof EClass) {
				EClass cls = (EClass) clsfr;
				
				rsf.write("instance_");
				rsf.write(cls.getName());
				rsf.write(" null\n");
			}
		}
		
		for (EPackage pkg : ePackage.getESubpackages()) {
			exportTypes(pkg, rsf);
		}
	}
	
	/**
	 * Export null references for all references and attributes. This prevents
	 * Crocopat from throwing 'Warning: Undefined variable ...'
	 * for references that do not exist in a model.
	 * 
	 * @throws IOException 
	 */
	private void exportReferences(EPackage ePackage, Writer rsf) throws IOException {
		exportReferences(new HashSet<String>(), ePackage, rsf);
	}
	
	/**
	 * @param references references and attributes already written
	 * @throws IOException 
	 */
	private void exportReferences(Set<String> references, EPackage ePackage, Writer rsf) throws IOException {
		for (EClassifier clsfr : ePackage.getEClassifiers()) {
			if (clsfr instanceof EClass) {
				EClass cls = (EClass) clsfr;
				
				for (EReference ref : cls.getEAllReferences()) {
					if (!references.contains(ref.getName())) {
						references.add(ref.getName());
						rsf.write(getReferenceName(ref));
						rsf.write(" \"null\" \"null\"\n");
					}
				}
				
				for (EAttribute attr : cls.getEAllAttributes()) {
					if (!references.contains(attr.getName())) {
						references.add(attr.getName());
						rsf.write(getAttributeName(attr));
						rsf.write(" \"null\" \"null\"\n");
					}
				}
				
			}
		}
		
		// recurse
		for (EPackage pkg : ePackage.getESubpackages()) {
			exportReferences(references, pkg, rsf);
		}
	}
	
}
