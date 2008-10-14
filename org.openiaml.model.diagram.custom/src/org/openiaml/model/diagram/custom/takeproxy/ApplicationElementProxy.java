package org.openiaml.model.diagram.custom.takeproxy;

import org.openiaml.model.model.ApplicationElement;

public class ApplicationElementProxy extends NamedElementProxy  {

	private ApplicationElement proxyObject;
	
	public ApplicationElementProxy(ApplicationElement e2) {
		this.proxyObject = e2;
	}

}
