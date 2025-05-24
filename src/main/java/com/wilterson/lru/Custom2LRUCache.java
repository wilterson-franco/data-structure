package com.wilterson.lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * This is a custom solution that uses a LinkedList for keeping the order
 * of the items, as required by LRU, and a HashMap for ensuring O(1) time
 * complexity for getting and putting a new item. Updating an existing item
 * will be O(n) because bumping the existing item to the top of the
 * LinkedList requires a linear search.
 *
 * <p>It's important to note that this implementation assumes that there won't
 * be duplicate values in the cache, even if with different keys. Duplicate
 * values might result in a wrong item removed from the cache as there is
 * no way to determine the correct value in the LinkedList.
 *
 * <p>The unit test givenFullCacheWithTwoValuesUnderDifferentKeys_whenFirstItemRemoved_thenFirstItemShouldNotBeInTheCache
 * is falling due to this problem.
 */
public class Custom2LRUCache<K, V> implements LRUCache<K, V> {

    private final int capacity;
    private final Map<K, V> cacheMap;
    private final List<V> linkedList;

    public Custom2LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
        linkedList = new LinkedList<>();
    }

    @Override
    public V putValue(K key, V value) {

        if (cacheMap.containsKey(key)) {

            V existingValue = cacheMap.put(key, value);
            linkedList.remove(existingValue);
            linkedList.addFirst(value);

            return existingValue;
        }

        if (removeEldestEntryPolicy()) {
            removeEldestEntry();
        }

        cacheMap.put(key, value);
        linkedList.addFirst(value);

        return value;
    }

    @Override
    public V getValue(K key) {

        V value = cacheMap.get(key);

        if (value != null) {
            refreshItemAccess(value);
        }

        return value;
    }

    private boolean removeEldestEntryPolicy() {
        return cacheMap.size() >= capacity;
    }

    private V removeEldestEntry() {

        V removedItem = linkedList.removeLast();

        Optional<Entry<K, V>> optionalEntryMap = getKeyFromValue(removedItem);

        if (optionalEntryMap.isPresent()) {
            return cacheMap.remove(optionalEntryMap.get().getKey());
        }

        throw new IllegalStateException("Cache data is inconsistent");
    }

    private Optional<Entry<K, V>> getKeyFromValue(V removedItem) {
        return cacheMap.entrySet()
                .stream()
                .filter(entryMap -> entryMap.getValue().equals(removedItem))
                .findFirst();
    }

    private void refreshItemAccess(V value) {
        linkedList.remove(value);
        linkedList.addFirst(value);
    }
}
