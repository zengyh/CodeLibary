package utils.http.htmlunit;

import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import utils.http.jsoup.HTMLParserUtils;

/**
 * 文件名称: HTMLUnitUtils.java
 * 编写人: yh.zeng
 * 编写时间: 13-11-23
 * 文件描述: HtmlUnit抓取页面工具类
 */
public class HTMLUnitUtils {
	
	public static HtmlPage getPage(String url){
		
		WebClient webClient = new WebClient();
		HtmlPage page = null;
		try {
		    webClient.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
		    webClient.getOptions().setCssEnabled(false); //禁用css支持  
		    webClient.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
		    webClient.getOptions().setTimeout(10000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
			page = webClient.getPage(url);
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
		
		return page;
	}
	
	public static String getPageContent(String url){
		HtmlPage page = getPage(url);
        return page == null ? "" : page.asXml();
	}
	
	public static Document getDocument(String url){
		String pageContent = getPageContent(url);
		return HTMLParserUtils.getDocumentByContent( pageContent );
	}

}
