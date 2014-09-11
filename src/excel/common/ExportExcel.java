package excel.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 导出工具类
 * @author yh.zeng
 */
public class ExportExcel<T extends Serializable> {

	private HSSFWorkbook hw = new HSSFWorkbook();

	/**
	 * 生成sheet 名称
	 */
	private String sheetName = "shtteName";

	private boolean showHeader = true;

	/**
	 * 缓存成员变量
	 */
	private List<String> fieldNameCaches = new ArrayList<String>();


	public void createHeader(Collection<T> data) throws IllegalAccessException {
		//Sheet 创建工作表
		HSSFSheet sheet = hw.createSheet(sheetName);

		int j = 0;
		for (T t:data) {
			Field[] fields = t.getClass().getDeclaredFields();
			/**
			 * 加入允许字段缓存数据
			 * if == 0时表示要添加缓存数据
			 */
			if (j == 0) {
				HSSFRow headRow = null;
				if (this.showHeader) {
					headRow = sheet.createRow(0);
				}
				int i = 0;
				for (Field field:fields) {
					//判断Excel 安全允许注解
					AllowExcel allowExcel = field.getAnnotation(AllowExcel.class);
					if (allowExcel != null && allowExcel.value()) {
						//显示头部信息
						if (this.showHeader) {
							HSSFCell cell = headRow.createCell(i-1);
							cell.setCellValue(allowExcel.name());
						}
						this.fieldNameCaches.add(field.getName());
						System.out.println(allowExcel.name());
					}
					i++;
				}
			}
			j++;

			//创建产生行数据
		    HSSFRow hssfRow = sheet.createRow(j);
		    this.setCellValueToRow(t, hssfRow);
		}
	}

	/**
	 * 输出Excel Row 信息
	 * @param t T extends Serializable
	 * @param hssfRow HSSFRow
	 * @return HSSFRow
	 * @throws IllegalAccessException
	 */
	public HSSFRow setCellValueToRow(T t,HSSFRow hssfRow) throws IllegalAccessException {
		Field fields[] = t.getClass().getDeclaredFields();
		//定义Excel 输出行数
		int i = 0;
		for (Field field:fields) {
			//缓存中是否存在允许字段
			if (this.isCacheFiledName(field.getName())) {
				HSSFCell cell = hssfRow.createCell(i);
				i++;
				field.setAccessible(true);
				Object obj = field.get(t);
				//类型转换
				if (obj instanceof Integer ) {
					cell.setCellValue((Integer)obj);
				}
				else if (obj instanceof String) {
					cell.setCellValue((String)obj);
				}
				else if (obj instanceof Date) {
					cell.setCellValue((Date)obj);
				}
				else if (obj instanceof Double) {
					cell.setCellValue((Double)obj);
				}
				else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean)obj);
				}
				else if (obj instanceof Float) {
					cell.setCellValue((Float)obj);
				}
				else if (obj instanceof Long) {
					cell.setCellValue((Long)obj);
				}
				else {
					throw new TypeNotPresentException("类型不支持", null);
				}

			}
		}
		return hssfRow;
	}

	/**
	 * 判断Cache 是否有对应的FiledName
	 * @param fieldName String
	 * @return boolean
	 */
	public boolean isCacheFiledName(String fieldName) {
		if (fieldName == null) {
			return false;
		}
		for (String fieldNameCache:this.fieldNameCaches) {
			if (fieldName.equals(fieldNameCache)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String args[]) throws IllegalAccessException {
		ExportExcel<Student> export = new ExportExcel<Student>();
		List<Student> list = new ArrayList<Student>();
		Student stu = new Student();
		stu.setId(123);
		stu.setDate(new Date());
		stu.setName("liquing");
		Student stu1 = new Student();
		stu1.setId(1231);
		stu1.setDate(new Date());
		stu1.setName("刘海");
		Student stu2 = new Student();
		stu2.setId(1231);
		stu2.setDate(new Date());
		stu2.setName("liquing1");
		list.add(stu);
		list.add(stu1);
		list.add(stu2);
		export.createHeader(list);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("e:\\um.xls");
			export.hw.write(out);
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if (out != null) {
				try {
					out.close();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 查找对应的类自定义方法
	 * @param methodName
	 * @return boolean
	 */
	public boolean isClassMethod(String methodName) {
		if (methodName != null) {
			if ("getClass".equals(methodName)) {
				return false;
			}
			if (methodName.startsWith("get")
					|| methodName.startsWith("is")
					|| methodName.startsWith("set")) {
				return true;
			}
			return false;
		}
		return false;
	}

}