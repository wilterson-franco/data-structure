package com.wilterson.lru.simulation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LRUCacheWithLinkedList<K, V> implements SimLRUCache<K, V> {

    private int capacity;
    private Map<K, V> cacheMap;
    private List<Node<K, V>> linkedList;

    public LRUCacheWithLinkedList(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
        linkedList = new LinkedList<>();
    }

    public boolean removeElderlyElementPolicy() {
        return capacity <= cacheMap.size();
    }

    @Override
    public V putValue(K key, V value) {

        if (alreadyCached(key)) {
            V existingValue = cacheMap.put(key, value);
            linkedList.remove(new Node<>(key, existingValue));
            linkedList.addFirst(new Node<>(key, value));
            return existingValue;
        }

        if (removeElderlyElementPolicy()) {
            removeElderlyElement();
        }

        cacheMap.put(key, value);
        linkedList.addFirst(new Node<>(key, value));

        return value;
    }

    @Override
    public V getValue(K key) {

        V value = cacheMap.get(key);

        moveToHead(new Node<>(key, value));

        return value;
    }

    private boolean alreadyCached(K key) {
        return cacheMap.containsKey(key);
    }

    private void removeElderlyElement() {
        Node<K, V> node = linkedList.removeLast();
        cacheMap.remove(node.key);
    }

    private void moveToHead(Node<K, V> node) {
        linkedList.remove(node);
        linkedList.addFirst(node);
    }

    static private record Node<K, V>(K key, V value) {

    }
}