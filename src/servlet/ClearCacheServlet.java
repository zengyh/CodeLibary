package servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import utils.StringUtils;
import utils.oscache.OscacheUtils;

/**
 * Title: ClearCacheServlet.java
 * Description: 清除KEY对应的OSCACHE缓存
 * @author yh.zeng
 * @date 2017-6-16
 */
public class ClearCacheServlet extends HttpServlet {

	private static final long serialVersionUID = -3266620619413471751L;
	private Logger logger = Logger.getLogger(ClearCacheServlet.class);
	private OscacheUtils.CACHE_SCOPE CACHE_SCOPE = OscacheUtils.CACHE_SCOPE.APPLICATION_SCOPE; //缓存作用域
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String scope = config.getInitParameter("scope");
		if(scope != null){
			if(!scope.equals("application") && !scope.equals("session")){
				logger.error("init-param参数scope的值必须是application或session！");
				throw new ServletException("init-param参数scope的值必须是application或session！");
			}else{
				if(scope.equals("application")){
					CACHE_SCOPE = OscacheUtils.CACHE_SCOPE.APPLICATION_SCOPE;
				}else{
					CACHE_SCOPE = OscacheUtils.CACHE_SCOPE.SESSION_SCOPE;
				}
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String cachekey = req.getParameter("cachekey"); //缓存的key
		boolean success = true;
		
		try{
			OscacheUtils.flushCacheByKey(req, cachekey, CACHE_SCOPE);
		}catch (Exception e){
			e.printStackTrace();
			logger.error(StringUtils.getExceptionMessage(e));
			success = false;
		}
		if(success){
			resp.getWriter().write("success");
		}else{
			resp.getWriter().write("fail");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	    doGet(req, resp);
	}
    
}
