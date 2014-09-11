package webservice.net.ilkj.soap.server;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.File;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: yh.zeng
 * Date: 14-7-17
 * Time: 上午9:17
 * To change this template use File | Settings | File Templates.
 */
@WebService(targetNamespace = "http://client.soap.ilkj.net.webservice")
public class HelloServiceImpl implements IHelloService {

    @Resource
    private WebServiceContext context;

    @Override
    public Customer selectMaxAgeStudent(Customer c1, Customer c2) {
        if (c1.getBirthday().getTime() > c2.getBirthday().getTime()) {
            return c2;
        } else {
            return c1;
        }
    }

    @Override
    public Customer selectMaxLongNameStudent(Customer c1, Customer c2) {
        if (c1.getName().length() > c2.getName().length()) {
            return c1;
        } else {
            return c2;
        }
    }

    @Override
    public void testWebServiceContext() {
        MessageContext mcontext = context.getMessageContext();
        Set<String> set = mcontext.keySet();
        for (String key : set) {
            System.out.println("************" + key + "\t" + mcontext.get(key));
            try {
                System.out.println("***********+" + mcontext.getScope(key));
            } catch (Exception e) {
                System.out.println("************" + key + "is not exists");
            }
        }
    }

    @Override
    public Customer getCustomerWithAttachment() {
        Customer customer = new Customer();
        customer.setId(3);
        customer.setName("customer has attachments");
        customer.setImageData(new DataHandler(new FileDataSource(new File("C:/Users/ad/Desktop/baidu.png"))));

        return customer;
    }
}
