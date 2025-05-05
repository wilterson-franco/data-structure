package com.wilterson.lru;

public interface LRUCache<K, V> {

    V putValue(K key, V value);

    V getValue(K key);
}