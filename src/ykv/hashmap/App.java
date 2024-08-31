package ykv.hashmap;

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
    }
}
