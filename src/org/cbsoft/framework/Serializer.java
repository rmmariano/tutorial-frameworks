package org.cbsoft.framework;

public interface Serializer {

	public void generateFile(String filename, Object obj);

	public void setPostProcessor(PostProcessor pp);

	public PostProcessor getPostProcessor();

}