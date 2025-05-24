package com.wilterson.lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This is a custom solution that uses a LinkedList for keeping the order
 * of the items, as required by LRU, and a HashMap for ensuring O(1) time
 * complexity for getting and putting a new item. Updating an existing item
 * will be O(n) because bumping the existing item to the top of the
 * LinkedList requires a linear search.
 *
 * <p>It's important to note that this solution fixes the problem from the
 * previous by having a linked list of Nodes. All unit tests are now
 * passing.
 */
public class Custom3LRUCache<K, V> implements LRUCache<K, V> {

    private final int capacity;
    private final Map<K, V> cacheMap;
    private final List<Node<K, V>> linkedList;

    public Custom3LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
        linkedList = new LinkedList<>();
    }

    @Override
    public V putValue(K key, V value) {

        if (cacheMap.containsKey(key)) {

            V existingValue = cacheMap.put(key, value);
            linkedList.remove(new Node<>(key, existingValue));
            linkedList.addFirst(new Node<>(key, value));

            return existingValue;
        }

        if (removeEldestEntryPolicy()) {
            removeEldestEntry();
        }

        cacheMap.put(key, value);
        linkedList.addFirst(new Node<>(key, value));

        return value;
    }

    @Override
    public V getValue(K key) {

        V value = cacheMap.get(key);

        if (value != null) {
            refreshItemAccess(new Node<>(key, value));
        }

        return value;
    }

    private boolean removeEldestEntryPolicy() {
        return cacheMap.size() >= capacity;
    }

    private V removeEldestEntry() {
        Node<K, V> removedNode = linkedList.removeLast();
        return cacheMap.remove(removedNode.key());
    }

    private void refreshItemAccess(Node<K, V> node) {
        linkedList.remove(node);
        linkedList.addFirst(node);
    }

    private record Node<K, V>(K key, V value) {

    }
}
