package com.wilterson.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapLRUCache<K, V> extends LinkedHashMap<K, V> implements LRUCache<K, V> {

    private final int capacity;

    public LinkedHashMapLRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return capacity < size();
    }

    @Override
    public V putValue(K key, V value) {
        return super.put(key, value);
    }

    @Override
    public V getValue(K key) {
        return getOrDefault(key, null);
    }
}
