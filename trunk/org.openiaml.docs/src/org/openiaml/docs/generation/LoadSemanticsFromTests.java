/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.openiaml.docs.generation.semantics.ITagHandler;
import org.openiaml.docs.generation.semantics.SemanticFinder;
import org.openiaml.docs.generation.semantics.SemanticHandlerException;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.JavaElement;
import org.openiaml.docs.modeldoc.JavaMethod;
import org.openiaml.docs.modeldoc.JavadocClassReference;
import org.openiaml.docs.modeldoc.JavadocFragment;
import org.openiaml.docs.modeldoc.JavadocMethodReference;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.JavadocTextElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.Reference;

/**
 * @author jmwright
 *
 */
public class LoadSemanticsFromTests extends DocumentationHelper implements ILoader {
	
	private File folder;
	
	private String plugin;
	
	private String packageBase;

	private DocumentationGenerator generator;
	
	public List<ITagHandler> getSemanticTagHandlers() {
		return generator.getSemanticTagHandlers();
	}
	
	/**
	 * @param folder
	 * @param plugin
	 * @param packageBase
	 * @param semanticTagHandlers
	 */
	public LoadSemanticsFromTests(File folder, String plugin,
			String packageBase, DocumentationGenerator generator) {
		super();
		this.folder = folder;
		this.plugin = plugin;
		this.packageBase = packageBase;
		this. generator = generator;
	}

	/**
	 * Load test case semantics.
	 * 
	 * @param factory
	 * @param root
	 * @throws IOException 
	 */
	public void load(final ModeldocFactory factory,
			final ModelDocumentation root) throws DocumentationGenerationException {

		try {
			loadTestCaseSemantics(factory, 
					root, 
					folder /* folder */, 
					plugin /* plugin */, 
					packageBase /* starting package */);
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
	protected void loadTestCaseSemantics(final ModeldocFactory factory,
			final ModelDocumentation root, File folder, String plugin, String pkg) throws IOException {
		
		if (!folder.exists())
			throw new RuntimeException(folder + " does not exist");
		
		// for every java in this folder,
		String[] files = folder.list();
		for (String file : files) {
			File inFile = new File(folder.getAbsolutePath() + File.separator + file);
			if (inFile.isDirectory()) {
				// recurse over directories
				loadTestCaseSemantics(factory, root, inFile, plugin, pkg + "." + file);
			} else if (file.endsWith(".java")) {
				// iterate over this file
				String name = file.substring(0, file.lastIndexOf(".java")); // remove extension
				
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
				List<?> fragments, 
				JavaElement javaReference) throws SemanticHandlerException {
			List<JavadocFragment> result = new ArrayList<JavadocFragment>();
			for (Object o : fragments) {
				if (o instanceof TagElement) {
					TagElement tag = (TagElement) o;
					JavadocTagElement e = factory.createJavadocTagElement();
					e.setName(tag.getTagName());
					
					// recurse to create parents						
					e.getFragments().addAll(handleTagFragment(tag.fragments(), javaReference));
					
					// link up any references to model elements
					handleModelReferences(e, javaReference);
					
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
		 * Iterate over all semantic handlers in {@link LoadSemanticsFromTests#getSemanticTagHandlers()}
		 * and identify potential semantic tags.
		 * 
		 * @param e
		 * @param reference
		 * @throws SemanticHandlerException 
		 */
		protected void handleModelReferences(JavadocTagElement e, Reference reference) throws SemanticHandlerException {
			SemanticFinder finder = new SemanticFinder();
			for (ITagHandler sem : getSemanticTagHandlers()) {
				finder.findSemanticReferences(LoadSemanticsFromTests.this, root, e, reference, sem);
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
		 * @throws SemanticHandlerException 
		 */
		private JavadocTagElement handleTagFragment(
				TagElement fragment, JavaElement javaReference) throws SemanticHandlerException {
			List<Object> input = new ArrayList<Object>();
			input.add(fragment);
			List<JavadocFragment> result = handleTagFragment(input, javaReference);

			JavadocTagElement je = (JavadocTagElement) result.get(0);
			
			return je; 
		}

		@Override
		public boolean visit(Javadoc node) {
			try {
				if (node.getParent() instanceof TypeDeclaration) {
					TypeDeclaration parent = (TypeDeclaration) node.getParent();
				
					if (parentMatches(parent, cls)) {					
						for (Object o : node.tags()) {
							TagElement tag = (TagElement) o;
							JavadocTagElement docs = handleTagFragment(tag, cls);
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
							JavadocTagElement docs = handleTagFragment(tag, method);
							method.getJavadocs().add(docs);
						}
					}
					
				}
			} catch (SemanticHandlerException e) {
				throw new RuntimeException("Could not process node '" + node + "': " + e.getMessage(), e);
			}

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
	
}
