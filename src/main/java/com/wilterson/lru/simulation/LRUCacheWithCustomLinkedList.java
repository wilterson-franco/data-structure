package com.wilterson.lru.simulation;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheWithCustomLinkedList<K, V> implements SimLRUCache<K, V> {

    private int capacity;
    private Map<K, V> cacheMap;
    private LRUList<Node<K, V>> linkedList;

    public LRUCacheWithCustomLinkedList(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
        linkedList = new CustomLinkedList<>();
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

        if (value != null) {
            moveToHead(new Node<>(key, value));
        }

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

    private record Node<K, V>(K key, V value) {

    }

    static private class CustomLinkedList<T> implements LRUList<T> {

        private Element<T> head;
        private Element<T> tail;
        private int size;

        public void addFirst(T node) {

            Element<T> element = new Element<>(node, head, null);

            if (head == null) {
                head = tail = element;
                return;
            }

            head.prev = element;
            head = element;

        }

        public T removeLast() {

            if (tail == null) {
                return null;
            }

            Element<T> toRemove = tail;

            if (tail == head) {
                tail = head = null;
                return toRemove.data;
            }

            tail = tail.prev;
            toRemove.prev.next = null;

            return toRemove.data;
        }

        public T remove(T data) {

            Element<T> element = head;

            while (element != null) {

                if (element.data.equals(data)) {

                    if (element != head) {
                        element.prev.next = element.next;
                    } else {
                        head = head.next;
                        element.prev = null;
                    }

                    if (element != tail) {
                        element.next.prev = element.prev;
                    } else {
                        tail = tail.prev;
                        element.next = null;
                    }

                    return element.data;

                }

                element = element.next;
            }

            return null;
        }

        static private class Element<T> {

            private T data;
            private Element<T> next;
            private Element<T> prev;

            public Element(T data, Element<T> next, Element<T> prev) {
                this.data = data;
                this.next = next;
                this.prev = prev;
            }
        }
    }

    private interface LRUList<T> {

        void addFirst(T node);

        T removeLast();

        T remove(T node);

    }
}