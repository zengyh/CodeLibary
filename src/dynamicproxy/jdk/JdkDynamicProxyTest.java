package dynamicproxy.jdk;

import java.lang.reflect.Proxy;

/**
 * 文件名称: JdkDynamicProxyTest.java
 * 编写人: yh.zeng
 * 编写时间: 17-1-18 下午6:24
 * 文件描述: todo
 */
public class JdkDynamicProxyTest
{

    public static void main(String args[]) {
        //无构造参数demo
        ISayHello sayHello = (ISayHello)  new JdkDynamicProxyHandler(SayHelloImpl.class).getProxyObj();
        sayHello.sayHello();

        //有构造参数的demo
        ISayHello sayHello2 = (ISayHello)  new JdkDynamicProxyHandler(SayHelloImp2.class,new Object[]{"Hello World2！"}).getProxyObj();
        sayHello2.sayHello();
    }

}
