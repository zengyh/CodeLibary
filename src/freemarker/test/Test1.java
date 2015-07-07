package freemarker.test;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件名称: Test1.java
 * 编写人: yh.zeng
 * 编写时间: 15-7-7 上午11:38
 * 文件描述: freemarker 简单使用例子
 */
public class Test1
{

    private Configuration cfg;            //模版配置对象

    public void init() throws Exception {
        //初始化FreeMarker配置
        //创建一个Configuration实例
        cfg = new Configuration();


        //设置模板文件所在的目录
        cfg.setClassForTemplateLoading(this.getClass(), "/freemarker/ftl");

/*      //设置模板文件所在的目录
        cfg.setServletContextForTemplateLoading(getServletContext(),
                "WEB-INF/templates");*/
    }

    public void process() throws Exception {
        //构造填充数据的Map
        Map map = new HashMap();
        map.put("user", "yh.zeng");
        //创建模版对象
        Template t = cfg.getTemplate("helloworld.ftl");
        //在模版上执行插值操作，并输出到制定的输出流中
        t.process(map, new OutputStreamWriter(System.out));
    }

    public static void main(String[] args) throws Exception {
        Test1 hf = new Test1();
        hf.init();
        hf.process();
    }
}

