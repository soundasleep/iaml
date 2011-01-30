package org.openiaml.model.diagram.custom.actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.diagram.ui.render.util.DiagramImageUtils;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.openiaml.model.helpers.IamlBreadcrumb;
import org.openiaml.model.helpers.IamlBreadcrumb.BreadcrumbLinker;


/**
 * Export all of the images in a model diagram to multiple image files, and
 * save all of these images as clickable HTML.
 * 
 * @author jmwright
 *
 */
public class ExportToClickableHtml extends ExportImagePartsAction {

	/**
	 * We want to render much more of the entire model; we
	 * render up to 100 images.
	 */
	@Override
	public int getMaxImages() {
		return 100;
	}

	/**
	 * Keeps track of (source edit parts -> destination files)
	 */
	private Map<DiagramEditPart,IPath> partDestinationMap;
	
	private Map<DiagramEditPart,Rectangle> partRectangleMap;
	
	private Map<DiagramEditPart,EObject> partEObjectMap;
	
	/**
	 * Stores a list of all the resolved EObjects of children
	 */
	private Map<DiagramEditPart,List<RenderedChildInformation>> partChildrenListMap = 
		new HashMap<DiagramEditPart,List<RenderedChildInformation>>();
	
	public class RenderedChildInformation {
		private EObject resolvedObject;
		private Rectangle bounds;
		private String tooltip;
		
		public RenderedChildInformation(EObject resolvedObject, Rectangle bounds, String tooltip) {
			super();
			this.resolvedObject = resolvedObject;
			this.bounds = bounds;
			this.tooltip = tooltip;
		}
		
		public EObject getResolvedObject() {
			return resolvedObject;
		}
		public Rectangle getBounds() {
			return bounds;
		}
		public String getTooltip() {
			return tooltip;
		}
		
	}
	
	public class MyCopyToImageUtil extends CopyToImageUtil {

		@Override
		public DiagramGenerator copyToImage(DiagramEditPart part,
				IPath destination, ImageFileFormat format,
				IProgressMonitor monitor) throws CoreException {
			DiagramGenerator result = super.copyToImage(part, destination, format, monitor);
			
			// keep track of the part -> destination
			partDestinationMap.put(part, destination);
			partEObjectMap.put(part, part.resolveSemanticElement());
			
			// make a copy of the image rectangle used
			// we do this before we close the editor, so that we don't lose the editor instance/children
			Rectangle rect = DiagramImageUtils.calculateImageRectangle(
					part.getPrimaryEditParts(),
					getImageMargin(part), 
					getEmptyImageSize(part));
			partRectangleMap.put(part, rect);
			
			// save all the children now
			List<RenderedChildInformation> children = new ArrayList<RenderedChildInformation>();
			for (Object o : part.getChildren()) {
				if (o instanceof GraphicalEditPart) {
					GraphicalEditPart gep = (GraphicalEditPart) o;
					
					RenderedChildInformation info = new RenderedChildInformation(
							gep.resolveSemanticElement(), 
							gep.getContentPane().getBounds(),
							IamlBreadcrumb.getEObjectBreadcrumbString(gep.resolveSemanticElement()) );
					children.add(info);
				}
			}
			partChildrenListMap.put(part, children);
			
			return result;
		}
		
	}

	/**
	 * We extend CopyToImageUtil to also keep a track of all
	 * clickable elements in the current edit part.
	 */
	@Override
	protected CopyToImageUtil getCopyToImageUtil() {
		return new MyCopyToImageUtil();
	}

	private IFile targetDiagram;
	
	/**
	 * Once we have finished exporting all of the images, we go through
	 * every saved node:
	 * 
	 * <ol>
	 * 	<li>Export a HTML file for the current part</li>
	 *  <li>Find all children</li>
	 *  <li>Find if any exported image parts correspond to the target element</li>
	 *  <li>If so, write out a clickable map region corresponding to the target HTML</li>
	 * </ol>
	 * 
	 * @param container the container to place the exported images and HTML into, or <code>null</code> to place in the same container as <code>targetDiagram</code>
	 */
	@Override
	public void doExport(IFile targetDiagram, IContainer container, IProgressMonitor monitor)
			throws ExportImageException {
		
		monitor.beginTask("Exporting to HTML", 105);
		this.targetDiagram = targetDiagram;
		
		// initialise maps
		partDestinationMap = new HashMap<DiagramEditPart,IPath>();
		partRectangleMap = new HashMap<DiagramEditPart,Rectangle>();
		partEObjectMap = new HashMap<DiagramEditPart,EObject>();
		
		// do parent
		super.doExport(targetDiagram, container, new SubProgressMonitor(monitor, 70));
		
		IProgressMonitor finalMonitor = new SubProgressMonitor(monitor, 30);
		finalMonitor.beginTask("Writing HTML files", partDestinationMap.size() * 2);
		
		// get all image parts
		for (DiagramEditPart root : partDestinationMap.keySet()) {
			if (monitor.isCanceled())
				return;
			
			IPath destination = partDestinationMap.get(root);
			IPath htmlDestination = getHTMLDestinationFor(destination);
			EObject resolved = partEObjectMap.get(root);
			finalMonitor.subTask("Writing " + htmlDestination.lastSegment());
			
			// construct the HTML
			StringBuffer html = new StringBuffer();
			html.append(getHTMLHeader(targetDiagram, resolved))
				.append(getBreadcrumb(resolved))
				.append(getImageTag(destination))
				.append(getClickableMap(root))
				.append(getHTMLFooter());
			finalMonitor.worked(1);
			
			// export
			try {
				File destFile = new File(htmlDestination.toOSString());
				FileWriter fw = new FileWriter(destFile);
				fw.write(html.toString());
				fw.close();
			} catch (IOException e) {
				throw new ExportImageException(e);
			}
			finalMonitor.worked(1);
		}
		
		finalMonitor.done();
		
		// once finished, refresh parent (folder, project)
		try {
			targetDiagram.getParent().refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(monitor, 5));
		} catch (CoreException e) {
			throw new ExportImageException(e);
		}

		// all finished
		monitor.done();
	}
	
	/**
	 * We want to place all generated files into a new folder.
	 * 
	 * @param container the container to place images into
	 * @throws ExportImageException 
	 */
	@Override
	protected IPath generateImageDestination(IContainer container) throws ExportImageException {

		// get default
		IPath source = super.generateImageDestination(container);
		
		// does the folder exist?
		// get the folder name from the diagram, e.g. Foo.iaml_diagram -> "Foo"
		String newFolderName = targetDiagram.getFullPath().removeFileExtension().lastSegment();
		
		IPath targetFolder = source.removeLastSegments(1).append(newFolderName).addTrailingSeparator();
		File f = new File(targetFolder.toOSString());
		if (!f.exists()) {
			if (!f.mkdir()) {
				// could not create directory
				throw new ExportImageException("Could not mkdir: '" + f + "'");
			}
		}
		
		// create the new path
		return targetFolder.append(source.lastSegment());

	}

	/**
	 * Allows linking of breadcrumb elements to target pages, if they
	 * have been rendered.
	 * 
	 * @author jmwright
	 *
	 */
	public class HtmlBreadcrumbLinker extends BreadcrumbLinker {

		private Map<DiagramEditPart, IPath> partDestinationMap;

		public HtmlBreadcrumbLinker(
				Map<DiagramEditPart, IPath> partDestinationMap) {
			this.partDestinationMap = partDestinationMap;
		}

		@Override
		public String link(EObject object, String s) {
			// does the given EObject resolve to any target in the 
			// rendered map?
			
			for (DiagramEditPart part : partDestinationMap.keySet()) {
				// try and prevent an NPE caused by internal getChangeRecorder() returning null
				if (part.getEditingDomain() instanceof InternalTransactionalEditingDomain) {
					if (((InternalTransactionalEditingDomain) part.getEditingDomain()).getChangeRecorder() == null) {
						continue;
					}
				}

				EObject resolved;
				try {
					resolved = part.resolveSemanticElement();
				} catch (RuntimeException e) {
					throw new RuntimeException("Could not resolve element for part '" + part + "': " + e.getMessage(), e);
				}
				
				if (EcoreUtil.equals(resolved, object)) {
					// it maps
					return new StringBuffer()
						.append("<a href=\"")
						.append(getHTMLDestinationFor(partDestinationMap.get(part)).lastSegment())
						.append("\">")
						.append(s)
						.append("</a>").toString();
				}
			}
			
			// return default
			return s;
		}
		
	}
	
	/**
	 * Get a linkable breadcrumb.
	 * 
	 * @param destination
	 * @return
	 */
	private String getBreadcrumb(EObject resolved) {
		return new StringBuffer()
		.append("<h2>")
		.append(IamlBreadcrumb.breadcrumb(resolved, 4, new HtmlBreadcrumbLinker(partDestinationMap)))
		.append("</h2>\n")
		.toString();
	}

	/**
	 * For a given path (e.g. "foo.png"), get the HTML file
	 * that will render this (e.g. "foo.html").
	 * 
	 * @param source
	 * @return
	 */
	protected IPath getHTMLDestinationFor(IPath source) {
		return source.removeFileExtension().addFileExtension("html");
	}
	
	/**
	 * Construct the map. 
	 * 
	 * @return
	 */
	protected String getClickableMap(DiagramEditPart root) {
		StringBuffer buf = new StringBuffer();
		buf.append("<map name=\"generated_map\">\n");

		// we need to find out the source rect that was taken
		// to construct the image, so we can base the bounds
		// on this minimal bound
		Rectangle rect = partRectangleMap.get(root);
		
		// for all the saved children
		for (RenderedChildInformation child : partChildrenListMap.get(root)) {
			
			// do any edit parts link up to this one?
			DiagramEditPart found = null;
			for (DiagramEditPart target : partEObjectMap.keySet()) {
				if (EcoreUtil.equals(partEObjectMap.get(target), child.getResolvedObject())) {
					found = target;
					break;
				}
			}
			
			if (found != null) {
				// found a link
				// assume the link is square
				
				Rectangle bounds = child.getBounds();
				IPath dest = partDestinationMap.get(found);
				
				buf.append("<area shape=\"rect\" coords=\"")
					.append(bounds.x-rect.x).append(",")
					.append(bounds.y-rect.y).append(",")
					.append(bounds.width+bounds.x-rect.x).append(",")
					.append(bounds.height+bounds.y-rect.y)
					.append("\" href=\"")
					.append(escapeHTML(getHTMLDestinationFor(dest).lastSegment()))
					.append("\" alt=\"")
					.append(escapeHTML(child.getTooltip()))
					.append("\" title=\"")
					.append(escapeHTML(child.getTooltip()))
					.append("\" />\n");
			} else {
				// we can still add tooltip information anyway

				Rectangle bounds = child.getBounds();
				
				buf.append("<area shape=\"rect\" coords=\"")
					.append(bounds.x-rect.x).append(",")
					.append(bounds.y-rect.y).append(",")
					.append(bounds.width+bounds.x-rect.x).append(",")
					.append(bounds.height+bounds.y-rect.y)
					.append("\" alt=\"")
					.append(escapeHTML(child.getTooltip()))
					.append("\" title=\"")
					.append(escapeHTML(child.getTooltip()))
					.append("\" />\n");
			
			}
			
		}
		
		buf.append("</map>");
		return buf.toString();
	}

	/**
	 * Escape the given HTML.
	 */
	protected String escapeHTML(String s) {
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}
	
	/**
	 * Get the HTML title for the given object.
	 * 
	 * @param targetDiagram the source (root) diagram
	 * @param part the current EObject to render a title for
	 * @return
	 */
	protected String getHTMLTitle(IFile targetDiagram, EObject part) {
		String filename = targetDiagram.getProjectRelativePath().lastSegment();
		return new StringBuffer()
			.append(escapeHTML(filename))
			.append(" : ")
			.append(escapeHTML(IamlBreadcrumb.getEObjectBreadcrumbString(part)))
			.toString();
	}
	
	/**
	 * Get the HTML header.
	 * 
	 * @param targetDiagram the source (root) diagram
	 * @param part the current part to render a title for
	 * @return
	 */
	protected String getHTMLHeader(IFile targetDiagram, EObject part) {
		return new StringBuffer()
			.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n")
			.append("\"http://www.w3.org/TR/html4/loose.dtd\">\n")
			.append("<html><head>\n")
			.append("<title>")
			.append(getHTMLTitle(targetDiagram, part))
			.append("</title>\n")
			.append("<style>\n")
			.append("body { font-family: Arial; font-size: 1em; }\n")
			.append("h2 { font-weight: normal; font-size: 1em; }\n")
			.append("h2, h2 a { color: #666; }\n")
			.append("h2 a:hover { color: #333; }\n")
			.append("img { border: 1px solid #ccc; }\n")
			.append("</style>\n")
			.append("</head><body>\n")
			.toString();
	}
	
	protected String getHTMLFooter() {
		return "</body></html>\n";
	}
	
	protected String getImageTag(IPath file) {
		return "<img src=\"" + file.lastSegment() + "\" usemap=\"#generated_map\">\n";
	}

	/**
	 * Copied from DiagramGenerator.
	 */
	private static final int DEFAULT_IMAGE_MARGIN_PIXELS = 10;
	private static final int DEFAULT_EMPTY_IMAGE_SIZE_PIXELS = 100;
	
	/**
	 * Copied from DiagramGenerator.
	 */
	private int getImageMargin(DiagramEditPart part) {
		return getMapMode(part).DPtoLP(DEFAULT_IMAGE_MARGIN_PIXELS);
	}

	/**
	 * Copied from DiagramGenerator.
	 */
	private Dimension getEmptyImageSize(DiagramEditPart part) {
		return (Dimension) getMapMode(part).DPtoLP(new Dimension(
				DEFAULT_EMPTY_IMAGE_SIZE_PIXELS,
				DEFAULT_EMPTY_IMAGE_SIZE_PIXELS));
	}

	/**
	 * Copied from DiagramGenerator.
	 */
	protected IMapMode getMapMode(DiagramEditPart part) {
		return MapModeUtil.getMapMode(part.getFigure());
	}

}
