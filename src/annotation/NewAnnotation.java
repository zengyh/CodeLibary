package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NewAnnotation {  
    EnumTest value() default EnumTest.ChengZhen;
}
enum EnumTest{LiXiaoLong,ChengZhen,WangFeiHong};