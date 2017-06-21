package utils.oscache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.web.ServletCacheAdministrator;

/**
 * Title: OscacheUtils.java
 * Description: Oscache缓存工具类
 * @author yh.zeng
 * @date 2017-6-16
 */
public class OscacheUtils {
	
	public enum  CACHE_SCOPE{
		APPLICATION_SCOPE,
		SESSION_SCOPE
	}

	/**
	 * 强制刷新（即清除）OSCACHE的缓存数据
	 * @param request   请求对象
	 * @param key       缓存的KEY
	 * @param scope     缓存的作用域
	 */
	public static void flushCacheByKey(HttpServletRequest request, String key, CACHE_SCOPE scope){
		int scopetype = 0;
		switch(scope){
		        case APPLICATION_SCOPE:
		        	    scopetype = PageContext.APPLICATION_SCOPE;
		        	    break;
		        case SESSION_SCOPE:
		        	    scopetype = PageContext.SESSION_SCOPE;
		        	    break;
		}
		Cache cache = ServletCacheAdministrator.getInstance(request.getSession().getServletContext()).getCache(request,  
				scopetype);
		cache.flushEntry(key);
	}
	
}
