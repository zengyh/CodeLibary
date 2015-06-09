package support.struts2.config.providers;

import com.opensymphony.xwork2.config.providers.XmlConfigurationProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * 文件名称: CustomConfigurationProvider.java
 * 编写人: yh.zeng
 * 编写时间: 15-6-9 上午11:04
 * 文件描述: 按照spring的原理让struts2自动加载模块的struts的配置文件
 */
public class CustomConfigurationProvider extends XmlConfigurationProvider
{
    private static String configFilePattern = "classpath*:strutsCfg/struts-*.xml";

    public static void setConfigFilePattern(String pattern) {
        CustomConfigurationProvider.configFilePattern = pattern;
    }

    public CustomConfigurationProvider() {
        Map<String, String> mappings = new HashMap<String, String>();
        mappings.put("-//OpenSymphony Group//XWork 2.1.3//EN", "xwork-2.1.3.dtd");
        mappings.put("-//OpenSymphony Group//XWork 2.1//EN", "xwork-2.1.dtd");
        mappings.put("-//OpenSymphony Group//XWork 2.0//EN", "xwork-2.0.dtd");
        mappings.put("-//OpenSymphony Group//XWork 1.1.1//EN", "xwork-1.1.1.dtd");
        mappings.put("-//OpenSymphony Group//XWork 1.1//EN", "xwork-1.1.dtd");
        mappings.put("-//OpenSymphony Group//XWork 1.0//EN", "xwork-1.0.dtd");
        mappings.put("-//Apache Software Foundation//DTD Struts Configuration 2.0//EN", "struts-2.0.dtd");
        mappings.put("-//Apache Software Foundation//DTD Struts Configuration 2.1//EN", "struts-2.1.dtd");
        mappings.put("-//Apache Software Foundation//DTD Struts Configuration 2.1.7//EN", "struts-2.1.7.dtd");
        setDtdMappings(mappings);
    }

    @Override
    public boolean needsReload() {
        return true;
    }

    @Override
    protected Iterator<URL> getConfigurationUrls(String fileName) throws IOException {
        List<URL> urls = new ArrayList<URL>();
        Resource[] resources = getAllResourcesUrl();
        for (Resource resource : resources) {
            urls.add(resource.getURL());
        }

        return urls.iterator();
    }

    /**
     * 获取系统中需要搜寻的struts的配置
     *
     * @return
     * @throws java.io.IOException
     */
    private Resource[] getAllResourcesUrl() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            return resolver.getResources(configFilePattern);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Resource[0];
    }
}
