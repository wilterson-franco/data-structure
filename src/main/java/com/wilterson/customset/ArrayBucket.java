package com.wilterson.customset;

public class ArrayBucket implements Bucket {

    private static final int INITIAL_CAPACITY = 4;
    private final Object[] elements;
    private int tail;

    public ArrayBucket() {
        elements = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(Object data) {

        if (isFull()) {
            // TODO: expand the capacity
        }

        elements[tail] = data;
        tail++;
    }

    @Override
    public int size() {
        return tail;
    }

    @Override
    public boolean contain(Object element) {

        if (indexOf(element) == -1) {
            return false;
        }

        return true;
    }

    @Override
    public boolean remove(Object element) {

        int index = indexOf(element);

        if (index == -1) {
            return false;
        }

        if (index != elements.length - 1) {
            for (int i = index; i < elements.length - 1; i++) {
                elements[i] = elements[i + 1];
            }
        }

        tail--;
        elements[tail] = null;

        return true;
    }

    private boolean equal(Object left, Object right) {
        return (left == null) ? right == null : left.equals(right);
    }

    private boolean isFull() {
        return elements.length == tail;
    }

    private int indexOf(Object element) {

        for (int i = 0; i < tail; i++) {
            if (equal(elements[i], element)) {
                return i;
            }
        }

        return -1;
    }
}