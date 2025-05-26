package com.wilterson.lru.simulation;

public interface SimLRUCache<K, V> {

    V putValue(K k, V v);

    V getValue(K k);
}