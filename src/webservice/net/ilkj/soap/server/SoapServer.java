package webservice.net.ilkj.soap.server;


import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import javax.xml.ws.Endpoint;

/**
 * Created by IntelliJ IDEA.
 * User: yh.zeng
 * Date: 14-7-17
 * Time: 上午9:25
 * To change this template use File | Settings | File Templates.
 */
public class SoapServer {

    public static void main(String args[]){
   /* 方式一                                                                          */
        Endpoint.publish("http://127.0.0.1:8080/webServices/helloService",new HelloServiceImpl());

   /*  方式二
       JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setAddress("http://127.0.0.1:8080/webServices/helloService");
        factoryBean.setServiceClass(HelloServiceImpl.class);
        factoryBean.setServiceBean(new HelloServiceImpl());

        factoryBean.getInInterceptors().add(new LoggingInInterceptor());
        factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());

        factoryBean.create().start();*/
    }
}
