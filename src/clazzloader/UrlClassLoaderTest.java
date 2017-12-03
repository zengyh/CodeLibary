package clazzloader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * @编写人： yh.zeng
 * @编写时间：2017-12-3 下午2:26:59
 * @文件描述:  java如何通过类加载器加载类和jar包 Demo
 */
public class UrlClassLoaderTest {

	public static void main(String args[]){
        String fileUrlString = new File("D:\\loader").toURI().toString();
        fileUrlString = fileUrlString.replaceAll("!/", "%21/");
		try {
			ClassLoader classLoader = new URLClassLoader(new URL[]{new URL(fileUrlString)});
			Class HelloWorldClazz = classLoader.loadClass("terry.codex.HelloWorld");
			Object helloWorld = HelloWorldClazz.newInstance();
			Method method = helloWorld.getClass().getMethod("sayHello", null);
			method.invoke(helloWorld, null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
