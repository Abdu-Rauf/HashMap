package com.myList;

public class Main {

    public static void main(String[] args) {
        MyList<String> list = new MyList<>();

        System.out.println("Empty list: " + list);
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");
        list.add(null);
        System.out.println("Initial list: " + list);

        System.out.println("Iterating over list:");
        for (String value : list) {
            System.out.println("value: " + value);
        }

        System.out.println("Get index 1: " + list.get(1));
        System.out.println("Index of Orange: " + list.indexOf("Orange"));
        System.out.println("Index of null: " + list.indexOf(null));
        System.out.println("Contains Apple: " + list.contains("Apple"));
        System.out.println("Contains Mango: " + list.contains("Mango"));

        list.remove("Banana");
        System.out.println("After removing Banana: " + list);
        list.remove(null);
        System.out.println("After removing null: " + list);

        for (int i = 1; i <= 10; i++) {
            list.add("Item" + i);
        }
        System.out.println("After resizing: " + list);
        System.out.println("Size: " + list.size());
    }
}
