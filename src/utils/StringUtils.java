package utils;

import org.hibernate.lob.SerializableBlob;
import org.hibernate.lob.SerializableClob;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件名称: StringUtils.java
 * 编写人: yh.zeng
 * 编写时间: 13-10-29
 * 文件描述: 字符串工具类
 */
public class StringUtils
{

    public static boolean toBoolean(String str) {
        return str.equals("true") ? true : false;
    }

    public static Long toLong(Object object) {
        return object == null || object.toString().equals("") ? null : Long.parseLong(object.toString());
    }

    public static Integer toInteger(Object object) {
        return object == null || object.toString().equals("") ? null : Integer.parseInt(object.toString());
    }

    public static Double toDouble(Object object) {
        return object == null || object.toString().equals("") ? null : Double.parseDouble(object.toString());
    }

    public static Float toFloat(Object object) {
        return object == null || object.toString().equals("") ? null : Float.parseFloat(object.toString());
    }

    public static Short toShort(Object object) {
        return object == null || object.toString().equals("") ? null : Short.parseShort(object.toString());
    }

    public static Byte toByte(Object object) {
        return object == null || object.toString().equals("") ? null : new Byte(object.toString());
    }

    public static Date toDate(Object object) throws ParseException {
        return object == null || object.toString().equals("") ? null : getFormat().parse(object.toString());
    }

    public static Date toDate(Object object, String format) throws ParseException {
        return object == null || object.toString().equals("") ? null : getFormat(format).parse(object.toString());
    }

    public static String getString(Long value) {
        return value == null ? null : Long.toString(value);
    }

    public static String getString(Integer value) {
        return value == null ? null : Integer.toString(value);
    }

    public static String getString(Double value) {
        return value == null ? null : Double.toString(value);
    }

    public static String getString(Float value) {
        return value == null ? null : Float.toString(value);
    }

    public static String getString(Short value) {
        return value == null ? null : Short.toString(value);
    }

    public static String getString(Byte value) {
        return value == null ? null : Byte.toString(value);
    }

    public static String getString(Date date) {
        return date == null ? null : getFormat().format(date);
    }


    public static String getString(Date date, String format) {
        return date == null ? null : getFormat(format).format(date);
    }

    public static SimpleDateFormat getFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static SimpleDateFormat getFormat(String str) {
        return new SimpleDateFormat(str);
    }

    /**
     * 获取字符串的首个大写字母的位置
     *
     * @param str
     * @return
     */
    public static int firstUpperCaseIndex(String str) {

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 从字符串str尾部开始截取掉尾部到identifiers标识的字符串
     * 如：subStringFromTail("f/a/b/c, /, 1)  --->  f/a/b
     * subStringFromTail("f/a/b/c, /, 2)  --->  f/a
     *
     * @param str         要截取的字符串
     * @param identifiers 字符串identifiers标识
     * @param amounts     截取多少次
     * @return
     */
    public static String subStringFromTail(String str, String identifiers, int amounts) {

        if (amounts > 0 && str.indexOf(identifiers) != -1) {
            str = str.substring(0, str.lastIndexOf(identifiers));
            amounts--;
            str = subStringFromTail(str, identifiers, amounts);
        }

        return str;

    }

    /**
     * 从字符串str头部开始截取掉头部到identifiers标识的字符串
     * 如：subStringFromHead("f/a/b/c, /, 1)  --->  a/b/c
     * subStringFromHead("f/a/b/c, /, 2)  --->  /b/c
     *
     * @param str         要截取的字符串
     * @param identifiers 字符串identifiers标识
     * @param amounts     截取多少次
     * @return
     */
    public static String subStringFromHead(String str, String identifiers, int amounts) {

        if (amounts > 0 && str.indexOf(identifiers) != -1) {
            str = str.substring(str.indexOf(identifiers) + identifiers.length());
            amounts--;
            str = subStringFromHead(str, identifiers, amounts);
        }

        return str;

    }

    /**
     * 判断字符串str是否为空字符串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }


    /**
     * 判断num数字是否为空
     *
     * @param num
     * @return
     */
    public static boolean isEmpty(Integer num) {
        return num == null;
    }

    /**
     * 去除orginalStr字符串中的HTML标签
     *
     * @param orginalStr
     * @return
     */
    public static String removeHtmlTag(String orginalStr) {

        return orginalStr == null ? "" : orginalStr.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "");

    }

    /**
     * 判断是字符c是否是中文字符,若是则返回true，其他返回false
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否乱码,若是则返回true，其他返回false
     *
     * @param strName
     * @return
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {

                if (!isChinese(c)) {
                    count = count + 1;
                    // System.out.print(c);
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }

    }

    public static String getStringValue(String str) {
        return (str == null || "null".equals(str)) ? "" : str.trim();
    }

    public static String getClob(SerializableClob c) {
        Reader reader;
        StringBuffer sb = new StringBuffer();
        try {
            reader = c.getCharacterStream();
            BufferedReader br = new BufferedReader(reader);
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public static String getBlob(SerializableBlob b) {
        Reader reader;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new InputStreamReader(b.getBinaryStream());
            BufferedReader br = new BufferedReader(reader);
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * 获取异常信息
     *
     * @param e
     * @return
     */
    public static String getExceptionMessage(Exception e) {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        return stringWriter.toString();
    }


    /**
     * 返回某数字的百分比形式
     *
     * @param object 要转换成百分比形式的数字
     * @param n      转换成百分比形式之后，精确到小数点之后多少位
     * @return
     */
    public static String getPercentStr(Object object, int n) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(n);   //转换成百分比形式之后，精确到小数点之后多少位

        return nf.format(object);
    }


    /**
     * 获取类似 not in (...) 查询条件的语句
     *
     * @param colName    列名
     * @param inValueStr not in 里面的值，以逗号隔开
     * @param operator   条件关系符号，只能是 and 或者是 or 两种值，分别表示 是 and not in(...)，或者是 or not in(...)
     * @return
     */
    private String getLikeNotInWhereCondition(String colName, String inValueStr, String operator) {
        StringBuilder condition = new StringBuilder();
        if (inValueStr != null) {
            condition.append(" ");
            condition.append(operator);
            condition.append(" ( ");
            boolean isFirst = true;
            for (String value : inValueStr.split(",")) {
                if (isFirst) {
                    condition.append(colName).append(" != ").append(value);
                    isFirst = false;
                } else {
                    condition.append(" and ").append(colName).append(" != ").append(value);
                }
            }
            condition.append(" ) ");
        }
        return condition.toString();
    }


    /**
     * 获取类似 in (...) 查询条件的语句
     *
     * @param colName    列名
     * @param inValueStr in 里面的值，以逗号隔开
     * @param operator   条件关系符号，只能是 and 或者是 or 两种值，分别表示 是 and in(...)，或者是 or in(...)
     * @return
     */
    private String getLikeInWhereCondition(String colName, String inValueStr, String operator) {
        StringBuilder condition = new StringBuilder();
        if (inValueStr != null) {
            condition.append(" ");
            condition.append(operator);
            condition.append(" ( ");
            boolean isFirst = true;
            for (String value : inValueStr.split(",")) {
                if (isFirst) {
                    condition.append(colName).append(" = ").append(value);
                    isFirst = false;
                } else {
                    condition.append(" or ").append(colName).append(" = ").append(value);
                }
            }
            condition.append(" ) ");
        }
        return condition.toString();
    }


    /**
     * 字符转码，获取正常显示的字符
     * @param str
     * @return
     */
    public static String encodeStr(String str) {

        String _str = _encodeStr(str, "UTF-8");

        if (_str == null) {
            _str = _encodeStr(str, "GB2312");
        }

        if (_str == null) {
            _str = _encodeStr(str, "GBK");
        }

        return _str == null ? str : _str;

    }


    private static String _encodeStr(String str, String encoding) {
        boolean isSuccess = false;

        try {
            str = new String(str.getBytes("ISO-8859-1"), encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        isSuccess = isMessyCode(str);

        if (!isSuccess) {
            str = null;
        }

        return str;

    }

}
