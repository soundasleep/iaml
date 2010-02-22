/**
 * 
 */
package org.openiaml.model.diagram.helpers;

/**
 * @author jmwright
 *
 */
public class GetShortcuts {
	
	/**
	 * Jevon: [issue 47] For a given list of EObjects in the model, what edges should be displayed?
	 */
	public static void getAllImportantRelationships(java.util.List<org.eclipse.emf.ecore.EObject> doneAlready, 
			java.util.List<org.eclipse.emf.ecore.EObject> elements, 
			java.util.List<org.eclipse.emf.ecore.EObject> edges, 
			org.eclipse.gmf.runtime.notation.View view,
			org.eclipse.emf.common.util.EList<? extends org.eclipse.emf.ecore.EObject> list,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		for (org.eclipse.emf.ecore.EObject e : list) {
			// NOTE: model-specific
			if (e instanceof org.openiaml.model.model.WireEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromWireEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.WireEdgesSource) e).getOutEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.WireEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromWireEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.WireEdgeDestination) e).getInEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.ExecutionEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromExecutionEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.ExecutionEdgesSource) e).getOutExecutions(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.ExecutionEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromExecutionEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.ExecutionEdgeDestination) e).getInExecutions(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.DataFlowEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromDataFlowEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.DataFlowEdgesSource) e).getOutFlows(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.DataFlowEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromDataFlowEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.DataFlowEdgeDestination) e).getInFlows(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.wires.ParameterEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.wires.ParameterEdgesSource) e).getOutParameterEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.wires.ParameterEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.wires.ParameterEdgeDestination) e).getInParameterEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.wires.ExtendsEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromExtendsEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.wires.ExtendsEdgesSource) e).getOutExtendsEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.wires.ExtendsEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromExtendsEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.wires.ExtendsEdgeDestination) e).getInExtendsEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.users.RequiresEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromRequiresEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.users.RequiresEdgesSource) e).getOutRequiresEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.users.RequiresEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromRequiresEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.users.RequiresEdgeDestination) e).getInRequiresEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.users.ProvidesEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromProvidesEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.users.ProvidesEdgesSource) e).getOutProvidesEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.users.ProvidesEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromProvidesEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.users.ProvidesEdgeDestination) e).getInProvidesEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.wires.ConstraintEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromConstraintEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.wires.ConstraintEdgesSource) e).getOutConstraintEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.wires.ConstraintEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromConstraintEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.wires.ConstraintEdgeDestination) e).getInConstraintEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.wires.ConditionEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromConditionEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.wires.ConditionEdgesSource) e).getOutConditionEdges(), registry, updater));
			}
			if (e instanceof org.openiaml.model.model.wires.ConditionEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromConditionEdges(doneAlready, edges, view, e,
						((org.openiaml.model.model.wires.ConditionEdgeDestination) e).getInConditionEdges(), registry, updater));
			}
		}
	}
	
	private static java.util.List<org.openiaml.model.model.WireEdge> getAllShortcutsFromWireEdges(
			java.util.List<org.eclipse.emf.ecore.EObject> doneAlready, 
			java.util.List<org.eclipse.emf.ecore.EObject> edges, 
			org.eclipse.gmf.runtime.notation.View view, 
			org.eclipse.emf.ecore.EObject source,
			java.util.List<org.openiaml.model.model.WireEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		java.util.List result = new java.util.LinkedList();
		
		edges.addAll(outEdges);
	
		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (org.openiaml.model.model.WireEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
			
			// additional special logic: if we have a WireEdgeDestination, get all of the incoming edges
			// that are RunInstanceWires, and render these as shortcut elements too (i.e. parameters)
			// (this covers SelectWires, etc...: see Issue 69)
			if (wire instanceof org.openiaml.model.model.WireEdgeDestination) {
				org.openiaml.model.model.WireEdgeDestination run = (org.openiaml.model.model.WireEdgeDestination) wire;
				
				if (run instanceof org.openiaml.model.model.wires.ParameterEdgesSource) {
					// specifically, if this wire is also a destination of parameters, follow these up
					org.openiaml.model.model.wires.ParameterEdgesSource prun =
						(org.openiaml.model.model.wires.ParameterEdgesSource) run;
					
					result.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, wire, prun.getOutParameterEdges(), registry, updater)); 
				}
	
				if (run instanceof org.openiaml.model.model.wires.ParameterEdgeDestination) {
					// specifically, if this wire is also a source of parameters, follow these up
					org.openiaml.model.model.wires.ParameterEdgeDestination prun =
						(org.openiaml.model.model.wires.ParameterEdgeDestination) run;
					
					result.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, wire, prun.getInParameterEdges(), registry, updater)); 
				}
				
				org.openiaml.model.model.WireEdgeDestination e = run;
				if (e instanceof org.openiaml.model.model.wires.ConditionEdgesSource) {
					// get all incoming edges
					result.addAll(getAllShortcutsFromConditionEdges(doneAlready, edges, view, e,
							((org.openiaml.model.model.wires.ConditionEdgesSource) e).getOutConditionEdges(), registry, updater));
				}
				if (e instanceof org.openiaml.model.model.wires.ConditionEdgeDestination) {
					// get all incoming edges
					result.addAll(getAllShortcutsFromConditionEdges(doneAlready, edges, view, e,
							((org.openiaml.model.model.wires.ConditionEdgeDestination) e).getInConditionEdges(), registry, updater));
				}
			} 
			
			// ParameterEdges are now done in #getAllShortcutsFromParameterEdges()
		}
	
		return result;
	}
	
	private static java.util.List<org.openiaml.model.model.wires.ParameterEdge> getAllShortcutsFromParameterEdges(
			java.util.List<org.eclipse.emf.ecore.EObject> doneAlready, 
			java.util.List<org.eclipse.emf.ecore.EObject> edges, 
			org.eclipse.gmf.runtime.notation.View view, 
			org.eclipse.emf.ecore.EObject source,
			java.util.List<org.openiaml.model.model.wires.ParameterEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		java.util.List result = new java.util.LinkedList();
		
		edges.addAll(outEdges);
	
		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (org.openiaml.model.model.wires.ParameterEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}
	
		return result;
	}
		
	private static java.util.List<org.openiaml.model.model.wires.ExtendsEdge> getAllShortcutsFromExtendsEdges(
			java.util.List<org.eclipse.emf.ecore.EObject> doneAlready, 
			java.util.List<org.eclipse.emf.ecore.EObject> edges, 
			org.eclipse.gmf.runtime.notation.View view, 
			org.eclipse.emf.ecore.EObject source,
			java.util.List<org.openiaml.model.model.wires.ExtendsEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		java.util.List result = new java.util.LinkedList();
		
		edges.addAll(outEdges);
	
		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (org.openiaml.model.model.wires.ExtendsEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}
	
		return result;
	}
		
	private static java.util.List<org.openiaml.model.model.wires.RequiresEdge> getAllShortcutsFromRequiresEdges(
			java.util.List<org.eclipse.emf.ecore.EObject> doneAlready, 
			java.util.List<org.eclipse.emf.ecore.EObject> edges, 
			org.eclipse.gmf.runtime.notation.View view, 
			org.eclipse.emf.ecore.EObject source,
			java.util.List<org.openiaml.model.model.wires.RequiresEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		java.util.List result = new java.util.LinkedList();
		
		edges.addAll(outEdges);
	
		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (org.openiaml.model.model.wires.RequiresEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}
	
		return result;
	}
		
	private static java.util.List<org.openiaml.model.model.wires.ProvidesEdge> getAllShortcutsFromProvidesEdges(
			java.util.List<org.eclipse.emf.ecore.EObject> doneAlready, 
			java.util.List<org.eclipse.emf.ecore.EObject> edges, 
			org.eclipse.gmf.runtime.notation.View view, 
			org.eclipse.emf.ecore.EObject source,
			java.util.List<org.openiaml.model.model.wires.ProvidesEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		java.util.List result = new java.util.LinkedList();
		
		edges.addAll(outEdges);
	
		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (org.openiaml.model.model.wires.ProvidesEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}
	
		return result;
	}
	
	private static java.util.List<org.openiaml.model.model.wires.ConstraintEdge> getAllShortcutsFromConstraintEdges(
			java.util.List<org.eclipse.emf.ecore.EObject> doneAlready, 
			java.util.List<org.eclipse.emf.ecore.EObject> edges, 
			org.eclipse.gmf.runtime.notation.View view, 
			org.eclipse.emf.ecore.EObject source,
			java.util.List<org.openiaml.model.model.wires.ConstraintEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		java.util.List result = new java.util.LinkedList();
		
		edges.addAll(outEdges);
	
		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (org.openiaml.model.model.wires.ConstraintEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}
	
		return result;
	}
		
	private static java.util.List<org.openiaml.model.model.wires.ConditionEdge> getAllShortcutsFromConditionEdges(
			java.util.List<org.eclipse.emf.ecore.EObject> doneAlready, 
			java.util.List<org.eclipse.emf.ecore.EObject> edges, 
			org.eclipse.gmf.runtime.notation.View view, 
			org.eclipse.emf.ecore.EObject source,
			java.util.List<org.openiaml.model.model.wires.ConditionEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		java.util.List result = new java.util.LinkedList();
		
		edges.addAll(outEdges);
	
		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (org.openiaml.model.model.wires.ConditionEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
	
				// additional special logic: if we have a WireEdgeDestination, get all of the incoming edges
				// that are RunInstanceWires, and render these as shortcut elements too (i.e. parameters)
				// (this covers SelectWires, etc...: see Issue 69)
				if (wire instanceof org.openiaml.model.model.WireEdgeDestination) {
					org.openiaml.model.model.WireEdgeDestination run = (org.openiaml.model.model.WireEdgeDestination) wire;
					
					if (run instanceof org.openiaml.model.model.wires.ParameterEdgesSource) {
						// specifically, if this wire is also a destination of parameters, follow these up
						org.openiaml.model.model.wires.ParameterEdgesSource prun =
							(org.openiaml.model.model.wires.ParameterEdgesSource) run;
						
						result.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, wire, prun.getOutParameterEdges(), registry, updater)); 
					}
	
					if (run instanceof org.openiaml.model.model.wires.ParameterEdgeDestination) {
						// specifically, if this wire is also a source of parameters, follow these up
						org.openiaml.model.model.wires.ParameterEdgeDestination prun =
							(org.openiaml.model.model.wires.ParameterEdgeDestination) run;
						
						result.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, wire, prun.getInParameterEdges(), registry, updater)); 
					}
				} 
			}
		}
	
		return result;
	}
	
	/**
	 * Jevon: [issue 47] For a list of edges, what elements are they all connected to?
	 * 
	 * NOTE: return type is model-specific
	 */
	private static java.util.List<org.openiaml.model.model.ExecutionEdge> getAllShortcutsFromExecutionEdges(
			java.util.List<org.eclipse.emf.ecore.EObject> doneAlready, 
			java.util.List<org.eclipse.emf.ecore.EObject> edges, 
			org.eclipse.gmf.runtime.notation.View view, 
			org.eclipse.emf.ecore.EObject source,
			java.util.List<org.openiaml.model.model.ExecutionEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		java.util.List result = new java.util.LinkedList();
		
		edges.addAll(outEdges);
	
		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (org.openiaml.model.model.ExecutionEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}
	
		return result;
	}
	
	/**
	 * Jevon: [issue 47] For a list of edges, what elements are they all connected to?
	 * 
	 * NOTE: return type is model-specific
	 */
	private static java.util.List<org.openiaml.model.model.DataFlowEdge> getAllShortcutsFromDataFlowEdges(
			java.util.List<org.eclipse.emf.ecore.EObject> doneAlready, 
			java.util.List<org.eclipse.emf.ecore.EObject> edges, 
			org.eclipse.gmf.runtime.notation.View view, 
			org.eclipse.emf.ecore.EObject source,
			java.util.List<org.openiaml.model.model.DataFlowEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		java.util.List result = new java.util.LinkedList();
		
		edges.addAll(outEdges);
	
		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (org.openiaml.model.model.DataFlowEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}
	
		return result;
	}
	
	/**
	 * Get the source element of the relationship, or <code>null</code> if there is none.
	 * 
	 * @param relationship
	 * @return the source element of the relationship, or <code>null</code> if none can be found
	 */
	public static org.eclipse.emf.ecore.EObject getSourceElement(org.eclipse.emf.ecore.EObject relationship) {	
		// NOTE: model-specific
		if (relationship instanceof org.openiaml.model.model.WireEdge) {
			return ((org.openiaml.model.model.WireEdge) relationship).getFrom();
		}
		if (relationship instanceof org.openiaml.model.model.ExecutionEdge) {
			return ((org.openiaml.model.model.ExecutionEdge) relationship).getFrom();
		}
		if (relationship instanceof org.openiaml.model.model.DataFlowEdge) {
			return ((org.openiaml.model.model.DataFlowEdge) relationship).getFrom();
		}
		if (relationship instanceof org.openiaml.model.model.wires.ParameterEdge) {
			return ((org.openiaml.model.model.wires.ParameterEdge) relationship).getFrom();
		}
		if (relationship instanceof org.openiaml.model.model.wires.ExtendsEdge) {
			return ((org.openiaml.model.model.wires.ExtendsEdge) relationship).getFrom();
		}
		if (relationship instanceof org.openiaml.model.model.wires.ProvidesEdge) {
			return ((org.openiaml.model.model.wires.ProvidesEdge) relationship).getFrom();
		}
		if (relationship instanceof org.openiaml.model.model.wires.RequiresEdge) {
			return ((org.openiaml.model.model.wires.RequiresEdge) relationship).getFrom();
		}
		if (relationship instanceof org.openiaml.model.model.wires.ConstraintEdge) {
			return ((org.openiaml.model.model.wires.ConstraintEdge) relationship).getFrom();
		}
		if (relationship instanceof org.openiaml.model.model.wires.ConditionEdge) {
			return ((org.openiaml.model.model.wires.ConditionEdge) relationship).getFrom();
		}
		
		// if we get this far, there may be a relationship type that
		// we're not checking for
		return null;
	}
	
	/**
	 * Get the target element of the relationship, or <code>null</code> if there is none.
	 * 
	 * @param relationship
	 * @return the target element of the relationship, or <code>null</code> if none can be found
	 */
	public static org.eclipse.emf.ecore.EObject getTargetElement(org.eclipse.emf.ecore.EObject relationship) {	
		// NOTE: model-specific

		if (relationship instanceof org.openiaml.model.model.WireEdge) {
			return ((org.openiaml.model.model.WireEdge) relationship).getTo();
		}
		if (relationship instanceof org.openiaml.model.model.ExecutionEdge) {
			return ((org.openiaml.model.model.ExecutionEdge) relationship).getTo();
		}
		if (relationship instanceof org.openiaml.model.model.DataFlowEdge) {
			return ((org.openiaml.model.model.DataFlowEdge) relationship).getTo();
		}
		if (relationship instanceof org.openiaml.model.model.wires.ParameterEdge) {
			return ((org.openiaml.model.model.wires.ParameterEdge) relationship).getTo();
		}
		if (relationship instanceof org.openiaml.model.model.wires.ExtendsEdge) {
			return ((org.openiaml.model.model.wires.ExtendsEdge) relationship).getTo();
		}
		if (relationship instanceof org.openiaml.model.model.wires.ProvidesEdge) {
			return ((org.openiaml.model.model.wires.ProvidesEdge) relationship).getTo();
		}
		if (relationship instanceof org.openiaml.model.model.wires.RequiresEdge) {
			return ((org.openiaml.model.model.wires.RequiresEdge) relationship).getTo();
		}
		if (relationship instanceof org.openiaml.model.model.wires.ConstraintEdge) {
			return ((org.openiaml.model.model.wires.ConstraintEdge) relationship).getTo();
		}
		if (relationship instanceof org.openiaml.model.model.wires.ConditionEdge) {
			return ((org.openiaml.model.model.wires.ConditionEdge) relationship).getTo();
		}
		
		// if we get this far, there may be a relationship type that
		// we're not checking for
		return null;
	}
	
	
}
