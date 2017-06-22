package utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {
	
	/**
	 * 将对象转换成JSON字符串
	 * @param object
	 * @return
	 */
    public static String converToJsonStr(Object object){
    	if(object == null){
    		return null;
    	}else{
    		if(object instanceof List || object.getClass().isArray()){
    			return JSONObject.fromObject(object).toString();
    		}else{
    			return JSONArray.fromObject(object).toString();
    		}
    		
    	}
    }
}
