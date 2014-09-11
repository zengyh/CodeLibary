package excel.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * <p>导出excel 工具包</p>
 * @author yh.zeng
 * @version 1.0
 *
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
public @interface AllowExcel {

	boolean value() default true;
	
	String name();
	
}
