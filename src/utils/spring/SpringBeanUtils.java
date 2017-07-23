package utils.spring;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import utils.StringUtils;

/**
 * Title: SpringBeanUtils.java
 * Description: 获取Spring的Bean实例对象的工具类
 * @author yh.zeng
 * @date 2017-6-27
 */
public class SpringBeanUtils {
	
	private static Logger logger = Logger.getLogger(SpringBeanUtils.class);
	
	static String filePath ="WebRoot/WEB-INF/applicationContext.xml";
	static  ApplicationContext CONTEXT ;
	static{
		try{
			CONTEXT = new FileSystemXmlApplicationContext(filePath);
		}catch(Exception e){
			logger.error(StringUtils.getExceptionMessage(e));
		}
	}
	
	
	/**
	 * 获取Bean
	 * @param uniqueIdentifier Bean的唯一标识，可以是ID也可以是name
	 * @return
	 */
	public static Object getBean(String uniqueIdentifier){
		return CONTEXT.getBean(uniqueIdentifier);
	}
	
	/**
	 * 获取SessionFacotry对象
	 * @param uniqueIdentifier  SessionFactory Bean的唯一标识，可以是ID也可以是name
	 * @return
	 */
	public static SessionFactory getSessionFactory(String uniqueIdentifier){
		return (SessionFactory) CONTEXT.getBean(uniqueIdentifier);
	}

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		SpringBeanUtils.filePath = filePath;
		CONTEXT = new FileSystemXmlApplicationContext(filePath);
	}
	
}
