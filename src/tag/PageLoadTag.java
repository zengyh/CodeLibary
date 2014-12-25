package tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.lang.reflect.Method;

/**
 * 文件名称: PageLoadTag.java
 * 编写人: yh.zeng
 * 编写时间: 14-12-25 下午4:00
 * 文件描述: 页面加载初始化 TAG
 */
public class PageLoadTag extends BodyTagSupport
{
    Logger logger = LoggerFactory.getLogger(PageLoadTag.class);

    private String clazz;   //类名

    private String method;  //方法名

    @Override
    public int doStartTag() throws JspException {

        return  BodyTagSupport.SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {

        method = method == null ? "loadPage" : method;

        try {
            Class _clazz  = Class.forName(clazz);
            Method _method = _clazz.getMethod(method,  PageContext.class);
            _method.invoke(_clazz.newInstance(), this.pageContext);

        } catch (Exception e) {
            logger.info(StringUtils.getExceptionMessage(e));
        }


        return BodyTagSupport.EVAL_PAGE;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
