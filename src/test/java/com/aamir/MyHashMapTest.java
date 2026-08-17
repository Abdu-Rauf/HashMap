package com.aamir;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    private MyHashMap<String, Integer> map;

    @BeforeEach
    void setup() {
        map = new MyHashMap<>(4);
    }

    @Test
    void emptyMapReturnsNullForGet() {
        assertNull(map.get("apple"));
    }

    @Test
    void putThenGetReturnsCorrectValue() {
        map.put("Apple", 5);
        assertEquals(5, map.get("Apple"));
    }

    @Test
    void puttingSameKeyOverwritesValue() {
        map.put("Apple", 5);
        map.put("Apple", 10);
        assertEquals(10, map.get("Apple"));
    }

    @Test
    void putNullKeyThrowsException() {
        assertThrows(NullPointerException.class, () -> map.put(null, 1));
    }

    @Test
    void containsKeyThrowsForNullKey() {
        assertThrows(NullPointerException.class, () -> map.containsKey(null));
    }
    @Test
    void containsKeyReturnsFalseForAbsentKey() {
        map.put("Apple", 5);
        assertFalse(map.containsKey("Orange"));
    }
}