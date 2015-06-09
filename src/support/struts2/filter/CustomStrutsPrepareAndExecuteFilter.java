package support.struts2.filter;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import support.struts2.config.providers.CustomConfigurationProvider;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * 文件名称: CustomStrutsPrepareAndExecuteFilter.java
 * 编写人: yh.zeng
 * 编写时间: 15-6-9 上午11:04
 * 文件描述:
 */
public class CustomStrutsPrepareAndExecuteFilter extends StrutsPrepareAndExecuteFilter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        String pattern = filterConfig.getInitParameter("configPattern");
        if(StringUtils.isNotBlank(pattern)){
            CustomConfigurationProvider.setConfigFilePattern(pattern);
        }
    }
}

