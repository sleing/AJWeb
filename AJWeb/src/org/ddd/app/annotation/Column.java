package org.ddd.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	public String value() default ""; //数据库的字段的名称
	public String description() default "";	
	public String label() default "";
	public boolean nullable() default true;
	public int maxLength() default 0;
	public int displayIndex() default 0;
}
