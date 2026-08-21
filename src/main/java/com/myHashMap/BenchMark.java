package com.myHashMap;

import java.util.HashMap;
import java.util.Random;

public class BenchMark {
    public static void main(String[] args) {
        int n = 100_000;
        int warmupIterations = 5;
        String[] keys = generateRandomKeys(n);

        System.out.println("=== Running Warmup ===");
        for (int w = 0; w < warmupIterations; w++) {
            warmupMyHashMap(keys);
            warmupJavaHashMap(keys);
        }

        System.out.println("\n=== Running Benchmark ===\n");

        // PUT operations
        MyHashMap<String, Integer> myMap = new MyHashMap<>();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            myMap.put(keys[i], i);
        }
        long myPutTime = System.nanoTime() - start;

        HashMap<String, Integer> javaMap = new HashMap<>();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            javaMap.put(keys[i], i);
        }
        long javaPutTime = System.nanoTime() - start;

        System.out.println("PUT Operations:");
        System.out.println("  MyHashMap put: " + myPutTime / 1_000_000 + " ms");
        System.out.println("  java.util.HashMap put: " + javaPutTime / 1_000_000 + " ms");

        // GET operations
        int myCheck = 0;
        start = System.nanoTime();
        for (String s : keys) {
            Integer v = myMap.get(s);
            if(v!=null) myCheck+=v;
        }
        long myGetTime = System.nanoTime() - start;

        int javaCheck = 0;
        start = System.nanoTime();
        for (String s : keys) {
            Integer v = javaMap.get(s);
            if(v!=null) javaCheck+=v;
        }
        long javaGetTime = System.nanoTime() - start;

        System.out.println("\nGET Operations:");
        System.out.println("  MyHashMap get: " + myGetTime / 1_000_000 + " ms");
        System.out.println("  java.util.HashMap get: " + javaGetTime / 1_000_000 + " ms");
        System.out.println("  Checksum Verified: " + (javaCheck==myCheck));

        // REMOVE operations
        start = System.nanoTime();
        for (String s : keys) {
            myMap.remove(s);
        }
        long myRemoveTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (String s : keys) {
            javaMap.remove(s);
        }
        long javaRemoveTime = System.nanoTime() - start;

        System.out.println("\nREMOVE Operations:");
        System.out.println("  MyHashMap remove: " + myRemoveTime / 1_000_000 + " ms");
        System.out.println("  java.util.HashMap remove: " + javaRemoveTime / 1_000_000 + " ms");
    }

    private static void warmupMyHashMap(String[] keys) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], i);
        }
        for (String key : keys) {
            map.get(key);
        }
    }

    private static void warmupJavaHashMap(String[] keys) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], i);
        }
        for (String key : keys) {
            map.get(key);
        }
    }

    private static String[] generateRandomKeys(int n) {
        String[] keys = new String[n];
        Random random = new Random(42);
        for (int i = 0; i < n; i++) {
            keys[i] = "key-" + random.nextLong();
        }
        return keys;
    }
}