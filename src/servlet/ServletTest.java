package servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件名称: ServletTest.java
 * 编写人: yh.zeng
 * 编写时间: 16-6-23 下午10:40
 * 文件描述: todo
 */
public class ServletTest extends HttpServlet
{
    private Logger logger =  Logger.getLogger(ServletTest.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("ServletTest[doGet]");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("ServletTest[doPost]");
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("ServletTest[Service]");
        super.service(req, resp);
    }

    @Override
    public void init() throws ServletException {
        logger.info("ServletTest[init]");
        super.init();

    }

    @Override
    public void destroy() {
        logger.info("ServletTest[destroy]");
        super.destroy();
    }
}
