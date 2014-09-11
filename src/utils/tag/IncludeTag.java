package utils.tag;

import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;

/**
 * 文件名称: IncludeTag.java
 * 编写人: yh.zeng
 * 编写时间: 14-8-16 下午4:40
 * 文件描述: todo
 */
public class IncludeTag extends javax.servlet.jsp.tagext.BodyTagSupport
{

    private StringBuffer uri = new StringBuffer();

    public void setUri(String uri) {
//        this.uri.append(((HttpServletRequest) this.pageContext.getRequest()).getContextPath());
//        this.uri.append(uri.startsWith("/") ? "" : "/");
        this.uri.append(uri);
    }

    public void addParameter(String name, String value) {
        if ( StringUtils.isEmpty( name ) || StringUtils.isEmpty( value )) {
            return;
        }
        if (this.uri.indexOf("?") == -1) {
            this.uri.append("?");
        } else {
            this.uri.append("&");
        }
        this.uri.append(name);
        this.uri.append("=");
        this.uri.append(value);
    }


    @Override
    public int doEndTag() throws JspException
    {
        try {
            this.pageContext.include(this.uri.toString());
            this.uri = new StringBuffer();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Tag.EVAL_PAGE;
    }
}
