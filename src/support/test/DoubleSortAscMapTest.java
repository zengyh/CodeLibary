package support.test;

import support.DoubleSortAscMap;

import java.util.Iterator;

/**
 * 文件名称: DoubleSortAscMapTest.java
 * 编写人: yh.zeng
 * 编写时间: 15-7-7 上午11:32
 * 文件描述: todo
 */
public class DoubleSortAscMapTest
{

    public static void main(String args[]) {
        DoubleSortAscMap<String, Double> map = new DoubleSortAscMap<String, Double>();
        map.put("b", new Double(2.0));
        map.put("c", new Double(3.0));
        map.put("a", new Double(1.0));

        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            System.out.println(key + ":" + map.get(key));
        }
    }

}
