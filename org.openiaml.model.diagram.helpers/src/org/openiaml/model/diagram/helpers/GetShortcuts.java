/**
 *
 */
package org.openiaml.model.diagram.helpers;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.openiaml.model.model.Action;
import org.openiaml.model.model.ActionEdgeSource;
import org.openiaml.model.model.ComplexTerm;
import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Function;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.SimpleCondition;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.domain.SchemaEdge;
import org.openiaml.model.model.domain.SelectEdge;
import org.openiaml.model.model.operations.DataFlowEdge;
import org.openiaml.model.model.operations.DataFlowEdgeDestination;
import org.openiaml.model.model.operations.DataFlowEdgesSource;
import org.openiaml.model.model.operations.ExecutionEdge;
import org.openiaml.model.model.operations.ExecutionEdgeDestination;
import org.openiaml.model.model.operations.ExecutionEdgesSource;
import org.openiaml.model.model.users.ProvidesEdgeDestination;
import org.openiaml.model.model.users.ProvidesEdgesSource;
import org.openiaml.model.model.users.RequiresEdgeDestination;
import org.openiaml.model.model.users.RequiresEdgesSource;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.ConstraintEdge;
import org.openiaml.model.model.wires.ConstraintEdgeDestination;
import org.openiaml.model.model.wires.ConstraintEdgesSource;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.ProvidesEdge;
import org.openiaml.model.model.wires.RequiresEdge;

/**
 * @author jmwright
 *
 */
public class GetShortcuts {

	/**
	 * Jevon: [issue 47] For a given list of EObjects in the model, what edges should be displayed?
	 */
	public static void getAllImportantRelationships(List<EObject> doneAlready,
			List<EObject> elements,
			List<EObject> edges,
			View view,
			EList<? extends EObject> list,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		for (EObject e : list) {
			// NOTE: model-specific
			if (e instanceof WireSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromWires(doneAlready, edges, view, e,
						((WireSource) e).getOutWires(), registry, updater));
			}
			if (e instanceof WireDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromWires(doneAlready, edges, view, e,
						((WireDestination) e).getInWires(), registry, updater));
			}
			if (e instanceof ActionEdgeSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromECARules(doneAlready, edges, view, e,
						((ActionEdgeSource) e).getListeners(), registry, updater));
			}
			if (e instanceof Action) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromECARules(doneAlready, edges, view, e,
						((Action) e).getRules(), registry, updater));
			}
			if (e instanceof ExecutionEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromExecutionEdges(doneAlready, edges, view, e,
						((ExecutionEdgesSource) e).getOutExecutions(), registry, updater));
			}
			if (e instanceof ExecutionEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromExecutionEdges(doneAlready, edges, view, e,
						((ExecutionEdgeDestination) e).getInExecutions(), registry, updater));
			}
			if (e instanceof DataFlowEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromDataFlowEdges(doneAlready, edges, view, e,
						((DataFlowEdgesSource) e).getOutFlows(), registry, updater));
			}
			if (e instanceof DataFlowEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromDataFlowEdges(doneAlready, edges, view, e,
						((DataFlowEdgeDestination) e).getInFlows(), registry, updater));
			}
			if (e instanceof ParameterEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, e,
						((ParameterEdgesSource) e).getOutParameterEdges(), registry, updater));
			}
			if (e instanceof ParameterEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, e,
						((ParameterEdgeDestination) e).getInParameterEdges(), registry, updater));
			}
			if (e instanceof ExtendsEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromExtendsEdges(doneAlready, edges, view, e,
						((ExtendsEdgesSource) e).getOutExtendsEdges(), registry, updater));
			}
			if (e instanceof ExtendsEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromExtendsEdges(doneAlready, edges, view, e,
						((ExtendsEdgeDestination) e).getInExtendsEdges(), registry, updater));
			}
			if (e instanceof RequiresEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromRequiresEdges(doneAlready, edges, view, e,
						((RequiresEdgesSource) e).getOutRequiresEdges(), registry, updater));
			}
			if (e instanceof RequiresEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromRequiresEdges(doneAlready, edges, view, e,
						((RequiresEdgeDestination) e).getInRequiresEdges(), registry, updater));
			}
			if (e instanceof ProvidesEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromProvidesEdges(doneAlready, edges, view, e,
						((ProvidesEdgesSource) e).getOutProvidesEdges(), registry, updater));
			}
			if (e instanceof ProvidesEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromProvidesEdges(doneAlready, edges, view, e,
						((ProvidesEdgeDestination) e).getInProvidesEdges(), registry, updater));
			}
			if (e instanceof ConstraintEdgesSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromConstraintEdges(doneAlready, edges, view, e,
						((ConstraintEdgesSource) e).getOutConstraintEdges(), registry, updater));
			}
			if (e instanceof ConstraintEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromConstraintEdges(doneAlready, edges, view, e,
						((ConstraintEdgeDestination) e).getInConstraintEdges(), registry, updater));
			}
			if (e instanceof Function) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromComplexTerms(doneAlready, edges, view, e,
						((Function) e).getConditioned(), registry, updater));
			}
			if (e instanceof ConditionEdgeDestination) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromComplexTerms(doneAlready, edges, view, e,
						((ConditionEdgeDestination) e).getConditions(), registry, updater));
			}
			if (e instanceof DomainSchema) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromSchemaEdges(doneAlready, edges, view, e,
						((DomainSchema) e).getInSchemas(), registry, updater));
			}
			if (e instanceof DomainSource) {
				// get all outgoing edges
				elements.addAll(getAllShortcutsFromSchemaEdges(doneAlready, edges, view, e,
						((DomainSource) e).getOutSchemas(), registry, updater));
			}
			if (e instanceof DomainSource) {
				// get all incoming edges
				elements.addAll(getAllShortcutsFromSelectEdges(doneAlready, edges, view, e,
						((DomainSource) e).getInSelects(), registry, updater));
			}
			if (e instanceof DomainIterator) {
				// get all outgoing edges
				elements.addAll(getAllShortcutsFromSelectEdges(doneAlready, edges, view, e,
						((DomainIterator) e).getOutSelects(), registry, updater));
			}
		}
	}

	private static List<Wire> getAllShortcutsFromWires(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<Wire> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (Wire wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}

			// additional special logic: if we have a WireDestination, get all of the incoming edges
			// that are RunActions, and render these as shortcut elements too (i.e. parameters)
			// (this covers SelectWires, etc...: see Issue 69)
			if (wire instanceof ParameterEdgesSource) {
				// specifically, if this wire is also a destination of parameters, follow these up
				ParameterEdgesSource prun =
					(ParameterEdgesSource) wire;

				result.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, wire, prun.getOutParameterEdges(), registry, updater));
			}

			if (wire instanceof ParameterEdgeDestination) {
				// specifically, if this wire is also a source of parameters, follow these up
				ParameterEdgeDestination prun =
					(ParameterEdgeDestination) wire;

				result.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, wire, prun.getInParameterEdges(), registry, updater));
			}

			if (wire instanceof Function) {
				// get all incoming edges
				result.addAll(getAllShortcutsFromComplexTerms(doneAlready, edges, view, wire,
						((Function) wire).getConditioned(), registry, updater));
			}
			if (wire instanceof ConditionEdgeDestination) {
				// get all incoming edges
				result.addAll(getAllShortcutsFromComplexTerms(doneAlready, edges, view, wire,
						((ConditionEdgeDestination) wire).getConditions(), registry, updater));
			}

			// ParameterEdges are now done in #getAllShortcutsFromParameterEdges()
		}

		return result;
	}

	private static List<ECARule> getAllShortcutsFromECARules(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<ECARule> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (ECARule wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getTrigger(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTarget(), wire, view, source, doneAlready, result, edges);
			}

			// additional special logic: if we have a WireDestination, get all of the incoming edges
			// that are RunActions, and render these as shortcut elements too (i.e. parameters)
			// (this covers SelectWires, etc...: see Issue 69)
			if (wire instanceof ParameterEdgesSource) {
				// specifically, if this wire is also a destination of parameters, follow these up
				ParameterEdgesSource prun =
					(ParameterEdgesSource) wire;

				result.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, wire, prun.getOutParameterEdges(), registry, updater));
			}

			if (wire instanceof ParameterEdgeDestination) {
				// specifically, if this wire is also a source of parameters, follow these up
				ParameterEdgeDestination prun =
					(ParameterEdgeDestination) wire;

				result.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, wire, prun.getInParameterEdges(), registry, updater));
			}

			if (wire instanceof Function) {
				// get all incoming edges
				result.addAll(getAllShortcutsFromComplexTerms(doneAlready, edges, view, wire,
						((Function) wire).getConditioned(), registry, updater));
			}
			if (wire instanceof ConditionEdgeDestination) {
				// get all incoming edges
				result.addAll(getAllShortcutsFromComplexTerms(doneAlready, edges, view, wire,
						((ConditionEdgeDestination) wire).getConditions(), registry, updater));
			}
		}

		return result;
	}

	private static List<Parameter> getAllShortcutsFromParameterEdges(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<Parameter> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (Parameter wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getParameterValue(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getParameterTerm(), wire, view, source, doneAlready, result, edges);
			}
		}

		return result;
	}

	private static List<ExtendsEdge> getAllShortcutsFromExtendsEdges(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<ExtendsEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (ExtendsEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}

		return result;
	}

	private static List<RequiresEdge> getAllShortcutsFromRequiresEdges(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<RequiresEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (RequiresEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
			
			if (wire instanceof ConstraintEdgesSource) {
				// get all incoming edges
				result.addAll(getAllShortcutsFromConstraintEdges(doneAlready, edges, view, wire,
						((ConstraintEdgesSource) wire).getOutConstraintEdges(), registry, updater));
			}
			if (wire instanceof ConstraintEdgeDestination) {
				// get all incoming edges
				result.addAll(getAllShortcutsFromConstraintEdges(doneAlready, edges, view, wire,
						((ConstraintEdgeDestination) wire).getInConstraintEdges(), registry, updater));
			}
		}

		return result;
	}

	private static List<ProvidesEdge> getAllShortcutsFromProvidesEdges(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<ProvidesEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (ProvidesEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}

		return result;
	}

	private static List<ConstraintEdge> getAllShortcutsFromConstraintEdges(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<ConstraintEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (ConstraintEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}

		return result;
	}

	private static List<ConstraintEdge> getAllShortcutsFromSchemaEdges(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<SchemaEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (SchemaEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}

		return result;
	}
	
	private static List<ConstraintEdge> getAllShortcutsFromSelectEdges(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<SelectEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (SelectEdge wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFrom(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getTo(), wire, view, source, doneAlready, result, edges);
			}
		}

		return result;
	}

	private static List<ComplexTerm> getAllShortcutsFromComplexTerms(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<ComplexTerm> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (ComplexTerm wire : outEdges) {
			// only look into these edges if we can actually render them...
			if (registry.getLinkWithClassVisualID(wire) != -1) {
				updater.considerElementForShortcut(wire.getFunction(), wire, view, source, doneAlready, result, edges);
				updater.considerElementForShortcut(wire.getConditioned(), wire, view, source, doneAlready, result, edges);

				// additional special logic: if we have a WireDestination, get all of the incoming edges
				// that are RunActions, and render these as shortcut elements too (i.e. parameters)
				// (this covers SelectWires, etc...: see Issue 69)
				if (wire instanceof ParameterEdgesSource) {
					// specifically, if this wire is also a destination of parameters, follow these up
					ParameterEdgesSource prun =
						(ParameterEdgesSource) wire;

					result.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, wire, prun.getOutParameterEdges(), registry, updater));
				}

				if (wire instanceof ParameterEdgeDestination) {
					// specifically, if this wire is also a source of parameters, follow these up
					ParameterEdgeDestination prun =
						(ParameterEdgeDestination) wire;

					result.addAll(getAllShortcutsFromParameterEdges(doneAlready, edges, view, wire, prun.getInParameterEdges(), registry, updater));
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
	private static List<ExecutionEdge> getAllShortcutsFromExecutionEdges(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<ExecutionEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (ExecutionEdge wire : outEdges) {
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
	private static List<DataFlowEdge> getAllShortcutsFromDataFlowEdges(
			List<EObject> doneAlready,
			List<EObject> edges,
			View view,
			EObject source,
			List<DataFlowEdge> outEdges,
			IVisualIDRegistryInstance registry,
			IDiagramUpdater updater) {
		List result = new LinkedList();

		edges.addAll(outEdges);

		// get all nodes at the start and end of the edge
		// that are not the original object source
		// NOTE: model-specific
		for (DataFlowEdge wire : outEdges) {
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
	public static EObject getSourceElement(EObject relationship) {
		return getSourceElement(relationship, false);
	}

	/**
	 * Get the source element of the relationship, or <code>null</code> if there is none.
	 *
	 * @param relationship
	 * @param throwExceptionIfNoneFound throw an IllegalArgumentException if the element could not be found.
	 * 		used in test cases.
	 * @return the source element of the relationship, or <code>null</code> if none can be found
	 * @throws IllegalArgumentException if <code>throwExceptionIfNoneFound</code> is true, and the relationship is not recognized
	 */
	public static EObject getSourceElement(EObject relationship, boolean throwExceptionIfNoneFound) throws IllegalArgumentException {
		// NOTE: model-specific
		if (relationship instanceof Wire) {
			return ((Wire) relationship).getFrom();
		}
		if (relationship instanceof ECARule) {
			return ((ECARule) relationship).getTrigger();
		}
		if (relationship instanceof ExecutionEdge) {
			return ((ExecutionEdge) relationship).getFrom();
		}
		if (relationship instanceof DataFlowEdge) {
			return ((DataFlowEdge) relationship).getFrom();
		}
		if (relationship instanceof Parameter) {
			return ((Parameter) relationship).getParameterValue();
		}
		if (relationship instanceof ExtendsEdge) {
			return ((ExtendsEdge) relationship).getFrom();
		}
		if (relationship instanceof ProvidesEdge) {
			return ((ProvidesEdge) relationship).getFrom();
		}
		if (relationship instanceof RequiresEdge) {
			return ((RequiresEdge) relationship).getFrom();
		}
		if (relationship instanceof ConstraintEdge) {
			return ((ConstraintEdge) relationship).getFrom();
		}
		if (relationship instanceof SimpleCondition) {
			return ((SimpleCondition) relationship).getFunction();
		}
		if (relationship instanceof SelectEdge) {
			return ((SelectEdge) relationship).getFrom();
		}
		if (relationship instanceof SchemaEdge) {
			return ((SchemaEdge) relationship).getFrom();
		}

		// if we get this far, there may be a relationship type that
		// we're not checking for
		if (throwExceptionIfNoneFound)
			throw new IllegalArgumentException("EObject '" + relationship + "' is not an edge.");
		return null;
	}

	/**
	 * Get the target element of the relationship, or <code>null</code> if there is none.
	 *
	 * @param relationship
	 * @return the target element of the relationship, or <code>null</code> if none can be found
	 */
	public static EObject getTargetElement(EObject relationship) {
		return getTargetElement(relationship, false);
	}

	/**
	 * Get the target element of the relationship, or <code>null</code> if there is none.
	 *
	 * @param relationship
	 * @param throwExceptionIfNoneFound throw an IllegalArgumentException if the element could not be found.
	 * 		used in test cases.
	 * @return the target element of the relationship, or <code>null</code> if none can be found
	 * @throws IllegalArgumentException if <code>throwExceptionIfNoneFound</code> is true, and the relationship is not recognized
	 */
	public static EObject getTargetElement(EObject relationship, boolean throwExceptionIfNoneFound) throws IllegalArgumentException {
		// NOTE: model-specific

		if (relationship instanceof Wire) {
			return ((Wire) relationship).getTo();
		}
		if (relationship instanceof ECARule) {
			return ((ECARule) relationship).getTarget();
		}
		if (relationship instanceof ExecutionEdge) {
			return ((ExecutionEdge) relationship).getTo();
		}
		if (relationship instanceof DataFlowEdge) {
			return ((DataFlowEdge) relationship).getTo();
		}
		if (relationship instanceof Parameter) {
			return ((Parameter) relationship).getParameterTerm();
		}
		if (relationship instanceof ExtendsEdge) {
			return ((ExtendsEdge) relationship).getTo();
		}
		if (relationship instanceof ProvidesEdge) {
			return ((ProvidesEdge) relationship).getTo();
		}
		if (relationship instanceof RequiresEdge) {
			return ((RequiresEdge) relationship).getTo();
		}
		if (relationship instanceof ConstraintEdge) {
			return ((ConstraintEdge) relationship).getTo();
		}
		if (relationship instanceof SimpleCondition) {
			return ((SimpleCondition) relationship).getConditioned();
		}
		if (relationship instanceof SelectEdge) {
			return ((SelectEdge) relationship).getTo();
		}
		if (relationship instanceof SchemaEdge) {
			return ((SchemaEdge) relationship).getTo();
		}

		// if we get this far, there may be a relationship type that
		// we're not checking for
		if (throwExceptionIfNoneFound)
			throw new IllegalArgumentException("EObject '" + relationship + "' is not an edge.");
		return null;
	}


}
