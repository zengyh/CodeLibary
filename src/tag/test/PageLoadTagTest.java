package tag.test;

import javax.servlet.jsp.PageContext;

/**
 * 文件名称: PageLoadTagTest.java
 * 编写人: yh.zeng
 * 编写时间: 14-12-25 下午4:31
 * 文件描述: 测试PageLoadTag
 */
public class PageLoadTagTest
{

    public void loadPage(PageContext pageContext){
        pageContext.getRequest().setAttribute("msg", "invoke the method tag.test.PageLoadTagTest.loadPage");
    }

}
