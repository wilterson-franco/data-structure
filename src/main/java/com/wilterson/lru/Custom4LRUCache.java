package com.wilterson.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a custom solution that uses a custom linked list for keeping the
 * order of the items, as required by LRU, and a HashMap for ensuring O(1)
 * time complexity for searching, getting and putting a new item. Updating
 * an existing item takes O(n) time complexity because bumping it to the
 * top of the linked list requires a linear search.
 *
 * <p>This solution differs from the previous by having a custom linked list,
 * rather using the Java's LinkedList. It proves my capacity of implementing
 * such data structure.
 */
public class Custom4LRUCache<K, V> implements LRUCache<K, V> {

    private final int capacity;
    private final Map<K, V> cacheMap;
    private final LRUOrderedList<Node<K, V>> linkedList;

    public Custom4LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
        linkedList = new LRUDoublyLinkedList<>();
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

    private interface LRUOrderedList<T> {

        void addFirst(T data);

        T removeLast();

        T remove(T data);
    }

    static private class LRUDoublyLinkedList<T> implements LRUOrderedList<T> {

        private Element<T> head;
        private Element<T> tail;

        @Override
        public void addFirst(T data) {

            Element<T> element = new Element<>(data, head);

            if (head == null) {
                tail = head = element;
            } else {
                head.prev = element;
                head = element;
            }
        }

        @Override
        public T removeLast() {

            if (tail != null) {
                Element<T> lastElement = tail;

                if (tail == head) {
                    tail = head = null;
                    return lastElement.data;
                }

                tail = tail.prev;
                tail.next = null;
                return lastElement.data;
            }

            return null;
        }

        @Override
        public T remove(T data) {

            Element<T> element = head;

            while (element != null) {

                if (element.data.equals(data)) {

                    if (element != head) {
                        element.prev.next = element.next;
                    } else {
                        head = element.next;
                    }

                    if (element != tail) {
                        element.next.prev = element.prev;
                    } else {
                        tail = element.prev;
                    }

                    return element.data;
                }

                element = element.next;
            }

            return null;
        }

        static private class Element<T> {

            private final T data;
            private Element<T> next;
            private Element<T> prev;

            public Element(T data, Element<T> next) {
                this.data = data;
                this.next = next;
            }
        }
    }
}
