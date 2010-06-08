/**
 * 
 */
package org.openiaml.docs.generation;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.openiaml.docs.generation.semantics.ITagHandler;
import org.openiaml.docs.modeldoc.EMFAttribute;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * @author jmwright
 *
 */
public class LoadEMFClasses extends EMFLoaderHelper implements ILoader {
	
	/**
	 * The root EMF package to load from.
	 */
	private EPackage rootPackage;
	
	/**
	 * The plugin the generated package is stored in, e.g. "org.openiaml.model".
	 */
	private String plugin;
	
	/**
	 * The base of the plugin, e.g. "org.openiaml.model.".
	 */
	private String packageBase;

	private DocumentationGenerator generator;
	
	/**
	 * @param rootPackage
	 * @param plugin
	 * @param packageBase
	 */
	public LoadEMFClasses(EPackage rootPackage, String plugin,
			String packageBase, DocumentationGenerator generator) {
		super();
		this.rootPackage = rootPackage;
		this.plugin = plugin;
		this.packageBase = packageBase;
		this.generator = generator;
	}

	@Override
	public List<ITagHandler> getSemanticTagHandlers() {
		return generator.getSemanticTagHandlers();
	}
	
	/* (non-Javadoc)
	 * @see org.openiaml.docs.generation.IDocGenerator#load(org.openiaml.docs.modeldoc.ModeldocFactory, org.openiaml.docs.modeldoc.ModelDocumentation)
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) {
		loadEMFClasses(rootPackage, factory, root);
	}
	
	/**
	 * Load all EMF classes in the given package into the given documentation.
	 * 
	 * @param root
	 */
	public void loadEMFClasses(EPackage pkg, ModeldocFactory factory, ModelDocumentation root) {
		for (EClassifier classifier : pkg.getEClassifiers()) {
			if (classifier instanceof EClass) {
				
				EClass cls = (EClass) classifier;
				
				EMFClass c = factory.createEMFClass();
				c.setTargetClass(cls);
				c.setName(cls.getName());
				c.setAbstract(cls.isAbstract());
				c.setInterface(cls.isInterface());

				// add tagline
				JavadocTagElement e = getTaglineForEMFElement(factory, cls);
				if (e != null) {
					c.setTagline(e);
				}
				
				c.setParent(root);
				
				// link up the source java class
				getJavaClassFor(c, cls, factory, root);
				
				// parse attributes
				parseAttributes(factory, cls, c);
			}
		}
	
		// load all subpackages
		for (EPackage sub : pkg.getESubpackages()) {
			loadEMFClasses(sub, factory, root);
		}
	}
	

	/**
	 * Add all (local) attributes in the source class to the target EMFClass.
	 */
	private void parseAttributes(ModeldocFactory factory, EClass source, EMFClass target) {
		for (EAttribute a : source.getEAttributes()) {
			EMFAttribute created = factory.createEMFAttribute();
			created.setName(a.getName());
			created.setId(a.isID());
			created.setLowerBound(a.getLowerBound());
			created.setUpperBound(a.getUpperBound());
			created.setType(a.getEAttributeType().getName());
			created.setDefaultLiteral(a.getDefaultValueLiteral());
			
			// add tagline
			JavadocTagElement e = getTaglineForEMFElement(factory, a);
			if (e != null) {
				created.setTagline(e);
			}
			
			target.getAttributes().add(created);
		}
		
	}

	/**
	 * Link up a Java reference for the given EMFClass.
	 * 
	 * @param c
	 * @param cls
	 * @param factory
	 * @param root
	 */
	protected void getJavaClassFor(EMFClass c, EClass cls,
			ModeldocFactory factory, ModelDocumentation root) {
		
		JavaClass jc = factory.createJavaClass();
		jc.setPlugin(plugin);				
		jc.setPackage(packageBase + getJavaPackageFor(cls.getEPackage()));
		jc.setName(cls.getName());
		jc.setParent(root);
		
		c.setRuntimeClass(jc);
		
	}

	private String getJavaPackageFor(EPackage c) {
		if (c == null)
			return "";
		String parent = getJavaPackageFor(c.getESuperPackage());
		
		return parent + (parent.isEmpty() ? "" : ".") + c.getName();
	}
	
}
