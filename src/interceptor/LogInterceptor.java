package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;

/**
 * 文件名称: LogInterceptor.java
 * 编写人: yh.zeng
 * 编写时间: 17-1-18 下午1:26
 * 文件描述: todo
 */
public class LogInterceptor extends AbstractInterceptor
{
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        actionInvocation.invoke();
        String actionClassName = actionInvocation.getAction().getClass().getName();
        String actionMethodName = actionInvocation.getProxy().getMethod();
        HttpSession session = ServletActionContext.getRequest().getSession();

        return null;
    }
}
