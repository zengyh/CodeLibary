package dynamicproxy.jdk;

/**
 * 文件名称: SayHelloImp2.java
 * 编写人: yh.zeng
 * 编写时间: 17-1-18 下午7:19
 * 文件描述: todo
 */
public class SayHelloImp2  implements ISayHello
{
    private  String word = null;

    public  SayHelloImp2(String word){
        this.word = word;
    }

    @Override
    public void sayHello() {
        System.out.println(word);
    }
}
