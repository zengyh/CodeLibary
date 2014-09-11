package utils.http.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.StringUtils;
import utils.http.htmlunit.HTMLUnitUtils;

/**
 * 文件名称: HTMLParserUtils.java
 * 编写人: yh.zeng
 * 编写时间: 13-11-23
 * 文件描述: Jsoup解析页面工具类
 */
public class HTMLParserUtils {

	private static final String CHARSET = "UTF-8";
	
	
	public static Document getDocumentByUrl(String url){
		Document doc = null;
		 try {
			doc = Jsoup.connect(url).timeout(10000).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return doc;
		
	}
	
	
	public static Document getDocumentByUrl2(String url){
	    return  HTMLUnitUtils.getDocument( url );
	}
	
	
	public static Document getDocumentByContent(String html){
		return Jsoup.parse(html); 
	}
	
	
	public static Document getDocument(File file){
		Document doc = null;
		try {
		    doc =  Jsoup.parse(file, CHARSET);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
	}
	
	/**
	 * 和JQUERY相似，根据选择器对元素进行检索
	 * @param document  文档类对象
	 * @param selector  类似于JQUERY的选择器
	 * @return
	 */
	public static Element getElement(Document document,String selector){
		
		Elements els = document.select(selector);
		
		if(els != null && !els.isEmpty()){
			return els.get(0);
		}else{
			return null;
		}
		
	}
	
	
    /**
     * 和JQUERY相似，根据选择器对元素进行检索,返回检索到的节点的outerHTML
     * @param url        文档URL地址
     * @param selector   类似于JQUERY的选择器
     * @return
     */
	public static String getOuterHTML(String url,String selector){
		
		String outerHTML = null;
			
		Document document = getDocumentByUrl(url);
		Elements els = document.select(selector);

		if (els != null && !els.isEmpty()) {

			resetLinks(url, els.get(0));

			outerHTML = els.get(0).outerHtml();
		}
		
		return outerHTML;

		
	}


	/**
	 * 重新设置element节点以及所包含的子节点的链接路径，主要是解决链接路径是使用绝对路径的情况
	 * @param url       页面的链接地址
	 * @param element   要重新设置链接路径的节点
	 */
	public static void resetLinks(String url, Element element) {
		// TODO Auto-generated method stub
		resetFormLinks(url, element);
		resetALinks(url, element);
		resetIMGLinks(url, element);
	}

	private static void resetIMGLinks(String url, Element element) {
		// TODO Auto-generated method stub
		Elements els = element.select("img");
		
		for(Element el : els){
			String _url = el.attr("src");
			if(_url != null){
				el.attr("src",getAbsolutePath(url,_url));
			}
		}
	}


	private static void resetALinks(String url, Element element) {
		// TODO Auto-generated method stub
		Elements els = element.select("a");
		
		for(Element el : els){
			String _url = el.attr("href");
			if(_url != null){
				el.attr("href",getAbsolutePath(url,_url));
			}
		}
	}


	private static void resetFormLinks(String url, Element element) {
		// TODO Auto-generated method stub
		Elements els = element.select("form");
		
		for(Element el : els){
			String _url = el.attr("action");
			if(_url != null){
				el.attr("action",getAbsolutePath(url,_url));
			}
		}
		
	}


	/**
	 * 获取相对路径的绝对路径
	 * @param path          该相对路径基于的路径
	 * @param relativePath  相对路径
	 * @return
	 */
	public static String getAbsolutePath(String path, String relativePath) {
		// TODO Auto-generated method stub
		String thePath = null;
		
		if(relativePath.indexOf("./") == 0 || relativePath.indexOf("/") == 0){
			path = StringUtils.subStringFromTail( path, "/", 1 );
			relativePath = StringUtils.subStringFromHead(relativePath, "/", 1);
			
			thePath = getAbsolutePath(path, relativePath);
		    
		}else if(relativePath.indexOf("../") == 0){
			path = StringUtils.subStringFromTail(path, "/", 2);
			relativePath = StringUtils.subStringFromHead(relativePath, "../", 1);
			
			thePath = getAbsolutePath(path, relativePath);
		
		}else{
			if(relativePath.indexOf("#") == -1
					&& relativePath.indexOf("javascript:") == -1
					&& relativePath.indexOf("JAVASCRIPT:") == -1
					&& relativePath.indexOf("http:") == -1
					&& relativePath.indexOf("HTTP:") == -1
					&& relativePath.indexOf("https:") == -1
					&& relativePath.indexOf("HTTPS:") == -1
			 ){
				thePath = path+"/" + relativePath;
			}else{
				thePath = relativePath;
			}
		}
		
		return thePath;
	}


	/**
	 * 获取url地址的域名
	 * @param url
	 * @return
	 */
	private static String getDomainName(String url) {
		// TODO Auto-generated method stub
		
		String domainName = null;
		
		if(url != null){
			if(url.indexOf("//") > -1){
				String subString = url.substring(url.indexOf("//") + 2);
				if(subString.indexOf("/") > -1){
					domainName = subString.substring(0,subString.indexOf("/"));
				}
			}
			
		}
		
		return domainName;
	}
	
}
