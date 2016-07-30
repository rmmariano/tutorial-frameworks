package org.cbsoft.framework;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

public class FileSerializer {
	
	private PostProcessor pp;
	private DataFormatter df;

//	public FileSerializer() {
//		super();
//	}

	public FileSerializer(PostProcessor pp, DataFormatter df) {
		this.pp = pp;
		this.df = df;
	}	
	
	public void generateFile(String filename, Object obj) {
		byte[] bytes = df.formatData(getPropertiesList(obj));
		
		try {
			bytes = pp.postProcess(bytes);	
			FileOutputStream fileout = new FileOutputStream(filename);
			fileout.write(bytes);
			fileout.close();
		} catch (Exception e) {
			throw new RuntimeException("Problems writing the file",e);
		}
		
	}
	
	public Map<String,Object> getPropertiesList(Object obj){
		
		Map<String,Object> props = new HashMap<String, Object>();		
		Class<?> clazz = obj.getClass();
		
		for(Method m: clazz.getMethods()){			
			if(isAllowedGetter(m)){				
				try {					
					// primeiro parametro é o objeto que sera utilizado na reflexao, depois os argumentos, caso tenha				
					Object value = m.invoke(obj);	
					
					// pega o nome do método
					String getterName = m.getName();
					
					String propName = getterName.substring(3, 4).toLowerCase() + 
							getterName.substring(4);
					
					
					if(m.isAnnotationPresent(Prefix.class)){
						Prefix prefix = m.getAnnotation(Prefix.class);
						value = prefix.value() + value;						
					}
					
					props.put(propName, value);					
				} catch (Exception e) {
					throw new RuntimeException("Cannot retrieve properties", e);
				}
				
			}
			
		}

		return props;
	}

	private boolean isAllowedGetter(Method m) {
				// quero somente os métodos get, logo tem que começar com get
		return m.getName().startsWith("get") &&
				// nao ter parametros
				m.getParameterTypes().length == 0 &&
					// o retorno diferente de void
					m.getReturnType() != void.class &&
						// nao pode ser a getClass (de Object)
						!m.getName().equals("getClass") &&
							// e nao pode ter a anotacao DontIncludeOnFile no metodo
							!m.isAnnotationPresent(DontIncludeOnFile.class);
	}

}