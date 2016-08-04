package org.cbsoft.framework;

public class Main {
	
	public static void main(String[] args){
		Product p = new Product("notebook", "HP", 1999.99, "2348203894032948");
		p.setSecretCode("Não deve aparecer");
				
//		Serializer cxs = new FileSerializer(new Compressor(), new PropertiesFormatter());
//		cxs.generateFile("product.zip", p);
		
		SerializeBuilder builder = new SerializeBuilder();
		
		Serializer cxs = builder.createPropertiesSerializer()
								.withEncryption(1)
								.withLoggin()
								.build();
		
//		Serializer cxs = new SerializerLogger(new FileSerializer(new Crypto(1), new PropertiesFormatter()));
		
		cxs.generateFile("product.txt", p);
		
	}

}
