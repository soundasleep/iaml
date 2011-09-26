/**
 * 
 */
package org.openiaml.model;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.model.EXSDDataType;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelFactory;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.VisualFactory;

/**
 * Static method to create a new model instance. Essentially takes the code
 * out of dynamic templates (<code>templates/aspects/xpt/editor/DiagramEditorUtil.xpt</code>)
 * and into a Java class.
 * 
 * @author jmwright
 *
 */
public class InitialModel {
	
	/**
	 * Represents an exception that occured while trying to initialise a new model
	 * instance.
	 * 
	 * @author jmwright
	 *
	 */
	public static class ModelInitializationException extends Exception {

		private static final long serialVersionUID = 1L;

		public ModelInitializationException(String message) {
			super(message);
		}

		public ModelInitializationException(String message, ModelLoadException e) {
			super(message, e);
		}
		
	}
	
	private static XSDSchema xsdSchemaInstance = null;
	
	/**
	 * Initialise the EMF data types from
	 * <code>/org.eclipse.xsd/cache/www.w3.org/2001/XMLSchema.xsd</code>.
	 */
	private static void initialiseEMFXSDDataTypes() throws ModelInitializationException {
		if (xsdSchemaInstance == null) {
			URI uri = URI.createPlatformPluginURI("/org.eclipse.xsd/cache/www.w3.org/2001/XMLSchema.xsd", false);
			EObject model;
			try {
				model = ModelLoader.load(uri);
			} catch (ModelLoadException e) {
				throw new ModelInitializationException("Could not load " + uri + ": " + e.getMessage(), e);
			}
			if (!(model instanceof XSDSchema)) {
				throw new ModelInitializationException("Could not load " + uri + ": loaded model was not an XSDSchema");
			}
			xsdSchemaInstance = (XSDSchema) model;
		}
	}
	
	/**
	 * To reduce the clutter in initial diagrams, only a limited set of
	 * XSD data types are provided by default. Note that this does not
	 * restrict a model instance from referencing other XSD data types
	 * in the XSD data type definition. 
	 */
	private static final List<String> INCLUDED_DATA_TYPES = Arrays.asList(
			"integer",
			"string",
			"boolean"
			);
	
	private static void addEMFXSDDataTypes(InternetApplication app) throws ModelInitializationException {
		initialiseEMFXSDDataTypes();
		
		for (XSDTypeDefinition type : xsdSchemaInstance.getTypeDefinitions()) {
			if (type instanceof XSDSimpleTypeDefinition) {
				XSDSimpleTypeDefinition xs = (XSDSimpleTypeDefinition) type;
				
				// for now, we will only initialise with a few builtin types
				if (INCLUDED_DATA_TYPES.contains(xs.getName())) {
					EXSDDataType dt = ModelFactory.eINSTANCE.createEXSDDataType();
					dt.setId("xsd_" + xs.getName());
					dt.setDefinition(xs);
					dt.setName("xsd:" + xs.getName());
					app.getXsdDataTypes().add(dt);
				}
			}
		}
	}

	private static XSDSchema builtinXsdTypes = null;
	
	/**
	 * Initialise the builtin data types from
	 * <code>/org.openiaml.model/model/datatypes.xsd</code>.
	 */
	private static void initialiseBuiltinXSDDataTypes() throws ModelInitializationException {
		if (builtinXsdTypes == null) {
			URI uri = URI.createPlatformPluginURI("/org.openiaml.model/model/datatypes.xsd", false);
			EObject model;
			try {
				model = ModelLoader.load(uri);
			} catch (ModelLoadException e) {
				throw new ModelInitializationException("Could not load " + uri + ": " + e.getMessage(), e);
			}
			if (!(model instanceof XSDSchema)) {
				throw new ModelInitializationException("Could not load " + uri + ": loaded model was not an XSDSchema");
			}
			builtinXsdTypes = (XSDSchema) model;
		}
	}
	
	private static void addBuiltinXSDDataTypes(InternetApplication app) throws ModelInitializationException {
		initialiseBuiltinXSDDataTypes();
		
		for (XSDTypeDefinition type : builtinXsdTypes.getTypeDefinitions()) {
			if (type instanceof XSDSimpleTypeDefinition) {
				XSDSimpleTypeDefinition xs = (XSDSimpleTypeDefinition) type;
				
				EXSDDataType dt = ModelFactory.eINSTANCE.createEXSDDataType();
				dt.setId("builtin_" + xs.getName());
				dt.setDefinition(xs);
				dt.setName("builtin:" + xs.getName());
				app.getXsdDataTypes().add(dt);
			}
		}
	}

	/**
	 * 
	 * @param app the initially created root model element; this element is
	 * 		empty.
	 */
	public static void createInitialModel(InternetApplication app) {
		
		try {
		
			Frame frame = VisualFactory.eINSTANCE.createFrame();
			frame.setName("Home");
			frame.setId("index");	// default page
			app.getScopes().add(frame);
			
			// load EMF XSD data types
			addEMFXSDDataTypes(app);
			
			// load custom XSD data types
			addBuiltinXSDDataTypes(app);
		
		} catch (ModelInitializationException e) {
			IamlModelPlugin.getInstance().logError("Could not initialise model: " + e.getMessage(), e);
		}
		
	}
	
}
