/**
 * 
 */
package org.openiaml.simplegmf.codegen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openarchitectureware.xpand2.output.FileHandle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Implements an XML beautifer.
 * 
 * @author Jevon
 *
 */
public class XMLBeautifier implements org.openarchitectureware.xpand2.output.PostProcessor {

	/**
	 * Load an XML document from a file.
	 */
	private Document loadDocument(File f) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		return loadDocument( new FileInputStream(f) );
	}
	
	/**
	 * Load an XML document from an input source.
	 */
	private Document loadDocument(InputStream source) throws ParserConfigurationException, SAXException, IOException {
		// load the model version
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(source);
		
		// done
		source.close();
		
		return doc;
	}
	
	private void removeContainedText(Node doc) {
		NodeList nl = doc.getChildNodes();
		List<Text> toRemove = new ArrayList<Text>();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if (n instanceof Text) {
				Text t = (Text) n;
				if (t.getNodeValue() == null || t.getNodeValue().trim().isEmpty()) {
					toRemove.add(t);
				}
			} else if (n instanceof Element) {
				removeContainedText(n);
			}
		}
		
		for (Text t : toRemove) {
			doc.removeChild(t);
		}
	}
	
	/**
	 * Try saving an XML document.
	 */
	private void saveDocument(Document doc, File target) throws IOException, TransformerException {
        TransformerFactory transfac = TransformerFactory.newInstance();
        transfac.setAttribute("indent-number", 2);
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");	// omit '<?xml version="1.0"?>'
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        
        // TODO clean this up into a piped input/output stream setup?
		FileWriter sw = new FileWriter(target);
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc);
        trans.transform(source, result);
        sw.close();
	}
	
	/**
	 * Actually implements the beautification process.
	 * 
	 * @see org.openarchitectureware.xpand2.output.PostProcessor#afterClose(org.openarchitectureware.xpand2.output.FileHandle)
	 */
	@Override
	public void afterClose(FileHandle fh) {
		System.out.println(fh.getTargetFile());
		
		// only process XML files
		String name = fh.getTargetFile().getName().toLowerCase();
		if (name.endsWith(".xml") || name.endsWith(".xsd") ||
				name.endsWith(".gmfgraph") || name.endsWith(".gmfmap") ||
				name.endsWith(".gmfgen") || name.endsWith(".gmftool") ||
				name.endsWith(".simplegmf") || name.endsWith(".ecore") ||
				name.endsWith(".xmi")) {
			
			try {
				Document doc = loadDocument(fh.getTargetFile());
				removeContainedText(doc);
				saveDocument(doc, fh.getTargetFile());
				System.out.println("saved " + fh.getTargetFile());
			} catch (IOException e) {
				throw new RuntimeException("Could not read target file " + fh.getTargetFile() + ": " + e.getMessage(), e);
			} catch (ParserConfigurationException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (SAXException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (TransformerException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
	}

	@Override
	public void beforeWriteAndClose(FileHandle impl) {
		// empty
	}

}
