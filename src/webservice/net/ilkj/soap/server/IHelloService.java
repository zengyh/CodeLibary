package webservice.net.ilkj.soap.server;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

/**
 * Created by IntelliJ IDEA.
 * User: yh.zeng
 * Date: 14-7-16
 * Time: 下午5:57
 * To change this template use File | Settings | File Templates.
 */
@WebService(targetNamespace="http://client.soap.ilkj.net.webservice")
@MTOM
public interface IHelloService {

     Customer selectMaxAgeStudent( @WebParam( name = "c1" ) Customer c1, @WebParam( name = "c2" ) Customer c2 );

     Customer selectMaxLongNameStudent( @WebParam( name = "c1" ) Customer c1, @WebParam( name = "c2" ) Customer c2 );

     void testWebServiceContext();

     Customer getCustomerWithAttachment();
}
