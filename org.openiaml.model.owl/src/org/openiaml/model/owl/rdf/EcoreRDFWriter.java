/**
 * 
 */
package org.openiaml.model.owl.rdf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * <p>A class to take an arbitrary EMF instance and write it out
 * as RDF, using only the information provided by EMF.</p>
 * 
 * <p>Compare this to an ATL information, which requires inputs of
 * both the instance and the instance's EMF model, and lots of 
 * hack that don't correspond to the ATL philosophy.</p>
 * 
 * @author jmwright
 *
 */
public class EcoreRDFWriter {

	Map<EObject,Resource> resourceMap = new HashMap<EObject,Resource>();
	Map<EStructuralFeature,Property> featureMap = new HashMap<EStructuralFeature,Property>();
	
	public Model transform(EObject source) {
		Model model = ModelFactory.createDefaultModel();
		
		// default namespaces
		model.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		model.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		model.setNsPrefix("owl", "http://www.w3.org/2002/07/owl#");
		model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");

		// handle root
		handleEObject(model, source);
		
		// create the model recursively for all contents
		Iterator<EObject> contents = source.eAllContents();
		while (contents.hasNext()) {
			EObject obj = contents.next();
			handleEObject(model, obj);
		}
		
		return model;
	}
	
	private Property rdfType;
	
	/**
	 * Get the Property for rdf:type.
	 * 
	 * @param model
	 * @return
	 */
	protected Property getRdfTypeProperty(Model model) {
		if (rdfType == null) {
			rdfType = model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
		}
		return rdfType;
	}
	
	/**
	 * A statically-instantiated map of EMF types to XML schema types.
	 * From l3l.sido.emf4sw.transformations/ecore2owl.atl
	 */
	protected Map<String,String> typeMap = new HashMap<String,String>();		
	{
		typeMap.put("String", "http://www.w3.org/2001/XMLSchema#string");
		typeMap.put("Integer", "http://www.w3.org/2001/XMLSchema#int");
		typeMap.put("Boolean", "http://www.w3.org/2001/XMLSchema#boolean");
		typeMap.put("UnlimitedNatural", "http://www.w3.org/2001/XMLSchema#integer");
		typeMap.put("Byte", "http://www.w3.org/2001/XMLSchema#byte");
		typeMap.put("Currency", "http://www.w3.org/2001/XMLSchema#decimal");
		typeMap.put("Date", "http://www.w3.org/2001/XMLSchema#date");
		typeMap.put("Double", "http://www.w3.org/2001/XMLSchema#double");
		typeMap.put("Long", "http://www.w3.org/2001/XMLSchema#long");
		typeMap.put("Single", "http://www.w3.org/2001/XMLSchema#short");
		typeMap.put("Variant", "http://www.w3.org/2001/XMLSchema#string");
		typeMap.put("EString", "http://www.w3.org/2001/XMLSchema#string");
		typeMap.put("EInteger", "http://www.w3.org/2001/XMLSchema#int");
		typeMap.put("EInt", "http://www.w3.org/2001/XMLSchema#int");
	}
	
	protected void handleEObject(Model model, EObject obj) {
		Resource res = getResourceFor(model, obj);
		
		// give it an rdf:type
		res.addProperty(getRdfTypeProperty(model), model.createResource(obj.eClass().getEPackage().getNsURI() + "#" + obj.eClass().getName()));

		// all references (containment, references)
		for (EReference feature : obj.eClass().getEAllReferences()) {
			if (feature.isMany()) {
				for (EObject target : ((List<EObject>) obj.eGet(feature))) {
					Property property = getPredicateFor(model, feature);
					Resource targetRes = getResourceFor(model, target);
					
					res.addProperty(property, targetRes);
				}
			} else {
				if (obj.eGet(feature) != null) {
					Property property = getPredicateFor(model, feature);
					Resource targetRes = getResourceFor(model, (EObject) obj.eGet(feature));
					
					res.addProperty(property, targetRes);
				}
			}
		}
		
		// all attributes
		for (EAttribute attr : obj.eClass().getEAllAttributes()) {
			if (obj.eGet(attr) != null) {
				Property property = getPredicateFor(model, attr);
				
				String uri = typeMap.get(attr.getEAttributeType().getName());
				RDFDatatype type = TypeMapper.getInstance().getTypeByName(uri);
				String value = obj.eGet(attr).toString();
				res.addProperty(property, value, type);
			}
		}
	}
	
	/**
	 * Get an ID for the object. If the object has a property with eID = true,
	 * use this ID instead of generating a UUID.
	 * 
	 * @param obj
	 */
	protected String getID(EObject obj) {
		for (EAttribute attr : obj.eClass().getEAllAttributes()) {
			if (attr.isID() && obj.eGet(attr) != null) {
				return obj.eGet(attr).toString();
			}
		}
		
		return EcoreUtil.generateUUID();
	}
	
	protected Resource getResourceFor(Model model, EObject obj) {
		if (!resourceMap.containsKey(obj)) {
			// set ns prefix
			EPackage pkg = obj.eClass().getEPackage();
			model.setNsPrefix(pkg.getNsPrefix(), pkg.getNsURI() + "#");
			
			String uri = pkg.getNsURI() + "#" + getID(obj); 
			Resource res = model.createResource(uri);
			resourceMap.put(obj, res);
		}
		return resourceMap.get(obj);
	}
	
	public Property getPredicateFor(Model model, EStructuralFeature feature) {
		if (!featureMap.containsKey(feature)) {
			// set ns prefix
			EPackage pkg = feature.getEContainingClass().getEPackage();
			model.setNsPrefix(pkg.getNsPrefix(), pkg.getNsURI() + "#");

			String uri = pkg.getNsURI() + "#" + feature.getName(); 
			Property prop = model.createProperty(uri);
			featureMap.put(feature, prop);
		}
		return featureMap.get(feature);
	}

}
