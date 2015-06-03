package utils.paramter;

import utils.StringUtils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件名称: DateUtils.java
 * 编写人: yh.zeng
 * 编写时间: 14-3-4
 * 文件描述: Request参数获取工具类
 */
public class ParameterUtils {

    final static String ENCODES[] = new String[]{"UTF-8","GB2312","GBK"};

    /**
   	 * 字符串转码 ，获取能正常显示的字符
   	 * @param str
   	 * @return
   	 */
   	public static String encodeStr(String str){
           String orginStr = str;

           for (String encode : ENCODES) {
               try {
                   str = new String(orginStr.getBytes("ISO-8859-1"), encode);
                   if (!StringUtils.isMessyCode(str)) {
                       break;
                   }
               } catch (UnsupportedEncodingException e) {
               }
           }


   		return StringUtils.isMessyCode(str) ? orginStr : str;
   	}

	/**
	 * 将request请求传递的paramName转换成int类型，默认值为 defaultValue
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 */
	public static int parameter2Int(HttpServletRequest request,
			String paramName,int defaultValue){
		int value ;

		if(request.getParameter(paramName) != null){
			String _value = request.getParameter(paramName);
            _value = encodeStr(_value);
			value = StringUtils.toInteger(_value.trim());
		}else{
			value = defaultValue;
		}

		return value;

	}

	/**
	 * 将request请求传递的paramName转换成String类型，默认值为 defaultValue
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 */
	public static String parameter2String(HttpServletRequest request,
			String paramName,String defaultValue){
		String value = null;

		if(request.getParameter(paramName) != null){

			String _value = request.getParameter(paramName);
            _value = encodeStr(_value);
			value = _value.trim();
		}else{
			value = defaultValue;
		}

		return value;

	}

	/**
	 * 将request请求传递的paramName转换成Double类型，默认值为 defaultValue
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 */
	public static Double parameter2Double(HttpServletRequest request,
			String paramName,Double defaultValue){
		Double value = null;

		if(request.getParameter(paramName) != null){
            String _value = request.getParameter(paramName);
            _value = encodeStr(_value);
            value = StringUtils.toDouble(_value.trim());
        }else{
			value = defaultValue;
		}

		return value;
	}

}
