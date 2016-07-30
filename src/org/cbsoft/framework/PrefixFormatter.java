package org.cbsoft.framework;

public class PrefixFormatter implements ValueFormatter {
	
	private String prefix;
	
	public PrefixFormatter(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public Object formatValue(Object value) {		
		return prefix + value.toString();
	}

}
