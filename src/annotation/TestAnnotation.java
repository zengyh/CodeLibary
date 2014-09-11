package annotation;

import java.lang.reflect.Field;


public class TestAnnotation {
	
     public static void main(String args[]) throws IllegalArgumentException, IllegalAccessException, SecurityException{
    	 Person person1 = new Person();
    	 person1.setName("first person");
    	 person1.setAge("1");
    	 Person person2 = new Person();
    	 person2.setName("second person");
      	 person2.setAge("2");
      	 
    	 printInfo(person1,person1.getClass().getDeclaredFields());
         printInfo(person2,person2.getClass().getDeclaredFields());
     }
     
     private static void printInfo(Person person, Field[] fields) throws IllegalArgumentException, IllegalAccessException{
    	 for(Field field:fields){
    		 NewAnnotation newannotation = field.getAnnotation(NewAnnotation.class);
    	     if(newannotation!=null){
    	    	 System.out.println(newannotation.annotationType().getName());
    	    	 System.out.print(newannotation.value()+": "); 
    	    	 System.out.print((String)field.get(person));
    	     }
    		 System.out.println("");
    	 }
     }

}
