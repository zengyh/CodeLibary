package dynamicproxy.jdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 文件名称: JdkDynamicProxyHandler.java
 * 编写人: yh.zeng
 * 编写时间: 17-1-18 下午6:25
 * 文件描述: 动态代理工具类，通过JDK自带的反射机制包实现
 */
public class JdkDynamicProxyHandler implements InvocationHandler
{
    private Object realObj = null;        //被代理的对象，自动生成
    private Class realClass = null;       //被代理的类，作为参数传入构造方法
    private Object[] realObjArgs = null;  //被代理的类的构造方法的参数，作为参数传入构造方法
    private Object proxyObj = null;       //代理对象，自动生成

    /**
     *
     * @param realClass    被代理的类
     */
    public JdkDynamicProxyHandler(Class realClass){
        this.realClass = realClass;
    }


    /**
     *
     * @param realClass   被代理的类
     * @param args     被代理的类的构造方法的参数
     */
    public JdkDynamicProxyHandler(Class realClass, Object... args){
        this.realClass = realClass;
        this.realObjArgs = args;
    }

    /**
     * 获取代理对象
     * @return  Object
     */
    public synchronized Object getProxyObj(){
        if(proxyObj == null){
            this.proxyObj =  Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), realClass.getInterfaces(), this);
        }
        return proxyObj;
    }

    /**
     * 最终调用被代理的对象的方法的实现代码
     * @param proxy
     * @param method
     * @param args
     * @return 返回被代理对象调用相应方法返回的值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(realObj == null){
            Constructor constructor = null;
            if(realObjArgs != null && realObjArgs.length > 0){ //调用被代理类的有参构造方法进行实例化
                Class clazz[] = new Class[realObjArgs.length];
                for(int i = 0; i < clazz.length; i++){
                    clazz[i] = realObjArgs[i].getClass();
                }
                constructor = realClass.getConstructor(clazz);
                realObj = constructor.newInstance(realObjArgs);
            }else{    //调用被代理类的无参构造方法进行实例化
                constructor = realClass.getConstructor();
                realObj = constructor.newInstance();
            }

        }
        return method.invoke(realObj, args);
    }
}
