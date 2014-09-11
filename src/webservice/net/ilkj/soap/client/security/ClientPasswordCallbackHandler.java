package webservice.net.ilkj.soap.client.security;

import org.apache.ws.security.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: yh.zeng
 * Date: 14-7-19
 * Time: 下午1:29
 * To change this template use File | Settings | File Templates.
 */
public class ClientPasswordCallbackHandler implements CallbackHandler{

    private final String USER = "Fetion2";

    private final String PASSWORD = "Fetion";

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        WSPasswordCallback wsPasswordCallback = (WSPasswordCallback)callbacks[0];
        wsPasswordCallback.setIdentifier(USER);   //用户名
        wsPasswordCallback.setPassword(PASSWORD);  //密码

    }
}
