package annotation;

import  annotation.NewAnnotation.EnumTest;

public class Person {
    @NewAnnotation
	String name;
    @NewAnnotation(value= EnumTest.LiXiaoLong)
	String age;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
    
}
