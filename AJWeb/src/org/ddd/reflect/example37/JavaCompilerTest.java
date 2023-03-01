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

public class JavaCompilerTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {

        String string = "public class Hello {"
        		+ " public static void main(String []args)"
        		+ "{"
        		+ "System.out.println(\"Hello \"+args[0]+\",this is dynamically compiled and executed!\");"
        		+ "}"
        		+ "}";
        File file = new File(System.getProperty("java.io.tmpdir") +File.separator+"Hello.java");
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
	        URL[] urls = new URL[]{new URL("file:/" +file.getParent().replace('\\', '/')+"/")};
	        URLClassLoader classLoader = new URLClassLoader(urls);
	        Class<?> clazz = classLoader.loadClass("Hello");
	        System.out.println("类名为："+clazz.getName());
	        Method method = clazz.getDeclaredMethod("main", String[].class);
	        method.invoke(null,  (Object) new String[]{"xcy"});
	        
	        Field f=ClassLoader.class.getDeclaredField("classes");
	        f.setAccessible(true);
	        f.get(classLoader);
	        Vector classes=(Vector)f.get(ClassLoader.getSystemClassLoader());
	        System.out.println(classes.size());

	        classLoader.close();
        }
        else
        {
           System.err.println("编译出错，错误参加控制输出");
        }
	}
}
