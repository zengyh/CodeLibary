package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * 文件名称: PropertyUtils.java
 * 编写人: yh.zeng
 * 编写时间: 14-8-16
 * 文件描述: 读取properties文件的工具类
 */
public class PropertyUtils {

	public static Properties getProperties(String fileName){
		Properties orgProps = new Properties();
		try {
			String filePath = PropertyUtils.class.getClassLoader().getResource(
					"").getPath();
			filePath = filePath + fileName;

            BufferedReader bf = new BufferedReader(new FileReader(new File(filePath)));
			orgProps.load(bf);
		}catch (Exception e){
			e.printStackTrace();
		}
		return orgProps;
		
	}
	
	public static String getValue(String fileName, String propertyName){
		Properties orgProps = new Properties();
		try {
            orgProps = getProperties( fileName );
		}catch (Exception e){
			e.printStackTrace();
		}
		return orgProps.getProperty(propertyName);
	}
	
}
