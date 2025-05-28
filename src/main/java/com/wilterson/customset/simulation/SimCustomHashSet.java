package com.wilterson.customset.simulation;

import java.util.ArrayList;
import java.util.List;


interface CustomSet<T> {

    void add(T element);

    boolean remove(T element);

    boolean contains(T element);

    int size();
}

public class SimCustomHashSet<T> implements CustomSet<T> {

    private static final int INITIAL_CAPACITY = 4;

    private int capacity;
    private List<Bucket> buckets;

    public SimCustomHashSet() {

        capacity = INITIAL_CAPACITY;
        buckets = new ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            buckets.add(new DoublyLinkedListBucket());
        }
    }

    @Override
    public void add(T element) {
        if (!contains(element)) {
            getBucketFor(element).add(element);
        }
    }

    @Override
    public boolean remove(T element) {
        return getBucketFor(element).remove(element);
    }

    @Override
    public boolean contains(T element) {
        return getBucketFor(element).contains(element);
    }

    @Override
    public int size() {
        return buckets.stream().mapToInt(bucket -> bucket.size()).sum();
    }

    private Bucket getBucketFor(T element) {
        return buckets.get(findBucketIndex(element));
    }

    private int findBucketIndex(T element) {
        return (capacity - 1) & element.hashCode();
    }
}

interface Bucket {

    void add(Object element);

    boolean remove(Object element);

    boolean contains(Object element);

    int size();
}

class DoublyLinkedListBucket implements Bucket {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(Object element) {

        size++;

        if (head == null) {
            head = tail = new Node(element, null, null);
            return;
        }

        head = new Node(element, head, null);
        head.next.prev = head;
    }

    @Override
    public boolean remove(Object element) {

        Node toRemove = findNode(element);

        if (toRemove != null) {

            if (head == tail) {
                head = tail = null;
            } else {

                if (isHead(toRemove)) {
                    toRemove.next.prev = null;
                    head = toRemove.next;
                } else {
                    toRemove.prev.next = toRemove.next;
                }

                if (isTail(toRemove)) {
                    toRemove.prev.next = null;
                    tail = toRemove.prev;
                } else {
                    toRemove.next.prev = toRemove.prev;
                }
            }

            size--;

            return true;
        }

        return false;
    }

    @Override
    public boolean contains(Object element) {
        return findNode(element) != null;
    }

    @Override
    public int size() {
        return size;
    }

    private Node findNode(Object element) {

        Node node = head;

        while (node != null) {
            if (node.data.equals(element)) {
                return node;
            }
            node = node.next;
        }

        return null;
    }

    private boolean isHead(Node node) {
        return node == head;
    }

    private boolean isTail(Node node) {
        return node == tail;
    }

    private static class Node {

        private Object data;
        private Node next;
        private Node prev;

        public Node(Object data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node(Object data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
