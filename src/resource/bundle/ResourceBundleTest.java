package resource.bundle;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * @编写人： yh.zeng
 * @编写时间：2017-12-3 上午12:35:42
 * @文件描述: 通过ResourceBundle的方式从资源文件（如messages.properties）中自动读取并进行资源绑定的demo
 *           可以用于实现国际化
 */
public class ResourceBundleTest {

	public static void main(String args[]){
        String bundleName = "terry.codex.LocalStrings";

        /**
         * 1、在当前类加载器下，找Java 虚拟机实例的当前默认语言环境值对应的配置文件，会找以下文件内容
         *        terry/codex/LocalStrings_zh.class
         *        terry/codex/LocalStrings_zh.properties
         *        terry/codex/LocalStrings.class
         *        terry/codex/LocalStrings.properties
         *    文件内容自上而下覆盖
         *    LocalStrings_zh.class文件会覆盖掉LocalStrings_zh.properties文件的内容
         *    LocalStrings_zh.properties文件会覆盖掉LocalStrings.class文件的内容
         *    LocalStrings.class文件会覆盖掉LocalStrings.properties文件的内容
         *    注意：LocalStrings_zh.class文件必须是ResourceBundle的子类，实现handleGetObject和getKeys方法！
         */
        try {
        	System.out.println("Example1：");
        	ResourceBundle bundle  = ResourceBundle.getBundle(bundleName);
            if(bundle != null){
            	String value = bundle.getString("tmpdir");
            	String params[] = new String[]{"D:\\TMP"};
                MessageFormat mf = new MessageFormat(value);
                System.out.println(mf.format(params, new StringBuffer(), null).toString());
            }
		} catch (MissingResourceException ex) {
			ex.printStackTrace();
		}



        /**
         * 2、Locale.ENGLISH在当前类加载器下找以下文件的内容：
         *       terry/codex/LocalStrings_en.class
         *       terry/codex/LocalStrings_en.properties
         *       terry/codex/LocalStrings.class
         *       terry/codex/LocalStrings.properties
         *    文件内容自上而下覆盖
         *    LocalStrings_en.class文件会覆盖掉LocalStrings_en.properties文件的内容
         *    LocalStrings_en.properties文件会覆盖掉LocalStrings.class文件的内容
         *    LocalStrings.class文件会覆盖掉LocalStrings.properties文件的内容
         *    注意：LocalStrings_en.class文件必须是ResourceBundle的子类，实现handleGetObject和getKeys方法！
         */
        try {
        	System.out.println("Example2：");
        	ResourceBundle bundle  = ResourceBundle.getBundle(bundleName, Locale.ENGLISH);
            if(bundle != null){
            	String value = bundle.getString("tmpdir");
            	String params[] = new String[]{"D:\\TMP"};
                MessageFormat mf = new MessageFormat(value);
                mf.setLocale(Locale.ENGLISH);
                System.out.println(mf.format(params, new StringBuffer(), null).toString());
            }
		} catch (MissingResourceException ex) {
			ex.printStackTrace();
		}


        /**
         * 3、在当前类加载器下找以下文件：
         *       terry/codex/LocalStrings_zh.class
         *       terry/codex/LocalStrings_zh.properties
         *       terry/codex/LocalStrings.class
         *       terry/codex/LocalStrings.properties
         *    文件内容自上而下覆盖
         *    LocalStrings_zh.class文件会覆盖掉LocalStrings_zh.properties文件的内容
         *    LocalStrings_zh.properties文件会覆盖掉LocalStrings.class文件的内容
         *    LocalStrings.class文件会覆盖掉LocalStrings.properties文件的内容
         *    注意：LocalStrings_zh.class文件必须是ResourceBundle的子类，实现handleGetObject和getKeys方法！
         */
        try {
        	System.out.println("Example3：");
        	ResourceBundle bundle  = ResourceBundle.getBundle(bundleName, Locale.CHINA);
            if(bundle != null){
            	String value = bundle.getString("tmpdir");
            	String params[] = new String[]{"D:\\TMP"};
                MessageFormat mf = new MessageFormat(value);
                mf.setLocale(Locale.CHINA);
                System.out.println(mf.format(params, new StringBuffer(), null).toString());
            }
		} catch (MissingResourceException ex) {
			ex.printStackTrace();
		}


        /**
         * 4、在当前类加载器下找以下文件：
         *       terry/codex/LocalStrings_fr.class
         *       terry/codex/LocalStrings_fr.properties
         *       terry/codex/LocalStrings.class
         *       terry/codex/LocalStrings.properties
         *    文件内容自上而下覆盖
         *    LocalStrings_fr.class文件会覆盖掉LocalStrings_fr.properties文件的内容
         *    LocalStrings_fr.properties文件会覆盖掉LocalStrings.class文件的内容
         *    LocalStrings.class文件会覆盖掉LocalStrings.properties文件的内容
         *    注意：LocalStrings_fr.class文件必须是ResourceBundle的子类，实现handleGetObject和getKeys方法！
         */
        try {
        	System.out.println("Example4：");
        	ResourceBundle bundle  = ResourceBundle.getBundle(bundleName, Locale.FRANCE);
            if(bundle != null){
            	String value = bundle.getString("tmpdir");
            	String params[] = new String[]{"D:\\TMP"};
                MessageFormat mf = new MessageFormat(value);
                mf.setLocale(Locale.FRANCE);
                System.out.println(mf.format(params, new StringBuffer(), null).toString());
            }
		} catch (MissingResourceException ex) {
			ex.printStackTrace();
		}


        /**
         * 5、在D:\\loader这个类加载路径下找以下文件：
         *       terry/codex/LocalStrings_zh.class
         *       terry/codex/LocalStrings_zh.properties
         *       terry/codex/LocalStrings.class
         *       terry/codex/LocalStrings.properties
         *    文件内容自上而下覆盖
         *    LocalStrings_zh.class文件会覆盖掉LocalStrings_zh.properties文件的内容
         *    LocalStrings_zh.properties文件会覆盖掉LocalStrings.class文件的内容
         *    LocalStrings.class文件会覆盖掉LocalStrings.properties文件的内容
         *    注意：LocalStrings_zh.class文件必须是ResourceBundle的子类，实现handleGetObject和getKeys方法！
         */
		try {
			bundleName = "terry.codex2.LocalStrings";
			System.out.println("Example5：");
	        String fileUrlString = new File("D:\\loader").toURI().toString();
	        fileUrlString = fileUrlString.replaceAll("!/", "%21/");
			ClassLoader classLoader = new URLClassLoader(new URL[]{new URL(fileUrlString)});
	        if (classLoader != null) {
	            try {
	            	ResourceBundle bundle = ResourceBundle.getBundle(bundleName, Locale.CHINA, classLoader);
	                if(bundle != null){
	                	String value = bundle.getString("tmpdir");
	                	String params[] = new String[]{"D:\\TMP"};
	                    MessageFormat mf = new MessageFormat(value);
	                    mf.setLocale(Locale.CHINA);
	                    System.out.println(mf.format(params, new StringBuffer(), null).toString());
	                }
	            } catch (MissingResourceException ex2) {
	            	ex2.printStackTrace();
	            }
	        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
