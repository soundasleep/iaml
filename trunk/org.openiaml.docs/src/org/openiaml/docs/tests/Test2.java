/**
 * 
 */
package org.openiaml.docs.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MemberRef;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.openarchitectureware.expression.ast.DeclaredParameter;
import org.openarchitectureware.expression.ast.Identifier;
import org.openarchitectureware.xtend.ast.Check;
import org.openarchitectureware.xtend.ast.Extension;
import org.openarchitectureware.xtend.ast.ExtensionFile;
import org.openarchitectureware.xtend.parser.ParseFacade;
import org.openiaml.docs.modeldoc.Constraint;
import org.openiaml.docs.modeldoc.ConstraintType;
import org.openiaml.docs.modeldoc.DroolsPackage;
import org.openiaml.docs.modeldoc.DroolsRule;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.FileLineReference;
import org.openiaml.docs.modeldoc.FileReference;
import org.openiaml.docs.modeldoc.GraphicalRepresentation;
import org.openiaml.docs.modeldoc.InferenceSemantic;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.JavaMethod;
import org.openiaml.docs.modeldoc.JavadocClassReference;
import org.openiaml.docs.modeldoc.JavadocFragment;
import org.openiaml.docs.modeldoc.JavadocMethodReference;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.JavadocTextElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModelExtension;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.OperationalSemantic;
import org.openiaml.docs.modeldoc.Reference;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.visual.VisualPackage;

/**
 * Tries to generate a model documentation instance from IAML.
 * 
 * @author jmwright
 *
 */
public class Test2 extends TestCase {
	
	private char[] readFile(File f) throws IOException {
		
		final int SIZE = 1024;
		StringBuffer buf = new StringBuffer();
		
		FileReader fr = new FileReader(f);
		while (true) {
			CharBuffer buffer = CharBuffer.allocate(SIZE);
			int len = fr.read(buffer);
			if (len == -1)
				break;
			buf.append(buffer.array(), 0, len);
		}
		fr.close();
		return buf.toString().toCharArray();

	}

	public ModelDocumentation createDocumentation() throws Exception {
		final ModeldocFactory factory = ModeldocFactory.eINSTANCE;
		
		final ModelDocumentation root = factory.createModelDocumentation();
		
		// load all EMF classes
		loadEMFClasses(ModelPackage.eINSTANCE, factory, root);

		// get all constraints
		loadOAWChecks(factory, root);
		
		// get all model extensions
		loadOAWExtensions(factory, root);
		
		// load all test cases for operational semantics
		loadTestCaseSemantics(factory, root);
		
		// load all rule files for inference semantics
		loadInferenceSemantics(factory, root);
		
		// get all icons
		loadIcons(factory, root);
		
		//System.out.println(cu);
		
		return root;
	}
	
	/**
	 * Load all of the runtime icons as GraphicalRepresentations.
	 * 
	 * @param factory
	 * @param root
	 */
	private void loadIcons(ModeldocFactory factory, ModelDocumentation root) {
		
		String plugin = "org.openiaml.model.edit";
		String pkg = "icons.full.obj16";
		File dir = new File("../org.openiaml.model.edit/icons/full/obj16/");
		
		String[] files = dir.list();
		for (String file : files) {
			// does there exist an EMFClass for this file?
			for (EMFClass cls : root.getClasses()) {
				String name = cls.getTargetClass().getName(); 
				if (file.startsWith(name + ".")) {
					// found a reference
					FileReference fr = factory.createFileReference();
					fr.setPlugin(plugin);
					fr.setPackage(pkg);
					fr.setName(file);
					root.getReferences().add(fr);
					
					// add a GraphicalReference
					GraphicalRepresentation gr = factory.createGraphicalRepresentation();
					gr.setReference(fr);
					cls.getGraphicalRepresentations().add(gr);
					
					// stop searching
					break;
				}
			}
		}
		
	}

	/**
	 * @param factory
	 * @param root
	 * @throws IOException 
	 */
	private void loadInferenceSemantics(ModeldocFactory factory,
			ModelDocumentation root) throws IOException {
	
		// get the default inference engine
		DroolsInferenceEngine engine = new CreateMissingElementsWithDrools(null, false);
		
		// for each rule file
		for (String file : engine.getRuleFiles()) {
			// load the inference rules
			String[] bits = getSimpleFilename(file);
			loadInferenceSemantics(factory, 
					root,
					"org.openiaml.model.drools" /* plugin */,
					bits[0] /* package */,
					bits[1] /* name */,
					new File("../org.openiaml.model.drools/" + file ) );
		}
		
	}

	/**
	 * Converts <code>/foo/bar/bar.drl</code> into <code>foo.baz</code>
	 * and <code>baz</code>.
	 * 
	 * @param file
	 * @return
	 */
	private String[] getSimpleFilename(String file) {
		
		String sep = "/";
		file = file.substring(0, file.lastIndexOf("."));
		String pkg = file.substring(0, file.lastIndexOf(sep));
		// remove leading /'s
		while (pkg.startsWith(sep)) {
			pkg = pkg.substring(1);
		}
		pkg = pkg.replace(sep, ".");
		
		String name = file.substring(file.lastIndexOf(sep) + 1);

		return new String[] { pkg, name };
		
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
	private void loadInferenceSemantics(ModeldocFactory factory,
			ModelDocumentation root, String plugin, String pkg,
			String name, File file) throws IOException {
		
		// create a package for this rule file
		DroolsPackage drools = factory.createDroolsPackage();
		drools.setPlugin(plugin);
		drools.setPackage(pkg);
		drools.setName(name);
		root.getReferences().add(drools);

		CharBuffer buf = CharBuffer.wrap(readFile(file));
		String[] lines = buf.toString().split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			
			String key = "# @semantics";
			if (line.startsWith(key)) {
				line = line.substring(key.length() + 1).trim();
				
				DroolsRule rule = createDroolsRule(factory, i + 1, lines);
				if (rule != null) {
					drools.getRules().add(rule);

					// a root tag for @semantics
					JavadocTagElement e = factory.createJavadocTagElement();
					e.setJavaParent(rule);
					e.setName("@semantics");
					
					// and then parse the line
					parseSemanticLine(line, factory, root, e);
				}
			}
		}
		
	}

	/**
	 * Parse down until we find a line starting with 
	 * <code>rule "rule title"</code>.
	 *  
	 * @return a newly created rule, or null if none could be found
	 */
	private DroolsRule createDroolsRule(ModeldocFactory factory, int i, String[] lines) {
		for (int j = i; j < lines.length; j++) {
			if (lines[i].trim().startsWith("rule ")) {
				// found a rule
				String ruleName = lines[i].trim();
				if (!ruleName.contains("\""))
					throw new RuntimeException("Rule does not contain \": '" + lines[i] + "'");
				ruleName = ruleName.substring(ruleName.indexOf("\""));
				if (!ruleName.contains("\""))
					throw new RuntimeException("Rule does not contain \" twice: '" + lines[i] + "'");
				ruleName = ruleName.substring(0, ruleName.lastIndexOf("\""));
				
				DroolsRule rule = factory.createDroolsRule();
				rule.setName(ruleName);
				rule.setLine(j);
				return rule;
			}
		}
		
		return null;
	}
	
	/**
	 * Parse the given semantic rule line, e.g.
	 * <code>This element {@model Element} ...</code>
	 */
	private void parseSemanticLine(String line, ModeldocFactory factory,
			ModelDocumentation root, JavadocTagElement e) {
		
		HandleInlineJavadoc inline = new HandleInlineJavadoc(factory, root, e);
		
		int pos = 0;
		while (true) {
			// find a {
			int next = line.indexOf('{', pos);
			if (next != -1) {
				// found one
				String text = line.substring(pos, next);
				inline.handleText(text);
				
				int next2 = line.indexOf('}', next);
				String tag = line.substring(next + 1, next2);
				if (tag.startsWith("@")) {
					String tagName = tag.substring(0, tag.indexOf(" "));
					String tagText = tag.substring(tag.indexOf(" ") + 1);
					inline.handleTag(tagName, tagText);
				} else {
					inline.handleText("{" + tag + "}"); 
				}
				
				pos = next2 + 1;
			} else {
				// found the end; break
				break;
			}
		}
		
		// handle remaining test
		String text = line.substring(pos);
		inline.handleText(text);
		
	}
	
	private class HandleInlineJavadoc {
		private ModeldocFactory factory;
		private ModelDocumentation root;
		private JavadocTagElement tagElement;
		
		public HandleInlineJavadoc(ModeldocFactory factory, ModelDocumentation root, JavadocTagElement tagElement) {
			this.factory = factory;
			this.root = root;
			this.tagElement = tagElement;
		}
		
		/**
		 * Handle a fragment of Javadoc text.
		 * 
		 * @param text
		 */
		private void handleText(String text) {
			JavadocTextElement e = factory.createJavadocTextElement();
			e.setValue(text);
			tagElement.getFragments().add(e);
		}
		
		/**
		 * Handle a '@tag text' inline.
		 * 
		 * Expects "<code>@model ClassName</code>" tag for it to add
		 * an InferenceSemantic.
		 * 
		 * @param tag cannot be null.
		 * @param text
		 */
		private void handleTag(String tag, String text) {
			if (tag == null)
				throw new NullPointerException("tag cannot be null");
			
			JavadocTagElement fragment = factory.createJavadocTagElement();
			fragment.setName(tag);
			
			JavadocTextElement text2 = factory.createJavadocTextElement();
			text2.setValue(text);
			fragment.getFragments().add(text2);
			
			// what kind of tag is it?
			if (tag.equals("@model")) {
				
				// find the class it refers to
				EMFClass cls = getEMFClassFor(root, text);
				if (cls != null) {
					// make an inference semantics link
					InferenceSemantic semantic = factory.createInferenceSemantic();
					semantic.setDescription(tagElement);
					semantic.setReference(tagElement);
					
					// add it to the EMFclass
					cls.getInferenceSemantics().add(semantic);
				}
				
			}
			
			tagElement.getFragments().add(fragment);

		}
	}

	/**
	 * Find an existing EMFClass for the given name.
	 * Does not create a new one.
	 * 
	 * @param o
	 * @return the class found, or null
	 */
	private EMFClass getEMFClassFor(ModelDocumentation root, String name) {
		for (EMFClass cls : root.getClasses()) {
			if (cls.getTargetClass().getName().equals(name)) {
				// found it
				return cls;
			}
			
		}
		
		return null;
	}

	/**
	 * Load test case semantics in the given directory recursively.
	 * 
	 * @param factory
	 * @param root
	 * @throws IOException
	 */
	public void loadTestCaseSemantics(final ModeldocFactory factory,
			final ModelDocumentation root, File folder, String plugin, String pkg) throws IOException {
		
		// for every java in this folder,
		String[] files = folder.list();
		for (String file : files) {
			File inFile = new File(folder.getAbsolutePath() + File.separator + file);
			if (inFile.isDirectory()) {
				// recurse over directories
				loadTestCaseSemantics(factory, root, inFile, plugin, pkg + "." + file);
			} else if (file.endsWith(".java")) {
				// iterate over this file
				String name = file.substring(0, file.lastIndexOf(".java"));
				
				System.out.println("Parsing '" + inFile + "'...");
				
				ASTParser parser = ASTParser.newParser(AST.JLS3);
				parser.setKind(ASTParser.K_COMPILATION_UNIT);
				char[] input = readFile(inFile);
				parser.setSource(input);
				parser.setResolveBindings(true);
				CompilationUnit cu = (CompilationUnit) parser.createAST(null);
				
				// create a new JavaClass
				JavaClass cls = factory.createJavaClass();
				cls.setPlugin(plugin);
				cls.setPackage(pkg);
				cls.setName(name);
				cls.setParent(root);
				
				cu.accept(new OperationalSemanticsJavadocVisitor(factory, root, cls, input));
			}
		}
		
	}

	/**
	 * An AST visitor to iterate over Java ASTs (loaded by Eclipse)
	 * and locate Javadoc comments for operational semantics.
	 * 
	 * @author jmwright
	 *
	 */
	private class OperationalSemanticsJavadocVisitor extends ASTVisitor {
		
		private ModeldocFactory factory;
		private ModelDocumentation root;
		private JavaClass cls;
		private char[] input;
		
		/**
		 * @param factory
		 * @param root
		 */
		public OperationalSemanticsJavadocVisitor(ModeldocFactory factory,
				ModelDocumentation root, JavaClass cls, char[] input) {
			super();
			this.factory = factory;
			this.root = root;
			this.cls = cls;
			this.input = input;
		}

		private List<JavadocFragment> handleTagFragment(
				List<?> fragments) {
			List<JavadocFragment> result = new ArrayList<JavadocFragment>();
			for (Object o : fragments) {
				if (o instanceof TagElement) {
					TagElement tag = (TagElement) o;
					JavadocTagElement e = factory.createJavadocTagElement();
					e.setName(tag.getTagName());
					
					// recurse to create parents						
					e.getFragments().addAll(handleTagFragment(tag.fragments()));
					
					// link up any references to model elements
					handleModelReferences(e);
					
					result.add(e);
				} else if (o instanceof TextElement) {
					TextElement text = (TextElement) o;
					JavadocTextElement e = factory.createJavadocTextElement();
					e.setValue(text.getText());
					result.add(e);
				} else if (o instanceof MethodRef) {
					MethodRef ref = (MethodRef) o;
					JavaMethod method = getMethodFor(ref);
					if (method != null) {
						JavadocMethodReference e = factory.createJavadocMethodReference();
						e.setReference(method);
						result.add(e);
					}
				} else if (o instanceof SimpleName) {
					// assume it is a class name
					JavaClass cls = getJavaClassFor((SimpleName) o);
					if (cls != null) {
						JavadocClassReference e = factory.createJavadocClassReference();
						e.setReference(cls);
						result.add(e);
					}
				} else if (o instanceof QualifiedName) {
					// assume it is a class name (with package)
					JavaClass cls = getJavaClassFor((QualifiedName) o);
					if (cls != null) {
						JavadocClassReference e = factory.createJavadocClassReference();
						e.setReference(cls);
						result.add(e);
					}
				} else if (o instanceof MemberRef) {
					// ignore
				} else {
					throw new RuntimeException("Unknown element type '" + o.getClass() + "': '" + o + "'");
				}
			}
			return result;
		}

		/**
		 * With the given JavaDoc element, should there be any links 
		 * created?
		 * 
		 * @param e
		 */
		private void handleModelReferences(JavadocTagElement e) {
			if ("@model".equals(e.getName())) {
				// cycle through all model elements
				JavadocTextElement refName = null;
				for (JavadocFragment f : e.getFragments()) {
					// select the first TextElement; this is the target class
					if (f instanceof JavadocTextElement) {
						refName = (JavadocTextElement) f;
					}						
				}
				if (refName != null) {
					for (EMFClass emf : root.getClasses()) {
						if (emf.getTargetClass().getName().equals(refName.getValue().trim())) {
							// create an operational semantic
							OperationalSemantic op = factory.createOperationalSemantic();
							op.setDescription(e);
							op.setReference(e);
							
							// add a reference
							emf.getOperationalSemantics().add(op);
						}
					}
				}
			}
		}

		/**
		 * Find an existing JavaClass for the given name.
		 * Does not create a new one.
		 * 
		 * @param o
		 * @return the class found, or null
		 */
		private JavaClass getJavaClassFor(SimpleName o) {
			for (Reference r : root.getReferences()) {
				if (r instanceof JavaClass) {
					JavaClass cls = (JavaClass) r;
					if (cls.getName().equals(o.getFullyQualifiedName())) {
						// found it
						return cls;
					}
				}
			}
			
			return null;
		}

		/**
		 * Find an existing JavaClass for the given name.
		 * Does not create a new one.
		 * Since it is a qualified name, we are also looking for packages.
		 * 
		 * @param o
		 * @return the class found, or null
		 */
		private JavaClass getJavaClassFor(QualifiedName o) {
			for (Reference r : root.getReferences()) {
				if (r instanceof JavaClass) {
					JavaClass cls = (JavaClass) r;
					String qn = cls.getPackage() + "." + cls.getName();
					if (qn.equals(o.getFullyQualifiedName())) {
						// found it
						return cls;
					}
				}
			}
			
			return null;
		}
		
		/**
		 * Wrap a single TagElement and get a single JavadocTagElement back.
		 * 
		 * @param fragment
		 * @return
		 */
		private JavadocTagElement handleTagFragment(
				TagElement fragment) {
			List<Object> input = new ArrayList<Object>();
			input.add(fragment);
			List<JavadocFragment> result = handleTagFragment(input);
			return (JavadocTagElement) result.get(0);
		}

		@Override
		public boolean visit(Javadoc node) {
			if (node.getParent() instanceof TypeDeclaration) {
				TypeDeclaration parent = (TypeDeclaration) node.getParent();
				
				if (parentMatches(parent, cls)) {					
					for (Object o : node.tags()) {
						TagElement tag = (TagElement) o;
						JavadocTagElement docs = handleTagFragment(tag);
						cls.getJavadocs().add(docs);
					}
				}
		
			} else if (node.getParent() instanceof MethodDeclaration) {
				// a method
				MethodDeclaration parent = (MethodDeclaration) node.getParent();
				
				JavaMethod method = getMethodFor(parent, cls);
				if (method != null) {
					for (Object o : node.tags()) {
						TagElement tag = (TagElement) o;
						JavadocTagElement docs = handleTagFragment(tag);
						method.getJavadocs().add(docs);
					}
				}
				
			}
			
			
			/*
			System.out.println(node);
			for (Object o : node.tags()) {
				TagElement tag = (TagElement) o;
				System.out.println(tag.getTagName() + " = " + tag.fragments());
			}
			*/
			return super.visit(node);
		}

		/**
		 * Get the JavaMethod for the given parent in the given class.
		 * Create it if necessary and possible. 
		 * Return null if no such method can be created.
		 * 
		 * @param ref
		 * @return
		 */
		private JavaMethod getMethodFor(MethodRef ref) {
			return getMethodFor(ref, cls);
		}
		
		/**
		 * Get the JavaMethod for the given parent in the given class.
		 * Create it if necessary and possible. 
		 * Return null if no such method can be created.
		 * 
		 * @param ref
		 * @return
		 */
		private JavaMethod getMethodFor(MethodRef ref, JavaClass cls) {
			if (ref.getQualifier() != null) {
				if (!ref.getQualifier().equals(cls.getName()))
					return null;	// a different class: can never match
			}
			for (JavaMethod m : cls.getMethods()) {
				if (m.getName().equals(ref.getName().getFullyQualifiedName())) {
					// found it
					return m;
				}
			}
			
			// need to create a new one
			JavaMethod method = factory.createJavaMethod();
			method.setName( ref.getName().getFullyQualifiedName() );
			// cannot update the line count; we let {@link #getMethodFor(MethodDeclaration, JavaClass)} do this.
			// method.setLine( getLineFor(ref.getStartPosition()) );
			method.setJavaClass(cls);
			return method;		
		}
		
		/**
		 * Get the JavaMethod for the given parent in the given
		 * class. Create it if necessary. Return null if no such method 
		 * can be created.
		 * 
		 * @param parent
		 * @param cls
		 * @return
		 */
		private JavaMethod getMethodFor(MethodDeclaration parent,
				JavaClass cls) {
			// any existing methods?
			for (JavaMethod m : cls.getMethods()) {
				String methodName = parent.getName().getFullyQualifiedName();
				if (m.getName().equals(methodName)) {
					// update the line
					m.setLine( getLineFor(parent.getStartPosition()) );
					return m;
				}
			}
			
			// we need to make a new one
			JavaMethod method = factory.createJavaMethod();
			method.setName( parent.getName().getFullyQualifiedName() );
			method.setLine( getLineFor(parent.getStartPosition()) );
			method.setJavaClass(cls);
			return method;				
		}
		
		/**
		 * Get the line number for a given position.
		 * 
		 * @param pos
		 * @return
		 */
		private int getLineFor(int pos) {
			int r = 0;
			for (int i = 0; i <= pos; i++) {
				if (input[i] == '\n')
					r++;
			}
			return r;
		}

		private boolean parentMatches(TypeDeclaration parent, JavaClass cls) {
			if (parent.getParent() instanceof CompilationUnit) {
				// root element in a class
				CompilationUnit cu = (CompilationUnit) parent.getParent();
				String cuPackage = cu.getPackage().getName().getFullyQualifiedName();
				String clsPackage = cls.getPackage();
				String cuName = parent.getName().getFullyQualifiedName();
				String clsName = cls.getName();
				if (cuPackage.equals(clsPackage) && cuName.equals(clsName)) {
					// matches!
					return true;
				}
			}
			return false;
		}
		
	}
	
	/**
	 * Load test case semantics.
	 * 
	 * @param factory
	 * @param root
	 * @throws IOException 
	 */
	public void loadTestCaseSemantics(final ModeldocFactory factory,
			final ModelDocumentation root) throws IOException {

		loadTestCaseSemantics(factory, 
				root, 
				new File("../org.openiaml.model.tests/src/org") /* folder */, 
				"org.openiaml.model.tests" /* plugin */, 
				"org" /* starting package */);
	}

	/**
	 * Load all of the OAW constraints.
	 */
	private void loadOAWChecks(ModeldocFactory factory, ModelDocumentation root) throws Exception {
		String checkFile = "../org.openiaml.model.codegen.oaw/src/metamodel/Checks.chk";
		InputStream in = new FileInputStream(checkFile);
		
		FileReference fr = factory.createFileReference();
		fr.setPackage("org.openiaml.model.codegen.oaw");
		fr.setPackage("src.metamodel");
		fr.setName("Checks.chk");
		fr.setParent(root);
		
		ExtensionFile file = ParseFacade.file(new InputStreamReader(in), checkFile);
		
		for (Check check : file.getChecks()) {
			
			// map the Identifier (the context) to an EMFClass
			EMFClass identifier = mapOAWIdentifier(root, check.getType());
			if (identifier == null)
				continue;	// unidentified type (e.g. emf::EObject)

			// make a new Constraint for this check
			Constraint constraint = factory.createConstraint();
			constraint.setConstraint(check.getConstraint().toString());
			constraint.setType( check.isErrorCheck() ? ConstraintType.ERROR : ConstraintType.WARNING );
			constraint.setMessage( check.getMsg().toString() );

			// make a new FileReference
			FileLineReference line = factory.createFileLineReference();
			line.setFile(fr);
			line.setLine(check.getLine());
			constraint.setReference(fr);
			
			// add this constraint
			identifier.getConstraints().add(constraint);
		}
	}

	
	/**
	 * Load all of the OAW extensions.
	 */
	private void loadOAWExtensions(ModeldocFactory factory, ModelDocumentation root) throws Exception {
		String checkFile = "../org.openiaml.model.codegen.oaw/src/metamodel/Extensions.ext";
		InputStream in = new FileInputStream(checkFile);
		
		FileReference fr = factory.createFileReference();
		fr.setPackage("org.openiaml.model.codegen.oaw");
		fr.setPackage("src.metamodel");
		fr.setName("Extensions.ext");
		fr.setParent(root);
		
		ExtensionFile file = ParseFacade.file(new InputStreamReader(in), checkFile);
		
		for (Extension ext : file.getExtensions()) {
		
			// is there at least one type?
			if (ext.getFormalParameters() != null && ext.getFormalParameters().size() > 0) {
				
				// map the Type (the first parameter) to an EMFClass
				EMFClass identifier = mapOAWType(root, ext.getFormalParameters().get(0));				
				if (identifier == null)
					continue;	// unidentified type (e.g. emf::EObject)
				
				// make a new Extension
				ModelExtension extension = factory.createModelExtension();
				extension.setName(ext.getName());
				extension.setValue(ext.toString());
				
				// make a new FileReference
				FileLineReference line = factory.createFileLineReference();
				line.setFile(fr);
				line.setLine(ext.getLine());
				extension.setReference(fr);

				// add this extension
				identifier.getExtensions().add(extension);

			}
		}
	}

	/**
	 * @param root
	 * @param declaredParameter
	 * @return
	 */
	private EMFClass mapOAWType(ModelDocumentation root, DeclaredParameter declaredParameter) {
		return mapOAWIdentifier(root, declaredParameter.getType());
	}

	/**
	 * Map the Identifier (the context) to an EMFClass in the root.
	 * 
	 * @param type
	 * @return
	 */
	private EMFClass mapOAWIdentifier(ModelDocumentation root, Identifier type) {
		for (EMFClass cls : root.getClasses()) {
			if (identifierMatches(type, cls.getTargetClass())) {
				return cls;
			}
		}
		
		// could not find
		System.err.println("Could not identify type '" + type + "'");
		return null;
	}

	/**
	 * @param type
	 * @param cls
	 * @return
	 */
	private boolean identifierMatches(Identifier type, EClass ref) {
		String[] bits = type.getValue().split("::");
		
		if (ref.getName().equals(bits[bits.length - 1])) {
			// same name
			EPackage currentPackage = ref.getEPackage();
			for (int i = bits.length - 2; i >= 0; i--) {
				if (currentPackage == null) {
					// bail 
					return false;
				}
				if (!currentPackage.getName().equals(bits[i])) {
					// bail
					return false;
				}
				currentPackage = currentPackage.getESuperPackage();
			}
			
			// we should be at the root of the package heirarchy
			if (currentPackage != null)
				return false;
			
			// everything matches!
			return true;
		}
		
		return false;
	}
	
	public void testIdentifierMatches() {
		
		assertTrue(identifierMatches(new Identifier("model::InternetApplication"), ModelPackage.eINSTANCE.getInternetApplication()));
		assertTrue(identifierMatches(new Identifier("model::visual::Page"), VisualPackage.eINSTANCE.getPage()));
		assertFalse(identifierMatches(new Identifier("model::InternetApplication"), VisualPackage.eINSTANCE.getPage()));
		assertFalse(identifierMatches(new Identifier("model::visual::Page"), ModelPackage.eINSTANCE.getInternetApplication()));
		
	}

	/**
	 * Load all EMF classes in the given package into the given documentation.
	 * 
	 * @param root
	 */
	protected void loadEMFClasses(EPackage pkg, ModeldocFactory factory, ModelDocumentation root) {
		for (EClassifier classifier : pkg.getEClassifiers()) {
			if (classifier instanceof EClass) {
				
				EClass cls = (EClass) classifier;
				
				EMFClass c = factory.createEMFClass();
				c.setTargetClass(cls);
				c.setDescription("TODO Description");
				c.setTagline("TODO Tagline");
				c.setParent(root);
				
				// link up the source java class
				getJavaClassFor(c, cls, factory, root);
				
			}
		}
	
		// load all subpackages
		for (EPackage sub : pkg.getESubpackages()) {
			loadEMFClasses(sub, factory, root);
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
		jc.setPlugin("org.openiaml.model");				
		jc.setPackage("org.openiaml.model." + getJavaPackageFor(cls.getEPackage()));
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

	/**
	 * Tries to create a new instance of ModelDocumentation and
	 * save it to a file.
	 * 
	 * @throws Exception
	 */
	public void testDump() throws Exception {
		EObject root = createDocumentation();
		
		ResourceSet resourceSet = new ResourceSetImpl();
        URI fileURI = URI.createFileURI(new File("test.modeldoc")
                .getAbsolutePath());
        Resource resource = resourceSet.createResource(fileURI);
        resource.getContents().add(root);
        resource.save(Collections.EMPTY_MAP);
	}
	
	/*
	public void testNewModel() throws Exception {
		
		// get the EClass from IAML
		EClass page = VisualPackage.eINSTANCE.getPage();
		
		EMFClass c = ModeldocFactory.eINSTANCE.createEMFClass();
		c.setTargetClass(page);

		
        
        // try loading it!
		EMFClass c2 = null;
		{
			ResourceSet resourceSet = new ResourceSetImpl();
	        URI fileURI = URI.createFileURI(new File("test.modeldoc")
	                .getAbsolutePath());
	        Resource resource = resourceSet.getResource(fileURI, true);
	        c2 = (EMFClass) resource.getContents().get(0);
		}
		
		assertEquals(page, c2.getTargetClass());
		System.out.println(c2.getTargetClass());
		System.out.println(((EClass) c2.getTargetClass()).getEAllContainments());
        
	}
	*/
		
}
