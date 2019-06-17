package com.marko.shop.infrastructure.common.visitor;

public interface AbstractVisitorElement {

	void acceptVisitor(AbstractVisitor visitor);

	void setValue(Object value);

	Object getValue();
	
}
