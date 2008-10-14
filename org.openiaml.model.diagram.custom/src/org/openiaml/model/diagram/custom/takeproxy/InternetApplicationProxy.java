package org.openiaml.model.diagram.custom.takeproxy;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.model.ApplicationElement;

public class InternetApplicationProxy extends NamedElementProxy  {

	private org.openiaml.model.model.InternetApplication proxyObject;
	
	public InternetApplicationProxy(org.openiaml.model.model.InternetApplication rootObject) {
		this.proxyObject = rootObject;
	}

	public List<ApplicationElementProxy> getChildren() {
		ArrayList<ApplicationElementProxy> e = new ArrayList<ApplicationElementProxy>();
		for (ApplicationElement e2 : proxyObject.getChildren())
			e.add(new ApplicationElementProxy(e2));
		return e;
	}

}
