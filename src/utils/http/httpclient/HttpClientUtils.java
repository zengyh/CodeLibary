package utils.http.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import utils.PropertyUtils;
import utils.StringUtils;


/**
 * 文件名称: HttpClientUtils.java
 * 编写人: yh.zeng
 * 编写时间: 13-5-3
 * 文件描述: HttpClient抓取页面工具类
 */
public class HttpClientUtils {
	
	public enum RequestMethod{
		POST,
		GET
	}
	
	public static final  String CHARSET_NAME = PropertyUtils.getValue( "global.properties", "charsetName" );
	
	
	public static String[] getPageContent(String[] urls){
		
		String contents[] = new String[urls.length];
		
		//线程数
		int threadNumber = StringUtils.toInteger( PropertyUtils.getValue( "global.properties", "threadNumber" ) );
		//队列数
		int maxQueueSize = StringUtils.toInteger(PropertyUtils.getValue("global.properties", "maxQueueSize"));
		//建立线程池
	    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
	    		threadNumber, threadNumber,
				0, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(maxQueueSize, false),
				new ThreadPoolExecutor.CallerRunsPolicy()
	    );
	    
	    CountDownLatch countDownLatch = new CountDownLatch(urls.length);  //栅栏
	    Job[] jobs = new Job[urls.length];
	    
	    for(int i = 0 ;i < urls.length; i++){
	    	jobs[i] = new Job(urls[i], countDownLatch);
	    	threadPool.execute(jobs[i]);
	    }
	    
	    try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < jobs.length; i++){
			contents[i] = jobs[i].getContent();
		}
		return contents;
	}
	
	
	/**
	 * 获取页面内容,若使用GET方法，paramsMap 可以为null
	 * @param url         地址
	 * @param method      请求方法POST或GET
	 * @param paramsMap   请求参数
	 * @return            请求的页面内容
	 */
	@SuppressWarnings("deprecation")
	public static String getPageContent(String url, RequestMethod method, Map<String,String> paramsMap){
	
		DefaultHttpClient client = new DefaultHttpClient();
		
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2*60000); //连接超时2分钟
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10*60000); //数据传输超时时间10分钟
		
		//设置异常恢复机制
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception,
					int executionCount, HttpContext context) {
				if (executionCount >= 5) {
					// 如果超过最大重试次数，那么就不要继续了
					return false;
				}
				if (exception instanceof NoHttpResponseException) {
					// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {
					// 不要重试SSL握手异常
					return false;
				}
				HttpRequest request = (HttpRequest) context
						.getAttribute(ExecutionContext.HTTP_REQUEST);
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// 如果请求被认为是幂等的，那么就重试
					return true;
				}
				return false;
			}
		};
        client.setHttpRequestRetryHandler(myRetryHandler);
		
		HttpResponse response = null;
		String content = null;
		
		try{

			switch(method){
		     	case  POST :
		     		  List<NameValuePair> params = getParams2List(paramsMap);
					  HttpPost post = new HttpPost(url);
				      post.setEntity(new UrlEncodedFormEntity(params, CHARSET_NAME));
				   
				      response = client.execute(post);
		     		  break;
		     	case  GET:
		     		  HttpGet request = null;
		     		  if(paramsMap != null){
			     		  String paramsStr = getParams2String(paramsMap);
			     		  request = new HttpGet(url+paramsStr); 
		     		  }else{
		     			  request = new HttpGet(url); 
		     		  }
		     		  response = client.execute(request);
		     		  break;
			}
			

			content = getContentFromInputStream(response.getEntity().getContent());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			client.getConnectionManager().shutdown(); // 关闭连接,释放资源
		}

		return content;
	    
	}
	
	
	/**
	 * 将Map<String,String>封装的参数转变成NameValuePair类型的List参数列表
	 * @param paramsMap     通过Map<String,String>封装的参数
	 * @return              NameValuePair类型的List参数列表
	 */
	public static List<NameValuePair> getParams2List(Map<String,String> paramsMap){
		
	    //设置传递的参数	
	    List<NameValuePair> params = new ArrayList<NameValuePair>(1);
	      
	    Iterator<String> paramsKeys = paramsMap.keySet().iterator();
	    while(paramsKeys.hasNext()){
	    	  String key = paramsKeys.next();
	    	  String value = paramsMap.get(key);
		      params.add(new BasicNameValuePair(key, value));
	    }
	    
	    return params;
		
	}
	
	/**
	 * 将Map<String,String>封装的参数转化成字符串形式
	 * @param paramsMap    通过Map<String,String>封装的参数
	 * @return             ?a=1&b=2  形式的参数字符串
	 */
    public static String getParams2String(Map<String,String> paramsMap){
		
	    //设置传递的参数	
	    StringBuffer params = new StringBuffer();
	    params.append("?");  
	    
	    int i = 1;
	    Iterator<String> paramsKeys = paramsMap.keySet().iterator();
	    while(paramsKeys.hasNext()){
	    	  if(i != 1){
	    		  params.append("&");
	    	  }
	    	  String key = URLEncoder.encode(paramsKeys.next());
	    	  String value = URLEncoder.encode(paramsMap.get(key));
		      params.append(key+"="+value);
		      
		      i++;
	    }
	    
	    return params.toString();
		
	}
	
	/**
	 * 从InputStream输入流中读取内容
	 * @param inputStream
	 * @return              从inputStream输入流中读取到的内容
	 */
	public static String getContentFromInputStream(InputStream inputStream){
	   
		StringBuffer buffer = new StringBuffer();
		
	    byte[]  bytes  =  new  byte[1024 *  5];   //输出流的缓冲区 5kb
	      
	    int len;
	    try {
			while ((len = inputStream.read(bytes))!=-1) {
			     buffer.append(new String(bytes, 0, len, CHARSET_NAME));
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
		
		return buffer.toString();
	}
	
	
    static class Job implements Runnable{
    	
    	private String url;
    	private CountDownLatch countDownLatch;
    	private String content;
    	
    	public Job(String url, CountDownLatch countDownLatch){
    		this.url = url;
    		this.countDownLatch = countDownLatch;
    	}

		public void run() {
			// TODO Auto-generated method stub
			content = getPageContent(url, RequestMethod.GET, null);
			countDownLatch.countDown();
		}
		
		public String getContent(){
			return this.content;
		}
    	
    }
	
	
}
