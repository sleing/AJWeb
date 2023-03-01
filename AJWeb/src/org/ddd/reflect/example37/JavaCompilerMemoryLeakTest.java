package org.ddd.reflect.example37;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Vector;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JavaCompilerMemoryLeakTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {

		String tempPath = System.getProperty("java.io.tmpdir") +File.separator;
        URL[] urls = new URL[]{new URL("file:/" +tempPath.replace('\\', '/')+"/")};
        URLClassLoader classLoader = new URLClassLoader(urls);
        for(int i=0;i<100;i++)
        {
	        String string = "public class Hello"+i+" {"
	        		+ " public static void main(String []args)"
	        		+ "{"
	        		+ "System.out.println(\"Hello，this is dynamically compiled and executed!\");"
	        		+ "}"
	        		+ "}";
	        File file = new File(tempPath+"Hello"+i+".java");
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	          
	        byte[] bytes = string.getBytes();
	        FileOutputStream stream = new FileOutputStream(file);
	        stream.write(bytes, 0, bytes.length);
	        stream.close();
	  
	        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
	     

	        int result = javaCompiler.run(null, null, null,file.getAbsolutePath());
	        if(result == 0)
	        {
	        	System.out.println("编译成功");
		
		        Class<?> clazz = classLoader.loadClass("Hello"+i);
		        System.out.println("类名为："+clazz.getName());
		        Method method = clazz.getDeclaredMethod("main", String[].class);
		        method.invoke(null,  (Object) new String[]{"aa","bb"});
		        
		        Field f=ClassLoader.class.getDeclaredField("classes");
		        f.setAccessible(true);
		        f.get(classLoader);
		        Vector classes=(Vector)f.get(classLoader);
		        System.out.println(classes.size());
	        }
	        else
	        {
	           System.err.println("编译出错，错误参加控制输出");
	        }
        }
        
        classLoader.close();
	}
}
