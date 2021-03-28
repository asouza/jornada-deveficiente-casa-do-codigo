package com.deveficiente.casadocodigov2.compartilhado;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD,ElementType.CONSTRUCTOR})
public @interface Generated {
	
	public static final String ECLIPSE = "Gerado pelo eclipse";

	String value();
}
