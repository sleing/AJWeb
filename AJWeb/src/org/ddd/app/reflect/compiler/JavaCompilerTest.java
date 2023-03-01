package org.ddd.app.reflect.compiler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JavaCompilerTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        /**
	         * 将 string 写入Hello.java中
	         * 通过文件输出流
         */
        String string = "public class Hello { public static void main(String []args){System.out.println(\"Hello\");}}";
        File file = new File("C:\\IDE\\eclipse-workspaceTeach\\AJWeb\\src\\org\\cqut\\ddd\\reflect\\compiler\\source\\Hello.java");
        if (!file.exists()) {
            file.createNewFile();
        }
       
        byte[] bytes = string.getBytes();
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(bytes, 0, bytes.length);
        stream.close();
        /**
	         * 编译Hello.java
	         * 通过反射调用main函数实现函数的运行
         */
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int result = javaCompiler.run(null, null, null, "C:\\IDE\\eclipse-workspaceTeach\\AJWeb\\src\\org\\cqut\\ddd\\reflect\\compiler\\source\\Hello.java");
        System.out.println(result == 0 ? "success" : "failure");
        URL[] urls = new URL[]{new URL("file:/" + "C:/IDE/eclipse-workspaceTeach/AJWeb/src/org/cqut/ddd/reflect/compiler/source/")};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class c = classLoader.loadClass("Hello");
        System.out.println(c.getName());
        Method method = c.getDeclaredMethod("main", String[].class);
        method.invoke(null,  (Object) new String[]{"aa","bb"});

	}

}
