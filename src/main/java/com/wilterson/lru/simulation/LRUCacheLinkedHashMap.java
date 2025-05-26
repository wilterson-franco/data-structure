package com.wilterson.lru.simulation;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap<K, V> extends LinkedHashMap<K, V> implements SimLRUCache<K, V> {

    private int capacity;

    public LRUCacheLinkedHashMap(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return capacity < size();
    }

    @Override
    public V putValue(K key, V value) {
        return put(key, value);
    }

    @Override
    public V getValue(K key) {
        return getOrDefault(key, null);
    }
}