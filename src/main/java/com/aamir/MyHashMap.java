package com.aamir;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashMap<K,V> implements Iterable<K> {

    private int capacity;
    private double threshold;
    private int size = 0;

    ListNode<K,V>[] buckets;

    
    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = roundToPowerOf2(capacity);
        this.threshold = this.capacity*0.75;
        this.buckets = (ListNode<K,V>[]) new ListNode[capacity];
    }
    
    public MyHashMap() {
        this(16);
    }
    
    public int roundToPowerOf2(int capacity) {
        int result = 1;
        while (result < capacity) {
            result *= 2;
        }
        return result;
    }

    public int getBucketIndex(K key, int capacity) {
        int hashCode = key.hashCode();
        return hashCode & (capacity-1);
    }

    public void put(K key, V value) {
        if (key == null) throw new NullPointerException("key cannot be null");
        int index = getBucketIndex(key,capacity);

        ListNode<K,V> current = buckets[index];
        ListNode<K,V> prev = buckets[index];
        while(current!=null){
            if(current.key.equals(key)){
                current.value = value;
                return;
            }
            prev = current;
            current = current.next;
        }
        ListNode<K,V> toAdd = new ListNode<K,V>(key, value);
        if(prev==null){
            buckets[index] = toAdd;
        }else{
            prev.next = toAdd;
        }

        if(++size > threshold){
            resize();
        }

    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean initial = true;
        for (int i = 0; i < capacity; i++) {
            ListNode<K,V> current = buckets[i];
            while (current != null) {
                if (initial) {
                    sb.append(current.key).append(":").append(current.value);
                    initial = false;
                }
                else{
                    sb.append(", ").append(current.key).append(":").append(current.value);
                }
                current = current.next;

            }
        }
        sb.append("}");
        return sb.toString();
    }

    public V get(K key) {
        if (key == null) throw new NullPointerException("key cannot be null");
        int index = getBucketIndex(key,capacity);
        ListNode<K,V> current = buckets[index];

        while(current!=null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        if (key == null) throw new NullPointerException("key cannot be null");
        int index = getBucketIndex(key,capacity);
        ListNode<K,V> current = buckets[index];
        ListNode<K,V> prev = null;

        while (current!=null) {
            if (current.key.equals(key)) {
                if(prev == null){
                    buckets[index] = current.next;
                }else{
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public void resize() {
        int newCapacity = capacity*2;
        ListNode<K,V>[] newBucket = (ListNode<K,V>[]) new ListNode[newCapacity];

        for(int i=0;i<buckets.length;i++){
            ListNode<K,V> current = buckets[i];

            while(current!=null){
                ListNode<K,V> next = current.next;
                int newIdx = getBucketIndex(current.key, newCapacity);
                current.next = newBucket[newIdx];
                newBucket[newIdx] = current;
                current = next;
            }
        }
        buckets = newBucket;
        capacity = newCapacity;
        threshold = capacity*0.75;
    }

    public void printBucket() {
        for (int i = 0; i < capacity; i++) {
            System.out.print(i + ":");
            ListNode<K,V> current = buckets[i];
            while (current != null) {
                System.out.print("[" + current.key + "," + current.value + "]");
                current = current.next;
            }
            System.out.println();
        }
    }

    public boolean containsKey(K key) {
        if (key == null) throw new NullPointerException("key cannot be null");
        int index = getBucketIndex(key, capacity);

        ListNode<K,V> current = buckets[index];
        while(current!=null){
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;

    }
    @Override
    public Iterator<K> iterator() {
        return new MyIterator();
    }


    private class MyIterator implements Iterator<K> {
        int bucketIndex = 0;
        ListNode<K,V> current;

        MyIterator(){
            current = buckets[bucketIndex];
            advanceToNextBucket();
        }

        public void advanceToNextBucket() {
            while(current == null && bucketIndex<buckets.length-1){
                bucketIndex++;
                current = buckets[bucketIndex];
            }
        }

        public boolean hasNext() {
            return current!=null;
        }
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            K res = current.key;
            current = current.next;
            if (current==null) {
                advanceToNextBucket();
            }
            return res;
        }
    }
}
