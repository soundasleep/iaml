package org.openiaml.model.diagram.custom.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Action to migrate an old .iaml file to a new .iaml file
 * 
 * @see org.openiaml.model.codegen.oaw
 * @author jmwright
 *
 */
public class MigrateModelAction implements IViewActionDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					IFile source = (IFile) o;
					// we need to get a new file
					IPath containerPath = source.getFullPath().removeLastSegments(1);
					String fileName = source.getName();
					String fileExtension = source.getFileExtension();
					// generate unique name
					String uniqueName = IamlDiagramEditorUtil.getUniqueFileName( containerPath, fileName, fileExtension);

					// TODO migrate this to a wizard
					InputDialog dialog = new InputDialog(
							PlatformUI.getWorkbench().getDisplay().getActiveShell(),
							"Enter in destination model file",
							"Please enter in the destination model file",
							uniqueName,
							null	// InputValidator
						);
					dialog.setBlockOnOpen(true);
					dialog.open();
					
					String destination = dialog.getValue();
					if (destination == null) {
						// cancelled
						return;
					}
					
					// get the file
					IFile target = source.getProject().getFile(
							source.getProjectRelativePath().removeLastSegments(1).append(destination)
						);
					
					if (target.exists()) {
						IamlDiagramEditorPlugin.getInstance().logError(
								"The target model file already exists.", null); //$NON-NLS-1$
						return;
					}
					
					// must not be the same file
					if (source.getLocation().toString().equals(target.getLocation().toString())) {
						IamlDiagramEditorPlugin.getInstance().logError(
								"Cannot write to the same file.", null); //$NON-NLS-1$
						return;
					}
					
					// migrate it
					IStatus status = migrateModel(source, target, new NullProgressMonitor());
					if (!status.isOK()) {
						// TODO remove this reference to the plugin and remove the reference in plugin.xml
						IamlDiagramEditorPlugin.getInstance().logError(
								"Could not migrate '" + source + "' to '" + target + "': " + status.getMessage(), status.getException());
					}
				}
			}
		}

	}
	
	/**
	 * Migrate a file to another file. 
	 * 
	 * We have a couple of options for loading the files for migration:
	 * <ol>
	 *   <li>Keep multiple instances of the model files in multiple plugins, and let
	 *       EMF decide which model implementation to load. Once the model is loaded,
	 *       we can use EMF functionality to traverse over the model and create a new
	 *       version.</li>
	 *   <li>Use ATL. But ATL also requires a metamodel for both the source and target
	 *       models.</li>
	 *   <li>Load the model file with XML and create a new XMI model manually. This is
	 *       more error prone.</li> 
	 * </ol>
	 * 
	 * We also have some options for doing multiple migrations in a row (say v1, v2, v3, v4):
	 * <ol>
	 *   <li>v1 -> v2, v2 -> v3, v3 -> v4</li>
	 *   <li>v1 -> v4, v2 -> v4, v3 -> v4</li>
	 * </ol>
	 * 
	 * The first option requires less development work but more processing time
	 * since they chain.
	 * 
	 * @param input
	 * @param output
	 * @param monitor
	 * @return
	 */
	public IStatus migrateModel(IFile input, IFile output, IProgressMonitor monitor) {
		// sanity
		assert(input.exists());
		assert(!output.exists());
		
		try {
			// get all the migrators
			ArrayList<IamlModelMigrator> migrators = new ArrayList<IamlModelMigrator>();
			migrators.add(new Migrate0To1());
			
			// load the initial document
			Document doc = loadDocument(input.getContents());
			
			// try each of them
			for (IamlModelMigrator m : migrators) {
				if (m.isVersion(doc)) {
					// we want to migrate it with this migrator
					InputStream target = m.migrate(doc, monitor);
					
					// write this to the file
					if (output.exists()) {
						// replace
						output.setContents(target, true, false, monitor);
					} else {
						// insert
						output.create(target, true, monitor);
					}
					
					// then reload the file for the next round
					doc = loadDocument(output.getContents());
					
					// we now want to pipe the target and source together
					
				}
			}
		} catch (Exception e) {
			return new Status(Status.ERROR, PLUGIN_ID, "Could not load migrated model: " + e.getMessage(), e);
		}
		
		return Status.OK_STATUS;
	}
	
	/**
	 * Eclipse can't handle the RewindableInputStream that the XML reader
	 * uses, and throws an IOException(read error). This happens because
	 * we are trying to read the same XML file multiple times.
	 * 
	 * In this method we load the model up once and only once per file. The
	 * loaded document is instead passed to the migrators.
	 * 
	 * @param source
	 * @return
	 * @throws MigrationException
	 */
	public Document loadDocument(InputStream source) throws MigrationException {
		try {
			// load the model version
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(source);
			
			// done
			source.close();
			
			return doc;
		} catch (Exception e) {
			throw new MigrationException(e);
		}
	}
	
	/**
	 * Migrate model version 0.0 to version 0.1.
	 * 
	 * Differences:
	 * - 0.1 now has ID values
	 * 
	 * @author jmwright
	 *
	 */
	protected class Migrate0To1 implements IamlModelMigrator {

		public String toString() {
			return "Migrator 0.0 to 0.1 [" + super.toString() + "]";
		}
		
		/**
		 * TODO write up what defines a 0.0 model 
		 * 
		 * @see org.openiaml.model.diagram.custom.actions.MigrateModelAction.IamlModelMigrator#isVersion(org.eclipse.core.resources.IFile)
		 */
		@Override
		public boolean isVersion(Document doc) throws MigrationException {
			try {
				// get parameters
				String nsPackage = doc.getDocumentElement().getAttribute("xmlns:iaml");
				String rootId = doc.getDocumentElement().getAttribute("id");

				if (nsPackage.equals("http://openiaml.org/model") && 
						rootId.isEmpty()) {
					// this is us!
					return true;
				}
			} catch (Exception e) {
				throw new MigrationException(e);
			}
				
			// TODO Auto-generated method stub
			return false;
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.diagram.custom.actions.MigrateModelAction.IamlModelMigrator#migrate(org.eclipse.core.resources.IFile, org.eclipse.core.resources.IFile, org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		public InputStream migrate(Document doc, IProgressMonitor monitor)
				throws MigrationException {
			try {
				// create the new document (output)
				DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
				DocumentBuilder db2 = dbf2.newDocumentBuilder();
				Document doc2 = db2.newDocument();
				
				// cycle through each element and add "id" attributes
				// and map their "id" attribute to their original position
				idMap = new HashMap<String,String>();
				recurseOverDocument(doc, doc2);

	            TransformerFactory transfac = TransformerFactory.newInstance();
	            Transformer trans = transfac.newTransformer();
	            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");	// omit '<?xml version="1.0"?>'
	            trans.setOutputProperty(OutputKeys.INDENT, "yes");

	            // TODO clean this up into a piped input/output stream setup?
				File f = File.createTempFile("test", "iaml");
				f.deleteOnExit();		// delete when done
				FileWriter sw = new FileWriter(f);
	            StreamResult result = new StreamResult(sw);
	            DOMSource source = new DOMSource(doc2);
	            trans.transform(source, result);
	            sw.close();

	            // done: load this output file as an input stream for the next migrator
	            FileInputStream fout = new FileInputStream(f);
	            return fout;
	
			} catch (Exception e) {
				throw new MigrationException(e);
			}
		}

		protected List<String> deletedIds;
		
		protected void recurseOverDocument(Document input, Document output) {
			// reset deleted list
			deletedIds = new ArrayList<String>();
			
			// recurse over the document, adding elements and translating
			// where necessary
			recurseOverDocument( input.getDocumentElement(), output, output );
			
			// go over all deleted elements and remove them from all
			// referenced attributes
			removeDeletedReferences(output.getDocumentElement());
		}
		
		/**
		 * We need to be able to handle removed references. Because
		 * a reference may be deleted either at the start or end of the document,
		 * and before or after we have translated any references to it,
		 * we must remove deleted references <em>after</em> the translation
		 * process has been completed.
		 * 
		 * This may be quite slow.
		 * 
		 * @param element
		 */
		protected void removeDeletedReferences(Element element) {
			for (String id : deletedIds) {
				System.out.println("removing reference " + id);
				
				for (int i = 0; i < element.getAttributes().getLength(); i++) {
					String attrName = element.getAttributes().item(i).getNodeName();
					String attrValue = element.getAttributes().item(i).getNodeValue();
					
					// does this attribute have it?
					if (attrValue.equals(id)) {
						element.removeAttribute(attrName);
					} else if (attrValue.indexOf(id + " ") == 0) {
						// starts with it
						element.setAttribute(attrName, attrValue.substring( attrValue.indexOf(id + " " )));
					} else if (attrValue.indexOf(" " + id) > 0) {
						// ends with it
						element.setAttribute(attrName, attrValue.substring( 0, attrValue.indexOf(" " + id )));
					}
					
				}
			}
			
			// process over child nodes
			for (int i = 0; i < element.getChildNodes().getLength(); i++) {
				Node n = element.getChildNodes().item(i);
				if (n instanceof Element) {
					removeDeletedReferences((Element) n);
				}
			}
		}
		
		/**
		 * Calculate the old and new IDs for this element, add the
		 * mapping (for future references), and add it as a deleted reference.
		 * 
		 * @param element
		 */
		protected void addDeletedReference(Element element) {
			String oldId = calculateOldId(element);
			String newId = getMigratedId(oldId);
			idMap.put(oldId, newId);
			deletedIds.add(newId);
		}

		private int idCounter = 0;
		
		protected void recurseOverDocument(Element element,
				Node output, Document document) {
			String nodeName = element.getNodeName();
			
			// special cases
			// *.edges --> *.wires
			if (element.getNodeName().equals("edges")) {
				nodeName = "wires";
			}
			Element e = document.createElement( nodeName );
			
			// an <operation> can no longer contain <wires>
			if (nodeName.equals("wires") && output.getNodeName().equals("operations")) {
				// TODO add warning!
				System.err.println("<operation> can no longer contain <wires>");
				addDeletedReference(element);
				return;
			}
			
			// cycle over attributes
			// TODO add a wrapper to make this iterable
			for (int i = 0; i < element.getAttributes().getLength(); i++) {
				Node n = element.getAttributes().item(i);
				String value = n.getNodeValue();
				
				// should we replace IDs?
				if (value.indexOf("//@") >= 0) {
					value = replaceReferences(value);
				}
				
				e.setAttribute( n.getNodeName(), value );
			}
			
			// does it have an id?
			if (element.getAttribute("id").isEmpty()) {
				String oldId = calculateOldId(element);
				String newId = getMigratedId(oldId);
				idMap.put(oldId, newId);
				e.setAttribute("id", newId);
			}
			
			// should we replace the xsi:type?
			if (!element.getAttribute("xsi:type").isEmpty()) {
				// RunWire --> RunInstanceWire
				String xsiType = element.getAttribute("xsi:type");
				if (xsiType.equals("iaml.wires:RunWire")) {
					xsiType = "iaml.wires:RunInstanceWire";
				}

				// ProvidedParameterWire --> ParameterWire
				if (xsiType.equals("iaml.wires:ProvidedParameterWire")) {
					xsiType = "iaml.wires:ParameterWire";
				}

				// PropertyToExecutionWire has been removed
				if (xsiType.equals("iaml.wires:PropertyToExecutionWire")) {
					// TODO warning!
					System.err.println("no more type iaml.wires:PropertyToExecutionWire");
					addDeletedReference(element);
					return;
				}
				
				// we can no longer have operations that are StartNodes:
				// replace with a normal operation
				if (element.getNodeName().equals("operations")) {
					if (xsiType.equals("iaml.operations:StartNode") ||
							xsiType.equals("iaml.operations:StopNode") ||
							xsiType.equals("iaml.operations:FinishNode")) {
						xsiType = "iaml:ChainedOperation";
						// TODO add this warning in the final result (so the 
						// user can view it)
						// we can't add an attribute that isn't in the EMF model
						// e.setAttribute("migratedWarning", "operations can no longer be StartNode, StopNode or FinishNode");
					}
				}

				e.setAttribute("xsi:type", xsiType);
			}
			
			// recurse over children
			for (int i = 0; i < element.getChildNodes().getLength(); i++) {
				Node n = element.getChildNodes().item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					recurseOverDocument((Element) n, e, document);
				} else {					
					// e.appendChild(document.adoptNode(n));	// this also adds the children of the node, which we don't want 
				}
			}
			
			output.appendChild(e);
		}
		
		/**
		 * Replace "//@foo.1/@bar.2 //@car.3" with "id1 id2"
		 * 
		 * @param value
		 * @return
		 */
		private String replaceReferences(String value) {
			// multiple references in here?
			String[] references = value.split(" ");
			String newValue = "";
			for (String ref : references) {
				newValue += getMigratedId(ref) + " ";
			}
			return newValue.trim();
		}

		/**
		 * Calculate what would be the old ID for this element.
		 * 
		 * @param element
		 * @return
		 */
		private String calculateOldId(Element element) {
			Node n = element.getParentNode();
			if (n == null || n instanceof Document || !(n instanceof Element)) {
				return "/";		// root
			}
			return calculateOldId((Element) n) + "/@" + element.getNodeName() + "." + getNodeIndex(element);
		}
		
		
		/**
		 * Get the migrated ID for this element. In some cases the element
		 * will be referenced before the "id" attribute can be added;
		 * in others, the references will occur after the "id" attribute
		 * has been added.
		 * 
		 * @param oldId
		 * @return
		 */
		private String getMigratedId(String oldId) {
			if (idMap.containsKey(oldId)) {
				return idMap.get(oldId);
			}
			String newId = "migrated" + idCounter++;
			idMap.put(oldId, newId);
			return newId;
		}

		/**
		 * @param element
		 * @return
		 */
		private int getNodeIndex(Element element) {
			Node n = element.getParentNode();
			int count = 0;
			for (int i = 0; i < n.getChildNodes().getLength(); i++) {
				Node nn = n.getChildNodes().item(i);
				if (nn.getNodeName().equals(element.getNodeName())) {
					if (nn.equals(element))
						return count;
					count++;
				}
			}
			// should never get this far
			throw new RuntimeException("Could not find frequency of node '" + element + "'");
		}

		protected Map<String,String> idMap;
		
	}
	
	public class MigrationException extends Exception {

		private static final long serialVersionUID = 1L;

		public MigrationException(Exception e) {
			super(e);
		}
		
	}
	
	public interface IamlModelMigrator {
		/**
		 * Is the given file a model of this version?
		 */
		public boolean isVersion(Document doc) throws MigrationException;
		
		/**
		 * Migrate a file to the next version.
		 */
		public InputStream migrate(Document doc, IProgressMonitor monitor)
			throws MigrationException;
	}
	
	public static final String PLUGIN_ID = "org.openiaml.model.diagram.custom";

	Object[] selection;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = null;
		if (selection instanceof IStructuredSelection) {
			this.selection = ((IStructuredSelection) selection).toArray();
		}
	}

}
