package org.ddd.reflect.example13;


public class Bootstrap {
	public static void main(String[] args) {
		String className = "org.ddd.reflect.example11.Person";  //指定被加载的类名
		try {
			Class<?> clazz = Class.forName(className);
			System.out.println(clazz.getCanonicalName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}





