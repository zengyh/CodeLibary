package dynamicproxy.jdk;

/**
 * 文件名称: SayHelloImpl.java
 * 编写人: yh.zeng
 * 编写时间: 17-1-18 下午6:15
 * 文件描述: todo
 */
public class SayHelloImpl implements ISayHello
{
    private String word = null;

    @Override
    public void sayHello() {
        System.out.println("Hello World！");
    }

}
