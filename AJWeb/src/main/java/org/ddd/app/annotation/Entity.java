package org.ddd.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
	public String value() default ""; //tableName 
	public ID id() default @ID;
	public String description() default "";
	public String label() default "";

}
