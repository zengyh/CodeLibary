package utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 文件名称: MacUtils.java
 * 编写人: yh.zeng
 * 编写时间: 13-11-5
 * 文件描述: 获取MAC地址工具类
 */
public class MacUtils {

	/** 取得日志记录器Logger */
	public static Logger logger = Logger.getLogger(MacUtils.class);

	/**
	 * 获取MAC地址
	 * @param request
	 * @return
	 */
	public static String getMac(HttpServletRequest request) {
		String macAddress = "";

		if (request.getHeader("x-forwarded-for") == null) {
			String ip = request.getRemoteAddr();
			String str = "";
			Process p;

			try {
				p = Runtime.getRuntime().exec("nbtstat -A " + ip);

				InputStreamReader ir = new InputStreamReader(p.getInputStream());
				LineNumberReader input = new LineNumberReader(ir);
				for (int i = 1; i < 100; i++) {
					str = input.readLine();
					if (str != null) {
						if (str.indexOf("MAC Address") > 1) {
							macAddress = str.substring(
									str.indexOf("MAC Address") + 14,
									str.length());
							break;
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("执行MacUtils类的getMac方法出错!");
			}

		}

		return macAddress;
	}

}
