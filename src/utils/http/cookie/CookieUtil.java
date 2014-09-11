package utils.http.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件名称: CookieUtil.java
 * 编写人: yh.zeng
 * 编写时间: 13-4-12
 * 文件描述: Cookie工具类
 */
public class CookieUtil {
	/**
	 * 添加一个cookie值
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @param time
	 *            cookie的有效期
	 * @param response
	 *            保存cookie的对象
	 */
	public static void setCookie(String name, String value, Integer time,
			HttpServletResponse response) {
		try {
			// 关键点
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}catch(Exception e){
			
		    e.printStackTrace();
		}
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(time);
		response.addCookie(cookie);
	}

	/**
	 * 根据name值,从cookie当中取值
	 * 
	 * @param name
	 *            要获取的name
	 * @param request
	 *            cookie存在的对象
	 * @return 与name对应的cookie值
	 */
	public static String getCookie(String name, HttpServletRequest request) {
		/*Cookie[] cs = request.getCookies();
		String value = "";
		if (cs != null) {
			for (Cookie c : cs) {
				if (name.equals(c.getName())) {
					try {
						// 关键点
						value = URLDecoder.decode(c.getValue(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					return value;
				}
			}
		}
		return value;
		*/
		
		String value = "";
		String cookieStr = request.getHeader("cookie");
		if (cookieStr != null) {
			String[] cookies = cookieStr.split(";");
			for (int i = 0; i < cookies.length; i++) {
				String cookie = cookies[i].trim();
				if (cookie.startsWith(name + "=")) {
					value = cookie.replace(name + "=", "").trim();
					break;
				}
			}
		}
		return value;
		
	}
	
	public static void delCookie(String name, HttpServletRequest request,
			HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(name)) {
					// 设置生命周期为0
					c.setMaxAge(0);
					c.setPath("/");
					response.addCookie(c);
				}
			}
		}
	}
	
	
	
}
