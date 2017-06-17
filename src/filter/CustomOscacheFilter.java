package filter;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.web.ServletCacheAdministrator;
import com.opensymphony.oscache.web.filter.CacheFilter;

/**
 * Title: CustomOscacheFilter.java
 * Description:  自定义oscache缓存的filter
 *               修改了key的生成规则，让页面路径（相对于 Webapp 的路径）作为key
 * @author yh.zeng
 * @date 2017-6-16
 */
public class CustomOscacheFilter extends CacheFilter{
	
	private String getQueryString(HttpServletRequest httpRequest){
		String queryString = httpRequest.getQueryString();
		if(queryString == null || queryString.equals("")){
			return "";
		}else{
			return "?" + queryString;
		}
	}
	
	@Override
	public String createCacheKey(HttpServletRequest httpRequest,
			ServletCacheAdministrator scAdmin, Cache cache) {
		return httpRequest.getServletPath() + getQueryString(httpRequest) ;
	}

}
