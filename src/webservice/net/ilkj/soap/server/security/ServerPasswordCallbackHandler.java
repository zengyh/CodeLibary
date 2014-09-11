package webservice.net.ilkj.soap.server.security;

import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.WSSecurityException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: yh.zeng
 * Date: 14-7-19
 * Time: 下午1:00
 * To change this template use File | Settings | File Templates.
 */
public class ServerPasswordCallbackHandler implements CallbackHandler {

    private static String USER = "Fetion2";
    private static String PASSWORD = "Fetion";


    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback wsPasswordCallback = (WSPasswordCallback) callbacks[0];
        System.out.println("用户名：\t"+wsPasswordCallback.getIdentifer() + "\t密码：" + wsPasswordCallback.getPassword());

        if (WSConstants.PW_TEXT.equals(wsPasswordCallback.getPasswordType())) {  //密码传送发方式为明文
            if (wsPasswordCallback.getIdentifer().equals(USER)
                    && wsPasswordCallback.getPassword().equals(PASSWORD)) {

            } else {
                System.out.println("No Permission");
                throw new WSSecurityException("No Permission");
            }

        } else {   //密码传送方式为MD5加密，因为MD5加密接收到的password和passwordType都为null，但是能够接收到username，根据官方文档用下面的代码方式验证
            if (wsPasswordCallback.getIdentifer().equals(USER)) {
                wsPasswordCallback.setPassword(PASSWORD);
            } else {
                System.out.println("No Permission");
                throw new WSSecurityException("No Permission");
            }

        }

    }
}
