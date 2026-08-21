package com.myHashMap;

public class Main {
    public static void main(String[] args) {

        MyHashMap<String, Integer> map = new MyHashMap<>(4);

        // Test Null key & null map handling
        // map.put(null,0);
        System.out.println(map);
        // System.out.println(map.get(null));
        System.out.println(map.get("apple"));
        System.out.println(map.remove("apple"));
        for(String s:map){
            System.out.println(s);
        }

        map.put("Apple", 5);
        map.put("Banana", 3);
        map.put("Orange", 7);
        System.out.println("Initial map: " + map);

        // Test the iterator
        for(String s:map){
            System.out.println("key:" + s + ", value:" + map.get(s));
        }
        
        
        // Test Resizing and print bucket
        map.printBucket();
        map.put("bucketCheck",1);
        map.printBucket();

        // Test containsKey
        System.out.println(map);
        System.out.println(map.remove("Orange"));
        System.out.println(map.containsKey("Apple"));

        // Test get/put and remove
        map.put("Grapes", 9);
        map.put("Mango", 11);
        System.out.println("After adding Grapes and Mango: " + map);
        System.out.println("Get Mango: " + map.get("Mango"));
        System.out.println("Remove Mango: " + map.remove("Mango"));
        System.out.println("Final map: " + map);
    }
}