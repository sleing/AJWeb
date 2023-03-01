package org.ddd.reflect.example39;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandler implements InvocationHandler {
	private Object proxied;
	public LogHandler(Object proxied){
		this.proxied = proxied;
	}
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		method.invoke(this.proxied, args);
		System.out.println("运行时间： " + System.currentTimeMillis());
		return null;
	}
}
