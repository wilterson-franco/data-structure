package com.wilterson.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomLRUCache<K, V> implements LRUCache<K, V> {

    private Map<K, Element<V>> cacheMap;
    private DoublyLinkedList<V> linkedList;
    private int capacity;

    public CustomLRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>(capacity);
        this.linkedList = new DoublyLinkedList<>();
    }

    @Override
    public V putValue(K key, V value) {

        Element<V> element = new Element<>(value);

        if (cacheMap.containsKey(key)) {
            Element<V> previous = update(key, element);
            return previous.value;
        }

        add(key, element);

        return value;
    }

    @Override
    public V getValue(K key) {
        var element = cacheMap.get(key);

        if (element != null) {
            bumpElementPriority(element);
            return element.value;
        }

        return null;
    }

    private boolean isFull() {
        return cacheMap.size() >= capacity;
    }

    private Element<V> update(K key, Element<V> value) {
        Element<V> previous = cacheMap.put(key, value);
        bumpElementPriority(value);
        return previous;
    }

    private void bumpElementPriority(Element<V> element) {
        linkedList.remove(element);
        linkedList.addFirst(element);
    }

    private void add(K key, Element<V> element) {

        if (isFull()) {
            Element<V> removedElement = linkedList.removeLast();
            findKey(removedElement).ifPresent(removedElementKey -> cacheMap.remove(removedElementKey));
        }

        linkedList.addFirst(element);
        cacheMap.put(key, element);
    }

    private Optional<K> findKey(Element<V> element) {
        return cacheMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().value.equals(element.value))
                .map(entry -> entry.getKey())
                .findFirst();
    }

    private static class Element<T> {

        private final T value;

        private Element<T> next, previous;

        public Element(T value) {
            this.value = value;
        }
    }

    private static class DoublyLinkedList<T> {

        private Element<T> head;
        private Element<T> tail;

        public void addFirst(Element<T> element) {

            if (head == null) {
                head = element;
                tail = element;
                return;
            }

            element.next = head;
            element.previous = null;
            head.previous = element;

            head = element;
        }

        public void remove(Element<T> element) {

            if (element.previous != null) {
                element.previous.next = element.next;
            } else {
                head = element.next;
            }

            if (element.next != null) {
                element.next.previous = element.previous;
            } else {
                tail = element.previous;
            }

            element.next = null;
            element.previous = null;
        }

        public Element<T> removeLast() {
            var oldTail = tail;
            remove(tail);
            return oldTail;
        }
    }
}
