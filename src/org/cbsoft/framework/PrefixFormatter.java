package org.cbsoft.framework;

import java.lang.annotation.Annotation;

public class PrefixFormatter implements ValueFormatter {
	
	private String prefix;
	
//	public PrefixFormatter(String prefix) {
//		this.prefix = prefix;
//	}

	@Override
	public Object formatValue(Object value) {		
		return prefix + value.toString();
	}

	@Override
	public void readAnnotation(Annotation an) {	
		Prefix p = (Prefix) an;
		prefix = p.value();
	}

}
