package edu.ykv.hashmap;

import edu.ykv.iterator.CustomIterator;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        CustomHashMap<String, Integer> customHashMap = new CustomHashMap<>();
        customHashMap.put("Alice", 20);
        customHashMap.put("Alice", 30);
        customHashMap.put("Bob", 25);
        customHashMap.put(null, 50);
        customHashMap.put(null, 40);

        System.out.println(customHashMap.get("Alice")); // Output: 30
        System.out.println(customHashMap.get("Bob")); // Output: 25
        System.out.println(customHashMap.get(null)); // Output: 40

        customHashMap.remove("Alice");
        System.out.println(customHashMap.get("Alice")); // Output: null
        customHashMap.remove(null);
        System.out.println();

        customHashMap.put("Jenny", 30);
        customHashMap.put("John", 25);
        customHashMap.put(null, 50);

        Map<String, Integer> maps = new HashMap<>();
        maps.put("Jenny", 30);


        //Варианты перебора пар key - value
        customHashMap.forEach(System.out::println);

        System.out.println("===== Iterable (entry set)=================================");
        customHashMap.entrySet().forEach(
                entry -> System.out.println(entry.key + " = " + entry.value)
        );

        System.out.println("======================================");
        customHashMap.entrySet().iterator().forEach(System.out::println);

        System.out.println("======================================");
        CustomIterator<CustomHashMap.Entry<String, Integer>> iterator = customHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println((iterator.next()));
        }

        System.out.println("======= key set iterator  ==============");
        CustomIterator<String> iterator2 = customHashMap.keySet().iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }

        System.out.println("======= values iterator  ==============");
        CustomIterator<Integer> iterator3 = customHashMap.values().iterator();
        while (iterator3.hasNext()) {
            System.out.println(iterator3.next());
        }

        System.out.println("======= values iterator (2) ==============");
        customHashMap.values().forEach(System.out::println);

        System.out.println("======= values iterator (3) ==============");
        customHashMap.values().iterator().forEach(System.out::println);


    }
}
