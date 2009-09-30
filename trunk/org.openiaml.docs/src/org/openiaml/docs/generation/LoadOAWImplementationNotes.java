/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.List;

import org.openiaml.docs.generation.semantics.ITagHandler;
import org.openiaml.docs.generation.semantics.SemanticFinder;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.Reference;
import org.openiaml.docs.modeldoc.Template;
import org.openiaml.docs.modeldoc.TemplateFile;

/**
 * Load notes from OAW templates.
 * 
 * @author jmwright
 *
 */
public class LoadOAWImplementationNotes extends DocumentationHelper implements ILoader {

	private String plugin;
	
	private List<ITagHandler> semanticTagHandlers;

	private File file;

	private String startingPackage;

	public LoadOAWImplementationNotes(File file, String plugin, String startingPackage,
			List<ITagHandler> semanticTagHandlers) {
		super();
		this.file = file;
		this.plugin = plugin;
		this.startingPackage = startingPackage;
		this.semanticTagHandlers = semanticTagHandlers;
	}

	public List<ITagHandler> getSemanticTagHandlers() {
		return semanticTagHandlers;
	}

	public void load(ModeldocFactory factory, ModelDocumentation root) throws DocumentationGenerationException {
		try {
			iterateOverTemplates(factory, root, file, plugin, startingPackage);
		} catch (IOException e) {
			throw new DocumentationGenerationException(e);
		}
	}

	/**
	 * Load test case semantics in the given directory recursively.
	 * 
	 * @param factory
	 * @param root
	 * @throws IOException
	 */
	protected void iterateOverTemplates(final ModeldocFactory factory,
			final ModelDocumentation root, File folder, String plugin, String pkg) throws IOException {
		
		// for every java in this folder,
		String[] files = folder.list();
		for (String file : files) {
			File inFile = new File(folder.getAbsolutePath() + File.separator + file);
			if (inFile.isDirectory()) {
				// recurse over directories
				iterateOverTemplates(factory, root, inFile, plugin, pkg + "." + file);
			} else if (file.endsWith(".xpt")) {
				// iterate over this file
				String name = file.substring(0, file.lastIndexOf(".xpt")); // remove extension
				
				System.out.println("Parsing '" + inFile + "'...");
				
				loadInferenceSemantics(factory, root, plugin, pkg, name, inFile);
			}
		}
		
	}
	
	/**
	 * Parse the given Drools file for inference semantics, of
	 * the format
	 * <code># @semantics ...{@model Element} ...</code>
	 * 
	 * @param factory
	 * @param root
	 * @param plugin
	 * @param file
	 * @throws IOException 
	 */
	protected void loadInferenceSemantics(final ModeldocFactory factory,
			ModelDocumentation root, String plugin, String pkg,
			String name, File file) throws IOException {
		
		// create a TemplateFile for this template file
		TemplateFile templateFile = factory.createTemplateFile();
		templateFile.setPlugin(plugin);
		templateFile.setPackage(pkg);
		templateFile.setName(name);
		root.getReferences().add(templateFile);

		CharBuffer buf = CharBuffer.wrap(readFile(file));
		String[] lines = buf.toString().split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			
			String key = "# @implementation";
			if (line.startsWith(key)) {
				line = line.substring(key.length() + 1).trim();
				
				Template template = createTemplate(factory, i + 1, lines);
				if (template != null) {
					templateFile.getTemplates().add(template);

					// a root tag for @semantics
					JavadocTagElement e = factory.createJavadocTagElement();
					e.setJavaParent(template);
					e.setName("@implementation");
					
					// parse the line into javadoc elements
					// (the line needs to be parsed into fragments before we can find semantic references)
					new BasicJavadocParser().parseSemanticLineIntoFragments(line, factory, e);
					
					// identify semantic rules back
					handleModelReferences(e, template, root);
				}
			}
		}
		
	}

	/**
	 * Iterate over all semantic handlers in {@link LoadSemanticsFromTests#getSemanticTagHandlers()}
	 * and identify potential semantic tags.
	 * 
	 * @param e
	 * @param reference
	 * @param root 
	 */
	protected void handleModelReferences(JavadocTagElement e, Reference reference, ModelDocumentation root) {
		SemanticFinder finder = new SemanticFinder();
		for (ITagHandler sem : getSemanticTagHandlers()) {
			finder.findSemanticReferences(LoadOAWImplementationNotes.this, root, e, reference, sem);
		}
	}
	
	/**
	 * Parse down until we find a line starting with 
	 * <code>«DEFINE name[(args)] FOR [element][-]»</code>.
	 *  
	 * @return a newly created template, or null if none could be found
	 */
	private Template createTemplate(ModeldocFactory factory, int i, String[] lines) {
		for (int j = i; j < lines.length; j++) {
			if (lines[i].trim().startsWith("«DEFINE ")) {
				// found a template
				
				// find the template name
				String templateName = lines[i].trim();
				// jump over to first whitespace
				if (!templateName.contains(" "))
					throw new RuntimeException("Template does not contain ' ': '" + templateName + "'");
				templateName = templateName.substring(templateName.indexOf(' ')).trim();
				// jump until ' FOR '
				if (!templateName.contains(" FOR "))
					throw new RuntimeException("Template does not contain ' FOR ': '" + templateName + "'");
				templateName = templateName.substring(0, templateName.indexOf(" FOR ")).trim();
				
				// find the template type
				String templateType = lines[i].trim();
				if (!templateType.contains(" FOR "))
					throw new RuntimeException("Template does not contain ' FOR ': '" + templateType + "'");
				templateType = templateType.substring(templateType.indexOf(" FOR ")).trim();
				
				// until the last »
				if (!templateType.contains("»"))
					throw new RuntimeException("Template does not contain '»': '" + templateType + "'");
				templateType = templateType.substring(0, templateType.indexOf("»")).trim();
				
				// remove '-'
				if (templateType.endsWith("-"))
					templateType = templateType.substring(0, templateType.length() - 1);
				
				Template template = factory.createTemplate();
				template.setName(templateName);
				template.setType(templateType);
				template.setLine(j);
				return template;
			}
		}
		
		return null;
	}
	
}
