package webservice.net.ilkj.soap.client;

import com.opensymphony.xwork2.interceptor.LoggingInterceptor;
import com.sun.jmx.remote.protocol.rmi.ServerProvider;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.sun.security.sasl.ServerFactoryImpl;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import webservice.net.ilkj.soap.client.security.ClientPasswordCallbackHandler;

import javax.activation.DataHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: yh.zeng
 * Date: 14-7-17
 * Time: 下午2:50
 * To change this template use File | Settings | File Templates.
 */
public class SoapClient {

    public static void main(String args[]) throws Exception {

        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
       // paramsMap.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT); //明文方式发送密码
        paramsMap.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST); //MD5加密发送
        paramsMap.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallbackHandler.class.getName());
        paramsMap.put(WSHandlerConstants.USER, "Fetion");//默认的用户名 ，这行代码必须要有，否则报错
        WSS4JOutInterceptor  wss4JOutInterceptor = new WSS4JOutInterceptor(paramsMap);

        IHelloService helloService = new HelloServiceImplService().getHelloServiceImplPort();
        Client client = ClientProxy.getClient(helloService);
        client.getOutInterceptors().add(wss4JOutInterceptor);   //添加用户名令牌机制
        client.getOutInterceptors().add(new LoggingOutInterceptor());

        Customer c1 = new Customer();
        c1.setId(1);
        c1.setName("A");
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("1989-01-28"));
        c1.setBirthday(new XMLGregorianCalendarImpl(calendar));

        Customer c2 = new Customer();
        c2.setId(1);
        c2.setName("BB");
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-28"));
        c2.setBirthday(new XMLGregorianCalendarImpl(calendar));

        Customer customer = helloService.selectMaxAgeStudent(c1, c2);
        System.out.println(customer.getName());

        customer = helloService.selectMaxLongNameStudent(c1, c2);
        System.out.println(customer.getName());

        helloService.testWebServiceContext();

        customer = helloService.getCustomerWithAttachment();
        System.out.println("*************name\t" + customer.getName());
        DataHandler data = customer.getImageData();
        System.out.println("*************contentType\t" + data.getContentType());


        FileOutputStream fos = new FileOutputStream(new File("C:/Users/ad/Desktop/tem.png"));
        data.writeTo(fos);
        fos.flush();
        fos.close();
    }
}
