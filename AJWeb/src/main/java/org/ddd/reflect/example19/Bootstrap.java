package org.ddd.reflect.example19;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class Bootstrap {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		Class clazz = Person.class;
		Method method = clazz.getMethod("speak");
		System.out.println(generateSignature(method));
		method = clazz.getMethod("eat", String.class);
		System.out.println(generateSignature(method));
		method = clazz.getMethod("listen");
		System.out.println(generateSignature(method));
		method = clazz.getMethod("fly");
		System.out.println(generateSignature(method));
		method = clazz.getMethod("think");
		System.out.println(generateSignature(method));
//		method = clazz.getMethod("userTool");	//不合法的调用
//		method = clazz.getMethod("userTool",String.class);	//不合法的调用
	}
	public static String generateSignature(Method method)
	{
		StringBuilder sb = new StringBuilder();
	    
	 
	    if (Modifier.isPublic(method.getModifiers())) 		 sb.append("public ");
	    if (Modifier.isProtected(method.getModifiers()))     sb.append("protected ");
	    if (Modifier.isPrivate(method.getModifiers()))       sb.append("private ");
	    if (Modifier.isAbstract(method.getModifiers()))      sb.append("abstract ");
	    if (Modifier.isStatic(method.getModifiers()))        sb.append("static ");
	    if (Modifier.isFinal(method.getModifiers()))         sb.append("final ");
	    if (Modifier.isSynchronized(method.getModifiers()))  sb.append("synchronized ");
	    if (Modifier.isNative(method.getModifiers()))        sb.append("native ");
	 
	    sb.append(method.getReturnType().getSimpleName()).append(" ");
	    sb.append(method.getName());
	    sb.append("(");
	    for(Parameter parameter:method.getParameters())
	    {
	    	sb.append(parameter.getType().getSimpleName()).append(" ");
	    	//为了取得参数名称，必须JDK8以上，另外需要在编译时增加 -parameters
	    	sb.append(parameter.getName());
	    }
	    sb.append(")");
	    return sb.toString();
	}
}
