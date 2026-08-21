package com.myHashMap;
public class ListNode<K,V> {
    
    final K key;
    V value;
    ListNode<K,V> next;

    public ListNode(K key, V value){
        this.key = key;
        this.value = value;
        this.next = null;
    }

}
