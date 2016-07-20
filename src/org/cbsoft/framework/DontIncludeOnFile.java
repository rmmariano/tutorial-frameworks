package org.cbsoft.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Como essa anotacao vai ser consumida por reflexao, precisa colocar o RUNTIME
@Retention(RetentionPolicy.RUNTIME)
// So sera utilizada em metodos
@Target(ElementType.METHOD)
public @interface DontIncludeOnFile {

}



// As anotacoes nao fazem nada, precisa criar alguem que processe elas!