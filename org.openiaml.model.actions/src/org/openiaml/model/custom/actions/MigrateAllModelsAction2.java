package org.openiaml.model.custom.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.domain.DomainFactory;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainPackage;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.domain.DomainStoreTypes;
import org.openiaml.model.model.domain.RemoteDomainObject;
import org.openiaml.model.model.domain.SchemaEdge;
import org.openiaml.model.model.domain.SelectEdge;
import org.openiaml.model.model.users.UserStore;
import org.openiaml.model.model.wires.NewInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * A temporary migrator that uses EMF.
 * 
 * @author jmwright
 *
 */
public class MigrateAllModelsAction2 extends ProgressEnabledAction<IContainer> {

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus execute(IContainer individual, IProgressMonitor monitor) {
		try {		
			Set<IFile> models = findTargetModels(individual);
			
			monitor.beginTask("Migrating models", models.size());
			for (IFile f : models) {
				try {
					
					monitor.subTask("Migrating " + f.getName());
				
				// load into EMF
				InternetApplication model;
				try {
					model = (InternetApplication) ModelLoader.load(f);
				} catch (Exception e) {
					// log it and continue
					logStatus(errorStatus("Could not load file " + f, e));
					continue;
				}
				
				// first translate DomainObjectInstance to DomainIterator
				
				{
					// modify it
					List<EObject> allObjects = new ArrayList<EObject>();
					Iterator<EObject> it = model.eAllContents();
					while (it.hasNext()) {
						allObjects.add(it.next());
					}
					for (EObject obj : allObjects) {
						EObject container = obj.eContainer();
						
						if (obj instanceof DomainObjectInstance) {
							DomainObjectInstance doi = (DomainObjectInstance) obj;
							if (container instanceof Scope) {
								Scope frame = (Scope) container;
								
								DomainIterator di = DomainFactory.eINSTANCE.createDomainIterator();
								
								for (EAttribute attr : doi.eClass().getEAllAttributes()) {
									if (attr.equals(ModelPackage.eINSTANCE.getGeneratedElement_Id())) {
										// ignore 'id'
										continue;
									}

									di.eSet(attr, doi.eGet(attr));
								}
								
								for (EReference attr : doi.eClass().getEAllReferences()) {
									if (!attr.isMany()) {
										di.eSet(attr, doi.eGet(attr));									
									} else {
										List<Object> list = (List<Object>) doi.eGet(attr);									
										List<Object> toAdd = new ArrayList<Object>(list);
										for (Object o : toAdd) {
											((List<Object>) di.eGet(attr)).add(o);
										}
									}
								}
		
								// replace the new DomainIterator
								// and remove the current DOI
								frame.getElements().add(di);
								frame.getElements().remove(doi);
							}
							
						}
						
					}
				}
				
				// now change DomainObject to DomainSchema, and put it in the root
				// also change DomainObject to DomainSource, and put it in the root
				
				{
					// modify it
					List<EObject> allObjects = new ArrayList<EObject>();
					Iterator<EObject> it = model.eAllContents();
					while (it.hasNext()) {
						allObjects.add(it.next());
					}
					for (EObject obj : allObjects) {
						EObject container = obj.eContainer();
						
						if (obj instanceof DomainObject) {
							DomainObject doi = (DomainObject) obj;
							if (container instanceof DomainStore) {
								DomainStore frame = (DomainStore) container;
								
								DomainSchema di = DomainFactory.eINSTANCE.createDomainSchema();								
								DomainSource ds = DomainFactory.eINSTANCE.createDomainSource();
								
								// connected by SchemaEdge
								{
									SchemaEdge edge = DomainFactory.eINSTANCE.createSchemaEdge();
									edge.setFrom(ds);
									edge.setTo(di);
									ds.getSchemaEdges().add(edge);
								}

								ds.setType(frame.getType());
								ds.setFile(frame.getFile());
								
								// copy over DomainStore.permissions directly
								if (frame instanceof UserStore) {
									di.getPermissions().addAll(((UserStore) frame).getPermissions());
								}
								
								// set the type
								if (doi instanceof RemoteDomainObject) {
									RemoteDomainObject rdo = (RemoteDomainObject) doi;
									ds.setType(DomainStoreTypes.RSS2_0);
									ds.setUrl(rdo.getUrl());
									ds.setCache(3600);
								}

								for (EAttribute attr : doi.eClass().getEAllAttributes()) {
									if (attr.equals(ModelPackage.eINSTANCE.getGeneratedElement_Id())) {
										// ignore 'id'
										continue;
									}

									if (attr.equals(ModelPackage.eINSTANCE.getChangeable_DefaultValue())) {
										// drop 'defaultValue'
										continue;
									}

									if (attr.equals(ModelPackage.eINSTANCE.getChangeable_FieldValue())) {
										// drop 'fieldValue'
										continue;
									}

									if (attr.equals(ModelPackage.eINSTANCE.getChangeable_Type())) {
										// drop 'type'
										continue;
									}
									
									if (attr.equals(DomainPackage.eINSTANCE.getRemoteDomainObject_Url())) {
										// drop 'url'
										continue;
									}
									if (attr.equals(DomainPackage.eINSTANCE.getRemoteDomainObject_Age())) {
										// drop 'age'
										continue;
									}

									// find the target EAttribute
									boolean found = false;
									for (EAttribute a2 : di.eClass().getEAllAttributes()) {
										if (a2.equals(attr)) {
											di.eSet(a2, doi.eGet(attr));
											found = true;
											break;
										}
									}
									
									if (!found) {
										System.out.println(doi.eClass().getEAllAttributes());
										System.out.println("--");
										System.out.println(di.eClass().getEAllAttributes());
										throw new RuntimeException(di.eClass() + " does not contain " + attr);
									}
									
								}
								
								for (EReference attr : doi.eClass().getEAllReferences()) {
									// ignore empty references
									if (attr.isMany() && ((List) doi.eGet(attr)).isEmpty())
										continue;
									if (!attr.isMany() && doi.eGet(attr) == null)
										continue;
									
									// find the target EReference
									boolean found = false;
									for (EReference a2 : di.eClass().getEAllReferences()) {
										if (a2.equals(attr) || matches(attr, a2)) {
											if (!attr.isMany()) {
												di.eSet(a2, doi.eGet(attr));									
											} else {
												List<Object> list = (List<Object>) doi.eGet(attr);									
												List<Object> toAdd = new ArrayList<Object>(list);
												for (Object o : toAdd) {
													Object targetList = di.eGet(a2);
													if (attr.equals(ModelPackage.eINSTANCE.getWireDestination_InWires())) {
														if (o instanceof Wire) {
															((List<Object>) targetList).add(o);
														}
													} else if (attr.equals(ModelPackage.eINSTANCE.getWireSource_OutWires())) {
														if (o instanceof Wire) {
															((List<Object>) targetList).add(o);
														}
													} else {
														((List<Object>) targetList).add(o);
													}
												}
											}
											
											found = true;
											break;
										}
									}
									
									if (!found) {
										System.out.println(doi.eClass().getEAllReferences());
										System.out.println("--");
										System.out.println(di.eClass().getEAllReferences());
										throw new RuntimeException(di.eClass().getName() + " does not contain " + attr.getName() + " from " + doi.eClass().getName());
									}
									
								}
		
								// replace the new DomainIterator
								// and remove the current DOI
								
								frame.getChildren().remove(doi);
								model.getSchemas().add(di);
								model.getSources().add(ds);
							}
							
						}
						
					}
				}
				
				// intermediary save				
				model.eResource().save( ModelLoader.getSaveOptions() );
				
				// merge SelectWire into the DomainIterator
				
				{
					// modify it
					List<EObject> allObjects = new ArrayList<EObject>();
					Iterator<EObject> it = model.eAllContents();
					while (it.hasNext()) {
						allObjects.add(it.next());
					}
					for (EObject obj : allObjects) {
						EObject container = obj.eContainer();
						
						if (obj instanceof SelectWire) {
							SelectWire wire = (SelectWire) obj;
							
							DomainIterator to;
							if (wire.getTo() instanceof DomainAttributeInstance) {
								// if its selecting a DomainAttributeInstance, we will
								// create a DomainIterator explicitly
								DomainAttributeInstance dai = (DomainAttributeInstance) wire.getTo();
								if (dai.eContainer() instanceof Scope) {
									Scope cc = (Scope) dai.eContainer();
									
									to = DomainFactory.eINSTANCE.createDomainIterator();
									to.setName("Iterator for " + dai.getName());
									to.getAttributes().add(dai);
									cc.getElements().add(to);
								} else {
									throw new RuntimeException("DomainAttributeInstance was not contained within a Scope: " + dai + ", " + dai.eContainer());
								}
							} else {
								if (!(wire.getTo() instanceof DomainIterator)) {
									throw new RuntimeException("wire.to is not a DomainIterator: " + wire.getTo());
								}
								
								to = (DomainIterator) wire.getTo();
							}
							
							if (!(wire.getFrom() instanceof DomainSchema)) {
								throw new RuntimeException("wire.from is not a DomainSchema: " + wire.getFrom());
							}
							
							DomainSchema from = (DomainSchema) wire.getFrom();
							DomainIterator di = to;
							SelectWire doi = wire;
							
							// derive the Source
							DomainSource source = from.getInSchemas().get(0).getFrom();
							if (source == null) {
								throw new RuntimeException("Edge unexpectedly was null: " + from);
							}
							
							// create a SourceEdge to connect the two
							{
								SelectEdge edge = DomainFactory.eINSTANCE.createSelectEdge();
								edge.setTo(source);
								edge.setFrom(to);
								source.getSelectEdges().add(edge);
							}
							
							// put this SelectWire information into the iterator
							to.setQuery(wire.getQuery());
							to.setLimit(wire.getLimit());
							to.setOrderAscending(wire.isOrderAscending());
							to.setOrderBy(wire.getOrderBy());
							
							wire.setFrom(null);
							wire.setTo(null);
							wire.setOrderBy(null);
				
							for (EAttribute attr : doi.eClass().getEAllAttributes()) {
								if (attr.equals(ModelPackage.eINSTANCE.getGeneratedElement_Id())) {
									// ignore 'id'
									continue;
								}
								
								if (attr.equals(WiresPackage.eINSTANCE.getSelectWire_Limit())) {
									// ignore 'limit'
									continue;
								}

								if (attr.equals(WiresPackage.eINSTANCE.getSelectWire_Query())) {
									// ignore 'query'
									continue;
								}

								if (attr.equals(WiresPackage.eINSTANCE.getSelectWire_OrderAscending())) {
									// ignore 'orderAscending'
									continue;
								}

								// find the target EAttribute
								boolean found = false;
								for (EAttribute a2 : di.eClass().getEAllAttributes()) {
									if (a2.equals(attr)) {
										di.eSet(a2, doi.eGet(attr));
										found = true;
										break;
									}
								}
								
								if (!found) {
									System.out.println(doi.eClass().getEAllAttributes());
									System.out.println("--");
									System.out.println(di.eClass().getEAllAttributes());
									throw new RuntimeException(di.eClass() + " does not contain " + attr);
								}
								
							}
							
							for (EReference attr : doi.eClass().getEAllReferences()) {
								if (attr.equals(WiresPackage.eINSTANCE.getSelectWire_OrderBy())) {
									// ignore 'orderBy'
									continue;
								}
								
								if (attr.equals(ModelPackage.eINSTANCE.getWire_From())) {
									// ignore 'from'
									continue;
								}

								if (attr.equals(ModelPackage.eINSTANCE.getWire_To())) {
									// ignore 'to'
									continue;
								}
								
								// ignore empty references
								if (attr.isMany() && ((List) doi.eGet(attr)).isEmpty())
									continue;
								if (!attr.isMany() && doi.eGet(attr) == null)
									continue;
								
								// find the target EReference
								boolean found = false;
								for (EReference a2 : di.eClass().getEAllReferences()) {
									if (a2.equals(attr) || matches(attr, a2)) {
										if (!attr.isMany()) {
											di.eSet(a2, doi.eGet(attr));									
										} else {
											List<Object> list = (List<Object>) doi.eGet(attr);									
											List<Object> toAdd = new ArrayList<Object>(list);
											for (Object o : toAdd) {
												Object targetList = di.eGet(a2);
												((List<Object>) targetList).add(o);
											}
										}
										
										found = true;
										break;
									}
								}
								
								if (!found) {
									System.out.println(doi.eClass().getEAllReferences());
									System.out.println("--");
									System.out.println(di.eClass().getEAllReferences());
									throw new RuntimeException(di.eClass().getName() + " does not contain " + attr.getName() + " from " + doi.eClass().getName());
								}
								
							}
	
							// replace the new DomainIterator
							// and remove the current DOI
							
							
							if (wire.eContainer() instanceof ContainsWires) {
								ContainsWires cw = (ContainsWires) wire.eContainer();
								cw.getWires().remove(wire);
							} else {
								throw new RuntimeException("Wire container was not a ContainsWires: " + wire.eContainer());
							}
					
							
						}
						
					}
				}
				
				// merge NewInstanceWire into the DomainIterator
				
				{
					// modify it
					List<EObject> allObjects = new ArrayList<EObject>();
					Iterator<EObject> it = model.eAllContents();
					while (it.hasNext()) {
						allObjects.add(it.next());
					}
					for (EObject obj : allObjects) {
						EObject container = obj.eContainer();
						
						if (obj instanceof NewInstanceWire) {
							NewInstanceWire wire = (NewInstanceWire) obj;
							
							DomainIterator to;
							if (wire.getTo() instanceof DomainAttributeInstance) {
								// if its selecting a DomainAttributeInstance, we will
								// create a DomainIterator explicitly
								DomainAttributeInstance dai = (DomainAttributeInstance) wire.getTo();
								if (dai.eContainer() instanceof Scope) {
									Scope cc = (Scope) dai.eContainer();
									
									to = DomainFactory.eINSTANCE.createDomainIterator();
									to.setName("Iterator for " + dai.getName());
									to.getAttributes().add(dai);
									cc.getElements().add(to);
								} else {
									throw new RuntimeException("DomainAttributeInstance was not contained within a Scope: " + dai + ", " + dai.eContainer());
								}
							} else {
								if (!(wire.getTo() instanceof DomainIterator)) {
									throw new RuntimeException("wire.to is not a DomainIterator: " + wire.getTo());
								}
								
								to = (DomainIterator) wire.getTo();
							}
							
							if (!(wire.getFrom() instanceof DomainSchema)) {
								throw new RuntimeException("wire.from is not a DomainSchema: " + wire.getFrom());
							}
							
							DomainSchema from = (DomainSchema) wire.getFrom();
							DomainIterator di = to;
							NewInstanceWire doi = wire;
							
							// derive the Source
							DomainSource source = from.getInSchemas().get(0).getFrom();
							if (source == null) {
								throw new RuntimeException("Edge unexpectedly was null: " + from);
							}
							
							// create a SourceEdge to connect the two
							{
								SelectEdge edge = DomainFactory.eINSTANCE.createSelectEdge();
								edge.setTo(source);
								edge.setFrom(to);
								source.getSelectEdges().add(edge);
							}
							
							// put this SelectWire information into the iterator
							to.setQuery("new");
							
							wire.setFrom(null);
							wire.setTo(null);
				
							for (EAttribute attr : doi.eClass().getEAllAttributes()) {
								if (attr.equals(ModelPackage.eINSTANCE.getGeneratedElement_Id())) {
									// ignore 'id'
									continue;
								}
								
								// find the target EAttribute
								boolean found = false;
								for (EAttribute a2 : di.eClass().getEAllAttributes()) {
									if (a2.equals(attr)) {
										di.eSet(a2, doi.eGet(attr));
										found = true;
										break;
									}
								}
								
								if (!found) {
									System.out.println(doi.eClass().getEAllAttributes());
									System.out.println("--");
									System.out.println(di.eClass().getEAllAttributes());
									throw new RuntimeException(di.eClass() + " does not contain " + attr);
								}
								
							}
							
							for (EReference attr : doi.eClass().getEAllReferences()) {
								if (attr.equals(ModelPackage.eINSTANCE.getWire_From())) {
									// ignore 'from'
									continue;
								}

								if (attr.equals(ModelPackage.eINSTANCE.getWire_To())) {
									// ignore 'to'
									continue;
								}
								
								// ignore empty references
								if (attr.isMany() && ((List) doi.eGet(attr)).isEmpty())
									continue;
								if (!attr.isMany() && doi.eGet(attr) == null)
									continue;
								
								// find the target EReference
								boolean found = false;
								for (EReference a2 : di.eClass().getEAllReferences()) {
									if (a2.equals(attr) || matches(attr, a2)) {
										if (!attr.isMany()) {
											di.eSet(a2, doi.eGet(attr));									
										} else {
											List<Object> list = (List<Object>) doi.eGet(attr);									
											List<Object> toAdd = new ArrayList<Object>(list);
											for (Object o : toAdd) {
												Object targetList = di.eGet(a2);
												((List<Object>) targetList).add(o);
											}
										}
										
										found = true;
										break;
									}
								}
								
								if (!found) {
									System.out.println(doi.eClass().getEAllReferences());
									System.out.println("--");
									System.out.println(di.eClass().getEAllReferences());
									throw new RuntimeException(di.eClass().getName() + " does not contain " + attr.getName() + " from " + doi.eClass().getName());
								}
								
							}
	
							// replace the new DomainIterator
							// and remove the current DOI
							
							
							if (wire.eContainer() instanceof ContainsWires) {
								ContainsWires cw = (ContainsWires) wire.eContainer();
								cw.getWires().remove(wire);
							} else {
								throw new RuntimeException("Wire container was not a ContainsWires: " + wire.eContainer());
							}
					
							
						}
						
					}
				}
				
				// remove all DomainStores
				model.getDomainStores().clear();
				
				// finally save it				
				model.eResource().save( ModelLoader.getSaveOptions() );
				
				} catch (Exception e) {
					return errorStatus("Could not load '" + f + "': " + e.getMessage(), e);
				}
				
				// now make sure we can reload the same model
				try {
					ModelLoader.load(f);
				} catch (Exception e) {
					logStatus(errorStatus("Could not reload migrated model '" + f + "': " + e.getMessage(), e));
				}
				
				monitor.worked(1);
				
			}
			
		} catch (CoreException e) {
			return errorStatus("Core exception: " + e.getMessage(), e);
		} finally {
			monitor.done();
		}
		
		return Status.OK_STATUS;

	}

	/**
	 * Does the given reference match the new reference?
	 * 
	 * @return
	 */
	private boolean matches(EReference old, EReference target) {
		if (old.equals(ModelPackage.eINSTANCE.getDomainObject_Attributes())) {
			if (target.equals(DomainPackage.eINSTANCE.getDomainSchema_Attributes())) {
				// DomainObject.attributes -> DomainSchema.attributes
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @param eAllContents
	 * @return
	 */
	private Iterable<EObject> toIterable(TreeIterator<EObject> it) {
		List<EObject> result = new ArrayList<EObject>();
		while (it.hasNext()) {
			result.add(it.next());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IContainer individual, String message) {
		return "Could not migrate models in '" + individual + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Migrating all contained models";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#getSelection(java.lang.Object[])
	 */
	@Override
	public List<IContainer> getSelection(Object[] selection) {
		final List<IContainer> ifiles = new ArrayList<IContainer>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IContainer) {
					ifiles.add((IContainer) o);
				}
			}
		}
		
		return ifiles;
	}

	/**
	 * Find all models in the given resource, and sub-folders.
	 * 
	 * @param project2
	 * @return
	 * @throws CoreException 
	 */
	private Set<IFile> findTargetModels(IResource res) throws CoreException {
		HashSet<IFile> result = new HashSet<IFile>();
		
		if (res instanceof IContainer) {
			IContainer c = (IContainer) res;
			
			for (IResource r : c.members()) {
				result.addAll(findTargetModels(r));
			}
		}
		
		if (res instanceof IFile) {
			IFile f = (IFile) res;
			if (f.getFileExtension() != null && f.getFileExtension().equals("iaml")) {
				// found one
				result.add(f);
			}
		}
		
		return result;
	}
	
}
