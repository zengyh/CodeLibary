package support;

import java.util.*;

/**
 * 文件名称: IntegerSortAscMap.java
 * 编写人: yh.zeng
 * 编写时间: 14-12-1 下午3:42
 * 文件描述: 按照value升序排序
 */
public class IntegerSortAscMap<K, V extends Integer> extends TreeMap<K, V>
{
    private Map<K, Integer> sortValuesMap = new HashMap<K, Integer>();

    private Comparator<K> hiddenComparator = new Comparator<K>()
    {
        @Override
        public int compare(Object key1, Object key2) {
            if(key1.equals(key2)){
                return 0;
            }
            int compareValue = sortValuesMap.get(key1).compareTo(sortValuesMap.get(key2));
            if (compareValue == 0) {
                return 1;
            } else {
                return compareValue;
            }
        }
    };

    private TreeMap<K, V> realMap = new TreeMap<K, V>(hiddenComparator);

    public IntegerSortAscMap() {
    }

    public IntegerSortAscMap(SortedMap<K, ? extends V> m) {
        if (m != null) {
            Iterator<K> iterator = m.keySet().iterator();
            while (iterator.hasNext()) {
                K key = iterator.next();
                put(key, m.get(key));
            }
        }
    }

    public IntegerSortAscMap(Map<? extends K, ? extends V> m) {
        if (m != null) {
            Iterator iterator = m.keySet().iterator();
            while (iterator.hasNext()) {
                K key = (K) iterator.next();
                put(key, m.get(key));
            }
        }
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        if (map != null) {
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                K key = (K) iterator.next();
                put(key, map.get(key));
            }
        }
    }

    public V put(K key, V value) {
        sortValuesMap.put(key, value);
        return realMap.put(key, value);
    }

    public V remove(Object key) {
        V value = realMap.remove(key);
        sortValuesMap.remove(key);
        return value;
    }

    public void clear() {
        realMap.clear();
        sortValuesMap.clear();
    }

    public Object clone() {
        return super.clone();
    }

    public int size() {
        return realMap.size();
    }

    public boolean containsKey(Object key) {
        return realMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return realMap.containsValue(value);
    }

    public V get(Object key) {
        return realMap.get(key);
    }

    public Comparator<? super K> comparator() {
        return hiddenComparator;
    }

    public K firstKey() {
        return realMap.firstKey();
    }

    public K lastKey() {
        return realMap.lastKey();
    }

    public Map.Entry<K, V> firstEntry() {
        return realMap.firstEntry();
    }

    public Map.Entry<K, V> lastEntry() {
        return realMap.lastEntry();
    }

    public Map.Entry<K, V> pollFirstEntry() {
        return realMap.pollFirstEntry();
    }

    public Map.Entry<K, V> pollLastEntry() {
        return realMap.pollLastEntry();
    }

    public Map.Entry<K, V> lowerEntry(K key) {
        return realMap.lowerEntry(key);
    }

    public K lowerKey(K key) {
        return realMap.lowerKey(key);
    }

    public Map.Entry<K, V> floorEntry(K key) {
        return realMap.floorEntry(key);
    }

    public K floorKey(K key) {
        return realMap.floorKey(key);
    }

    public Map.Entry<K, V> ceilingEntry(K key) {
        return realMap.ceilingEntry(key);
    }

    public K ceilingKey(K key) {
        return realMap.ceilingKey(key);
    }

    public Map.Entry<K, V> higherEntry(K key) {
        return realMap.higherEntry(key);
    }

    public K higherKey(K key) {
        return realMap.higherKey(key);
    }

    public Set<K> keySet() {
        return realMap.keySet();
    }

    public NavigableSet<K> navigableKeySet() {
        return realMap.navigableKeySet();
    }

    public NavigableSet<K> descendingKeySet() {
        return realMap.descendingKeySet();
    }

    public Collection<V> values() {
        return realMap.values();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return realMap.entrySet();
    }


    @Override
    public boolean isEmpty() {
        return realMap.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
