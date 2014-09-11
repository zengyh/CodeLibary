package webservice.net.ilkj.servlet;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: yh.zeng
 * Date: 14-7-18
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 */
public class MyCXFNoSpringServlet extends CXFNonSpringServlet {

    @Override
    public void loadBus(ServletConfig servletConfig) throws ServletException {
        super.loadBus(servletConfig);    //To change body of overridden methods use File | Settings | File Templates.
        Bus bus = this.getBus();
        BusFactory.setDefaultBus(bus);

        Enumeration<String> enumeration = this.getInitParameterNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = this.getInitParameter(key);
            JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
            try {
                Class clazz = Class.forName(value);
                factoryBean.setServiceClass(clazz);
                // factoryBean.setServiceBean(clazz.newInstance());
                factoryBean.setAddress(key);
                factoryBean.getInInterceptors().add(new LoggingInInterceptor());
                factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
                Server server = factoryBean.create();
                server.start();
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

}
