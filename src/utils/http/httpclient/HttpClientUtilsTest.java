package utils.http.httpclient;


/**
 * 文件名称: HttpClientUtilsTest.java
 * 编写人: yh.zeng
 * 编写时间: 13-5-3
 * 文件描述: HttpClientUtils测试类
 */
public class HttpClientUtilsTest
{

    public static void main( String args[] )
    {

        //1 多线程方式抓取页面内容
        System.out.println(HttpClientUtils.getPageContent( new String[]{"http://www.cnhnb.com/"} )[0]);

        //2 单线程方式
        System.out.println(HttpClientUtils.getPageContent( "http://www.cnhnb.com/", HttpClientUtils.RequestMethod.GET, null ));

    }

}
