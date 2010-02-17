/**
 * 
 */
package org.openiaml.model.drools;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.domain.DomainPackage;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.SetWire;

import ca.ecliptical.emf.xpath.EMFXPath;

/**
 * Helper functions for Drools rules.
 * 
 * This is necessary because:
 * 
 * <ul>
 * 	<li>The same rules are used in multiple files (such as {@link #connects(WireEdge, Object, Object)}.</li>
 *  <li>Function names cannot be shared across multiple rule files (so each rule file needs its own package).</li>
 * </ul>
 * 
 * @author jmwright
 *
 */
public class DroolsHelperFunctions {

	public boolean connects(WireEdge wire, Object a, Object b) {
		if (wire == null) 
			throw new NullPointerException("Wire '" + wire + "' = null");
		if (wire.getFrom() == null)
			throw new NullPointerException("Wire '" + wire + "'.from = null");
		if (wire.getTo() == null)
			throw new NullPointerException("Wire '" + wire + "'.to = null");
		return (wire.getFrom().equals(a) && wire.getTo().equals(b)) ||
			(wire.getFrom().equals(b) && wire.getTo().equals(a));
	}

	public boolean isXPath(String query) {
		return query.startsWith("xpath:");
	}

	/**
	 * Could this potentially be an XPath match?
	 * 
	 * In particular, we take a query /a/b[c]/d[e] and
	 * consider /a/b/d as potential matches. /a/b[c]/d[e] will become
	 * the ConditionWire.
	 *
	 * Note we have to use the same XPath expressions as test
	 * cases, i.e. //iaml:children[@name], not //Page
	 * @throws JaxenException 
	 */
	public boolean potentialXPathMatch(EObject container, DynamicApplicationElementSet ds, Frame target) throws JaxenException {
		String query = ds.getQuery();
		if (!query.startsWith("xpath:"))
			throw new RuntimeException("potentialXPathMatch was called without an xpath: query: '" + query + "'");

		// remove prefix		
		query = query.substring("xpath:".length());
		
		// remove all conditionals
		// TODO note this will fail if we have a[a[b]], though I don't think this is possible in XPath anyway 	
		query = query.replaceAll("\\[([^\\]]+)\\]", "");
		
		// evaluate xpath using EMFXPath
		// TODO move this into Java code (to reduce redundancy of the following code)
		EMFXPath xpath = new EMFXPath(query);
		xpath.addNamespace("iaml", ModelPackage.eNS_URI);
		xpath.addNamespace("iaml.domain", DomainPackage.eNS_URI);
		xpath.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		List<?> results = xpath.selectNodes(container);

		if (results.contains(target)) {
			return true;
		}
		
		return false;
	}

	public boolean loginAttributeMatches(ApplicationElementProperty p, DomainAttribute a) {
		return p.getName() != null && p.getName().equals("current " + a.getName());
	}

	/**
	 * Does the given DomainObject have at least one attribute?
	 * 
	 * @param dobj
	 * @return
	 */
	public boolean hasDomainAttribute(DomainObject dobj) {
		return dobj.getAttributes().size() > 0;
	}

	/**
	 * Does the given LoginHandler[instance] have incoming ParameterWires from
	 * attributes contained by the given DomainObject?
	 *  
	 * @param handler
	 * @param dobj
	 * @return
	 */
	public boolean hasIncomingParameterEdgesFrom(LoginHandler handler, DomainObject dobj) {
		for (ParameterEdge edge : handler.getInParameterEdges()) {
			if (edge.getFrom() instanceof DomainAttribute) {
				if (dobj.equals(((DomainAttribute) edge.getFrom()).eContainer())) {
					// found one
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check that the given attribute is not a primary key, or a
	 * derived foreign key.
	 * 	
	 * @param attribute
	 * @return
	 */
	public boolean notPrimaryKey(DomainAttribute attribute) {
		return !attribute.getName().contains("generated primary key");
	}

	/**
	 * What is the last chained operation for the given PrimitiveOperation?
	 */
	public PrimitiveOperation lastChainedOperation(PrimitiveOperation op) {
		for (WireEdge wire : op.getOutEdges()) {
			if (wire.getTo() instanceof PrimitiveOperation) {
				return lastChainedOperation((PrimitiveOperation) wire.getTo());
			}
		}
		return op;
	}

	/**
	 * Get the query string for selecting this user.
	 *
	 * We can't rely on using only attributes inferred to exist in the
	 * current role, because they may not have been inferred yet (they
	 * would be inferred in a later step).
	 *
	 * Also iterates over extends so we get parent attributes. 
	 */
	public String getUserQueryString(Role role) {
		String q = "";
		for (DomainAttribute attribute : role.getAttributes()) {
			// ignore primary keys
			if (notPrimaryKey(attribute)) {
				// ignore attributes which are extensions of other attributes;
				// these attributes will be selected later
				if (attribute.getOutExtendsEdges().isEmpty()) {
					// add this attribute as a query
					if (!q.isEmpty()) {
						q += " and ";
					}
					q += attribute.getName() + " = :" + attribute.getName();
				}
			} 
		}
		
		// get all parents
		for (ExtendsEdge w : role.getOutExtendsEdges()) {
			if (w.getTo() instanceof Role) {
				String q2 = getUserQueryString((Role) w.getTo());
				if (!q2.isEmpty()) {
					if (q.isEmpty()) {
						q = q2;
					} else {
						q += " and " + q2;
					}
				}		
			}
		}
		
		return q;
	}
	
	public String getQueryString(LoginHandler login_handler, DomainObject dobj) {
		String q = "";
		for (ParameterEdge wire : login_handler.getInParameterEdges()) {
			if (wire.getFrom() instanceof DomainAttribute &&
					dobj.equals(wire.getFrom().eContainer())) {
				// add this attribute as a query
				DomainAttribute attribute = (DomainAttribute) wire.getFrom();
				if (notPrimaryKey(attribute)) {
					if (!q.isEmpty()) {
						q += " and ";
					}
					q += attribute.getName() + " = :" + attribute.getName();
				} 
			}
		}
		
		return q;
	}

	public boolean nameMatches(NamedElement e1, NamedElement e2) {
		return e1.getName().toLowerCase().equals(e2.getName().toLowerCase());
	}

	public boolean connectsSet(SetWire wire, Object a, Object b) {
		if (wire.getFrom() == null)
			throw new NullPointerException("Wire '" + wire + "'.from = null");
		if (wire.getTo() == null)
			throw new NullPointerException("Wire '" + wire + "'.to = null");
		return wire.getFrom().equals(a) && wire.getTo().equals(b);
	}

	public Session containingSession(EObject e) {
		if (e.eContainer() == null) {
			return null;
		}
		if (e.eContainer() instanceof Session) {
			return (Session) e.eContainer();
		}
		return containingSession(e.eContainer());
	}

	public boolean containingSessionEquals(EObject e, Session s) {
		Session actual = containingSession(e);
		return s.equals(actual);
	}

	public boolean debug(Object o) {
		System.out.println(o);
		return true;
	}

	/**
	 * For a DomainAttribute, this returns true if it eventually
	 * extends a DomainAttribute which is a generated primary key.
	 */
	public boolean notExtendingGeneratedPrimaryKey(DomainAttribute attr) {
		if (attr.isPrimaryKey() && attr.isIsGenerated()) {
			return false;
		}
		for (ExtendsEdge w : attr.getOutExtendsEdges()) {
			if (w.getTo() instanceof DomainAttribute) {
				if (!notExtendingGeneratedPrimaryKey((DomainAttribute) w.getTo())) {
					// we are extending a generated primary key
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Does the given session directly or indirectly contain the given object? 
	 * 
	 * @param session the session to check against
	 * @param prop the object to investigate
	 * @return true if the given session contains the given property to some degree
	 */
	public boolean sessionContains(Session session, EObject prop) {
		EObject obj = prop;
		int i = 0;
		while (i < 1000) {
			i++;
			if (obj == null) {
				// we ran out of hierarchy
				return false;
			} else if (obj.equals(session)) {
				// 'prop' is contained directly or indirectly by the given session 
				return true;
			} else {
				// recurse up the containment
				obj = obj.eContainer();
			}
		}
		
		throw new RuntimeException("Possible infinite loop detected in containment hierarchy: " + prop);
	}

}
