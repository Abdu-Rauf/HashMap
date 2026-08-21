package com.myList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] list;
    private int currIndex;

    public MyList(){
        this.list = (T[]) new Object[DEFAULT_CAPACITY];
        this.currIndex = 0;
    }

    public void add(T value){
        if (list.length==currIndex) {
            grow();
        }
        list[currIndex] = value;
        currIndex++;
    }

    public T get(int index) {
        if (index<0 || index>=currIndex) {
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

    private void grow(){
        T[] newList = (T[]) new Object[list.length + list.length / 2];

        for(int i=0;i<currIndex;i++){
            newList[i] = list[i];
        }
        list = newList;

    }

    public int indexOf(T value){
        for(int i=0;i<currIndex;i++){
            if (Objects.equals(list[i], value)) {
                return i;
            }
        }
        return - 1;
    }

    private void moveToTheLeft(int fromIndex){
        int elementsToMove = currIndex - fromIndex - 1;
        System.arraycopy(list, fromIndex + 1, list, fromIndex, elementsToMove);
        list[currIndex-1] = null;
    }

    public void remove(T val){
        int idx = indexOf(val);
        if (idx<0) {
            return;
        }
        moveToTheLeft(idx);
        currIndex--;
    }
    public boolean contains(T val){
        return indexOf(val) >=0;
    }
    public int size() {
        return currIndex;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("[");
        for (int i = 0; i < currIndex; i++) {
            if (i > 0) {
                string.append(",");
            }
            string.append(list[i]);
        }
        string.append("]");
        return string.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new myIterator();
    }
    private class myIterator implements Iterator<T>{
        int idx = 0;
        @Override
        public boolean hasNext() {
            return idx<currIndex;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return list[idx++];
        }

    }

}
