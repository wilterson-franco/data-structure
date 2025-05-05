# LRU Algorithm

Implement a Cache data structure using a Least Recently Used algorithm. A Cache stores as many elements as possible, and when full, discards the least recently used items first. Please implement a constructor that takes the desired capacity as well as the put(K k, V v), and get(K k) methods.

## What is an LRU cache algorithm?
An LRU (Least Recently Used) cache is a caching algorithm designed to discard the least recently accessed items first when the cache reaches its maximum capacity.

### Key Concepts:
**Purpose**: Keep frequently accessed data quickly available while limiting memory usage.

**Eviction Policy**: When the cache is full and a new item needs to be added, the item that hasn't been used for the longest time (the least recently used) is removed.

**Access Updates**: Every time a cached item is accessed (read or written), it’s marked as "recently used."

### Typical Implementation:
To achieve fast operations (usually O(1) time for get and put), an LRU cache is often implemented with:

**HashMap (or Dictionary)** — for fast key-to-node lookups.

**Doubly Linked List** — to track the order of use.

### Operations:
**get(key)**: If the key exists, move the node to the head (mark as most recently used) and return the value.

**put(key, value)**:
- If the key exists
  - update the value and move it to the head.
- If the key doesn’t exist:
  - If the cache is at capacity
    - remove the tail node.
  - Insert the new key-value pair at the head.

### Example:
Assume cache capacity is 2:
- put(1, A) → Cache: {1:A}
- put(2, B) → Cache: {2:B, 1:A}
- get(1) → Accesses key 1 → Cache: {1:A, 2:B}
- put(3, C) → Key 2 is evicted → Cache: {3:C, 1:A}

## Solution 1

@Getter
public class LeastRecentlyUsedCache extends List<Element<T>> {

    private final int capacity;

    public LeastRecentlyUsedCache(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) {
        
        if (isFull()) {
            deleteItem(findLeastRecentlyAccessed());
        }

        this.add(element);
    }

    public T get(T element) {
        return this.get(this.indexOf(element));
    }

    private boolean isFull() {
        return this.size() >= capacity;
    }

    private void deleteItem(T element) {
        this.delete(element);
    }

    privatee T findLeastRecentlyAccessed() {
        return this.streams()
                    .sorting(Comparator::comparing(Element::accessdTime)
                    .sorted())
                    toList()
                    .get(0);
    }
}

class Element<T> {
    private T element;
    private LocalDateTime accessedTime;
}



## Solution 2

public class LRUCache<K, V> {

    private Map<K, V> cacheMap;
    private List<V> linkedList;
    private int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>(capacity);
        this.linkedList = new LinkedList<>(capacity);
    }

    public void put(K key, V value) {

        if (cacheMap.containKey(key)) {
            update(key, value)
        } else {
            add(key, value);
        }
    }

    public V get(K key) {
        return cacheMap.get(key);
    }

    private boolean isFull() {
        return linkedList.size() >= capacity;
    }

    private void update(K key,  value) {
        cacheMap.put(key, value);
        bumpElementPriority(value);
    }

    private void bumpElementPriority(V value) {
        findKey(value).ifPresent(key -> cacheMap.remove(key));
        linkedList.set(0, value);
    }

    private void addT(K key, V value) {

        if (isFull()) {
            var removedItem = linkedList.removeLast();
            findKey(removedItem).ifPresent(key -> cacheMap.remove(key));
        }

        linkedList.addFirst(value);
        cacheMap.put(key, value);
    }

    private Optional<K> findKey(V value) {
        return cacheMap.entrySet()
                    .stream()
                    .filter(entry -> entry.equals(value))
                    .map(entry -> entry.getKey())
                    .findFirst();
    }
}