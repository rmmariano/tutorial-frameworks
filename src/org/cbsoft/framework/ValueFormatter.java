package org.cbsoft.framework;

import java.lang.annotation.Annotation;

public interface ValueFormatter {
	
	public Object formatValue(Object value);
	// pega as informações da annotation
	public void readAnnotation(Annotation an);

}
